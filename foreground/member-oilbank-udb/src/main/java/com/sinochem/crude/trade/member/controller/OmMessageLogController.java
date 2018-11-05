package com.sinochem.crude.trade.member.controller;

import com.alibaba.fastjson.JSONObject;
import com.eyeieye.melody.web.url.URLBroker;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.http.MessageHttpUtil;
import com.sinochem.crude.trade.member.contact.UrlMapping;
import com.sinochem.crude.trade.member.domain.Member;
import com.sinochem.crude.trade.member.domain.MessageLog;
import com.sinochem.crude.trade.member.domain.MessageLogMember;
import com.sinochem.crude.trade.member.enums.EnumMessageLogType;
import com.sinochem.crude.trade.member.service.MemberCredentialsService;
import com.sinochem.crude.trade.member.service.MessageLogService;
import com.sinochem.crude.trade.member.service.PersonService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.member.util.LayEditResult;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Controller
@RolesAccess("admin")
public class OmMessageLogController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OmMessageLogController.class);

    @Autowired
    private MessageLogService messageLogService;

    @Autowired
    private MemberCredentialsService memberCredentialsService;

    @Autowired
    private PersonService personService;

    @Autowired
    private URLBroker uploadServerBroker;

    @Autowired
    private HttpConnectionManager httpConnectionManager;

    @RightAccess(130)
    @RequestMapping(value = UrlMapping.OM_MESSAGE_LOG_LIST)
    public void list(MessageLog messageLog, PageInfo pageInfo, ModelMap modelMap) {
        modelMap.put("page", new PageInfoResult(this.messageLogService.getLogList(messageLog, pageInfo)));
        modelMap.put("types", EnumMessageLogType.values());
        modelMap.put("messageLog", messageLog);
    }

    @RightAccess(131)
    @RequestMapping(value = UrlMapping.OM_MESSAGE_LOG_ADD, method = RequestMethod.GET)
    public void toAdd(ModelMap modelMap) {
        modelMap.put("types", EnumMessageLogType.values());
        modelMap.put("credentials", this.memberCredentialsService.getCredentials(true));
        modelMap.put("roles", this.personService.getRoles());
    }

    @RightAccess(135)
    @RequestMapping(value = UrlMapping.OM_MESSAGE_LOG_MEMBER_FITTER)
    @ResponseBody
    public ResultDatas credentialMember(String code, String role) {
        ResultDatas res = new ResultDatas();
        try {
            List<Member> members = null;
            if (StringUtils.isNotBlank(code)) members = this.personService.selectMembersByCredential(code);
            else if (StringUtils.isNotBlank(role)) members = this.personService.selectMembersByRole(role);
            res.setDatas(members);
        } catch (BizException be) {
            LOGGER.error(be.toString());
            res.setFail(be.getMessage());
        }
        return res;
    }

    @RightAccess(133)
    @RequestMapping(value = UrlMapping.OM_MESSAGE_LOG_DETAIL, method = RequestMethod.GET)
    public void detail(ModelMap modelMap, MessageLog messageLog) {
        modelMap.put("types", EnumMessageLogType.values());
        modelMap.put("messageLog", this.messageLogService.getLogDetail(messageLog.getId()));
    }

    /**
     * @param messageLog
     * @param messageLogMember
     * @param user
     * @param credentials
     * @param roles
     * @param toId1            如果为-1 ， 则推送所有账号
     * @param delayTime
     * @param periodTime
     * @return
     */
    @RightAccess(132)
    @RequestMapping(value = UrlMapping.OM_MESSAGE_LOG_SAVE, method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas add(MessageLog messageLog, MessageLogMember messageLogMember, CurrentUser user, String credentials, String roles, String toId1, String delayTime, String periodTime) {
        ResultDatas res = new ResultDatas();
        try {
            this.messageLogService.add(messageLog, messageLogMember, user.getMemberId(), credentials, roles, toId1, delayTime, periodTime);
        } catch (BizException be) {
            LOGGER.error(be.toString());
            res.setFail(be.getMessage());
        }
        return res;
    }

    @RequestMapping(value = UrlMapping.OM_MESSAGE_LOG_RESEND, method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas resend(CurrentUser user, Long messageLogId, Long messageLogMemberId) {
        ResultDatas res = new ResultDatas();
        try {
            this.messageLogService.reSend(user.getMemberId(), messageLogId, messageLogMemberId);
        } catch (BizException be) {
            LOGGER.error(be.toString());
            res.setFail(be.getMessage());
        }
        return res;
    }


    /**
     * 启动任务
     *
     * @param messageLogId
     * @return
     */
    @RightAccess(134)
    @RequestMapping(value = UrlMapping.OM_MESSAGE_LOG_START_JOB, method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas startJob(Long messageLogId) {
        ResultDatas res = new ResultDatas();
        try {
            this.messageLogService.addPushTask(messageLogId);
        } catch (BizException be) {
            LOGGER.error(be.toString());
            res.setFail(be.getMessage());
        }
        return res;
    }

    /**
     * 富文本编辑器图片上传
     *
     * @param file
     * @return
     */
    @RequestMapping(value = UrlMapping.OM_MESSAGE_LOG_IMG_UPLOAD, method = RequestMethod.POST)
    @ResponseBody
    public LayEditResult imgUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        LayEditResult layEditResult;
        File f = null;
        try {
            String tempFileDir = request.getContextPath() + "/tempFile";
            File folder = new File(tempFileDir);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            f = new File(tempFileDir + "/" + file.getOriginalFilename());
            file.transferTo(f);
            String url = uploadServerBroker.get("/upload/file.json").toString();
            String res = MessageHttpUtil.upload(httpConnectionManager.getHttpClient(), url, f);
            JSONObject resJson = JSONObject.parseObject(res);
            layEditResult = LayEditResult.setSuccess("成功", resJson.getString("path"), null);
        } catch (BizException e) {
            e.printStackTrace();
            layEditResult = LayEditResult.setFail(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            layEditResult = LayEditResult.setFail("上传接口请求失败!");
        } finally {
            if (f != null) f.delete();
        }
        return layEditResult;
    }

}

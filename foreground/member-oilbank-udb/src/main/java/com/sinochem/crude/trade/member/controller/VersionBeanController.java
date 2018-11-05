package com.sinochem.crude.trade.member.controller;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.member.contact.MsgConstant;
import com.sinochem.crude.trade.member.contact.UrlMapping;
import com.sinochem.crude.trade.member.domain.VersionBean;
import com.sinochem.crude.trade.member.model.VersionBeanVO;
import com.sinochem.crude.trade.member.service.VersionBeanService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * @Made By WangTing
 * 本类用使用后台控制app版本的controller层
 *
 */

@Controller
@RolesAccess("admin")
public class VersionBeanController {

    Logger logger = LoggerFactory.getLogger(VersionBeanController.class);

    @Autowired
    private VersionBeanService versionBeanService;
    @Value("${aliyun.oss.bucket}")
    private String bucket;

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    /**
     * 查询所有列表
     * @param modelMap
     * @return
     */
    @RightAccess(113)
    @RequestMapping(UrlMapping.OM_APP_LIST)
    public String  selectAll(CurrentUser user,ModelMap modelMap) throws BizException {
        if(user == null || user.getMemberId() == null){
            return "/welcome";
        }

        try {
            List<VersionBeanVO> list = versionBeanService.selectAll();
            modelMap.put("list",list);
        } catch (Exception e) {
            logger.error("获取列表异常：" , e);
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO7));
        }
        return "om/app/list";

    }

    @RightAccess(114)
    @RequestMapping(UrlMapping.OM_APP_SELECTBYPRIMARYKEY)
    public void  selectByPrimaryKey(CurrentUser user,ModelMap modelMap,Long id) throws BizException {

        try {
            VersionBeanVO versionBeanVO = versionBeanService.selectByPrimaryKey(id);
            modelMap.put("versionBeanVO",versionBeanVO);
        } catch (Exception e) {
            logger.error("获取列表异常：" , e);
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO7));
        }

    }

    @RightAccess(115)
    @RequestMapping(UrlMapping.OM_APP_EDIT)
    public String  edit(CurrentUser user,ModelMap modelMap,Long id) throws BizException {
        this.selectByPrimaryKey(user,modelMap,id);
        return "om/app/edit";
    }

    @RightAccess(116)
    @RequestMapping(UrlMapping.OM_APP_UPDATE)
    @ResponseBody
    public Result  update(CurrentUser user,VersionBean versionBean){
        Result result = new Result();
        if(user == null || user.getMemberId() == null){
            result.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO8));
            result.setStatus(Result.ERROR);
            return result;
        }
        try {
            versionBean.setUpdateTime(new Date());
            versionBean.setUpdateUser(user.getMemberId());
            versionBeanService.update(versionBean);
        } catch (Exception e) {
            logger.error("更新数据异常" );
            result.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO22));
            result.setStatus(Result.ERROR);
            return result;
        }
        result.setStatus(Result.SUCCESS);
        return result;
    }

    @RightAccess(117)
    @RequestMapping(UrlMapping.OM_APP_SAVE)
    @ResponseBody
    public Result save(CurrentUser user, VersionBean versionBean){
        Result result = new Result();
        if(user == null || user.getMemberId() == null){
            result.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO8));
            result.setStatus(Result.ERROR);
            return result;
        }
        try {
            versionBean.setCreateTime(new Date());
            versionBean.setCreateUser(user.getMemberId());
            versionBean.setUpdateTime(new Date());
            versionBean.setUpdateUser(user.getMemberId());
            versionBeanService.add(versionBean);
        } catch (Exception e) {
            logger.error("保存数据异常" );
            result.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO10));
            result.setStatus(Result.ERROR);
            return result;
        }
        result.setStatus(Result.SUCCESS);
        return result;
    }


    //刪除
    @RightAccess(118)
    @RequestMapping(UrlMapping.OM_APP_DELETE)
    @ResponseBody
    public Result  delete(CurrentUser user, Long id){
        Result result = new Result();
        if(user == null || user.getMemberId() == null){
            result.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO8));
            result.setStatus(Result.ERROR);
            return result;
        }
        try {
            versionBeanService.delete(id);
        } catch (Exception e) {
            logger.error("删除数据异常" );
            result.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO19));
            result.setStatus(Result.ERROR);
            return result;
        }
        result.setStatus(Result.SUCCESS);
        return result;
    }

    //获取最新版本
    @RequestMapping(UrlMapping.OM_APP_GETNEWVERSION)
    @ResponseBody
    @WithoutAccess
    public ResultDatas getNewVersion(String versionLanguage){
        if(versionLanguage == null){
            versionLanguage = "zh";
        }
        List<VersionBeanVO> list = null;
        if(versionLanguage.equals("en")) {
            list = versionBeanService.selectByVersionLanguage(versionLanguage);
        }else{
            list = versionBeanService.selectByVersionLanguage("zh");
        }
        ResultDatas resultDatas = new ResultDatas();
        if(list != null && list.size() > 0){
            VersionBeanVO versionBeanVO = list.get(0);
            if(StringUtil.isNotEmpty(versionBeanVO.getUrl())){
                String ossPath = getOssPath(versionBeanVO.getUrl());
                if(StringUtil.isNotEmpty(ossPath)){
                    versionBeanVO.setOssPath(ossPath);
                    resultDatas.setDatas(versionBeanVO);
                }else{
                    resultDatas.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO23));
                }
            }else{
                resultDatas.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO23));
            }
        }else{
            resultDatas.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO23));
        }
        return resultDatas;
    }


    public String getOssPath(String filePath){
        OSSClient ossClient = null;
        String ossPath = "";
        try {
            ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            // 设置URL过期时间为1小时
            Date expiration = new Date(new Date().getTime() + 3600 * 1000);
            ossPath = ossClient.generatePresignedUrl(bucket, filePath ,expiration, HttpMethod.GET).toString();
        }catch (Exception e) {
            logger.error("文件下载失败", e);
            return ossPath;
        } finally {
            if(ossClient != null){
                ossClient.shutdown();
            }
        }
        return ossPath;
    }
}

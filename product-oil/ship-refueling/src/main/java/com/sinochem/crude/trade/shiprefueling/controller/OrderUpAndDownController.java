package com.sinochem.crude.trade.shiprefueling.controller;

import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shiprefueling.constants.Constants;
import com.sinochem.crude.trade.shiprefueling.controller.common.ResultDatas;
import com.sinochem.crude.trade.shiprefueling.domain.po.Chments;
import com.sinochem.crude.trade.shiprefueling.enums.ExceptionEnum;
import com.sinochem.crude.trade.shiprefueling.service.ChmentsService;
import com.sinochem.crude.trade.shiprefueling.service.OrderUpAndDownService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * 文件上传下载
 */
@Controller
@RequestMapping("order")
@WithoutAccess
public class OrderUpAndDownController {
    public static final Log log = LogFactory.getLog(RIgnitionController.class);

    @Autowired
    private OrderUpAndDownService fileUpAndDownService;
    @Autowired
    private ChmentsService chmentsService;

    /**
     * 上游订单导入订单模板下载
     *
     * @param request
     * @param resp
     */
    @RequestMapping(value = "/upexcelmodel.json")
    public void upstreamExcel(HttpServletRequest request, HttpServletResponse resp) {
        String filename = "upstreamOrder.xlsx";
        downExcel(request, resp, filename);
    }

    /**
     * 上游订单导入订单模板下载
     *
     * @param request
     * @param resp
     */
    @RequestMapping(value = "/downexcelmodel.json")
    public void downstreamExcel(HttpServletRequest request, HttpServletResponse resp) {
        String filename = "downstreamOrder.xlsx";
        downExcel(request, resp, filename);
    }

    private void downExcel(HttpServletRequest request, HttpServletResponse resp, String filename) {
        //下载文件路径
        String path = request.getServletContext().getRealPath("/WEB-INF/classes/template");
        File file = new File(path + File.separator + filename);
        OutputStream os = null;
        if (file.exists()) {
            try {
                os = new BufferedOutputStream(resp.getOutputStream());
                resp.setContentType("application/octet-stream");
                if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
                    //IE浏览器
                    filename = URLEncoder.encode(filename, "UTF-8");
                } else {
                    //其他浏览器
                    filename = URLDecoder.decode(filename + ".xls");
                }

                // 指定下载的文件名
                resp.setHeader("Content-disposition", "attachment; filename="
                        + new String(filename.getBytes("utf-8"), "ISO8859-1"));
                os.write(FileUtils.readFileToByteArray(file));
                os.flush();
            } catch (IOException e) {
                log.error("文件下载异常:{}", e);
            } finally {
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {
                        log.error("文件下载流关闭失败");
                    }
                }
            }

        }
    }

    /**
     * 船燃订单和船供订单附件上传
     *
     * @param file
     * @param user
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "fileupload.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas<Object> upload(@RequestParam("file") MultipartFile file, CurrentUser user,
                                     @RequestParam Map<String, Object> map) throws Exception {

        ResultDatas<Object> res = new ResultDatas<>();
        if (null == user) {
            //没有权限
            BizException bizException = new BizException();
            bizException.setCode(ExceptionEnum.NO_AUTHORIZATION.getCode());
            throw bizException;
        }

        if (map == null
                || map.get("businessType") == null
                || map.get("fileType") == null
                || map.get("orderId") == null) {

            throw  new BizException("请求参数错误");
        }

        try {
            String url = fileUpAndDownService.uploadFile(
                    file, Long.parseLong(String.valueOf(map.get("orderId"))),
                    String.valueOf(map.get("businessType")), String.valueOf(map.get("fileType")), user);

            res.setDatas(url);
        } catch (Exception e) {
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        }
        return res;
    }


    /**
     * 删除附件
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "deletefile.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas deleteFile(@RequestBody Map<String, Object> map) throws BizException {
        ResultDatas<Object> res = new ResultDatas<>();
        if (map == null || map.isEmpty() || map.get("attachId") == null) {
            throw new BizException("请求参数不合法");
        }
        try {
            fileUpAndDownService.deleteFileByAttachId(Long.parseLong(map.get("attachId").toString()));
        } catch (Exception e) {
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        }
        return res;
    }


    /**
     * 已上传的单据查询
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "uploadedfiles.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas getUploadedFiles(@RequestBody Map<String, Object> map) throws BizException {
        ResultDatas res = new ResultDatas();
        if (map == null || map.get("orderId") == null) {
            throw new BizException("请求参数不正确");
        }
        try {
            List<Chments> chments = chmentsService.queryUploadedList(Long.parseLong(map.get("orderId").toString()));
            res.setDatas(chments);
        } catch (Exception e) {
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        }

        return res;
    }
}

package com.sinochem.crude.trade.shiprefueling.controller;

import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shiprefueling.constants.Constants;
import com.sinochem.crude.trade.shiprefueling.controller.common.KeyGenUtils;
import com.sinochem.crude.trade.shiprefueling.controller.common.ResultDatas;
import com.sinochem.crude.trade.shiprefueling.domain.po.Chments;
import com.sinochem.crude.trade.shiprefueling.enums.BusinessType;
import com.sinochem.crude.trade.shiprefueling.service.impl.FileUploadServiceImpl;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by songhaiqiang on 2018/6/2.
 */
@Controller
@WithoutAccess
public class  FileUpload {

    @Autowired
    private FileUploadServiceImpl fileUploadService;


    public static final Log log = LogFactory.getLog(ChmentsController.class);

    @RequestMapping(value = "file/upload.json" , method = POST)
    @ResponseBody
    public ResultDatas<String> uploadHasInfoChments(@RequestParam("file") MultipartFile file){
        ResultDatas<String> res=new ResultDatas<String>();
        try{
            String url = fileUploadService.upload(file);
            res.setDatas(url);
        }catch (BizException e){
            log.error(e);
            res.setFail(e.getMessage() , Constants.EXCEPTION_STATUS);
        }catch (IOException e){
            log.error(e);
            res.setFail(e.getMessage() , Constants.EXCEPTION_STATUS);
        }
        return res;
    }

    @RequestMapping(value = "file/deleteObj.json" ,method = {POST , GET})
    @ResponseBody
    public ResultDatas<String> deleteObject(@RequestParam(value = "path" , required = true) String path){
        ResultDatas<String> res=new ResultDatas<String>();
        try{
            fileUploadService.deleteFile(path);
        }catch (Exception e){
            e.printStackTrace();
            res.setMessage(Constants.EXCEPTION_MESSAGE);
            res.setStatus(Constants.EXCEPTION_STATUS);
        }
        return res;
    }
}

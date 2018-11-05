package com.sinochem.crude.trade.shiprefueling.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSErrorCode;
import com.aliyun.oss.ServiceException;
import com.sinochem.crude.trade.shiprefueling.service.ChmentsService;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

/**
 * Created by songhaiqiang on 2018/6/2.
 */
@Service
public class FileUploadServiceImpl {

    @Autowired
    private OssServiceImpl ossService;


    public String upload(MultipartFile file)throws BizException , IOException{
        String fileName = file.getOriginalFilename();
        String path = ossService.getPath(fileName.substring(fileName.lastIndexOf(".")));
        return ossService.upload(file.getInputStream() , path);
    }



    public void deleteFile(String path){
        ossService.deleteSingleObect(path);
    }

}

package com.sinochem.crude.trade.member.service.udbservice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sinochem.crude.trade.member.contact.UdbUrlMapping;
import com.sinochem.crude.trade.member.helper.HttpClientHelper;
import com.sinochem.crude.trade.member.model.udbvo.FileUdbVo;
import com.sinochem.crude.trade.member.util.UdbResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by wgp on 2018/8/7.
 */
@Component
public class FileUdbService {

    @Value("${UDB.webSite}")
    private String WEBSITE;

    @Autowired
    private HttpClientHelper helper;

    public UdbResult uploadFile(){
       String result =  helper.httpGet("webSite",WEBSITE, UdbUrlMapping.OSS_POLICY);
       UdbResult<FileUdbVo> udbResult = JSON.parseObject(result,new TypeReference<UdbResult<FileUdbVo>>(){});
       return udbResult;
    }

}

/*
 * Copyright (c) 2016. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.shiprefueling.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.*;
import com.google.common.base.Strings;
import com.google.common.io.ByteStreams;
import com.google.common.io.Closer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by HuangWj on 2016/11/2. FileManager implementation for aliyun oss
 */
@Service
public class OssFileManager{

    private static final Log log = LogFactory.getLog(OssFileManager.class);

    private OSSClient ossClient;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessId;
    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.oss.endpoint}")
    private String showHost;

    public OssFileManager(){}

    public OssFileManager(OSSClient ossClient, String accessId, String showHost) {
        this.ossClient = ossClient;
        this.accessId = accessId;
        this.showHost = showHost;
    }

    @Bean(destroyMethod = "shutdown")
    public OSSClient ossClient() {
        OSSClient ossClient = new OSSClient(showHost, accessId, accessKeySecret);
        log.info("Aliyun oss client setup complete.");
        return ossClient;
    }

    private String getBasePath(String endpoint, String bucket) {
        if (endpoint.startsWith("http://")) {
            StringBuilder endpointBuilder = new StringBuilder(endpoint);
            return endpointBuilder.insert(7, bucket + ".").toString();
        } else if (endpoint.startsWith("https://")) {
            StringBuilder endpointBuilder = new StringBuilder(endpoint);
            return endpointBuilder.insert(8, bucket + ".").toString();
        }
        return bucket + "." + endpoint;
    }

    public String save(InputStream data, String group, String path) throws IOException {
        if (!ossClient.doesBucketExist(group)) {
            ossClient.createBucket(group);
            ossClient.setBucketAcl(group, CannedAccessControlList.PublicRead);
        }
        ossClient.putObject(group, path, data);

        return "/" + path;
    }

    public byte[] read(String group, String path) throws IOException {
        return read(group, path, null);
    }

    public byte[] read(String group, String path, String params) throws IOException {
        Closer closer = Closer.create();
        GetObjectRequest request = new GetObjectRequest(group, path);
        if (!Strings.isNullOrEmpty(params)) {
            request.setProcess(params);
        }
        byte[] bytes;
        try {
            OSSObject object = ossClient.getObject(request);
            InputStream inputStream = closer.register(object.getObjectContent());
            ByteArrayOutputStream outputStream = closer.register(new ByteArrayOutputStream());
            //noinspection ResultOfMethodCallIgnored
            ByteStreams.copy(inputStream, outputStream);
            bytes = outputStream.toByteArray();
        } catch (Throwable e) {
            throw closer.rethrow(e);
        } finally {
            closer.close();
        }
        return bytes;
    }

    public Map<String, String> getPolicy(String group, String dir) {
        try {
            URL url = ossClient.getEndpoint().toURL();
            String host = url.getProtocol() + "://" + group + "." + url.getHost();
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);
            Map<String, String> respMap = new LinkedHashMap<>();
            respMap.put("accessid", accessId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            //respMap.put("expire", formatISO8601Date(expiration));
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            return respMap;
        } catch (Exception e) {
            log.error("get policy fail.", e);
        }
        return null;
    }

    public String getBaseUrl(String group) {
        URL url;
        try {
            url = new URL(showHost);
        } catch (MalformedURLException e) {
            return "";
        }
        return url.getProtocol() + "://" + group + "." + url.getHost();
    }
}

/*
 * Copyright (c) 2016. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.common.aliyun.oss.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PolicyConditions;
import com.google.common.base.Strings;
import com.google.common.io.ByteStreams;
import com.google.common.io.Closer;
import com.sinochem.crude.trade.common.aliyun.oss.FileManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by HuangWj on 2016/11/2. FileManager implementation for aliyun oss
 */
public class OssFileManager implements FileManager {

    private static final Log log = LogFactory.getLog(OssFileManager.class);

    private OSSClient ossClient;

    private String accessId;

    private String showHost;

    public OssFileManager(OSSClient ossClient, String accessId, String showHost) {
        this.ossClient = ossClient;
        this.accessId = accessId;
        this.showHost = showHost;
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

    @Override
    public String save(InputStream data, String group, String path) throws IOException {
        if (!ossClient.doesBucketExist(group)) {
            ossClient.createBucket(group);
            ossClient.setBucketAcl(group, CannedAccessControlList.PublicRead);
        }
        ossClient.putObject(group, path, data);

        return "/" + path;
    }

    @Override
    public byte[] read(String group, String path) throws IOException {
        return read(group, path, null);
    }

    @Override
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

    @Override
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

    @Override
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

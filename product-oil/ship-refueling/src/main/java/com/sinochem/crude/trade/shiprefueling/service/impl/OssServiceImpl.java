package com.sinochem.crude.trade.shiprefueling.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.PutObjectResult;
import com.sinochem.crude.trade.shiprefueling.service.OssService;
import com.sinochem.it.b2b.common.exception.BizException;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Value("${aliyun.oss.bucket}")
    private String bucket;

    @Value("${aliyun.oss.show.endpoint}")
    private String showHost;

    /*@Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;*/
    @Autowired
    private OSSClient ossClient;

    /*@Override
    public void init() {
        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }*/

    @Override
    public String getPath(String suffix) {
        //生成uuid
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //文件路径
        String path = "shiprefueling" + "/" + DateUtils.format(new Date(), "yyyyMMdd") + "/" + uuid;

        return path + suffix;
    }

    @Override
    public String upload(InputStream inputStream) throws BizException {
        return upload(inputStream, getPath(""));
    }

    @Override
    public String upload(InputStream inputStream, String path) throws BizException {
        try {
            //init();
            PutObjectResult putObjectResult = ossClient.putObject(bucket, path, inputStream);
            //ossClient.shutdown();
        } catch (Exception e){
            throw new BizException("上传文件失败，请检查配置信息");
        }

       //return ossClient.getEndpoint() + "/" + path;
        return showHost+"/" + path;
    }

    @Override
    public void deleteSingleObect(String path) {
        ossClient.deleteObject(bucket, path);
        //ossClient.shutdown();
    }

    @Override
    public void deleteBatchObect(List<String> paths) {
        DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(bucket).withKeys(paths));
        List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
    }
}

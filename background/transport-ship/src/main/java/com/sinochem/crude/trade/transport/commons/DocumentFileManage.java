package com.sinochem.crude.trade.transport.commons;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;

public class DocumentFileManage {
	private String bucket;
	private OSSClient ossClient; 
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public DocumentFileManage(OSSClient ossClient, String bucket){
		this.ossClient = ossClient;
		this.bucket = bucket;
	}

	/**
	 * 文件上传
	 * @param data 文件流
	 * @param path 文件路径，不包含OSS路径
	 * @return 文件访问路径
	 * @throws IOException
	 */
	public String upload(InputStream data, String path) throws IOException {
		@SuppressWarnings("unused")
		PutObjectResult ret = ossClient.putObject(bucket, path, data);
		
		Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
		// 生成URL
	    URL url = ossClient.generatePresignedUrl(bucket, path, expiration);
	    String urlPath = "";
	    if (url != null) {
	      urlPath = url.toString();
	      int pos = urlPath.indexOf("?");
	      urlPath = urlPath.substring(0, pos);
	    }
	    
		return urlPath;
	}
	
	/**
	 * 文件下载 
	 * @param filePath 文件路径
	 * @throws IOException
	 */
	public void download(String filePath, HttpServletResponse response) {
		int idx = filePath.lastIndexOf(".");
		String suffix = "";
		if(idx > 0){
			suffix = filePath.substring(idx);
		}
		String fileName = KeyGenUtils.newUuid()+suffix;
		
		response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Disposition", "attachment;Filename="
				+ fileName);
		
		InputStream fileStreamIn = null;
		OutputStream fileStreamOut = null;
		try {
			OSSObject ossObject = ossClient.getObject(bucket, filePath);
			fileStreamIn = ossObject.getObjectContent();
			fileStreamOut = response.getOutputStream();
			
			BufferedInputStream bis = new BufferedInputStream(fileStreamIn);
			
			byte[] buf = new byte[1024];
			for(int read = 0; read!= -1;){
				read = bis.read(buf, 0, buf.length);
				fileStreamOut.write(buf, 0, read);
			}
			
			fileStreamOut.flush();
		} catch (Exception e) {
			logger.error("文件下载失败", e);
			
			if(fileStreamOut != null){
				try {
					fileStreamOut.close();
				} catch (IOException e1) {
					logger.error("IOException", e1);
				}
			}
			if(fileStreamIn != null){
				try {
					fileStreamIn.close();
				} catch (IOException e1) {
					logger.error("IOException", e1);
				}
			}
		}
	}
}

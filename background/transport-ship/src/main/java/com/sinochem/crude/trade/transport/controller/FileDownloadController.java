package com.sinochem.crude.trade.transport.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;

/**
 * 导出excel
 * @author gyy
 *
 */
@Controller
public class FileDownloadController {

	private  Log log = LogFactory.getLog(FileDownloadController.class);
	
	
	@Value("${aliyun.oss.bucket}")
    private  String publicBucket;
	
	@Value("${oss.server.host}")
	private  String host;
	
	@Autowired
	private OSSClient ossClient;
	
	/**
	 * 下载文件公共方法（OSS）
	 * @param pathkey
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="download.htm", method=RequestMethod.GET)
	public void download(@RequestParam(value = "path",required = false) String path,@RequestParam(value="fileName", required=false) String fileName, HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		if(StringUtils.isBlank(fileName)) {
			fileName = KeyGenUtils.newUuid();
		}else {
			if(isMSBrowser(request)){
	            fileName = URLEncoder.encode(fileName, "UTF-8");  
	        }else{
	            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");  
	        } 
		}
		if (com.mysql.jdbc.StringUtils.isNullOrEmpty(path)) {
			return ;
		} else {
			String[] split = path.split(".com/");
			int index = path.lastIndexOf(".");
			int length = path.length();
			String str = path.substring(index+1, length);
			fileName = fileName +"."+str;
			if (split.length > 1) {
				path = split[1];
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
		response.setHeader("Content-Disposition", "attachment;Filename="+ fileName);
		InputStream fileStreamIn = null;
		OutputStream fileStreamOut = null;
		try {
			OSSObject ossObject = ossClient.getObject(publicBucket, path);
			fileStreamIn = ossObject.getObjectContent();
			fileStreamOut = response.getOutputStream();
			
			BufferedInputStream bis = new BufferedInputStream(fileStreamIn);
			
			byte[] buf = new byte[1024];
			int read = 0;
			while((read = bis.read(buf))!= -1){
				fileStreamOut.write(buf, 0, read);
			}
			
		} catch (Exception e) {
			log.error("文件下载失败", e);
		} finally {
			if(fileStreamOut != null){
				try {
					fileStreamOut.close();
				} catch (IOException e1) {
					log.error("IOException", e1);
				}
			}
			if(fileStreamIn != null){
				try {
					fileStreamIn.close();
				} catch (IOException e1) {
					log.error("IOException", e1);
				}
			}
		}
	}
	
	/**
	 * 文件预览公共方法（OSS）
	 * @param pathkey
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="downloadOpen.htm", method=RequestMethod.GET)
	public void downloadOpen(@RequestParam(value = "path",required = false) String path,@RequestParam(value="fileName", required=false) String fileName, HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		if(StringUtils.isBlank(fileName)) {
			fileName = KeyGenUtils.newUuid();
		}else {
			if(isMSBrowser(request)){
				fileName = URLEncoder.encode(fileName, "UTF-8");  
			}else{
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");  
			} 
		}
		if (com.mysql.jdbc.StringUtils.isNullOrEmpty(path)) {
			return ;
		} else {
			String[] split = path.split(".com/");
			int index = path.lastIndexOf(".");
			int length = path.length();
			String str = path.substring(index+1, length);
			fileName = fileName +"."+str;
			if (split.length > 1) {
				path = split[1];
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
		response.setHeader("Content-Disposition", "attachment;Filename="+ fileName);
		InputStream fileStreamIn = null;
		OutputStream fileStreamOut = null;
		try {
			OSSObject ossObject = ossClient.getObject(publicBucket, path);
			fileStreamIn = ossObject.getObjectContent();
			fileStreamOut = response.getOutputStream();
			
			BufferedInputStream bis = new BufferedInputStream(fileStreamIn);
			
			byte[] buf = new byte[1024];
			int read = 0;
			while((read = bis.read(buf))!= -1){
				fileStreamOut.write(buf, 0, read);
			}
			
		} catch (Exception e) {
			log.error("文件下载失败", e);
		} finally {
			if(fileStreamOut != null){
				try {
					fileStreamOut.close();
				} catch (IOException e1) {
					log.error("IOException", e1);
				}
			}
			if(fileStreamIn != null){
				try {
					fileStreamIn.close();
				} catch (IOException e1) {
					log.error("IOException", e1);
				}
			}
		}
	}
	
	//IE浏览器判断
		public boolean isMSBrowser(HttpServletRequest request) {  
	        String[] IEBrowserSignals = {"MSIE", "Trident", "Edge"};  
	        String userAgent = request.getHeader("User-Agent");  
	        for (String signal : IEBrowserSignals) {  
	            if (userAgent.contains(signal)){  
	                return true;  
	            }  
	        }  
	        return false;  
	    }
}

package com.sinochem.crude.trade.shipping.controller.common;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PolicyConditions;
import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.shipping.constant.UrlMapping;
import com.sinochem.it.b2b.common.http.ConnectionManager;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.WithoutAccess;

@Controller
public class CommonController {
	
	private Log logger = LogFactory.getLog(CommonController.class);
	
	@Value("${aliyun.oss.accessKeyId}")
	private String accessKeyId;
	@Value("${aliyun.oss.endpoint}")
	private String endpoint;
	@Value("${aliyun.oss.bucket}")
	private String bucket;
	
	@Autowired
	private OSSClient ossClient;
	
	/**
	 * 下载文件公共方法（OSS）
	 * @param pathkey
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	@WithoutAccess
	@RequestMapping(value=UrlMapping.DOC_DOWNLOAD_OSS, method=RequestMethod.GET)
	public void download(
			@RequestParam("path") String path, 
			@RequestParam(value="fileName", required=false) String fileName, 
			HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException{

		if(StringUtils.isBlank(fileName)) {
			fileName = KeyGenUtils.newUuid();
		}else {
			String queryString = request.getQueryString();
			String[] split = queryString.split("&");
			String[] split1 = split[1].split("=");
			String s = split1[1];
			fileName = URLDecoder.decode(s,"UTF-8");
			if(isMSBrowser(request)){
	            fileName = URLEncoder.encode(fileName, "UTF-8");  
	        }else{
	            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");  
	        } 
		}
		
		response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Disposition", "attachment;Filename="
				+ fileName);
		
		InputStream fileStreamIn = null;
		OutputStream fileStreamOut = null;
		try {
			OSSObject ossObject = ossClient.getObject(bucket, path);
			fileStreamIn = ossObject.getObjectContent();
			fileStreamOut = response.getOutputStream();
			
			BufferedInputStream bis = new BufferedInputStream(fileStreamIn);
			
			byte[] buf = new byte[1024];
			int read = 0;
			while((read = bis.read(buf))!= -1){
				fileStreamOut.write(buf, 0, read);
			}
			
		} catch (Exception e) {
			logger.error("文件下载失败", e);
		} finally {
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
	
	/**
	 * 获取文件流（OSS）
	 * @param pathkey
	 */
	@WithoutAccess
	@RequestMapping(value="common/doc/file.htm", method=RequestMethod.GET)
	public void download(@RequestParam("path") String path, 
			HttpServletRequest request, HttpServletResponse response) {
		
		InputStream fileStreamIn = null;
		OutputStream fileStreamOut = null;
		try {
			OSSObject ossObject = ossClient.getObject(bucket, path);
			fileStreamIn = ossObject.getObjectContent();
			fileStreamOut = response.getOutputStream();
			
			BufferedInputStream bis = new BufferedInputStream(fileStreamIn);
			
			byte[] buf = new byte[1024];
			int read = 0;
			while((read = bis.read(buf))!= -1){
				fileStreamOut.write(buf, 0, read);
			}
			
		} catch (Exception e) {
			logger.error("文件下载失败", e);
		} finally {
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
	
	/**
	 * OSS web直传时获取policy参数
	 * @return
	 */
	@RequestMapping(value="api/oss/getParams.json", method=RequestMethod.GET)
	@WithoutAccess
	@ResponseBody
	public ResultDatas<Map<String, String>> getOssPostParams(){
        String dir = "shipping/";
//        String host = "http://" + bucket + "." + endpoint.replace("http://", "");
        String host = endpoint;
    	long expireTime = 30;
    	long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
        Date expiration = new Date(expireEndTime);
        
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

        ResultDatas<Map<String, String>> result = new ResultDatas<>();
        try {
        	String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);
            
            Map<String, String> respMap = new LinkedHashMap<String, String>();
            respMap.put("accessid", accessKeyId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            
            result.setDatas(respMap);
            
		} catch (Exception e) {
			result.setFail("system error");
		}
        
        return result;
	}
	
    public static JSONObject httpPost(JSONObject paramJson, String url) throws Exception {
        CloseableHttpResponse httpResponse = null;
        CloseableHttpClient closeableHttpClient = getConnectionManager().getHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-type","application/json; charset=utf-8");
        httpPost.setEntity(new StringEntity(paramJson.toJSONString(), CharsetUtils.get("UTF-8")));

        try {
            httpResponse = closeableHttpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();

            if (httpEntity == null) {
                throw new Exception("网络异常");
            }

            String responseString = EntityUtils.toString(httpEntity, "UTF-8");
            EntityUtils.consume(httpEntity);

            return JSONObject.parseObject(responseString);
        } catch (Exception e) {
            throw e;
        } finally {
            if (httpResponse != null) {
                httpResponse.close();
            }
        }
    }
    
    /** getters */
    public static ConnectionManager getConnectionManager() {
        return ContextLoader.getCurrentWebApplicationContext().getBean(ConnectionManager.class);
    }


	@RequestMapping(value="common/deleteFile.json", method=RequestMethod.POST)
	@ResponseBody
    public ResultDatas<Boolean> oosDeleteOject(String path){
    		ResultDatas<Boolean> result = new ResultDatas<>();
    		try{
				if(ossClient.doesObjectExist(bucket,path)){
					ossClient.deleteObject(bucket,path);
					result.setDatas(true);
				}else{
					result.setDatas(false);
				}
			}catch (Exception e){
    			logger.error("删除oos文件失败！",e);
			}finally {
				return result;
			}
	}

}

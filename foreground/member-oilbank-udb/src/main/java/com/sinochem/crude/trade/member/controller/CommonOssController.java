package com.sinochem.crude.trade.member.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.member.contact.MsgConstant;
import com.sinochem.crude.trade.member.contact.UdbCodeConstant;
import com.sinochem.crude.trade.member.contact.UrlMapping;
import com.sinochem.crude.trade.member.model.FileInfoVO;
import com.sinochem.crude.trade.member.model.udbvo.FileUdbVo;
import com.sinochem.crude.trade.member.service.udbservice.FileUdbService;
import com.sinochem.crude.trade.member.util.UdbResult;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.WithoutAccess;

@Controller
@WithoutAccess
public class CommonOssController {
	
	private Log logger = LogFactory.getLog(CommonOssController.class);
	
	@Value("${aliyun.oss.bucket}")
	private String bucket;

	@Value("${aliyun.oss.endpoint}")
	private String endpoint;

	@Value("${aliyun.oss.accessKeyId}")
	private String accessKeyId;

	@Value("${aliyun.oss.accessKeySecret}")
	private String accessKeySecret;

	@Value("${aliyun.oss.protocol}")
	private String ossProtocol;


	@Autowired
	private FileUdbService fileUdbService;

	/**
	 * OSS web直传时获取policy参数
	 * @return 引用后台执行文件在web端直接上传的方法
	 */
	@RequestMapping(value= UrlMapping.COMMON_OSS_GETPARAMS, method=RequestMethod.GET)
	@ResponseBody
	public ResultDatas<Map<String, String>> getOssPostParams(){
		OSSClient ossClient = null;
		String dir = "orderexecute/";
		String host = ossProtocol + "://" + bucket + "." + endpoint.replace(ossProtocol + "://", "");
		//String host = endpoint;
		long expireTime = 30;
		long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
		Date expiration = new Date(expireEndTime);

		PolicyConditions policyConds = new PolicyConditions();
		policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
		policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

		ResultDatas<Map<String, String>> result = new ResultDatas<>();
		try {
			ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
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
			result.setFail("系统错误");
		}finally {
			if(ossClient != null){
				ossClient.shutdown();
			}
		}

		return result;
	}

	/**
	 * 上传文件公共方法（OSS）
	 * @param file
	 * @return
	 */
	@RequestMapping(value=UrlMapping.COMMON_DOC_UPLOAD, method=RequestMethod.POST)
	@ResponseBody
	@WithoutAccess
	public ResultDatas<FileInfoVO> upload(@RequestParam("file") MultipartFile file, HttpServletResponse response){
		ResultDatas<FileInfoVO> result = new ResultDatas<>();
		FileInfoVO fileInfo = new FileInfoVO();
		
		String fileName = file.getOriginalFilename();
		InputStream input = null;
		String size = String.valueOf(file.getSize());
		String suffix = "";
		if(fileName.lastIndexOf(".")> 0){
			suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		}
		OSSClient ossClient = null;
		try {
			input = file.getInputStream();
			String path = "orderexecute/" + newUuid()+ "." + suffix;

			ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			ossClient.putObject(bucket, path, input);
			
			fileInfo.setPath(path);
			fileInfo.setSuffix(suffix);
			fileInfo.setSize(size);
			fileInfo.setOriginalName(fileName);
		} catch (IOException e) {
			result.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO11));
			logger.error("上传文件失败");
		} finally {
			if(input != null){
				try {
					input.close();
				} catch (IOException e1) {
					logger.error(e1);
				}
			}
			if(ossClient != null){
				ossClient.shutdown();
			}
		}
		
		result.setDatas(fileInfo);
		return result;
	}
	
	
	/**
	 * 下载文件公共方法（OSS）
	 * @param path
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value=UrlMapping.COMMON_DOC_DOWNLOAD, method=RequestMethod.GET)
	@WithoutAccess
	public void download(
			@RequestParam("path") String path, 
			@RequestParam(value="fileName", required=false) String fileName, 
			HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException{
		if(StringUtils.isBlank(fileName)) {
			fileName = newUuid();
		}else {
			if(isMSBrowser(request)){
	            fileName = URLEncoder.encode(fileName, "UTF-8");  
	        }else{
	            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
	        }
		}

		response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Disposition", "attachment;Filename="+fileName);
		
		InputStream fileStreamIn = null;
		OutputStream fileStreamOut = null;
		OSSClient ossClient = null;
		try {
			ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
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
			if(ossClient != null){
				ossClient.shutdown();
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
	 * 文件类型校验
//	 * @param fileName
	 * @return
	 */
//	private boolean fileTypeValidate(String fileName){
//		if(StringUtils.isNotEmpty(fileName)){
//			if(fileName.endsWith(".pdf")){
//				return true;
//			}
//			if(fileName.endsWith(".doc")){
//				return true;
//			}
//			if(fileName.endsWith(".docx")){
//				return true;
//			}
//		}
//		
//		return false;
//	}

	public static String newUuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}


	@RequestMapping(value = UrlMapping.UDB_FILE_UPLOAD,method = RequestMethod.GET)
	@ResponseBody
	public ResultDatas<FileUdbVo>  uploadFile(){
		UdbResult<FileUdbVo> udbResult = null;
		ResultDatas resultDatas = new ResultDatas();
		try{
			udbResult = fileUdbService.uploadFile();
			if(udbResult != null && UdbCodeConstant.SUCCESS.equals(udbResult.getCode())){
				resultDatas.setDatas(udbResult.getResponse());
				resultDatas.setStatus(Result.SUCCESS);
			}
		}catch (Exception e){
			logger.error(udbResult.getMessage(),e);
			resultDatas.setStatus(Result.EEROR);
		}finally {
			return resultDatas;
		}
	}

}

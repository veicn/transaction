package com.sinochem.crude.trade.orderexecute.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PolicyConditions;
import com.sinochem.crude.trade.UrlMapping;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.model.FileInfoVO;
import com.sinochem.crude.trade.orderexecute.service.OrderService;
import com.sinochem.it.b2b.doc.api.remote.DocUploadRemoteService;
import com.sinochem.it.b2b.member.access.WithoutAccess;

@Controller
public class CommonController {
	
	private Log logger = LogFactory.getLog(OrderContractController.class);
	
	@Value("${aliyun.oss.accessKeyId}")
	private String accessKeyId;
	@Value("${aliyun.oss.endpoint}")
	private String endpoint;
	@Value("${aliyun.oss.bucket}")
	private String bucket;
	
	@Autowired
	private OSSClient ossClient;
	@Autowired
	private OrderService orderService;
	@Autowired
	private DocUploadRemoteService docUploadRemoteService;
	
//	/**
//	 * @deprecated
//	 * 上传方法已替换为直传到OSS，不再经过本地服务
//	 * 上传文件公共方法（OSS）
//	 * @param files
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value=UrlMapping.DOC_UPLOAD_OSS, method=RequestMethod.POST)
//	@ResponseBody
//	public ResultDatas<FileInfoVO> upload(@RequestParam("file") MultipartFile file, HttpServletResponse response){
//		ResultDatas<FileInfoVO> result = new ResultDatas<>();
//		FileInfoVO fileInfo = new FileInfoVO();
//		
//		String fileName = file.getOriginalFilename();
//		InputStream input = null;
////		String size = new BigDecimal((double)file.getSize()/1024).setScale(1, RoundingMode.DOWN).toString();
//		String size = String.valueOf(file.getSize());
//		String suffix = "";
//		if(fileName.lastIndexOf(".")> 0){
//			suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
//		}
//		
//		try {
//			input = file.getInputStream();
//			String path = "orderexecute/" + KeyGenUtils.newUuid()+ "." + suffix;
//			
//			ossClient.putObject(bucket, path, input);
//			
//			fileInfo.setPath(path);
//			fileInfo.setSuffix(suffix);
//			fileInfo.setSize(size);
//			fileInfo.setOriginalName(fileName);
//		} catch (IOException e) {
//			result.setFail("上传文件失败");
//			logger.error("上传文件失败", e);
//		} finally {
//			if(input != null){
//				try {
//					input.close();
//				} catch (IOException e1) {
//					logger.error(e1);
//				}
//			}
//		}
//		
//		result.setDatas(fileInfo);
//		return result;
//	}
	
	/**
	 * 单证上传（接文档中心）
	 * @param files
	 * @return
	 */
	@RequestMapping(value=UrlMapping.DOC_UPLOAD_DOCCENTER, method=RequestMethod.POST)
	@ResponseBody
	@WithoutAccess
	public ResultDatas<FileInfoVO> uploadDoc(@RequestParam("file") MultipartFile file, 
			HttpServletRequest request, 
			HttpServletResponse response, 
			CurrentUser user,
			@RequestParam("uuid") String uuid,
			@RequestParam("fileCode") String fileCode){
		ResultDatas<FileInfoVO> result = new ResultDatas<>();
		FileInfoVO fileInfo = new FileInfoVO();
		
		// 默认9999，没有user时使用
		Long memberId = (long) 9999;
		if(user != null && user.getMemberId() != null) {
			memberId = user.getMemberId();
		}
		
		// 接口需要
		int fileType = 0;
		String fileName = file.getOriginalFilename();
		
		// 本地需要
		String returnPath = null;
		String size = String.valueOf(file.getSize());
		String suffix = "";
		if(fileName.lastIndexOf(".")> 0){
			suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
			suffix = suffix.toLowerCase();
		}
		
		try {
			Order order = orderService.findByUuid(uuid);
			if(order == null) {
				result.setStatus(9999);
				result.setMessage("没有找到订单信息");
				result.setCode("orderexecute.code.00001");
				return result;
			}
			
			if("jpg".equals(suffix) || "jpeg".equals(suffix) || "bmp".equals(suffix)) {
				fileType = 0;
			} else {
				fileType = 1;
			}
			
			com.sinochem.it.b2b.common.result.ResultDatas res = docUploadRemoteService.docUpload(
					file.getBytes(), 
					fileType, 
					fileName, 
					String.valueOf(order.getTradeId()), 
					fileCode, 
					memberId);
			
			if(res != null) {
				if(res.getStatus() == 0) {
					if(res.getDatas() != null) {
						Map<String, Object> map = (Map<String, Object>) res.getDatas();
						if(map.get("path") != null) {
							returnPath = String.valueOf(map.get("path"));
						}
					}
				} else {
					result.setStatus(res.getStatus());
					result.setMessage(res.getMessage());
					return result;
				}
			}

			if(returnPath == null) {
				result.setStatus(9999);
				result.setMessage("没有取得文件路径");
				result.setCode("orderexecute.code.00002");
				return result;
			}
			fileInfo.setPath(returnPath);
			fileInfo.setSuffix(suffix);
			fileInfo.setSize(size);
			fileInfo.setOriginalName(fileName);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(9999);
			result.setMessage("调用上传接口出错");
			result.setCode("orderexecute.code.00003");
			return result;
		}
		
		result.setDatas(fileInfo);
		return result;
	}
	
	
	/**
	 * 下载文件公共方法（OSS）
	 * @param pathkey
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value=UrlMapping.DOC_DOWNLOAD_OSS, method=RequestMethod.GET)
	public void download(
			@RequestParam("path") String path, 
			@RequestParam(value="fileName", required=false) String fileName, 
			HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException{
		if(StringUtils.isBlank(fileName)) {
			String suffix = path.substring(path.lastIndexOf("."));
			fileName = KeyGenUtils.newUuid()+suffix;
		}else {
			if(isMSBrowser(request)){
	            fileName = URLEncoder.encode(fileName, "UTF-8");  
	        }else{
	            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");  
	        } 
		}
		
		response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Disposition", "attachment;Filename=\""+ fileName+"\"");
		
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
	@RequestMapping(value=UrlMapping.DOC_FILE_STREAM, method=RequestMethod.GET)
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
	@RequestMapping(value=UrlMapping.GET_OSS_PARAMS, method=RequestMethod.GET)
	@ResponseBody
	public ResultDatas<Map<String, String>> getOssPostParams(){
        String dir = "orderexecute/";
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
			result.setFail("系统错误");
			result.setCode("orderexecute.code.00005");
		}
        
        return result;
	}
	
}

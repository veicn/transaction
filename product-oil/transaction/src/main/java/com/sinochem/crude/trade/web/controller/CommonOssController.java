package com.sinochem.crude.trade.web.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.sinochem.crude.trade.blockchain.utils.BlockChainUtil;
import com.sinochem.crude.trade.transaction.model.vo.FileInfoVO;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.apache.commons.fileupload.disk.DiskFileItem;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.UUID;

@Controller
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
	@Autowired
	private OSSClient ossClient;

	/**
	 * 上传文件公共方法（OSS）
	 * @param file
	 * @return
	 */
	@RequestMapping(value="common/doc/upload.json", method=RequestMethod.POST)
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
			String path = "transaction/" + newUuid()+ "." + suffix;

			ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			ossClient.putObject(bucket, path, input);

			fileInfo.setPath(path);
			fileInfo.setSuffix(suffix);
			fileInfo.setSize(size);
			fileInfo.setOriginalName(fileName);
            fileInfo.setFileSHA(BlockChainUtil.getFileSha1(file.getBytes()));
		} catch (IOException e) {
			result.setFail("上传文件失败");
			logger.error("上传文件失败", e);
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
	@RequestMapping(value="common/doc/download.htm", method=RequestMethod.GET)
	public void download(
			@RequestParam("path") String path, 
			@RequestParam(value="fileName", required=false) String fileName,
			@RequestParam(value = "uuid" , required = false) String uuid,
			HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException{
		String queryString = request.getQueryString();
		String[] split = queryString.split("&");
		String[] split1 = split[1].split("=");
		String s = split1[1];
		fileName = URLDecoder.decode(s,"UTF-8");

		if(StringUtils.isBlank(fileName)) {
			//BillCoreUpload byUuid = _billCoreUploadService.findByUuid(uuid);
			//byUuid.getFileName();
			/*if (StringUtil.isNotBlank(byUuid.getFileName())) {
				fileName=byUuid.getFileName();
			} else {


			}*/
			fileName = newUuid();
		}else {
			if(isMSBrowser(request)){
	            fileName = URLEncoder.encode(fileName, "UTF-8");
	        }else{
	           // fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
				//fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
				//fileName = new String(fileName.getBytes("ISO-8859-1"), "UTF-8");
	        }
		}

		response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Disposition", "attachment;Filename="
				+ new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));
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
	 * 删除oss文件
	 * @param path
	 * @return
	 */
	//@RequestMapping(value="common/doc/deleteFile.json", method=RequestMethod.POST)
	//@ResponseBody
	public boolean oosDeleteOject(String path){
		ResultDatas<Boolean> result = new ResultDatas<>();
		try{
			if(ossClient.doesObjectExist(bucket,path)){
				ossClient.deleteObject(bucket,path);
				return true;
			}else{
				return false;
			}
		}catch (Exception e){
			logger.error("删除oos文件失败！",e);
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
	
}

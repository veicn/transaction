package com.sinochem.crude.trade.transport.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.eyeieye.melody.web.locale.VisitorLocale;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.URLMapping;
import com.sinochem.crude.trade.transport.commons.DocumentFileManage;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.model.FileInfoVO;
import com.sinochem.crude.trade.transport.model.ShipPactVO;
import com.sinochem.crude.trade.transport.service.CommonService;

@Controller
public class CommonController {
	
	Log log = LogFactory.getLog(CommonController.class);
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private DocumentFileManage fileManage;
	
	/**
	 * 上传文件公共方法
	 * @param files
	 * @return
	 */
	@RequestMapping(value="common/doc/upload.json", method=RequestMethod.POST)
	@ResponseBody
	public ResultDatas<FileInfoVO> upload(@RequestParam("file") MultipartFile file, HttpServletResponse response){
		ResultDatas<FileInfoVO> result = new ResultDatas<>();
		FileInfoVO fileInfo = new FileInfoVO();
		
		String fileName = file.getOriginalFilename();
		@SuppressWarnings("unused")
		String returnPath = null;
		InputStream input = null;
//		String size = new BigDecimal((double)file.getSize()/1024).setScale(1, RoundingMode.DOWN).toString();
		String size = String.valueOf(file.getSize());
		
		String suffix = "";
		if(fileName.lastIndexOf(".")> 0){
			suffix = fileName.substring(fileName.lastIndexOf("."));
		}
		
		try {
			input = file.getInputStream();
			String path = "transport/"+KeyGenUtils.newUuid()+suffix;
			returnPath = fileManage.upload(input, path);
			
			fileInfo.setName(fileName);
			fileInfo.setPath(path);
			fileInfo.setSuffix(suffix);
			fileInfo.setSize(size);
		} catch (IOException e) {
			result.setFail("上传文件失败");
			log.error("上传文件失败", e);
			if(input != null){
				try {
					input.close();
				} catch (IOException e1) {
					log.error(e1);
				}
			}
		}
		
		result.setDatas(fileInfo);
		return result;
	}
	
	/**
	 * 下载文件公共方法
	 * @param pathkey
	 * @param response
	 */
	@RequestMapping(value="common/doc/download.htm", method=RequestMethod.GET)
	public void download(@RequestParam("path") String path, HttpServletResponse response){
		fileManage.download(path, response);
	}
	
	/*private boolean fileTypeValidate(String fileName){
		if(StringUtils.isNotEmpty(fileName)){
			if(fileName.endsWith(".pdf")){
				return true;
			}
			if(fileName.endsWith(".doc")){
				return true;
			}
			if(fileName.endsWith(".docx")){
				return true;
			}
		}
		
		return false;
	}
	*/
	
	
	/**
	 *查询用户信息
	 * @param vo
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value=URLMapping.GET_USERINFO, method = RequestMethod.POST  )
	public ResultDatas<Object> getUserInfo(@RequestBody ShipPactVO vo,CurrentUser user) {
		 ResultDatas<Object> res = new  ResultDatas<>();
		try {
			Map<String,Object> map = new HashMap<>();
			Map<String, Object> map2 = BeanConvertUtils.beanToMap(user);
			map.putAll(map2);
			EnterpriseVo epMember = commonService.queryUserByEpMemberId(user.getEpMemberId());
			if (epMember != null){
				//获取当前语言环境
				Locale current = VisitorLocale.getCurrent();
				String lang = current.getLanguage();
				String nameStr="";
				if ("en".equals(lang)){
					nameStr = epMember.getEnglishName();
				} else {
					nameStr = epMember.getFullName();
				}
				map.put("email", epMember.getEmail());
				map.put("epMemberName", nameStr);
				map.put("contacts", epMember.getContactName());
				map.put("tel", epMember.getMobile());
			}
			res.setDatas(map);
		} catch (BizException e) {
			log.error("getUserInfo error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("getUserInfo error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
}

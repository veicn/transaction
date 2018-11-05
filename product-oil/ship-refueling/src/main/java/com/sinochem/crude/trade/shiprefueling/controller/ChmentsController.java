package com.sinochem.crude.trade.shiprefueling.controller;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shiprefueling.constants.Constants;
import com.sinochem.crude.trade.shiprefueling.constants.UrlMapping;
import com.sinochem.crude.trade.shiprefueling.controller.common.BeanConvertUtils;
import com.sinochem.crude.trade.shiprefueling.controller.common.ResultDatas;
import com.sinochem.crude.trade.shiprefueling.controller.common.SimplePageInfo;
import com.sinochem.crude.trade.shiprefueling.domain.po.Chments;
import com.sinochem.crude.trade.shiprefueling.model.vo.ChmentsVO;
import com.sinochem.crude.trade.shiprefueling.service.ChmentsService;
import com.sinochem.crude.trade.shiprefueling.service.impl.FileUploadServiceImpl;
import com.sinochem.it.b2b.member.access.WithoutAccess;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
/**
 * 附件控制器 (仅供自己开发调试用)
 * 
 * @author niuwk
 *
 */
@Controller
@WithoutAccess
public class ChmentsController  {

	@Autowired
	private ChmentsService chmentsService;
	@Autowired
	private FileUploadServiceImpl fileUploadService;


	public static final Log log = LogFactory.getLog(ChmentsController.class);


	/**
	 * 添加附件信息
	 * @param haseInfo
	 * @return
	 */
    @RequestMapping(value = UrlMapping.ATTACHMENTS_INSERT, method = POST)
    @ResponseBody
    public ResultDatas<Object> insertAttachmentsInfo(@RequestBody ChmentsVO vo,CurrentUser user) {
        ResultDatas<Object> res = new ResultDatas<Object>();
        try {
        	vo.setCreateUser(user.getEpMemberId());
        	chmentsService.insertAttachmentsRecord(vo);
        } catch (Exception e) {
        	res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(e.getMessage());
            
        }
        return res;
    }
	
	/**
	 * 修改采购信息
	 * @param haseInfo
	 * @return
	 */
    @RequestMapping(value = UrlMapping.ATTACHMENTS_MODIFY, method = POST)
    @ResponseBody
    public ResultDatas<Object> updateAttachmentsRecordByUuid(@RequestBody  ChmentsVO vo,CurrentUser user) {
        ResultDatas<Object> res = new ResultDatas<Object>();
        try {
        	vo.setUpdateUser(user.getEpMemberId());
        	chmentsService.updateAttachmentsRecordByUuid(vo);
        } catch (Exception e) {
        	res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(e.getMessage());
            
        }
        return res;
    }
	
	/**
	 * 删除附件信息
	 * @param haseInfo
	 * @return
	 */
    @RequestMapping(value = UrlMapping.ATTACHMENTS_DELETE, method = POST)
    @ResponseBody
    public ResultDatas<Object> deleteHaseInfo(@RequestBody ChmentsVO vo) {
        ResultDatas<Object> res = new ResultDatas<Object>();
        try {
        	int i = chmentsService.deleteRecordByUuid(vo.getUuid());
        	if(i==0){
        		res.setStatus(Constants.EXCEPTION_STATUS);
        	}
        } catch (Exception e) {
        	res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(e.getMessage());
            
        }
        return res;
    }
    
    
	/**
	 * 查询采购信息
	 * @param haseInfo
	 * @return
	 */
    @RequestMapping(value = UrlMapping.ATTACHMENTS_GET, method = POST)
    @ResponseBody
    public ResultDatas<Object> getAttachmentInfo(@RequestBody ChmentsVO vo) {
        ResultDatas<Object> res = new ResultDatas<Object>();
        Chments info = null;
        try {
        	 info = chmentsService.findByUuid(vo.getUuid());
        	 res.setDatas(info);
        } catch (Exception e) {
        	res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(e.getMessage());
            
        }
        return res;
    }
    
    
    /**
     * 分页列表
     * @param haseInfo
     * @return
     */
    @RequestMapping(value = UrlMapping.ATTACHMENTS_LIST, method = POST)
    @ResponseBody
    public ResultDatas<List<Map<String,Object>>> getHaseInfoList(@RequestBody ChmentsVO vo) {
    	ResultDatas<List<Map<String,Object>>> res=new ResultDatas<>();
        try {
        	SimplePageInfo pageInfo = vo.getPageInfo();
        	Map<String, Object> map = null;
        	map = BeanConvertUtils.beanToMap(vo);
        	Page<Map<String, Object>> page = chmentsService.queryAttachmentList(map,pageInfo);
        	res.setDatas(page);
 			res.setPageNum(page.getPageNum());
 			res.setPageSize(page.getPageSize());
 			res.setTotal(page.getTotal());
 			res.setPageCount(page.getPages());
        } catch (Exception e) {
        	res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(e.getMessage());
            
        }
        return res;
    }




//    @RequestMapping(value = "hasInfo/upload.json" , method = POST)
//	@ResponseBody
//    public ResultDatas<String> uploadHasInfoChments(@RequestParam("file") MultipartFile file , CurrentUser currentUser){
//		ResultDatas<String> res=new ResultDatas<String>();
//		try{
//			String url = fileUploadService.upload(file);
//			Chments chments = new Chments();
//			chments.setAliveFlag("");
//			//主键ID
////			chments.setAttachmentId();
//			chments.setCreateDate(new Date());
//			chments.setCreateUser(currentUser.getMemberId());
//			chments.setName(url.substring(url.lastIndexOf("//") , url.lastIndexOf(".")));
////			chments.setLangVer();
//			chments.setPath(url);
//			chments.setUuid(KeyGenUtils.newUuid());
//			chments.setVersion("1");
//			chments.setType(BusinessType.PURCHASE.getCode());
//			int chmentId = chmentsService.insertRecord(chments);
//			res.setDatas(url);
//			log.info(chments);
//			log.info(chmentId);
//		}catch (BizException e){
//			log.error(e);
//
//		}catch (IOException e){
//			log.error(e);
//
//		}
//		return res;
//	}





}

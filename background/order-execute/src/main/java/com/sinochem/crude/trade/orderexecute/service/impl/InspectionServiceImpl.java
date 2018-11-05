package com.sinochem.crude.trade.orderexecute.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.orderexecute.dao.OrderMapper;
import com.sinochem.crude.trade.orderexecute.dao.OrderShipLoadinginfoMapper;
import com.sinochem.crude.trade.orderexecute.dao.OrderShipUnloadinginfoMapper;
import com.sinochem.crude.trade.orderexecute.domain.OrderShipLoadinginfo;
import com.sinochem.crude.trade.orderexecute.domain.OrderShipUnloadinginfo;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.InspectionContentForDischargeportVO;
import com.sinochem.crude.trade.orderexecute.model.InspectionContentForLoadingportVO;
import com.sinochem.crude.trade.orderexecute.model.InspectionOrderListVO;
import com.sinochem.crude.trade.orderexecute.service.InspectionService;

@Service
public class InspectionServiceImpl implements InspectionService {
	
	private final Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderShipUnloadinginfoMapper unloadinginfoMapper;
	@Autowired
	private OrderShipLoadinginfoMapper loadinginfoMapper;

	@Override
	public Page<InspectionOrderListVO> queryListForPage(Map<String, Object> queryParams, SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), false);
		return (Page<InspectionOrderListVO>) orderMapper.queryInspectionOrderList(queryParams);
	}
	
	@Override
	public List<String> distinctShipForInspection(Map<String, Object> queryParams){
		return orderMapper.distinctShipForInspection(queryParams);
	}
	
	@Override
	public List<String> distinctPortForInspection(Map<String, Object> queryParams){
		return orderMapper.distinctPortForInspection(queryParams);
	}

	@Override
	public int saveInspectionLoadportDetail(Long id, InspectionContentForLoadingportVO contentVO) {
		int result = 0;
		
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create(); 
		String inspectionContentJsonStr = gson.toJson(contentVO);
		
		try {
			OrderShipLoadinginfo entity = new OrderShipLoadinginfo();
			entity.setId(id);
			entity.setInspectionContent(inspectionContentJsonStr);
			
			result = loadinginfoMapper.updateRecordById(entity);
		} catch (Exception e) {
			log.error("保存失败", e);
			throw new OrderExecException("orderexecute.code.00012", "保存失败");
		}
		
		return result;
	}
	
	@Override
	public int saveInspectionDischargeportDetail(Long id, InspectionContentForDischargeportVO contentVO) {
		int result = 0;
		
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create(); 
		String inspectionContentJsonStr = gson.toJson(contentVO);
		
		try {
			OrderShipUnloadinginfo entity = new OrderShipUnloadinginfo();
			entity.setId(id);
			entity.setInspectionContent(inspectionContentJsonStr);
			
			result = unloadinginfoMapper.updateRecordById(entity);
		} catch (Exception e) {
			log.error("保存失败", e);
			throw new OrderExecException("orderexecute.code.00012", "保存失败");
		}
		
		return result;
	}

	@Override
	public InspectionContentForLoadingportVO getInspectionLoadportDetail(Long id) {
		
		OrderShipLoadinginfo entity = loadinginfoMapper.findByPrimaryKey(id);
		String inspectionContentJson = entity.getInspectionContent();
		
		InspectionContentForLoadingportVO vo = null;
		if(StringUtils.isNotBlank(inspectionContentJson)) {
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create(); 
			vo = gson.fromJson(inspectionContentJson, InspectionContentForLoadingportVO.class); 
		}
		
		return vo;
	}
	
	@Override
	public InspectionContentForDischargeportVO getInspectionDischargeportDetail(Long id) {
		
		OrderShipUnloadinginfo entity = unloadinginfoMapper.findByPrimaryKey(id);
		String inspectionContentJson = entity.getInspectionContent();
		
		InspectionContentForDischargeportVO vo = null;
		if(StringUtils.isNotBlank(inspectionContentJson)) {
			vo = JSONObject.parseObject(inspectionContentJson, InspectionContentForDischargeportVO.class);
			
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create(); 
			vo = gson.fromJson(inspectionContentJson, InspectionContentForDischargeportVO.class); 
		}
		
		return vo;
	}
}

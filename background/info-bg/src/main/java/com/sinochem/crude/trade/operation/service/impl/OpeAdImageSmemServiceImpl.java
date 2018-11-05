package com.sinochem.crude.trade.operation.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.commons.exceptions.OperationException;
import com.sinochem.crude.trade.operation.dao.OpeAdImageSetMapper;
import com.sinochem.crude.trade.operation.dao.OpeAdImageSmemMapper;
import com.sinochem.crude.trade.operation.domain.OpeAdImageSet;
import com.sinochem.crude.trade.operation.domain.OpeAdImageSmem;
import com.sinochem.crude.trade.operation.service.OpeAdImageSmemService;
import com.sinochem.crude.trade.operation.vo.CarouselQuery;
import com.sinochem.crude.trade.operation.vo.OpeAdImage;
import com.sinochem.crude.trade.operation.vo.OpeAdImageRes;
import com.sinochem.crude.trade.operation.vo.OpeAdImageSetIn;

@Service
public class OpeAdImageSmemServiceImpl implements OpeAdImageSmemService {
	
	private static final Log log = LogFactory.getLog(OpeAdImageSmemServiceImpl.class);
	
	@Autowired
	private OpeAdImageSmemMapper opeAdImageSmemMapper;
	
	@Autowired
	private OpeAdImageSetMapper opeAdImageSetMapper;
	
	public OpeAdImageSmemMapper getMapper(){
		return opeAdImageSmemMapper;
	} 
	
	
	public List<OpeAdImageSmem> queryByEntitys(OpeAdImageSmem opeadimagesmem){
		return opeAdImageSmemMapper.queryByEntitys(opeadimagesmem);
	}
	
	
	public OpeAdImageSmem findByPrimaryKey(String imageId){
		return opeAdImageSmemMapper.findByPrimaryKey(imageId);	
	}
	
	
	public void updateRecord(OpeAdImageSmem opeadimagesmem)  {
		opeAdImageSmemMapper.updateRecord(opeadimagesmem);
	}
	
	
	public void deleteRecordByKey(String imageId  , String deleteUser)  {
		opeAdImageSmemMapper.deleteRecordByKey(imageId , deleteUser);
	}
	
	
	public void insertRecord(OpeAdImageSmem opeadimagesmem){
		opeAdImageSmemMapper.insertRecord(opeadimagesmem);
	}
	
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return opeAdImageSmemMapper.queryRecords(map);
	}
	
	
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) opeAdImageSmemMapper.queryRecords(map);
	}
	
	
	public int countRecords(Map<String, Object> map){
		return opeAdImageSmemMapper.countRecords(map);
	}
	
	
	public void deleteRecords(Map<String, Object> map)  {
		opeAdImageSmemMapper.deleteRecords(map);
	}
	
	
	public void updateRecords(Map<String, Object> map)  {
		opeAdImageSmemMapper.updateRecords(map);
	}

	//**********************************************************/
	
	@Override
	public List<String> queryImageDes() {
		return opeAdImageSmemMapper.queryImageDes();
	}
	
	@Override
	public List<String> queryTypeCode() {
		return opeAdImageSmemMapper.queryTypeCode();
	}
	
	@Override
	public List<String> queryPageCode(String typeCode) {
		return opeAdImageSmemMapper.queryPageCode(typeCode);
	}


	@Override
	public Page<Map<String, Object>> queryCarousels(CarouselQuery iCarouselQuery, SimplePageInfo pageInfo) {
		if (pageInfo != null) {
			PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		}
		return (Page<Map<String, Object>>)opeAdImageSmemMapper.queryRecords(BeanConvertUtils.beanToMap(iCarouselQuery));
	}


	@Override
	public List<OpeAdImageRes> queryAdimageListByOpeAdImage(OpeAdImageSetIn input) {
		List<OpeAdImageRes> OpeAdImageResResList = new ArrayList<>(); 
		try{
			OpeAdImageSet opeAdImageSet = BeanConvertUtils.beanToBean(input, OpeAdImageSet.class);
			List<OpeAdImageSet> opeAdImageSetList = opeAdImageSetMapper.queryByEntitys(opeAdImageSet);// 查询广告位
			if(opeAdImageSetList != null && !opeAdImageSetList.isEmpty()){
				OpeAdImageSmem opeAdImageSmem;
				for (OpeAdImageSet opeAdImageSet2 : opeAdImageSetList) {
					opeAdImageSmem =new OpeAdImageSmem();
					opeAdImageSmem.setAdSetId(opeAdImageSet2.getAdSetId());
					opeAdImageSmem.setStatus("10");
					// 查询广告位下的广告图
					List<OpeAdImageSmem> list = opeAdImageSmemMapper.queryAdimageListByOpeAdImage(BeanConvertUtils.beanToMap(opeAdImageSmem));
					
					OpeAdImageRes opeAdImageRes = new OpeAdImageRes();
					if(list != null && !list.isEmpty()){// 如果不为空把广告图返回
						List<OpeAdImage> opList = new ArrayList<>();
						OpeAdImage opeAdImage;
						for (OpeAdImageSmem opeAdImag : list) {
							opeAdImage = new OpeAdImage();
							opeAdImage.setImageUrl(opeAdImag.getImageUrl());
							opeAdImage.setGotoUrl(opeAdImag.getGotoUrl());
							opeAdImage.setImageId(opeAdImag.getImageId());
							opeAdImage.setAdSetId(opeAdImag.getAdSetId());
							opList.add(opeAdImage);
						}
						//List<OpeAdImage> opeAdImageList = BeanConvertUtils.beanToBeanInList(list, OpeAdImage.class);
						opeAdImageRes.setList(opList);
						
					}else{// 为空直接返回广告位默认图
						List<OpeAdImage> opeAdImageList = new ArrayList<>();
						OpeAdImage opeAdImage = new OpeAdImage();
						opeAdImage.setImageUrl(opeAdImageSet2.getDefaultImage());
						opeAdImage.setGotoUrl(opeAdImageSet2.getDefaultLinkUrl());
						opeAdImageList.add(opeAdImage);
						opeAdImageRes.setList(opeAdImageList);
						opeAdImageRes.setType("1");
					}
					opeAdImageRes.setAdPageSet(opeAdImageSet2.getAdPageSet());
					OpeAdImageResResList.add(opeAdImageRes);
				}
			}else{
				throw new OperationException(OperationException.TYPE.OPEX001);
			}
		}catch(Exception e){
			log.error(e);
		}
		log.info("3广告位总图片出参");
		return OpeAdImageResResList;
	}


	@Override
	public Page<Map<String, Object>> adImagelList(Map<String, Object> beanToMap, SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
		
		return (Page<Map<String, Object>>)opeAdImageSmemMapper.adImagelList(beanToMap);
	}

	
}

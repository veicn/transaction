package com.sinochem.crude.trade.goods.remote.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.eyeieye.melody.util.StringUtil;
import com.eyeieye.melody.util.uuid.UUIDGenerator;
import com.sinochem.crude.trade.goods.dao.TCrudeCatagoryMapper;
import com.sinochem.crude.trade.goods.dao.TCrudeOilMapper;
import com.sinochem.crude.trade.goods.dao.TCrudeOriginAreaMapper;
import com.sinochem.crude.trade.goods.dao.TCrudeOriginMapper;
import com.sinochem.crude.trade.goods.dao.TCrudeQualityMapper;
import com.sinochem.crude.trade.goods.model.TCrudeCatagory;
import com.sinochem.crude.trade.goods.model.TCrudeOil;
import com.sinochem.crude.trade.goods.model.TCrudeOrigin;
import com.sinochem.crude.trade.goods.model.TCrudeOriginArea;
import com.sinochem.crude.trade.goods.model.TCrudeQuality;
import com.sinochem.crude.trade.goods.query.TCrudeOilQuery;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoService;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoVO;

@Service
public class CrudeOilInfoServiceImpl implements CrudeOilInfoService {

	@Autowired
	TCrudeOilMapper tCrudeOilMapper;
	@Autowired
	TCrudeQualityMapper tCrudeQualityMapper;
	@Autowired
	TCrudeCatagoryMapper tCrudeCatagoryMapper;
	@Autowired
	TCrudeOriginAreaMapper tCrudeOriginAreaMapper;
	@Autowired
	TCrudeOriginMapper tCrudeOriginMapper;
	@Autowired
	private UUIDGenerator uuidGenerator;
	
	@Override
	public List<CrudeOilInfoVO> findCrudeOilInfos(String crudeName, Long catagoryId) {
		TCrudeOilQuery query = new TCrudeOilQuery();
		query.setAliveFlag("1");
		query.setCrudeName(crudeName);
		query.setCatagoryId(catagoryId);
		return tCrudeOilMapper.selectCrudeOilInfos(query);
	}

	@Override
	public CrudeOilInfoVO findCrudeOilInfoById(Long id) {
		if(id == null) return null;
		TCrudeOilQuery query = new TCrudeOilQuery();
		query.setId(id);
		List<CrudeOilInfoVO> vos = tCrudeOilMapper.selectCrudeOilInfos(query);
		if(CollectionUtils.isNotEmpty(vos)){
			return vos.get(0);
		}
		return null;
	}

	@Override
	public CrudeOilInfoVO findCrudeOilInfoByUUID(String uuid) {
		if(StringUtils.isBlank(uuid)) return null;
		TCrudeOilQuery query = new TCrudeOilQuery();
		query.setUuid(uuid);
		List<CrudeOilInfoVO> vos = tCrudeOilMapper.selectCrudeOilInfos(query);
		if(CollectionUtils.isNotEmpty(vos)){
			return vos.get(0);
		}
		return null;
	}
	
	@Transactional
	@Override
	public int addCrudeOilInfo(CrudeOilInfoVO crudeOilInfoVO) {

		TCrudeOil crudeOil = new TCrudeOil();
		BeanUtils.copyProperties(crudeOilInfoVO, crudeOil);
		
		//原油产地区域
		TCrudeOriginArea crudeOriginArea = new TCrudeOriginArea();
		if(crudeOilInfoVO.getOriginAreaId() == null && 
				(StringUtil.isNotBlank(crudeOilInfoVO.getAreaNameC()) || 
						StringUtil.isNotBlank(crudeOilInfoVO.getAreaNameE()))){
			crudeOriginArea.setUuid(uuidGenerator.gain());
			crudeOriginArea.setAreaNameC(crudeOilInfoVO.getAreaNameC());
			crudeOriginArea.setAreaNameE(crudeOilInfoVO.getAreaNameE());
			tCrudeOriginAreaMapper.insert(crudeOriginArea);
			crudeOil.setOriginAreaId(crudeOriginArea.getId());
		}
				
		//原油产地
		TCrudeOrigin crudeOrigin = new TCrudeOrigin();
		if(crudeOilInfoVO.getOriginId() == null && 
				(StringUtil.isNotBlank(crudeOilInfoVO.getOriginNameC()) || 
						StringUtil.isNotBlank(crudeOilInfoVO.getOriginNameE()))){
			crudeOrigin.setUuid(uuidGenerator.gain());
			crudeOrigin.setAreaId(crudeOil.getOriginAreaId());
			crudeOrigin.setOriginNameC(crudeOilInfoVO.getOriginNameC());
			crudeOrigin.setOriginNameE(crudeOilInfoVO.getOriginNameE());
			tCrudeOriginMapper.insert(crudeOrigin);
			crudeOil.setOriginId(crudeOrigin.getId());
		}
		
		//原油类型
		TCrudeCatagory crudeCatagory = new TCrudeCatagory();
		if(crudeOilInfoVO.getCatagoryId() == null && 
				(StringUtil.isNotBlank(crudeOilInfoVO.getCatagoryNameC()) || 
						StringUtil.isNotBlank(crudeOilInfoVO.getCatagoryNameE()))){
			crudeCatagory.setUuid(uuidGenerator.gain());
			crudeCatagory.setCatagoryNameC(crudeOilInfoVO.getCatagoryNameC());
			crudeCatagory.setCatagoryNameE(crudeOilInfoVO.getCatagoryNameE());
			tCrudeCatagoryMapper.insert(crudeCatagory);
			crudeOil.setCatagoryId(crudeCatagory.getId());
		}
		
		//原油产品
		crudeOil.setUuid(uuidGenerator.gain());
		int result = tCrudeOilMapper.insert(crudeOil);
		if(result == 1){
			//原油品质
			TCrudeQuality crudeQuality = new TCrudeQuality();
			BeanUtils.copyProperties(crudeOilInfoVO, crudeQuality);
			crudeQuality.setUuid(uuidGenerator.gain());
			crudeQuality.setCrudeId(crudeOil.getId());
			tCrudeQualityMapper.insert(crudeQuality);
		}
		
		return result;
	}

	@Override
	public CrudeOilInfoVO findByENameAndAreaId(Long areaId) {
		return tCrudeOriginMapper.queryByAndOrginById(areaId);
	}
}

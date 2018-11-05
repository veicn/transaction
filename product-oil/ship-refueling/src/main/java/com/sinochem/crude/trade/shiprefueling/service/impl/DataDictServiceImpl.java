package com.sinochem.crude.trade.shiprefueling.service.impl;

import com.eyeieye.melody.util.DateUtil;
import com.eyeieye.melody.util.StringUtil;
import com.eyeieye.melody.web.url.URLBroker;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shiprefueling.constants.Constants;
import com.sinochem.crude.trade.shiprefueling.controller.common.KeyGenUtils;
import com.sinochem.crude.trade.shiprefueling.controller.common.SimplePageInfo;
import com.sinochem.crude.trade.shiprefueling.dao.InfoMapper;
import com.sinochem.crude.trade.shiprefueling.dao.IryQuotationMapper;
import com.sinochem.crude.trade.shiprefueling.dao.TDataDictMapper;
import com.sinochem.crude.trade.shiprefueling.domain.po.Chments;
import com.sinochem.crude.trade.shiprefueling.domain.po.Info;
import com.sinochem.crude.trade.shiprefueling.domain.po.IryQuotation;
import com.sinochem.crude.trade.shiprefueling.domain.po.TDataDict;
import com.sinochem.crude.trade.shiprefueling.enums.BusinessType;
import com.sinochem.crude.trade.shiprefueling.enums.FuelOilEnums;
import com.sinochem.crude.trade.shiprefueling.enums.OilTypeEnums;
import com.sinochem.crude.trade.shiprefueling.enums.TypeOfShippingEnums;
import com.sinochem.crude.trade.shiprefueling.model.query.InfoQuery;
import com.sinochem.crude.trade.shiprefueling.model.query.IryQuotationQuery;
import com.sinochem.crude.trade.shiprefueling.model.vo.ChmentsVO;
import com.sinochem.crude.trade.shiprefueling.model.vo.InfoVO;
import com.sinochem.crude.trade.shiprefueling.service.*;
import com.sinochem.crude.trade.shiprefueling.utils.DictoryUtils;
import com.sinochem.it.b2b.common.exception.BizException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class DataDictServiceImpl implements DataDictService {

	@Autowired
	private TDataDictMapper tDataDictMapper;


	/**
	 * 查询船加油和原料油
	 * @param tDataDict
	 * @return
	 */
	@Override
	public List<Map<String , String>> getOilClassificationDataDict(String typeCode , String pDictCode) {
		TDataDict tDataDict = new TDataDict();
		tDataDict.setTypeCode(typeCode);
		tDataDict.setpDictCode(pDictCode);
		return tDataDictMapper.selectOil(tDataDict);
	}



}

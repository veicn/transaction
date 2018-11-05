package com.sinochem.crude.trade.shiprefueling.service;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shiprefueling.controller.common.SimplePageInfo;
import com.sinochem.crude.trade.shiprefueling.dao.InfoMapper;
import com.sinochem.crude.trade.shiprefueling.domain.po.Info;
import com.sinochem.crude.trade.shiprefueling.domain.po.IryQuotation;
import com.sinochem.crude.trade.shiprefueling.domain.po.TDataDict;
import com.sinochem.crude.trade.shiprefueling.model.query.InfoQuery;
import com.sinochem.crude.trade.shiprefueling.model.query.IryQuotationQuery;
import com.sinochem.crude.trade.shiprefueling.model.vo.ChmentsVO;
import com.sinochem.crude.trade.shiprefueling.model.vo.InfoVO;
import com.sinochem.it.b2b.common.exception.BizException;

import java.util.List;
import java.util.Map;

public interface DataDictService {

	/**
	 * 获取油品类型 或者 规格
	 * @param tDataDict
	 * @return
	 */
	public List<Map<String , String>> getOilClassificationDataDict(String typeCode , String pDictCode);


}

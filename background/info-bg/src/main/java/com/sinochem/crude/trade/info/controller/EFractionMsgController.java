package com.sinochem.crude.trade.info.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.URLMapping;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.info.model.EFractionMsgVO;
import com.sinochem.crude.trade.info.query.FractionQuery;
import com.sinochem.crude.trade.info.service.EFractionMsgService;
import com.sinochem.it.b2b.member.access.WithoutAccess;

@Controller
public class EFractionMsgController {

	@Autowired
	private EFractionMsgService eFractionMsgService;

	public static final Log log = LogFactory.getLog(EFractionMsgController.class);

	/**
	 * 馏分信息列表
	 * @param query
	 * @return
	 * @throws BizException
	 */
	@WithoutAccess
	@RequestMapping(value = URLMapping.EFRACTIONMSG_LIST)
	@ResponseBody
	public ResultDatas<List<EFractionMsgVO>> eFractionMsgList(@RequestBody FractionQuery query) throws BizException {

		ResultDatas<List<EFractionMsgVO>> resultDatas = new ResultDatas<>();
		// return false if no condition 
		if (null == query) {
			resultDatas.setFail("");
			return resultDatas;
		}

		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(query.getPageNum());
		pageInfo.setPageSize(query.getPageSize());

		String crudeNameE = query.getCrudeNameE();
		Page<Map<String, Object>> mapListd = eFractionMsgService.queryeFractionMsg(crudeNameE, pageInfo);		
		
		resultDatas.setPageNum(mapListd.getPageNum());
		resultDatas.setPageSize(mapListd.getPageSize());
		resultDatas.setPageCount(mapListd.getPages());
		resultDatas.setTotal(mapListd.getTotal());
		resultDatas.setDatas(BeanConvertUtils.mapToBeanInList(mapListd,EFractionMsgVO.class));

		return resultDatas;
	}

}

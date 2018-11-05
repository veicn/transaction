package com.sinochem.crude.trade.transport.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;
import com.sinochem.crude.trade.common.SimplePageInfo;

public class IntentionQuery extends QueryBase {

	/**
	 * 询盘查询条件
	 */
	private static final long serialVersionUID = 1L;

	/** 询盘单编号 */
	String intentionCode;

	/** 船盘uuid */
	String shipPlateUuid;

	/** 货盘uuid */
	String palletUuid;

	
	/** 询盘单编号 */
	public String getIntentionCode() {
		return intentionCode;
	}

	/** 询盘单编号 */
	public void setIntentionCode(String intentionCode) {
		this.intentionCode = intentionCode;
	}

	/** 船盘uuid */
	public String getShipPlateUuid() {
		return shipPlateUuid;
	}

	/** 船盘uuid */
	public void setShipPlateUuid(String shipPlateUuid) {
		this.shipPlateUuid = shipPlateUuid;
	}

	/** 货盘uuid */
	public String getPalletUuid() {
		return palletUuid;
	}

	/** 货盘uuid */
	public void setPalletUuid(String palletUuid) {
		this.palletUuid = palletUuid;
	}

	@Override
	public Map<String, String> getParameters() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("intentionCode", getIntentionCode());
		param.put("shipPlateUuid", shipPlateUuid);
		return param;
	}

	public SimplePageInfo getPageInfo() {
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(super.getCurrentPage());
		pageInfo.setPageSize(super.getPageSize());
		return pageInfo;
	}
}

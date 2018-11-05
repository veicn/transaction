package com.sinochem.crude.trade.orderexecute.query;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.orderexecute.commons.QueryBaseExt;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;

public class InspectionOrderQuery extends QueryBaseExt {
	private static final long serialVersionUID = 3615095238888008343L;
	
	/**
	 * 商检公司ID
	 */
	private Long memberId;
	/**
	 * 港口名称
	 */
	private String portName;
	/**
	 * 船名
	 */
	private String shipName;
	/**
	 * 到货开始时间
	 */
	private String ddrStart;
	/**
	 * 到货结束时间
	 */
	private String ddrEnd;
	
	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public String getDdrStart() {
		return ddrStart;
	}

	public void setDdrStart(String ddrStart) {
		this.ddrStart = ddrStart;
	}

	public String getDdrEnd() {
		return ddrEnd;
	}

	public void setDdrEnd(String ddrEnd) {
		this.ddrEnd = ddrEnd;
	}

	public Map<String, Object> getQueryParam() {
		Map<String, Object> params = new HashMap<>();
		params.put("memberId", this.memberId);
		params.put("portName", this.portName);
		params.put("shipName", this.shipName);
		
		//日期处理
		if(StringUtils.isNotBlank(this.ddrStart)) {
			try {
				params.put("ddrStart", DateTimeUtils.toDate(this.ddrStart, "yyyy-MM-dd"));
			} catch (Exception e) {
				throw new OrderExecException("orderexecute.code.00088","日期格式不正确");
			}
		}
		if(StringUtils.isNotBlank(this.ddrEnd)) {
			try {
				params.put("ddrEnd", DateTimeUtils.toDate(this.ddrEnd, "yyyy-MM-dd"));
			} catch (Exception e) {
				throw new OrderExecException("orderexecute.code.00088","日期格式不正确");
			}
		}
		
		return params;
	}
	
	@Override
	public Map<String, String> getParameters() {
		Map<String, String> params = new HashMap<>();
		params.put("memberId", String.valueOf(this.memberId));
		params.put("portName", this.portName);
		params.put("shipName", this.shipName);
		params.put("ddrStart", this.ddrStart);
		params.put("ddrEnd", this.ddrEnd);
		
		return params;
	}
	
	public SimplePageInfo getPageInfo(){
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(super.getCurrentPage());
		pageInfo.setPageSize(super.getPageSize());
		
		return pageInfo;
	}
}

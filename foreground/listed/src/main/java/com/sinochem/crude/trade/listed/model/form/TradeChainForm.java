package com.sinochem.crude.trade.listed.model.form;

import com.sinochem.crude.trade.listed.model.vo.tradingChainVo.TradeChainDemandVO;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 创建贸易链界面
 * @author jiangxiuqiang
 *
 */
public class TradeChainForm implements Serializable {
	/**
	 * 报价单id
	 */
	private Long biddingId;

	/**
	 * 贸易链uuid
	 */
	private String uuid;

	/**
	 * 贸易链操作状态
	 */
	private String status;


	/**
	 * 草约订单详情
	 */
	@Valid
	private List<TradeChainDemandVO> biddingList = new ArrayList<TradeChainDemandVO>();

	public Long getBiddingId() {
		return biddingId;
	}

	public void setBiddingId(Long biddingId) {
		this.biddingId = biddingId;
	}

	public List<TradeChainDemandVO> getBiddingList() {
		return biddingList;
	}

	public void setBiddingList(List<TradeChainDemandVO> biddingList) {
		this.biddingList = biddingList;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}

package com.sinochem.crude.trade.shipping.model.vo;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.shipping.model.query.ConfirmationSheetQuery;

public class ConfirmationSheetQueryVO {

	/** 租船合同编号 */
	private String charterPartyNumber;

	/** 产品 */
	private String product;

	/** 当前有效状态 */
	private String status;

	/** 创建时间-开始 */
	private String createDateStart;

	/** 创建时间-结束 */
	private String createDateEnd;

	public String getCharterPartyNumber() {
		return charterPartyNumber;
	}

	public void setCharterPartyNumber(String charterPartyNumber) {
		this.charterPartyNumber = charterPartyNumber;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateDateStart() {
		return createDateStart;
	}

	public void setCreateDateStart(String createDateStart) {
		this.createDateStart = createDateStart;
	}

	public String getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(String createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	/**
	 * 将VO转换为领域类domain
	 */

	public ConfirmationSheetQuery voToDomain() {
		ConfirmationSheetQuery confirmationSheetQuery = new ConfirmationSheetQuery();

		if (StringUtil.isNotBlank(this.status)) {
			confirmationSheetQuery.setStatus(this.status);
		}

		if (StringUtil.isNotBlank(this.charterPartyNumber)) {
			confirmationSheetQuery.setCharterPartyNumber(this.charterPartyNumber);
		}

		if (StringUtil.isNotBlank(this.createDateStart)) {
			confirmationSheetQuery.setCreateDateStart(DateUtil.getDate(this.createDateStart));
		}

		if (StringUtil.isNotBlank(this.createDateEnd)) {
			confirmationSheetQuery.setCreateDateEnd(DateUtil.getDate(this.createDateEnd));
		}

		if (StringUtil.isNotBlank(this.product)) {
			confirmationSheetQuery.setProduct(this.product);
		}

		return confirmationSheetQuery;
	}

}

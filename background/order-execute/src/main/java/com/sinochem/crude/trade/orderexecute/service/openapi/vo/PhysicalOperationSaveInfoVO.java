package com.sinochem.crude.trade.orderexecute.service.openapi.vo;

import java.io.Serializable;

import com.sinochem.crude.trade.orderexecute.service.openapi.constants.TypeCodeEnum;

/**
 * 对账信息VO
 */
public class PhysicalOperationSaveInfoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 订单UUID */
	private String id;
	
	/** 对账类型EST：临时，FINAL：最终  */
	private String type_code;
	
	/**  0:生成结算单，1:撤销结算单*/
	private int status;
	
	private String values;

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType_code() {
		return type_code;
	}

	public void setType_code(TypeCodeEnum typeCodeEnum) {
		this.type_code = typeCodeEnum.name();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}

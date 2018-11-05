package com.sinochem.crude.trade.transport.model.res;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 未租船订单相关信息VO 
 * @author wangxinran
 *
 */
public class ForShipOrderInfoRes implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -850657959447491665L;

	/** 订单编号 */
	private String orderNo;
	
	/** 创建时间 */
	private String createDate;
	
	/** 合同号 */
	private String contractNo;
	
	/** 供应商 */
	private String sellerCustomerName;
	
	/** 油品名称 */
	private String name;
	
	/** 数量 */
	private BigDecimal quantity;
	
	/** 数量单位编号 */
	private String quantityUnitCode;
	
	/** 数量单位名称 */
	private String quantityUnitName;
	
	/** 装港 */
	private String loadPort;
	
	/** 卸港 */
	private String unport;
	
	/** 装期 */
	private String deliveryDate;
	
	/** 装期开始 */
	private String deliveryDateStart;
	
	/** 装期结束 */
	private String deliveryDateEnd;

	/** 装港 */
	private String loadPortName;

	/** 卸港 */
	private String unportName;

    /** 油品名称 */
    private String nameOil;


    public String getLoadPortName() {
        return loadPortName;
    }

    public void setLoadPortName(String loadPortName) {
        this.loadPortName = loadPortName;
    }

    public String getUnportName() {
        return unportName;
    }

    public void setUnportName(String unportName) {
        this.unportName = unportName;
    }

    public String getNameOil() {
        return nameOil;
    }

    public void setNameOil(String nameOil) {
        this.nameOil = nameOil;
    }

    /** 订单编号 */
	public String getOrderNo() {
		return orderNo;
	}

	/** 订单编号 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/** 创建时间 */
	public String getCreateDate() {
		return createDate;
	}
	
	/** 创建时间 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/** 合同号 */
	public String getContractNo() {
		return contractNo;
	}

	/** 合同号 */
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	/** 供应商 */
	public String getSellerCustomerName() {
		return sellerCustomerName;
	}

	/** 供应商 */
	public void setSellerCustomerName(String sellerCustomerName) {
		this.sellerCustomerName = sellerCustomerName;
	}

	/** 油品名称 */
	public String getName() {
		return name;
	}

	/** 油品名称 */
	public void setName(String name) {
		this.name = name;
	}

	/** 数量 */
	public BigDecimal getQuantity() {
		return quantity;
	}

	/** 数量 */
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	
	/** 数量单位代码 */
	public String getQuantityUnitCode() {
		return quantityUnitCode;
	}

	/** 数量单位代码 */
	public void setQuantityUnitCode(String quantityUnitCode) {
		this.quantityUnitCode = quantityUnitCode;
	}
	
	/** 数量单位名称 */
	public String getQuantityUnitName() {
		return quantityUnitName;
	}

	/** 数量单位名称 */
	public void setQuantityUnitName(String quantityUnitName) {
		this.quantityUnitName = quantityUnitName;
	}

	/** 订单编号 */
	public String getLoadPort() {
		return loadPort;
	}

	/** 订单编号 */
	public void setLoadPort(String loadPort) {
		this.loadPort = loadPort;
	}

	/** 订单编号 */
	public String getUnport() {
		return unport;
	}

	/** 订单编号 */
	public void setUnport(String unport) {
		this.unport = unport;
	}
	
	/** 装期 */
	public String getDeliveryDate() {
		return deliveryDate;
	}

	/** 装期 */
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	/** 装期开始 */
	public String getDeliveryDateStart() {
		return deliveryDateStart;
	}

	/** 装期开始 */
	public void setDeliveryDateStart(String deliveryDateStart) {
		this.deliveryDateStart = deliveryDateStart;
	}
	
	/** 装期结束 */
	public String getDeliveryDateEnd() {
		return deliveryDateEnd;
	}

	/** 装期结束 */
	public void setDeliveryDateEnd(String deliveryDateEnd) {
		this.deliveryDateEnd = deliveryDateEnd;
	}
}

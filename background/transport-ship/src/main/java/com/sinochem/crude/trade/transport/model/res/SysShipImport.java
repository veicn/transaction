package com.sinochem.crude.trade.transport.model.res;

import java.io.Serializable;
import java.math.BigDecimal;

public class SysShipImport implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 船舶代码IMO */
	private String imo;

	/** 船舶MMSI */
	private String mmsi;

	/** 船舶用途类型 */
	private String useType;

	/** 船名 */
	private String name;

	/** 船型 */
	private String type;

	/** 建成年份 */
	private String completeDate;

	/** 船体 */
	private String hull;

	/** 船长（米） */
	private java.math.BigDecimal length;

	/** 船宽（米） */
	private java.math.BigDecimal wide;

	/** 吃水（米） */
	private java.math.BigDecimal draft;

	/** 载重吨 */
	private java.math.BigDecimal loadQuantity;

	/** 载重容积 */
	private java.math.BigDecimal capacity;
	
	/**船旗*/
	private String pennant;

	/** 船舶代码IMO */
	public void setImo(String imo) {
		this.imo = imo;
	}

	/** 船舶代码IMO */
	public String getImo() {
		return this.imo;
	}

	/** 船舶MMSI */
	public String getMmsi() {
		return mmsi;
	}

	/** 船舶MMSI */
	public void setMmsi(String mmsi) {
		this.mmsi = mmsi;
	}

	/** 船舶用途类型 */
	public void setUseType(String useType) {
		this.useType = useType;
	}

	/** 船舶用途类型 */
	public String getUseType() {
		return this.useType;
	}

	/** 船名 */
	public void setName(String name) {
		this.name = name;
	}

	/** 船名 */
	public String getName() {
		return this.name;
	}

	/** 船型 */
	public void setType(String type) {
		this.type = type;
	}

	/** 船型 */
	public String getType() {
		return this.type;
	}

	/** 建成年份 */
	public void setCompleteDate(String completeDate) {
		this.completeDate = completeDate;
	}

	/** 建成年份 */
	public String getCompleteDate() {
		return this.completeDate;
	}

	/** 船体 */
	public void setHull(String hull) {
		this.hull = hull;
	}

	/** 船体 */
	public String getHull() {
		return this.hull;
	}

	/** 船长（米） */
	public void setLength(java.math.BigDecimal length) {
		this.length = length;
	}

	/** 船长（米） */
	public java.math.BigDecimal getLength() {
		return this.length;
	}

	/** 船宽（米） */
	public void setWide(java.math.BigDecimal wide) {
		this.wide = wide;
	}

	/** 船宽（米） */
	public java.math.BigDecimal getWide() {
		return this.wide;
	}

	/** 吃水（米） */
	public void setDraft(java.math.BigDecimal draft) {
		this.draft = draft;
	}

	/** 吃水（米） */
	public java.math.BigDecimal getDraft() {
		return this.draft;
	}

	/** 载重吨 */
	public void setLoadQuantity(java.math.BigDecimal loadQuantity) {
		this.loadQuantity = loadQuantity;
	}

	/** 载重吨 */
	public java.math.BigDecimal getLoadQuantity() {
		return this.loadQuantity;
	}

	/** 载重容积 */
	public void setCapacity(java.math.BigDecimal capacity) {
		this.capacity = capacity;
	}

	/** 载重容积 */
	public java.math.BigDecimal getCapacity() {
		return this.capacity;
	}
	
	/**船旗*/
	public String getPennant() {
		return pennant;
	}

	/**船旗*/
	public void setPennant(String pennant) {
		this.pennant = pennant;
	}

	public SysShipImport(String imo, String mmsi, String useType, String name, String type, String completeDate,
			String hull, BigDecimal length, BigDecimal wide, BigDecimal draft, BigDecimal loadQuantity,
			BigDecimal capacity, String pennant) {
		super();
		this.imo = imo;
		this.mmsi = mmsi;
		this.useType = useType;
		this.name = name;
		this.type = type;
		this.completeDate = completeDate;
		this.hull = hull;
		this.length = length;
		this.wide = wide;
		this.draft = draft;
		this.loadQuantity = loadQuantity;
		this.capacity = capacity;
		this.pennant = pennant;
	}
	

}

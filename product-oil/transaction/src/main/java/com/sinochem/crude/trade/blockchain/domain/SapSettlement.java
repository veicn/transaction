package com.sinochem.crude.trade.blockchain.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SapSettlement implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/***/
	private Long ID;  
	
	/***/
	private String BSTKD;  
	
	/**订单类型*/
	private String VBTYP;  
	
	/**销售编号*/
	private String VBELN;  
	
	/**客户编码*/
	private String KUNNR;  
	
	/**客户名称*/
	private String NAME1;  
	
	/**贸易条款*/
	private String MYTK;  
	
	/**付款条件*/
	private String ZTERM;  
	
	/**船名*/
	private String ZSHIP;  
	
	/**提单日*/
	private String KEYDT;  
	
	/**最终用户*/
	private String TEXT5;  
	
	/**预计合同付款时间*/
	private String TEXT4;  
	
	/**外部订单号*/
	private String AUFEX;  
	
	/**币种*/
	private String WAERK;  
	
	/**最终结算总金额(美元)*/
	private String NETWR;  
	
	/**最终结算单价(美元/桶)*/
	private String DANJIA;  
	
	/**销售编号行项目*/
	private String POSNR;  
	
	/**油种编码*/
	private String MATNR;  
	
	/**油种描述*/
	private String MAKTX;  
	
	/**合同量(桶)*/
	private String KWMENG;  
	
	/**溢短装*/
	private String EDZ;  
	
	/**单价(美元/桶)*/
	private String NETPR;  
	
	/**计价期*/
	private String TEXT1;  
	
	/**计价基准*/
	private String TEXT2;  
	
	/**计价公式*/
	private String TEXT3;  
	
	/**1(最新)0(否)*/
	private String NEWEST;  
	
	/**起息日*/
	private String VALTG;  
	
	/**主合同号*/
	private String MCONT;
	
	/**数据状态*/
	private String aliveFlag;  
	
	/**创建人*/
	private String createUser;  
	
	/**创建时间*/
	private String createDate;  
	
	/**修改人*/
	private String updateUser;  
	
	/**修改时间*/
	private String updateDate;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getBSTKD() {
		return BSTKD;
	}

	public void setBSTKD(String bSTKD) {
		BSTKD = bSTKD;
	}

	public String getVBTYP() {
		return VBTYP;
	}

	public void setVBTYP(String vBTYP) {
		VBTYP = vBTYP;
	}

	public String getVBELN() {
		return VBELN;
	}

	public void setVBELN(String vBELN) {
		VBELN = vBELN;
	}

	public String getKUNNR() {
		return KUNNR;
	}

	public void setKUNNR(String kUNNR) {
		KUNNR = kUNNR;
	}

	public String getNAME1() {
		return NAME1;
	}

	public void setNAME1(String nAME1) {
		NAME1 = nAME1;
	}

	public String getMYTK() {
		return MYTK;
	}

	public void setMYTK(String mYTK) {
		MYTK = mYTK;
	}

	public String getZTERM() {
		return ZTERM;
	}

	public void setZTERM(String zTERM) {
		ZTERM = zTERM;
	}

	public String getZSHIP() {
		return ZSHIP;
	}

	public void setZSHIP(String zSHIP) {
		ZSHIP = zSHIP;
	}

	public String getKEYDT() {
		return KEYDT;
	}

	public void setKEYDT(String kEYDT) {
		KEYDT = kEYDT;
	}

	public String getTEXT5() {
		return TEXT5;
	}

	public void setTEXT5(String tEXT5) {
		TEXT5 = tEXT5;
	}

	public String getTEXT4() {
		return TEXT4;
	}

	public void setTEXT4(String tEXT4) {
		TEXT4 = tEXT4;
	}

	public String getAUFEX() {
		return AUFEX;
	}

	public void setAUFEX(String aUFEX) {
		AUFEX = aUFEX;
	}

	public String getWAERK() {
		return WAERK;
	}

	public void setWAERK(String wAERK) {
		WAERK = wAERK;
	}

	public String getNETWR() {
		return NETWR;
	}

	public void setNETWR(String nETWR) {
		NETWR = nETWR;
	}

	public String getDANJIA() {
		return DANJIA;
	}

	public void setDANJIA(String dANJIA) {
		DANJIA = dANJIA;
	}

	public String getPOSNR() {
		return POSNR;
	}

	public void setPOSNR(String pOSNR) {
		POSNR = pOSNR;
	}

	public String getMATNR() {
		return MATNR;
	}

	public void setMATNR(String mATNR) {
		MATNR = mATNR;
	}

	public String getMAKTX() {
		return MAKTX;
	}

	public void setMAKTX(String mAKTX) {
		MAKTX = mAKTX;
	}

	public String getKWMENG() {
		return KWMENG;
	}

	public void setKWMENG(String kWMENG) {
		KWMENG = kWMENG;
	}

	public String getEDZ() {
		return EDZ;
	}

	public void setEDZ(String EDZ) {
		this.EDZ = EDZ;
	}

	public String getNETPR() {
		return NETPR;
	}

	public void setNETPR(String nETPR) {
		NETPR = nETPR;
	}

	public String getTEXT1() {
		return TEXT1;
	}

	public void setTEXT1(String tEXT1) {
		TEXT1 = tEXT1;
	}

	public String getTEXT2() {
		return TEXT2;
	}

	public void setTEXT2(String tEXT2) {
		TEXT2 = tEXT2;
	}

	public String getTEXT3() {
		return TEXT3;
	}

	public void setTEXT3(String tEXT3) {
		TEXT3 = tEXT3;
	}

	public String getNEWEST() {
		return NEWEST;
	}

	public void setNEWEST(String nEWEST) {
		NEWEST = nEWEST;
	}

	public String getVALTG() {
		return VALTG;
	}

	public void setVALTG(String vALTG) {
		VALTG = vALTG;
	}

	public String getAliveFlag() {
		return aliveFlag;
	}

	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}  
	
	public String getMCONT() {
		return MCONT;
	}

	public void setMCONT(String mCONT) {
		MCONT = mCONT;
	}

	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",this.ID);
	map.put("BSTKD",this.BSTKD);
	map.put("VBTYP",this.VBTYP);
	map.put("vbeln",this.VBELN);
	map.put("VBELN",this.KUNNR);
	map.put("NAME1",this.NAME1);
	map.put("MYTK",this.MYTK);
	map.put("ZTERM",this.ZTERM);
	map.put("ZSHIP",this.ZSHIP);
	map.put("KEYDT",this.KEYDT);
	map.put("TEXT5",this.TEXT5);
	map.put("TEXT4",this.TEXT4);
	map.put("AUFEX",this.AUFEX);
	map.put("WAERK",this.WAERK);
	map.put("NETWR",this.NETWR);
	map.put("DANJIA",this.DANJIA);
	map.put("POSNR",this.POSNR);
	map.put("matnr",this.MATNR);
	map.put("MATNR",this.MAKTX);
	map.put("KWMENG",this.KWMENG);
	map.put("EDZ",this.EDZ);
	map.put("NETPR",this.NETPR);
	map.put("TEXT1",this.TEXT1);
	map.put("TEXT2",this.TEXT2);
	map.put("TEXT3",this.TEXT3);
	map.put("NEWEST",this.NEWEST);
	map.put("VALTG",this.VALTG);
	map.put("MCONT",this.MCONT);
	map.put("aliveFlag",this.aliveFlag);
	map.put("createUser",this.createUser);
	map.put("createDate",this.createDate);
	map.put("updateUser",this.updateUser);
	map.put("updateDate",this.updateDate);
			return map;
	}
	
}
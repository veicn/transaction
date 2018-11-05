package com.sinochem.crude.trade.shipping.model.vo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.base.BaseDomainVO;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.shipping.domain.Agreement;

public class AgreementVO extends BaseDomainVO<Agreement> {

	/**主键_ID*/
	private Long agreementId;  
	
	/**UUID*/
	private String uuid;  
	
	/**UUID*/
	private String demandsUuid;
	
	/**买家ID*/
	private Long buyerId;  
	
	/**卖家ID*/
	private Long sellerId;  
	
	/**租船需求表ID*/
	private Long demandsId;  
	
	/**租船代理协议编码*/
	private String agreementCd;  
	
	/**订单ID*/
	private Long orderId;  
	
	/**签约方日期*/
	private String charterPartyDate;  
	
	/**托运人*/
	private String consignor;  
	
	/**租船代理名*/
	private String charteringAgentNm;  
	
	/**租船契约*/
	private String charterParty;  
	
	/**船名*/
	private String vesselName;  
	
	/**产品*/
	private String productNm;  
	
	/**数量*/
	private String quantity;  
	
	/**误差范围*/
	private String rangeOfError;  
	
	/**装货港*/
	private String portOfLoading;  
	
	/**卸货港*/
	private String portOfDischarge;  
	
	/**受载期开始*/
	private String laycanStrat;  
	
	/**受载期结束*/
	private String laycanEnd;  
	
	/**标识*/
	private String imo;  
	
	/**需求单创建者ID*/
	private Long epMemberId;  
	
	/**附件地址*/
	private String uploadQ88;  
	
	/**Q88文件名称*/
	private String uploadQ88FileNm;  
	
	/**附件地址*/
	private String uploadCp;  
	
	/**合同文件名称*/
	private String uploadCpFileNm;  
	
	/**状态*/
	private String status;  
	
	/**预留字段1*/
	private String ext1;  
	
	/**版本号*/
	private String version;  
	
	/**当前有效状态*/
	private String aliveFlag;  
	
	/**查询开始日期*/
	private String queryStart; 
	
	/**查询结束日期*/
	private String queryEnd; 
	/**预览*/
	private String lookUrl;
	/**file type*/
	private String fileFormat1;
	
	private String fileFormat2;
	/** 产品来源 */
	private String productSourceCode;
	/**
	 * 泉炼表示Flag
	 */
	private String quanzhouFlag;

	public String getQuanzhouFlag() {
		return quanzhouFlag;
	}

	public void setQuanzhouFlag(String quanzhouFlag) {
		this.quanzhouFlag = quanzhouFlag;
	}

	public String getQueryStart() {
		return queryStart;
	}
	public void setQueryStart(String queryStart) {
		this.queryStart = queryStart;
	}
	public String getQueryEnd() {
		return queryEnd;
	}
	public void setQueryEnd(String queryEnd) {
		this.queryEnd = queryEnd;
	}
		/**主键_ID*/
	public void setAgreementId(Long agreementId){
		this.agreementId=agreementId;
	}
	/**主键_ID*/
	public Long getAgreementId(){
		return this.agreementId;
	}
	
	/**UUID*/
	public void setUuid(String uuid){
		this.uuid=uuid;
	}
	/**UUID*/
	public String getUuid(){
		return this.uuid;
	}
	
	/**买家ID*/
	public void setBuyerId(Long buyerId){
		this.buyerId=buyerId;
	}
	/**买家ID*/
	public Long getBuyerId(){
		return this.buyerId;
	}
	
	/**卖家ID*/
	public void setSellerId(Long sellerId){
		this.sellerId=sellerId;
	}
	/**卖家ID*/
	public Long getSellerId(){
		return this.sellerId;
	}
	
	/**租船需求表ID*/
	public void setDemandsId(Long demandsId){
		this.demandsId=demandsId;
	}
	/**租船需求表ID*/
	public Long getDemandsId(){
		return this.demandsId;
	}
	
	/**租船代理协议编码*/
	public void setAgreementCd(String agreementCd){
		this.agreementCd=agreementCd;
	}
	/**租船代理协议编码*/
	public String getAgreementCd(){
		return this.agreementCd;
	}
	
	/**订单ID*/
	public void setOrderId(Long orderId){
		this.orderId=orderId;
	}
	/**订单ID*/
	public Long getOrderId(){
		return this.orderId;
	}
	
	/**签约方日期*/
	public void setCharterPartyDate(String charterPartyDate){
		this.charterPartyDate=charterPartyDate;
	}
	/**签约方日期*/
	public String getCharterPartyDate(){
		return this.charterPartyDate;
	}
	
	/**托运人*/
	public void setConsignor(String consignor){
		this.consignor=consignor;
	}
	/**托运人*/
	public String getConsignor(){
		return this.consignor;
	}
	
	/**租船代理名*/
	public void setCharteringAgentNm(String charteringAgentNm){
		this.charteringAgentNm=charteringAgentNm;
	}
	/**租船代理名*/
	public String getCharteringAgentNm(){
		return this.charteringAgentNm;
	}
	
	/**租船契约*/
	public void setCharterParty(String charterParty){
		this.charterParty=charterParty;
	}
	/**租船契约*/
	public String getCharterParty(){
		return this.charterParty;
	}
	
	/**船名*/
	public void setVesselName(String vesselName){
		this.vesselName=vesselName;
	}
	/**船名*/
	public String getVesselName(){
		return this.vesselName;
	}
	
	/**产品*/
	public void setProductNm(String productNm){
		this.productNm=productNm;
	}
	/**产品*/
	public String getProductNm(){
		return this.productNm;
	}
	
	/**数量*/
	public void setQuantity(String quantity){
		this.quantity=quantity;
	}
	/**数量*/
	public String getQuantity(){
		return this.quantity;
	}
	
	/**误差范围*/
	public void setRangeOfError(String rangeOfError){
		this.rangeOfError=rangeOfError;
	}
	/**误差范围*/
	public String getRangeOfError(){
		return this.rangeOfError;
	}
	
	/**装货港*/
	public void setPortOfLoading(String portOfLoading){
		this.portOfLoading=portOfLoading;
	}
	/**装货港*/
	public String getPortOfLoading(){
		return this.portOfLoading;
	}
	
	/**卸货港*/
	public void setPortOfDischarge(String portOfDischarge){
		this.portOfDischarge=portOfDischarge;
	}
	/**卸货港*/
	public String getPortOfDischarge(){
		return this.portOfDischarge;
	}
	
	/**受载期开始*/
	public void setLaycanStrat(String laycanStrat){
		this.laycanStrat=laycanStrat;
	}
	/**受载期开始*/
	public String getLaycanStrat(){
		return this.laycanStrat;
	}
	
	/**受载期结束*/
	public void setLaycanEnd(String laycanEnd){
		this.laycanEnd=laycanEnd;
	}
	/**受载期结束*/
	public String getLaycanEnd(){
		return this.laycanEnd;
	}
	
	/**标识*/
	public void setImo(String imo){
		this.imo=imo;
	}
	/**标识*/
	public String getImo(){
		return this.imo;
	}
	
	/**需求单创建者ID*/
	public void setEpMemberId(Long epMemberId){
		this.epMemberId=epMemberId;
	}
	/**需求单创建者ID*/
	public Long getEpMemberId(){
		return this.epMemberId;
	}
	
	/**附件地址*/
	public void setUploadQ88(String uploadQ88){
		this.uploadQ88=uploadQ88;
	}
	/**附件地址*/
	public String getUploadQ88(){
		return this.uploadQ88;
	}
	
	/**Q88文件名称*/
	public void setUploadQ88FileNm(String uploadQ88FileNm){
		this.uploadQ88FileNm=uploadQ88FileNm;
	}
	/**Q88文件名称*/
	public String getUploadQ88FileNm(){
		return this.uploadQ88FileNm;
	}
	
	/**附件地址*/
	public void setUploadCp(String uploadCp){
		this.uploadCp=uploadCp;
	}
	/**附件地址*/
	public String getUploadCp(){
		return this.uploadCp;
	}
	
	/**合同文件名称*/
	public void setUploadCpFileNm(String uploadCpFileNm){
		this.uploadCpFileNm=uploadCpFileNm;
	}
	/**合同文件名称*/
	public String getUploadCpFileNm(){
		return this.uploadCpFileNm;
	}
	
	/**状态*/
	public void setStatus(String status){
		this.status=status;
	}
	/**状态*/
	public String getStatus(){
		return this.status;
	}
	
	/**预留字段1*/
	public void setExt1(String ext1){
		this.ext1=ext1;
	}
	/**预留字段1*/
	public String getExt1(){
		return this.ext1;
	}
	
	/**版本号*/
	public void setVersion(String version){
		this.version=version;
	}
	/**版本号*/
	public String getVersion(){
		return this.version;
	}
	
	/**当前有效状态*/
	public void setAliveFlag(String aliveFlag){
		this.aliveFlag=aliveFlag;
	}
	/**当前有效状态*/
	public String getAliveFlag(){
		return this.aliveFlag;
	}
	
	public String getDemandsUuid() {
		return demandsUuid;
	}
	public void setDemandsUuid(String demandsUuid) {
		this.demandsUuid = demandsUuid;
	}
	public String getLookUrl() {
		return lookUrl;
	}
	public void setLookUrl(String lookUrl) {
		this.lookUrl = lookUrl;
	}
	public String getFileFormat1() {
		return fileFormat1;
	}
	public void setFileFormat1(String fileFormat1) {
		this.fileFormat1 = fileFormat1;
	}
	public String getFileFormat2() {
		return fileFormat2;
	}
	public void setFileFormat2(String fileFormat2) {
		this.fileFormat2 = fileFormat2;
	}
	public String getProductSourceCode() {
		return productSourceCode;
	}

	public void setProductSourceCode(String productSourceCode) {
		this.productSourceCode = productSourceCode;
	}

	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("agreementId",this.agreementId);
		map.put("uuid",this.uuid);
		map.put("buyerId",this.buyerId);
		map.put("sellerId",this.sellerId);
		map.put("demandsId",this.demandsId);
		map.put("agreementCd",this.agreementCd);
		map.put("orderId",this.orderId);
		map.put("charterPartyDate",this.charterPartyDate);
		map.put("consignor",this.consignor);
		map.put("charteringAgentNm",this.charteringAgentNm);
		map.put("charterParty",this.charterParty);
		map.put("vesselName",this.vesselName);
		map.put("productNm",this.productNm);
		map.put("quantity",this.quantity);
		map.put("rangeOfError",this.rangeOfError);
		map.put("portOfLoading",this.portOfLoading);
		map.put("portOfDischarge",this.portOfDischarge);
		map.put("laycanStrat",this.laycanStrat);
		map.put("laycanEnd",this.laycanEnd);
		map.put("imo",this.imo);
		map.put("epMemberId",this.epMemberId);
		map.put("uploadQ88",this.uploadQ88);
		map.put("uploadQ88FileNm",this.uploadQ88FileNm);
		map.put("uploadCp",this.uploadCp);
		map.put("uploadCpFileNm",this.uploadCpFileNm);
		map.put("status",this.status);
		map.put("ext1",this.ext1);
		map.put("version",this.version);
		map.put("aliveFlag",this.aliveFlag);
		map.put("productSourceCode",this.productSourceCode);
		return map;
	}
	@Override
	protected void convertToVO(Agreement domain) {
		this.agreementId = domain.getAgreementId();
		this.uuid = domain.getUuid();
		this.buyerId = domain.getBuyerId();
		this.sellerId = domain.getSellerId();
		this.demandsId = domain.getDemandsId();
		this.agreementCd = domain.getAgreementCd();
		this.charteringAgentNm = domain.getCharteringAgentNm();
		this.orderId = domain.getOrderId();
		this.charterPartyDate = DateUtil.formatDate(domain.getCharterPartyDate());
		this.consignor = domain.getConsignor();
		this.charterParty = domain.getCharterParty();
		this.vesselName = domain.getVesselName();
		this.productNm = domain.getProductNm();
		this.quantity = domain.getQuantity();
		this.rangeOfError = domain.getRangeOfError();
		this.portOfLoading = domain.getPortOfLoading();
		this.portOfDischarge = domain.getPortOfDischarge();
		this.laycanStrat = DateUtil.formatDate(domain.getLaycanStrat());
		this.laycanEnd = DateUtil.formatDate(domain.getLaycanEnd());
		this.imo = domain.getImo();
		this.epMemberId = domain.getEpMemberId();
		this.uploadQ88 = domain.getUploadQ88();
		this.uploadQ88FileNm = domain.getUploadQ88FileNm();
		this.uploadCp = domain.getUploadCp();
		this.uploadCpFileNm = domain.getUploadCpFileNm();
		this.status = domain.getStatus();
		this.ext1 = domain.getExt1();
		this.version = domain.getVersion();
		this.aliveFlag = domain.getAliveFlag();
		this.productSourceCode = domain.getProductSourceCode();
	}
	@Override
	protected Agreement convertToDomain() {
		Agreement  agreement= new Agreement();
		if (StringUtil.isNotBlank(this.uuid)) {
			agreement.setUuid(this.uuid);
		}
		if (this.buyerId != null) {
			agreement.setBuyerId(this.buyerId);
		}
		if (this.sellerId != null ) {
			agreement.setSellerId(this.sellerId);
		}
		if (this.demandsId != null) {
			agreement.setDemandsId(this.demandsId);
		}
		if (this.orderId != null) {
			agreement.setOrderId(this.orderId);
		}
		if (StringUtil.isNotBlank(this.agreementCd)) {
			agreement.setAgreementCd(this.agreementCd);
		}
		if (StringUtil.isNotBlank(this.charterPartyDate)) {
			agreement.setCharterPartyDate(DateUtil.getDate(this.charterPartyDate));
		}
		if (StringUtil.isNotBlank(this.consignor)) {
			agreement.setConsignor(this.consignor);
		}
		if (StringUtil.isNotBlank(this.charteringAgentNm)) {
			agreement.setCharteringAgentNm(this.charteringAgentNm);
		}
		if (StringUtil.isNotBlank(this.charterParty)) {
			agreement.setCharterParty(this.charterParty);
		}
		if (StringUtil.isNotBlank(this.vesselName)) {
			agreement.setVesselName(this.vesselName);
		}
		if (StringUtil.isNotBlank(this.productNm)) {
			agreement.setProductNm(this.productNm);
		}
		if (StringUtil.isNotBlank(this.quantity)) {
			agreement.setQuantity(this.quantity);
		}
		if (StringUtil.isNotBlank(this.rangeOfError)) {
			agreement.setRangeOfError(this.rangeOfError);
		}
		if (StringUtil.isNotBlank(this.portOfLoading)) {
			agreement.setPortOfLoading(this.portOfLoading);
		}
		if (StringUtil.isNotBlank(this.portOfDischarge)) {
			agreement.setPortOfDischarge(this.portOfDischarge);
		}
		if (StringUtil.isNotBlank(this.laycanStrat)) {
			agreement.setLaycanStrat(DateUtil.getDate(this.laycanStrat));
		}
		if (StringUtil.isNotBlank(this.laycanEnd)) {
			agreement.setLaycanEnd(DateUtil.getDate(this.laycanEnd));
		}
		if (StringUtil.isNotBlank(this.imo)) {
			agreement.setImo(this.imo);
		}
		if (this.epMemberId !=null) {
			agreement.setEpMemberId(this.epMemberId);
		}
		if (StringUtil.isNotBlank(this.uploadQ88)) {
			agreement.setUploadQ88(this.uploadQ88);
		}
		if (StringUtil.isNotBlank(this.uploadQ88FileNm)) {
			agreement.setUploadQ88FileNm(this.uploadQ88FileNm);
		}
		if (StringUtil.isNotBlank(this.uploadCp)) {
			agreement.setUploadCp(this.uploadCp);
		}
		if (StringUtil.isNotBlank(this.uploadCpFileNm)) {
			agreement.setUploadCpFileNm(this.uploadCpFileNm);
		}
		if (StringUtil.isNotBlank(this.status)) {
			agreement.setStatus(this.status);
		}
		if (StringUtil.isNotBlank(this.productSourceCode)) {
			agreement.setProductSourceCode(this.productSourceCode);
		}
		return agreement;
	}  
	
	public AgreementVO toObjectVo(Agreement agreement){
		AgreementVO agreementVO = new AgreementVO();
		
		if (StringUtil.isNotBlank(agreement.getUuid())) {
			agreementVO.setUuid(agreement.getUuid());
		}
		if (StringUtil.isNotBlank(agreement.getPortOfLoading())) {
			agreementVO.setPortOfLoading(agreement.getPortOfLoading());
		}
		if (StringUtil.isNotBlank(agreement.getPortOfDischarge())) {
			agreementVO.setPortOfDischarge(agreement.getPortOfDischarge());
		}
		if (StringUtil.isNotBlank(agreement.getVesselName())) {
			agreementVO.setVesselName(agreement.getVesselName());
		}
		if (StringUtil.isNotBlank(agreement.getImo())) {
			agreementVO.setImo(agreement.getImo());
		}
		if (StringUtil.isNotBlank(agreement.getProductNm())) {
			agreementVO.setProductNm(agreement.getProductNm());
		}
		if (agreement.getQuantity() != null) {
			agreementVO.setQuantity(agreement.getQuantity());
		}
		if (agreement.getBuyerId()!= null) {
			agreementVO.setBuyerId(agreement.getBuyerId());
		}
		if (agreement.getSellerId()!= null) {
			agreementVO.setSellerId(agreement.getSellerId());
		}
		if (agreement.getDemandsId()!= null) {
			agreementVO.setDemandsId(agreement.getDemandsId());
		}
		Date laycanStart2 = agreement.getLaycanStrat();
		if (laycanStart2 != null) {
			String formatDate = DateUtil.formatDate(laycanStart2);
			agreementVO.setLaycanStrat(formatDate);
		}
		Date laycanEnd2 = agreement.getLaycanEnd();
		if (laycanEnd2 != null) {
			String formatDate = DateUtil.formatDate(laycanEnd2);
			agreementVO.setLaycanEnd(formatDate);
		}
		Date charterPartyDate2 = agreement.getCharterPartyDate();
		if (charterPartyDate2 != null) {
			String formatDate = DateUtil.formatDate(charterPartyDate2);
			agreementVO.setCharterPartyDate(formatDate);
		}
		if (StringUtil.isNotBlank(agreement.getAgreementCd())) {
			agreementVO.setAgreementCd(agreement.getAgreementCd());
		}
		if (StringUtil.isNotBlank(agreement.getConsignor())) {
			agreementVO.setConsignor(agreement.getConsignor());
		}
		if (StringUtil.isNotBlank(agreement.getCharteringAgentNm())) {
			agreementVO.setCharteringAgentNm(agreement.getCharteringAgentNm());
		}
		if (StringUtil.isNotBlank(agreement.getCharterParty())) {
			agreementVO.setCharterParty(agreement.getCharterParty());
		}
		if (StringUtil.isNotBlank(agreement.getVesselName())) {
			agreementVO.setVesselName(agreement.getVesselName());
		}
		if (StringUtil.isNotBlank(agreement.getProductNm())) {
			agreementVO.setProductNm(agreement.getProductNm());
		}
		if (StringUtil.isNotBlank(agreement.getQuantity())) {
			agreementVO.setQuantity(agreement.getQuantity());
		}
		if (StringUtil.isNotBlank(agreement.getRangeOfError())) {
			agreementVO.setRangeOfError(agreement.getRangeOfError());
		}
		if (StringUtil.isNotBlank(agreement.getImo())) {
			agreementVO.setImo(agreement.getImo());
		}
		if (StringUtil.isNotBlank(agreement.getStatus())) {
			agreementVO.setStatus(agreement.getStatus());
		}
		if (StringUtil.isNotBlank(agreement.getUploadQ88())) {
			agreementVO.setUploadQ88(agreement.getUploadQ88());
		}
		if (StringUtil.isNotBlank(agreement.getUploadCp())) {
			agreementVO.setUploadCp(agreement.getUploadCp());
		}
		if (StringUtil.isNotBlank(agreement.getAliveFlag())) {
			agreementVO.setAliveFlag(agreement.getAliveFlag());
		}
		if (StringUtil.isNotBlank(agreement.getProductSourceCode())) {
			agreementVO.setProductSourceCode(agreement.getProductSourceCode());
		}
		return agreementVO;
	}
	
	public AgreementVO() {
		super();
	}
	
	public AgreementVO(Agreement domain) {
		super(domain);
	}

	/**
	 * 将领域类domain转化为VO
	 */
	public AgreementVO domainToVO(Agreement agreement) {
		AgreementVO agreementVO = new AgreementVO();

		if (StringUtil.isNotBlank(agreement.getUuid())) {
			agreementVO.setUuid(agreement.getUuid());
		}
		if (StringUtil.isNotBlank(agreement.getPortOfLoading())) {
			agreementVO.setPortOfLoading(agreement.getPortOfLoading());
		}
		if (StringUtil.isNotBlank(agreement.getPortOfDischarge())) {
			agreementVO.setPortOfDischarge(agreement.getPortOfDischarge());
		}
		if (StringUtil.isNotBlank(agreement.getVesselName())) {
			agreementVO.setVesselName(agreement.getVesselName());
		}
		if (StringUtil.isNotBlank(agreement.getImo())) {
			agreementVO.setImo(agreement.getImo());
		}
		if (StringUtil.isNotBlank(agreement.getProductNm())) {
			agreementVO.setProductNm(agreement.getProductNm());
		}
		if (agreement.getQuantity() != null) {
			agreementVO.setQuantity(agreement.getQuantity());
		}
		if (agreement.getBuyerId()!= null) {
			agreementVO.setBuyerId(agreement.getBuyerId());
		}
		if(agreement.getOrderId()!=null){
			agreementVO.setOrderId(agreement.getOrderId());
		}
		if (agreement.getSellerId()!= null) {
			agreementVO.setSellerId(agreement.getSellerId());
		}
		if (agreement.getDemandsId()!= null) {
			agreementVO.setDemandsId(agreement.getDemandsId());
		}
		Date laycanStart2 = agreement.getLaycanStrat();
		if (laycanStart2 != null) {
			String formatDate = DateUtil.formatDate(laycanStart2);
			agreementVO.setLaycanStrat(formatDate);
		}
		Date laycanEnd2 = agreement.getLaycanEnd();
		if (laycanEnd2 != null) {
			String formatDate = DateUtil.formatDate(laycanEnd2);
			agreementVO.setLaycanEnd(formatDate);
		}
		Date charterPartyDate2 = agreement.getCharterPartyDate();
		if (charterPartyDate2 != null) {
			String formatDate = DateUtil.formatDate(charterPartyDate2);
			agreementVO.setCharterPartyDate(formatDate);
		}
		if (StringUtil.isNotBlank(agreement.getAgreementCd())) {
			agreementVO.setAgreementCd(agreement.getAgreementCd());
		}
		if (StringUtil.isNotBlank(agreement.getConsignor())) {
			agreementVO.setConsignor(agreement.getConsignor());
		}
		if (StringUtil.isNotBlank(agreement.getCharteringAgentNm())) {
			agreementVO.setCharteringAgentNm(agreement.getCharteringAgentNm());
		}
		if (StringUtil.isNotBlank(agreement.getCharterParty())) {
			agreementVO.setCharterParty(agreement.getCharterParty());
		}
		if (StringUtil.isNotBlank(agreement.getVesselName())) {
			agreementVO.setVesselName(agreement.getVesselName());
		}
		if (StringUtil.isNotBlank(agreement.getProductNm())) {
			agreementVO.setProductNm(agreement.getProductNm());
		}
		if (StringUtil.isNotBlank(agreement.getQuantity())) {
			agreementVO.setQuantity(agreement.getQuantity());
		}
		if (StringUtil.isNotBlank(agreement.getRangeOfError())) {
			agreementVO.setRangeOfError(agreement.getRangeOfError());
		}
		if (StringUtil.isNotBlank(agreement.getImo())) {
			agreementVO.setImo(agreement.getImo());
		}
		if (StringUtil.isNotBlank(agreement.getStatus())) {
			agreementVO.setStatus(agreement.getStatus());
		}
		if (StringUtil.isNotBlank(agreement.getUploadQ88())) {
			agreementVO.setUploadQ88(agreement.getUploadQ88());
		}
		if (StringUtil.isNotBlank(agreement.getUploadQ88FileNm())) {
			agreementVO.setUploadQ88FileNm(agreement.getUploadQ88FileNm());
		}
		if (StringUtil.isNotBlank(agreement.getUploadCpFileNm())) {
			agreementVO.setUploadCpFileNm(agreement.getUploadCpFileNm());
		}
		if (StringUtil.isNotBlank(agreement.getUploadCp())) {
			agreementVO.setUploadCp(agreement.getUploadCp());
		}
		if (StringUtil.isNotBlank(agreement.getAliveFlag())) {
			agreementVO.setAliveFlag(agreement.getAliveFlag());
		}
		if (StringUtil.isNotBlank(agreement.getProductSourceCode())) {
			agreementVO.setProductSourceCode(agreement.getProductSourceCode());
		}
		return agreementVO;
	}


}

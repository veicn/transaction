package com.sinochem.crude.trade.shipping.model.vo;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.base.BaseDomainVO;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.shipping.domain.LoadPort;

public class LoadPortVO extends BaseDomainVO<LoadPort> {


	
	/**UUID*/
	private String uuid;  
	
	/**船舶确认单ID*/
	private Long shipConfirmationSheetId;  
	
	/***/
	private String vesselName;  
	
	/***/
	private String portOfLoading;  
	
	/***/
	private String imo;  
	
	/***/
	private String product;  
	
	/***/
	private String quantityTons;  
	
	/***/
	private java.math.BigDecimal quantity;  
	
	/**预记装载时间开始*/
	private String laycanStart;  
	
	/**预记装载时间结束*/
	private String laycanEnd;  
	
	/**船舶准备就绪日期*/
	private String norTendered;  
	
	/**下锚日期*/
	private String anchorAweigh;  
	
	/**引水员登船时间*/
	private String pobDatetimeOne;  
	
	/**停船位*/
	private Integer pobBerth;  
	
	/**第一根缆绳上岸时间*/
	private String firstLineDatetime;  
	
	/**预计靠泊时间*/
	private String firstLineEtb;  
	
	/**停船位*/
	private Long firstLineBerth;  
	
	/**全速*/
	private String allFast;  
	
	/**储罐检测预约时间*/
	private String tanksInspectionDatetime;  
	
	/**储罐检测备注*/
	private String tanksInspectionRemarks;  
	
	/**商检*/
	private String independentInspection;  
	
	/**商检日期*/
	private String independentInspectionDatetime;  
	
	/**商检说明*/
	private String independentInspectionRemarks;  
	
	/**船载数量*/
	private java.math.BigDecimal independentInspectionObq;  
	
	/**接臂*/
	private String shoreArmsConnecting;  
	
	/**开始装载时间*/
	private String commencedLoadingDatetime;  
	
	/**预计装完时间*/
	private String etc;  
	
	/**实际装载完成时间*/
	private String completedLoadingDatetime;  
	
	/**拆臂扫管线日期*/
	private String shoreArmDisconnecting;  
	
	/**商检上船*/
	private String cargoSurvey;  
	
	/**提单公吨*/
	private java.math.BigDecimal blMetricTons;  
	
	/**提单长吨*/
	private java.math.BigDecimal blLongTons;  
	
	/***/
	private java.math.BigDecimal ltr;  
	
	/***/
	private java.math.BigDecimal bbls;  
	
	/**出港时间*/
	private String leaveDatetime;  
	
	/**下个港口ID*/
	private String nextPortId;  
	
	/**下个港口-目的地*/
	private String nextPort;  
	
	/**步骤 到第几步*/
	private Long step;

	private String comUuid;
	
	private Integer tableMaxFlag;
	
	private List<LoadingProgressVO> progreeList;
	
	
	public Integer getTableMaxFlag() {
		return tableMaxFlag;
	}

	public void setTableMaxFlag(Integer tableMaxFlag) {
		this.tableMaxFlag = tableMaxFlag;
	}

	public List<LoadingProgressVO> getProgreeList() {
		return progreeList;
	}

	public void setProgreeList(List<LoadingProgressVO> progreeList) {
		this.progreeList = progreeList;
	}

	public String getComUuid() {
		return comUuid;
	}

	public void setComUuid(String comUuid) {
		this.comUuid = comUuid;
	}

	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Long getShipConfirmationSheetId() {
		return shipConfirmationSheetId;
	}

	public void setShipConfirmationSheetId(Long shipConfirmationSheetId) {
		this.shipConfirmationSheetId = shipConfirmationSheetId;
	}

	public String getVesselName() {
		return vesselName;
	}

	public void setVesselName(String vesselName) {
		this.vesselName = vesselName;
	}

	public String getPortOfLoading() {
		return portOfLoading;
	}

	public void setPortOfLoading(String portOfLoading) {
		this.portOfLoading = portOfLoading;
	}

	public String getImo() {
		return imo;
	}

	public void setImo(String imo) {
		this.imo = imo;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getQuantityTons() {
		return quantityTons;
	}

	public void setQuantityTons(String quantityTons) {
		this.quantityTons = quantityTons;
	}

	

	public java.math.BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(java.math.BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getLaycanStart() {
		return laycanStart;
	}

	public void setLaycanStart(String laycanStart) {
		this.laycanStart = laycanStart;
	}

	public String getLaycanEnd() {
		return laycanEnd;
	}

	public void setLaycanEnd(String laycanEnd) {
		this.laycanEnd = laycanEnd;
	}

	public String getNorTendered() {
		return norTendered;
	}

	public void setNorTendered(String norTendered) {
		this.norTendered = norTendered;
	}

	public String getAnchorAweigh() {
		return anchorAweigh;
	}

	public void setAnchorAweigh(String anchorAweigh) {
		this.anchorAweigh = anchorAweigh;
	}

	public String getFirstLineDatetime() {
		return firstLineDatetime;
	}

	public void setFirstLineDatetime(String firstLineDatetime) {
		this.firstLineDatetime = firstLineDatetime;
	}

	public String getFirstLineEtb() {
		return firstLineEtb;
	}

	public void setFirstLineEtb(String firstLineEtb) {
		this.firstLineEtb = firstLineEtb;
	}

	public String getAllFast() {
		return allFast;
	}

	public void setAllFast(String allFast) {
		this.allFast = allFast;
	}

	public String getTanksInspectionDatetime() {
		return tanksInspectionDatetime;
	}

	public void setTanksInspectionDatetime(String tanksInspectionDatetime) {
		this.tanksInspectionDatetime = tanksInspectionDatetime;
	}

	public String getIndependentInspectionDatetime() {
		return independentInspectionDatetime;
	}

	public void setIndependentInspectionDatetime(
			String independentInspectionDatetime) {
		this.independentInspectionDatetime = independentInspectionDatetime;
	}

	public String getShoreArmsConnecting() {
		return shoreArmsConnecting;
	}

	public void setShoreArmsConnecting(String shoreArmsConnecting) {
		this.shoreArmsConnecting = shoreArmsConnecting;
	}

	public String getCommencedLoadingDatetime() {
		return commencedLoadingDatetime;
	}

	public void setCommencedLoadingDatetime(String commencedLoadingDatetime) {
		this.commencedLoadingDatetime = commencedLoadingDatetime;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	public String getCompletedLoadingDatetime() {
		return completedLoadingDatetime;
	}

	public void setCompletedLoadingDatetime(String completedLoadingDatetime) {
		this.completedLoadingDatetime = completedLoadingDatetime;
	}

	public String getShoreArmDisconnecting() {
		return shoreArmDisconnecting;
	}

	public void setShoreArmDisconnecting(String shoreArmDisconnecting) {
		this.shoreArmDisconnecting = shoreArmDisconnecting;
	}

	public String getCargoSurvey() {
		return cargoSurvey;
	}

	public void setCargoSurvey(String cargoSurvey) {
		this.cargoSurvey = cargoSurvey;
	}

	public String getLeaveDatetime() {
		return leaveDatetime;
	}

	public void setLeaveDatetime(String leaveDatetime) {
		this.leaveDatetime = leaveDatetime;
	}


	public String getPobDatetimeOne() {
		return pobDatetimeOne;
	}

	public void setPobDatetimeOne(String pobDatetimeOne) {
		this.pobDatetimeOne = pobDatetimeOne;
	}

	public Integer getPobBerth() {
		return pobBerth;
	}

	public void setPobBerth(Integer pobBerth) {
		this.pobBerth = pobBerth;
	}

	public Long getFirstLineBerth() {
		return firstLineBerth;
	}

	public void setFirstLineBerth(Long firstLineBerth) {
		this.firstLineBerth = firstLineBerth;
	}


	public String getTanksInspectionRemarks() {
		return tanksInspectionRemarks;
	}

	public void setTanksInspectionRemarks(String tanksInspectionRemarks) {
		this.tanksInspectionRemarks = tanksInspectionRemarks;
	}

	public String getIndependentInspection() {
		return independentInspection;
	}

	public void setIndependentInspection(String independentInspection) {
		this.independentInspection = independentInspection;
	}


	public String getIndependentInspectionRemarks() {
		return independentInspectionRemarks;
	}

	public void setIndependentInspectionRemarks(String independentInspectionRemarks) {
		this.independentInspectionRemarks = independentInspectionRemarks;
	}

	public java.math.BigDecimal getIndependentInspectionObq() {
		return independentInspectionObq;
	}

	public void setIndependentInspectionObq(
			java.math.BigDecimal independentInspectionObq) {
		this.independentInspectionObq = independentInspectionObq;
	}

	public java.math.BigDecimal getBlMetricTons() {
		return blMetricTons;
	}

	public void setBlMetricTons(java.math.BigDecimal blMetricTons) {
		this.blMetricTons = blMetricTons;
	}

	public java.math.BigDecimal getBlLongTons() {
		return blLongTons;
	}

	public void setBlLongTons(java.math.BigDecimal blLongTons) {
		this.blLongTons = blLongTons;
	}

	public java.math.BigDecimal getLtr() {
		return ltr;
	}

	public void setLtr(java.math.BigDecimal ltr) {
		this.ltr = ltr;
	}

	public java.math.BigDecimal getBbls() {
		return bbls;
	}

	public void setBbls(java.math.BigDecimal bbls) {
		this.bbls = bbls;
	}


	public String getNextPortId() {
		return nextPortId;
	}

	public void setNextPortId(String nextPortId) {
		this.nextPortId = nextPortId;
	}

	public String getNextPort() {
		return nextPort;
	}

	public void setNextPort(String nextPort) {
		this.nextPort = nextPort;
	}

	public Long getStep() {
		return step;
	}

	public void setStep(Long step) {
		this.step = step;
	}

	@Override
	protected void convertToVO(LoadPort domain) {
		
	}

	@Override
	protected LoadPort convertToDomain() {
		LoadPort loadPort = new LoadPort();

		if (StringUtil.isNotBlank(this.allFast)) {
			loadPort.setAllFast(DateUtil.getDateTime(this.allFast));
		}
		if (StringUtil.isNotBlank(this.anchorAweigh)) {
			loadPort.setAnchorAweigh(DateUtil.getDateTime(this.anchorAweigh));
		}
		if (this.bbls != null) {
			loadPort.setBbls(this.bbls);
		}
		if (this.blLongTons != null ) {
			loadPort.setBlLongTons(this.blLongTons);
		}
		if (this.blMetricTons !=null) {
			loadPort.setBlMetricTons(this.blMetricTons);
		}
		if (StringUtil.isNotBlank(this.cargoSurvey)) {
			loadPort.setCargoSurvey(DateUtil.getDateTime(this.cargoSurvey));
			//loadPort.setCargoSurvey(sdf.parse(this.cargoSurvey));
		}
		if (StringUtil.isNotBlank(this.commencedLoadingDatetime)) {
			loadPort.setCommencedLoadingDatetime(DateUtil.getDateTime(this.commencedLoadingDatetime));
			//loadPort.setCommencedLoadingDatetime(sdf.parse(this.commencedLoadingDatetime));
		}
		if (StringUtil.isNotBlank(this.completedLoadingDatetime)) {
			loadPort.setCompletedLoadingDatetime(DateUtil.getDateTime(this.completedLoadingDatetime));
			//loadPort.setCompletedLoadingDatetime(sdf.parse(this.completedLoadingDatetime));
		}
		if (StringUtil.isNotBlank(this.etc)) {
			loadPort.setEtc(DateUtil.getDateTime(this.etc));
		}
		if (this.firstLineBerth != null) {
			loadPort.setFirstLineBerth(this.firstLineBerth);
		}
		if (StringUtil.isNotBlank(this.firstLineDatetime)) {
			loadPort.setFirstLineDatetime(DateUtil.getDateTime(this.firstLineDatetime));
		}
		if (StringUtil.isNotBlank(this.firstLineEtb)) {
			loadPort.setFirstLineEtb(DateUtil.getDateTime(this.firstLineEtb));
		}
		if (StringUtil.isNotBlank(this.imo)) {
			loadPort.setImo(this.imo);
		}
		if (StringUtil.isNotBlank(this.independentInspection)) {
			loadPort.setIndependentInspection(this.independentInspection);
		}
		if (StringUtil.isNotBlank(this.independentInspectionDatetime)) {
			loadPort.setIndependentInspectionDatetime(DateUtil.getDateTime(this.independentInspectionDatetime));
		}
		if (this.independentInspectionObq != null) {
			loadPort.setIndependentInspectionObq(this.independentInspectionObq);
		}
		if (StringUtil.isNotBlank(this.independentInspectionRemarks)) {
			loadPort.setIndependentInspectionRemarks(this.independentInspectionRemarks);
		}
		if (StringUtil.isNotBlank(this.laycanEnd)) {
			//loadPort.setLaycanEnd(DateUtil.getDateTime(this.laycanEnd));
			loadPort.setLaycanEnd(DateUtil.getDate(this.laycanEnd));
		}
		if (StringUtil.isNotBlank(this.laycanStart)) {
			//loadPort.setLaycanStart(DateUtil.getDate(this.laycanStart));
			loadPort.setLaycanStart(DateUtil.getDate(this.laycanStart));
		}
		if (StringUtil.isNotBlank(this.leaveDatetime)) {
			loadPort.setLeaveDatetime(DateUtil.getDateTime(this.leaveDatetime));
		}
		if (this.ltr != null ) {
			loadPort.setLtr(this.ltr);
		}
		if (StringUtil.isNotBlank(this.nextPort)) {
			loadPort.setNextPort(this.nextPort);
		}
		if (StringUtil.isNotBlank(this.nextPortId)) {
			loadPort.setNextPortId(this.nextPortId);
		}
		if (StringUtil.isNotBlank(this.norTendered)) {
			loadPort.setNorTendered(DateUtil.getDateTime(this.norTendered));
		}
		if (this.pobBerth !=null) {
			BigDecimal bigDecimal = new BigDecimal(this.pobBerth);
			loadPort.setPobBerth(bigDecimal);
		}
		if (this.pobDatetimeOne != null) {
			
			loadPort.setPobDatetimeOne(DateUtil.getDateTime(this.pobDatetimeOne));
		}
		if (StringUtil.isNotBlank(this.portOfLoading)) {
			loadPort.setPortOfLoading(this.portOfLoading);
		}
		if (StringUtil.isNotBlank(this.product)) {
			loadPort.setProduct(this.product);
		}
		if (this.quantity != null) {
			loadPort.setQuantity(this.quantity);
		}
		if (StringUtil.isNotBlank(this.quantityTons)) {
			loadPort.setQuantityTons(this.quantityTons);
		}
		if (this.shipConfirmationSheetId != null) {
			loadPort.setShipConfirmationSheetId(this.shipConfirmationSheetId);
		}
		if (StringUtil.isNotBlank(this.shoreArmDisconnecting)) {
			loadPort.setShoreArmDisconnecting(DateUtil.getDateTime(this.shoreArmDisconnecting));
		}
		if (StringUtil.isNotBlank(this.shoreArmsConnecting)) {
			loadPort.setShoreArmsConnecting(DateUtil.getDateTime(this.shoreArmsConnecting));
		}
		if (this.step != null) {
			loadPort.setStep(this.step);
		}
		if (StringUtil.isNotBlank(this.tanksInspectionDatetime)) {
			loadPort.setTanksInspectionDatetime(DateUtil.getDateTime(this.tanksInspectionDatetime));
		}
		if (StringUtil.isNotBlank(this.tanksInspectionRemarks)) {
			loadPort.setTanksInspectionRemarks(this.tanksInspectionRemarks);
		}
		if (StringUtil.isNotBlank(this.vesselName)) {
			loadPort.setVesselName(this.vesselName);
		}
		if (StringUtil.isNotBlank(this.uuid)) {
			loadPort.setUuid(this.uuid);
		}
		if (StringUtil.isNotBlank(this.comUuid)) {
			loadPort.setComUuid(this.comUuid);
		}
			
		
		return loadPort;
	}  
	
	public LoadPortVO toObjectVo(LoadPort loadport){
		LoadPortVO loadPortVO = new LoadPortVO();
		DecimalFormat df = new DecimalFormat("#.###");
		if (StringUtil.isNotBlank(loadport.getUuid())) {
			loadPortVO.setUuid(loadport.getUuid());
		}
		if (StringUtil.isNotBlank(loadport.getPortOfLoading())) {
			loadPortVO.setPortOfLoading(loadport.getPortOfLoading());
		}
		if (StringUtil.isNotBlank(loadport.getVesselName())) {
			loadPortVO.setVesselName(loadport.getVesselName());
		}
		if (StringUtil.isNotBlank(loadport.getImo())) {
			loadPortVO.setImo(loadport.getImo());
		}
		if (StringUtil.isNotBlank(loadport.getProduct())) {
			loadPortVO.setProduct(loadport.getProduct());
		}
		if (loadport.getQuantity() != null) {
			loadPortVO.setQuantity(loadport.getQuantity());
		}
		if (StringUtil.isNotBlank(loadport.getQuantityTons())) {
			loadPortVO.setQuantityTons(loadport.getQuantityTons());
		}
		Date laycanStart2 = loadport.getLaycanStart();
		if (laycanStart2 != null) {
			String formatDate = DateUtil.formatDate(laycanStart2);
			loadPortVO.setLaycanStart(formatDate);
		}
		Date laycanEnd2 = loadport.getLaycanEnd();
		if (laycanEnd2 != null) {
			String formatDate = DateUtil.formatDate(laycanEnd2);
			loadPortVO.setLaycanEnd(formatDate);
		}
		Date norTendered2 = loadport.getNorTendered();
		if (norTendered2 != null) {
			String formatDate = DateUtil.formatDateTime(norTendered2);
			loadPortVO.setNorTendered(formatDate);
		}
		Date anchorAweigh2 = loadport.getAnchorAweigh();
		if (anchorAweigh2 != null) {
			String formatDate = DateUtil.formatDateTime(anchorAweigh2);
			loadPortVO.setAnchorAweigh(formatDate);
		}
		Date pobDatetimeOne2 = loadport.getPobDatetimeOne();
		if (pobDatetimeOne2 != null) {
			String formatDate = DateUtil.formatDateTime(pobDatetimeOne2);
			loadPortVO.setPobDatetimeOne(formatDate);
		}
		if (loadport.getPobBerth() != null) {
			int intValue = loadport.getPobBerth().intValue();
			loadPortVO.setPobBerth(intValue);
		}
		
		Date firstLineDatetime2 = loadport.getFirstLineDatetime();
		if (firstLineDatetime2 != null) {
			String formatDate = DateUtil.formatDateTime(firstLineDatetime2);
			loadPortVO.setFirstLineDatetime(formatDate);
		}
		Date firstLineEtb2 = loadport.getFirstLineEtb();
		if (firstLineEtb2 != null) {
			String formatDate = DateUtil.formatDateTime(firstLineEtb2);
			loadPortVO.setFirstLineEtb(formatDate);
		}
		if (loadport.getFirstLineBerth() != null) {
			loadPortVO.setFirstLineBerth(loadport.getFirstLineBerth());
		}
		Date allFast2 = loadport.getAllFast();
		if (allFast2 != null) {
			String formatDate = DateUtil.formatDateTime(allFast2);
			loadPortVO.setAllFast(formatDate);
		}
		
		Date tanksInspectionDatetime2 = loadport.getTanksInspectionDatetime();
		if (tanksInspectionDatetime2 != null) {
			String formatDate = DateUtil.formatDateTime(tanksInspectionDatetime2);
			loadPortVO.setTanksInspectionDatetime(formatDate);
		}
		if (StringUtil.isNotBlank(loadport.getTanksInspectionRemarks())) {
			loadPortVO.setTanksInspectionRemarks(loadport.getTanksInspectionRemarks());
		}
		
		
		if (StringUtil.isNotBlank(loadport.getIndependentInspection())) {
			loadPortVO.setIndependentInspection(loadport.getIndependentInspection());
		}
		Date independentInspectionDatetime2 = loadport.getIndependentInspectionDatetime();
		if (independentInspectionDatetime2 != null) {
			String formatDate = DateUtil.formatDateTime(independentInspectionDatetime2);
			loadPortVO.setIndependentInspectionDatetime(formatDate);
		}
		if (loadport.getIndependentInspectionObq() != null) {
			loadPortVO.setIndependentInspectionObq(loadport.getIndependentInspectionObq().setScale(2));
		}
		if (StringUtil.isNotBlank(loadport.getIndependentInspectionRemarks())) {
			loadPortVO.setIndependentInspectionRemarks(loadport.getIndependentInspectionRemarks());
		}
		Date shoreArmsConnecting2 = loadport.getShoreArmsConnecting();
		if (shoreArmsConnecting2 != null) {
			String formatDate = DateUtil.formatDateTime(shoreArmsConnecting2);
			loadPortVO.setShoreArmsConnecting(formatDate);
		}
		Date commencedLoadingDatetime2 = loadport.getCommencedLoadingDatetime();
		if (commencedLoadingDatetime2 != null) {
			String formatDate = DateUtil.formatDateTime(commencedLoadingDatetime2);
			loadPortVO.setCommencedLoadingDatetime(formatDate);
		}
		Date etc2 = loadport.getEtc();
		if (etc2 != null) {
			String formatDate = DateUtil.formatDateTime(etc2);
			loadPortVO.setEtc(formatDate);
		}
		
		Date completedLoadingDatetime2 = loadport.getCompletedLoadingDatetime();
		if (completedLoadingDatetime2 != null) {
			String formatDate = DateUtil.formatDateTime(completedLoadingDatetime2);
			loadPortVO.setCompletedLoadingDatetime(formatDate);
		}
		
		Date shoreArmDisconnecting2 = loadport.getShoreArmDisconnecting();
		if (shoreArmDisconnecting2 != null) {
			String formatDate = DateUtil.formatDateTime(shoreArmDisconnecting2);
			loadPortVO.setShoreArmDisconnecting(formatDate);
		}
		Date cargoSurvey2 = loadport.getCargoSurvey();
		if (cargoSurvey2 != null) {
			String formatDate = DateUtil.formatDateTime(cargoSurvey2);
			loadPortVO.setCargoSurvey(formatDate);
		}
		BigDecimal blMetricTons2 = loadport.getBlMetricTons();
		if (blMetricTons2 != null) {
			loadPortVO.setBlMetricTons(new BigDecimal(df.format(blMetricTons2)));
		}
		BigDecimal blLongTons2 = loadport.getBlLongTons();
		if (blLongTons2 != null) {
			loadPortVO.setBlLongTons(new BigDecimal(df.format(blLongTons2)));
		}
		BigDecimal ltr2 = loadport.getLtr();
		if (ltr2 != null) {
			loadPortVO.setLtr(new BigDecimal(df.format(ltr2)));
		}
		BigDecimal bbls2 = loadport.getBbls();
		if (bbls2 != null) {
			loadPortVO.setBbls(new BigDecimal(df.format(bbls2)));
		}
		Date leaveDatetime2 = loadport.getLeaveDatetime();
		if (leaveDatetime2!=null) {
			String formatDate = DateUtil.formatDateTime(leaveDatetime2);
			loadPortVO.setLeaveDatetime(formatDate);
		}
		
		if (StringUtil.isNotBlank(loadport.getNextPort())) {
			loadPortVO.setNextPort(loadport.getNextPort());
		}
		if (StringUtil.isNotBlank(loadport.getNextPortId())) {
			loadPortVO.setNextPortId(loadport.getNextPortId());
		}
		
		
		return loadPortVO;
	}
	
	
}
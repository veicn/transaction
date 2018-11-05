package com.sinochem.crude.trade.shipping.model.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.base.BaseDomainVO;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.shipping.domain.LoadingProgress;

public class LoadingProgressVO extends BaseDomainVO<LoadingProgress> {

	/**UUID*/
	private String uuid;  
	
	/**船舶确认单ID*/
	private Long shipConfirmationSheetId;  
	
	/**船舶装港表ID*/
	private String shipLoadPortId;  
	
	/**船舶装港表*/
	private String shipLoadPort;  
	
	/**日期*/
	private String datetime;  
	
	/**已装吨数*/
	private java.math.BigDecimal cargoLoaded;  
	
	/**未装吨数*/
	private java.math.BigDecimal theCargoToBeLoaded;  
	
	/**装速*/
	private java.math.BigDecimal loadingSpeed;  
	
	/**预计完货时间*/
	private String etc;  

	private String confirmUuid;

	private String ext1;
	
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

	public String getShipLoadPortId() {
		return shipLoadPortId;
	}

	public void setShipLoadPortId(String shipLoadPortId) {
		this.shipLoadPortId = shipLoadPortId;
	}

	public String getShipLoadPort() {
		return shipLoadPort;
	}

	public void setShipLoadPort(String shipLoadPort) {
		this.shipLoadPort = shipLoadPort;
	}


	public String getDatetime() {
		return datetime;
	}

	public java.math.BigDecimal getCargoLoaded() {
		return cargoLoaded;
	}

	public void setCargoLoaded(java.math.BigDecimal cargoLoaded) {
		this.cargoLoaded = cargoLoaded;
	}

	public java.math.BigDecimal getTheCargoToBeLoaded() {
		return theCargoToBeLoaded;
	}

	public void setTheCargoToBeLoaded(java.math.BigDecimal theCargoToBeLoaded) {
		this.theCargoToBeLoaded = theCargoToBeLoaded;
	}

	public java.math.BigDecimal getLoadingSpeed() {
		return loadingSpeed;
	}

	public void setLoadingSpeed(java.math.BigDecimal loadingSpeed) {
		this.loadingSpeed = loadingSpeed;
	}

	

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	@Override
	protected void convertToVO(LoadingProgress domain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected LoadingProgress convertToDomain() {
		LoadingProgress loadingProgress = new LoadingProgress();
		
		if (StringUtil.isNotBlank(this.uuid)) {
			loadingProgress.setUuid(this.uuid);
		}
		if (this.shipConfirmationSheetId != null) {
			loadingProgress.setShipConfirmationSheetId(this.shipConfirmationSheetId);
		}
		if (StringUtil.isNotBlank(this.shipLoadPort)) {
			loadingProgress.setShipLoadPort(this.shipLoadPort);
		}
		if (StringUtil.isNotBlank(this.shipLoadPortId)) {
			loadingProgress.setShipLoadPortId(this.shipLoadPortId);
		}
		if (StringUtil.isNotBlank(this.datetime)) {
			Date date2 = DateUtil.getDateTime(this.datetime);
			loadingProgress.setDatetime(date2);
		}
		if (this.cargoLoaded!=null) {
			loadingProgress.setCargoLoaded(this.cargoLoaded);
		}
		if (this.theCargoToBeLoaded != null) {
			loadingProgress.setTheCargoToBeLoaded(this.theCargoToBeLoaded);
		}
		if (this.loadingSpeed !=null) {
			loadingProgress.setLoadingSpeed(this.loadingSpeed);
		}
		if (StringUtil.isNotBlank(this.etc)) {
			Date date = DateUtil.getDateTime(this.etc);
			loadingProgress.setEtc(date);
		}
		
		return loadingProgress;
	}
	
	/**
	 * domain 转vo  对象
	 * @param loadingprogress
	 * @return
	 */
	public LoadingProgressVO toObjectVo(LoadingProgress loadingprogress){
		LoadingProgressVO loadingProgressVO = new LoadingProgressVO();
		if (StringUtil.isNotBlank(loadingprogress.getUuid())) {
			loadingProgressVO.setUuid(loadingprogress.getUuid());
		}
		if (loadingprogress.getShipConfirmationSheetId() != null) {
			loadingProgressVO.setShipConfirmationSheetId(loadingprogress.getShipConfirmationSheetId());
		}
		if (StringUtil.isNotBlank(loadingprogress.getShipLoadPort())) {
			loadingProgressVO.setShipLoadPort(loadingprogress.getShipLoadPort());
		}
		if (StringUtil.isNotBlank(loadingprogress.getShipLoadPortId())) {
			loadingProgressVO.setShipLoadPortId(loadingprogress.getShipLoadPortId());
		}
		Date datetime2 = loadingprogress.getDatetime();
		if (datetime2 != null) {
			String formatDate = DateUtil.formatDateTime(datetime2);
			loadingProgressVO.setDatetime(formatDate);
		}
		if (loadingprogress.getCargoLoaded() != null) {
			loadingProgressVO.setCargoLoaded(loadingprogress.getCargoLoaded().setScale(2));
		}
		if (loadingprogress.getTheCargoToBeLoaded() !=null) {
			loadingProgressVO.setTheCargoToBeLoaded(loadingprogress.getTheCargoToBeLoaded().setScale(2));
		}
		if (loadingprogress.getLoadingSpeed() != null) {
			loadingProgressVO.setLoadingSpeed(loadingprogress.getLoadingSpeed().setScale(2));
		}
		Date etc2 = loadingprogress.getEtc();
		if (etc2 != null) {
			String formatDate = DateUtil.formatDateTime(etc2);
			loadingProgressVO.setEtc(formatDate);
		}
		
		return loadingProgressVO;
	}
	/**
	 * 领域list 转volist
	 * @param loadingList
	 * @return
	 */
	public List<LoadingProgressVO> toListVo(List<LoadingProgress> loadingList){
		List<LoadingProgressVO> listVo = new ArrayList<LoadingProgressVO>();
		for (LoadingProgress loadingprogress : loadingList) {
			LoadingProgressVO loadingProgressVO = new LoadingProgressVO();
			if (StringUtil.isNotBlank(loadingprogress.getUuid())) {
				loadingProgressVO.setUuid(loadingprogress.getUuid());
			}
			if (loadingprogress.getShipConfirmationSheetId() != null) {
				loadingProgressVO.setShipConfirmationSheetId(loadingprogress.getShipConfirmationSheetId());
			}
			if (StringUtil.isNotBlank(loadingprogress.getShipLoadPort())) {
				loadingProgressVO.setShipLoadPort(loadingprogress.getShipLoadPort());
			}
			if (StringUtil.isNotBlank(loadingprogress.getShipLoadPortId())) {
				loadingProgressVO.setShipLoadPortId(loadingprogress.getShipLoadPortId());
			}
			Date datetime2 = loadingprogress.getDatetime();
			if (datetime2 != null) {
				String formatDate = DateUtil.formatDateTime(datetime2);
				loadingProgressVO.setDatetime(formatDate);
			}
			if (loadingprogress.getCargoLoaded() != null) {
				loadingProgressVO.setCargoLoaded(loadingprogress.getCargoLoaded().setScale(2));
			}
			if (loadingprogress.getTheCargoToBeLoaded() !=null) {
				loadingProgressVO.setTheCargoToBeLoaded(loadingprogress.getTheCargoToBeLoaded().setScale(2));
			}
			if (loadingprogress.getLoadingSpeed() != null) {
				loadingProgressVO.setLoadingSpeed(loadingprogress.getLoadingSpeed().setScale(2));
			}
			Date etc2 = loadingprogress.getEtc();
			if (etc2 != null) {
				String formatDate = DateUtil.formatDateTime(etc2);
				loadingProgressVO.setEtc(formatDate);
			}
			
			listVo.add(loadingProgressVO);
		}
		
		return listVo;
	}

	public String getConfirmUuid() {
		return confirmUuid;
	}

	public void setConfirmUuid(String confirmUuid) {
		this.confirmUuid = confirmUuid;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
}
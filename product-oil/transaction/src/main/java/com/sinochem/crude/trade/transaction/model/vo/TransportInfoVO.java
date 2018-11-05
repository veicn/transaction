package com.sinochem.crude.trade.transaction.model.vo;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.base.BasePersistVO;
import com.sinochem.crude.trade.common.model.vo.DictionaryVO;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.transaction.domain.po.TransportInfo;
import com.sinochem.crude.trade.transaction.service.DictionaryService;
import org.springframework.web.context.ContextLoader;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 运输信息的VO
 * @author Yichen Zhao
 * datE: 20180302
 */
public class TransportInfoVO extends BasePersistVO<TransportInfo> {

    /**
     * 运输方式（TransportModeEnum）
     */
    private DictionaryVO transportModeVO;

    /**
     * 装港代码
     */
    private String loadingPort;

    /**
     * 允许装货的时间（多长时间内装完）
     */
    private String laytimeAsString;

    /**
     * 装港开始时间
     */
    private String laycanStartDateAsString;

    /**
     * 装港结束时间
     */
    private String laycanEndDateAsString;

    /**
     * 卸港代码
     */
    private String dischargePort;

    /**
     * 装港国家
     */
    private String loadingCountry;

    /**
     * 装港国家
     */
    private String dischargeCountry;

    /**
     * 滞期费率
     */
    public DictionaryVO demurrageRateVO;

    /**
     *滞期费率num
     */
    public String demurrageRateNumAsString;

    public TransportInfoVO(){
    	
    }
    public TransportInfoVO(TransportInfo transportInfo) {
        super(transportInfo);
    }

    @Override
    protected void convertToVO(TransportInfo domain) {
        DictionaryService dictionaryService =
                ContextLoader.getCurrentWebApplicationContext().getBean(DictionaryService.class);

        String transportModeCode = domain.getTransportModeCode();
        if (!StringUtil.isEmpty(transportModeCode)) {
            DictionaryVO transportModeVO = dictionaryService.getTransportMode(transportModeCode);
            this.transportModeVO = transportModeVO;
        }

        String demurrageRateCode = domain.getDemurrageRateCode();
        if (!StringUtil.isEmpty(demurrageRateCode)) {
            DictionaryVO demurrageRateVO = dictionaryService.getDemurrageRate(demurrageRateCode);
            this.demurrageRateVO = demurrageRateVO;
        }

        BigDecimal demurrageRateNum = domain.getDemurrageRateNum();
        if (demurrageRateNum != null) {
            //this.loadingPortVO = dictionaryService.getShipPort(loadingPortCode);
            this.demurrageRateNumAsString = demurrageRateNum.toString();
        }

        String loadingPort = domain.getLoadingPort();
        if (!StringUtil.isEmpty(loadingPort)) {
            //this.loadingPortVO = dictionaryService.getShipPort(loadingPortCode);
            this.loadingPort = loadingPort;
        }

        BigDecimal laytime = domain.getLaytime();
        if (laytime != null) {
            this.laytimeAsString = laytime.toString();
        }

        Date laycanStartDate = domain.getLaycanStartDate();
        if (laycanStartDate != null) {
            this.laycanStartDateAsString = DateUtil.formatDate(laycanStartDate);
        }

        Date laycanEndDate = domain.getLaycanEndDate();
        if (laycanEndDate != null) {
            this.laycanEndDateAsString = DateUtil.formatDate(laycanEndDate);
        }

        String dischargePort = domain.getDischargePort();
        if (!StringUtil.isEmpty(dischargePort)) {
            //this.dischargePortVO = dictionaryService.getShipPort(dischargePortCode);
            this.dischargePort = dischargePort;
        }

        String dischargeCountry = domain.getDischargeCountry();
        if (!StringUtil.isEmpty(dischargeCountry)) {
            //this.dischargePortVO = dictionaryService.getShipPort(dischargePortCode);
            this.dischargeCountry = dischargeCountry;
        }

        String loadingCountry = domain.getLoadingCountry();
        if (!StringUtil.isEmpty(loadingCountry)) {
            //this.dischargePortVO = dictionaryService.getShipPort(dischargePortCode);
            this.loadingCountry = loadingCountry;
        }
    }

    @Override
    protected TransportInfo convertToDomain() {
        TransportInfo transportInfo = new TransportInfo();

        if (this.transportModeVO != null) {
            transportInfo.setTransportModeCode(this.transportModeVO.getCode());
        }

        if (this.demurrageRateVO != null) {
            transportInfo.setDemurrageRateCode(this.demurrageRateVO.getCode());
        }

        if (!StringUtil.isEmpty(this.demurrageRateNumAsString)) {
            transportInfo.setDemurrageRateNum(new BigDecimal(this.demurrageRateNumAsString));
        }

        if (!StringUtil.isEmpty(this.loadingPort)) {
            transportInfo.setLoadingPort(this.loadingPort);
        }

        if (!StringUtil.isEmpty(this.laytimeAsString)) {
            transportInfo.setLaytime(new BigDecimal(this.laytimeAsString));
        }

        if (!StringUtil.isEmpty(this.laycanStartDateAsString)) {
            transportInfo.setLaycanStartDate(DateUtil.getDate(this.laycanStartDateAsString));
        }

        if (!StringUtil.isEmpty(this.laycanEndDateAsString)) {
            transportInfo.setLaycanEndDate(DateUtil.getDate(this.laycanEndDateAsString));
        }

        if (!StringUtil.isEmpty(this.dischargePort)) {
            transportInfo.setDischargePort(this.dischargePort);
        }

        if (!StringUtil.isEmpty(this.dischargeCountry)) {
            transportInfo.setDischargeCountry(this.dischargeCountry);
        }

        if (!StringUtil.isEmpty(this.loadingCountry)) {
            transportInfo.setLoadingCountry(this.loadingCountry);
        }

        return transportInfo;
    }

    /** getters and setters */
    public DictionaryVO getTransportModeVO() {
        return transportModeVO;
    }

    public void setTransportModeVO(DictionaryVO transportModeVO) {
        this.transportModeVO = transportModeVO;
    }

    public String getLaytimeAsString() {
        return laytimeAsString;
    }

    public void setLaytimeAsString(String laytimeAsString) {
        this.laytimeAsString = laytimeAsString;
    }

    public String getLaycanStartDateAsString() {
        return laycanStartDateAsString;
    }

    public void setLaycanStartDateAsString(String laycanStartDateAsString) {
        this.laycanStartDateAsString = laycanStartDateAsString;
    }

    public String getLaycanEndDateAsString() {
        return laycanEndDateAsString;
    }

    public void setLaycanEndDateAsString(String laycanEndDateAsString) {
        this.laycanEndDateAsString = laycanEndDateAsString;
    }

    public String getLoadingPort() {
        return loadingPort;
    }

    public void setLoadingPort(String loadingPort) {
        this.loadingPort = loadingPort;
    }

    public String getDischargePort() {
        return dischargePort;
    }

    public void setDischargePort(String dischargePort) {
        this.dischargePort = dischargePort;
    }

    public String getLoadingCountry() {
        return loadingCountry;
    }

    public void setLoadingCountry(String loadingCountry) {
        this.loadingCountry = loadingCountry;
    }

    public String getDischargeCountry() {
        return dischargeCountry;
    }

    public void setDischargeCountry(String dischargeCountry) {
        this.dischargeCountry = dischargeCountry;
    }

    public DictionaryVO getDemurrageRateVO() {
        return demurrageRateVO;
    }

    public void setDemurrageRateVO(DictionaryVO demurrageRateVO) {
        this.demurrageRateVO = demurrageRateVO;
    }

    public String getDemurrageRateNumAsString() {
        return demurrageRateNumAsString;
    }

    public void setDemurrageRateNumAsString(String demurrageRateNumAsString) {
        this.demurrageRateNumAsString = demurrageRateNumAsString;
    }

}

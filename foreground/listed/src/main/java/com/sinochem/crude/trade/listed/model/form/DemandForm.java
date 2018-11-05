package com.sinochem.crude.trade.listed.model.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.listed.domain.DemandDetail;
import com.sinochem.crude.trade.listed.model.vo.*;

/**
 * Created by xyuser on 2017/12/24.
 */
public class DemandForm {

    private String pageType;

    @Valid
    private DemandVO demand = new DemandVO();

    @Valid
    private DemandShipVO demandShip = new DemandShipVO();

    private List<DemandShipBerthVO> demandShipBerthList = new ArrayList<DemandShipBerthVO>();

    @Valid
    private DemandRelevanterBuyerVO buyerRelevanter = new DemandRelevanterBuyerVO();

    private DemandRelevanterAgentVO agentRelevanter = new DemandRelevanterAgentVO();

    @Valid
    private IdForm idForm = new IdForm();

    private List<CrudeOilForm> crudeOilFormList = new ArrayList<CrudeOilForm>();

    @Valid
    private List<DemandDetailVO> demandDetailList = new ArrayList<DemandDetailVO>();

    private List<CrudeOilVO> crudeOils = new ArrayList<>();

    private List<DemandSpecifyEnterpriseVO> specifyEnterpriseList = new ArrayList<>();

    private String epMemberIds;
    private String enterpriseNames;

    /** getters and setters */
    public DemandVO getDemand() {
        return demand;
    }

    public void setDemand(DemandVO demand) {
        this.demand = demand;
    }

    public DemandShipVO getDemandShip() {
        return demandShip;
    }

    public void setDemandShip(DemandShipVO demandShip) {
        this.demandShip = demandShip;
    }

    public List<DemandShipBerthVO> getDemandShipBerthList() {
        return demandShipBerthList;
    }

    public void setDemandShipBerthList(List<DemandShipBerthVO> demandShipBerthList) {
        this.demandShipBerthList = demandShipBerthList;
    }

    public DemandRelevanterBuyerVO getBuyerRelevanter() {
        return buyerRelevanter;
    }

    public void setBuyerRelevanter(DemandRelevanterBuyerVO buyerRelevanter) {
        this.buyerRelevanter = buyerRelevanter;
    }

    public DemandRelevanterAgentVO getAgentRelevanter() {
        return agentRelevanter;
    }

    public void setAgentRelevanter(DemandRelevanterAgentVO agentRelevanter) {
        this.agentRelevanter = agentRelevanter;
    }

    public IdForm getIdForm() {
        return idForm;
    }

    public void setIdForm(IdForm idForm) {
        this.idForm = idForm;
    }

    public List<CrudeOilForm> getCrudeOilFormList() {
        return crudeOilFormList;
    }

    public void setCrudeOilFormList(List<CrudeOilForm> crudeOilFormList) {
        this.crudeOilFormList = crudeOilFormList;
    }

    public List<DemandDetailVO> getDemandDetailList() {
        return demandDetailList;
    }

    public void setDemandDetailList(List<DemandDetailVO> demandDetailList) {
        this.demandDetailList = demandDetailList;
    }

    public List<CrudeOilVO> getCrudeOils() {
        return crudeOils;
    }

    public void setCrudeOils(List<CrudeOilVO> crudeOils) {
        this.crudeOils = crudeOils;
    }

    public String getEpMemberIds() {
        return epMemberIds;
    }

    public void setEpMemberIds(String epMemberIds) {
        this.epMemberIds = epMemberIds;
    }

    public String getEnterpriseNames() {
        return enterpriseNames;
    }

    public void setEnterpriseNames(String enterpriseNames) {
        this.enterpriseNames = enterpriseNames;
    }

    public List<DemandSpecifyEnterpriseVO> getSpecifyEnterpriseList() {
        return specifyEnterpriseList;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public void setSpecifyEnterpriseList() {
        List<DemandSpecifyEnterpriseVO> specifyEnterpriseList = new ArrayList<>();
        if (!StringUtil.isEmpty(this.epMemberIds)) {
            String[] epMemberIdArr = this.epMemberIds.split(",");
            String[] enterpriseNameArr = this.enterpriseNames.split(",");
            if (epMemberIdArr.length == enterpriseNameArr.length) {
                DemandSpecifyEnterpriseVO vo = null;
                for (int i=0; i<epMemberIdArr.length; i++) {
                    if (StringUtil.isNotEmpty(epMemberIdArr[i])) {
                        vo = new DemandSpecifyEnterpriseVO();
                        vo.setEpMemberId(Long.valueOf(epMemberIdArr[i]));
                        vo.setEnterpriseName(enterpriseNameArr[i]);
                        specifyEnterpriseList.add(vo);
                    }
                }
            }
        }
        this.specifyEnterpriseList = specifyEnterpriseList;
    }
}

package com.sinochem.crude.trade.listed.helper;

import com.sinochem.crude.trade.listed.domain.Demand;
import com.sinochem.crude.trade.listed.domain.DemandRelevanter;
import com.sinochem.crude.trade.listed.domain.DemandShip;
import com.sinochem.crude.trade.listed.domain.DemandShipBerth;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.order.remote.*;

/**
 * 提供转换为share中的VO的方法
 * @author Yichen Zhao
 * date: 20180118
 */
public class ShareVOHelper {

    public static DemandOrderVo convertToDemandOrderVO(Demand demand) {
        DemandOrderVo demandOrderVO = new DemandOrderVo();

        demandOrderVO.setId(demand.getId());
        demandOrderVO.setNum(demand.getNum());
        demandOrderVO.setNumfloat(demand.getNumfloat());
        demandOrderVO.setTradeItem(demand.getTradeItem());
        demandOrderVO.setAgio(demand.getAgio());
        demandOrderVO.setValuationBase(demand.getValuationBase());
        demandOrderVO.setPayItem(demand.getPayItem());
        demandOrderVO.setPayItemJSON(demand.getPayItemJSON());
        demandOrderVO.setPayTime(demand.getPayTime());
        demandOrderVO.setPayTime(demand.getPayTime());
        demandOrderVO.setCreater(demand.getCreater());
        demandOrderVO.setCreateTime(demand.getCreateTime());
        demandOrderVO.setUpdater(demand.getUpdater());
        demandOrderVO.setUpdaterTime(demand.getUpdaterTime());
        demandOrderVO.setParentId(demand.getParentId());
        demandOrderVO.setValuationFormula(demand.getValuationFormula());
        demandOrderVO.setValuationFormulaJson(demand.getValuationFormulaJson());
        demandOrderVO.setType(demand.getType());
        demandOrderVO.setDealType(demand.getDealType());
        demandOrderVO.setBizType(demand.getBizType());
        demandOrderVO.setOtherItem(demand.getOtherItem());
        demandOrderVO.setAuthItem(demand.getAuthItem());
        demandOrderVO.setBusinessCheckOrg(demand.getBusinessCheckOrg());
        demandOrderVO.setExportConfFile(demand.getExportConfFile());
        demandOrderVO.setRemark(demand.getRemark());
        demandOrderVO.setTenderOutTime(demand.getTenderOutTime());
        demandOrderVO.setStopBidTime(demand.getStopBidTime());
        demandOrderVO.setUuid(demand.getUuid());
        demandOrderVO.setCrudeOilOptions(demand.getCrudeOilOptions());
        demandOrderVO.setStatus(demand.getStatus());
        demandOrderVO.setIsAgent(demand.getIsAgent());
        demandOrderVO.setAgenter(demand.getAgenter());
        demandOrderVO.setExportType(demand.getExportType());
        demandOrderVO.setQcReport(demand.getQcReport());
        demandOrderVO.setPricingMode(demand.getPricingMode());
        demandOrderVO.setPricingMeasureUnit(demand.getPricingMeasureUnit());
        demandOrderVO.setMeasureMode(demand.getMeasureMode());
        demandOrderVO.setPriceDeclare(demand.getPriceDeclare());
        demandOrderVO.setIsSeal(demand.getIsSeal());
        demandOrderVO.setValuationCurrency(demand.getValuationCurrency());
        demandOrderVO.setValuationProidType(demand.getValuationProidType());
        demandOrderVO.setValuationProidStart(demand.getValuationProidStart());
        demandOrderVO.setValuationProidEnd(demand.getValuationProidEnd());
        demandOrderVO.setUnitPrice(demand.getUnitPrice());
        demandOrderVO.setPurchaseMode(demand.getPurchaseMode());
        demandOrderVO.setPurchaseType(demand.getPurchaseType());
        demandOrderVO.setLoadAndDischargePermittedTimespan(demand.getLoadAndDischargePermittedTimespan());
        demandOrderVO.setContractKind(demand.getContractKind());
        demandOrderVO.setInspectionFeeSharingType(demand.getInspectionFeeSharingType());
        demandOrderVO.setLaw(demand.getLaw());
        demandOrderVO.setGtc(demand.getGtc());
        return demandOrderVO;
    }

    public static RelevanterVO convertToRelevanterVO(DemandRelevanter demandRelevanter) {
        RelevanterVO relevanterVO = new RelevanterVO();

        relevanterVO.setId(demandRelevanter.getId());
        relevanterVO.setEMemberId(demandRelevanter.getEMemberId());
        relevanterVO.setDemandId(demandRelevanter.getDemandId());
        relevanterVO.setContacter(demandRelevanter.getContacter());
        relevanterVO.setPhoneNo(demandRelevanter.getPhoneNo());
        relevanterVO.setFamil(demandRelevanter.getFamil());
        relevanterVO.setFax(demandRelevanter.getFax());
        relevanterVO.setEnterpriseName(demandRelevanter.getEnterpriseName());
        relevanterVO.setEnterpriseAddress(demandRelevanter.getEnterpriseAddress());
        relevanterVO.setDealerId(demandRelevanter.getDealerId());
        relevanterVO.setDealerName(demandRelevanter.getDealerName());

        return relevanterVO;
    }

    public static Transport convertToTransportVO(DemandShip demandShip) {
        Transport transport = new Transport();

        transport.setId(demandShip.getId());
        transport.setTransportModes(demandShip.getTransportModes());
        transport.setDemandId(demandShip.getDemandId());
        transport.setDischargePort(demandShip.getDischargePort());
        transport.setShipmentPort(demandShip.getShipmentPort());
        transport.setDischargeStartTime(demandShip.getDischargeStartTime());
        transport.setDischargeEndTime(demandShip.getDischargeEndTime());
        transport.setShipmentStartTime(demandShip.getShipmentStartTime());
        transport.setShipmentEndTime(demandShip.getShipmentEndTime());
        transport.setShipType(demandShip.getShipType());
        transport.setCreater(demandShip.getCreater());
        transport.setCreateTime(demandShip.getCreateTime());
        transport.setUpdater(demandShip.getUpdater());
        transport.setUpdateTime(demandShip.getUpdateTime());

        return transport;
    }

    public static ShipBerth convertToShipBerthVO(DemandShipBerth demandShipBerth) {
        ShipBerth shipBerth = new ShipBerth();

        shipBerth.setId(demandShipBerth.getId());
        shipBerth.setBerthName(demandShipBerth.getBerthName());
        shipBerth.setBerthGrade(demandShipBerth.getBerthGrade());
        shipBerth.setDraftLimitation(demandShipBerth.getDraftLimitation());
        shipBerth.setCarryingCapacityMin(demandShipBerth.getCarryingCapacityMin());
        shipBerth.setCarryingCapacityMax(demandShipBerth.getCarryingCapacityMax());
        shipBerth.setDemandId(demandShipBerth.getDemandId());
        shipBerth.setShipType(demandShipBerth.getShipType());
        shipBerth.setBerthDesc(demandShipBerth.getBerthDesc());

        return shipBerth;
    }

    public static UserVo convertToUserVO(CurrentUser currentUser) {
        UserVo userVO = new UserVo();

        userVO.setMemberId(currentUser.getMemberId());
        userVO.setEpMemberId(currentUser.getEpMemberId());
        userVO.setName(currentUser.getName());
        userVO.setRoles(currentUser.getRoles());

        return userVO;
    }
}

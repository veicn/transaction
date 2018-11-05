package com.sinochem.crude.trade.order.remote.impl;


import com.alibaba.fastjson.JSON;
import com.eyeieye.melody.util.StringUtil;
import com.eyeieye.melody.util.uuid.UUIDGenerator;
import com.sinochem.crude.trade.order.dao.ContractMapper;
import com.sinochem.crude.trade.order.domain.*;
import com.sinochem.crude.trade.order.domain.CrudeOilResource;
import com.sinochem.crude.trade.order.domain.ProductOilResource;
import com.sinochem.crude.trade.order.enums.EnumContractType;
import com.sinochem.crude.trade.order.remote.*;
import com.sinochem.crude.trade.order.service.ContractService;
import com.sinochem.it.b2b.common.exception.BizException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class DemandOrderServiceImpl  implements DemandOrderService{;

    Logger logger = LoggerFactory.getLogger(DemandOrderServiceImpl.class);

    @Autowired
    UUIDGenerator uuidGenerator;

    @Autowired
    private ContractService contractService;

    @Autowired
    private ContractMapper contractMapper;

    @Override
    public DemandOrderReturnVO createOrderByDemandData(DemandOrderVo demandOrderVo,UserVo userVo) throws BizException{
        logger.info("当前用户："+JSON.toJSONString(userVo));
        logger.info("推送信息："+JSON.toJSONString(demandOrderVo));
        DemandOrderReturnVO returnVO = new DemandOrderReturnVO();
        String orderNo = null;
        if(demandOrderVo == null)
            return returnVO;
        if(demandOrderVo.getId() != null){
            Contract contract = contractService.selectByBiddingId(demandOrderVo.getId());
            if(contract != null){
                returnVO.setOrderNo(contract.getOrderNo());
                returnVO.setUuid(contract.getUuid());
                return returnVO;
            }
        }else{
            throw new BizException("报价单号不存在");
        }
        try{
            if("CrudeOil".equals(demandOrderVo.getBizType())){//原油
                return createCrudeOilBuyContract(demandOrderVo,userVo.getEpMemberId());
            }else if("ProductOil".equals(demandOrderVo.getBizType())){//成品油
                return createProductOilSaleContract(demandOrderVo,userVo.getEpMemberId());
            }
        }catch (Exception e){
            logger.error("确认中标异常：" + e.getMessage(),e);
            throw new BizException(e.getMessage(), e);
        }
        return returnVO;
    }

    @Override
    public void confirmContract(String orderNo, Long oper, String token) throws BizException {
        if(StringUtil.isNotBlank(orderNo)){
            Contract contract = contractMapper.selectByOrderNo(orderNo);
            if(contract == null || contract.getId() == null){
                throw new BizException("订单信息不存在：" + orderNo);
            }
            contractService.confirmContract(contract.getId(), oper, token);
        }else{
            throw new BizException("orderNo不能为空！");
        }

    }

    private DemandOrderReturnVO createCrudeOilBuyContract(DemandOrderVo demandOrderVo,Long oper)throws Exception{
        //原油买方发起
        CrudeOilBuyContract crudeOilBuyContract = new CrudeOilBuyContract();
        crudeOilBuyContract.setInitiator(oper);
        crudeOilBuyContract.setBizType(demandOrderVo.getBizType());
        if(demandOrderVo.getBuyer() != null)
            crudeOilBuyContract.setBuyer(demandOrderVo.getBuyer().getEMemberId());
        if(demandOrderVo.getProvider() != null)
            crudeOilBuyContract.setSeller(demandOrderVo.getProvider().getEMemberId());
        crudeOilBuyContract.setContractType(EnumContractType.NormalContract.getCode());

        crudeOilBuyContract.setNumFloat(demandOrderVo.getNumfloat());
        crudeOilBuyContract.setValuationFormula(demandOrderVo.getValuationFormula());
        crudeOilBuyContract.setValuationFormulaJson(demandOrderVo.getValuationFormulaJson());
        crudeOilBuyContract.setTradeItem(demandOrderVo.getTradeItem());
        crudeOilBuyContract.setAgio(demandOrderVo.getAgio());
        crudeOilBuyContract.setValuationBase(demandOrderVo.getValuationBase());
        crudeOilBuyContract.setValuationProidType(demandOrderVo.getValuationProidType());
        crudeOilBuyContract.setValuationProidStart(demandOrderVo.getValuationProidStart());
        crudeOilBuyContract.setValuationProidEnd(demandOrderVo.getValuationProidEnd());
        crudeOilBuyContract.setPayItem(demandOrderVo.getPayItem());
        crudeOilBuyContract.setPayItemJSON(demandOrderVo.getPayItemJSON());
        if(crudeOilBuyContract.getPayTime() != null){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            crudeOilBuyContract.setPayTime(formatter.parse(demandOrderVo.getPayTime()));
        }

        crudeOilBuyContract.setContractKind(demandOrderVo.getContractKind());
        crudeOilBuyContract.setMeasureMode(demandOrderVo.getMeasureMode());
        crudeOilBuyContract.setLoadAndDischargePermittedTimespan(demandOrderVo.getLoadAndDischargePermittedTimespan());
        crudeOilBuyContract.setPurchaseMode(demandOrderVo.getPurchaseMode());
        crudeOilBuyContract.setNum(demandOrderVo.getNum());
        crudeOilBuyContract.setUnit(1L);//数量单位
        crudeOilBuyContract.setUuid(uuidGenerator.gain());

        crudeOilBuyContract.setRemark(demandOrderVo.getRemark());
        crudeOilBuyContract.setCreater(demandOrderVo.getCreater());
        crudeOilBuyContract.setCreateTime(demandOrderVo.getCreateTime());
        crudeOilBuyContract.setUpdater(demandOrderVo.getUpdater());
        crudeOilBuyContract.setUpdateTime(demandOrderVo.getUpdaterTime());

        crudeOilBuyContract.setToken(String.valueOf(System.currentTimeMillis()));
        crudeOilBuyContract.setType(demandOrderVo.getType());//单据D
        crudeOilBuyContract.setDealType(demandOrderVo.getDealType());

        Integer unitPirce = demandOrderVo.getUnitPrice();
        if (unitPirce != null) {
            crudeOilBuyContract.setPrice(new BigDecimal(demandOrderVo.getUnitPrice()/100));
        }

        crudeOilBuyContract.setBiddingId(demandOrderVo.getId());
        crudeOilBuyContract.setPurchaseType(demandOrderVo.getPurchaseType());
        //执行人保存
        crudeOilBuyContract.setExecutor(demandOrderVo.getBuyer().getId());
        crudeOilBuyContract.setOtherItem(demandOrderVo.getOtherItem());
        crudeOilBuyContract.setAuthItem(demandOrderVo.getAuthItem()+"");
        crudeOilBuyContract.setBusinessCheckOrg(demandOrderVo.getBusinessCheckOrg());
        crudeOilBuyContract.setExportConfFile(demandOrderVo.getExportConfFile());
        crudeOilBuyContract.setTenderOutTime(demandOrderVo.getTenderOutTime());
        crudeOilBuyContract.setStopBidTime(demandOrderVo.getStopBidTime());
        crudeOilBuyContract.setInspectionFeeSharingType(demandOrderVo.getInspectionFeeSharingType());
        crudeOilBuyContract.setLaw(demandOrderVo.getLaw());
        crudeOilBuyContract.setGtc(demandOrderVo.getGtc());

        //船务和泊位信息
        crudeOilBuyContract.setContractShip(getShip(demandOrderVo));

        if(getBuyer(demandOrderVo) != null) {
            crudeOilBuyContract.setContractBuyer(getBuyer(demandOrderVo));
        }

        if(getTrader(demandOrderVo) != null) {
            crudeOilBuyContract.setContractTrader(getTrader(demandOrderVo));
        }

        if(getProvider(demandOrderVo) != null) {
            crudeOilBuyContract.setContractSupplier(getProvider(demandOrderVo));
        }


        if(demandOrderVo.getCrudeOilResource() != null) {
            // 油种表
            CrudeOilResource crudeOilResource = new CrudeOilResource();
            crudeOilResource.setDesc(demandOrderVo.getCrudeOilResource().getDesc());
            crudeOilResource.setArea(demandOrderVo.getCrudeOilResource().getArea());
            crudeOilResource.setName(demandOrderVo.getCrudeOilResource().getName());
            crudeOilResource.setOrigin(demandOrderVo.getCrudeOilResource().getOrigin());
            crudeOilResource.setPropertyId(demandOrderVo.getCrudeOilResource().getPropertyId());
            crudeOilResource.setIndicator1Max(convertObjTolong(demandOrderVo.getCrudeOilResource().getIndicator1Max()));
            crudeOilResource.setIndicator1Min(convertObjTolong(demandOrderVo.getCrudeOilResource().getIndicator1Min()));
            crudeOilResource.setIndicator2Max(convertObjTolong(demandOrderVo.getCrudeOilResource().getIndicator2Max()));
            crudeOilResource.setIndicator2Min(convertObjTolong(demandOrderVo.getCrudeOilResource().getIndicator2Min()));
            crudeOilResource.setIndicator3Max(convertObjTolong(demandOrderVo.getCrudeOilResource().getIndicator3Max()));
            crudeOilResource.setIndicator3Min(convertObjTolong(demandOrderVo.getCrudeOilResource().getIndicator3Min()));
            crudeOilResource.setIndicator4Max(convertObjTolong(demandOrderVo.getCrudeOilResource().getIndicator4Max()));
            crudeOilResource.setIndicator4Min(convertObjTolong(demandOrderVo.getCrudeOilResource().getIndicator4Min()));
            crudeOilResource.setIndicator5Max(convertObjTolong(demandOrderVo.getCrudeOilResource().getIndicator5Max()));
            crudeOilResource.setIndicator5Min(convertObjTolong(demandOrderVo.getCrudeOilResource().getIndicator5Min()));
            crudeOilResource.setIndicator6Max(convertObjTolong(demandOrderVo.getCrudeOilResource().getIndicator6Max()));
            crudeOilResource.setIndicator6Min(convertObjTolong(demandOrderVo.getCrudeOilResource().getIndicator6Min()));
            crudeOilResource.setIndicator7Max(convertObjTolong(demandOrderVo.getCrudeOilResource().getIndicator7Max()));
            crudeOilResource.setIndicator7Min(convertObjTolong(demandOrderVo.getCrudeOilResource().getIndicator7Min()));
            crudeOilResource.setIndicator8Max(convertObjTolong(demandOrderVo.getCrudeOilResource().getIndicator8Max()));
            crudeOilResource.setIndicator8Min(convertObjTolong(demandOrderVo.getCrudeOilResource().getIndicator8Min()));
            crudeOilResource.setIndicator9Max(convertObjTolong(demandOrderVo.getCrudeOilResource().getIndicator9Max()));
            crudeOilResource.setIndicator9Min(convertObjTolong(demandOrderVo.getCrudeOilResource().getIndicator9Min()));
            crudeOilResource.setIndicator10Max(convertObjTolong(demandOrderVo.getCrudeOilResource().getIndicator10Max()));
            crudeOilResource.setIndicator10Min(convertObjTolong(demandOrderVo.getCrudeOilResource().getIndicator10Min()));
            crudeOilResource.setIndicator11Max(convertObjTolong(demandOrderVo.getCrudeOilResource().getIndicator11Max()));
            crudeOilResource.setIndicator11Min(convertObjTolong(demandOrderVo.getCrudeOilResource().getIndicator11Min()));
            crudeOilResource.setIndicator12Max(convertObjTolong(demandOrderVo.getCrudeOilResource().getIndicator12Max()));
            crudeOilResource.setIndicator12Min(convertObjTolong(demandOrderVo.getCrudeOilResource().getIndicator12Min()));
            crudeOilBuyContract.setCrudeOilResource(crudeOilResource);
        }

        /*if(demandOrderVo.getTransport() != null) {
            // 船务
            ContractShip contractShip = new ContractShip();
            contractShip.setDischargeEndTime(demandOrderVo.getTransport().getDischargeEndTime());
            contractShip.setDischargeStartTime(demandOrderVo.getTransport().getDischargeStartTime());
            contractShip.setShipmentEndTime(demandOrderVo.getTransport().getShipmentEndTime());
            contractShip.setShipmentStartTime(demandOrderVo.getTransport().getShipmentStartTime());
            contractShip.setDischargePort(demandOrderVo.getTransport().getDischargePort());
            contractShip.setShipmentPort(demandOrderVo.getTransport().getShipmentPort());

            crudeOilBuyContract.setContractShip(contractShip);
        }
        // 泊位
        crudeOilBuyContract.getContractShip().setContractShipBerths(getShipBerthList(demandOrderVo));*/
        //调用order模块创建合约
        contractService.createContract(crudeOilBuyContract,oper);
        DemandOrderReturnVO returnVO = new DemandOrderReturnVO();
        returnVO.setUuid(crudeOilBuyContract.getUuid());
        returnVO.setOrderNo(crudeOilBuyContract.getOrderNo());
        return returnVO;

    }
    private DemandOrderReturnVO createProductOilSaleContract(DemandOrderVo demandOrderVo,Long oper)throws Exception {
        //成品油卖方发起
        ProductOilSaleContract productOilSaleContract = new ProductOilSaleContract();
        productOilSaleContract.setInitiator(oper);
        productOilSaleContract.setBizType(demandOrderVo.getBizType());
        productOilSaleContract.setBuyer(demandOrderVo.getBuyer().getEMemberId());
        productOilSaleContract.setSeller(demandOrderVo.getProvider().getEMemberId());
        productOilSaleContract.setContractType(EnumContractType.NormalContract.getCode());
        productOilSaleContract.setNumFloat(demandOrderVo.getNumfloat());
        productOilSaleContract.setValuationFormula(demandOrderVo.getValuationFormula());
        productOilSaleContract.setTradeItem(demandOrderVo.getTradeItem());
        productOilSaleContract.setAgio(demandOrderVo.getAgio());
        productOilSaleContract.setValuationFormulaJson(demandOrderVo.getValuationFormulaJson());
        productOilSaleContract.setValuationBase(demandOrderVo.getValuationBase());
        productOilSaleContract.setValuationProidType(demandOrderVo.getValuationProidType());
        productOilSaleContract.setValuationProidStart(demandOrderVo.getValuationProidStart());
        productOilSaleContract.setValuationProidEnd(demandOrderVo.getValuationProidEnd());
        productOilSaleContract.setPayItem(demandOrderVo.getPayItem());
        if (productOilSaleContract.getPayTime() != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            productOilSaleContract.setPayTime(formatter.parse(demandOrderVo.getPayTime()));
        }

        productOilSaleContract.setContractKind(demandOrderVo.getContractKind());
        productOilSaleContract.setMeasureMode(demandOrderVo.getMeasureMode());
        productOilSaleContract.setLoadAndDischargePermittedTimespan(demandOrderVo.getLoadAndDischargePermittedTimespan());
        productOilSaleContract.setPurchaseMode(demandOrderVo.getPurchaseMode());
        productOilSaleContract.setNum(demandOrderVo.getNum());
        productOilSaleContract.setUnit(1L);//数量单位
        //contract.setRate();//汇率
        //contract.setMeterNum();//计量数量
        //contract.setMeterUnit((Long) demandOrderVo.getPricingMeasureUnit());
        productOilSaleContract.setUuid(uuidGenerator.gain());

        productOilSaleContract.setRemark(demandOrderVo.getRemark());
        productOilSaleContract.setCreater(demandOrderVo.getCreater());
        productOilSaleContract.setCreateTime(demandOrderVo.getCreateTime());
        productOilSaleContract.setUpdater(demandOrderVo.getUpdater());
        productOilSaleContract.setUpdateTime(demandOrderVo.getUpdaterTime());

        productOilSaleContract.setToken(String.valueOf(System.currentTimeMillis()));
        productOilSaleContract.setType(demandOrderVo.getType());//单据D
        productOilSaleContract.setDealType(demandOrderVo.getDealType());

        if (demandOrderVo.getUnitPrice() != null) {
            productOilSaleContract.setPrice(new BigDecimal(demandOrderVo.getUnitPrice() / 100));
        }

        productOilSaleContract.setBiddingId(demandOrderVo.getId());
        productOilSaleContract.setPurchaseType(demandOrderVo.getPurchaseType());
        //执行人保存
        if (demandOrderVo.getBuyer() != null)
            productOilSaleContract.setExecutor(demandOrderVo.getBuyer().getId());
        productOilSaleContract.setOtherItem(demandOrderVo.getOtherItem());
        productOilSaleContract.setAuthItem(demandOrderVo.getAuthItem() + "");
        productOilSaleContract.setBusinessCheckOrg(demandOrderVo.getBusinessCheckOrg());
        productOilSaleContract.setExportConfFile(demandOrderVo.getExportConfFile());
        productOilSaleContract.setTenderOutTime(demandOrderVo.getTenderOutTime());
        productOilSaleContract.setStopBidTime(demandOrderVo.getStopBidTime());

        productOilSaleContract.setContractShip(getShip(demandOrderVo));


        if (getBuyer(demandOrderVo) != null) {
            productOilSaleContract.setContractBuyer(getBuyer(demandOrderVo));
        }

        if (getTrader(demandOrderVo) != null) {
            productOilSaleContract.setContractTrader(getTrader(demandOrderVo));
        }

        if (getProvider(demandOrderVo) != null) {
            productOilSaleContract.setContractSupplier(getProvider(demandOrderVo));
        }
        if (demandOrderVo.getProductOilResource() != null) {
            // 油种表
            ProductOilResource productOilResource = new ProductOilResource();
            productOilResource.setDesc(demandOrderVo.getProductOilResource().getDesc());
            productOilResource.setOrigin(demandOrderVo.getProductOilResource().getOrigin());
            productOilResource.setName(demandOrderVo.getProductOilResource().getName());
            productOilResource.setProductOilSpecs(demandOrderVo.getProductOilResource().getProductOilSpecs());
            productOilResource.setProductOilClassify(demandOrderVo.getProductOilResource().getProductOilClassify());
            productOilResource.setProductOilKind(demandOrderVo.getProductOilResource().getProductOilKind());

            productOilResource.setIndicator1Min(convertObjTolong(demandOrderVo.getProductOilResource().getIndicator1Min()));
            productOilResource.setIndicator2Min(convertObjTolong(demandOrderVo.getProductOilResource().getIndicator2Min()));
            productOilResource.setIndicator3Min(convertObjTolong(demandOrderVo.getProductOilResource().getIndicator3Min()));
            productOilResource.setIndicator4Min(convertObjTolong(demandOrderVo.getProductOilResource().getIndicator4Min()));
            productOilResource.setIndicator5Min(convertObjTolong(demandOrderVo.getProductOilResource().getIndicator5Min()));
            productOilResource.setIndicator6Min(convertObjTolong(demandOrderVo.getProductOilResource().getIndicator6Min()));
            productOilResource.setIndicator7Min(convertObjTolong(demandOrderVo.getProductOilResource().getIndicator7Min()));
            productOilResource.setIndicator8Min(convertObjTolong(demandOrderVo.getProductOilResource().getIndicator8Min()));
            productOilResource.setIndicator9Min(convertObjTolong(demandOrderVo.getProductOilResource().getIndicator9Min()));
            productOilResource.setIndicator10Min(convertObjTolong(demandOrderVo.getProductOilResource().getIndicator10Min()));
            productOilResource.setIndicator11Min(convertObjTolong(demandOrderVo.getProductOilResource().getIndicator11Min()));
            productOilResource.setIndicator12Min(convertObjTolong(demandOrderVo.getProductOilResource().getIndicator12Min()));

            productOilResource.setIndicator1Max(convertObjTolong(demandOrderVo.getProductOilResource().getIndicator1Max()));
            productOilResource.setIndicator2Max(convertObjTolong(demandOrderVo.getProductOilResource().getIndicator2Max()));
            productOilResource.setIndicator3Max(convertObjTolong(demandOrderVo.getProductOilResource().getIndicator3Max()));
            productOilResource.setIndicator4Max(convertObjTolong(demandOrderVo.getProductOilResource().getIndicator4Max()));
            productOilResource.setIndicator5Max(convertObjTolong(demandOrderVo.getProductOilResource().getIndicator5Max()));
            productOilResource.setIndicator6Max(convertObjTolong(demandOrderVo.getProductOilResource().getIndicator6Max()));
            productOilResource.setIndicator7Max(convertObjTolong(demandOrderVo.getProductOilResource().getIndicator7Max()));
            productOilResource.setIndicator8Max(convertObjTolong(demandOrderVo.getProductOilResource().getIndicator8Max()));
            productOilResource.setIndicator9Max(convertObjTolong(demandOrderVo.getProductOilResource().getIndicator9Max()));
            productOilResource.setIndicator10Max(convertObjTolong(demandOrderVo.getProductOilResource().getIndicator10Max()));
            productOilResource.setIndicator11Max(convertObjTolong(demandOrderVo.getProductOilResource().getIndicator11Max()));
            productOilResource.setIndicator12Max(convertObjTolong(demandOrderVo.getProductOilResource().getIndicator12Max()));

            productOilSaleContract.setProductOilResource(productOilResource);

        }

        /*if (demandOrderVo.getTransport() != null) {
            // 船务
            ContractShip contractShip = new ContractShip();
            contractShip.setDischargeEndTime(demandOrderVo.getTransport().getDischargeEndTime());
            contractShip.setDischargeStartTime(demandOrderVo.getTransport().getDischargeStartTime());
            contractShip.setShipmentEndTime(demandOrderVo.getTransport().getShipmentEndTime());
            contractShip.setShipmentStartTime(demandOrderVo.getTransport().getShipmentStartTime());
            contractShip.setDischargePort(demandOrderVo.getTransport().getDischargePort());
            contractShip.setShipmentPort(demandOrderVo.getTransport().getShipmentPort());

            productOilSaleContract.setContractShip(contractShip);
        }

        // 泊位
        productOilSaleContract.getContractShip().setContractShipBerths(getShipBerthList(demandOrderVo));*/

        contractService.createContract(productOilSaleContract,oper);
        DemandOrderReturnVO returnVO = new DemandOrderReturnVO();
        returnVO.setUuid(productOilSaleContract.getUuid());
        returnVO.setOrderNo(productOilSaleContract.getOrderNo());
        return returnVO;

    }

    /**
     * 决解空值传递给long的问题
     * @param obj
     * @return
     */
    private long convertObjTolong(Object obj) {
        return obj == null ? 0L : Long.valueOf(String.valueOf(obj));
    }

    private ContractShip getShip(DemandOrderVo demandOrderVo){
        ContractShip ship = new ContractShip();
        //泊位信息
        Transport transport = demandOrderVo.getTransport();
        if (null != transport) {
            ship.setTransportModes(transport.getTransportModes());
            ship.setDischargePort(transport.getDischargePort());
            ship.setShipmentPort(transport.getShipmentPort());
            ship.setShipmentStartTime(transport.getShipmentStartTime());
            ship.setShipmentEndTime(transport.getShipmentEndTime());
            ship.setDischargeStartTime(transport.getDischargeStartTime());
            ship.setDischargeEndTime(transport.getDischargeEndTime());
            ship.setShipType(transport.getShipType());
            ship.setCreater(transport.getCreater());
            ship.setCreateTime(transport.getCreateTime());
            ship.setUpdater(transport.getUpdater());
            ship.setUpdateTime(transport.getUpdateTime());
        }
        //泊位放到运输实体中
        ship.setContractShipBerths(getShipBerthList(demandOrderVo));
        return ship;
    }

    /**
     * 获取泊位
     * @param demandOrderVo
     * @return
     */
    private List<ContractShipBerth> getShipBerthList(DemandOrderVo demandOrderVo){

        List<ContractShipBerth> contractShipBerthList = new ArrayList<>();
        ContractShipBerth contractShipBerth;
        if(demandOrderVo.getShipBerth()!=null){
            for (ShipBerth csb: demandOrderVo.getShipBerth()) {
                contractShipBerth  = new ContractShipBerth();
                contractShipBerth.setBerthGrade(csb.getBerthGrade());
                contractShipBerth.setBerthName(csb.getBerthName());
                contractShipBerth.setDraftLimitation(csb.getDraftLimitation());
                contractShipBerth.setCarryingCapacityMax(csb.getCarryingCapacityMax());
                contractShipBerth.setCarryingCapacityMin(csb.getCarryingCapacityMin());
                contractShipBerth.setShipType(csb.getShipType());
                contractShipBerth.setBerthDesc(csb.getBerthDesc());
                contractShipBerthList.add(contractShipBerth);
            }
        }
        return contractShipBerthList;
    }
    private ContractBuyer getBuyer(DemandOrderVo demandOrderVo){
        if(demandOrderVo.getBuyer() != null){
            ContractRelevanter buyer = new ContractRelevanter();
            buyer.setAddress(demandOrderVo.getBuyer().getEnterpriseAddress());
            buyer.setContacter(demandOrderVo.getBuyer().getContacter());
            buyer.setEmail(demandOrderVo.getBuyer().getFamil());
            buyer.setFax(demandOrderVo.getBuyer().getFax());
            buyer.setPhoneNo(demandOrderVo.getBuyer().getPhoneNo());
            buyer.setEMemberId(demandOrderVo.getBuyer().getEMemberId());
            buyer.seteMemberName(demandOrderVo.getBuyer().getEnterpriseName());

            // 设置交易员信息为当前登录人name和id  by sijliu  2017-12-27
            buyer.setDealerId(demandOrderVo.getBuyer().getDealerId());
            buyer.setDealerName(demandOrderVo.getBuyer().getDealerName());

            ContractBuyer contractBuyer = new ContractBuyer(buyer);
            return contractBuyer;
        }else{
            return null;
        }
    }
    private ContractTrader getTrader(DemandOrderVo demandOrderVo){
        if (demandOrderVo.getTrader() != null) {
            ContractRelevanter trader = new ContractRelevanter();
            trader.setPhoneNo(demandOrderVo.getTrader().getPhoneNo());
            trader.setFax(demandOrderVo.getTrader().getFax());
            trader.setAddress(demandOrderVo.getTrader().getEnterpriseAddress());
            trader.setContacter(demandOrderVo.getTrader().getContacter());
            trader.setEMemberId(demandOrderVo.getTrader().getEMemberId());
            trader.setEmail(demandOrderVo.getTrader().getFamil());
            trader.seteMemberName(demandOrderVo.getTrader().getEnterpriseName());

            // 设置交易员信息为当前登录人name和id  by sijliu  2017-12-27
            trader.setDealerId(demandOrderVo.getTrader().getDealerId());
            trader.setDealerName(demandOrderVo.getTrader().getDealerName());

            ContractTrader contractTrader = new ContractTrader(trader);
            return contractTrader;
        }else{
            return null;
        }
    }
    private ContractSupplier getProvider(DemandOrderVo demandOrderVo){
        if (demandOrderVo.getProvider() != null) {
            ContractRelevanter supplier = new ContractRelevanter();
            supplier.setPhoneNo(demandOrderVo.getProvider().getPhoneNo());
            supplier.setFax(demandOrderVo.getProvider().getFax());
            supplier.setAddress(demandOrderVo.getProvider().getEnterpriseAddress());
            supplier.setContacter(demandOrderVo.getProvider().getContacter());
            supplier.setEMemberId(demandOrderVo.getProvider().getEMemberId());
            supplier.setEmail(demandOrderVo.getProvider().getFamil());
            supplier.seteMemberName(demandOrderVo.getProvider().getEnterpriseName());

            // 设置交易员信息为当前登录人name和id  by sijliu  2017-12-27
            supplier.setDealerId(demandOrderVo.getProvider().getDealerId());
            supplier.setDealerName(demandOrderVo.getProvider().getDealerName());

            ContractSupplier contractSupplier = new ContractSupplier(supplier);
            return contractSupplier;
        }else{
            return null;
        }
    }
    private ContractShip get(DemandOrderVo demandOrderVo){
        if (demandOrderVo.getTransport() != null) {
            // 船务
            ContractShip contractShip = new ContractShip();
            contractShip.setDischargeEndTime(demandOrderVo.getTransport().getDischargeEndTime());
            contractShip.setDischargeStartTime(demandOrderVo.getTransport().getDischargeStartTime());
            contractShip.setShipmentEndTime(demandOrderVo.getTransport().getShipmentEndTime());
            contractShip.setShipmentStartTime(demandOrderVo.getTransport().getShipmentStartTime());
            contractShip.setDischargePort(demandOrderVo.getTransport().getDischargePort());
            contractShip.setShipmentPort(demandOrderVo.getTransport().getShipmentPort());

            return contractShip;
        }else{
            return null;
        }
    }
}

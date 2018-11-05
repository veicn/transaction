package com.sinochem.crude.trade.listed.service.impl;

import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.listed.constant.Constant;
import com.sinochem.crude.trade.listed.domain.Demand;
import com.sinochem.crude.trade.listed.domain.DemandRelevanter;
import com.sinochem.crude.trade.listed.domain.DemandSpecifyEnterprise;
import com.sinochem.crude.trade.listed.enums.DealType;
import com.sinochem.crude.trade.listed.enums.DemandBiddingType;
import com.sinochem.crude.trade.listed.enums.DemandRelevanterType;
import com.sinochem.crude.trade.listed.service.*;
import com.sinochem.it.b2b.common.exception.BizException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DemandMessageServiceImpl implements DemandMessageService {
    @Autowired
    private DemandService demandService;
    @Autowired
    private DemandRelevanterService demandRelevanterService;
    @Autowired
    private SpecifyEnterpriceService specifyEnterpriceService;
    @Autowired
    private URLBroker listedServer;
    @Autowired
    private URLBroker orderServer;
    @Autowired
    private MessageService messageService;
    /**
     * 定向发布
     * @param demandId
     */
    @Override
    public void demandDirectionalRelease(Long demandId) {
        try{
            Demand demand = demandService.getDemandByKey(demandId);
            DemandRelevanter buyer = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeOne(demandId, DemandRelevanterType.BUYER.getCode());
            DemandRelevanter seller = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeOne(demandId, DemandRelevanterType.SUPPLIER.getCode());
            List<DemandSpecifyEnterprise> specifyEnterpriseList = specifyEnterpriceService.getDemandSpecifyEnterpriseListByDemandId(demandId);
            if(specifyEnterpriseList != null && specifyEnterpriseList.size() > 0){
                if(DealType.BUY.getCode().equals(demand.getDealType())){ //采购需求
                    for(DemandSpecifyEnterprise enterprise : specifyEnterpriseList){
                        // 向被定向的企业发送消息
                        Map<String, Object> model = new HashMap<String, Object>();
                        model.put("pUserName",buyer.getEnterpriseName());
                        model.put("hyperlink",listedServer.get("crudeoillobby/purchasingDemandDetail.htm").put("demandId", demand.getId()).toString());
                        model.put("demandNo", demand.getUuid());
                        //messageService.sendMessage("采购需求定向发布",enterprise.getEpMemberId(), "demand_directional_release",model);
                        messageService.sendMessage(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0023),enterprise.getEpMemberId(),enterprise.getEpMemberId(), "demand_directional_release",model);
                        
                    }
                }else{
                    for(DemandSpecifyEnterprise enterprise : specifyEnterpriseList){
                        // 向被定向的企业发送消息
                        Map<String, Object> model = new HashMap<String, Object>();
                        model.put("pUserName",seller.getEnterpriseName());
                        model.put("hyperlink",listedServer.get("crudeoillobby/sellingDemandDetail.htm").put("demandId", demand.getId()).toString());
                        model.put("demandNo", demand.getUuid());
                        //messageService.sendMessage("销售资源定向发布",enterprise.getEpMemberId(), "selling_demand_directional_release",model);
                        messageService.sendMessage(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0024),enterprise.getEpMemberId(),enterprise.getEpMemberId(), "selling_demand_directional_release",model);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 发起报价
     * @param biddingId
     */
    @Override
    public void biddingRelease(Long biddingId) {
        try{
            Demand biddingDemand = demandService.getDemandByKey(biddingId);
            DemandRelevanter buyer = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeOne(biddingId, DemandRelevanterType.BUYER.getCode());
            DemandRelevanter seller = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeOne(biddingId, DemandRelevanterType.SUPPLIER.getCode());
            if(DealType.BUY.getCode().equals(biddingDemand.getDealType())){//买单，发送给卖家
                //发送消息(卖家)
                Map<String, Object> model = new HashMap<String, Object>();
                model.put("pUserName",buyer.getEnterpriseName());
                //投标类型 1-意向投标 2-实际投标
                if(DemandBiddingType.INTENTION_BIDDING.getCode().compareTo(biddingDemand.getBiddingType()) == 0){
                    model.put("biddingType","意向");
                }else{
                    model.put("biddingType","正式");
                }
                model.put("hyperlink",listedServer.get("oilSaleBidding/biddingDetail.htm").put("demandId", biddingDemand.getId()).toString());
                model.put("demandNo", biddingDemand.getUuid());
                //messageService.sendMessage("销售需求报价发布",seller.getDealerId(), "bidding_release",model);
                messageService.sendMessage(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0025),seller.getDealerId(),seller.getEMemberId(), "bidding_release",model);
            }else{ //卖单，发送给买家
                //发送消息(买家)
                Map<String, Object> model = new HashMap<String, Object>();
                model.put("pUserName",seller.getEnterpriseName());
                //投标类型 1-意向投标 2-实际投标
                if(DemandBiddingType.INTENTION_BIDDING.getCode().compareTo(biddingDemand.getBiddingType()) == 0){
                    model.put("biddingType","意向");
                }else{
                    model.put("biddingType","正式");
                }
                model.put("hyperlink",listedServer.get("purchaseManager/biddingDetail.htm").put("demandId", biddingDemand.getId()).toString());
                model.put("demandNo", biddingDemand.getUuid());
                //messageService.sendMessage("采购需求报价发布",buyer.getDealerId(), "bidding_release",model);
                messageService.sendMessage(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0026),buyer.getDealerId(),buyer.getEMemberId(), "bidding_release",model);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 修改报价
     * @param biddingId
     */
    @Override
    public void biddingUpdate(Long biddingId) {
        try{
            Demand biddingDemand = demandService.getDemandByKey(biddingId);
            DemandRelevanter buyer = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeOne(biddingId, DemandRelevanterType.BUYER.getCode());
            DemandRelevanter seller = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeOne(biddingId, DemandRelevanterType.SUPPLIER.getCode());
            //发送消息(卖家)
            if(DealType.BUY.getCode().equals(biddingDemand.getDealType())){//买单，发送给卖家
                Map<String, Object> model = new HashMap<String, Object>();
                model.put("pUserName",buyer.getEnterpriseName());
                //投标类型 1-意向投标 2-实际投标
                if(DemandBiddingType.INTENTION_BIDDING.getCode().compareTo(biddingDemand.getBiddingType()) == 0){
                    model.put("biddingType","意向");
                }else{
                    model.put("biddingType","正式");
                }
                model.put("hyperlink",listedServer.get("oilSaleBidding/biddingDetail.htm").put("demandId", biddingDemand.getId()).toString());
                model.put("demandNo", biddingDemand.getUuid());
                //messageService.sendMessage("销售需求报价修改",seller.getDealerId(), "bidding_update",model);
                messageService.sendMessage(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0027),seller.getDealerId(),seller.getEMemberId(), "bidding_update",model);
            }else{//卖单，发送给买家
                Map<String, Object> model = new HashMap<String, Object>();
                model.put("pUserName",seller.getEnterpriseName());
                //投标类型 1-意向投标 2-实际投标
                if(DemandBiddingType.INTENTION_BIDDING.getCode().compareTo(biddingDemand.getBiddingType()) == 0){
                    model.put("biddingType","意向");
                }else{
                    model.put("biddingType","正式");
                }
                model.put("hyperlink",listedServer.get("purchaseManager/biddingDetail.htm").put("demandId", biddingDemand.getId()).toString());
                model.put("demandNo", biddingDemand.getUuid());
                //messageService.sendMessage("采购需求报价修改",buyer.getDealerId(), "bidding_update",model);
                messageService.sendMessage(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0028),buyer.getDealerId(),buyer.getEMemberId(), "bidding_update",model);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 确认报价
     * @param biddingId
     * @param orderNo 订单编码
     * @param flag   是否中标 true 中标  false 未中标
     */
    @Override
    public void confirmBidding(Long biddingId, String orderNo, String uuid, boolean flag) {
        try{
            Demand biddingDemand = demandService.getDemandByKey(biddingId);
            Demand demand = demandService.getDemandByKey(biddingDemand.getParentId());
            DemandRelevanter buyer = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeOne(biddingId, DemandRelevanterType.BUYER.getCode());
            DemandRelevanter seller = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeOne(biddingId, DemandRelevanterType.SUPPLIER.getCode());
            if(DemandBiddingType.INTENTION_BIDDING.getCode().compareTo(biddingDemand.getBiddingType()) == 0){
                if(DealType.BUY.getCode().equals(biddingDemand.getDealType())){//买单，发送给买家
                    Map<String, Object> model = new HashMap<String, Object>();
                    model.put("pUserName",seller.getEnterpriseName());
                    model.put("hyperlink",listedServer.get("oilSaleBidding/biddingDetail.htm").put("demandId", biddingDemand.getId()).toString());
                    model.put("demandNo", biddingDemand.getUuid());
                    //messageService.sendMessage("销售需求意向报价确认",buyer.getDealerId(), "bidding_intention_confirm",model);
                    messageService.sendMessage(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0029),buyer.getDealerId(),buyer.getEMemberId(), "bidding_intention_confirm",model);
                }else{//卖单，发送给卖家
                    Map<String, Object> model = new HashMap<String, Object>();
                    model.put("pUserName",buyer.getEnterpriseName());
                    model.put("hyperlink",listedServer.get("purchaseManager/biddingDetail.htm").put("demandId", biddingDemand.getId()).toString());
                    model.put("demandNo", biddingDemand.getUuid());
                    //messageService.sendMessage("采购需求意向报价确认",seller.getDealerId(), "bidding_intention_confirm",model);
                    messageService.sendMessage(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0030),seller.getDealerId(),seller.getEMemberId(), "bidding_intention_confirm",model);
                }
            }else{
                if(flag){
                    if(DealType.BUY.getCode().equals(biddingDemand.getDealType())){//买单，发送给买家
                        Map<String, Object> model = new HashMap<String, Object>();
                        model.put("pUserName",seller.getEnterpriseName());
                        model.put("demandNo", biddingDemand.getUuid());
                        model.put("hyperlink",orderServer.get("buyer/crudeOilOrderInfo.htm").put("uuid", uuid).toString());
                        model.put("orderNo", orderNo);
                        //messageService.sendMessage("销售需求报价中标",buyer.getDealerId(), "bidding_confirm",model);
                        messageService.sendMessage(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0031),buyer.getDealerId(),buyer.getEMemberId(), "bidding_confirm",model);
                    }else{//卖单，发送给卖家
                        Map<String, Object> model = new HashMap<String, Object>();
                        model.put("pUserName",buyer.getEnterpriseName());
                        model.put("demandNo", biddingDemand.getUuid());
                        model.put("hyperlink",orderServer.get("buyer/crudeOilOrderInfo.htm").put("uuid", uuid).toString());
                        model.put("orderNo", orderNo);
                        //messageService.sendMessage("采购需求报价中标",seller.getDealerId(), "bidding_confirm",model);
                        messageService.sendMessage(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0032),seller.getDealerId(),seller.getEMemberId(), "bidding_confirm",model);
                    }
                }else{
                    if(DealType.BUY.getCode().equals(biddingDemand.getDealType())){//买单，发送给买家
                        Map<String, Object> model = new HashMap<String, Object>();
                        model.put("pUserName",seller.getEnterpriseName());
                        model.put("hyperlink",listedServer.get("oilSaleBidding/biddingDetail.htm").put("demandId", biddingDemand.getId()).toString());
                        model.put("biddingNo", biddingDemand.getUuid());
                        model.put("demandNo", demand.getUuid());
                        //messageService.sendMessage("采购需求报价未中标",buyer.getDealerId(), "bidding_not_confirm",model);
                        messageService.sendMessage(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0033),buyer.getDealerId(),buyer.getEMemberId(), "bidding_not_confirm",model);
                    }else{//卖单，发送给卖家
                        Map<String, Object> model = new HashMap<String, Object>();
                        model.put("pUserName",buyer.getEnterpriseName());
                        model.put("hyperlink",listedServer.get("purchaseManager/biddingDetail.htm").put("demandId", biddingDemand.getId()).toString());
                        model.put("biddingNo", biddingDemand.getUuid());
                        model.put("demandNo", demand.getUuid());
                        //messageService.sendMessage("采购需求报价未中标",seller.getDealerId(), "bidding_not_confirm",model);
                        messageService.sendMessage(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0034),seller.getDealerId(),seller.getEMemberId(), "bidding_not_confirm",model);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

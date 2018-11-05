package com.sinochem.crude.trade.order.service.impl;

import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.order.constart.MsgConstart;
import com.sinochem.crude.trade.order.domain.Contract;
import com.sinochem.crude.trade.order.domain.ContractRelevanter;
import com.sinochem.crude.trade.order.service.ContractMessageService;
import com.sinochem.crude.trade.order.service.ContractRelevanterService;
import com.sinochem.crude.trade.order.service.ContractService;
import com.sinochem.crude.trade.order.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ContractMessageServiceImpl implements ContractMessageService {
    @Autowired
    private URLBroker orderServer;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private ContractRelevanterService contractRelevanterService;
    @Override
    public void applyCancelContract(Long contractId, Long epMemberId) {
        try{
            Contract contract = contractService.getContract(contractId);
            ContractRelevanter buyer =contractRelevanterService.query(contractId,"B");
            ContractRelevanter seller =contractRelevanterService.query(contractId,"S");
            if(contract != null && contract.getBuyer().compareTo(epMemberId) == 0){ //买家发起 发送给卖家
                Map<String, Object> model = new HashMap<String, Object>();
                model.put("pUserName",buyer.geteMemberName());
                model.put("hyperlink",orderServer.get("buyer/crudeOilOrderInfo.htm").put("uuid", contract.getUuid()).toString());
                model.put("orderNo", contract.getOrderNo());
                messageService.sendMessage(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0008),seller.getDealerId(),seller.getEMemberId(), "apply_cancel_contract",model);
            }
            if(contract != null && contract.getSeller().compareTo(epMemberId) == 0 ){ //卖家发起 发送给买家
                Map<String, Object> model = new HashMap<String, Object>();
                model.put("pUserName",seller.geteMemberName());
                model.put("hyperlink",orderServer.get("buyer/crudeOilOrderInfo.htm").put("uuid", contract.getUuid()).toString());
                model.put("orderNo", contract.getOrderNo());
                messageService.sendMessage(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0008),buyer.getDealerId(), buyer.getEMemberId(),"apply_cancel_contract",model);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void revokeCancelContract(Long contractId, Long epMemberId) {
        try{
            Contract contract = contractService.getContract(contractId);
            ContractRelevanter buyer =contractRelevanterService.query(contractId,"B");
            ContractRelevanter seller =contractRelevanterService.query(contractId,"S");
            if(contract != null && contract.getBuyer().compareTo(epMemberId) == 0){ //买家发起 发送给卖家
                Map<String, Object> model = new HashMap<String, Object>();
                model.put("pUserName",buyer.geteMemberName());
                model.put("hyperlink",orderServer.get("buyer/crudeOilOrderInfo.htm").put("uuid", contract.getUuid()).toString());
                model.put("orderNo", contract.getOrderNo());
                messageService.sendMessage(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0009),seller.getDealerId(), seller.getEMemberId(), "revoke_cancel_contract",model);
            }
            if(contract != null && contract.getSeller().compareTo(epMemberId) == 0){ //卖家发起 发送给买家
                Map<String, Object> model = new HashMap<String, Object>();
                model.put("pUserName",seller.geteMemberName());
                model.put("hyperlink",orderServer.get("buyer/crudeOilOrderInfo.htm").put("uuid", contract.getUuid()).toString());
                model.put("orderNo", contract.getOrderNo());
                messageService.sendMessage(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0009),buyer.getDealerId(), buyer.getEMemberId(),"revoke_cancel_contract",model);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void confirmCancelContract(Long contractId, boolean operStatus, Long epMemberId) {
        try{
            Contract contract = contractService.getContract(contractId);
            ContractRelevanter buyer =contractRelevanterService.query(contractId,"B");
            ContractRelevanter seller =contractRelevanterService.query(contractId,"S");
            String title = "";
            String tplName = "";
            if(operStatus){
                title = VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0010);
                tplName = "confirm_cancel_contract";
            }else{
                title = VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0011);
                tplName = "refuse_cancel_contract";
            }
            Long toMemberId = null;
            String eMemberName = "";
            Long toEpMemberId = null;
            if(contract != null && contract.getBuyer().compareTo(epMemberId) == 0){ //买家发起 发送给卖家
                toMemberId = seller.getDealerId();
                toEpMemberId = seller.getEMemberId();
                eMemberName = buyer.geteMemberName();
            }else{
                toMemberId = buyer.getDealerId();
                toEpMemberId = buyer.getEMemberId();
                eMemberName = seller.geteMemberName();
            }
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("pUserName",eMemberName);
            model.put("hyperlink",orderServer.get("buyer/crudeOilOrderInfo.htm").put("uuid", contract.getUuid()).toString());
            model.put("orderNo", contract.getOrderNo());
            messageService.sendMessage(title,toMemberId,toEpMemberId, tplName,model);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

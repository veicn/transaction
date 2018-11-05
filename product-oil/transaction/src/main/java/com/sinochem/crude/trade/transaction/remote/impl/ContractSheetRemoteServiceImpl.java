package com.sinochem.crude.trade.transaction.remote.impl;


import java.math.BigDecimal;

import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.model.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.transaction.domain.ContractSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.ContractSheet;
import com.sinochem.crude.trade.transaction.enums.ContractSheetStatusEnum;
import com.sinochem.crude.trade.transaction.remote.ContractSheetRemoteService;
import com.sinochem.crude.trade.transaction.service.ContractSheetService;
import com.sinochem.crude.trade.transaction.vo.ContractSheetRemoteVO;
import com.sinochem.it.b2b.common.exception.BizException;

@Service("contractSheetRemoteServiceImpl")
public class ContractSheetRemoteServiceImpl implements ContractSheetRemoteService {

    private final Logger LOGGER = LoggerFactory.getLogger(ContractSheetRemoteServiceImpl.class);

    @Autowired
    private ContractSheetService contractSheetService;


    @Override
    public ContractSheetRemoteVO getContractSheetByUuid(String uuid) throws BizException{

        ContractSheetRemoteVO contractSheetRemoteVO = new ContractSheetRemoteVO();

        if(!StringUtil.isEmpty(uuid)){

            ContractSheet contractSheet = contractSheetService.getContractSheetByUuid(null,uuid);
            ContractSheetCombine  contractSheetCombine = contractSheetService.getContractSheetCombine(null,contractSheet);
            ContractSheetCombineVO contractSheetCombineVO = new ContractSheetCombineVO(contractSheetCombine);
            ContractSheetVO contractSheetVO = contractSheetCombineVO.getContractSheetVO();
            if(contractSheetVO != null){
                String conId = contractSheetVO.getId();
                if (!StringUtil.isEmpty(conId)) {
                    Long orderId=Long.valueOf(conId);//做try catch
                    contractSheetRemoteVO.setOrderId(orderId);
                }
                String orderNumber=contractSheetVO.getSerialNumber();
                contractSheetRemoteVO.setOrderNumber(orderNumber);
            }

            SaleSheetVO saleSheetVO = contractSheetCombineVO.getSaleSheetVO();
            if(saleSheetVO != null){
                if(saleSheetVO.getProductSourceVO() !=null){
                    contractSheetRemoteVO.setProductSource(saleSheetVO.getProductSourceVO().getCode());
                }
            }

            ProductInfoVO productInfoVO = contractSheetCombineVO.getProductInfoVO();
            if (productInfoVO != null) {

                String tradeTerms = productInfoVO.getTradeTermVO().getEnName();
                contractSheetRemoteVO.setTradeTerms(tradeTerms);

                String productNm = productInfoVO.getProductCategoryVO().getEnName();
                contractSheetRemoteVO.setProdoctNm(productNm);

                String quantityStr = productInfoVO.getQuantityAsString();
                BigDecimal quantity = new BigDecimal(quantityStr);
                contractSheetRemoteVO.setQuantity(quantity);

                String tolerance = productInfoVO.getTolerance();
                if (tolerance != null && !StringUtil.isEmpty(tolerance)) {
                    Long rangeOfError=Long.valueOf(tolerance);//做try catch
                    contractSheetRemoteVO.setRangeOfError(rangeOfError);
                }
            }

            StakeHolderInfoVO buyerInfoVO = contractSheetCombineVO.getBuyerInfoVO();
            if (buyerInfoVO != null) {
                contractSheetRemoteVO.setBuyerId(buyerInfoVO.getEnterpriseId());
            }

            StakeHolderInfoVO salerInfoVO = contractSheetCombineVO.getSalerInfoVO();
            if (salerInfoVO != null) {
                contractSheetRemoteVO.setSellerId(salerInfoVO.getEnterpriseId());
            }

            TransportInfoVO transportInfoVO = contractSheetCombineVO.getTransportInfoVO();
            if (transportInfoVO != null) {

                String loadingPort = transportInfoVO.getLoadingPort();
                if (loadingPort != null) {
                    //String portOfLoading = loadingPortVO;
                    contractSheetRemoteVO.setPortOfLoading(loadingPort);
                }

                String dischargePort = transportInfoVO.getDischargePort();
                if (dischargePort != null) {
                    contractSheetRemoteVO.setPortOfDischarge(dischargePort);
                }

                String laycanStart = transportInfoVO.getLaycanStartDateAsString();
                contractSheetRemoteVO.setLaycanStart(laycanStart);

                String laycanEnd = transportInfoVO.getLaycanStartDateAsString();
                contractSheetRemoteVO.setLaycanEnd(laycanEnd);
            }
        }
        return contractSheetRemoteVO;
    }

    @Override
    public int completeContractSheet(Long id) {
        try {
            ContractSheet contractSheet = contractSheetService.getContractSheetById(null, id);
            contractSheetService.changeContractSheetStatus(null, contractSheet, ContractSheetStatusEnum.COMPLETED.getCode());

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);

            return 0;
        }
    }

    public ContractSheetRemoteVO getContractSheetById(Long id){
        ContractSheetRemoteVO contractSheetRemoteVO = new ContractSheetRemoteVO();
        try{
            if(id != null){
                ContractSheet contractSheet = contractSheetService.getContractSheetById(null,id);
                ContractSheetCombine  contractSheetCombine = contractSheetService.getContractSheetCombine(null,contractSheet);
                ContractSheetCombineVO contractSheetCombineVO = new ContractSheetCombineVO(contractSheetCombine);
                ContractSheetVO contractSheetVO = contractSheetCombineVO.getContractSheetVO();
                if(contractSheetVO != null){
                    String conId = contractSheetVO.getId();
                    if (!StringUtil.isEmpty(conId)) {
                        Long orderId=Long.valueOf(conId);//做try catch
                        contractSheetRemoteVO.setOrderId(orderId);
                    }
                    String uuid = contractSheetVO.getUuid();
                    if(!StringUtil.isEmpty(uuid)){
                        contractSheetRemoteVO.setUuid(uuid);
                    }
                    String orderNumber=contractSheetVO.getSerialNumber();
                    contractSheetRemoteVO.setOrderNumber(orderNumber);
                }

                SaleSheetVO saleSheetVO = contractSheetCombineVO.getSaleSheetVO();
                if(saleSheetVO != null){
                    if(saleSheetVO.getProductSourceVO() !=null){
                        contractSheetRemoteVO.setProductSource(saleSheetVO.getProductSourceVO().getCode());
                    }
                }

                ProductInfoVO productInfoVO = contractSheetCombineVO.getProductInfoVO();
                if (productInfoVO != null) {

                    String tradeTerms = productInfoVO.getTradeTermVO().getEnName();
                    contractSheetRemoteVO.setTradeTerms(tradeTerms);

                    String productNm = productInfoVO.getProductCategoryVO().getEnName();
                    contractSheetRemoteVO.setProdoctNm(productNm);

                    String quantityStr = productInfoVO.getQuantityAsString();
                    BigDecimal quantity = new BigDecimal(quantityStr);
                    contractSheetRemoteVO.setQuantity(quantity);

                    String tolerance = productInfoVO.getTolerance();
                    if (tolerance != null && !StringUtil.isEmpty(tolerance)) {
                        Long rangeOfError=Long.valueOf(tolerance);//做try catch
                        contractSheetRemoteVO.setRangeOfError(rangeOfError);
                    }
                }

                StakeHolderInfoVO buyerInfoVO = contractSheetCombineVO.getBuyerInfoVO();
                if (buyerInfoVO != null) {
                    contractSheetRemoteVO.setBuyerId(buyerInfoVO.getEnterpriseId());
                }

                StakeHolderInfoVO salerInfoVO = contractSheetCombineVO.getSalerInfoVO();
                if (salerInfoVO != null) {
                    contractSheetRemoteVO.setSellerId(salerInfoVO.getEnterpriseId());
                }

                TransportInfoVO transportInfoVO = contractSheetCombineVO.getTransportInfoVO();
                if (transportInfoVO != null) {

                    String loadingPort = transportInfoVO.getLoadingPort();
                    if (loadingPort != null) {
                        //String portOfLoading = loadingPortVO.getEnName();
                        contractSheetRemoteVO.setPortOfLoading(loadingPort);
                    }

                    String dischargePort = transportInfoVO.getDischargePort();
                    if (loadingPort != null) {
                        //String dischargePort = dischargePortVO.getEnName();
                        contractSheetRemoteVO.setPortOfDischarge(loadingPort);
                    }

                    String laycanStart = transportInfoVO.getLaycanStartDateAsString();
                    contractSheetRemoteVO.setLaycanStart(laycanStart);

                    String laycanEnd = transportInfoVO.getLaycanStartDateAsString();
                    contractSheetRemoteVO.setLaycanEnd(laycanEnd);
                }
            }
            return contractSheetRemoteVO;
        }catch(BizException e){
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }
}

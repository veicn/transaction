package com.sinochem.crude.trade.transaction.model.vo;

import com.sinochem.crude.trade.blockchain.domain.TBrokerAppoint;
import com.sinochem.crude.trade.blockchain.domain.TInspectorAppoint;
import com.sinochem.crude.trade.blockchain.domain.TShipagentAppoint;
import com.sinochem.crude.trade.common.base.BaseDomainVO;
import com.sinochem.crude.trade.transaction.domain.ContractSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.*;

/**
 * 组合各部分信息的合约单（订单）的VO
 * @author Yichen Zhao
 * date: 20180303
 */
public class ContractSheetCombineVO extends BaseDomainVO<ContractSheetCombine>{

    /**
     * 合约单（订单）信息
     */
    private ContractSheetVO contractSheetVO;

    /**
     * 销售单信息
     */
    private SaleSheetVO saleSheetVO;

    /**
     * 报价单信息
     */
    private BiddingSheetVO biddingSheetVO;

    /**
     * 商品信息
     */
    private ProductInfoVO productInfoVO;

    /**
     * 计价信息
     */
    private PricingInfoVO pricingInfoVO;

    /**
     * 运输信息
     */
    private TransportInfoVO transportInfoVO;

    /**
     * 卖家信息
     */
    private StakeHolderInfoVO salerInfoVO;

    /**
     * 买家信息
     */
    private StakeHolderInfoVO buyerInfoVO;

    /**
     * 其它信息
     */
    private OtherInfoVO otherInfoVO;


    private TBrokerAppoint tBrokerAppoint;
    private TShipagentAppoint tShipagentAppoint;
    private TInspectorAppoint tInspectorAppoint;

    public TBrokerAppoint gettBrokerAppoint() {
        return tBrokerAppoint;
    }

    public void settBrokerAppoint(TBrokerAppoint tBrokerAppoint) {
        this.tBrokerAppoint = tBrokerAppoint;
    }

    public TShipagentAppoint gettShipagentAppoint() {
        return tShipagentAppoint;
    }

    public void settShipagentAppoint(TShipagentAppoint tShipagentAppoint) {
        this.tShipagentAppoint = tShipagentAppoint;
    }

    public TInspectorAppoint gettInspectorAppoint() {
        return tInspectorAppoint;
    }

    public void settInspectorAppoint(TInspectorAppoint tInspectorAppoint) {
        this.tInspectorAppoint = tInspectorAppoint;
    }


    public ContractSheetCombineVO() {

    }

    public ContractSheetCombineVO(ContractSheetCombine domain) {
        super(domain);
    }

    @Override
    protected void convertToVO(ContractSheetCombine domain) {
        ContractSheet contractSheet = domain.getContractSheet();
        if (contractSheet != null) {
            this.contractSheetVO = new ContractSheetVO(contractSheet);
        }

        SaleSheet saleSheet = domain.getSaleSheet();
        if (saleSheet != null) {
            this.saleSheetVO = new SaleSheetVO(saleSheet);
        }

        BiddingSheet biddingSheet = domain.getBiddingSheet();
        if (biddingSheet != null) {
            this.biddingSheetVO = new BiddingSheetVO(biddingSheet);
        }

        ProductInfo productInfo = domain.getProductInfo();
        if (productInfo != null) {
            this.productInfoVO = new ProductInfoVO(productInfo);
        }

        PricingInfo pricingInfo = domain.getPricingInfo();
        if (pricingInfo != null) {
            this.pricingInfoVO = new PricingInfoVO(pricingInfo);
        }

        TransportInfo transportInfo = domain.getTransportInfo();
        if (transportInfo != null) {
            this.transportInfoVO = new TransportInfoVO(transportInfo);
        }

        StakeholderInfo salerInfo = domain.getSalerInfo();
        if (salerInfo != null) {
            this.salerInfoVO = new StakeHolderInfoVO(salerInfo);
        }

        StakeholderInfo buyerInfo = domain.getBuyerInfo();
        if (buyerInfo != null) {
            this.buyerInfoVO = new StakeHolderInfoVO(buyerInfo);
        }

        OtherInfo otherInfo = domain.getOtherInfo();
        if (otherInfo != null) {
            this.otherInfoVO = new OtherInfoVO(otherInfo);
        }

        this.tBrokerAppoint=domain.gettBrokerAppoint();
        this.tInspectorAppoint=domain.gettInspectorAppoint();
        this.tShipagentAppoint=domain.gettShipagentAppoint();
    }

    @Override
    protected ContractSheetCombine convertToDomain() {
        ContractSheetCombine contractSheetCombine = new ContractSheetCombine();

        if (this.contractSheetVO != null) {
            contractSheetCombine.setContractSheet(this.contractSheetVO.getDomain());
        }

        if (this.saleSheetVO != null) {
            contractSheetCombine.setSaleSheet(this.saleSheetVO.getDomain());
        }

        if (this.biddingSheetVO != null) {
            contractSheetCombine.setBiddingSheet(this.biddingSheetVO.getDomain());
        }

        if (this.productInfoVO != null) {
            contractSheetCombine.setProductInfo(this.productInfoVO.getDomain());
        }

        if (this.pricingInfoVO != null) {
            contractSheetCombine.setPricingInfo(this.pricingInfoVO.getDomain());
        }

        if (this.transportInfoVO != null) {
            contractSheetCombine.setTransportInfo(this.transportInfoVO.getDomain());
        }

        if (this.salerInfoVO != null) {
            contractSheetCombine.setSalerInfo(this.salerInfoVO.getDomain());
        }

        if (this.buyerInfoVO != null) {
            contractSheetCombine.setBuyerInfo(this.buyerInfoVO.getDomain());
        }

        if (this.otherInfoVO != null) {
            contractSheetCombine.setOtherInfo(this.otherInfoVO.getDomain());
        }

        return contractSheetCombine;
    }

    /** getters and setters */
    public ContractSheetVO getContractSheetVO() {
        return contractSheetVO;
    }

    public void setContractSheetVO(ContractSheetVO contractSheetVO) {
        this.contractSheetVO = contractSheetVO;
    }

    public SaleSheetVO getSaleSheetVO() {
        return saleSheetVO;
    }

    public void setSaleSheetVO(SaleSheetVO saleSheetVO) {
        this.saleSheetVO = saleSheetVO;
    }

    public BiddingSheetVO getBiddingSheetVO() {
        return biddingSheetVO;
    }

    public void setBiddingSheetVO(BiddingSheetVO biddingSheetVO) {
        this.biddingSheetVO = biddingSheetVO;
    }

    public ProductInfoVO getProductInfoVO() {
        return productInfoVO;
    }

    public void setProductInfoVO(ProductInfoVO productInfoVO) {
        this.productInfoVO = productInfoVO;
    }

    public PricingInfoVO getPricingInfoVO() {
        return pricingInfoVO;
    }

    public void setPricingInfoVO(PricingInfoVO pricingInfoVO) {
        this.pricingInfoVO = pricingInfoVO;
    }

    public TransportInfoVO getTransportInfoVO() {
        return transportInfoVO;
    }

    public void setTransportInfoVO(TransportInfoVO transportInfoVO) {
        this.transportInfoVO = transportInfoVO;
    }

    public StakeHolderInfoVO getSalerInfoVO() {
        return salerInfoVO;
    }

    public void setSalerInfoVO(StakeHolderInfoVO salerInfoVO) {
        this.salerInfoVO = salerInfoVO;
    }

    public StakeHolderInfoVO getBuyerInfoVO() {
        return buyerInfoVO;
    }

    public void setBuyerInfoVO(StakeHolderInfoVO buyerInfoVO) {
        this.buyerInfoVO = buyerInfoVO;
    }

    public OtherInfoVO getOtherInfoVO() {
        return otherInfoVO;
    }

    public void setOtherInfoVO(OtherInfoVO otherInfoVO) {
        this.otherInfoVO = otherInfoVO;
    }


}

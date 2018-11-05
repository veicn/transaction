package com.sinochem.crude.trade.transaction.model.vo;

import com.sinochem.crude.trade.common.base.BaseDomainVO;
import com.sinochem.crude.trade.transaction.domain.DemandSheetCombine;
import com.sinochem.crude.trade.transaction.domain.SaleSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.*;

/**
 * 组合各部分信息的销售单的VO
 * @author Yichen Zhao
 * date: 20180303
 */
public class DemandSheetCombineVO extends BaseDomainVO<DemandSheetCombine> {

    /**
     * 销售单信息
     */
    private DemandSheetVO demandSheetVO;

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
     * 买家信息
     */
    private StakeHolderInfoVO buyerInfoVO;

    /**
     * 其它信息
     */
    private OtherInfoVO otherInfoVO;


    public DemandSheetCombineVO() {

    }

    public DemandSheetCombineVO(DemandSheetCombine demandSheetCombine) {
        super(demandSheetCombine);
    }

    @Override
    protected void convertToVO(DemandSheetCombine domain) {
        DemandSheet demandSheet = domain.getDemandSheet();
        if (demandSheet != null) {
            this.demandSheetVO = new DemandSheetVO(demandSheet);
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

        StakeholderInfo buyerInfo = domain.getBuyerInfo();
        if (buyerInfo != null) {
            this.buyerInfoVO = new StakeHolderInfoVO(buyerInfo);
        }

        OtherInfo otherInfo = domain.getOtherInfo();
        if (otherInfo != null) {
            this.otherInfoVO = new OtherInfoVO(otherInfo);
        }
    }

    @Override
    protected DemandSheetCombine convertToDomain() {
        DemandSheetCombine demandSheetCombine = new DemandSheetCombine();

        if (this.demandSheetVO != null) {
            demandSheetCombine.setDemandSheet(this.demandSheetVO.getDomain());
        }

        if (this.productInfoVO != null) {
            demandSheetCombine.setProductInfo(this.productInfoVO.getDomain());
        }

        if (this.pricingInfoVO != null) {
            demandSheetCombine.setPricingInfo(this.pricingInfoVO.getDomain());
        }

        if (this.transportInfoVO != null) {
            demandSheetCombine.setTransportInfo(this.transportInfoVO.getDomain());
        }

        if (this.buyerInfoVO != null) {
            demandSheetCombine.setBuyerInfo(this.buyerInfoVO.getDomain());
        }

        if (this.otherInfoVO != null) {
            demandSheetCombine.setOtherInfo(this.otherInfoVO.getDomain());
        }

        return demandSheetCombine;
    }

    /** getters and setters */
    public DemandSheetVO getDemandSheetVO() {
        return demandSheetVO;
    }

    public void setDemandSheetVO(DemandSheetVO demandSheetVO) {
        this.demandSheetVO = demandSheetVO;
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

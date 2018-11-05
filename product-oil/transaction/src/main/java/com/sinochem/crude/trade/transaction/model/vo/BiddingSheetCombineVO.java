package com.sinochem.crude.trade.transaction.model.vo;

import com.sinochem.crude.trade.common.base.BaseDomainVO;
import com.sinochem.crude.trade.transaction.domain.BiddingSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.*;

/**
 * 组合各部分信息详情的报价单的VO
 * @author Yichen Zhao
 * date: 20180302
 */
public class BiddingSheetCombineVO extends BaseDomainVO<BiddingSheetCombine> {

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
     * 买家信息
     */
    private StakeHolderInfoVO buyerInfoVO;

    /**
     * 卖家信息
     */
    private StakeHolderInfoVO salerInfoVO;

    /**
     * 其它信息
     */
    private OtherInfoVO otherInfoVO;

    public BiddingSheetCombineVO() {

    }

    public BiddingSheetCombineVO(BiddingSheetCombine biddingSheetCombine) {
        super(biddingSheetCombine);
    }
    @Override
    protected void convertToVO(BiddingSheetCombine domain) {
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

        StakeholderInfo buyerInfo = domain.getBuyerInfo();
        if (buyerInfo != null) {
            this.buyerInfoVO = new StakeHolderInfoVO(buyerInfo);
        }

        StakeholderInfo salerInfoVO = domain.getSalerInfo();
        if (salerInfoVO != null) {
            this.salerInfoVO = new StakeHolderInfoVO(salerInfoVO);
        }

        OtherInfo otherInfo = domain.getOtherInfo();
        if (otherInfo != null) {
            this.otherInfoVO = new OtherInfoVO(otherInfo);
        }
    }

    @Override
    protected BiddingSheetCombine convertToDomain() {
        BiddingSheetCombine biddingSheetCombine = new BiddingSheetCombine();

        if (this.biddingSheetVO != null) {
            biddingSheetCombine.setBiddingSheet(this.biddingSheetVO.getDomain());
        }

        if (this.productInfoVO != null) {
            biddingSheetCombine.setProductInfo(this.productInfoVO.getDomain());
        }

        if (this.pricingInfoVO != null) {
            biddingSheetCombine.setPricingInfo(this.pricingInfoVO.getDomain());
        }

        if (this.transportInfoVO != null) {
            biddingSheetCombine.setTransportInfo(this.transportInfoVO.getDomain());
        }

        if (this.buyerInfoVO != null) {
            biddingSheetCombine.setBuyerInfo(this.buyerInfoVO.getDomain());
        }

        if (this.salerInfoVO != null) {
            biddingSheetCombine.setSalerInfo(this.salerInfoVO.getDomain());
        }

        if (this.otherInfoVO != null) {
            biddingSheetCombine.setOtherInfo(this.otherInfoVO.getDomain());
        }

        return biddingSheetCombine;
    }

    /** getters and setters */
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

    public StakeHolderInfoVO getSalerInfoVO() {
        return salerInfoVO;
    }

    public void setSalerInfoVO(StakeHolderInfoVO salerInfoVO) {
        this.salerInfoVO = salerInfoVO;
    }
}

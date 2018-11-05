package com.sinochem.crude.trade.order.model.result;

import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleIfStatement.Else;
import com.sinochem.crude.trade.common.enums.SerialNumberBizType;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.order.constart.Constart;
import com.sinochem.crude.trade.order.enums.EnumDoubleSignType;

import java.util.Date;

public class ContractListResult {
    /**
     * 主键
     */
    private Long contractId;
    /**
     * 创建时间
     */
    private Date createTime;
    private String uuid;
    //买方
    private Long initiator;
    //交易类型
    private String dealType;
    //原油有种
    private String crudeOilOptions;
    private String crudeOilOptionsName;
    //成品油种类
    private Integer productOilKind;
    private String productOilKindName;
    //成品油规格
    private Integer productOilSpecs;
    private String productOilSpecsName;
    //价格公式
    private String valuationFormula;
    //贴水
    private Long agio;
    /**
     * 数量
     */
    private Long num;
    /**
     * 数量 国际化
     */
    private Long enNum;

    public Long getEnNum() {
        return enNum;
    }

    public void setEnNum(Long enNum) {
        this.enNum = enNum;
    }

    /**
     * 贸易条款
     */
    private int tradeItem;
    //装港
    private String shipmentPort;
    //装货开始时间
    private Date shipmentStart;
    //装货结束时间
    private Date shipmentEnd;
    //卸货港
    private String dischargePort;
    //到货开始时间
    private Date dischargeStart;
    //到货结束时间
    private Date dischargeEnd;
    //计价期类型
    private Integer valuationProidType;
    //计价期开始时间
    private Date valuationProidStart;
    //计价期结束时间
    private Date valuationProidEnd;
    //合约类型
    private String contractType;

    /**
     * 付款条款
     */
    private String payItem;
    /**
     * 付款条款JSON
     */
    private String payItemJSON;

    public String getPayItem() {
        return payItem;
    }

    public void setPayItem(String payItem) {
        this.payItem = payItem;
    }

    public String getPayItemJSON() {
        return payItemJSON;
    }

    public void setPayItemJSON(String payItemJSON) {
        this.payItemJSON = payItemJSON;
    }

    //付款时间
    private Date payTime;
    //业务类型  B 买 S 卖
    private String bizType;
    /**
     * 订单状态
     */
    private Integer orderStatus;
    /**
     * 企业id
     */
    private Long memberId;
    /**
     * 企业name
     */
    private String memberName;
    //采购单id
    private Long biddingId;
    //买方id
    private Long buyer;

    private String buyerName;
    //卖方id
    private Long seller;

    private String sellerName;
    //
    private String orderNo;

    //中间商
    private Long trader;
    private Long executor;
    private String doubleStatus;
    private String doubleSign;
    private Long longNum;
    //取消标志
    private boolean cancelFlag;


    /**
     * 获取状态显示值
     * @return
     */
    public String getOrderStatusStr(){
        if(cancelFlag){
            return VisitorLocale.getByCurrentLanguage(Constart.ORDERSTATUS06);//"已取消";
        }else{
            if("L".equals(contractType)){
                if(EnumDoubleSignType.CONFIRM_CONTRACT.getCode().equals(doubleStatus) && !"11".equals(doubleSign)){
                    if("00".equals(doubleSign)){
                        return VisitorLocale.getByCurrentLanguage(Constart.ORDERSTATUS07);//"已保存";
                    }else{
                        return VisitorLocale.getByCurrentLanguage(Constart.ORDERSTATUS01);//"待确认"
                    }
                }else{
                    return VisitorLocale.getByCurrentLanguage(Constart.ORDERSTATUS02);//"已确认"
                }
            }else{
                if(EnumDoubleSignType.CANCEL_CONTRACT.getCode().equals(doubleStatus)){
                    return VisitorLocale.getByCurrentLanguage(Constart.ORDERSTATUS05);//"取消中";
                }else{
                    if(new Integer(2).equals(orderStatus)) {
                        return VisitorLocale.getByCurrentLanguage(Constart.ORDERSTATUS04);//"已完成";
                    } else if(new Integer(0).equals(orderStatus)) {
                        return VisitorLocale.getByCurrentLanguage(Constart.ORDERSTATUS02);//"未开始";
                    }else if(new Integer(-1).equals(orderStatus)) {
                        return VisitorLocale.getByCurrentLanguage(Constart.ORDERSTATUS01);//"待确认"
                    } else{
                        return VisitorLocale.getByCurrentLanguage(Constart.ORDERSTATUS03);//"执行中";
                    }
                }
            }

        }
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getInitiator() {
        return initiator;
    }

    public void setInitiator(Long initiator) {
        this.initiator = initiator;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getCrudeOilOptions() {
        return crudeOilOptions;
    }

    public void setCrudeOilOptions(String crudeOilOptions) {
        this.crudeOilOptions = crudeOilOptions;
    }

    public Integer getProductOilKind() {
        return productOilKind;
    }

    public void setProductOilKind(Integer productOilKind) {
        this.productOilKind = productOilKind;
    }

    public Integer getProductOilSpecs() {
        return productOilSpecs;
    }

    public void setProductOilSpecs(Integer productOilSpecs) {
        this.productOilSpecs = productOilSpecs;
    }

    public String getValuationFormula() {
        return valuationFormula;
    }

    public void setValuationFormula(String valuationFormula) {
        this.valuationFormula = valuationFormula;
    }

    public Long getAgio() {
        return agio;
    }

    public void setAgio(Long agio) {
        this.agio = agio;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public int getTradeItem() {
        return tradeItem;
    }

    public void setTradeItem(int tradeItem) {
        this.tradeItem = tradeItem;
    }

    public Date getShipmentStart() {
        return shipmentStart;
    }

    public void setShipmentStart(Date shipmentStart) {
        this.shipmentStart = shipmentStart;
    }

    public Date getShipmentEnd() {
        return shipmentEnd;
    }

    public void setShipmentEnd(Date shipmentEnd) {
        this.shipmentEnd = shipmentEnd;
    }

    public Date getDischargeStart() {
        return dischargeStart;
    }

    public void setDischargeStart(Date dischargeStart) {
        this.dischargeStart = dischargeStart;
    }

    public Date getDischargeEnd() {
        return dischargeEnd;
    }

    public void setDischargeEnd(Date dischargeEnd) {
        this.dischargeEnd = dischargeEnd;
    }

    public Integer getValuationProidType() {
        return valuationProidType;
    }

    public void setValuationProidType(Integer valuationProidType) {
        this.valuationProidType = valuationProidType;
    }

    public Date getValuationProidStart() {
        return valuationProidStart;
    }

    public void setValuationProidStart(Date valuationProidStart) {
        this.valuationProidStart = valuationProidStart;
    }

    public Date getValuationProidEnd() {
        return valuationProidEnd;
    }

    public void setValuationProidEnd(Date valuationProidEnd) {
        this.valuationProidEnd = valuationProidEnd;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getBiddingId() {
        return biddingId;
    }

    public void setBiddingId(Long biddingId) {
        this.biddingId = biddingId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Long getBuyer() {
        return buyer;
    }

    public void setBuyer(Long buyer) {
        this.buyer = buyer;
    }

    public Long getSeller() {
        return seller;
    }

    public void setSeller(Long seller) {
        this.seller = seller;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public Long getTrader() {
        return trader;
    }

    public void setTrader(Long trader) {
        this.trader = trader;
    }

    public Long getExecutor() {
        return executor;
    }

    public void setExecutor(Long executor) {
        this.executor = executor;
    }

    public String getDoubleStatus() {
        return doubleStatus;
    }

    public void setDoubleStatus(String doubleStatus) {
        this.doubleStatus = doubleStatus;
    }

    public String getDoubleSign() {
        return doubleSign;
    }

    public void setDoubleSign(String doubleSign) {
        this.doubleSign = doubleSign;
    }

    public Long getLongNum() {
        return longNum;
    }

    public void setLongNum(Long longNum) {
        this.longNum = longNum;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCrudeOilOptionsName() {
        return crudeOilOptionsName;
    }

    public void setCrudeOilOptionsName(String crudeOilOptionsName) {
        this.crudeOilOptionsName = crudeOilOptionsName;
    }

    public String getProductOilKindName() {
        return productOilKindName;
    }

    public void setProductOilKindName(String productOilKindName) {
        this.productOilKindName = productOilKindName;
    }

    public String getProductOilSpecsName() {
        return productOilSpecsName;
    }

    public void setProductOilSpecsName(String productOilSpecsName) {
        this.productOilSpecsName = productOilSpecsName;
    }

    public boolean isCancelFlag() {
        return cancelFlag;
    }

    public void setCancelFlag(boolean cancelFlag) {
        this.cancelFlag = cancelFlag;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getShipmentPort() {
        return shipmentPort;
    }

    public void setShipmentPort(String shipmentPort) {
        this.shipmentPort = shipmentPort;
    }

    public String getDischargePort() {
        return dischargePort;
    }

    public void setDischargePort(String dischargePort) {
        this.dischargePort = dischargePort;
    }
}
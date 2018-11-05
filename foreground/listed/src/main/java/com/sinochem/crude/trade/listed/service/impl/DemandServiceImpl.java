package com.sinochem.crude.trade.listed.service.impl;



import java.text.ParseException;
import java.util.*;

import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.listed.constant.MsgConstant;
import com.sinochem.crude.trade.listed.service.*;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.order.remote.*;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.eyeieye.melody.util.DateUtil;
import com.eyeieye.melody.util.StringUtil;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoService;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoVO;
import com.sinochem.crude.trade.listed.constant.Constant;
import com.sinochem.crude.trade.listed.dao.CrudeOilResourceMapper;
import com.sinochem.crude.trade.listed.dao.DemandJoinTableMapper;
import com.sinochem.crude.trade.listed.dao.DemandMapper;
import com.sinochem.crude.trade.listed.dao.DemandRelevanterMapper;
import com.sinochem.crude.trade.listed.dao.DemandShipBerthMapper;
import com.sinochem.crude.trade.listed.dao.DemandShipMapper;
import com.sinochem.crude.trade.listed.dao.DemandSpecifyEnterpriseMapper;
import com.sinochem.crude.trade.listed.domain.CrudeOil;
import com.sinochem.crude.trade.listed.domain.Demand;
import com.sinochem.crude.trade.listed.domain.DemandRelevanter;
import com.sinochem.crude.trade.listed.domain.DemandShip;
import com.sinochem.crude.trade.listed.domain.DemandShipBerth;
import com.sinochem.crude.trade.listed.domain.DemandSpecifyEnterprise;
import com.sinochem.crude.trade.listed.enums.BiddingStatus;
import com.sinochem.crude.trade.listed.enums.BizType;
import com.sinochem.crude.trade.listed.enums.CrudeOilDemandOrderProptertyType;
import com.sinochem.crude.trade.listed.enums.CrudeOilPublishRangeType;
import com.sinochem.crude.trade.listed.enums.CrudeOilQuantityType;
import com.sinochem.crude.trade.listed.enums.DataQueryOrderType;
import com.sinochem.crude.trade.listed.enums.DealType;
import com.sinochem.crude.trade.listed.enums.DemandBiddingType;
import com.sinochem.crude.trade.listed.enums.DemandRelevanterType;
import com.sinochem.crude.trade.listed.enums.DemandStatus;
import com.sinochem.crude.trade.listed.enums.DemandType;
import com.sinochem.crude.trade.listed.enums.SerialNumberBizType;
import com.sinochem.crude.trade.listed.helper.SerialNumberUtils;
import com.sinochem.crude.trade.listed.helper.ShareVOHelper;
import com.sinochem.crude.trade.listed.model.query.CrudeOilDemandQuery;
import com.sinochem.crude.trade.listed.model.query.ResourceQuery;
import com.sinochem.crude.trade.listed.model.result.DemandJoinResult;
import com.sinochem.crude.trade.listed.model.vo.CrudeOilDemandQueryVO;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.common.page.PageUtils;

/**
 * Created by sijliu on 15/11/2017.
 */
@Service
@Transactional
public class DemandServiceImpl implements DemandService {

    private Logger LOGGER = LoggerFactory.getLogger(DemandServiceImpl.class);

    @Autowired(required = false)
    private DemandMapper demandMapper;

    @Autowired
    private CrudeOilResourceMapper crudeOilResourceMapper;
    @Autowired
    private DemandRelevanterMapper demandRelevanterMapper;
    @Autowired
    private DemandShipMapper demandShipMapper;
    @Autowired
    private DemandShipBerthMapper demandShipBerthMapper;

    @Autowired
    private DemandJoinTableMapper demandJoinTableMapper;

    @Autowired(required = false)
    private DemandOrderService demandOrderService;

    @Autowired
    private DemandShipService demandShipService;

    @Autowired
    private DemandShipBerthService berthService;

    @Autowired
    private DemandRelevanterService relevanterService;

    @Autowired
    private CrudeOilInfoService crudeOilInfoService;

    @Autowired
    DemandSpecifyEnterpriseMapper specifyEnterpriseMapper;

    @Autowired
    private DemandMessageService demandMessageService;

    @Autowired
    private EnterpriseService enterpriseService;

    @Override
    public Long saveOrUpdateDemand(Demand record) throws BizException{

        // 需求id为空的情况下
        if (record.getId() == null) {
            demandMapper.insertSelective(record);
        } else {
            demandMapper.updateByPrimaryKeySelective(record);

        }

        return record.getId();
    }

    @Override
    public Demand getDemandByKey(Long id) {
        return demandMapper.selectByPrimaryKey(id);
    }

    @Override
    public Demand getDemandByUuid(String uuid) {
        return demandMapper.selectByUuid(uuid);
    }

    @Override
    public List<Demand> getDemandsByParentId(Long parentId) {
        return demandMapper.selectDemandsByParentId(parentId);
    }

	@Override
	public List<Demand> getDemandsByIds(List<Long> ids) {
		// TODO Auto-generated method stub
		return demandMapper.getDemandsByIds(ids);
	}



    @Override
    public String confirmActualBiddingAndRemind(Long demandBiddingId, String remindFlag, CurrentUser user) throws Exception {

        boolean flag = true;
        UserVo userVo = ShareVOHelper.convertToUserVO(user);
        // 调用订单生成接口
        DemandOrderReturnVO returnVO = generateOrder(demandBiddingId, userVo);

        String orderNo = "";
        // 订单生成成功
        if (flag && returnVO != null && StringUtils.isNotEmpty(returnVO.getOrderNo())) {
            orderNo = returnVO.getOrderNo();
            // 2、根据需求报价id，修改对应的报价单及需求单状态
            Demand biddingDemand = demandMapper.selectByPrimaryKey(demandBiddingId);
            if (biddingDemand != null) {
                // 修改为中标状态
                biddingDemand.setStatus(BiddingStatus.DEMAND_STATUS_20.getCode());
                // 更新
                demandMapper.updateByPrimaryKeySelective(biddingDemand);
                //消息发送
                demandMessageService.confirmBidding(biddingDemand.getId(),returnVO.getOrderNo(),returnVO.getUuid(),true);
                // 3、根据remindFlag, 判断是否提醒未中标的供应商
                // ZJ 添加，根据该功能判断是否该需求单已完成
                if (StringUtils.isNotEmpty(remindFlag)) {
                    // 更新报价单对应的需求单状态3：完成
                    Demand d = new Demand();
                    d.setId(biddingDemand.getParentId());
                    d.setStatus(DemandStatus.DEMAND_STATUS_3.getCode());
                    demandMapper.updateByPrimaryKeySelective(d);
                    // 更新需求单下其他报价单状态为30：结束
                    List<Demand> list = demandMapper.selectDemandsByParentId(biddingDemand.getParentId());
                    for (Demand demand : list) {
                        //将10-已报价状态的报价单改为结束
                        if (demand != null && demand.getStatus() == BiddingStatus.DEMAND_STATUS_10.getCode()) {
                            demand.setStatus(BiddingStatus.DEMAND_STATUS_30.getCode());
                            demandMapper.updateByPrimaryKey(demand);
                            //消息发送
                            demandMessageService.confirmBidding(demand.getId(),"","",false);
                        }
                    }
                }
            }
        }
        //
        return orderNo;
    }

    @Override
    public DemandOrderReturnVO generateOrder(Long demandId, UserVo userVo) throws Exception {
        // 1、调用list-share中的接口生成订单
        DemandOrderVo demandOrderVo;
        // 1.1 根据demandBiddingId获取报价单信息
        Demand bdemand = demandMapper.selectByPrimaryKeyLock(demandId);
        if (bdemand == null) {
            LOGGER.error("ID为"+ demandId +"的报价单未找到！");
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0112));
        }
        Date stopBidTime = bdemand.getStopBidTime();
        if (stopBidTime != null) {
            try {
                if (DateUtil.getToday().getTime().after(stopBidTime)) {
                    throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0113));
                }
            } catch (ParseException pe) {
                throw pe;
            }
        }

        if (bdemand.getStatus() != null) {
            if (BiddingStatus.DEMAND_STATUS_20.getCode().equals(bdemand.getStatus().intValue())) {
                throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0114));
            } else if (BiddingStatus.DEMAND_STATUS_30.getCode().equals(bdemand.getStatus().intValue())) {
                throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0115));
            }
        }

        demandOrderVo = ShareVOHelper.convertToDemandOrderVO(bdemand);


        if (BizType.CRUDE_OIL.getCode().equals(bdemand.getBizType())) {
            List<CrudeOil> crudeOils = crudeOilResourceMapper.getCrudeOilListByDemandId(bdemand.getId());
            if (CollectionUtils.isNotEmpty(crudeOils)) {
                // 设置原油信息
                CrudeOil crudeOil = crudeOils.get(0);
                CrudeOilResource crudeOilResourceVO = crudeOil.convertToCrudeOilResourceVO();
                demandOrderVo.setCrudeOilResource(crudeOilResourceVO);
            }

        }

        DemandRelevanter buyer = demandRelevanterMapper.getDemandRelevantersByDemandIdAndType(bdemand.getId(), DemandRelevanterType.BUYER.getCode());
        if (buyer != null) {
            // 设置买方信息
            RelevanterVO buyerVO = ShareVOHelper.convertToRelevanterVO(buyer);
            demandOrderVo.setBuyer(buyerVO);
        }

        DemandRelevanter trader = demandRelevanterMapper.getDemandRelevantersByDemandIdAndType(bdemand.getId(), DemandRelevanterType.AGENT.getCode());
        if (trader != null) {
            // 设置代理商、贸易商信息
            RelevanterVO traderVO = ShareVOHelper.convertToRelevanterVO(trader);
            demandOrderVo.setTrader(traderVO);
        }

        DemandRelevanter provider = demandRelevanterMapper.getDemandRelevantersByDemandIdAndType(bdemand.getId(), DemandRelevanterType.SUPPLIER.getCode());
        if (provider != null) {
            // 设置供应商信息
            RelevanterVO providerVO = ShareVOHelper.convertToRelevanterVO(provider);
            demandOrderVo.setProvider(providerVO);
        }

        List<DemandShip> demandShips = demandShipMapper.selectByDemandId(bdemand.getId());
        if (CollectionUtils.isNotEmpty(demandShips)) {
            // 设置船务运输信息
            Transport transport = ShareVOHelper.convertToTransportVO(demandShips.get(0));
            demandOrderVo.setTransport(transport);
        }

        List<DemandShipBerth> shipBerths = demandShipBerthMapper.selectByDemandId(bdemand.getId());
        if (CollectionUtils.isNotEmpty(shipBerths)) {
            for(int i=0; i < shipBerths.size(); i++ ) {
                // 设置泊位信息
                ShipBerth shipBerth = ShareVOHelper.convertToShipBerthVO(shipBerths.get(i));
                demandOrderVo.shipBerth.add(shipBerth);
            }
        }

        return demandOrderService.createOrderByDemandData(demandOrderVo, userVo);
    }

    /**
     * 确认意向报价
     * @param demandId
     * @param user
     * @return
     * @throws BizException
     */
    @Override
    public Long confirmIntentionBiddingAndRemind(Long demandId, CurrentUser user) throws BizException {
        /** 参数校验 */
        if (demandId == null) {
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0035));
        }

        Demand intentionBiddingDemand = demandMapper.selectByPrimaryKey(demandId);
        if (intentionBiddingDemand == null || intentionBiddingDemand.getParentId() == null) {
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0116));
        }

        if (!DemandBiddingType.INTENTION_BIDDING.getCode().equals(intentionBiddingDemand.getBiddingType())) {
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0117));
        }

        /** 权限校验 */
        if (user.getEpMemberId() != null && user.getEpMemberId().equals(intentionBiddingDemand.getCreater())) {
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0118));
        }

        Long parentId = intentionBiddingDemand.getParentId();

        Demand parentDemand = demandMapper.selectByPrimaryKey(parentId);
        if (parentDemand == null) {
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0007));
        }

        if (user.getEpMemberId() == null || !user.getEpMemberId().equals(parentDemand.getCreater())) {
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0120));
        }

        //意向报价单设置为结束
        intentionBiddingDemand.setStatus(BiddingStatus.DEMAND_STATUS_20.getCode());
        demandMapper.updateByPrimaryKeySelective(intentionBiddingDemand);

        //发送消息
        demandMessageService.confirmBidding(intentionBiddingDemand.getId(),"","",true);

        return intentionBiddingDemand.getParentId();
    }

    @Override
    public List<Demand> getDemandListByIds(String contrastIds) {
        if (StringUtils.isNotEmpty(contrastIds)) {
            String[] contrastIdArr = contrastIds.split(",");
            List<Long> idList = new ArrayList<>();
            for (String str : contrastIdArr) {
                idList.add(Long.valueOf(str));
            }
            return demandMapper.getDemandsByIds(idList);
        }
        return null;
    }

    @Override
    public void copyProperties(Demand desc, Demand source) {
        desc.setTradeItem(source.getTradeItem());
        desc.setValuationBase(source.getValuationBase());
        desc.setPayTime(source.getPayTime());
        desc.setPurchaseMode(source.getPurchaseMode());
        desc.setType(source.getType());
        desc.setDealType(source.getDealType());
        desc.setBizType(source.getBizType());
        desc.setProductOilId(source.getProductOilId());
        desc.setProductOilClassify(source.getProductOilClassify());
        desc.setProductOilKind(source.getProductOilKind());
        desc.setProductOilSpecs(source.getProductOilSpecs());
        desc.setOtherItem(source.getOtherItem());
        desc.setAuthItem(source.getAuthItem());
        desc.setBusinessCheckOrg(source.getBusinessCheckOrg());
		desc.setTenderOutTime(source.getTenderOutTime());
		desc.setStopBidTime(source.getStopBidTime());
		desc.setResponseStartTime(source.getResponseStartTime());
		desc.setResponseEndTime(source.getResponseEndTime());
		desc.setCrudeOilOptions(source.getCrudeOilOptions());
		desc.setIsAgent(source.getIsAgent());
		desc.setAgenter(source.getAgenter());
		desc.setExportType(source.getExportType());
		desc.setIsSeal(source.getIsSeal());
		desc.setPurchaseMode(source.getPurchaseMode());
		desc.setMeasureMode(source.getMeasureMode());
    }

    @Override
    public Long tender(Demand demand, Long oper, DemandShip ship, String shipBerthIds, DemandRelevanter buyer) throws Exception {

		Long parentId = demand.getParentId();
		List<Demand> dlist = this.getDemandsByParentId(parentId);
        Demand bidding = null;
        for(Demand b : dlist){
            if (oper.equals(b.getCreater())) {
                bidding = b;
                break;
            }
        }

        // 如果当前用户已经报价
        if (bidding != null) {
            // 删除已报价的油种信息
            crudeOilResourceMapper.deleteByDemandId(bidding.getId());
            // 删除已报价的泊位信息
            demandShipBerthMapper.deleteByDemandId(bidding.getId());
            // 删除船务信息
            demandShipMapper.deleteByDemandId(bidding.getId());
            // 删除供应商信息
            demandRelevanterMapper.deleteByDemandId(bidding.getId());
            // 删除原有报价
            demandMapper.deleteByPrimaryKey(bidding.getId());
        }
        Demand parentDemand = this.getDemandByKey(parentId);
        if(parentDemand == null) {
        	throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0121));
        }

        Date stopBidTime = parentDemand.getStopBidTime();
        if (stopBidTime != null) {
            try {
                if (stopBidTime.before(DateUtil.getToday().getTime())) {
                    throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0122));
                }
            } catch (ParseException pe) {
                throw pe;
            }
        }

        this.copyProperties(demand, parentDemand);
        if (StringUtils.isEmpty(demand.getValuationFormulaJson())) {
            demand.setValuationFormulaJson(parentDemand.getValuationFormulaJson());
            demand.setValuationFormula(parentDemand.getValuationFormula());
        }

        //保存demand
        demand.setId(null);
        demand.setParentId(parentId);

    	// 生成报价单短号
    	demand.setUuid(SerialNumberUtils.getSerialNumber12Len(SerialNumberBizType.ProductOilQuote, new Date()));
    	demand.setStatus(Demand.STATUS_QUOTE_10);
    	demand.setPubDate(new Date());
    	demand.setCreater(oper);
    	demand.setCreateTime(null);

    	demand.setNum(null);
    	demand.setAgio(null);

    	demand.setType(DemandType.BIDDING.getCode());
    	demand.setDealType(DealType.BUY.getCode());
    	demand.setBizType(Constant.BIZ_TYPE_PRODUCTOIL);
    	
    	Long demandId = this.saveOrUpdateDemand(demand);

    	List<DemandShip> ships = demandShipMapper.selectByDemandId(demand.getParentId());
    	DemandShip ds = null;
    	if (CollectionUtils.isNotEmpty(ships)) {
    	    ds = ships.get(0);
    	    // 卸货港
            if (StringUtils.isEmpty(ship.getDischargePort())) {
                ship.setDischargePort(ds.getDischargePort());
            }
            // 装货港
            if (StringUtils.isEmpty(ship.getShipmentPort())) {
                ship.setShipmentPort(ds.getShipmentPort());
            }
            if (ship.getDischargeStartTime() == null) {
                ship.setDischargeStartTime(ds.getDischargeStartTime());
            }
            if (ship.getDischargeEndTime() == null) {
                ship.setDischargeEndTime(ds.getDischargeEndTime());
            }
            if (ship.getShipmentStartTime() == null) {
                ship.setShipmentStartTime(ds.getShipmentStartTime());
            }
            if (ship.getShipmentEndTime() == null) {
                ship.setShipmentEndTime(ds.getShipmentEndTime());
            }
        }
        ship.setId(null);
        ship.setDemandId(demandId);

        this.demandShipService.saveOrUpdateDemandShip(ship);
        this.berthService.copySaveShipBerthByDemandIdAndBerthIds(demand.getParentId(), demandId, shipBerthIds);
        this.relevanterService.copySaveRelevanterByDemandId(demand.getParentId(), demandId, buyer);

    	return demandId;
	}

    /**
     * 查询所有发布信息条数
     * @return
     */
    @Override
    public Long getDemandCount() {
        return demandMapper.getDemandCount();
    }

    @Override
    public PageInfoResult queryDemandJoinTableByCondition(ResourceQuery resourceQuery,PageInfo pageInfo) {
        if (pageInfo != null) {
            PageUtils.page(pageInfo);
        }
        List<DemandJoinResult> list = demandJoinTableMapper.selectDemandByCondition(resourceQuery);
        List<Long> epMemberIds = new ArrayList<>();
        for(DemandJoinResult result : list){
            epMemberIds.add(result.getPeMemberId());
            epMemberIds.add(result.getBeMemberId());
        }
        Map<Long, String> nameMap = enterpriseService.getEnterpriseName(epMemberIds.toArray(new Long[]{}),VisitorLocale.getCurrent());
        for(DemandJoinResult result : list){
            result.setbEnterpriseName(StringUtil.isNotEmpty(nameMap.get(result.getBeMemberId()))?nameMap.get(result.getBeMemberId()):result.getbEnterpriseName());
            result.setpEnterpriseName(StringUtil.isNotEmpty(nameMap.get(result.getPeMemberId()))?nameMap.get(result.getPeMemberId()):result.getpEnterpriseName());
        }
        return new PageInfoResult(list);
    }

    /**
     * 查找一条已过期的数据   -   建议删除
     * @param resourceQuery 查询条件
     * @param pageInfo 分页信息
     * @return
     */
    @Override
    public PageInfoResult queryInvalidDemandJoinTableByCondition(ResourceQuery resourceQuery,PageInfo pageInfo) {
        if (pageInfo != null) {
            PageUtils.page(pageInfo);
        }
        List<DemandJoinResult> list = demandJoinTableMapper.selectInvalidDemandByCondition(resourceQuery);
        List<Long> epMemberIds = new ArrayList<>();
        for(DemandJoinResult result : list){
            epMemberIds.add(result.getPeMemberId());
            epMemberIds.add(result.getBeMemberId());
        }
        Map<Long, String> nameMap = enterpriseService.getEnterpriseName(epMemberIds.toArray(new Long[]{}),VisitorLocale.getCurrent());
        for(DemandJoinResult result : list){
            result.setbEnterpriseName(StringUtil.isNotEmpty(nameMap.get(result.getBeMemberId()))?nameMap.get(result.getBeMemberId()):result.getbEnterpriseName());
            result.setpEnterpriseName(StringUtil.isNotEmpty(nameMap.get(result.getPeMemberId()))?nameMap.get(result.getPeMemberId()):result.getpEnterpriseName());
        }
        return new PageInfoResult(list);
    }

    @Override
    public PageInfoResult queryDemandBiddingJoinTableByCondition(ResourceQuery resourceQuery, PageInfo pageInfo) {
        if (pageInfo != null) {
            PageUtils.page(pageInfo);
        }
        List<DemandJoinResult> list = demandJoinTableMapper.selectBiddingByCondition(resourceQuery);
        List<Long> epMemberIds = new ArrayList<>();
        for(DemandJoinResult result : list){
            epMemberIds.add(result.getPeMemberId());
            epMemberIds.add(result.getBeMemberId());
        }
        Map<Long, String> nameMap = enterpriseService.getEnterpriseName(epMemberIds.toArray(new Long[]{}),VisitorLocale.getCurrent());
        for(DemandJoinResult result : list){
            result.setbEnterpriseName(StringUtil.isNotEmpty(nameMap.get(result.getBeMemberId()))?nameMap.get(result.getBeMemberId()):result.getbEnterpriseName());
            result.setpEnterpriseName(StringUtil.isNotEmpty(nameMap.get(result.getPeMemberId()))?nameMap.get(result.getPeMemberId()):result.getpEnterpriseName());
        }
        return new PageInfoResult(list);
    }

    @Override
    public List<DemandJoinResult> getTop5BiddingDatas(ResourceQuery resourceQuery) {
        return demandJoinTableMapper.selectBiddingFiveByCondition(resourceQuery);
    }

	@Override
	public List<DemandJoinResult> getCrudeOilCompareByIds(List<Long> ids) {
		// TODO Auto-generated method stub
		return demandJoinTableMapper.getCrudeOilCompareByIds(ids);
	}

    @Override
    public Long countMyDemandNumByCondition(ResourceQuery resourceQuery) {
        return demandJoinTableMapper.countDemandNumByCondition(resourceQuery);
    }

    @Override
    public Long countMyDemandNumByUserEpId(ResourceQuery resourceQuery) {
        return demandJoinTableMapper.countDemandByUserEpId(resourceQuery);
    }

    /**
     * 前台原油筛选页面
     *
     * @param crudeOilDemandQueryVO
     * @param pageInfo
     * @return
     */
    @Override
    public PageInfoResult queryCrudeOilDemandByCondition(CrudeOilDemandQueryVO crudeOilDemandQueryVO, PageInfo pageInfo) {
        if (pageInfo != null) {
            PageUtils.page(pageInfo);
        }

        CrudeOilDemandQuery crudeOilDemandQuery = new CrudeOilDemandQuery();
        
        crudeOilDemandQuery.setType(crudeOilDemandQueryVO.getType());
        crudeOilDemandQuery.setDealType(crudeOilDemandQueryVO.getDealType());
        
        if(!org.apache.commons.lang.StringUtils.isEmpty(crudeOilDemandQueryVO.getPurchaseType())){
            crudeOilDemandQuery.setPurchaseType(crudeOilDemandQueryVO.getPurchaseType());
        }
        if(!StringUtils.isEmpty(crudeOilDemandQueryVO.getCrudePlace()) ){
            crudeOilDemandQuery.setCrudePlace(crudeOilDemandQueryVO.getCrudePlace());
        }

        String oilNumber = crudeOilDemandQueryVO.getOilNumber();
        if (!StringUtil.isEmpty(oilNumber)) {
            if (CrudeOilQuantityType.BELOW_FIVE_HANDRED_THOUSAND.getCode().equals(oilNumber)) {
                crudeOilDemandQuery.setOilNumberMin(CrudeOilQuantityType.BELOW_FIVE_HANDRED_THOUSAND.getMin());
                crudeOilDemandQuery.setOilNumberMax(CrudeOilQuantityType.BELOW_FIVE_HANDRED_THOUSAND.getMax());
            } else if (CrudeOilQuantityType.FIVE_TO_TEN_HANDRED_THOUSAND.getCode().equals(oilNumber)) {
                crudeOilDemandQuery.setOilNumberMin(CrudeOilQuantityType.FIVE_TO_TEN_HANDRED_THOUSAND.getMin());
                crudeOilDemandQuery.setOilNumberMax(CrudeOilQuantityType.FIVE_TO_TEN_HANDRED_THOUSAND.getMax());
            } else if (CrudeOilQuantityType.TEN_TO_TWENTY_HANDRED_THOUSAND.getCode().equals(oilNumber)) {
                crudeOilDemandQuery.setOilNumberMin(CrudeOilQuantityType.TEN_TO_TWENTY_HANDRED_THOUSAND.getMin());
                crudeOilDemandQuery.setOilNumberMax(CrudeOilQuantityType.TEN_TO_TWENTY_HANDRED_THOUSAND.getMax());
            } else if (CrudeOilQuantityType.ABOVE_TWENTY_HANDRED_THOUSAND.getCode().equals(oilNumber)) {
                crudeOilDemandQuery.setOilNumberMin(CrudeOilQuantityType.ABOVE_TWENTY_HANDRED_THOUSAND.getMin());
                crudeOilDemandQuery.setOilNumberMax(CrudeOilQuantityType.ABOVE_TWENTY_HANDRED_THOUSAND.getMax());
            }
        }
        if (StringUtil.isNotEmpty(crudeOilDemandQueryVO.getOilType())) {
            CrudeOilInfoVO coivo = crudeOilInfoService.findCrudeOilInfoById(Long.valueOf(crudeOilDemandQueryVO.getOilType()));
            if (coivo == null ) {
                crudeOilDemandQuery.setOilType(null);
            } else {
                crudeOilDemandQuery.setOilType(coivo.getCrudeNameE());
            }
        }
        crudeOilDemandQuery.setEpMemberId(crudeOilDemandQueryVO.getEpMemberId());
        if (crudeOilDemandQueryVO.getSpecified() != null) {
            if (crudeOilDemandQueryVO.getSpecified() == 0) {
                crudeOilDemandQuery.setSpecified(0);
            } else if (crudeOilDemandQueryVO.getSpecified() == 1) {
                crudeOilDemandQuery.setSpecified(1);
            }
        }

        crudeOilDemandQuery.setPublishType(crudeOilDemandQueryVO.getPublishType());
        crudeOilDemandQuery.setEnterpriseId(crudeOilDemandQueryVO.getEnterprise());

        //默认按照发布时期降序排序
        String desc = DataQueryOrderType.DESC.getName();
        String keys = CrudeOilDemandOrderProptertyType.PUB_DATE.getName();

        if(crudeOilDemandQueryVO.getOrderByKeys() != null){
            if(crudeOilDemandQueryVO.getSort() != null){
                if(crudeOilDemandQueryVO.getSort().equals(DataQueryOrderType.ASC.getCode())){
                    desc = DataQueryOrderType.ASC.getName();
                }
            }

            String orderByKeys = crudeOilDemandQueryVO.getOrderByKeys();
            if (CrudeOilDemandOrderProptertyType.NUM.getCode().equals(orderByKeys)) {
                keys = CrudeOilDemandOrderProptertyType.NUM.getName();
            } else if (CrudeOilDemandOrderProptertyType.PUB_DATE.getCode().equals(orderByKeys)) {
                keys = CrudeOilDemandOrderProptertyType.PUB_DATE.getName();
            } else if (CrudeOilDemandOrderProptertyType.DISCHARGE_START_TIME.getCode().equals(orderByKeys)) {
                keys = CrudeOilDemandOrderProptertyType.DISCHARGE_START_TIME.getName();
            } else if (CrudeOilDemandOrderProptertyType.STOP_BID_TIME.getCode().equals(orderByKeys)) {
                keys = CrudeOilDemandOrderProptertyType.STOP_BID_TIME.getName();
            }
        }else{
            keys = CrudeOilDemandOrderProptertyType.PUB_DATE.getName();
        }
        crudeOilDemandQuery.setOrderByKeys(keys + " " + desc);

        List<DemandJoinResult> crudeOilDemandList = demandJoinTableMapper.queryCrudeOilDemandByCondition(crudeOilDemandQuery);
        List<Long> epMemberIds = new ArrayList<>();
        for(DemandJoinResult result : crudeOilDemandList){
            epMemberIds.add(result.getPeMemberId());
            epMemberIds.add(result.getBeMemberId());
        }
        Map<Long, String> nameMap = enterpriseService.getEnterpriseName(epMemberIds.toArray(new Long[]{}),VisitorLocale.getCurrent());
        for(DemandJoinResult result : crudeOilDemandList){
            result.setbEnterpriseName(StringUtil.isNotEmpty(nameMap.get(result.getBeMemberId()))?nameMap.get(result.getBeMemberId()):result.getbEnterpriseName());
            result.setpEnterpriseName(StringUtil.isNotEmpty(nameMap.get(result.getPeMemberId()))?nameMap.get(result.getPeMemberId()):result.getpEnterpriseName());
        }
        return new PageInfoResult(crudeOilDemandList);
    }


    @Override
    public Demand getDemandByKeyAndCurrentUser(Long demandId, Long epMemberId) throws BizException {
        Demand demand = demandMapper.selectByPrimaryKey(demandId);
        if (demand == null)
            throw  new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0123));
        else {
            if (CrudeOilPublishRangeType.SPECIFY_PUBLISH.getCode() == demand.getSpecified()) {
                List<DemandSpecifyEnterprise> specifyList = specifyEnterpriseMapper.selectObjByDemandId(demandId);
                boolean flag = false;
                for(DemandSpecifyEnterprise specify : specifyList) {
                    if (epMemberId.equals(specify.getEpMemberId()) || epMemberId.equals(demand.getCreater())) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0124));
                }
            }
        }
        return demand;
    }

    @Override
    public Result postpone(String demandId, String stopBidTime) throws BizException {
        Result result = new Result();
        if (StringUtil.isEmpty(demandId) || StringUtil.isEmpty(stopBidTime)) {
            result.setCode("ERROR");
            result.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0125));
            return result;
        } else {
            Demand demand = demandMapper.selectByPrimaryKey(Long.valueOf(demandId));
            if (demand == null) {
                result.setCode("ERROR");
                result.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0126));
                return result;
            } else {

                try {
                    Date stopBidDate = DateUtil.convertStringToDate("yyyy-MM-dd", stopBidTime);
                    if (demand.getTenderOutTime() != null) {
                        if (stopBidDate.after(demand.getTenderOutTime())) {
                            result.setCode("ERROR");
                            result.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0127));
                            return result;
                        }
                    }
                    if (demand.getStopBidTime() != null) {
                        if (stopBidDate.before(demand.getStopBidTime())) {
                            result.setCode("ERROR");
                            result.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0128));
                            return result;
                        }
                    }

                    // 已经逾期的需求，如果存在中标报价的需求则该需求不能延期
                    if (new Date().after(demand.getStopBidTime())) {
                        List<Demand> biddings = demandMapper.selectDemandsByParentId(demand.getId());
                        boolean flag = false;
                        if (CollectionUtils.isNotEmpty(biddings)) {
                            for (Demand bid : biddings) {
                                if (BiddingStatus.DEMAND_STATUS_20.getCode() == bid.getStatus()) {
                                    flag = true;
                                    break;
                                }
                            }
                        }
                        if (flag) {
                            result.setCode("ERROR");
                            result.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0129));
                            return result;
                        }
                    }

                    demand.setStopBidTime(stopBidDate);
                    demand.setUpdaterTime(new Date());
                    demandMapper.updateByPrimaryKeySelective(demand);

                    result.setCode("SUCCESS");
                } catch (ParseException e) {
                    result.setCode("ERROR");
                    result.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0130));
                    LOGGER.error(e.getMessage());
                }

            }
        }
        return result;
    }

    /**
     * 判断当前报价单是否已结标
     * @param demandId 报价单id
     * @return 1已结标 0未结标
     */
	@Override
	public Integer checkStopBid(Long demandId) throws BizException {
		Integer ret = demandMapper.checkStopBid(demandId);
		if(ret == null) {
			ret = 1;
		}
		return ret;
	}
}

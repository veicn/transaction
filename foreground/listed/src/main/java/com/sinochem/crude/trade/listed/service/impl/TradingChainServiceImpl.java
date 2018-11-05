package com.sinochem.crude.trade.listed.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.listed.constant.Constant;
import com.sinochem.crude.trade.listed.constant.MsgConstant;
import com.sinochem.crude.trade.listed.dao.*;
import com.sinochem.crude.trade.listed.domain.*;
import com.sinochem.crude.trade.listed.enums.*;
import com.sinochem.crude.trade.listed.helper.DateUtil;
import com.sinochem.crude.trade.listed.helper.DictUtils;
import com.sinochem.crude.trade.listed.helper.SerialNumberUtils;
import com.sinochem.crude.trade.listed.helper.ShareVOHelper;
import com.sinochem.crude.trade.listed.model.form.TradeChainForm;
import com.sinochem.crude.trade.listed.model.query.TradeChainQuery;
import com.sinochem.crude.trade.listed.model.vo.DemandRelevanterBuyerVO;
import com.sinochem.crude.trade.listed.model.vo.DemandRelevanterSupplierVO;
import com.sinochem.crude.trade.listed.model.vo.DemandVO;
import com.sinochem.crude.trade.listed.model.vo.tradingChainVo.EnterpriseNameByTradingChainVO;
import com.sinochem.crude.trade.listed.model.vo.tradingChainVo.TradeChainDemandVO;
import com.sinochem.crude.trade.listed.model.vo.tradingChainVo.TradingChainDetailsVO;
import com.sinochem.crude.trade.listed.service.*;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.order.remote.*;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.common.page.PageUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;

/**
 * 贸易链service实现类
 * Made By WangTing
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TradingChainServiceImpl implements TradingChainService {

    private Logger LOGGER = LoggerFactory.getLogger(TradingChainServiceImpl.class);


    @Autowired
    private TradingChainMapper tradingChainMapper;

    @Autowired
    private DemandMapper demandMapper;

    @Autowired
    private TradingChainDemandMapper tradingChainDemandMapper;

    @Autowired
    private TradingChainMemberMapper tradingChainMemberMapper;

    @Autowired
    private DemandService demandService;

    @Autowired
    private DemandShipService demandShipService;

    @Autowired
    private DemandRelevanterService demandRelevanterService;

    @Autowired
    private DemandOrderService demandOrderService;

    @Autowired
    private CrudeOilResourceMapper crudeOilResourceMapper;

    @Autowired
    private EnterpriseService enterpriseService;

    @Override
    public TradingChain selectByUuid(CurrentUser user, String uuid) {
        return tradingChainMapper.selectByUuid(uuid);
    }

    /**
     * 贸易链保存
     *
     */
    @Override
    public TradingChain saveTradingChain(CurrentUser user, TradeChainForm tradeChainForm) throws BizException{

//      新增贸易链表
        Long biddingId = tradeChainForm.getBiddingId();
//      判断该报价单是否存在
        Demand demand = existsDemand(biddingId);

        TradingChain tradingChain = new TradingChain();
        String operatorsId = "";

        TradeChainDemandVO tradeChainDemandVO1 = tradeChainForm.getBiddingList().get(0);
        TradeChainDemandVO tradeChainDemandVO2 = tradeChainForm.getBiddingList().get(1);
        if(tradeChainDemandVO2.getBuyerRelevanter().geteMemberId() == null || tradeChainDemandVO2.getSupplierRelevanter().geteMemberId() == null
                ||tradeChainDemandVO1.getBuyerRelevanter().geteMemberId() == null||tradeChainDemandVO1.getSupplierRelevanter().geteMemberId()==null){
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0000));
        }
        operatorsId = operatorsId + tradeChainDemandVO2.getBuyerRelevanter().geteMemberId()+","+ tradeChainDemandVO2.getSupplierRelevanter().geteMemberId()+","+ tradeChainDemandVO1.getSupplierRelevanter().geteMemberId();
        tradingChain.setSerialNumber(SerialNumberUtils.getSerialNumber12Len(SerialNumberBizType.TradingChain,new Date()));
        tradingChain.setDemandId(biddingId);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        tradingChain.setUuid(uuid);
        tradingChain.setStatus(TradingChainStatus.SAVED.getCode());
        tradingChain.setOperatorSum(3);
        tradingChain.setCreateTime(new Date());
        tradingChain.setDeadline(demand.getStopBidTime());
        tradingChain.setOperatorsId(operatorsId);
        tradingChain.setUserCreated(user.getMemberId());
        tradingChain.setAliveFlag(1);
        tradingChain.setVersion(0L);
        tradingChainMapper.insertSelective(tradingChain);
        TradingChain tradingChain1 = tradingChainMapper.selectByUuid(uuid);
        Long  tradingChainId = tradingChain1.getId();
//      新增demand表两条草约订单
        Long demand1 = saveOrUpdateTradeChainDemand(user, tradeChainDemandVO1, demand);
        Long demand2 = saveOrUpdateTradeChainDemand(user, tradeChainDemandVO2, demand);
//      新增贸易链表与demand中间表两条数据
        saveTradingChainDemand(user,demand1,tradingChainId,1);
        saveTradingChainDemand(user,demand2,tradingChainId,2);
//      新增贸易链表与member中间表三条数据
        saveTradingChainMember(user,tradingChainId,DemandRelevanterSupplierVO.convertVoToDomain(tradeChainDemandVO1.getSupplierRelevanter()),3,DemandRelevanterType.SUPPLIER.getCode());
        saveTradingChainMember(user,tradingChainId,DemandRelevanterBuyerVO.convertVoToDomain(tradeChainDemandVO1.getBuyerRelevanter()),2,DemandRelevanterType.AGENT.getCode());
        saveTradingChainMember(user,tradingChainId,DemandRelevanterBuyerVO.convertVoToDomain(tradeChainDemandVO2.getBuyerRelevanter()),1,DemandRelevanterType.BUYER.getCode());


        return tradingChain1;

 }

//  保存贸易链表与membere中间表方法
    private void saveTradingChainMember(CurrentUser user,Long tradingChainId, DemandRelevanter demandRelevanter,int ordersequence,String roleType){
        TradingChainMember tradingChainMember = new TradingChainMember();
        tradingChainMember.setTradingChainId(tradingChainId);
        tradingChainMember.setMemberId(demandRelevanter.getEMemberId());
        tradingChainMember.setSerialNumber(ordersequence);
        tradingChainMember.setRoleType(roleType);
        tradingChainMember.setUuid(UUID.randomUUID().toString().replace("-", ""));
        tradingChainMember.setCreateTime(new Date());
        tradingChainMember.setUserCreated(user.getEpMemberId());
        tradingChainMember.setAliveFlag(1L);
        tradingChainMember.setVersion(0L);
        tradingChainMemberMapper.insertSelective(tradingChainMember);
    }


//  保存贸易链表与demand中间表方法
    private void saveTradingChainDemand(CurrentUser user,Long demandId,Long tradingChainId,int ordersequence){
        TradingChainDemand tradingChainDemand = new TradingChainDemand();
        tradingChainDemand.setTradingChainId(tradingChainId);
        tradingChainDemand.setDemandId(demandId);
        tradingChainDemand.setSerialNumber(ordersequence);
        tradingChainDemand.setUuid(UUID.randomUUID().toString().replace("-", ""));
        tradingChainDemand.setCreateTime(new Date());
        tradingChainDemand.setUserCreated(user.getEpMemberId());
        tradingChainDemand.setAliveFlag(1L);
        tradingChainDemand.setVersion(0L);
        tradingChainDemandMapper.insertSelective(tradingChainDemand);
    }

//  保存或者修改除贸易链 demand表方法
    private Long saveOrUpdateTradeChainDemand(CurrentUser user,TradeChainDemandVO tradeChainDemandVO,Demand demand) throws BizException{

//      草约订单
        Demand biddingDemand = DemandVO.convertToDomain(tradeChainDemandVO.getBidding());
//      订单的船务信息
        DemandShip demandShip = tradeChainDemandVO.getDemandShip().convertVoToDomain();
//      订单的买家信息
        DemandRelevanter demandRelevanterBuyer = DemandRelevanterBuyerVO.convertVoToDomain(tradeChainDemandVO.getBuyerRelevanter());
//      订单的卖家信息
        DemandRelevanter demandRelevanterSupplier = DemandRelevanterSupplierVO.convertVoToDomain(tradeChainDemandVO.getSupplierRelevanter());

        // 取得报价对应的需求信息
        Demand requireDemand = demandService.getDemandByKey(demand.getParentId());
        if (requireDemand == null) {
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0095));
        }

        /**
         * 如果demand草约单的id为空就保存demand草约单，否则就是更新草约单
         */
//      此标志用来判断是新增还是修改(默认为修改) flag为true代表新增，false代表更新
        boolean flag = false;
        if(biddingDemand.getId() == null){
            flag=true;
            biddingDemand.setCreater(user.getEpMemberId());//创建人
            if (StringUtils.isEmpty(biddingDemand.getUuid())) {
                biddingDemand.setUuid(SerialNumberUtils.getSerialNumber12Len(SerialNumberBizType.CrudeOilQuote, new Date()));
            }
//      原油油种
            biddingDemand.setCrudeOilOptions(demand.getCrudeOilOptions());

//      单据类型
            biddingDemand.setType(DemandType.TRADINGCHAIN.getCode());
//      发布时间
            biddingDemand.setPubDate(new Date());
//      业务类型
            biddingDemand.setBizType(Constant.BIZ_TYPE_CRUDEOIL);
//      将草约单的parentId设置为报价单id
            biddingDemand.setParentId(demand.getId());
        }
        Long biddingDemandId = demandService.saveOrUpdateDemand(biddingDemand);

        /**
         * 如果是新增就保存原油油种
         */
        if(flag){
            List<CrudeOil> crudeOilList = crudeOilResourceMapper.getCrudeOilListByDemandId(demand.getId());
            for (CrudeOil crudeOil : crudeOilList) {
                crudeOil.setDemandId(biddingDemandId);
                crudeOilResourceMapper.insertSelective(crudeOil);
            }
        }


        /**
         * 保存船务
         */
        if (demandShip != null) {
            if(flag){
                demandShip.setDemandId(biddingDemandId);
                demandShip.setCreater(user.getMemberId());
                demandShip.setCreateTime(new Date());
            }else {
                demandShip.setUpdater(user.getEpMemberId());
                demandShip.setUpdateTime(new Date());
            }
            demandShipService.saveOrUpdateDemandShip(demandShip);
        } else {
            List<DemandShip> demandShipList = demandShipService.getShipByDemandId(biddingDemand.getParentId());
            DemandShip demandShip1 = demandShipList.get(0);
            demandShip1.setCreater(user.getEpMemberId());
            demandShip1.setCreateTime(new Date());
            demandShip1.setShipType(null);//供应商 船型   TODO
            demandShip1.setDemandId(biddingDemandId);
            demandShip1.setId(null);
            demandShipService.saveOrUpdateDemandShip(demandShip1);
        }

        /**
         * 保存订单的买家信息
         */
        if(flag){
            demandRelevanterBuyer.setDemandId(biddingDemandId);
        }

        // 画面传过来此变量
        if(demandRelevanterBuyer.getType() == null) {
            demandRelevanterBuyer.setType(DemandRelevanterType.BUYER.getCode());
        }
        demandRelevanterService.saveOrUpdateRelevanter(demandRelevanterBuyer);

        /**
         * 保存订单的卖家信息
         */
        if(flag){
             demandRelevanterSupplier.setDemandId(biddingDemandId);
        }

        // 画面传过来此变量
        if(demandRelevanterSupplier.getType() == null) {
            demandRelevanterSupplier.setType(DemandRelevanterType.SUPPLIER.getCode());
        }
        demandRelevanterService.saveOrUpdateRelevanter(demandRelevanterSupplier);

        return biddingDemandId;
    }

    /**
     *贸易链修改
     */
    @Override
    public TradingChain updateTradingChain(CurrentUser user, TradeChainForm tradeChainForm,String uuid) throws BizException{
        Long biddingId = tradeChainForm.getBiddingId();
//      判断该报价单是否存在
        Demand demand = existsDemand(biddingId);
//      第一步：修改贸易链表的数据

//      对于贸易链的时间需要加个判断

        TradingChain tradingChain = tradingChainMapper.selectByUuid(uuid);
        String operatorsId = "";

        TradeChainDemandVO tradeChainDemandVO1 = null;
        TradeChainDemandVO tradeChainDemandVO2 = null;

        List<TradeChainDemandVO> biddingList = tradeChainForm.getBiddingList();
        for (TradeChainDemandVO tv : biddingList) {
            if(tv.getOrdersequence() == 2){
                tradeChainDemandVO2 = tv;
            }
            if(tv.getOrdersequence() == 1){
                tradeChainDemandVO1 = tv;
            }
        }
        if(tradeChainDemandVO2.getBuyerRelevanter().geteMemberId() == null || tradeChainDemandVO2.getSupplierRelevanter().geteMemberId() == null
    ||tradeChainDemandVO1.getBuyerRelevanter().geteMemberId() == null||tradeChainDemandVO1.getSupplierRelevanter().geteMemberId()==null){
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0000));
        }
        operatorsId = operatorsId + tradeChainDemandVO2.getBuyerRelevanter().geteMemberId()
                        +","+ tradeChainDemandVO2.getSupplierRelevanter().geteMemberId()
                        +","+ tradeChainDemandVO1.getSupplierRelevanter().geteMemberId();
        tradingChain.setOperatorsId(operatorsId);
        tradingChain.setAlterTime(new Date());
        tradingChain.setUserModified(user.getEpMemberId());
        tradingChainMapper.updateByPrimaryKeySelective(tradingChain);
        Long  tradingChainId = tradingChain.getId();

//      第二步：修改草约订单的数据
        saveOrUpdateTradeChainDemand(user, tradeChainDemandVO1, demand);
        saveOrUpdateTradeChainDemand(user, tradeChainDemandVO2, demand);
//      第三步：修改贸易链与demand中间表的数据(目前业务中这个表数据不需要修改)
//      第四步：修改贸易链表与memeber中间表的数据
        TradingChainMember tradingChainMemberTrader = null;
        List<TradingChainMember> tradingChainMembers = tradingChainMemberMapper.selectByTradingChainId(tradingChain.getId());
        for ( TradingChainMember tradingChainMember: tradingChainMembers) {
//          查询出来的企业数据如果不是买家也不是卖家必定就是我们想要的贸易商
            if(tradingChainMember.getMemberId() != tradeChainDemandVO1.getSupplierRelevanter().geteMemberId()
                    && tradingChainMember.getMemberId() != tradeChainDemandVO2.getBuyerRelevanter().geteMemberId()){
                tradingChainMemberTrader = tradingChainMember;
            }
        }

        tradingChainMemberTrader.setMemberId(tradeChainDemandVO1.getBuyerRelevanter().geteMemberId());
        tradingChainMemberTrader.setAlterTime(new Date());
        tradingChainMemberTrader.setUserModified(user.getEpMemberId());
        tradingChainMemberMapper.updateByPrimaryKeySelective(tradingChainMemberTrader);
        return tradingChain;
    }


    /**
     *贸易链状态改变(提交、撤回、确认)
     */
    @Override
    public void alterTradingChainStatus(CurrentUser user,String uuid,Integer status) throws BizException{
        if(StringUtils.isEmpty(uuid)){
            LOGGER.error("传入的uuid不能为空");
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0000));
        }
        TradingChain tradingChain = tradingChainMapper.selectByUuid(uuid);
//      通过判断报价单的时间是否过期来判断我们的贸易链是否已过期
        existsDemand(tradingChain.getDemandId());
        TradingChainStatus tradingChainStatus = TradingChainStatus.getTradingChainStatusByCode(status);
        if(tradingChainStatus == null){
            LOGGER.error("传入的status非法");
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0002));
        }
//      如果本次提交的状态是保存，那么本次操作是撤回操作，撤回需要写撤回记录
        if(TradingChainStatus.SAVED.getCode().equals(status)){
            if(tradingChain.getStatus() > TradingChainStatus.PUBLISHED.getCode()){
                throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0194));
            }
            List<TradingChainMember> tradingChainMemberList = tradingChainMemberMapper.selectByTradingChainId(tradingChain.getId());
            for (TradingChainMember tradingChainMember : tradingChainMemberList) {
//              类型匹配代表是买家
                if(DemandRelevanterType.BUYER.getCode().equals(tradingChainMember.getRoleType())){
//                  拿到买家信息后如果这个状态是空，证明买家没有操作，将撤回操作写入
                    if(StringUtils.isEmpty(tradingChainMember.getOperationStatus())){
                        tradingChainMember.setOperationStatus(TradingChainEnterpriseOperate.WITHDRAW.getCode());
                        tradingChainMember.setRejectTime(new Date());
                        tradingChainMember.setAlterTime(new Date());
                        tradingChainMember.setUserModified(user.getEpMemberId());
                        tradingChainMemberMapper.updateByPrimaryKeySelective(tradingChainMember);
                        break;
                    }
//                  如果买家的操作记录有记录，证明已经撤回过，重新新增一条买家撤回信息，但是不放买家(后期机制改变，新增一条记录表)
                    if(TradingChainEnterpriseOperate.WITHDRAW.getCode().equals(tradingChainMember.getOperationStatus())){
                        TradingChainMember tradingChainMember2 = new TradingChainMember();
                        tradingChainMember2.setTradingChainId(tradingChainMember.getTradingChainId());
                        tradingChainMember2.setMemberId(tradingChainMember.getMemberId());
                        tradingChainMember2.setRoleType(tradingChainMember.getRoleType());
                        tradingChainMember2.setOperationStatus(tradingChainMember.getOperationStatus());
                        tradingChainMember2.setRejectTime(new Date());
                        tradingChainMember2.setUuid(UUID.randomUUID().toString().replace("-", ""));
                        tradingChainMember2.setCreateTime(new Date());
                        tradingChainMember2.setUserCreated(user.getEpMemberId());
                        tradingChainMember2.setAliveFlag(1L);
                        tradingChainMember2.setVersion(1L);
                        tradingChainMemberMapper.insertSelective(tradingChainMember2);
                        break;
                    }
                }
            }
        }

//      如果本次提交的状态是确认或者提交，只需要在贸易链表改变本条贸易链状态
        if(tradingChainStatus.getCode().equals(TradingChainStatus.PUBLISHED.getCode())){
            tradingChain.setStartTime(new Date());
        }
//      如果本次提交的状态是已完成，设置完成时间，更改报价单状态
        if(tradingChainStatus.getCode().equals(TradingChainStatus.COMPLETED.getCode())){
            tradingChain.setFinishTime(new Date());
            try {
                createOrderList(tradingChain);
            } catch (Exception e) {
                throw new BizException(e.getMessage(), e);
            }
        }

        tradingChain.setStatus(status);
        tradingChain.setUserModified(user.getEpMemberId());
        tradingChain.setAlterTime(new Date());
        tradingChainMapper.updateByPrimaryKeySelective(tradingChain);

    }

    /**
     * 生成订单
     */
    private void createOrderList(TradingChain tradingChain) throws Exception{

//      生成订单
        List<TradingChainDemand> tradingChainDemandList = tradingChainDemandMapper.selectByTradingChainId(tradingChain.getId());
        for (TradingChainDemand tradingChainDemand : tradingChainDemandList) {
            Long demandId = tradingChainDemand.getDemandId();
            DemandRelevanter supplier = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeTwo(demandId, DemandRelevanterType.SUPPLIER.getCode());

            UserVo userVO = new UserVo();
            userVO.setEpMemberId(supplier.getEMemberId());

            try {
                DemandOrderReturnVO demandOrderReturnVO = demandService.generateOrder(demandId, userVO);
                demandOrderService.confirmContract(demandOrderReturnVO.getOrderNo(), userVO.getEpMemberId(), null);

                tradingChainDemand.setOrderNo(demandOrderReturnVO.getOrderNo());
                tradingChainDemand.setOrderUuid(demandOrderReturnVO.getUuid());

                tradingChainDemandMapper.updateByPrimaryKeySelective(tradingChainDemand);
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception(e.getMessage(), e);
            }
        }


  //    3、根据需求报价id，修改对应的报价单及需求单状态
        // 修改为中标状态
        Demand biddingDemand = demandMapper.selectByPrimaryKey(tradingChain.getDemandId());
        biddingDemand.setStatus(BiddingStatus.DEMAND_STATUS_20.getCode());

        // 更新
        demandMapper.updateByPrimaryKeySelective(biddingDemand);
    }

    /**
     *通过条件查询贸易链列表(分页)
     */
    @Override
    public PageInfoResult getTradingChainList(CurrentUser user, TradeChainQuery tradeChainQuery, PageInfo pageInfo) throws BizException{
        if (pageInfo != null) {
            PageUtils.page(pageInfo);
        }
//      判断查询条件 三个月内还是一年内
        tradeChainQuery.setMemberId(user.getEpMemberId());
        String dataTime = tradeChainQuery != null ?tradeChainQuery.getDataTime():"";
        if (!org.apache.commons.lang.StringUtils.isEmpty(dataTime) && !"0".equals(dataTime)) {
            Date startTime = DateUtil.getDateTime(Integer.parseInt(dataTime));

            if (dataTime != null) {
                tradeChainQuery.setStartTime(startTime);
                tradeChainQuery.setEndTime(new Date());
            }
        }
//      通过查询条件混合查询贸易链列表
        List<TradingChain> tradingChainList = tradingChainMapper.uniteSelectByQuery(tradeChainQuery);
        for (int i=0;i<tradingChainList.size();i++) {
            TradingChain tradingChain = tradingChainList.get(i);
            Date stopBidTime = tradingChain.getDeadline();
            if (stopBidTime != null ) {
                try {
//      查询出来的贸易链数据如果某条贸易链数据过期了   如果加入了定时任务 可以考虑将这个段多余的for删除掉
                    if (stopBidTime.before(com.eyeieye.melody.util.DateUtil.getToday().getTime())) {
                        tradingChain.setStatus(TradingChainStatus.OVERDUE.getCode());
                        tradingChain.setUserModified(user.getEpMemberId());
                        tradingChain.setAlterTime(new Date());
                        tradingChainMapper.updateByPrimaryKeySelective(tradingChain);
                    }
//      如果是已保存状态的贸易链，不应该展示给贸易商和供应商
                    if(TradingChainStatus.SAVED.getCode().equals(tradingChain.getStatus())){
                        if(tradingChain.getOperatorsId().indexOf(user.getEpMemberId()+"") > 1){
                            tradingChainList.remove(tradingChain);
                        }
                    }
                } catch (ParseException pe) {
                    throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0180));
                }
            }

        }
        return new PageInfoResult(tradingChainList);
    }

    /**
     * 根据当前登录用户查询出所有与该用户参与贸易链的企业名称
     */
    @Override
    public List<EnterpriseNameByTradingChainVO> selectEnterpriseNameByTradingChain(CurrentUser user) throws BizException {
        List<EnterpriseNameByTradingChainVO> enterpriseNameByTradingChainVOS = tradingChainMapper.selectEnterpriseNameByTradingChain(user.getEpMemberId());
        if(enterpriseNameByTradingChainVOS != null){
            for(EnterpriseNameByTradingChainVO enterpriseNameByTradingChainVO : enterpriseNameByTradingChainVOS){
                EnterpriseVo enterpriseVo = enterpriseService.getByMemberId(enterpriseNameByTradingChainVO.getMemberId());
                if(enterpriseVo != null){
                    enterpriseNameByTradingChainVO.setEnterpriseNameEN(StringUtils.isNotEmpty(enterpriseVo.getEnglishName())?enterpriseVo.getEnglishName():enterpriseVo.getFullName());
                    enterpriseNameByTradingChainVO.setEnterpriseNameZH(StringUtils.isNotEmpty(enterpriseVo.getFullName())?enterpriseVo.getFullName():enterpriseVo.getEnglishName());
                }
            }
        }
        return enterpriseNameByTradingChainVOS;
    }


    /**
     *获取贸易链详情
     */
    @Override
    public TradingChainDetailsVO getTradingChainDetails(CurrentUser user,String uuid) throws BizException{
        if(StringUtils.isEmpty(uuid)){
            LOGGER.error("传入的uuid不能为空");
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0000));
        }
        TradingChain tradingChain = tradingChainMapper.selectByUuid(uuid);
//      从数据库查询本条贸易链相关六个表的数据
        List<TradingChainMember> tradingChainMemberList = tradingChainMemberMapper.selectByTradingChainId(tradingChain.getId());
        List<TradingChainDemand> tradingChainDemandList = tradingChainDemandMapper.selectByTradingChainId(tradingChain.getId());
        List<Demand> demandList = new ArrayList<Demand>();
        List<DemandShip> demandShipList = new ArrayList<DemandShip>();
        List<DemandRelevanter> demandRelevanterList1 = new ArrayList<DemandRelevanter>();
        List<DemandRelevanter> demandRelevanterList2 = new ArrayList<DemandRelevanter>();

//      通过判断贸易链与demand中间表的list
        for (TradingChainDemand tradingChainDemand:tradingChainDemandList) {

            Integer order = tradingChainDemand.getSerialNumber();
            Demand demand = demandMapper.selectByPrimaryKey(tradingChainDemand.getDemandId());
            List<DemandShip> demandShips = demandShipService.getShipByDemandId(demand.getId());
            if(order.equals(1)){
                demandRelevanterList1 = demandRelevanterService.getDemandRelevantersByDemandId(demand.getId());

            }else if(order.equals(2)){
                demandRelevanterList2 = demandRelevanterService.getDemandRelevantersByDemandId(demand.getId());
            }
            demandList.add(order.intValue() - 1, demand);
            demandShipList.add(order.intValue() - 1,demandShips.get(0));
        }
        for (DemandShip demandShip : demandShipList) {
        	 if(!StringUtils.isBlank(demandShip.getDischargePort()) && DictUtils.getUnLoadPortMap().keySet().contains(demandShip.getDischargePort())){
     			String[][] unLoadPortValue = DictUtils.getUnLoadPortValue(demandShip.getDischargePort());
     			String unLoadPort = VisitorLocale.getByCurrentLanguage(unLoadPortValue);
     			demandShip.setDischargePort(unLoadPort);
     		}
		}
        
        TradingChainDetailsVO tradingChainDetailsVO = new TradingChainDetailsVO();
        tradingChainDetailsVO.setTradingChainMemberList(tradingChainMemberList);
        tradingChainDetailsVO.setTradingChainDemandList(tradingChainDemandList);
        tradingChainDetailsVO.setDemandList(demandList);
        tradingChainDetailsVO.setDemandShipList(demandShipList);
        tradingChainDetailsVO.setDemandRelevanterList1(demandRelevanterList1);
        tradingChainDetailsVO.setDemandRelevanterList2(demandRelevanterList2);
        tradingChainDetailsVO.setTradingChain(tradingChain);
        return tradingChainDetailsVO;
    }




    /**
     * 贸易链确认
     */
    @Override
    public void confirmTradingChain(CurrentUser user,String uuid,String reason) throws BizException{
        if(StringUtils.isEmpty(uuid) ){
            LOGGER.error("传入的uuid不能为空");
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0000));
        }
        TradingChain tradingChain = tradingChainMapper.selectByUuid(uuid);
//      通过判断报价单的时间是否过期来判断我们的贸易链是否已过期
        existsDemand(tradingChain.getDemandId());
        List<TradingChainMember> tradingChainMemberList = tradingChainMemberMapper.selectByTradingChainId(tradingChain.getId());
//      定义本次操作的标识,确认成功变为false
        boolean a= true;
        for (TradingChainMember tradingChainMember : tradingChainMemberList) {
            if(tradingChainMember.getMemberId().equals(user.getEpMemberId())){
//              本次确认操作是贸易商在确认或者供应商在确认
                String roleType = tradingChainMember.getRoleType();
                if(DemandRelevanterType.AGENT.getCode().equals(roleType) || DemandRelevanterType.SUPPLIER.getCode().equals(roleType)){
                    a = false;
                    tradingChainMember.setOperationStatus(TradingChainEnterpriseOperate.CONFIRM.getCode());
                    tradingChainMember.setAcceptTime(new Date());
                    tradingChainMember.setAlterTime(new Date());
                    tradingChainMember.setUserModified(user.getEpMemberId());
                    tradingChainMemberMapper.updateByPrimaryKeySelective(tradingChainMember);
//                  在中间表插入确认记录同时改变本条贸易链的状态
                    if (DemandRelevanterType.AGENT.getCode().equals(roleType)) { // added by Yichen Zhao on 20180422 这里426后要修改下
                        alterTradingChainStatus(user,tradingChain.getUuid(),TradingChainStatus.CONFIRMING.getCode());
                    } else if (DemandRelevanterType.SUPPLIER.getCode().equals(roleType)) {
                        alterTradingChainStatus(user,tradingChain.getUuid(),TradingChainStatus.COMPLETED.getCode());
                    }


                }

            }

        }
        if(a){
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0181));
        }
    }


    /**
     * 贸易链驳回
     */
    @Override
    public void rejectTradingChain(CurrentUser user,String uuid,String reason) throws BizException{
        if(StringUtils.isEmpty(uuid) ){
            LOGGER.error("传入的uuid不能为空");
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0000));
        }
        TradingChain tradingChain = tradingChainMapper.selectByUuid(uuid);
//      通过判断报价单的时间是否过期来判断我们的贸易链是否已过期
        existsDemand(tradingChain.getDemandId());
        List<TradingChainMember> tradingChainMemberList = tradingChainMemberMapper.selectByTradingChainId(tradingChain.getId());
//      定义本次操作的标识,驳回成功变为false
        boolean a= true;
        for (TradingChainMember tradingChainMember : tradingChainMemberList) {
            if(tradingChainMember.getMemberId().equals(user.getEpMemberId())){
//              本次确认操作是贸易商在驳回或者是供应商在驳回
                if(DemandRelevanterType.AGENT.getCode().equals(tradingChainMember.getRoleType()) || DemandRelevanterType.SUPPLIER.getCode().equals(tradingChainMember.getRoleType())){
                    a = false;
                    tradingChainMember.setOperationStatus(TradingChainEnterpriseOperate.REJECT.getCode());
                    tradingChainMember.setRemark(reason);
                    tradingChainMember.setRejectTime(new Date());
                    tradingChainMember.setAlterTime(new Date());
                    tradingChainMember.setUserModified(user.getEpMemberId());
                    tradingChainMemberMapper.updateByPrimaryKeySelective(tradingChainMember);
//                  在中间表插入确认记录同时改变本条贸易链的状态
                    alterTradingChainStatus(user,tradingChain.getUuid(),TradingChainStatus.CANCELLED.getCode());
                    break;
                }
            }
        }
        if(a){
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0181));
        }

    }

    /**
     * 通过demandId查询贸易链
     * @param demandId
     * @return
     */
    @Override
    public List<TradingChain> selectByDemandId (Long demandId) throws BizException {
//      判断该报价单是否存在
        existsDemand(demandId);
        return tradingChainMapper.selectByDemandId(demandId);
    }

    /**
     * 输入报价单demandId判断是否有该报价单并且判断该报价单是否已经逾期
     *
     */
    public Demand existsDemand(Long demandId) throws BizException{
        if(demandId == null){
            return null;
        }

        Demand demand = demandMapper.selectByPrimaryKey(demandId);
        if( demand == null){
            LOGGER.info("查无此报价单");
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0179));
        }
        Date stopBidTime = demand.getStopBidTime();
        if (stopBidTime != null ) {
            try {
                if (stopBidTime.before(com.eyeieye.melody.util.DateUtil.getToday().getTime())) {
                    throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0096));
                }
            } catch (ParseException pe) {
                throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0180));
            }
        }
        return demand;
    }
}

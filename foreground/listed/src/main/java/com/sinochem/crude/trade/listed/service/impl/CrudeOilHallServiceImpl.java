package com.sinochem.crude.trade.listed.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.listed.constant.MsgConstant;
import com.sinochem.crude.trade.listed.enums.*;
import com.sinochem.crude.trade.listed.dao.DemandSpecifyEnterpriseMapper;
import com.sinochem.crude.trade.listed.domain.*;
import com.sinochem.crude.trade.listed.service.*;

import com.sinochem.it.b2b.common.utils.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoService;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoVO;
import com.sinochem.crude.trade.listed.constant.Constant;
import com.sinochem.crude.trade.listed.dao.DemandRelevanterMapper;
import com.sinochem.crude.trade.listed.dao.DemandShipBerthMapper;
import com.sinochem.crude.trade.listed.dao.DemandShipMapper;
import com.sinochem.crude.trade.listed.helper.SerialNumberUtils;
import com.sinochem.crude.trade.listed.model.form.CrudeOIlBiddingForm;
import com.sinochem.crude.trade.listed.model.form.CrudeOilForm;
import com.sinochem.crude.trade.listed.model.form.IdForm;
import com.sinochem.crude.trade.listed.model.vo.DemandVO;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.exception.BizException;

/**
 * Modified by Yichen Zhao
 * date: 20180105
 */
@Service
@Transactional
public class CrudeOilHallServiceImpl implements CrudeOilHallService {

    @Autowired
    private DemandService demandService;

    @Autowired
    private DemandShipBerthService demandShipBerthService;

    @Autowired
    private DemandRelevanterService demandRelevanterService;

    @Autowired
    private CrudeOilResourceService crudeOilResourceService;

    @Autowired
    private DemandDetailService demandDetailService;

    @Autowired
    private DemandShipService demandShipService;

    @Autowired(required = false)
    private DemandShipBerthMapper demandShipBerthMapper;

    @Autowired(required = false)
    private DemandShipMapper demandShipMapper;

    @Autowired(required = false)
    private DemandRelevanterMapper demandRelevanterMapper;

    @Autowired
    private CrudeOilInfoService crudeOilInfoService;

    @Autowired
    private DemandSpecifyEnterpriseMapper specifyEnterpriseMapper;

    /**
     * 保存需求单
     * @param demand
     * @param demandShipBerthList
     * @param demandRelevanterList
     * @param idForm
     * @param crudeOilFormList
     * @param demandDetailList
     * @param demandShip
     * @param currentUser
     */
    @Override
    public Demand saveProcurementDemand(Demand demand,
                                      List<DemandShipBerth> demandShipBerthList,
                                      List<DemandRelevanter> demandRelevanterList,
                                      IdForm idForm,
                                      List<CrudeOilForm> crudeOilFormList,
                                      List<DemandDetail> demandDetailList,
                                      DemandShip demandShip,
                                      CurrentUser currentUser, List<DemandSpecifyEnterprise> specifyEnterpriseList) throws BizException {


        /**
         * 保存需求单
         */
        demand.setCreater(currentUser.getEpMemberId());

        if (StringUtils.isEmpty(demand.getUuid())) {
            demand.setUuid(SerialNumberUtils.getSerialNumber12Len(SerialNumberBizType.CrudeOilDemand, new Date()));
        }
        if(DemandStatus.DEMAND_STATUS_2.getCode().equals(demand.getStatus())){
            demand.setPubDate(new Date());
        }
        demand.setType(DemandType.REQUIRE.getCode());
        demand.setDealType(DealType.BUY.getCode());
        demand.setBizType(BizType.CRUDE_OIL.getCode());

        demand.setNum(null);
        demandService.saveOrUpdateDemand(demand);

        //油种保存
        String oilTypes = saveCrudeOil(demand, crudeOilFormList);
        demand.setCrudeOilOptions(oilTypes);
        demandService.saveOrUpdateDemand(demand);

        /**
         * 代理商 和买家信息
         */
        if(demandRelevanterList!=null){
            for (DemandRelevanter dr:demandRelevanterList) {
                dr.setDemandId(demand.getId());

                // 设置当前登录人姓名和id  by sijliu  2017-12-27
                dr.setDealerId(currentUser.getMemberId());
                dr.setDealerName(currentUser.getName());

                demandRelevanterService.saveOrUpdateRelevanter(dr);
            }
        }

        // 保存采购需求指定的企业信息 by sijliu  2018-01-17
        if(demand.getSpecified() == DemandSpecifyType.SPECIFIED.getCode()){//0公开发布 1定向发布
            specifyEnterpriseChange(specifyEnterpriseList, demand.getId(), currentUser.getEpMemberId());
        }

        //泊位表保存
        if(demandShipBerthList!=null){
            demandShipBerthMapper.deleteByDemandId(demand.getId());
            // 新增
            for (DemandShipBerth ds:demandShipBerthList) {
                ds.setId(null);
                ds.setDemandId(demand.getId());
                demandShipBerthService.saveOrUpdateShipBerth(ds);
            }
        }
        //保存个性编辑啊
        if(demandDetailList!=null){
            demandDetailService.deleteDemandDetailByDemandId(demand.getId());
            for (DemandDetail dd:demandDetailList) {
                if(dd!=null){
                    dd.setId(null);
                    dd.setDemandId(demand.getId());
                    demandDetailService.saveOrUpdateDetails(dd);
                }
            }
        }


        /**
         * 保存demandShip
         */
        demandShipService.deleteByDemandId(demand.getId());
        demandShip.setDemandId(demand.getId());
        demandShip.setCreater(currentUser.getEpMemberId());
        demandShipService.saveOrUpdateDemandShip(demandShip);

        idForm.setDemandId(demand.getId());

        return demand;
    }

    /**
     * 销售资源维护_保存销售需求单
     * @param demand
     * @param demandShipBerthList
     * @param demandRelevanterList
     * @param idForm
     * @param crudeOilFormList
     * @param demandDetailList
     * @param demandShip
     * @param currentUser
     */
    @Override
    public Demand saveSaleDemand(Demand demand,
                                      List<DemandShipBerth> demandShipBerthList,
                                      List<DemandRelevanter> demandRelevanterList,
                                      IdForm idForm,
                                      List<CrudeOilForm> crudeOilFormList,
                                      List<DemandDetail> demandDetailList,
                                      DemandShip demandShip,
                                      CurrentUser currentUser, List<DemandSpecifyEnterprise> specifyEnterpriseList) throws BizException {


        /**
         * 保存需求单
         */
        demand.setCreater(currentUser.getEpMemberId());

        if (StringUtils.isEmpty(demand.getUuid())) {
            demand.setUuid(SerialNumberUtils.getSerialNumber12Len(SerialNumberBizType.CrudeOilDemand, new Date()));
        }
        if(DemandStatus.DEMAND_STATUS_2.getCode().equals(demand.getStatus())){
            demand.setPubDate(new Date());
        }
        demand.setType(DemandType.REQUIRE.getCode());
        demand.setDealType(DealType.SELL.getCode());
        demand.setBizType(BizType.CRUDE_OIL.getCode());

        demand.setNum(null);
        demandService.saveOrUpdateDemand(demand);

        //油种保存
        String oilTypes = saveCrudeOil(demand, crudeOilFormList);
        demand.setCrudeOilOptions(oilTypes);
        demandService.saveOrUpdateDemand(demand);

        /**
         * 代理商 和买家信息
         */
        if(demandRelevanterList!=null){
            for (DemandRelevanter dr:demandRelevanterList) {
                dr.setDemandId(demand.getId());

                // 设置当前登录人姓名和id  by sijliu  2017-12-27
                dr.setDealerId(currentUser.getMemberId());
                dr.setDealerName(currentUser.getName());

                demandRelevanterService.saveOrUpdateRelevanter(dr);
            }
        }

        // 保存采购需求指定的企业信息 by sijliu  2018-01-17
        if(demand.getSpecified() == DemandSpecifyType.SPECIFIED.getCode()){//0公开发布 1定向发布
            specifyEnterpriseChange(specifyEnterpriseList, demand.getId(), currentUser.getEpMemberId());
        }

        //泊位表保存
        if(demandShipBerthList!=null){
            demandShipBerthMapper.deleteByDemandId(demand.getId());
            // 新增
            for (DemandShipBerth ds:demandShipBerthList) {
                ds.setId(null);
                ds.setDemandId(demand.getId());
                demandShipBerthService.saveOrUpdateShipBerth(ds);
            }
        }
        //保存个性编辑啊
        if(demandDetailList!=null){
            demandDetailService.deleteDemandDetailByDemandId(demand.getId());
            for (DemandDetail dd:demandDetailList) {
                if(dd!=null){
                    dd.setId(null);
                    dd.setDemandId(demand.getId());
                    demandDetailService.saveOrUpdateDetails(dd);
                }
            }
        }


        /**
         * 保存demandShip
         */
        demandShipService.deleteByDemandId(demand.getId());
        demandShip.setDemandId(demand.getId());
        demandShip.setCreater(currentUser.getEpMemberId());
        demandShipService.saveOrUpdateDemandShip(demandShip);

        idForm.setDemandId(demand.getId());

        return demand;
    }

    /**
     * 发布需求单
     * @param demand
     * @param demandShipBerthList
     * @param demandRelevanterList
     * @param idForm
     * @param crudeOilInfoList
     * @param demandDetailList
     * @param demandShip
     * @param currentUser
     */
    @Override
    public void publishProcurementDemand(Demand demand,
                                         List<DemandShipBerth> demandShipBerthList,
                                         List<DemandRelevanter> demandRelevanterList,
                                         IdForm idForm,
                                         List<CrudeOilForm> crudeOilInfoList ,
                                         List<DemandDetail> demandDetailList,
                                         DemandShip demandShip,
                                         CurrentUser currentUser, List<DemandSpecifyEnterprise> specifyEnterpriseList) throws BizException {
        demand.setCreater(currentUser.getEpMemberId());

        if (StringUtils.isEmpty(demand.getUuid())) {
            demand.setUuid(SerialNumberUtils.getSerialNumber12Len(SerialNumberBizType.CrudeOilQuote, new Date()));
        }
        demand.setStatus(DemandStatus.DEMAND_STATUS_2.getCode());//已发布
        demand.setType(Constant.DEMAND_TYPE_D);
        demand.setDealType(Constant.DEMAND_TYPE_B);
        demand.setBizType(Constant.BIZ_TYPE_CRUDEOIL);
        demand.setPubDate(new Date());

        demand.setNum(null);
        demandService.saveOrUpdateDemand(demand);
        
        //油种保存
        String oilTypes = saveCrudeOil(demand, crudeOilInfoList);
        demand.setCrudeOilOptions(oilTypes);
        demandService.saveOrUpdateDemand(demand);

        /**
         * 保存demandShip
         */
        demandShipService.deleteByDemandId(demand.getId());
        demandShip.setDemandId(demand.getId());
        demandShip.setCreater(currentUser.getEpMemberId());
        demandShipService.saveOrUpdateDemandShip(demandShip);


        for (DemandRelevanter dr: demandRelevanterList) {

            // 设置交易员信息为当前登录人name和id  by sijliu  2017-12-27
            dr.setDealerId(currentUser.getMemberId());
            dr.setDealerName(currentUser.getName());

            dr.setDemandId(demand.getId());
            demandRelevanterService.saveOrUpdateRelevanter(dr);
        }

        // 保存采购需求指定的企业信息 by sijliu  2018-01-17
        specifyEnterpriseChange(specifyEnterpriseList, demand.getId(), currentUser.getEpMemberId());

        /**
         * 保存个性编辑信息
         */
        //保存个性编辑啊
        if (CollectionUtils.isNotEmpty(demandDetailList)) {
            demandDetailService.deleteDemandDetailByDemandId(demand.getId());
            for (DemandDetail dd:demandDetailList) {
                if(dd!=null){
                    dd.setId(null);
                    dd.setDemandId(demand.getId());
                    demandDetailService.saveOrUpdateDetails(dd);
                }
            }
        }

        //泊位表保存
        if(demandShipBerthList!=null){
            demandShipBerthMapper.deleteByDemandId(demand.getId());
            for (DemandShipBerth ds:demandShipBerthList) {
                ds.setId(null);
                ds.setDemandId(demand.getId());
                demandShipBerthService.saveOrUpdateShipBerth(ds);
            }
        }

        idForm.setDemandId(demand.getId());
    }

    /**
     * 原油报价、投标
     * @param currentUser
     * @param biddingForm
     * @throws BizException
     */
    @Override
    public Demand saveQuote(CurrentUser currentUser, CrudeOIlBiddingForm biddingForm) throws Exception {
        // 报价
        Demand biddingDemand = DemandVO.convertToDomain(biddingForm.getBidding());
        // 供应商
        DemandRelevanter demandRelevanter = biddingForm.getDemandRelevanter().convertVoToDomain();
        // 运输
        DemandShip demandShip = biddingForm.getDemandShip().convertVoToDomain();
        // 泊位
        DemandShipBerth demandShipBerth = biddingForm.getDemandShipBerth().convertVoToDomain();
        // 油
        CrudeOilForm crudeOilForm = biddingForm.getCrudeOil();
        
        // 物性表油种ID
        Long id = crudeOilForm.getPropertyId();
		if (id != null) {
			CrudeOilInfoVO crudeOilInfoVO = crudeOilInfoService.findCrudeOilInfoById(id);
			crudeOilInfoVO.setDesc(crudeOilForm.getDesc());
			crudeOilForm = CrudeOilForm.convertVoToForm(crudeOilInfoVO);
		}

        // 取得报价对应的需求信息
        Demand demand = demandService.getDemandByKey(biddingDemand.getParentId());
        if (demand == null) {
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0095));
        }

        Date stopBidTime = demand.getStopBidTime();
        if (stopBidTime != null ) {
            try {
                if (stopBidTime.before(com.eyeieye.melody.util.DateUtil.getToday().getTime())) {
                    throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0096));
                }
            } catch (ParseException pe) {
                throw pe;
            }
        }

        biddingDemand.setId(null);
        biddingDemand.setCreater(currentUser.getEpMemberId());//创建人

        if (StringUtils.isEmpty(biddingDemand.getUuid())) {
            biddingDemand.setUuid(SerialNumberUtils.getSerialNumber12Len(SerialNumberBizType.CrudeOilQuote, new Date()));
        }

        biddingDemand.setStatus(BiddingStatus.DEMAND_STATUS_10.getCode());
        if(biddingForm.getOilSaleFlag() != null && biddingForm.getOilSaleFlag() == 1) {
            biddingDemand.setDealType(DealType.BUY.getCode());
        } else {
            biddingDemand.setDealType(DealType.SELL.getCode());
        }
        
        biddingDemand.setCrudeOilOptions(crudeOilForm.getName());
        biddingDemand.setPayTime(demand.getPayTime());
        biddingDemand.setPurchaseType(demand.getPurchaseType());
        biddingDemand.setType(DemandType.BIDDING.getCode());
        biddingDemand.setTenderOutTime(demand.getTenderOutTime());
        biddingDemand.setIsSeal(demand.getIsSeal());
        biddingDemand.setPubDate(new Date());
        biddingDemand.setBizType(Constant.BIZ_TYPE_CRUDEOIL);
        biddingDemand.setPurchaseMode(demand.getPurchaseMode());
        biddingDemand.setCrudeOilOptions(crudeOilForm.getName());

        if (StringUtils.isEmpty(biddingDemand.getValuationFormulaJson())) {
            biddingDemand.setValuationFormulaJson(demand.getValuationFormulaJson());
            biddingDemand.setValuationFormula(demand.getValuationFormula());
        }
        biddingDemand.setCreateTime(null);

        biddingDemand.setAgio(null);
        biddingDemand.setNum(null);

        Long biddingDemandId = demandService.saveOrUpdateDemand(biddingDemand);

        //保存油种
        CrudeOil crudeOil;
        if (crudeOilForm != null) {
            crudeOil = CrudeOilForm.convertToDomain(crudeOilForm);
            if(StringUtils.isEmpty(crudeOil.getName())){
                throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0097));
            }
            Long crudeOilId = crudeOilForm.getCrudeOilId();
            if (crudeOilId != null) {
                //CrudeOilInfoVO crudeOilInfoVO = crudeOilInfoService.findCrudeOilInfoById(crudeOilId);
                //crudeOil.setArea(crudeOilInfoVO.getOriginAreaId());
            }
        } else {
            crudeOil = new CrudeOil();
        }

        crudeOil.setDemandId(biddingDemandId);
        crudeOilResourceService.saveOrUpdateObj(crudeOil);

        /**
         * 保存船务
         */
        if (demandShip != null) {
            demandShip.setDemandId(biddingDemandId);
            demandShip.setCreater(currentUser.getMemberId());
            demandShipService.saveOrUpdateDemandShip(demandShip);
        } else {
            List<DemandShip> demandShipList = demandShipService.getShipByDemandId(biddingDemand.getParentId());
            DemandShip demandShip1 = demandShipList.get(0);
            demandShip1.setCreater(currentUser.getEpMemberId());
            demandShip1.setShipType(null);//供应商 船型   TODO
            demandShip1.setDemandId(biddingDemandId);

            demandShip1.setId(null);
            demandShipService.saveOrUpdateDemandShip(demandShip1);
        }
        /**
         * 泊位保存
         */
        if(demandShipBerth!=null){
            demandShipBerth.setId(null);
            demandShipBerth.setDemandId(biddingDemandId);
            demandShipBerthService.saveOrUpdateShipBerth(demandShipBerth);
        }

        //保存供应商或购买商
        List<DemandRelevanter> demandRelevanters = demandRelevanterService.getDemandRelevantersByDemandId(biddingDemand.getParentId());

        for (DemandRelevanter de : demandRelevanters) {
            de.setId(null);
            de.setDemandId(biddingDemandId);
            demandRelevanterService.saveOrUpdateRelevanter(de);
        }

        demandRelevanter.setId(null);
        demandRelevanter.setDemandId(biddingDemandId);
        demandRelevanter.setEMemberId(currentUser.getEpMemberId());
        // 画面传过来此变量
        if(demandRelevanter.getType() == null) {
        	demandRelevanter.setType(DemandRelevanterType.SUPPLIER.getCode());
        }

        // 设置交易员信息为当前登录人name和id  by sijliu  2017-12-27
        demandRelevanter.setDealerId(currentUser.getMemberId());
        demandRelevanter.setDealerName(currentUser.getName());

        demandRelevanterService.saveOrUpdateRelevanter(demandRelevanter);

        return biddingDemand;
    }


    /**
     * 保存油种、性质(需求发布类型，1-按油种，2-按性质)
     * @param demand
     * @param crudeOilFormList
     */
    private String saveCrudeOil(Demand demand, List<CrudeOilForm> crudeOilFormList) {
        String Oiltypes="";       //油种
        //保存油表
        CrudeOil crudeOil;

        if(crudeOilFormList !=null) {
            crudeOilResourceService.deleteByDemandId(demand.getId());

            // 需求发布类型，1-按油种，2-按性质
            Integer publishType = demand.getPublishType();
            if (publishType != null && publishType.equals(2)) {
            	// 添加性质新数据
                for (CrudeOilForm crudeOilForm : crudeOilFormList) {
                    crudeOil = new CrudeOil();

                    //类型转换
                    TypeConversion(crudeOilForm, crudeOil);

                    crudeOil.setDemandId(demand.getId());
                    crudeOil.setDesc(crudeOilForm.getDesc());
                    crudeOilResourceService.saveOrUpdateObj(crudeOil);
                    
                    /* 
                     * API度只填写最小值、含硫量只填最大值时：API度>37.8 ; 含硫量<0.06%
                     * 同时填写最小值和最大值时：API：36.6-37.8 ; 含硫量：0.05%-0.06%
                     */
                    Long indicator1Min = crudeOil.getIndicator1Min();
                    Long indicator1Max = crudeOil.getIndicator1Max();
                    Long indicator2Min = crudeOil.getIndicator2Min();
                    Long indicator2Max = crudeOil.getIndicator2Max();
                    String indicator1Str = "";
                    String indicator2Str = "";
                    
                    if (indicator1Min != null && indicator1Min != 0
                    		&& indicator1Max != null && indicator1Max != 0) {
                    	indicator1Str = "API度：" + BigDecimal.valueOf(indicator1Min).divide(new BigDecimal(100)) + "-" + BigDecimal.valueOf(indicator1Max).divide(new BigDecimal(100));
                    } else if (indicator1Min != null && indicator1Min != 0) {
                    	indicator1Str = "API度 >" + BigDecimal.valueOf(indicator1Min).divide(new BigDecimal(100));
                    } else if (indicator1Max != null && indicator1Max != 0) {
                    	indicator1Str = "API度 <" + BigDecimal.valueOf(indicator1Max).divide(new BigDecimal(100));
                    }
                    
                    if (indicator2Min != null && indicator2Min != 0
                    		&& indicator2Max != null && indicator2Max != 0) {
                    	indicator2Str = "含硫量 ：" + BigDecimal.valueOf(indicator2Min).divide(new BigDecimal(100)) + "%-" + BigDecimal.valueOf(indicator2Max).divide(new BigDecimal(100)) + "%";
                    } else if (indicator2Min != null && indicator2Min != 0) {
                    	indicator2Str = "含硫量 >" + BigDecimal.valueOf(indicator2Min).divide(new BigDecimal(100));
                    } else if (indicator2Max != null && indicator2Max != 0) {
                    	indicator2Str = "含硫量 <" + BigDecimal.valueOf(indicator2Max).divide(new BigDecimal(100)) + "%";
                    }
                    
                    if (!indicator1Str.equals("") && !indicator1Str.equals("")) {
                    	Oiltypes = indicator1Str + "；" + indicator2Str;
                    } else if (!indicator1Str.equals("")) {
                    	Oiltypes = indicator1Str;
                    } else if (!indicator2Str.equals("")) {
                    	Oiltypes = indicator2Str;
                    }
                }
                
            } else {
            	// 添加油种新数据
                for (CrudeOilForm crudeOilForm : crudeOilFormList) {
                    crudeOil = new CrudeOil();
                    crudeOil.setName(crudeOilForm.getName());
                    crudeOil.setCnName(crudeOilForm.getCnName());
                    crudeOil.setOriginName(crudeOilForm.getOriginName());
                    crudeOil.setPropertyId(crudeOilForm.getPropertyId());
                    
                    //地区
                    if (!StringUtils.isBlank(crudeOilForm.getOrigin())) {
                    	crudeOil.setOrigin(Long.valueOf(crudeOilForm.getOrigin()));
                    }

                    crudeOil.setArea(crudeOilForm.getArea());
                    //类型转换
                    TypeConversion(crudeOilForm, crudeOil);

                    crudeOil.setDemandId(demand.getId());
                    crudeOil.setDesc(crudeOilForm.getDesc());
                    crudeOilResourceService.saveOrUpdateObj(crudeOil);
                    Oiltypes+= crudeOil.getName()+",";
                }
            }

        }
        return Oiltypes;
    }


    /**
     * 物性表类型转换
     * @param crudeOilForm
     * @param crudeOil
     */
    private void TypeConversion(CrudeOilForm crudeOilForm, CrudeOil crudeOil) {
        if (crudeOilForm.getIndicator1Min() != null && crudeOilForm.getIndicator1Min() != "") {
            Double getIndicator1Min = 100 * Double.valueOf(crudeOilForm.getIndicator1Min());
            crudeOil.setIndicator1Min(getIndicator1Min.longValue());
        }

        if (crudeOilForm.getIndicator2Min() != null && crudeOilForm.getIndicator2Min() != "") {

            Double getIndicator2Min = 100 * Double.valueOf(crudeOilForm.getIndicator2Min());
            crudeOil.setIndicator2Min(getIndicator2Min.longValue());
        }

        if (crudeOilForm.getIndicator3Min() != null && crudeOilForm.getIndicator3Min() != "") {

            Double getIndicator3Min = 100 * Double.valueOf(crudeOilForm.getIndicator3Min());
            crudeOil.setIndicator3Min(getIndicator3Min.longValue());
        }

        if (crudeOilForm.getIndicator4Min() != null && crudeOilForm.getIndicator4Min() != "") {

            Double getIndicator4Min = 100 * Double.valueOf(crudeOilForm.getIndicator4Min());
            crudeOil.setIndicator4Min(getIndicator4Min.longValue());
        }

        if (crudeOilForm.getIndicator5Min() != null && crudeOilForm.getIndicator5Min() != "") {
            Double getIndicator5Min = 100 * Double.valueOf(crudeOilForm.getIndicator5Min());
            crudeOil.setIndicator5Min(getIndicator5Min.longValue());
        }
        if (crudeOilForm.getIndicator6Min() != null && crudeOilForm.getIndicator6Min() != "") {
            Double getIndicator6Min = 100 * Double.valueOf(crudeOilForm.getIndicator6Min());
            crudeOil.setIndicator6Min(getIndicator6Min.longValue());
        }
        if (crudeOilForm.getIndicator7Min() != null && crudeOilForm.getIndicator7Min() != "") {
            Double getIndicator7Min = 100 * Double.valueOf(crudeOilForm.getIndicator7Min());
            crudeOil.setIndicator7Min(getIndicator7Min.longValue());
        }
        if (crudeOilForm.getIndicator8Min() != null && crudeOilForm.getIndicator8Min() != "") {
            Double getIndicator8Min = 10000 * Double.valueOf(crudeOilForm.getIndicator8Min());
            crudeOil.setIndicator8Min(getIndicator8Min.longValue());
        }
        if (crudeOilForm.getIndicator9Min() != null && crudeOilForm.getIndicator9Min() != "") {
            Double getIndicator9Min = 100 * Double.valueOf(crudeOilForm.getIndicator9Min());
            crudeOil.setIndicator9Min(getIndicator9Min.longValue());
        }
        if (crudeOilForm.getIndicator10Min() != null && crudeOilForm.getIndicator10Min() != "") {
            Double getIndicator10Min = 100 * Double.valueOf(crudeOilForm.getIndicator10Min());
            crudeOil.setIndicator10Min(getIndicator10Min.longValue());
        }
        if (crudeOilForm.getIndicator11Min() != null && crudeOilForm.getIndicator11Min() != "") {
            Double getIndicator11Min = 100 * Double.valueOf(crudeOilForm.getIndicator11Min());
            crudeOil.setIndicator11Min(getIndicator11Min.longValue());
        }
        if (crudeOilForm.getIndicator12Min() != null && crudeOilForm.getIndicator12Min() != "") {
            Double getIndicator12Min = 100 * Double.valueOf(crudeOilForm.getIndicator12Min());
            crudeOil.setIndicator12Min(getIndicator12Min.longValue());
        }

        if (crudeOilForm.getIndicator1Max() != null && crudeOilForm.getIndicator1Max() != "") {
            Double getIndicator1Max = 100 * Double.valueOf(crudeOilForm.getIndicator1Max());
            crudeOil.setIndicator1Max(getIndicator1Max.longValue());
        }

        if (crudeOilForm.getIndicator2Max() != null && crudeOilForm.getIndicator2Max() != "") {

            Double getIndicator2Max = 100 * Double.valueOf(crudeOilForm.getIndicator2Max());
            crudeOil.setIndicator2Max(getIndicator2Max.longValue());
        }

        if (crudeOilForm.getIndicator3Max() != null && crudeOilForm.getIndicator3Max() != "") {

            Double getIndicator3Max = 100 * Double.valueOf(crudeOilForm.getIndicator3Max());
            crudeOil.setIndicator3Max(getIndicator3Max.longValue());
        }

        if (crudeOilForm.getIndicator4Max() != null && crudeOilForm.getIndicator4Max() != "") {

            Double getIndicator4Max = 100 * Double.valueOf(crudeOilForm.getIndicator4Max());
            crudeOil.setIndicator4Max(getIndicator4Max.longValue());
        }

        if (crudeOilForm.getIndicator5Max() != null && crudeOilForm.getIndicator5Max() != "") {
            Double getIndicator5Max = 100 * Double.valueOf(crudeOilForm.getIndicator5Max());
            crudeOil.setIndicator5Max(getIndicator5Max.longValue());
        }
        if (crudeOilForm.getIndicator6Max() != null && crudeOilForm.getIndicator6Max() != "") {
            Double getIndicator6Max = 100 * Double.valueOf(crudeOilForm.getIndicator6Max());
            crudeOil.setIndicator6Max(getIndicator6Max.longValue());
        }
        if (crudeOilForm.getIndicator7Max() != null && crudeOilForm.getIndicator7Max() != "") {
            Double getIndicator7Max = 100 * Double.valueOf(crudeOilForm.getIndicator7Max());
            crudeOil.setIndicator7Max(getIndicator7Max.longValue());
        }
        if (crudeOilForm.getIndicator8Max() != null && crudeOilForm.getIndicator8Max() != "") {
            Double getIndicator8Max = 10000 * Double.valueOf(crudeOilForm.getIndicator8Max());
            crudeOil.setIndicator8Max(getIndicator8Max.longValue());
        }
        if (crudeOilForm.getIndicator9Max() != null && crudeOilForm.getIndicator9Max() != "") {
            Double getIndicator9Max = 100 * Double.valueOf(crudeOilForm.getIndicator9Max());
            crudeOil.setIndicator9Max(getIndicator9Max.longValue());
        }
        if (crudeOilForm.getIndicator10Max() != null && crudeOilForm.getIndicator10Max() != "") {
            Double getIndicator10Max = 100 * Double.valueOf(crudeOilForm.getIndicator10Max());
            crudeOil.setIndicator10Max(getIndicator10Max.longValue());
        }
        if (crudeOilForm.getIndicator11Max() != null && crudeOilForm.getIndicator11Max() != "") {
            Double getIndicator11Max = 100 * Double.valueOf(crudeOilForm.getIndicator11Max());
            crudeOil.setIndicator11Max(getIndicator11Max.longValue());
        }
        if (crudeOilForm.getIndicator12Max() != null && crudeOilForm.getIndicator12Max() != "") {
            Double getIndicator12Max = 100 * Double.valueOf(crudeOilForm.getIndicator12Max());
            crudeOil.setIndicator12Max(getIndicator12Max.longValue());
        }
    }

    /**
     * 采购需求的指定发布企业发生变化
     *  by sijliu  2018-01-17
     * @param specifyEnterpriseList 指定企业
     * @param demandId  需求id
     * @param operId    当前操作人
     */
    private void specifyEnterpriseChange(List<DemandSpecifyEnterprise> specifyEnterpriseList, Long demandId, Long operId) {
        if (CollectionUtils.isNotEmpty(specifyEnterpriseList)) {
            specifyEnterpriseMapper.deleteByDemandId(demandId);
            for (DemandSpecifyEnterprise specify : specifyEnterpriseList) {
                specify.setDemandId(demandId);
                specify.setCreater(operId);
                specifyEnterpriseMapper.insertSpecifyEnterprise(specify);
            }
        }
    }
}


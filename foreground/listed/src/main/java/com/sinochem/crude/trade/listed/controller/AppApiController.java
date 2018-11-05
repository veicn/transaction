package com.sinochem.crude.trade.listed.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.listed.constant.MsgConstant;
import com.sinochem.crude.trade.listed.constant.UrlMapping;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseDetailVO;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;
import com.sinochem.it.b2b.member.access.ApiSafeAccess;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BasicClientCookie2;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eyeieye.melody.util.StringUtil;
import com.eyeieye.melody.web.url.URLBroker;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoService;
import com.sinochem.crude.trade.listed.constant.Constant;
import com.sinochem.crude.trade.listed.domain.Demand;
import com.sinochem.crude.trade.listed.domain.DemandRelevanter;
import com.sinochem.crude.trade.listed.domain.DemandShip;
import com.sinochem.crude.trade.listed.domain.DemandShipBerth;
import com.sinochem.crude.trade.listed.enums.CrudeOilPublishRangeType;
import com.sinochem.crude.trade.listed.enums.DealType;
import com.sinochem.crude.trade.listed.enums.DemandPublishType;
import com.sinochem.crude.trade.listed.enums.DemandRelevanterType;
import com.sinochem.crude.trade.listed.enums.DemandStatus;
import com.sinochem.crude.trade.listed.helper.DictUtils;
import com.sinochem.crude.trade.listed.model.vo.CrudeOilDemandQueryVO;
import com.sinochem.crude.trade.listed.model.vo.DemandCompareVO;
import com.sinochem.crude.trade.listed.model.vo.DemandShipBerthVO;
import com.sinochem.crude.trade.listed.model.vo.DemandVO;
import com.sinochem.crude.trade.listed.model.vo.KeyValueVO;
import com.sinochem.crude.trade.listed.model.vo.ReleaseCrudeOilVO;
import com.sinochem.crude.trade.listed.model.vo.deprecate.CrudeOilPagingListVO;
import com.sinochem.crude.trade.listed.service.DemandRelevanterService;
import com.sinochem.crude.trade.listed.service.DemandService;
import com.sinochem.crude.trade.listed.service.DemandShipBerthService;
import com.sinochem.crude.trade.listed.service.DemandShipService;
import com.sinochem.crude.trade.listed.service.ReleaseCrudeOilFormService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.common.utils.DateUtil;
import com.sinochem.it.b2b.member.access.RolesAccess;
import com.sinochem.it.b2b.member.access.WithoutAccess;

/**
 * @Description: 供移动端调用
 * @Author : chenyz
 * @Date: 2017/12/11
 */
@Controller
@ApiSafeAccess
public class AppApiController {
    Logger logger = LoggerFactory.getLogger(AppApiController.class);

    @Autowired
    ReleaseCrudeOilFormService releaseCrudeOilFormService;

    @Autowired
    DemandShipBerthService demandShipBerthService;

    @Autowired
    DemandService demandService;

    @Autowired
    CrudeOilInfoService crudeOilInfoService;

    @Autowired
    private URLBroker memberServer;

    @Autowired
    private HttpConnectionManager httpConnectionManager;

    @Autowired
    private DemandShipService demandShipService;
    
    @Autowired
    private DemandRelevanterService demandRelevanterService;

    @Autowired
    private EnterpriseService enterpriseService;
    
    /**
     * 原油筛选数据
     * @return
     */
    @RequestMapping(value= UrlMapping.APP_CRUDEOILCONDITION)
    @ResponseBody
    public ResultDatas crudeOilConditon(HttpServletRequest request) {
        ResultDatas resultDatas = new ResultDatas();
        List mapList = new ArrayList();
        // 交易类型
        Map<String, Object> purchaseTypeMap = new HashMap<>();
        purchaseTypeMap.put("value", mapToList(DictUtils.getPurchaseTypeMap()));
        purchaseTypeMap.put("key","purchaseType");
        // 交易方式
        purchaseTypeMap.put("name",VisitorLocale.getByCurrentLanguage(Constant.LISTED_0134));
        mapList.add(purchaseTypeMap);
        // 原油产地
        /*Map<String, Object> regionMap = new HashMap<>();
        regionMap.put("value", mapToList(DictUtils.getCrudeOilOrigin()));
        regionMap.put("key","crudePlace");
        regionMap.put("name","产地");
        mapList.add(regionMap);*/
        // 原油油种
        /*List<ConfigurationValueVO> listc = new ArrayList<ConfigurationValueVO>();
        List<CrudeOilInfoVO> crudeList = crudeOilInfoService.findCrudeOilInfos(null, null);
        if (CollectionUtils.isNotEmpty(crudeList)) {
            for (CrudeOilInfoVO coiv : crudeList) {
                listc.add(new ConfigurationValueVO(coiv.getId().intValue(), coiv.getCrudeNameE()));
            }
        }
        Map<String, Object> oilMap = new HashMap<>();
        oilMap.put("value", listc);
        oilMap.put("key","oilType");
        oilMap.put("name","油种");
        mapList.add(oilMap);*/
        // 采购方式
        Map<String, Object> buyTypeMap = new HashedMap();
        buyTypeMap.put("value", DemandPublishType.convertToVOList());
        buyTypeMap.put("key","publishType");
        // 采购方式
        buyTypeMap.put("name", VisitorLocale.getByCurrentLanguage(Constant.LISTED_0146));
        mapList.add(buyTypeMap);
        // 发布范围
        Map<String, Object> publishRangeTypeMap = new HashedMap();
        publishRangeTypeMap.put("value", CrudeOilPublishRangeType.convertToVOList());
        publishRangeTypeMap.put("key","specified");
        // 发布范围
        publishRangeTypeMap.put("name", VisitorLocale.getByCurrentLanguage(Constant.LISTED_0137));
        mapList.add(publishRangeTypeMap);
        // 数量
        Map<String, Object> numMap = new HashMap<>();
        numMap.put("value", mapToList(DictUtils.getDemandNumMap()));
        numMap.put("key","oilNumber");
        // 数量
        numMap.put("name", VisitorLocale.getByCurrentLanguage(Constant.LISTED_0140));
        mapList.add(numMap);

        // 发布企业
        List<EnterpriseDetailVO> enterpriseDetailVOList = enterpriseService.getAllEnterprises();
        List<KeyValueVO> keyValueVOList = new ArrayList<>();
        for (EnterpriseDetailVO enterpriseDetailVO : enterpriseDetailVOList) {

            Long epMemberId = enterpriseDetailVO.getMemberId();
            if (epMemberId != null) {

                String enterpriseName = enterpriseDetailVO.getName();

                KeyValueVO keyValueVO = new KeyValueVO();
                keyValueVO.setKey(epMemberId.toString());
                keyValueVO.setValue(enterpriseName);

                keyValueVOList.add(keyValueVO);
            }
        }

        Map<String, Object> publistEnterpriseMap = new HashedMap();
        publistEnterpriseMap.put("value", keyValueVOList);
        publistEnterpriseMap.put("key","enterprise");
        // 发布企业
        publistEnterpriseMap.put("name", VisitorLocale.getByCurrentLanguage(Constant.LISTED_0145));
        mapList.add(publistEnterpriseMap);

        resultDatas.setDatas(mapList);
        return resultDatas;
    }

    private List mapToList(Map<Object,String> map){
        List<Map<String, Object>> list = new ArrayList<>();
        for(int i = 1 ; i <= map.size(); i++){
            if(map.get(i) != null){
                Map<String, Object> listmap = new HashMap<>();
                listmap.put("key",i);
                listmap.put("value",map.get(i));
                list.add(listmap);
            }
        }
        return list;
    }


    /**
     * 分页查询原油
     * @param copsv
     * @return
     */
    @RequestMapping(value=UrlMapping.APP_QUERYCRUDEOILLIST)
    @ResponseBody
    public ResultDatas queryCrudeOilList(@RequestBody CrudeOilDemandQueryVO copsv, CurrentUser user) {
        logger.info("原油分页查询条件信息：" + copsv.getPageNum() +"  " + copsv.getPageSize());
        ResultDatas resultDatas = new ResultDatas();
       try{
           // 分页信息
           PageInfo pageInfo = new PageInfo();
           if (copsv ==  null ) {
               resultDatas.setStatus(Result.ERROR);
               resultDatas.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0002));
               return resultDatas;
           } else {
               if (copsv.getPageSize() == null || copsv.getPageNum() == null) {
                   resultDatas.setStatus(Result.ERROR);
                   resultDatas.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0003));
                   return resultDatas;
               }
               pageInfo.setPageNum(copsv.getPageNum());
               pageInfo.setPageSize(copsv.getPageSize());
//            pageInfo.setSize(copsv.getSize());
           }
           copsv.setEpMemberId(user.getEpMemberId());
           copsv.setBizType(Constant.BIZ_TYPE_CRUDEOIL);
           copsv.setType(Constant.DEMAND_TYPE_D);//需求单
           copsv.setDealType(DealType.BUY.getCode());//买

           // 分页查询
           PageInfoResult<ReleaseCrudeOilVO> pageInfoResult =  releaseCrudeOilFormService.getReleaseCrudeOilFormList(pageInfo,copsv);
           // 定义结果集合
           List<CrudeOilPagingListVO> resultList = new ArrayList<>();

           // 查询结果非空的情况下
           if (CollectionUtils.isNotEmpty(pageInfoResult.getList())) {
               CrudeOilPagingListVO tempObj = null;
               for (ReleaseCrudeOilVO rcov: (List<ReleaseCrudeOilVO>)pageInfoResult.getList()) {
                   tempObj = new CrudeOilPagingListVO();
                   tempObj.setId(rcov.getId());
                   tempObj.setDemandNo(rcov.getUuid());
                   Long num = rcov.getNum();
                   if (num != null) {
                       DecimalFormat df = new DecimalFormat("#0");
                       double numD = num/10000000.0;
                       tempObj.setNum(df.format(numD));
                   }
                   tempObj.setOil(rcov.getCrudeOilOptions());
                   tempObj.setTradeItem(DictUtils.getTradeItemMap().get(rcov.getTradeItem()));
                   tempObj.setArrivalDate(DateUtil.formatDate(rcov.getDischargeStartTime())+VisitorLocale.getByCurrentLanguage(Constant.LISTED_0036)+DateUtil.formatDate(rcov.getDischargeEndTime()));
                   tempObj.setStopBidTime(DateUtil.formatDate(rcov.getStopBidTime()));
                   tempObj.setTenderOutTime(DateUtil.formatDate(rcov.getTenderOutTime()));
                   tempObj.setPayItem(rcov.getPayItem());
                   tempObj.setPurchaseType(rcov.getPurchaseType());
                   tempObj.setPubDate(DateUtil.formatDate(rcov.getPubDate()));
                   if(rcov.getPurchaseType() != null){
                       tempObj.setPurchaseType(rcov.getPurchaseType());
                       if(rcov.getPurchaseType() == 1){
                           // "招标"
                           tempObj.setPurchaseTypeContent(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0135));
                       }else{
                           // "询价"
                           tempObj.setPurchaseTypeContent(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0136));
                       }
                   }
                   tempObj.setCreater(rcov.getCreater());
                   resultList.add(tempObj);
               }
           }
           resultDatas.setDatas(resultList);
           return resultDatas;
       }catch (Exception e){
           logger.error("分页查询原油");
           logger.error("",e);
           resultDatas.setFail("分页查询原油失败",ResultDatas.ERROR);
           return resultDatas;
       }
    }



    /**
     * 根据ID获取泊位信息
     * @param demandId
     * @return
     */
    @RequestMapping(value = UrlMapping.APP_QUERYSHIPBERTHLIST)
    @ResponseBody
    public ResultDatas queryShipBerthList(Long demandId){
        logger.info("APP获取泊位信息：" + demandId);
        ResultDatas resultDatas = new ResultDatas();
        List<DemandShipBerthVO> voList = new ArrayList<>();
        if(demandId != null){
            List<DemandShipBerth> shipBerths = null;
            try {
                shipBerths = demandShipBerthService.getShipBerthByDemandId(demandId);
            } catch (BizException e) {
                logger.error("泊位信息获取异常：", e.getMessage());
            }
            if(shipBerths != null && shipBerths.size() > 0){
                for(DemandShipBerth shipBerth : shipBerths){
                    DemandShipBerthVO shipBerthVO = new DemandShipBerthVO();
                    shipBerthVO.setId(shipBerth.getId());
                    shipBerthVO.setBerthName(shipBerth.getBerthName());
                    if(shipBerth.getBerthGrade() != null){
                        shipBerthVO.setBerthGrade(shipBerth.getBerthGrade() + "万吨");
                    }
                    shipBerthVO.setShipType(shipBerth.getShipType());
                    
                    if(shipBerth.getShipType() != null){
                        shipBerthVO.setShipTypeContent(DictUtils.getValue("shipTypes",shipBerth.getShipType().toString()).toString());
                    }
                    
                    shipBerthVO.setBerthDesc(shipBerth.getBerthDesc());
                    shipBerthVO.setDraftLimitation(shipBerth.getDraftLimitation());
                    shipBerthVO.setDemandId(shipBerth.getDemandId());
                    voList.add(shipBerthVO);
                }
            }
        }
        logger.info("APP获取泊位信息结束 size：" + voList.size());
        resultDatas.setDatas(voList);
        return resultDatas;
    }

    /**
     * 获取热点信息
     * @return
     */
    @RequestMapping(value = UrlMapping.APP_QUERYHOTSPOTLIST)
    @ResponseBody
    public ResultDatas queryHotspotList(){
        logger.info("APP获取热点信息！");
        ResultDatas resultDatas = new ResultDatas();
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(1);
        pageInfo.setPageSize(2);
        List voList = new ArrayList<>();
        //原油
        CrudeOilDemandQueryVO crudeOilDemandQueryVO = new CrudeOilDemandQueryVO();
        PageInfoResult<ReleaseCrudeOilVO> pageInfoResult = releaseCrudeOilFormService.getReleaseCrudeOilFormList(pageInfo, crudeOilDemandQueryVO);
        List<ReleaseCrudeOilVO> list = pageInfoResult.getList();
        if(list != null && list.size() > 0){
            for(ReleaseCrudeOilVO rcov : list){
                CrudeOilPagingListVO tempObj = new CrudeOilPagingListVO();
                tempObj = new CrudeOilPagingListVO();
                tempObj.setId(rcov.getId());
                tempObj.setDemandNo(rcov.getUuid());
                Long num = rcov.getNum();
                if (num != null) {
                    DecimalFormat df = new DecimalFormat("#0");
                    double numD = num/10000000.0;
                    tempObj.setNum(df.format(numD));
                }
                tempObj.setOil(rcov.getCrudeOilOptions());
                tempObj.setTradeItem(DictUtils.getTradeItemMap().get(rcov.getTradeItem()));
                tempObj.setArrivalDate(DateUtil.formatDate(rcov.getDischargeStartTime())+VisitorLocale.getByCurrentLanguage(Constant.LISTED_0036)+DateUtil.formatDate(rcov.getDischargeEndTime()));
                tempObj.setStopBidTime(DateUtil.formatDate(rcov.getStopBidTime()));
                tempObj.setTenderOutTime(DateUtil.formatDate(rcov.getTenderOutTime()));
                tempObj.setBizType("CrudeOil");
                tempObj.setPayItem(rcov.getPayItem());
                tempObj.setPubDate(DateUtil.formatDate(rcov.getPubDate()));
                if(rcov.getPurchaseType() != null){
                    tempObj.setPurchaseType(rcov.getPurchaseType());
                    if(rcov.getPurchaseType() == 1){
                    	// "招标"
                        tempObj.setPurchaseTypeContent(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0135));
                    }else{
                    	// "询价"
                        tempObj.setPurchaseTypeContent(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0136));
                    }
                }
                tempObj.setCreater(rcov.getCreater());
                voList.add(tempObj);
            }
        }

        resultDatas.setDatas(voList);
        return resultDatas;
    }

    /**
     * 更新状态
     * @param demandVO
     * @return
     */
    @RequestMapping(value = UrlMapping.APP_UPDATEDEMANDSTATUS)
    @ResponseBody
    public ResultDatas updateDemandStatus(@RequestBody DemandVO demandVO) {
        ResultDatas resultDatas = new ResultDatas();

        Long id = demandVO.getId();
        if (id == null) {
            resultDatas.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0004));
            return resultDatas;
        }

        Integer targetStatus = demandVO.getStatus();
        if (targetStatus == null) {
            resultDatas.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0005));
            return resultDatas;
        }

        Demand originDemand;
        try {
            originDemand = demandService.getDemandByKey(id);
        } catch (Exception e) {
            logger.error(e.toString());
            resultDatas.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0006));
            return resultDatas;
        }

        if (originDemand == null) {
            resultDatas.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0007));
            return resultDatas;
        }

        Integer originStatus = originDemand.getStatus();
        if (originStatus == null) {
            resultDatas.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0008));
            return resultDatas;
        }

        if (!validDemandStatus(originStatus, targetStatus)) {
            resultDatas.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0009));
            return resultDatas;
        }

        Demand demandForUpdate = new Demand();
        demandForUpdate.setId(id);
        demandForUpdate.setStatus(targetStatus);

        try {
            demandService.saveOrUpdateDemand(demandForUpdate);
        } catch (Exception e) {
            logger.error(e.toString());
            resultDatas.setFail(e.getMessage());
        }

        return resultDatas;
    }

    /**
     * 获取字典信息
     * @return
     */
    @RequestMapping(value = UrlMapping.APP_GETDICLISTMAPBYBIZNAME)
    @ResponseBody
    public ResultDatas<List<KeyValueVO>> getDicListMapByBizName(HttpServletRequest request,String dicName) {
        request.getCookies();
    	logger.info("获取字典信息 --->{}",dicName);
    	ResultDatas<List<KeyValueVO>> dicListMap = new ResultDatas<List<KeyValueVO>>();
    	try {
			List<KeyValueVO> listKeyValue = getDicListMap(dicName);
    		//List<KeyValueVO> listKeyValue = getDicListMapByCache(dicName);
			dicListMap.setStatus(Result.SUCCESS);
			dicListMap.setDatas(listKeyValue);
			dicListMap.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0010));
		} catch (Exception e) {
			logger.error("获取字典信息失败 --->{}",dicName);
			logger.error("",e);
			dicListMap.setStatus(Result.ERROR);
			dicListMap.setDatas(null);
			dicListMap.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0011));
		}
    		
    	return dicListMap;
    }
    
    private List<KeyValueVO> getDicListMapByCache(String dicName){
    	Map<Object, String> dictCache = DictUtils.getCacheMap(dicName);
    	List<KeyValueVO> keyValueList = new ArrayList<KeyValueVO>();
    	for(Map.Entry<Object, String> item : dictCache.entrySet()) {
    		keyValueList.add(new KeyValueVO(String.valueOf(item.getKey()),String.valueOf(item.getValue())));
    	}
    	return keyValueList;
    }

    /**
     * 判断待改的需求单状态是否合法
     */
    private boolean validDemandStatus(Integer originStatus, Integer targetStatus) {
        if (originStatus.equals(targetStatus)) {
            return false;
        }

        if (DemandStatus.DEMAND_STATUS_0.getCode().equals(originStatus)) {
            return false;
        }

        if (DemandStatus.DEMAND_STATUS_1.getCode().equals(originStatus)
                || !DemandStatus.DEMAND_STATUS_3.getCode().equals(targetStatus)) {
            return true;
        }

        if (DemandStatus.DEMAND_STATUS_2.getCode().equals(originStatus)) {
            return true;
        }

        if (DemandStatus.DEMAND_STATUS_3.getCode().equals(originStatus)) {
            return false;
        }

        return false;
    }
    
    /**
     * 对DictUtils 采用反射，取得get*() ，*首字母转小写 
     * @param dicName
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    private List<KeyValueVO> getDicListMap(String dicName) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<KeyValueVO> listKeyValue = new ArrayList<KeyValueVO>();
		Class<DictUtils> dicClass = DictUtils.class;
    	Method[] dicMethods = dicClass.getMethods();
    	DictUtils dic = new DictUtils();
    	for(Method item : dicMethods) {
    		if(item.getName().contains("get")) {
    			String name = item.getName();
    			String newDicName = name.substring(3, name.length());
    			if(!Character.isLowerCase(newDicName.charAt(0))) {
    				String lowName = (new StringBuilder()).append(Character.toLowerCase(newDicName.charAt(0))).append(newDicName.substring(1)).toString();
    				if(dicName.equals(lowName) ) {
    					Map map =  (Map)item.invoke(dic, null);
    					Iterator entries = map.entrySet().iterator(); 
    					while (entries.hasNext()) { 
    					  Map.Entry entry = (Map.Entry) entries.next(); 
    					  listKeyValue.add(new KeyValueVO(String.valueOf(entry.getKey()), String.valueOf(entry.getValue())));
    					}
    					break;
    				}
    			}
    		}
    	}
    	return listKeyValue;
	}
    
    /**
     * 获取字典信息
     * @return
     */
    @RequestMapping(value = UrlMapping.APP_DEMANDSCOMPARE)
    @ResponseBody
    public ResultDatas<List<DemandCompareVO>> demandCompare(String demands) {
    	logger.info("信息比较 --->", demands);
    	
    	ResultDatas<List<DemandCompareVO>> result = new ResultDatas<List<DemandCompareVO>>();
    	List<DemandCompareVO> demandVOList = new ArrayList<DemandCompareVO>();
    	Map<Object, String> valuationProidTypeMap = DictUtils.getValuationProidTypeMap();
    	
    	try {
            if (demands != null && demands.split(",").length > 0) {
                String[] ids = demands.split(",");

                for (String c : ids) {
                    Demand demand = demandService.getDemandByKey(Long.parseLong(c));
                    DemandCompareVO vo = DemandCompareVO.convertToVO(demand);
                    demandVOList.add(vo);
                }
                
                if(demandVOList != null) {
                	for(int i = 0; i < demandVOList.size(); i++) {
                		// 企业信息
                		DemandRelevanter dr = null;
                		// 购买商信息
                		if(demandVOList.get(i).getDealType().equals(Constant.DEAL_TYPE_B)) {
                			dr = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeTwo(demandVOList.get(i).getId(), DemandRelevanterType.BUYER.getCode());
                		}
                		// 供应商信息
                		if(demandVOList.get(i).getDealType().equals(Constant.DEAL_TYPE_S)) {
                			dr = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeTwo(demandVOList.get(i).getId(), DemandRelevanterType.SUPPLIER.getCode());
                		}
                		
                		// 企业信息赋值
                		if(dr != null) {
                			demandVOList.get(i).setEnterpriseName(dr.getEnterpriseName());
                			demandVOList.get(i).setEnterpriseAddress(dr.getEnterpriseAddress());
                			demandVOList.get(i).setContacter(dr.getContacter());
                			demandVOList.get(i).setFamil(dr.getFamil());
                			demandVOList.get(i).setPhoneNo(dr.getPhoneNo());
                			demandVOList.get(i).setFax(dr.getFax());
                		}
                		
                		List<DemandShip> demandShip = demandShipService.getShipByDemandId(demandVOList.get(i).getId());
                		if(demandShip != null && demandShip.size() > 0) {
                			demandVOList.get(i).setShipmentPort(demandShip.get(0).getShipmentPort());
                			demandVOList.get(i).setShipmentStartTime(DateUtil.formatDate(demandShip.get(0).getShipmentStartTime()));
                			demandVOList.get(i).setShipmentEndTime(DateUtil.formatDate(demandShip.get(0).getShipmentEndTime()));
                			
                			demandVOList.get(i).setDischargePort(demandShip.get(0).getDischargePort());
                			demandVOList.get(i).setDischargeStartTime(DateUtil.formatDate(demandShip.get(0).getDischargeStartTime()));
                			demandVOList.get(i).setDischargeEndTime(DateUtil.formatDate(demandShip.get(0).getDischargeEndTime()));
                		}
                		
                		// 计价期
            			Integer valuationProidType = demandVOList.get(i).getValuationProidType();
    					String valuationProidTypeStr = "";
    					if(valuationProidType != null){
    						if(valuationProidType == 4){
    							valuationProidTypeStr =
    									valuationProidTypeMap.get(valuationProidType) +
    											"("
    											+ DateUtil.formatDate(demandVOList.get(i).getValuationProidStart())
    											+ VisitorLocale.getByCurrentLanguage(Constant.LISTED_0036)
    											+ DateUtil.formatDate(demandVOList.get(i).getValuationProidEnd())
    											+")";
    						}else if(valuationProidType == 5){
    							valuationProidTypeStr =
    									valuationProidTypeMap.get(valuationProidType) +
    											"("
    											+ demandVOList.get(i).getContractKind()
    											+")";
    						}else{
    							valuationProidTypeStr = valuationProidTypeMap.get(valuationProidType);
    						}
    					}
    					demandVOList.get(i).setValuationProidTypeStr(valuationProidTypeStr);
                	}
                }
            }
            
            result.setDatas(demandVOList);
		} catch (Exception e) {
			logger.error("信息比较失败 --->{}", demands);
			logger.error("", e);
			
			result.setStatus(Result.ERROR);
			result.setDatas(null);
			result.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0012));
		}
    		
    	return result;
    }
}

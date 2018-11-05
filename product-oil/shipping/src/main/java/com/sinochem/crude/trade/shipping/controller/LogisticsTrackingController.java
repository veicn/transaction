package com.sinochem.crude.trade.shipping.controller;


import java.lang.reflect.Type;
import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.eyeieye.melody.web.url.URLBroker;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sinochem.crude.trade.common.model.vo.DictionaryVO;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.common.utils.HttpUtils;
import com.sinochem.crude.trade.shipping.domain.*;
import com.sinochem.crude.trade.shipping.model.query.LoadingProgressQuery;

import com.sinochem.crude.trade.shipping.model.vo.*;
import com.sinochem.crude.trade.shipping.service.*;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.validator.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.StringUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.constant.UrlMapping;
import com.sinochem.crude.trade.shipping.exceptions.TransportException;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.springframework.web.client.RestTemplate;

/**
 * @author yangshaonan
 * @version 创建时间：2018年3月14日 下午7:28:20
 * 类说明    物流跟踪
 */
@Controller
@WithoutAccess
@RequestMapping(UrlMapping.PAGES_BACK_LOGISTICSTRACKING)
public class LogisticsTrackingController {

    @Value("${aliyun.oss.endpoint}")
    private String imgurl;

    @Autowired
    private ConfirmationSheetService confirmationSheetService;

    @Autowired
    private VoyageStartService voyageStartService;

    @Autowired
    private TransitLoadingService transitLoadingService;

    @Autowired
    private LoadPortService loadPortService;

    @Autowired
    private LoadingProgressService loadingProgressService;

    @Autowired
    private TransitUnloadingService transitUnloadingService;

    @Autowired
    private UnloadPortService unloadPortService;

    @Autowired
    private AgreementService agreementService;
    @Autowired
    private ShipDictionaryService shipDictionaryService;
    @Autowired
    URLBroker shipServer;
    @Autowired
    URLBroker wechatServer;

    public static final Log log = LogFactory
            .getLog(LogisticsTrackingController.class);


    @RequestMapping(UrlMapping.INDEX)
    public void index(ConfirmationSheetVO vo, CurrentUser user, ModelMap modelMap) {

        try {
            String confirmationSheetVoKey = "confirmationSheetVO";
            String voyageStartVOKey = "voyageStartVO";
            String loadingProgressKey = "loadingProgressVOList";
            String transitLoadingVOListKey = "transitLoadingVOList";
            String loadPortVOKey = "loadPortVO";
            String transitUnLoadingVOListKey = "transitUnLoadingVOList";
            String unloadPortVoKey = "unloadPortVo";
            //1、船舶确认表信息
            ConfirmationSheet confirmationSheet = confirmationSheetService.findByDemendsAndAgreement(vo);

            modelMap.put("imo", confirmationSheet.getImo());
            ConfirmationSheetVO confirmationSheetVO = new ConfirmationSheetVO();
            if (confirmationSheet != null) {
                confirmationSheetVO = confirmationSheetVO.domainToVO(confirmationSheet);
            }
            modelMap.addAttribute(confirmationSheetVoKey, confirmationSheetVO);
            if (confirmationSheet == null) {

            } else {
                // 2、航行开始信息
                VoyageStart voyageStart = voyageStartService.findByShipConfirmationSheetId(confirmationSheet.getConfirmationSheetId());
                VoyageStartVO voyageStartVO = new VoyageStartVO();
                if (voyageStart != null) {
                    voyageStartVO = voyageStartVO.domainToVO(voyageStart);
                }
                modelMap.addAttribute(voyageStartVOKey, voyageStartVO);


                // 2.1、装港进度信息
                List<LoadingProgress> loadingProgressList = new ArrayList<LoadingProgress>();
                LoadingProgressQuery loadingProgressQuery = new LoadingProgressQuery();
                loadingProgressQuery.setShipConfirmationSheetId(confirmationSheet.getConfirmationSheetId());
                loadingProgressList = loadingProgressService.findByConfirmationSheetId(loadingProgressQuery);
                List<LoadingProgressVO> loadingProgressVOList = new ArrayList<>();
                LoadingProgressVO loadingProgressVO = new LoadingProgressVO();
                for (LoadingProgress loadingProgress : loadingProgressList) {
                    LoadingProgressVO objectVo = loadingProgressVO.toObjectVo(loadingProgress);
                    loadingProgressVOList.add(objectVo);
                }
                modelMap.put(loadingProgressKey, loadingProgressVOList);

                // 3、装港前航程信息
                List<TransitLoading> transitLoadingList = new ArrayList<TransitLoading>();
                transitLoadingList = transitLoadingService.findByShipConfirmationSheetId(confirmationSheet.getConfirmationSheetId());
                List<TransitLoadingVO> transitLoadingVOList = new ArrayList<TransitLoadingVO>();
                TransitLoadingVO transitLoadingVO = new TransitLoadingVO();
                for (TransitLoading transitLoading : transitLoadingList) {
                    TransitLoadingVO objectVo = transitLoadingVO.toObjectVo(transitLoading);
                    transitLoadingVOList.add(objectVo);
                }
                modelMap.put(transitLoadingVOListKey, transitLoadingVOList);
                if (transitLoadingList.size() > 0) {
                    Date startTime = transitLoadingList.get(0).getDate();
                    modelMap.put("startLoadingTime", DateUtil.format(startTime, "yyyy-MM-dd"));
                    Date endTime = transitLoadingList.get(transitLoadingList.size() - 1).getDate();
                    modelMap.put("endLoadingTime", DateUtil.format(endTime, "yyyy-MM-dd"));

                }


                //4、装港信息
                LoadPortVO loadPortVO = new LoadPortVO();
                LoadPort loadPort = loadPortService.findByShipConfirmationSheetId(confirmationSheet.getConfirmationSheetId());
                if (loadPort != null) {
                    loadPortVO = loadPortVO.toObjectVo(loadPort);
                }
                modelMap.put(loadPortVOKey, loadPortVO);

                //5、卸港前信息
                List<TransitUnloading> transitUnloadingList = transitUnloadingService.findByConfirmationSheetId(confirmationSheet.getConfirmationSheetId());
                List<TransitUnloadingVO> transitUnLoadingVOList = new ArrayList<>();
                TransitUnloadingVO transitUnloadingVO = new TransitUnloadingVO();
                for (TransitUnloading transitUnloading : transitUnloadingList) {
                    TransitUnloadingVO objectVo = transitUnloadingVO.domainToVO(transitUnloading);
                    transitUnLoadingVOList.add(objectVo);
                }
                modelMap.put(transitUnLoadingVOListKey, transitUnLoadingVOList);
                if (transitUnloadingList.size() > 0) {
                    Date startTime = transitUnloadingList.get(0).getDatetime();
                    modelMap.put("startUnloadingTime", DateUtil.format(transitUnloadingList.get(0).getDatetime(), "yyyy-MM-dd"));
                    Date endTime = transitUnloadingList.get(transitUnLoadingVOList.size() - 1).getDatetime();
                    modelMap.put("endUnloadingTime", DateUtil.format(endTime, "yyyy-MM-dd"));

                }
                //6、卸港信息
                UnloadPort unloadPort = unloadPortService.findByShipConfirmationSheetId(confirmationSheet.getConfirmationSheetId());
                UnloadPortVO unloadPortVO = new UnloadPortVO();
                if (unloadPort != null) {
                    unloadPortVO = unloadPortVO.domainToVO(unloadPort);
                }
                modelMap.put(unloadPortVoKey, unloadPortVO);
            }

        } catch (Exception e) {
            log.error("物流追踪首页查询异常", e);
        }

    }

    /**
     * 查询船舶装港信息   船舶装港进度表
     *
     * @param confirmationUuid
     * @param user
     * @param modelMap
     */

    @RequestMapping(value = "findLoadPortDeatil.json")
    @ResponseBody
    public void findLoadPortDeatil(String confirmationUuid, CurrentUser user, ModelMap modelMap) {
        if (StringUtils.isNullOrEmpty(confirmationUuid)) {
            //
        } else {
            //根据UUID查询
            ConfirmationSheet confirmationSheet = confirmationSheetService.findByUuid(confirmationUuid);
            // domain转vo
            ConfirmationSheetVO confirmationSheetVO = new ConfirmationSheetVO();
            confirmationSheetVO = confirmationSheetVO.domainToVO(confirmationSheet);
            // 将confirmationSheetVO装入map，传到vm页面
            modelMap.addAttribute("confirmationSheetVO", confirmationSheetVO);

            //查询船舶装港信息
            LoadPort loadPort = loadPortService.findByShipConfirmationSheetId(confirmationSheet.getConfirmationSheetId());
            //船舶装港进度表
            LoadingProgress loadingProgress = loadingProgressService.findByConfirmationSheetId(confirmationSheet.getConfirmationSheetId());

            // toObjectVo
            LoadPortVO loadPortVO = new LoadPortVO();
            loadPortVO = loadPortVO.toObjectVo(loadPort);
            LoadingProgressVO loadingProgressVO = new LoadingProgressVO();
            loadingProgressVO = loadingProgressVO.toObjectVo(loadingProgress);

            // 将loadPortVO装入map，传到vm页面
            modelMap.addAttribute("loadPortVO", loadPortVO);
            modelMap.addAttribute("loadingProgressVO", loadingProgressVO);

        }
    }


    /**
     * 船舶在途信息查询
     *
     * @param confirmationUuid
     * @param user
     * @param modelMap
     */
    @RequestMapping(value = "unloadList.json", method = RequestMethod.GET)
    @ResponseBody
    public void unloadList(String confirmationUuid, CurrentUser user, ModelMap modelMap) {

        //根据UUID查询
        ConfirmationSheet confirmationSheet = confirmationSheetService.findByUuid(confirmationUuid);
        // domain转vo
        ConfirmationSheetVO confirmationSheetVO = new ConfirmationSheetVO();
        confirmationSheetVO = confirmationSheetVO.domainToVO(confirmationSheet);
        // 将confirmationSheetVO装入map，传到vm页面
        modelMap.addAttribute("confirmationSheetVO", confirmationSheetVO);

        // 获取船舶确认单id-confirmationSheetId
        Long confirmationSheetId = confirmationSheet.getConfirmationSheetId();

        // 根据船舶确认单id获取卸港前在途信息列表  transitUnloadingService
        List<TransitUnloading> transitUnloadingList = new ArrayList<TransitUnloading>();
        transitUnloadingList = transitUnloadingService.findByConfirmationSheetId(confirmationSheetId);
        List<TransitUnloadingVO> transitUnloadingVOList = new ArrayList<TransitUnloadingVO>();
        TransitUnloadingVO transitUnloadingVO = new TransitUnloadingVO();
        // domain转vo
        for (TransitUnloading transitUnloading : transitUnloadingList) {
            TransitUnloadingVO vo = transitUnloadingVO.domainToVO(transitUnloading);
            transitUnloadingVOList.add(vo);
        }
        // volist装入map传到vm
        modelMap.put("transitUnloadingVOList", transitUnloadingVOList);
    }

    /**
     * 查询船舶卸港信息
     *
     * @param confirmationUuid
     * @param user
     * @param modelMap
     */
    @RequestMapping(value = "findUnloadPortDeatil.json", method = RequestMethod.GET)
    @ResponseBody
    public void findUnloadPortDeatil(String confirmationUuid, CurrentUser user, ModelMap modelMap) {

        if (StringUtils.isNullOrEmpty(confirmationUuid)) {
            //
        } else {
            //根据UUID查询
            ConfirmationSheet confirmationSheet = confirmationSheetService.findByUuid(confirmationUuid);
            if (confirmationSheet != null) {
                //获取绝对路径
                if (!StringHelper.isNullOrEmptyString(confirmationSheet.getUploadQ88())) {
                    confirmationSheet.setUploadQ88(imgurl + "/" + confirmationSheet.getUploadQ88());
                }
                if (!StringHelper.isNullOrEmptyString(confirmationSheet.getUploadCp())) {
                    confirmationSheet.setUploadCp(imgurl + "/" + confirmationSheet.getUploadCp());
                }
//                if(confirmationSheet.getUploadQ88().length()>0) {
//                    confirmationSheet.setUploadQ88(imgurl + "/" + confirmationSheet.getUploadQ88());
//                }
//                if(confirmationSheet.getUploadCp().length()>0) {
//                    confirmationSheet.setUploadCp(imgurl + "/" + confirmationSheet.getUploadCp());
//                }
            }
            // domain转vo
            ConfirmationSheetVO confirmationSheetVO = new ConfirmationSheetVO();
            confirmationSheetVO = confirmationSheetVO.domainToVO(confirmationSheet);
            // 将confirmationSheetVO装入map，传到vm页面
            modelMap.addAttribute("confirmationSheetVO", confirmationSheetVO);

            // 根据ConfirmationSheetId查询船舶卸港信息
            UnloadPort unloadPort = unloadPortService.findByShipConfirmationSheetId(confirmationSheet.getConfirmationSheetId());
            UnloadPortVO unloadPortVO = new UnloadPortVO();
            unloadPortVO = unloadPortVO.domainToVO(unloadPort);

            // 将unloadPortVO装入map，传到vm页面
            modelMap.addAttribute("unloadPortVO", unloadPortVO);
        }
    }

    /**
     * 物流跟踪（微信自用？？）
     */
    @ResponseBody
    @RequestMapping(value = UrlMapping.WECHAT_GET_ALL_DATAS, method = RequestMethod.POST)
    public ResultDatas<Map<String, Object>> getAllDatas(@RequestBody ConfirmationSheetVO vo, CurrentUser user) {
        ResultDatas<Map<String, Object>> res = new ResultDatas<>();
        Map<String, Object> map = new HashMap<>();
        HashMap<String, DictionaryVO> seamap = shipDictionaryService.LoadPortSeaStatusMap();
        try {
            if (StringUtils.isNullOrEmpty(vo.getUuid())) {
                log.error("confirmationUuid参数为空");
                throw new TransportException(TransportException.TYPE.ITSH116);
            } else {
                String confirmationSheetVoKey = "confirmationSheetVO";
                String voyageStartVOKey = "voyageStartVO";
                String transitLoadingVOListKey = "transitLoadingVOList";
                String loadPortVOKey = "loadPortVO";
                String transitUnLoadingVOListKey = "transitUnLoadingVOList";
                String unloadPortVoKey = "unloadPortVo";
                //1、船舶确认表信息
                ConfirmationSheetVO confirmationSheetVO = new ConfirmationSheetVO();
                ConfirmationSheet confirmationSheet = null;
                if (vo.getType() == 1) {//确认单
                    confirmationSheet = confirmationSheetService.findByUuid(vo.getUuid());
                    if (confirmationSheet != null) {
                        if (confirmationSheet != null) {
                            //获取绝对路径
                            if (!StringHelper.isNullOrEmptyString(confirmationSheet.getUploadQ88())) {
                                confirmationSheet.setUploadQ88(imgurl + "/" + confirmationSheet.getUploadQ88());
                            }
                            if (!StringHelper.isNullOrEmptyString(confirmationSheet.getUploadCp())) {
                                confirmationSheet.setUploadCp(imgurl + "/" + confirmationSheet.getUploadCp());
                            }

                        }
                        confirmationSheetVO = confirmationSheetVO.domainToVO(confirmationSheet);
                    }
//                    else
//                    {
//                        res.setFail("确认单为空");
//                        log.info("确认单为空");
//                        return  res;
//                    }
                } else {
                    Agreement agreement = agreementService.findByUuid(vo.getUuid());
                    if (agreement != null) {
                        confirmationSheet = confirmationSheetService.findByAgreementId(agreement.getAgreementId());
                        if (confirmationSheet != null) {
                            //获取绝对路径
                            if (!StringHelper.isNullOrEmptyString(confirmationSheet.getUploadQ88())) {
                                confirmationSheet.setUploadQ88(imgurl + "/" + confirmationSheet.getUploadQ88());
                            }
                            if (!StringHelper.isNullOrEmptyString(confirmationSheet.getUploadCp())) {
                                confirmationSheet.setUploadCp(imgurl + "/" + confirmationSheet.getUploadCp());
                            }

                            confirmationSheetVO = confirmationSheetVO.domainToVO(confirmationSheet);
                        }

                    }
                }
                map.put(confirmationSheetVoKey, confirmationSheetVO);
                if (confirmationSheet == null) {
                    res.setFail("确认单为空");
                    log.info("确认单为空");
                    return res;
                } else {
                    // 2、航行开始信息
                    VoyageStart voyageStart = voyageStartService.findByShipConfirmationSheetId(confirmationSheet.getConfirmationSheetId());
                    //domain转vo
                    VoyageStartVO voyageStartVO = new VoyageStartVO();
                    if (voyageStart != null) {
                        if (!StringHelper.isNullOrEmptyString(voyageStart.getAccessory())) {
                            voyageStart.setAccessory(imgurl + "/" + voyageStart.getAccessory());
                        }

                        voyageStartVO = voyageStartVO.domainToVO(voyageStart);
                    }
                    //将voyageStartVO装入map，传到vm页面
                    map.put(voyageStartVOKey, voyageStartVO);
                    //船舶装港进度表
                    List<LoadingProgress> loadingProgressList = loadingProgressService.findListByConfirmationSheetId(confirmationSheet.getConfirmationSheetId());

                    if (loadingProgressList != null) {
                        List<LoadingProgressVO> loadingProgressListVO = new ArrayList<>();
                        LoadingProgressVO loadingProgressVO = new LoadingProgressVO();
                        for (LoadingProgress loadingProgress2 : loadingProgressList) {


                            LoadingProgressVO loadingProgressVO2 = loadingProgressVO.toObjectVo(loadingProgress2);
                            loadingProgressListVO.add(loadingProgressVO2);
                        }
                        map.put("loadingProgressListVO", loadingProgressListVO);
                    }
                    // 3、装港前航程信息
                    List<TransitLoading> transitLoadingList = new ArrayList<TransitLoading>();
                    transitLoadingList = transitLoadingService.findByShipConfirmationSheetId(confirmationSheet.getConfirmationSheetId());

                    List<TransitLoadingVO> transitLoadingVOList = new ArrayList<TransitLoadingVO>();
                    TransitLoadingVO transitLoadingVO = new TransitLoadingVO();

                    for (TransitLoading transitLoading : transitLoadingList) {
                        //设置sea字段
                        transitLoading.setSeaId(transitLoading.getSea());
                        if (seamap.get(transitLoading.getSea()) != null) {
                            transitLoading.setSea(seamap.get(transitLoading.getSea()).getEnName());
                        }

                        TransitLoadingVO objectVo = transitLoadingVO.toObjectVo(transitLoading);
                        transitLoadingVOList.add(objectVo);
                    }
                    map.put(transitLoadingVOListKey, transitLoadingVOList);

                    //4、装港信息
                    LoadPortVO loadPortVO = new LoadPortVO();
                    LoadPort loadPort = null;

                    loadPort = loadPortService.findByShipConfirmationSheetId(confirmationSheet.getConfirmationSheetId());

                    if (loadPort != null) {
                        loadPortVO = loadPortVO.toObjectVo(loadPort);
                    }
                    map.put(loadPortVOKey, loadPortVO);

                    //5、卸港前信息
                    List<TransitUnloading> transitUnloadingList = transitUnloadingService.findByConfirmationSheetId(confirmationSheet.getConfirmationSheetId());
                    List<TransitUnloadingVO> transitUnLoadingVOList = new ArrayList<>();
                    for (TransitUnloading transitUnloading : transitUnloadingList) {
                        //设置sea字段
                        transitUnloading.setSeaId(transitUnloading.getSea());
                        if (seamap.get(transitUnloading.getSea()) != null) {
                            transitUnloading.setSea(seamap.get(transitUnloading.getSea()).getEnName());
                        }

                        TransitUnloadingVO transitUnloadingVO = new TransitUnloadingVO();
                        TransitUnloadingVO objectVo = transitUnloadingVO.domainToVO(transitUnloading);
                        transitUnLoadingVOList.add(objectVo);
                    }
                    map.put(transitUnLoadingVOListKey, transitUnLoadingVOList);
                    //6、卸港信息
                    UnloadPort unloadPort = unloadPortService.findByShipConfirmationSheetId(confirmationSheet.getConfirmationSheetId());
                    UnloadPortVO unloadPortVO = new UnloadPortVO();
                    if (unloadPort != null) {
                        unloadPortVO = unloadPortVO.domainToVO(unloadPort);
                    }
                    map.put(unloadPortVoKey, unloadPortVO);
                }
            }
            res.setDatas(map);
        } catch (BizException e) {
            log.error("wechat getAllDatas error", e);
            res.setMessage(e.getMessage());
        } catch (Exception e) {
            log.error("wechat getAllDatas error", e);
            res.setMessage("系统异常");
        }
        return res;
    }

    /*
    获取地图轨迹 微信
     */
    @RequestMapping(value = UrlMapping.WECHAT_GET_IMOWAYPOINT, method = RequestMethod.POST)
    public ResultDatas<List<WayPointVO>> Getmmsi(@RequestBody String imo,String openId,Integer num) {
        ResultDatas<List<WayPointVO>> resultDatas = new ResultDatas<List<WayPointVO>>();
        try {
            if (StringHelper.isNullOrEmptyString(imo)) {
                resultDatas.setFail("imo is null");
                return resultDatas;
            }
            if (StringHelper.isNullOrEmptyString(openId)) {
                resultDatas.setFail("openId is null");
                return resultDatas;
            }
            //通过openid拿原油的token
            String wxurl=wechatServer.get("/getmembertoken.json?openId="+openId).toString();
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.add("Content-Type", "text/plain");
            RestTemplate template = new RestTemplate();
            HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
            ResponseEntity<String> response = template.exchange(wxurl, HttpMethod.GET, requestEntity, String.class);
            String sttr = response.getBody();
            Gson gson=new Gson();
            ResultDatas<String> tokendata=gson.fromJson(sttr,ResultDatas.class);
            if(tokendata==null)
            {
                resultDatas.setFail("token is null");
                return resultDatas;
            }
            String token=tokendata.getDatas();
            if(StringHelper.isNullOrEmptyString(token))
            {
                resultDatas.setFail("token is null");
                return resultDatas;
            }

            String url = shipServer.get("/api/geojson/imowaypoint.json").toString();
            url+="?oauth_token="+token;
            // 调用接口
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());
            //  String jsonstr= String.format("{\"imo\":\"%s\"}",imo);//JsonUtilsHelper.hashMap2Json(bobymap);
            HttpEntity<String> formEntity = new HttpEntity<String>(imo, headers);
            RestTemplate restTemplate = new RestTemplate();
            String json = restTemplate.postForObject(url, formEntity, String.class);

            Type objectType = new TypeToken<ResultDatas<ArrayList<WayPointVO>>>() {
            }.getType();
            resultDatas = gson.fromJson(json, objectType);
            if (resultDatas.getStatus() == 0) {
                // num=num==null?10:num;//前端接受最大数量
                if(num!=null && num>0&& resultDatas.getDatas().size()>num) {
                    int multiple=resultDatas.getDatas().size()/num;
                    if(multiple>0) {
                        List<WayPointVO> list = new ArrayList<>();
                        int i = 0;
                        for (WayPointVO vo : resultDatas.getDatas()) {
                            if (i % multiple == 0) {
                                list.add(vo);
                            }
                            i++;
                        }
                        resultDatas.setDatas(list);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultDatas;

    }
//    /*
//    获取mmsi 微信
//     */
//    @RequestMapping(value = "wechat/getmmsi.json",method = RequestMethod.GET)
//    public ResultDatas<String> Getmmsi(String imo)
//    {
//        ResultDatas<String> resultDatas=new ResultDatas<String>();
//        String mmsi="";
//        try {
//            mmsi= transitLoadingService.findShipTrackList(imo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        resultDatas.setDatas(mmsi);
//        return  resultDatas;
//
//    }
    /**
     * 物流跟踪 微信API 根据Uuid查询船舶确认单信息
     * @author wh
     */
    /*@ResponseBody
    @RequestMapping(value=UrlMapping.WECHAT_GETCONFIRMATIONSHEET, method = RequestMethod.POST)
	public ResultDatas<ConfirmationSheet> getConfirmationSheet(String confirmationUuid, CurrentUser user){
		System.out.println("********根据Uuid查询船舶确认单信息**********");
		ResultDatas<ConfirmationSheet> res = new ResultDatas<>();
		try {
			if(StringUtils.isNullOrEmpty(confirmationUuid)){
				throw new TransportException(TransportException.TYPE.ITSH116);
			}
			//根据UUID查询
			ConfirmationSheet confirmationSheet = confirmationSheetService.findByUuid(confirmationUuid);
			res.setDatas(confirmationSheet);
		} catch (BizException e) {
			log.error("getConfirmationSheet error",e);
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("getConfirmationSheet error",e);
			res.setMessage("系统异常");
		}
		return res;
	}
	
	*//**
     * 物流跟踪 微信API 第一步 配载计划查询 && 查看航程信息
     * @author wh
     *//*
    @ResponseBody
	@RequestMapping(value = UrlMapping.WECHAT_GETSTOWAGEPLAN, method = RequestMethod.POST)
	public ResultDatas<Map<String, Object>> getStowagePlan(@RequestBody ConfirmationSheetVO vo, CurrentUser user){
		System.out.println("******第一步** 配载计划查询 && 查看航程信息**********");
		ResultDatas<Map<String, Object>> res = new ResultDatas<>();
		try {
			if(StringUtils.isNullOrEmpty(vo.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH116);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			//根据UUID查询
			ConfirmationSheet confirmationSheet = confirmationSheetService.findByUuid(vo.getUuid());
			VoyageStart voyageStart = null;
			if (confirmationSheet != null) {
				ConfirmationSheetVO confirmationSheetVO = new ConfirmationSheetVO();
				confirmationSheetVO = confirmationSheetVO.domainToVO(confirmationSheet);
				map.put("confirmationSheetVO", confirmationSheetVO);
				voyageStart = voyageStartService.findByShipConfirmationSheetId(confirmationSheet.getConfirmationSheetId());
			}
			// 配载计划查询
			if(voyageStart != null){
				VoyageStartVO voyageStartVO = new VoyageStartVO();
				voyageStartVO = voyageStartVO.domainToVO(voyageStart);
				map.put("voyageStartVO",voyageStartVO);				
			}
			
			// 查看航程信息
			List<TransitLoading> transitLoadingList = new ArrayList<TransitLoading>();
			transitLoadingList = transitLoadingService.findByShipConfirmationSheetId(confirmationSheet.getConfirmationSheetId());
			List<TransitLoadingVO> transitLoadingVOList = new ArrayList<TransitLoadingVO>();
			TransitLoadingVO transitLoadingVO = new TransitLoadingVO();
			for (TransitLoading transitLoading : transitLoadingList) {
				TransitLoadingVO objectVo = transitLoadingVO.toObjectVo(transitLoading);
				transitLoadingVOList.add(objectVo);
			}
			res.setDatas(map);
		} catch (BizException e) {
			log.error("getStowagePlan error",e);
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("getStowagePlan error",e);
			res.setMessage("系统异常");
		}
		return res;
	}

	
	*//**
     * 物流跟踪 微信API 第二步 查询船舶装港信息   船舶装港进度表
     * @author wh
     *//*
	@ResponseBody
	@RequestMapping(value = UrlMapping.WECHAT_GETLOADPORTDEATIL, method = RequestMethod.POST)
	public ResultDatas<Map<String, Object>> getLoadPortDeatil(@RequestBody ConfirmationSheetVO vo, CurrentUser user){
		System.out.println("******第二步**查询船舶装港信息   船舶装港进度表**********");
		ResultDatas<Map<String, Object>> res = new ResultDatas<>();
		try {
			if(StringUtils.isNullOrEmpty(vo.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH116);
			}
			Map<String, Object> map = new HashMap<>();
			//根据UUID查询
			ConfirmationSheet confirmationSheet = confirmationSheetService.findByUuid(vo.getUuid());
			if (confirmationSheet != null) {
				ConfirmationSheetVO confirmationSheetVO = new ConfirmationSheetVO();
				confirmationSheetVO = confirmationSheetVO.domainToVO(confirmationSheet);
				map.put("confirmationSheetVO", confirmationSheetVO);				
			}
			//查询船舶装港信息
			LoadPort loadPort = loadPortService.findByShipConfirmationSheetId(confirmationSheet.getConfirmationSheetId());
			//船舶装港进度表
			LoadingProgress loadingProgress = loadingProgressService.findByConfirmationSheetId(confirmationSheet.getConfirmationSheetId());
			
			// toObjectVo
			if(loadPort != null){
				LoadPortVO loadPortVO = new LoadPortVO();
				loadPortVO = loadPortVO.toObjectVo(loadPort);
				map.put("loadPortVO", loadPortVO);				
			}
			if(loadingProgress != null){
				LoadingProgressVO loadingProgressVO = new LoadingProgressVO();
				loadingProgressVO = loadingProgressVO.toObjectVo(loadingProgress);
				map.put("loadingProgressVO", loadingProgressVO);				
			}	
			res.setDatas(map);
		} catch (BizException e) {
			log.error("getLoadPortDeatil error",e);
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("getLoadPortDeatil error",e);
			res.setMessage("系统异常");
		}
		return res;
	}
	
	
	*//**
     * 物流跟踪 微信API 第三步 船舶在途信息查询
     * @author wh
     *//*
	@ResponseBody
	@RequestMapping(value = UrlMapping.WECHAT_GETUNLOADLIST, method = RequestMethod.POST)
	public ResultDatas<Map<String, Object>> getUnloadList(@RequestBody ConfirmationSheetVO vo, CurrentUser user){
		System.out.println("******第三步**船舶在途信息查询**********");
		ResultDatas<Map<String, Object>> res = new ResultDatas<>();
		try {
			if(StringUtils.isNullOrEmpty(vo.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH116);
			}
			Map<String, Object> map = new HashMap<>();
			//根据UUID查询
			ConfirmationSheet confirmationSheet = confirmationSheetService.findByUuid(vo.getUuid());
			if (confirmationSheet != null) {
				ConfirmationSheetVO confirmationSheetVO = new ConfirmationSheetVO();
				confirmationSheetVO = confirmationSheetVO.domainToVO(confirmationSheet);
				map.put("confirmationSheetVO", confirmationSheetVO);				
			}
			// 获取船舶确认单id-confirmationSheetId
			Long confirmationSheetId = confirmationSheet.getConfirmationSheetId();

			// 根据船舶确认单id获取卸港前在途信息列表  transitUnloadingService
			List<TransitUnloading> transitUnloadingList = new ArrayList<TransitUnloading>();
			transitUnloadingList = transitUnloadingService.findByConfirmationSheetId(confirmationSheetId);
			List<TransitUnloadingVO> transitUnloadingVOList = new ArrayList<TransitUnloadingVO>();
			TransitUnloadingVO transitUnloadingVO = new TransitUnloadingVO();
			// domain转vo
			for (TransitUnloading transitUnloading : transitUnloadingList) {
				TransitUnloadingVO vo2 = transitUnloadingVO.domainToVO(transitUnloading);
				transitUnloadingVOList.add(vo2);
			}				
			map.put("transitUnloadingVOList", transitUnloadingVOList);
			res.setDatas(map);
		} catch (BizException e) {
			log.error("getUnloadList error",e);
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("getUnloadList error",e);
			res.setMessage("系统异常");
		}
		return res;
	}
	
	
	*//**
     * 物流跟踪 微信API 第四步 查询船舶卸港信息
     * @author wh
     *//*
	@ResponseBody
	@RequestMapping(value = UrlMapping.WECHAT_GETFINDUNLOADPORTDEATIL,  method = RequestMethod.POST)
	public ResultDatas<Map<String, Object>> getFindUnloadPortDeatil(@RequestBody ConfirmationSheetVO vo, CurrentUser user){
		System.out.println("******第四步**查询船舶卸港信息**********");
		ResultDatas<Map<String, Object>> res = new ResultDatas<>();
		try {
			if(StringUtils.isNullOrEmpty(vo.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH116);
			}
			Map<String, Object> map = new HashMap<>();
			//根据UUID查询
			ConfirmationSheet confirmationSheet = confirmationSheetService.findByUuid(vo.getUuid());
			if (confirmationSheet != null) {
				ConfirmationSheetVO confirmationSheetVO = new ConfirmationSheetVO();
				confirmationSheetVO = confirmationSheetVO.domainToVO(confirmationSheet);
				map.put("confirmationSheetVO", confirmationSheetVO);				
			}
			// 根据ConfirmationSheetId查询船舶卸港信息
			UnloadPort unloadPort = unloadPortService.findByShipConfirmationSheetId(confirmationSheet.getConfirmationSheetId()); 
			if(unloadPort != null){
				UnloadPortVO unloadPortVO = new UnloadPortVO();
				unloadPortVO = unloadPortVO.domainToVO(unloadPort);
				map.put("unloadPortVO", unloadPortVO);				
			}
			res.setDatas(map);
		} catch (BizException e) {
			log.error("getFindUnloadPortDeatil error",e);
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("getFindUnloadPortDeatil error",e);
			res.setMessage("系统异常");
		}
		return res;
	}
	
	*/
}

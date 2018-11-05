package com.sinochem.crude.trade.shipping.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.model.vo.DictionaryVO;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.constant.Constants;
import com.sinochem.crude.trade.shipping.constant.UrlMapping;
import com.sinochem.crude.trade.shipping.domain.ConfirmationSheet;
import com.sinochem.crude.trade.shipping.domain.TransitLoading;
import com.sinochem.crude.trade.shipping.model.query.TransitLoadingQuery;
import com.sinochem.crude.trade.shipping.model.vo.ConfirmationSheetVO;
import com.sinochem.crude.trade.shipping.model.vo.TransitLoadingRequseVo;
import com.sinochem.crude.trade.shipping.model.vo.TransitLoadingVO;
import com.sinochem.crude.trade.shipping.service.ConfirmationSheetService;
import com.sinochem.crude.trade.shipping.service.ShipDictionaryService;
import com.sinochem.crude.trade.shipping.service.TransitLoadingService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.common.result.ResultDatas;

/**
 * 船舶在途装刚前 controller
 *
 * @author zhaoyulong
 */
@Controller
@RequestMapping(UrlMapping.PRODUCT_BACK_TRANSITLOADING)
public class TransitLoadingController {

    public static final Log log = LogFactory.getLog(TransitLoadingController.class);
    @Autowired
    private TransitLoadingService transitLoadingService;
    
    @Autowired
	private ShipDictionaryService shipDictionaryService;
    @Autowired
    private ConfirmationSheetService confirmationSheetService;//确认单接口

    @RequestMapping(value = UrlMapping.INDEX)
    public void transitLoadingQuery(
            TransitLoadingVO vo, ModelMap map, CurrentUser currentUser, String confUUid, String tableMaxFlag, String step) {

        TransitLoadingQuery domain = new TransitLoadingQuery();
        if (StringUtil.isBlank(confUUid)) {
            //return 1;
        }
        //查询确认单主键
        ConfirmationSheet findByUuid = confirmationSheetService.findByUuid(confUUid);

        if (findByUuid == null) {
            //TODO 没有数据
        } else {

            //获取确认单domain
            ConfirmationSheetVO confirmationSheetVO = new ConfirmationSheetVO();
            //转确认单vo
            ConfirmationSheetVO domainToVO = confirmationSheetVO.domainToVO(findByUuid);

            domain.setShipConfirmationSheetId(findByUuid.getConfirmationSheetId());
            List<TransitLoading> voList = transitLoadingService.queryRecordsList(domain);

            if (null != voList) {

                List<TransitLoadingVO> listVo2 = vo.toListVo(voList);
                JSONArray fromObject = JSONArray.fromObject(listVo2);
                 Collection<DictionaryVO> values = shipDictionaryService.LoadPortSeaStatusMap().values();
                 JSONArray fromObject2 = JSONArray.fromObject(values);
                map.put("loadPortList", fromObject.toString());
                map.put("confObject", domainToVO);
                map.put("conuuid", confUUid);
                map.put("tableMaxFlag", tableMaxFlag);
                map.put("step", step);
                map.put("selectSea", fromObject2.toString());
            }
        }

    }

    /**
     * 查询航程数据  微信API端
     * @param currentUser
     * @param confUUid
     * @return
     */
    @WithoutAccess
    @RequestMapping(value = "queryTransitLoading.json")
    public ResultDatas<List<TransitLoadingVO>> queryTransitLoading(CurrentUser currentUser, String confUUid) {
        ResultDatas<List<TransitLoadingVO>> resultDatas = new ResultDatas<>();
        TransitLoadingQuery domain = new TransitLoadingQuery();
        if (StringUtil.isBlank(confUUid)) {
            //return 1;
        }
        //查询确认单主键
        ConfirmationSheet findByUuid = confirmationSheetService.findByUuid(confUUid);

        if (findByUuid == null) {
            //TODO 没有数据
        } else {
            domain.setShipConfirmationSheetId(findByUuid.getConfirmationSheetId());
            List<TransitLoading> transitLoadingList = transitLoadingService.queryRecordsList(domain);
            TransitLoadingVO vo = new TransitLoadingVO();
            List<TransitLoadingVO> voList = vo.toListVo(transitLoadingList);
            resultDatas.setDatas(voList);
        }
        return  resultDatas;
    }

    /**
     * 查询单条航程信息数据  微信API端
     * @param currentUser
     * @return
     */
    @WithoutAccess
    @RequestMapping(value = "getTransitLoading.json")
    public ResultDatas<TransitLoadingVO> getTransitLoading(CurrentUser currentUser, String uuid) {
        ResultDatas<TransitLoadingVO> resultDatas = new ResultDatas<>();
        TransitLoading transitLoading = transitLoadingService.findByUuid(uuid);
        TransitLoadingVO vo = new TransitLoadingVO();
        resultDatas.setDatas( vo.toObjectVo(transitLoading));
        return  resultDatas;
    }

    /**
     * 逻辑删除
     *
     * @param vo
     */
    @WithoutAccess
    @RequestMapping(value = "transitLoadingDelete.json",  method = RequestMethod.POST)
    public Result transitLoadingDelete(@RequestBody TransitLoadingVO vo) {
        Result result = new Result();
        try {
            if (null != vo) {
                TransitLoading domain = vo.getDomain();
                transitLoadingService.transitLocigDelete(domain);
            }
        } catch (BizException e) {
            result.setMessage("删除失败"+e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 逻辑删除 微信
     *
     * @param
     */
    @WithoutAccess
    @RequestMapping(value = "transitLoadingDeletewx.json",method = RequestMethod.POST)
    public Result transitLoadingDeletewx(@RequestBody String uuid) {
        Result result = new Result();
        try {
//            if (null != vo) {
//                Gson gson=new Gson();
            log.info("微信删除transitLoadingDeletewx--uuid："+uuid);
                TransitLoading domain = new TransitLoading();
                domain.setUuid(uuid);
//                TransitLoading domain = vo.getDomain();
                transitLoadingService.transitLocigDelete(domain);
         //  }
        } catch (BizException e) {
            log.error(e);
            result.setMessage("删除失败"+e.getMessage());
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 航程在途中信息新增
     *
     * @param vo
     * @param currentUser
     */
    @RequestMapping(value = UrlMapping.ADDLODING, method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> transitLoadingSave(@RequestBody TransitLoadingRequseVo vo, CurrentUser currentUser) {
        HashMap<String, Object> map =new HashMap<String, Object>();
        List<TransitLoading> listdomain = new ArrayList<TransitLoading>();
        if (null != vo) {
            List<TransitLoadingVO> parseArray = vo.getVolist();
            if (null != parseArray) {
                for (int i = 0; i < parseArray.size(); i++) {
                    TransitLoadingVO transitLoadingVO = parseArray.get(i);
                    if (null != transitLoadingVO) {
                        //转domain对象
                        TransitLoading domain = transitLoadingVO.getDomain();
                        listdomain.add(domain);
                    }
                }
            }
            try {
                Integer res = transitLoadingService.insertTransit(listdomain, currentUser, vo.getConUuid());
                if (res != null && res > 0) {
                	map.put("message", Constants.SAVE_TRU);
                	map.put("tableMaxFlag", vo.getTableMaxFlag());
                	map.put("confUUid", vo.getConUuid());
                } else {
                    map.put("message", Constants.SAVE_FALSE);
                }
            } catch (BizException e) {
                log.error("transitLoadingSave error", e);
                map.put("status", Constants.EXCEPTION_STATUS);
                map.put("message", e.getMessage());
            } catch (Exception e) {
                log.error("transitLoadingSave error", e);
                map.put("status", Constants.EXCEPTION_STATUS);
                map.put("message", Constants.EXCEPTION_MESSAGE);
            }
        }
        return map;
    }

    /**
     * 航程在途中信息新增  微信API
     *
     * @param vo
     * @param currentUser
     */
    @WithoutAccess
    @RequestMapping(value = "saveTransitLoading.json", method = RequestMethod.POST)
    @ResponseBody
    public Result saveTransitLoading(@RequestBody TransitLoadingVO vo, CurrentUser currentUser) {
        Result result = new Result();
        if (null != vo) {
            try {
                ConfirmationSheet confirmationSheet = confirmationSheetService.findByUuid(vo.getConfirmUUid());
                TransitLoading domain = vo.getDomain();
                domain.setShipConfirmationSheetId(confirmationSheet.getConfirmationSheetId());
                Integer res = transitLoadingService.saveTransitLoading(domain, currentUser);
                if (res != null && res > 0) {
                    result.setMessage("新增成功");
                } else {
                    result.setFail("新增失败");
                }
            } catch (BizException e) {
                log.error("transitLoadingSave error", e);
                result.setStatus(Constants.EXCEPTION_STATUS);
                result.setMessage(e.getMessage());
            } catch (Exception e) {
                log.error("transitLoadingSave error", e);
                result.setStatus(Constants.EXCEPTION_STATUS);
                result.setMessage(Constants.EXCEPTION_MESSAGE);
            }
        }
        return result;
    }

    /**
     * 查询航程跟踪要修改的对象
     *
     * @param vo
     * @param map
     * @return
     */
    @RequestMapping
    public Object transitLoadingUpdateQuery(TransitLoadingVO vo, ModelMap map) {
        if (null != vo) {
            if (StringUtil.isNotBlank(vo.getUuid())) {
                TransitLoading findByUuid = transitLoadingService.findByUuid(vo.getUuid());
                map.put("transitloading", findByUuid);
            }
        }
        return map;
    }

    /**
     * 修改
     * @param vo
     * @param currentUser
     */
    @RequestMapping(value = "updateLoding.json", method = RequestMethod.POST)
    public void transitLoadingUpdate(@RequestBody TransitLoadingVO vo, CurrentUser currentUser) {
        Result result = new Result();
        try {
            if (null != vo) {
                if (StringUtil.isNotBlank(vo.getUuid())) {
                    TransitLoading domain = vo.getDomain();
                    Integer updateRecordByUuid = transitLoadingService.updateRecordByUuid(domain);

                    if (updateRecordByUuid != null && updateRecordByUuid > 0) {
                        result.setMessage(""); //TODO
                    }
                }
            }
        } catch (BizException e) {
            result.setMessage(""); //TODO
            e.printStackTrace();
        }
    }


}

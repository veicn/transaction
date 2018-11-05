package com.sinochem.crude.trade.shipping.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.constant.Constants;
import com.sinochem.crude.trade.shipping.constant.UrlMapping;
import com.sinochem.crude.trade.shipping.domain.ConfirmationSheet;
import com.sinochem.crude.trade.shipping.domain.LoadPort;
import com.sinochem.crude.trade.shipping.domain.TransitUnloading;
import com.sinochem.crude.trade.shipping.domain.UnloadPort;
import com.sinochem.crude.trade.shipping.domain.VoyageStart;
import com.sinochem.crude.trade.shipping.model.vo.ConfirmationSheetVO;
import com.sinochem.crude.trade.shipping.model.vo.LoadPortVO;
import com.sinochem.crude.trade.shipping.model.vo.TransitUnloadingVO;
import com.sinochem.crude.trade.shipping.model.vo.UnloadPortVO;
import com.sinochem.crude.trade.shipping.model.vo.VoyageStartVO;
import com.sinochem.crude.trade.shipping.service.ConfirmationSheetService;
import com.sinochem.crude.trade.shipping.service.LoadPortService;
import com.sinochem.crude.trade.shipping.service.ShipDictionaryService;
import com.sinochem.crude.trade.shipping.service.TransitUnloadingService;
import com.sinochem.crude.trade.shipping.service.UnloadPortService;
import com.sinochem.crude.trade.shipping.service.VoyageStartService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.result.Result;

/**
 * @author Zhu Tao
 * @createtime 2018年3月10日下午4:33:45
 * @details 船舶在途卸港前信息维护
 */
@Controller
@RequestMapping(UrlMapping.PRODUCT_BACK_TRANSITUNLOADING)
// 开发时可以先把这个加上，这个是表示不做权限控制
public class TransitUnloadingController {

    @Autowired
    private TransitUnloadingService transitUnloadingService;
    @Autowired
    private ConfirmationSheetService confirmationSheetService;
    @Autowired
    private LoadPortService loadPortService;
    @Autowired
    private VoyageStartService voyageStartService;

    @Autowired
    private UnloadPortService unloadPortService;
    @Autowired
    private ShipDictionaryService shipDictionaryService;


    public static final Log log = LogFactory
            .getLog(TransitUnloadingController.class);

    //region 根据确认单VO对象获取维护配载计划页面-武刚鹏-2018年3月19日19:33:20

    /**
     * 武刚鹏 -2018年3月19日19:32:56
     * 配载计划、装港信息、在途信息、卸港信息维护
     * 进入船舶在途卸港前信息维护页面,获取装港信息及卸港前在途信息列表
     *
     * @return
     */
    @RequestMapping(UrlMapping.INDEX)
    public void index( ConfirmationSheetVO vo, CurrentUser currentUser,
                      ModelMap modelMap) {
//        1、定义界面返回的list和object key值
        List<TransitUnloadingVO> transitUnloadingVOList = new ArrayList<>();
        LoadPortVO loadPortVO = new LoadPortVO();
        String confirmationSheetIdKey = "confirmationSheetId";
        String confirmationSheetVoKey = "confirmationSheetVO";
        String voyageStartVOKey = "voyageStartVO";
        String tranListKey = "transitUnloadingVOList";
        String loadPortKey = "loadPortVO";
        String unloadPortKey = "unloadPortList";
        String unloadPortVoKey = "unloadPortVo";


        try {
            //1、根据VO对象获取确认单信息
            ConfirmationSheet confirmationSheet = confirmationSheetService
                    .findByDemendsAndAgreement(vo);
            if (confirmationSheet != null) {
                ConfirmationSheetVO confirmationSheetVO = new ConfirmationSheetVO();
                confirmationSheetVO = confirmationSheetVO.domainToVO(confirmationSheet);
                // 获取船舶确认单id-confirmationSheetId
                Long confirmationSheetId = confirmationSheet.getConfirmationSheetId();
                // 2、航行开始信息
                VoyageStart voyageStart = voyageStartService.findByShipConfirmationSheetId(confirmationSheet.getConfirmationSheetId());
                VoyageStartVO voyageStartVO = new VoyageStartVO();
                if(voyageStart!=null){
                    voyageStartVO = voyageStartVO.domainToVO(voyageStart);
                }
                //3、装港信息
                LoadPort loadPort = loadPortService.findByShipConfirmationSheetId(confirmationSheetId);
                if (loadPort != null) {
                    loadPortVO = loadPortVO.toObjectVo(loadPort);
                }
                // 4、根据船舶确认单id获取卸港前在途信息列表
                List<TransitUnloading> transitUnloadingList = transitUnloadingService.findByConfirmationSheetId(confirmationSheetId);
                for (TransitUnloading transitUnloading : transitUnloadingList) {
                    TransitUnloadingVO transitUnloadingVO = new TransitUnloadingVO();
                    transitUnloadingVO = transitUnloadingVO.domainToVO(transitUnloading);
                    transitUnloadingVOList.add(transitUnloadingVO);
                }

                //5、卸港港口信息
                Object[] portList = shipDictionaryService.PortStatusMap().values().toArray();

                //6、卸港信息
                UnloadPort unloadPort = unloadPortService.findByShipConfirmationSheetId(confirmationSheet.getConfirmationSheetId());
                UnloadPortVO unloadPortVO = new UnloadPortVO();
                if(unloadPort!= null){
                    unloadPortVO = unloadPortVO.domainToVO(unloadPort);
                }

                //确认单UUID
                modelMap.put(confirmationSheetIdKey, confirmationSheetId);
                //确认单实体
                modelMap.put(confirmationSheetVoKey,confirmationSheetVO);
                //航行开始
                modelMap.addAttribute(voyageStartVOKey, voyageStartVO);
                //装港信息
                modelMap.put(loadPortKey, loadPortVO);
                //卸港前在途信息
                modelMap.put(tranListKey, transitUnloadingVOList);
                //卸港港口信息
                modelMap.put(unloadPortKey, portList);
                //卸港信息
                modelMap.put(unloadPortVoKey,unloadPortVO);

            }

        } catch (Exception e) {
            log.error("getConfirmationSheet error", e);
        }

    }
    //endregion

    //region 保存--卸港前船舶在途信息添加 -武刚鹏-2018年3月19日19:30:54

    /**
     * 保存--卸港前船舶在途信息添加 -武刚鹏-2018年3月19日19:30:54
     *
     * @param vo
     * @param currentUser
     * @return
     */
    @RequestMapping(value = {"intransit.json"}, method = RequestMethod.POST)
    @ResponseBody
    public Result inTransit(@RequestBody TransitUnloadingVO vo,
                            CurrentUser currentUser) {

        Result result = null;
        try {
            TransitUnloading transitUnloading = new TransitUnloading();
            transitUnloading = vo.voToDomain();
            if (StringUtils.isNotBlank(transitUnloading.getShipConfirmationSheetId().toString())) {
                TransitUnloading tran = transitUnloadingService.findByUuid(vo.getUuid());
                Integer code = null;
                if (tran == null) {
                    code = transitUnloadingService.insertRecord(transitUnloading,currentUser);
                } else {
                    code = transitUnloadingService.updateRecordByUuid(transitUnloading);
                }
                if (code > 0) {
                    result = new Result();
                } else {
                    result.setStatus(Constants.EXCEPTION_STATUS);
                    result.setFail(Constants.SAVE_FALSE);
                }
            }
        } catch (BizException e) {
            log.error("TransitUnloadingController  inTransit", e);
            result.setFail(Constants.SAVE_FALSE);
            result.setStatus(Constants.EXCEPTION_STATUS);
        } catch (Exception e) {
            log.error("getConfirmationSheet error", e);
            result.setStatus(Constants.EXCEPTION_STATUS);
            result.setMessage(Constants.EXCEPTION_MESSAGE);
        } finally {
            return result;
        }
    }


    //endregion

    //region 根据uuid删除卸港在途信息 -武刚鹏-2018年3月19日19:31:47

    /**
     * 根据uuid删除卸港在途信息 -武刚鹏-2018年3月19日19:31:47
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = {"deleteTransitUnloading.json"}, method = RequestMethod.POST)
    @ResponseBody
    public Result deleteTransitUnloading(@RequestBody TransitUnloadingVO vo) {
        Result result = null;
        try {
            Boolean flag = transitUnloadingService.deleteRecordsByUuid(vo.getUuid());
            if (flag) {
                result = new Result();
            } else {
                result = new Result();
                result.setStatus(Constants.EXCEPTION_STATUS);
                result.setMessage("失败");
            }
        } catch (BizException e) {
            log.error("TransitUnloadingController  inTransit", e);
            result.setFail(Constants.SAVE_FALSE);
        } catch (Exception e) {
            log.error("getConfirmationSheet error", e);
            result.setStatus(Constants.EXCEPTION_STATUS);
            result.setMessage(Constants.EXCEPTION_MESSAGE);
        } finally {
            return result;
        }
    }
    //endregion
}

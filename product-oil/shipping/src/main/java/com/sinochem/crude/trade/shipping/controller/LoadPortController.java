package com.sinochem.crude.trade.shipping.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sinochem.crude.trade.common.model.vo.DictionaryVO;
import com.sinochem.crude.trade.shipping.enums.IndependentEnum;
import com.sinochem.crude.trade.shipping.service.*;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.validator.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.constant.Constants;
import com.sinochem.crude.trade.shipping.constant.UrlMapping;
import com.sinochem.crude.trade.shipping.domain.ConfirmationSheet;
import com.sinochem.crude.trade.shipping.domain.LoadPort;
import com.sinochem.crude.trade.shipping.domain.LoadingProgress;
import com.sinochem.crude.trade.shipping.model.query.LoadingProgressQuery;
import com.sinochem.crude.trade.shipping.model.vo.LoadPortVO;
import com.sinochem.crude.trade.shipping.model.vo.LoadingProgressVO;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.result.ResultDatas;

/**
 * 装货港controller
 *
 * @author zhaoyulong
 */
@Controller
@RequestMapping(UrlMapping.PRODUCT_BACK_LOADPORT)
public class LoadPortController {


    @Autowired
    private LoadPortService loadPortService;
    @Autowired
    private LoadingProgressService loadingProgressService;
    @Autowired
    private ShipDictionaryService shipDictionaryService;//下拉接口

    @Autowired
    private ConfirmationSheetService confirmationSheetService;

    @Autowired
    private MessageService messageService;

    public static final Log log = LogFactory.getLog(LoadPortController.class);

    /**
     * 装港信息查询
     *
     * @param vo
     * @param map
     */

    @RequestMapping(UrlMapping.INDEX)
    public void LoadPortQuery(LoadPortVO vo, ModelMap map) {
        if (null != vo) {
            if (StringUtil.isNotBlank(vo.getComUuid())) {


                //根据uuid 查询
                LoadPortVO loadPortVO = new LoadPortVO();
                ConfirmationSheet findByUuid2 = confirmationSheetService.findByUuid(vo.getComUuid());
                LoadPort findByUuid = null;
                if (null != findByUuid2) {
                    findByUuid = loadPortService.findByShipConfirmationSheetId(findByUuid2.getConfirmationSheetId());

                }

                //String comUuid = vo.getComUuid();
                //LoadPort findByUuid = loadPortService.confirmationfindbyuuid(vo.getComUuid());
                //数据为空证明 表里没数据 不需要查装港进度表
                if (null != findByUuid) {

                    LoadingProgressQuery loadingProgressQuery = new LoadingProgressQuery();

                    //Long类型
                    if (findByUuid.getShipConfirmationSheetId() != null) {
                        loadingProgressQuery.setShipConfirmationSheetId(findByUuid.getShipConfirmationSheetId());
                    }

                    if (findByUuid.getShipLoadPortId() != null) {
                        loadingProgressQuery.setShipLoadPortId(findByUuid.getShipLoadPortId().toString());
                    }

                    List<LoadingProgress> listDomain = loadingProgressService.findByConfirmationSheetId(loadingProgressQuery);
                    if (null != listDomain) {

                        LoadingProgressVO loadingProgressVO = new LoadingProgressVO();
                        List<LoadingProgressVO> listVo = loadingProgressVO.toListVo(listDomain);
                        JSONArray fromObject2 = JSONArray.fromObject(listVo);
                        map.put("progress", fromObject2.toString());
                    }
                    loadPortVO = vo.toObjectVo(findByUuid);
                }
                map.put("Independent", shipDictionaryService.ConfirmIndependentMap().values().toArray());
                map.put("portOfDisStart", shipDictionaryService.UnLoadPortMap().values().toArray());
                map.put("lortObject", loadPortVO);
            }

        }

    }

    /**
     * 装港信息查询  微信API
     *
     * @param vo
     */
    @WithoutAccess
    @RequestMapping("queryLoadPort.json")
    public ResultDatas<LoadPortVO> queryLoadPort(LoadPortVO vo, CurrentUser currentUser) {
        ResultDatas<LoadPortVO> resultDatas = new ResultDatas<>();
        if (null != vo) {
            if (StringUtil.isNotBlank(vo.getComUuid())) {
                //根据uuid 查询
                ConfirmationSheet confirmationSheet = confirmationSheetService.findByUuid(vo.getComUuid());
                if (confirmationSheet == null) {
                    LoadPort loadPort = new LoadPort();
                    resultDatas.setDatas(vo.toObjectVo(loadPort));
                    return resultDatas;
                }
                LoadPort findByUuid = loadPortService.findByUuid(confirmationSheet.getConfirmationSheetId() + "");
                if (findByUuid != null) {
                    //处理independentInspection 字段 数据库name，前端传code
                    for (IndependentEnum independentenum : IndependentEnum.values()) {
                        if (independentenum.getEnName().equals(findByUuid.getIndependentInspection())) {
                            findByUuid.setIndependentInspection(independentenum.getCode());
                            break;
                        }
                    }

                    LoadPortVO loadPortVO = vo.toObjectVo(findByUuid);
                    resultDatas.setDatas(loadPortVO);
                } else {
                    LoadPort loadPort = new LoadPort();
                    resultDatas.setDatas(vo.toObjectVo(loadPort));
                }
            }
        }
        return resultDatas;
    }


    /**
     * 装货港信息新增 （微信）
     *
     * @param vo
     * @param currentUser
     */
    @WithoutAccess
    @RequestMapping(value = UrlMapping.LOADINGPORTADD, method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> loadFirstStepSave(@RequestBody LoadPortVO vo, CurrentUser currentUser, String listarr, String loioo) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        if (null != vo) {
            List<LoadingProgressVO> progreeVoList = vo.getProgreeList();
            List<LoadingProgress> progreeList = new ArrayList<LoadingProgress>();
            if (null != vo.getProgreeList() && vo.getStep() != null && vo.getStep().equals(Constants.STEP_THREE)) {
                if (progreeVoList != null) {
                    for (int i = 0; i < progreeVoList.size(); i++) {

                        LoadingProgressVO loadingProgressVO = progreeVoList.get(i);
                        if (null != loadingProgressVO) {
                            LoadingProgress domain = loadingProgressVO.getDomain();
                            progreeList.add(domain);
                        }

                    }
                }
            }

            LoadPort domain = vo.getDomain();
            try {
                //add by fengzk  判断该确认单是否已经具有装港信息，如果有不许添加.
                if (StringHelper.isNullOrEmptyString(vo.getUuid())) {
                    LoadPort queryConfirmationSheetId = loadPortService.queryConfirmationSheetId(vo.getComUuid());
                    if (queryConfirmationSheetId != null) {
                        map.put("message", Constants.SAVE_FALSE + "装港信息已存在,不能新增");
                        return map;
                    }
                }

                //处理independentInspection 字段 数据库name，前端传code
                for (IndependentEnum independentenum : IndependentEnum.values()) {
                    if (independentenum.getCode().equals(domain.getIndependentInspection())) {
                        domain.setIndependentInspection(independentenum.getEnName());
                        break;
                    }
                }
                LoadPort loadport = null;
                if (domain.getUuid() != null) {
                    loadport = loadPortService.findByUuid(domain.getUuid());
                }
                ConfirmationSheet confirmationSheet = confirmationSheetService.findByUuid(domain.getComUuid());

                Integer res = loadPortService.firstStepSave(domain, currentUser, progreeList);

                if (res != null && res > 0) {
                    map.put("message", Constants.SAVE_TRU);
                    map.put("tableMaxFlag", vo.getTableMaxFlag());
                    map.put("step", vo.getStep());
                    map.put("confUUid", vo.getComUuid());
                    //add by fengzk  for 新增要返回uuid给前端 进行其他步修改而不是新增。
                    if (StringHelper.isNullOrEmptyString(vo.getUuid())) {
                        LoadPort loadPort = loadPortService.queryConfirmationSheetId(vo.getComUuid());
                        if (loadPort != null) {
                            map.put("uuid", loadPort.getUuid());
                        }
                    } else {
                        map.put("uuid", vo.getUuid());
                    }

                } else {
                    map.put("message", Constants.SAVE_FALSE);
                }
                map.put("status", "0");
                if (res > 0) {
                    if (domain.getStep() == Constants.STEP_TWO) {
                        messageService.sendSecondMessage(domain, confirmationSheet, loadport);
                    } else if (domain.getStep() == Constants.STEP_THREE) {
                        messageService.sendThreeMessage(domain, confirmationSheet, loadport);
                        messageService.sendThreeProcessMessage(confirmationSheet);
                    } else if (domain.getStep() == Constants.STEP_FOUR) {
                        messageService.sendFourMessage(domain, confirmationSheet, loadport);
                    }
                }
            } catch (BizException e) {
                log.error("loadFirstStepSave error", e);
                map.put("status", Constants.EXCEPTION_STATUS);
                map.put("message", e.getMessage());
            } catch (Exception e) {
                log.error("loadFirstStepSave error", e);
                map.put("status", Constants.EXCEPTION_STATUS);
                map.put("message", Constants.EXCEPTION_MESSAGE);
            }
        }
        return map;
    }

    /**
     * 船舶在途装港前查询下拉框
     *
     * @param map
     * @return
     */
    @WithoutAccess
    @RequestMapping(value = UrlMapping.QUERYSELECT, method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> querySelectKey(ModelMap map) {
        map.put("selectSea", shipDictionaryService.LoadPortSeaStatusMap().values().toArray());

        return map;
    }

    /**
     * 船舶在途装港前查询港口下拉框
     *
     * @param map
     * @return
     */
    @WithoutAccess
    @RequestMapping(value = UrlMapping.QUERYSELECTPORT, method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> querySelectKeyPort(ModelMap map) {
        map.put("selectSeaPort", shipDictionaryService.UnLoadPortMap().values().toArray());

        return map;
    }

    /**
     * 租船代理状态下拉框
     *
     * @param map
     * @return
     */
    @WithoutAccess
    @RequestMapping(value = UrlMapping.QUERYINDEPENDENT, method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> querySelectIndependent(ModelMap map) {
        map.put("selectIndependent", shipDictionaryService.ConfirmIndependentMap().values().toArray());

        return map;
    }


//	/**
//	 * 卸港港口信息查询下拉框 微信
//	 * @param
//	 * @return
//	 */
//	@WithoutAccess
//	@RequestMapping(value="portStatusenum.json")
//	public ResultDatas<List<DictionaryVO>> GetPortStatus(){
//
//		ResultDatas<List<DictionaryVO>> resultDatas=new  ResultDatas<List<DictionaryVO>>();
//		List<DictionaryVO> list=new ArrayList<>();
//		DictionaryVO dictionaryVO=null;
//		for(String key: shipDictionaryService.PortStatusMap().keySet())
//		{
//			dictionaryVO=shipDictionaryService.PortStatusMap().get(key);
//			list.add(dictionaryVO);
//		}
//		resultDatas.setDatas(list);
//		return resultDatas;
//	}


}

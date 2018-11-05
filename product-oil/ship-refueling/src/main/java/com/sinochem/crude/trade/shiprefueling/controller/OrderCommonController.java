package com.sinochem.crude.trade.shiprefueling.controller;

import com.sinochem.crude.trade.shiprefueling.constants.Constants;
import com.sinochem.crude.trade.shiprefueling.controller.common.ResultDatas;
import com.sinochem.crude.trade.shiprefueling.domain.po.Info;
import com.sinochem.crude.trade.shiprefueling.domain.po.TDataDict;
import com.sinochem.crude.trade.shiprefueling.enums.*;
import com.sinochem.crude.trade.shiprefueling.service.DataDictService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.*;

/**
 * 船燃船供订单模块的公用功能
 */
@Controller
@RequestMapping(value = "ordercommon")
@WithoutAccess
public class OrderCommonController {

    @Autowired
    private DataDictService dataDictService;


    /**
     * 获取运输方式下拉列表
     *
     * @return
     */
    @RequestMapping(value = "tranportways.json", method = {RequestMethod.GET ,RequestMethod.POST})
    @ResponseBody
    public ResultDatas getTransportWays(String oilType) {
        ResultDatas res = new ResultDatas();
        try {
            if(StringUtils.isEmpty(oilType)){
                throw new BizException("oilType不能为空");
            }
            ArrayList<Object> list = new ArrayList<>();
            if(Constants.INFO_TYPE_CRUDE.equals(oilType)){
                OilCrudeTypeOfShippingEnums[] values = OilCrudeTypeOfShippingEnums.values();
                for (OilCrudeTypeOfShippingEnums value : values) {
                    wrapperSelectsValue(list , value);
                }
            }else {
                TypeOfShippingEnums[] values = TypeOfShippingEnums.values();
                for (TypeOfShippingEnums value : values) {
                    wrapperSelectsValue(list , value);
                }
            }
            res.setDatas(list);
        } catch (Exception e) {
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        }
        return res;
    }

    /**
     * 船加油获得油品类别下拉列表
     * oilType:100
     * oilType:10
     */
    @RequestMapping(value = "oilvarieties.json", method = RequestMethod.GET)
    @ResponseBody
    public ResultDatas getOilVarieties(String oilType){
        ResultDatas res = new ResultDatas();
        try {
            Assert.hasText(oilType);
            List<Map<String , String>> list = null;
            if(oilType.equals(Constants.INFO_TYPE_SHIP)){
                list = dataDictService.getOilClassificationDataDict(Constants.DAT_DICT_TYPE_CODE_OIL_CLASSIFICATION ,
                        Constants.DAT_DICT_P_DICT_CODE_SHIP);
            }else if(oilType.equals(Constants.INFO_TYPE_CRUDE)){
                list = dataDictService.getOilClassificationDataDict(Constants.DAT_DICT_TYPE_CODE_OIL_CLASSIFICATION ,
                        Constants.DAT_DICT_P_DICT_CODE_CRUDE);
            }
            //将其它放到最后
            sortList(list);

            res.setDatas(list);
        } catch (Exception e) {
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
            e.printStackTrace();
        }
         return res;
    }



    /**
     * 油规格下拉列表
     * @return
     */
    @RequestMapping(value = "oilppms.json", method = RequestMethod.GET)
    @ResponseBody
    public ResultDatas getPPMTyps(@RequestParam String oilvarietie) {
        ResultDatas res = new ResultDatas();
        try {
            Assert.hasText(oilvarietie , "油品类别不能为空");

            List<Map<String , String>> list = dataDictService.getOilClassificationDataDict(Constants.DAT_DICT_TYPE_CODE_OIL_VARIETIES ,
                    oilvarietie);
            sortList(list);
            res.setDatas(list);
        }catch (Exception e) {
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        }

        return res;
    }


    private List<Map<String , String>> wrapperSelectsValue(List list , IEnums t){
        HashMap<Object, Object> map = new HashMap<>();
        map.put("code", t.getCode());
        map.put("enName", t.getEnName());
        map.put("zhName", t.getZhName());
        list.add(map);
        return list;
    }

    private void sortList(List<Map<String, String>> list){
        Collections.sort(list, new Comparator<Map<String, String>>() {
            @Override
            public int compare(Map<String, String> o1, Map<String, String> o2) {
                boolean f1 = o1.containsValue("其它");
                boolean f2 = o2.containsValue("其它");
                if(f1 && f2 ){
                    return 0;
                }else if(f1){
                    return 1;
                }
                return -1;
            }
        });
    }

}

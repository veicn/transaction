package com.sinochem.crude.trade.shiprefueling.controller;

import com.sinochem.crude.trade.shiprefueling.constants.Constants;
import com.sinochem.crude.trade.shiprefueling.constants.UrlMapping;
import com.sinochem.crude.trade.shiprefueling.controller.common.BeanConvertUtils;
import com.sinochem.crude.trade.shiprefueling.controller.common.ResultDatas;
import com.sinochem.crude.trade.shiprefueling.model.vo.GoryVO;
import com.sinochem.crude.trade.shiprefueling.service.GoryService;
import com.sinochem.it.b2b.member.access.WithoutAccess;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


/**
 * 订单品类控制器
 * 
 * @author niuwk
 *
 */
@Controller
@WithoutAccess
public class GoryController  {

	@Autowired
	private GoryService goryService;
	
	public static final Log log = LogFactory.getLog(GoryController.class);
	
  
	/**
	 * 根据订单UUID查询船燃订单信息列表
	 * @param vo
	 * @return
	 */
    @RequestMapping(value = UrlMapping.CATEGORY_LIST_BY_ORDERUUID, method = POST)
    @ResponseBody
    public ResultDatas<List<Map<String,Object>>> getIryQuotationList(@RequestBody GoryVO vo) {
    	ResultDatas<List<Map<String,Object>>> res=new ResultDatas<>();
        try {
        	Map<String, Object> map = null;
        	map = BeanConvertUtils.beanToMap(vo);
        	List<Map<String, Object>> list =  goryService.queryRecords(map);
        	res.setDatas(list);
        } catch (Exception e) {
        	res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(e.getMessage());

        }
        return res;
    }
	
}

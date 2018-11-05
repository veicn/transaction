package com.sinochem.crude.trade.orderexecute.service.impl;

import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.HttpUtils;
import com.sinochem.crude.trade.orderexecute.domain.InterfaceList;
import com.sinochem.crude.trade.orderexecute.domain.InterfaceOutput;
import com.sinochem.crude.trade.orderexecute.domain.InterfaceSystem;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.InterfaceListVO;
import com.sinochem.crude.trade.orderexecute.model.UserVO;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.crude.trade.orderexecute.dao.InterfaceListMapper;
import com.sinochem.crude.trade.orderexecute.dao.InterfaceOutputMapper;
import com.sinochem.crude.trade.orderexecute.dao.InterfaceSystemMapper;
import com.sinochem.crude.trade.orderexecute.service.InterfaceListService;
import com.sinochem.crude.trade.orderexecute.service.InterfaceOutputService;
import com.sinochem.crude.trade.orderexecute.service.InterfaceSystemService;
import com.sinochem.crude.trade.orderexecute.service.openapi.OutputService;

@Service
public class InterfaceOutputServiceImpl implements InterfaceOutputService {
	@Autowired
	private InterfaceOutputMapper interfaceOutputMapper;
	@Autowired
	private InterfaceSystemMapper interfaceSystemMapper;
	@Autowired
	private InterfaceListMapper interfaceListMapper;
	@Autowired
	private OutputService outputService;
	
	public InterfaceOutputMapper getMapper(){
		return interfaceOutputMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(InterfaceOutput interfaceoutput){
		 return interfaceOutputMapper.insertRecord(interfaceoutput);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long outputId) throws BizException{
		if (outputId == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return interfaceOutputMapper.deleteById(outputId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(InterfaceOutput  record){
    	return interfaceOutputMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(InterfaceOutput interfaceOutput) throws BizException{
		if ( interfaceOutput.getOutputId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","outputId 为空，不能修改","outputId");
		}
		return interfaceOutputMapper.updateRecordById(interfaceOutput);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return interfaceOutputMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(InterfaceOutput interfaceOutput){
		return interfaceOutputMapper.updateRecords(interfaceOutput.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public InterfaceOutput findByPrimaryKey(Long outputId){
		return  interfaceOutputMapper.findByPrimaryKey(outputId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public InterfaceOutput findByUuid(String uuid){
		return  interfaceOutputMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<InterfaceOutput> queryByEntitys(InterfaceOutput interfaceOutput){
		return  interfaceOutputMapper.queryByEntitys(interfaceOutput);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return interfaceOutputMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
		
		List<Map<String, Object>> list = interfaceOutputMapper.queryRecords(map);
		if(list != null && list.size() > 0){
			for (Map<String, Object> map2 : list) {
				String sysName = (String)map2.get("sysName");
				String interfaceCode = (String)map2.get("interfaceCode");
				
				InterfaceSystem interfaceSystem = new InterfaceSystem();
				interfaceSystem.setSysName(sysName);
				List<InterfaceSystem> queryByEntitys = interfaceSystemMapper.queryByEntitys(interfaceSystem);
				if(queryByEntitys != null && queryByEntitys.size() > 0){
					map2.put("sysDec",queryByEntitys.get(0).getSysDec());//
				}
				InterfaceList interfaceList = new InterfaceList();
				interfaceList.setSysName(sysName);
				interfaceList.setInterfaceCode(interfaceCode);
				List<InterfaceList> queryByEntitys2 = interfaceListMapper.queryByEntitys(interfaceList);
				if(queryByEntitys2 != null && queryByEntitys2.size() > 0){
					map2.put("interfaceDec",queryByEntitys2.get(0).getInterfaceDesc());//
				}
			}
		}
		
		return (Page<Map<String, Object>>) list;
		/*PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) interfaceOutputMapper.queryRecords(map);*/
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return interfaceOutputMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	
	@Override
	@Transactional
	public Result updateInterfaceOutput(Long outputId) {
		Result result = new Result();
		String interfaceCode = null;
		String interfaceUrl = null;
		String jsonResultString = null;
		Date startTime = null;
		Date endTime = null;
		boolean successFlg = false;
		
		startTime = DateTimeUtils.currentDate();
		InterfaceOutput interfaceOutput = interfaceOutputMapper.findByPrimaryKey(outputId);
		
		try {
			// 外部系统编号获取账号、密码
			UserVO userVO = outputService.getUser(interfaceOutput.getSysName());
			if (userVO != null && StringUtils.isNotBlank(userVO.getAccount())) {
				
				InterfaceListVO interfaceListVO = new InterfaceListVO();
				interfaceListVO.setSysName(interfaceOutput.getSysName());
				// 根据账号密码获取token
				String token = outputService.auth(interfaceListVO, userVO.getAccount(), userVO.getPassword());

				if (StringUtils.isNotBlank(token)) {

					if (StringUtils.isNotBlank(interfaceOutput.getUrl())) {
						// 调外部接口
						interfaceUrl = interfaceOutput.getUrl();
						String json = HttpUtils.sendRequest(interfaceUrl, interfaceOutput.getInputInfo(), token);
						JSONObject jsonResult = new JSONObject(json);
						jsonResultString = jsonResult.toString();

						if ("success".equals(jsonResult.get("status").toString())) {
							successFlg = true;
						} else {
							successFlg = false;
							result.setMessage(jsonResult.get("errmsg").toString());
						}
					} else {
						successFlg = false;
						result.setStatus(Constants.INTERFACE_STATUS_30);
						result.setMessage("接口信息不存在");
						result.setCode("orderexecute.code.00073");
					}
				} else {
					successFlg = false;
					result.setStatus(Constants.INTERFACE_STATUS_20);
					result.setMessage("对接系统认证失败");
					result.setCode("orderexecute.code.00074");
				}
			} else {
				successFlg = false;
				result.setStatus(Constants.INTERFACE_STATUS_10);
				result.setMessage("无对接系统");
				result.setCode("orderexecute.code.00075");
			}
			
		} catch (BizException e) {
			successFlg = false;
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setCode(e.getCode());
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			successFlg = false;
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setCode(Constants.EXCEPTION_CODE);
			result.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		endTime = DateTimeUtils.currentDate();
		try {
			// 日志记录
			interfaceOutput.setInterfaceCode(interfaceCode);
			interfaceOutput.setUrl(interfaceUrl);
			interfaceOutput.setStartTime(startTime);
			interfaceOutput.setEndTime(endTime);
			interfaceOutput.setOutputInfo(jsonResultString);
			interfaceOutput.setLangVer(Constants.LANG_VER);
			interfaceOutput.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			if (successFlg) {
				interfaceOutput.setStatus("1");
			} else {
				interfaceOutput.setStatus("0");
			}
			//更新
			interfaceOutputMapper.updateRecordById(interfaceOutput);
		} catch (BizException e) {
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setCode(e.getCode());
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setCode(Constants.EXCEPTION_CODE);
			result.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return result;
	}
	
}

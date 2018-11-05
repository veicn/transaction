package com.sinochem.crude.trade.orderexecute.service.openapi.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.HttpUtils;
import com.sinochem.crude.trade.common.utils.JedisUtils;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.crude.trade.orderexecute.dao.InterfaceListMapper;
import com.sinochem.crude.trade.orderexecute.dao.InterfaceOutputMapper;
import com.sinochem.crude.trade.orderexecute.dao.InterfaceSystemMapper;
import com.sinochem.crude.trade.orderexecute.domain.InterfaceList;
import com.sinochem.crude.trade.orderexecute.domain.InterfaceOutput;
import com.sinochem.crude.trade.orderexecute.domain.InterfaceSystem;
import com.sinochem.crude.trade.orderexecute.model.InterfaceListVO;
import com.sinochem.crude.trade.orderexecute.model.UserVO;
import com.sinochem.crude.trade.orderexecute.service.openapi.OutputService;
import com.sinochem.crude.trade.orderexecute.service.openapi.vo.PhysicalOperationSaveInfoVO;
import com.sinochem.crude.trade.orderexecute.service.openapi.vo.PhysicalOperationStatusVO;
import com.sinochem.crude.trade.orderexecute.service.openapi.vo.PhysicalOperationVO;
import com.sinochem.crude.trade.orderexecute.service.openapi.vo.PhysicalRecapContractVO;

/**
 * 调外部接口
 * 
 * @author Down
 *
 */
@Service
public class OutputServiceImpl implements OutputService {

	@Autowired
	private InterfaceListMapper interfaceListMapper;
	@Autowired
	private InterfaceSystemMapper interfaceSystemMapper;
	@Autowired
	private InterfaceOutputMapper interfaceOutputMapper;
	
	private final Logger log = Logger.getLogger(getClass());

	/**
	 * 获取token接口调用认证
	 */
	@Override
	public String auth(InterfaceListVO interfaceListVO, String account, String password) {

		// 定义返回值
		String result = StringUtil.EMPTY_STRING;

		// 获取缓存
		result = JedisUtils.get(account);

		// 验证缓存
		if (StringUtils.isNotBlank(result) && Boolean.TRUE.equals(this.checkToken(interfaceListVO, result))) {
			return result;
		}

		// 设置请求参数
		JSONObject jsonData = new JSONObject();

		jsonData.put("account", account);
		jsonData.put("password", password);

		// 获取接口URL
		InterfaceList interfaceList = new InterfaceList();

		interfaceList.setSysName(interfaceListVO.getSysName());
		interfaceList.setBusinessType(Constants.INTERFACE_AUTH);

		List<InterfaceList> interfaceListResult = interfaceListMapper.queryByEntitys(interfaceList);

		// 调用接口
		String tokenJson = HttpUtils.sendPost(interfaceListResult.get(0).getInterfaceUrl(), jsonData.toString(), "");

		JSONObject tokenResult = new JSONObject(tokenJson);

		// 接口返回值验证
		if ("SUCCESS".equals(tokenResult.get("status").toString())) {

			// 获取token
			result = tokenResult.get("token").toString();

			// 设置缓存
			JedisUtils.set(account, result, 0);

		}

		return result;
	}

	/**
	 * 校验token有效性
	 */
	@Override
	public Boolean checkToken(InterfaceListVO interfaceListVO, String token) {

		// 定义返回值
		Boolean result = Boolean.FALSE;

		// 获取接口URL
		InterfaceList interfaceList = new InterfaceList();

		interfaceList.setSysName(interfaceListVO.getSysName());
		interfaceList.setBusinessType(Constants.INTERFACE_ME);

		List<InterfaceList> interfaceListResult = interfaceListMapper.queryByEntitys(interfaceList);

		// 调用接口
		String json = HttpUtils.sendGet(interfaceListResult.get(0).getInterfaceUrl(), token);

		JSONObject jsonResult = new JSONObject(json);

		// 接口返回值验证
		if ("SUCCESS".equals(jsonResult.get("status").toString())) {
			result = Boolean.TRUE;
		}

		return result;
	}

	/**
	 * 获取外部系统的账号/密码
	 */
	@Override
	public UserVO getUser(String sysName) {

		// 定义返回结果
		UserVO result = new UserVO();

		// 获取账号/密码
		InterfaceSystem interfaceSystem = new InterfaceSystem();

		interfaceSystem.setSysName(sysName);

		List<InterfaceSystem> interfaceSystemLst = interfaceSystemMapper.queryByEntitys(interfaceSystem);

		// 设置返回结果
		if (interfaceSystemLst != null && interfaceSystemLst.size() > 0) {
			result.setSysName(interfaceSystemLst.get(0).getSysName());
			result.setAccount(interfaceSystemLst.get(0).getLoginName());
			result.setPassword(interfaceSystemLst.get(0).getPassword());
		}
		return result;
	}

	@Override
	public Result physicalRecapInsert(InterfaceListVO interfaceListVO, String jsonData) {

		Result result = new Result();

		String interfaceCode = null;
		String interfaceUrl = null;
		String jsonResultString = null;
		Date startTime = null;
		Date endTime = null;
		boolean successFlg = false;

		startTime = DateTimeUtils.currentDate();

		try {
			// 外部系统编号获取账号、密码
			UserVO userVO = this.getUser(interfaceListVO.getSysName());
			if (userVO != null && StringUtils.isNotBlank(userVO.getAccount())) {

				// 根据账号密码获取token
				String token = this.auth(interfaceListVO, userVO.getAccount(), userVO.getPassword());

				if (StringUtils.isNotBlank(token)) {

					// 外部系统编号、接口业务类型获取外部接口地址
					InterfaceList interfaceList = new InterfaceList();
					interfaceList.setSysName(interfaceListVO.getSysName());
					interfaceList.setBusinessType(Constants.INTERFACE_PHYSICAL_RECAP_UPDATE);
					List<InterfaceList> interfaceListResult = interfaceListMapper.queryByEntitys(interfaceList);

					if (interfaceListResult != null && interfaceListResult.size() > 0) {
						// 调外部接口
						interfaceCode = interfaceListResult.get(0).getInterfaceCode();
						interfaceUrl = interfaceListResult.get(0).getInterfaceUrl();
						String json = HttpUtils.sendRequest(interfaceUrl, jsonData, token);
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
					}
				} else {
					successFlg = false;
					result.setStatus(Constants.INTERFACE_STATUS_20);
					result.setMessage("对接系统认证失败");
				}
			} else {
				successFlg = false;
				result.setStatus(Constants.INTERFACE_STATUS_10);
				result.setMessage("无对接系统");
			}
			endTime = DateTimeUtils.currentDate();
		} catch (BizException e) {
			successFlg = false;
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setCode(e.getCode());
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			successFlg = false;
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setCode(Constants.EXCEPTION_CODE);
			result.setMessage(e.getMessage());
		}

		try {
			// 日志记录
			InterfaceOutput interfaceOutput = new InterfaceOutput();

			interfaceOutput.setSysName(interfaceListVO.getSysName());
			interfaceOutput.setInterfaceCode(interfaceCode);
			interfaceOutput.setUrl(interfaceUrl);
			interfaceOutput.setStartTime(startTime);
			interfaceOutput.setEndTime(endTime);
			interfaceOutput.setInputInfo(jsonData);
			interfaceOutput.setOutputInfo(jsonResultString);
			interfaceOutput.setLangVer(Constants.LANG_VER);
			interfaceOutput.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			if (successFlg) {
				interfaceOutput.setStatus("1");
			} else {
				interfaceOutput.setStatus("0");
			}
			interfaceOutputMapper.insertRecord(interfaceOutput);
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

	@Override
	public Result physicalRecapUpdate(InterfaceListVO interfaceListVO, String jsonData) {

		Result result = new Result();

		String interfaceCode = null;
		String interfaceUrl = null;
		String jsonResultString = null;
		Date startTime = null;
		Date endTime = null;
		boolean successFlg = false;

		startTime = DateTimeUtils.currentDate();

		try {
			// 外部系统编号获取账号、密码
			UserVO userVO = this.getUser(interfaceListVO.getSysName());
			if (userVO != null && StringUtils.isNotBlank(userVO.getAccount())) {

				// 根据账号密码获取token
				String token = this.auth(interfaceListVO, userVO.getAccount(), userVO.getPassword());

				if (StringUtils.isNotBlank(token)) {

					// 外部系统编号、接口业务类型获取外部接口地址
					InterfaceList interfaceList = new InterfaceList();
					interfaceList.setSysName(interfaceListVO.getSysName());
					interfaceList.setBusinessType(Constants.INTERFACE_PHYSICAL_RECAP_UPDATE);
					List<InterfaceList> interfaceListResult = interfaceListMapper.queryByEntitys(interfaceList);

					if (interfaceListResult != null && interfaceListResult.size() > 0) {
						interfaceCode = interfaceListResult.get(0).getInterfaceCode();
						interfaceUrl = interfaceListResult.get(0).getInterfaceUrl();
						// 调外部接口
						String json = HttpUtils.sendRequest(interfaceUrl, jsonData, token);
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
					}
				} else {
					successFlg = false;
					result.setStatus(Constants.INTERFACE_STATUS_20);
					result.setMessage("对接系统认证失败");
				}
			} else {
				successFlg = false;
				result.setStatus(Constants.INTERFACE_STATUS_10);
				result.setMessage("无对接系统");
			}
			endTime = DateTimeUtils.currentDate();
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

		try {
			// 日志记录
			InterfaceOutput interfaceOutput = new InterfaceOutput();

			interfaceOutput.setSysName(interfaceListVO.getSysName());
			interfaceOutput.setInterfaceCode(interfaceCode);
			interfaceOutput.setUrl(interfaceUrl);
			interfaceOutput.setStartTime(startTime);
			interfaceOutput.setEndTime(endTime);
			interfaceOutput.setInputInfo(jsonData);
			interfaceOutput.setOutputInfo(jsonResultString);
			interfaceOutput.setLangVer(Constants.LANG_VER);
			interfaceOutput.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			if (successFlg) {
				interfaceOutput.setStatus("1");
			} else {
				interfaceOutput.setStatus("0");
			}
			interfaceOutputMapper.insertRecord(interfaceOutput);
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

	@Override
	public ResultDatas<Void> physicalOperationUpdate(InterfaceListVO interfaceListVO,
			PhysicalOperationVO physicalOperationVO) {

		ResultDatas<Void> result = new ResultDatas<Void>();

		String interfaceCode = null;
		String interfaceUrl = null;
		String jsonResultString = null;
		String jsonDataString = null;
		Date startTime = null;
		Date endTime = null;
		boolean successFlg = false;

		startTime = DateTimeUtils.currentDate();
		Map<String, List<PhysicalOperationVO>> jsonMap = new HashMap<String, List<PhysicalOperationVO>>();
		List<PhysicalOperationVO> physicalOperationLst = new ArrayList<PhysicalOperationVO>();
		physicalOperationLst.add(physicalOperationVO);
		jsonMap.put("records", physicalOperationLst);
		JSONObject jsonData = new JSONObject(jsonMap);
		jsonDataString = jsonData.toString();
		
		try {

			// 外部系统编号获取账号、密码
			UserVO userVO = this.getUser(interfaceListVO.getSysName());
			if (userVO != null && StringUtils.isNotBlank(userVO.getAccount())) {
				// 根据账号密码获取token
				String token = this.auth(interfaceListVO, userVO.getAccount(), userVO.getPassword());

				if (StringUtils.isNotBlank(token)) {
					// 外部系统编号、接口业务类型获取外部接口地址
					InterfaceList interfaceList = new InterfaceList();
					interfaceList.setSysName(interfaceListVO.getSysName());
					interfaceList.setBusinessType(Constants.INTERFACE_PHYSICAL_OPRETION_UPDATE);
					List<InterfaceList> interfaceListResult = interfaceListMapper.queryByEntitys(interfaceList);

					if (interfaceListResult != null && interfaceListResult.size() > 0) {
						interfaceCode = interfaceListResult.get(0).getInterfaceCode();
						interfaceUrl = interfaceListResult.get(0).getInterfaceUrl();
						// 调外部接口
						
						String json = HttpUtils.sendRequest(interfaceUrl, jsonData.toString(), token);
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
					}
				} else {
					successFlg = false;
					result.setStatus(Constants.INTERFACE_STATUS_20);
					result.setMessage("对接系统认证失败");
				}
			} else {
				successFlg = false;
				result.setStatus(Constants.INTERFACE_STATUS_10);
				result.setMessage("无对接系统");
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
			InterfaceOutput interfaceOutput = new InterfaceOutput();

			interfaceOutput.setSysName(interfaceListVO.getSysName());
			interfaceOutput.setInterfaceCode(interfaceCode);
			interfaceOutput.setUrl(interfaceUrl);
			interfaceOutput.setStartTime(startTime);
			interfaceOutput.setEndTime(endTime);
			interfaceOutput.setInputInfo(jsonDataString);
			interfaceOutput.setOutputInfo(jsonResultString);
			interfaceOutput.setLangVer(Constants.LANG_VER);
			interfaceOutput.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			if (successFlg) {
				interfaceOutput.setStatus("1");
			} else {
				interfaceOutput.setStatus("0");
			}
			interfaceOutputMapper.insertRecord(interfaceOutput);
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

	@Override
	public ResultDatas<Void> physicalRecapContractSave(InterfaceListVO interfaceListVO,
			PhysicalRecapContractVO physicalRecapContractVO) {

		ResultDatas<Void> result = new ResultDatas<Void>();

		String interfaceCode = null;
		String interfaceUrl = null;
		String jsonResultString = null;
		String jsonDataString = null;
		Date startTime = null;
		Date endTime = null;
		boolean successFlg = false;

		startTime = DateTimeUtils.currentDate();
		JSONObject jsonData = new JSONObject(physicalRecapContractVO);
		jsonDataString = jsonData.toString();
		try {

			// 外部系统编号获取账号、密码
			UserVO userVO = this.getUser(interfaceListVO.getSysName());
			if (userVO != null && StringUtils.isNotBlank(userVO.getAccount())) {
				// 根据账号密码获取token
				String token = this.auth(interfaceListVO, userVO.getAccount(), userVO.getPassword());
				if (StringUtils.isNotBlank(token)) {
					// 外部系统编号、接口业务类型获取外部接口地址
					InterfaceList interfaceList = new InterfaceList();
					interfaceList.setSysName(interfaceListVO.getSysName());
					interfaceList.setBusinessType(Constants.INTERFACE_PHYSICAL_RECAP_SAVE_CONTRACT);
					List<InterfaceList> interfaceListResult = interfaceListMapper.queryByEntitys(interfaceList);

					if (interfaceListResult != null && interfaceListResult.size() > 0) {
						interfaceCode = interfaceListResult.get(0).getInterfaceCode();
						interfaceUrl = interfaceListResult.get(0).getInterfaceUrl();
						// 调外部接口
					
						String json = HttpUtils.sendPost(interfaceUrl, jsonData.toString(), token);
						JSONObject jsonResult = new JSONObject(json);
						jsonResultString = jsonResult.toString();

						if ("success".equals(jsonResult.get("status").toString())) {
							successFlg = true;
						} else {
							successFlg = false;
							result.setStatus(Constants.INTERFACE_STATUS_40);
							result.setMessage(jsonResult.get("errmsg").toString());
						}
					} else {
						successFlg = false;
						result.setStatus(Constants.INTERFACE_STATUS_30);
						result.setMessage("接口信息不存在");
					}
				} else {
					successFlg = false;
					result.setStatus(Constants.INTERFACE_STATUS_20);
					result.setMessage("对接系统认证失败");
				}
			} else {
				successFlg = false;
				result.setStatus(Constants.INTERFACE_STATUS_10);
				result.setMessage("无对接系统");
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
			InterfaceOutput interfaceOutput = new InterfaceOutput();

			interfaceOutput.setSysName(interfaceListVO.getSysName());
			interfaceOutput.setInterfaceCode(interfaceCode);
			interfaceOutput.setUrl(interfaceUrl);
			interfaceOutput.setStartTime(startTime);
			interfaceOutput.setEndTime(endTime);
			interfaceOutput.setInputInfo(jsonDataString);
			interfaceOutput.setOutputInfo(jsonResultString);
			interfaceOutput.setLangVer(Constants.LANG_VER);
			interfaceOutput.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			if (successFlg) {
				interfaceOutput.setStatus("1");
			} else {
				interfaceOutput.setStatus("0");
			}

			interfaceOutputMapper.insertRecord(interfaceOutput);
		} catch (Exception e) {
			log.error("日志记录异常（interfaceOutputMapper.insertRecord）！", e);
		}

		return result;
	}

	@Override
	public ResultDatas<Void> physicalOperationStatusUpdate(InterfaceListVO interfaceListVO,
			PhysicalOperationStatusVO physicalOperationStatusVO) {

		ResultDatas<Void> result = new ResultDatas<Void>();

		String interfaceCode = null;
		String interfaceUrl = null;
		String jsonResultString = null;
		String jsonDataString = null;
		Date startTime = null;
		Date endTime = null;
		boolean successFlg = false;

		startTime = DateTimeUtils.currentDate();
		JSONObject jsonData = new JSONObject(physicalOperationStatusVO);
		jsonDataString = jsonData.toString();
		
		try {

			// 外部系统编号获取账号、密码
			UserVO userVO = this.getUser(interfaceListVO.getSysName());
			if (userVO != null && StringUtils.isNotBlank(userVO.getAccount())) {
				// 根据账号密码获取token
				String token = this.auth(interfaceListVO, userVO.getAccount(), userVO.getPassword());

				if (StringUtils.isNotBlank(token)) {
					// 外部系统编号、接口业务类型获取外部接口地址
					InterfaceList interfaceList = new InterfaceList();
					interfaceList.setSysName(interfaceListVO.getSysName());
					interfaceList.setBusinessType(Constants.INTERFACE_UPDATE_DOC_STATUS);
					List<InterfaceList> interfaceListResult = interfaceListMapper.queryByEntitys(interfaceList);

					if (interfaceListResult != null && interfaceListResult.size() > 0) {
						interfaceCode = interfaceListResult.get(0).getInterfaceCode();
						interfaceUrl = interfaceListResult.get(0).getInterfaceUrl();
						// 调外部接口
						
						String json = HttpUtils.sendPost(interfaceUrl, jsonData.toString(),
								token);
						JSONObject jsonResult = new JSONObject(json);
						jsonResultString = jsonResult.toString();

						if ("success".equals(jsonResult.get("status").toString())) {
							successFlg = true;
						} else {
							// 错误信息
							successFlg = false;
							result.setMessage(jsonResult.get("errmsg").toString());
						}
					} else {
						successFlg = false;
						result.setStatus(Constants.INTERFACE_STATUS_30);
						result.setMessage("接口信息不存在");
					}
				} else {
					successFlg = false;
					result.setStatus(Constants.INTERFACE_STATUS_20);
					result.setMessage("对接系统认证失败");
				}
			} else {
				successFlg = false;
				result.setStatus(Constants.INTERFACE_STATUS_10);
				result.setMessage("无对接系统");
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
			InterfaceOutput interfaceOutput = new InterfaceOutput();

			interfaceOutput.setSysName(interfaceListVO.getSysName());
			interfaceOutput.setInterfaceCode(interfaceCode);
			interfaceOutput.setUrl(interfaceUrl);
			interfaceOutput.setStartTime(startTime);
			interfaceOutput.setEndTime(endTime);
			interfaceOutput.setInputInfo(jsonDataString);
			interfaceOutput.setOutputInfo(jsonResultString);
			interfaceOutput.setLangVer(Constants.LANG_VER);
			interfaceOutput.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			if (successFlg) {
				interfaceOutput.setStatus("1");
			} else {
				interfaceOutput.setStatus("0");
			}
			interfaceOutputMapper.insertRecord(interfaceOutput);
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

	@Override
	public ResultDatas<Void> physicalOperationSaveInfo(InterfaceListVO interfaceListVO,
			PhysicalOperationSaveInfoVO physicalOperationSaveInfoVO) {

		ResultDatas<Void> result = new ResultDatas<Void>();

		String interfaceCode = null;
		String interfaceUrl = null;
		String jsonResultString = null;
		String jsonDataString = null;
		Date startTime = null;
		Date endTime = null;
		boolean successFlg = false;

		startTime = DateTimeUtils.currentDate();
		JSONObject jsonData = new JSONObject(physicalOperationSaveInfoVO);
		jsonDataString = jsonData.toString();

		try {

			// 外部系统编号获取账号、密码
			UserVO userVO = this.getUser(interfaceListVO.getSysName());
			if (userVO != null && StringUtils.isNotBlank(userVO.getAccount())) {
				// 根据账号密码获取token
				String token = this.auth(interfaceListVO, userVO.getAccount(), userVO.getPassword());
				if (StringUtils.isNotBlank(token)) {
					// 外部系统编号、接口业务类型获取外部接口地址
					InterfaceList interfaceList = new InterfaceList();
					interfaceList.setSysName(interfaceListVO.getSysName());
					interfaceList.setBusinessType(Constants.INTERFACE_SETTLEMENT_SAVE);
					List<InterfaceList> interfaceListResult = interfaceListMapper.queryByEntitys(interfaceList);
					
					if (interfaceListResult != null && interfaceListResult.size() > 0) {
						interfaceCode = interfaceListResult.get(0).getInterfaceCode();
						interfaceUrl = interfaceListResult.get(0).getInterfaceUrl();
						// 调外部接口
						String json = HttpUtils.sendPost(interfaceUrl, jsonData.toString(),
								token);
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
					}
				} else {
					successFlg = false;
					result.setStatus(Constants.INTERFACE_STATUS_20);
					result.setMessage("对接系统认证失败");
				}
			} else {
				successFlg = false;
				result.setStatus(Constants.INTERFACE_STATUS_10);
				result.setMessage("无对接系统");
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
			InterfaceOutput interfaceOutput = new InterfaceOutput();

			interfaceOutput.setSysName(interfaceListVO.getSysName());
			interfaceOutput.setInterfaceCode(interfaceCode);
			interfaceOutput.setUrl(interfaceUrl);
			interfaceOutput.setStartTime(startTime);
			interfaceOutput.setEndTime(endTime);
			interfaceOutput.setInputInfo(jsonDataString);
			interfaceOutput.setOutputInfo(jsonResultString);
			interfaceOutput.setLangVer(Constants.LANG_VER);
			interfaceOutput.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			if (successFlg) {
				interfaceOutput.setStatus("1");
			} else {
				interfaceOutput.setStatus("0");
			}

			interfaceOutputMapper.insertRecord(interfaceOutput);
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

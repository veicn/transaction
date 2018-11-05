package com.sinochem.crude.trade.orderexecute.service.openapi;

import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.orderexecute.model.InterfaceListVO;
import com.sinochem.crude.trade.orderexecute.model.UserVO;
import com.sinochem.crude.trade.orderexecute.service.openapi.vo.PhysicalOperationSaveInfoVO;
import com.sinochem.crude.trade.orderexecute.service.openapi.vo.PhysicalOperationStatusVO;
import com.sinochem.crude.trade.orderexecute.service.openapi.vo.PhysicalOperationVO;
import com.sinochem.crude.trade.orderexecute.service.openapi.vo.PhysicalRecapContractVO;

public interface OutputService {

	/**
	 * 获取token接口调用认证
	 * 
	 * @param interfaceListVO 外部系统编号、接口业务类型
	 * @param account 帐号
	 * @param password 真实密码
	 * @return token
	 */
	String auth(InterfaceListVO interfaceListVO, String account, String password);

	/**
	 * 校验token有效性 http://sherlock.tuifacai.com/api/user/me
	 * 
	 * @param interfaceListVO 外部系统编号、接口业务类型
	 * @param token
	 */
	Boolean checkToken(InterfaceListVO interfaceListVO, String token);

	/**
	 * 根据外部系统编号从配置表里获取外部系统的账号/密码
	 * @param sysName 外部系统编号
	 * @return
	 */
	UserVO getUser(String sysName);
	
	/**
	 * 实货录入
	 * 1.外部系统编号获取账号、密码
	 * 2.根据账号密码获取token
	 * 3.外部系统编号、接口业务类型获取外部接口地址
	 * 4.调外部接口
	 * 5.日志记录
	 * @param interfaceListVO 外部系统编号、接口业务类型
	 * @param jsonData 实货数据
	 * @return
	 */
	Result physicalRecapInsert(InterfaceListVO interfaceListVO, String jsonData);
	
	/**
	 * 实货修改
	 * 1.外部系统编号获取账号、密码
	 * 2.根据账号密码获取token
	 * 3.外部系统编号、接口业务类型获取外部接口地址
	 * 4.调外部接口
	 * 5.日志记录
	 * @param interfaceListVO 外部系统编号、接口业务类型
	 * @param jsonData 实货数据
	 * @return
	 */
	Result physicalRecapUpdate(InterfaceListVO interfaceListVO, String jsonData);
	
	/**
	 * 执行修改
	 * 1.外部系统编号获取账号、密码
	 * 2.根据账号密码获取token
	 * 3.外部系统编号、接口业务类型获取外部接口地址
	 * 4.调外部接口
	 * 5.日志记录
	 * @param interfaceListVO 外部系统编号、接口业务类型
	 * @param physicalOperationVO 执行修改VO
	 * @return
	 */
	ResultDatas<Void> physicalOperationUpdate(InterfaceListVO interfaceListVO, PhysicalOperationVO physicalOperationVO);

	/**
	 * 实货合同附件更新
	 * 1.外部系统编号获取账号、密码
	 * 2.根据账号密码获取token
	 * 3.外部系统编号、接口业务类型获取外部接口地址
	 * 4.调外部接口
	 * 5.日志记录
	 * @param interfaceListVO 外部系统编号、接口业务类型
	 * @param physicalOperationVO 实货附件VO
	 * @return
	 */
	ResultDatas<Void> physicalRecapContractSave(InterfaceListVO interfaceListVO, PhysicalRecapContractVO physicalRecapContractVO);
	
	/**
	 * 对账信息确认
	 * 1.外部系统编号获取账号、密码
	 * 2.根据账号密码获取token
	 * 3.外部系统编号、接口业务类型获取外部接口地址
	 * 4.调外部接口
	 * 5.日志记录
	 * @param interfaceListVO 外部系统编号、接口业务类型
	 * @param physicalOperationVO 对账信息VO
	 * @return
	 */
	ResultDatas<Void> physicalOperationStatusUpdate(InterfaceListVO interfaceListVO, PhysicalOperationStatusVO physicalOperationStatusVO);
	
	/**
	 * 执行_007 :更新结算单
	 * 1.外部系统编号获取账号、密码
	 * 2.根据账号密码获取token
	 * 3.外部系统编号、接口业务类型获取外部接口地址
	 * 4.调外部接口
	 * 5.日志记录
	 * @param interfaceListVO 外部系统编号、接口业务类型
	 * @param physicalOperationVO 对账信息VO
	 * @return
	 */
	ResultDatas<Void> physicalOperationSaveInfo(InterfaceListVO interfaceListVO, PhysicalOperationSaveInfoVO physicalOperationSaveInfoVO);
}

package com.sinochem.crude.trade.order.service;

import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.order.domain.*;
import com.sinochem.it.b2b.common.exception.BizException;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单操作类<br/>
 * 
 * 业务说明： <br/>
 * 
 * 新增订单有两种模式，一种是通过手动新增，直接新增一个空的订单。<br/>
 * 输入油品一个，基本信息，船务信息，泊位信息，买家信息，卖家信息，中间信息<br/>
 * 中间商的信息不用填<br/>
 * 手动生成订单的可能是卖家，也可能是买家，也可能是中间商<br/>
 * 还有一种情况是订单因为需求单和报价单产生，在原油一定是买家生成，在成品油一定是卖家生成<br/>
 * 这个时候，应该是listed模快通过远程接口调用createContract生成，得到生成的订单的id<br/>
 * 再通过得到的id重新取一次订单，一般是302到订单模块界面，再读取订单<br/>
 * 这个时候订单就有了初始化数据<br/>
 * 订单在新增的时候，都是草约状态，无论是通过需求单或报价单生成还是手动生成<br/>
 * 订单在草约状态可以任意修改，所有信息，和报价一样，和需求单不一样，这里一个订单只能有一个油品<br/>
 * 生成修改订单的这一方默认是签单状态，需要另一方确认，另一方确认后变为双签<br/>
 * 但是如果在双签前有一方改动合同，都是将自己变为确认，对方变为未确认<br/>
 * 修改合同任何条款，token都会变化，在确认的时候，必须比对token一致后再确认，否则提示并跳转<br/>
 * 长约现在只有原油采购长约<br/>
 * 由买家自己发起，卖家或中间商双签<br/>
 * 长约多出来一个采购计划，有起始时间和结束时间，为年月，并连续，而且任意开始，任意结束<br/>
 * 可以由长约生成具体订单，只是将长约有的信息带出来，都可以修改走到订单流程<br/>
 * 
 * @author 胡凌 <br/>
 * 
 */
public interface ContractService {


	/**
	 * 新增一个订单
	 */
	public void createContract(Contract contract, Long oper) throws BizException;

	/**
	 * 得到一个订单
	 */
	public Contract getContract(Long orderId) throws BizException;

	/**
	 * 修改订单
	 *
	 */
	public void updateContract(Contract contract, Long oper) throws BizException;

	/**
	 * 申请取消订单
	 */
	public void applyCancelContract(String uuid,String remark, CurrentUser user) throws BizException;

	/**
	 * 撤销取消订单
	 */
	public void revokeCancelContract(String uuid,String remark, CurrentUser user) throws BizException;

	/**
	 * 确认取消订单
	 */
	public void confirmCancelContract(String uuid,String remark, boolean operStatus, CurrentUser user) throws BizException;

	/**
	 * 确认订单
	 */
	public void confirmContract(Long orderId, Long oper, String token) throws BizException;
	/**
	 * 根据报价单号查询订单，防止报价单重复推送
	 */
	public Contract selectByBiddingId(Long biddingId);

	/**
	 * 根据合约ID获取操作列表
	 */
	public List<ContractLog> contractOperList(Long orderId);
	/**
	 * 确认订单
	 */
	public void confirmContractNew(String uuid, Long epMemberId, String token, CurrentUser user)  throws BizException;
	/**
	 * 拒绝订单
	 */
	public void rejectedContractNew(String uuid, Long epMemberId, String remark, String token, CurrentUser user)  throws BizException;
	/**
	 * 撤回订单
	 */
	public void revokeContractNew(String uuid, Long epMemberId, String remark, String token, CurrentUser user)  throws BizException;

}

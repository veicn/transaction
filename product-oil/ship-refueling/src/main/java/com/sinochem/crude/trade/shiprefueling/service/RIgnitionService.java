package com.sinochem.crude.trade.shiprefueling.service;


import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shiprefueling.controller.common.ResultDatas;
import com.sinochem.crude.trade.shiprefueling.controller.common.SimplePageInfo;
import com.sinochem.crude.trade.shiprefueling.dao.RIgnitionMapper;
import com.sinochem.crude.trade.shiprefueling.domain.RIgnitionCombine;
import com.sinochem.crude.trade.shiprefueling.domain.po.Chments;
import com.sinochem.crude.trade.shiprefueling.domain.po.RIgnition;
import com.sinochem.crude.trade.shiprefueling.model.vo.ChmentsVO;
import com.sinochem.crude.trade.shiprefueling.model.vo.QueryRIgnitionVO;
import com.sinochem.it.b2b.common.exception.BizException;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface RIgnitionService {

	public abstract RIgnitionMapper getMapper();

	/**
	 * 新增
	 */
	int insertRecord(RIgnition rIgnition);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long orderId) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(RIgnition  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(RIgnition rIgnition) throws BizException;
	
	/**
	 * 根据uuid-修改对象
	 */
	int updateRecordByUuid(RIgnition rIgnition) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(RIgnition rIgnition);
	
	
	/**
	 * 根据主键-查询对象
	 */
	RIgnition findByPrimaryKey(Long orderId);

	/**
	 * 根据uuid查询对象
	 */
	RIgnition findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<RIgnition> queryByEntitys(RIgnition rIgnition);



	/**
	 * 根据条件-查询全部
	 */
	List<Map<String, Object>> queryRecords(Map<String, Object> map);
	

	/**
	 * 根据条件-查询记录条数
	 */
	int countRecords(Map<String, Object> map); 
	
	

	//**************************以下方法为开发者补充*********************************/

	/**
	 * 买家导入
	 */
	String buyImportRIgnition(List<RIgnitionCombine> list, CurrentUser user,String oilType);
	/**
	 *卖家导入
	 */
	String sellerImportRIgnition(List<RIgnitionCombine> list, CurrentUser user,String oilType);
	/**
	 * om导入
	 */
	String omImportRIgnition(List<RIgnitionCombine> list, CurrentUser user,String oilType);

	/**
	 * 多条件查询船燃订单信息
	 * @param map
	 * @return
	 */
	public abstract ResultDatas<List<Map<String, Object>>> queryIgnitionRecords(Map<String, Object> map, SimplePageInfo pageInfo) throws ParseException;

	ResultDatas<List<Map<String, Object>>> querySellIgnitionGroupByCompany(Map<String, Object> map, SimplePageInfo pageInfo);
	ResultDatas<List<Map<String, Object>>> queryBuyIgnitionGroupByCompany(Map<String, Object> map, SimplePageInfo pageInfo);

	List<Map<String,Object>> queryOrderListtByCompany(Map<String, Object> map);

	int updateRecordsAliveFlag(Long orderId, String s);
	/**
	 * 导出上游订单查询结果
	 * @param queryRIgnitionVO
	 * @return
	 */
	List<RIgnition> queryByQueryRIgnition(QueryRIgnitionVO queryRIgnitionVO);


	/**
	 * 上传发票信息
	 * @param invoiceList
	 * @param orderId 业务单据标识
	 * @return 关联的附件数量
	 */
	int uploadInvoice(List<Map<String , String>> invoiceList , long  orderId , CurrentUser user)throws BizException;

	/**
	 * 上传供油凭证和合同信息
	 * @param contractList
	 * @param oilSupplyVoucherList
	 * @param orderId 业务单据标识
	 * @return 关联的附件的数量
	 */
	int uploadOrderFile(List<Map<String , String>> fileList , String type , String fileType
			, long orderId , CurrentUser user)throws BizException;
}

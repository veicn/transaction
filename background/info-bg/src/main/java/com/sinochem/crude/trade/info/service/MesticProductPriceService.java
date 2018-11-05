package com.sinochem.crude.trade.info.service;

import java.util.Map;
import java.io.InputStream;
import java.util.List;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.info.domain.MesticProductPrice;
import com.sinochem.crude.trade.info.model.PIMSVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.info.dao.MesticProductPriceMapper; 

public interface MesticProductPriceService {
	
	public abstract MesticProductPriceMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(MesticProductPrice mesticProductPrice);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long id) throws BizException;
	
	 /**
     * 根据条件-逻辑删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(String  uuid);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(MesticProductPrice mesticProductPrice) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(MesticProductPrice mesticProductPrice);
	
	
	/**
	 * 根据主键-查询对象
	 */
	MesticProductPrice findByPrimaryKey(Long id);

	/**
	 * 根据uuid查询对象
	 */
	MesticProductPrice findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<MesticProductPrice> queryByEntitys(MesticProductPrice mesticProductPrice);
		
	/**
	 * 根据条件-查询全部
	 */
	List<Map<String, Object>> queryRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-分页查询
	 */
	Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo);
	
	/**
	 * 根据条件-查询记录条数
	 */
	int countRecords(Map<String, Object> map); 
	
	

	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 导入表
	 */
	
	public ResultDatas<Void> senWdImport(InputStream inputStream,CurrentUser user);
	
	/**
	 * 导出数据
	 */
	List<MesticProductPrice> dataExport();
	
	/**
	 * PMIS查询厂家报价
	 * @param vo
	 * @return
	 */
	public List<Map<String, Object>> queryVendPrice(PIMSVo vo);
}

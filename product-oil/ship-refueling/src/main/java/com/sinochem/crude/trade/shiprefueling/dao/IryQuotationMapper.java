package com.sinochem.crude.trade.shiprefueling.dao;

import com.sinochem.crude.trade.shiprefueling.domain.po.IryQuotation;
import com.sinochem.crude.trade.shiprefueling.model.query.IryQuotationQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IryQuotationMapper {

	public int insertRecord(IryQuotation entity);
	
	public int deleteById( @Param("inquiryQuotationId") Long inquiryQuotationId);
	
	public int deleteRecords(IryQuotation entity);
	
	public int updateRecordById(IryQuotation entity);
	
	public int updateRecordByUuid(IryQuotation entity);

	public int updateRecordByNeedUuid(IryQuotation entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public IryQuotation findByPrimaryKey( Long inquiryQuotationId);	
	
	public IryQuotation findByUuid(String uuid);	
	
	//返回对象的List
	public List<IryQuotation> queryByEntitys(IryQuotation entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);


    //**************************以下方法为开发者补充*********************************/
	/**
	 * 多条件查询列表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryiryQuotationRecords(Map<String,Object> map);

	/**
	 * 根据销售单查询询价list
	 * @param iryQuotationQuery
	 * @return
	 */
	List<Map<String,Object>> findByInfoSelectAll(IryQuotationQuery iryQuotationQuery);

	/**
	 * 根据uuid修改状态
	 * @param iryQuotation
	 * @return
	 */
    int updateByuuidStatus(IryQuotation iryQuotation);
}

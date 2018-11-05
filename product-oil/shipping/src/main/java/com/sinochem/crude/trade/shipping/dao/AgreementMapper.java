package com.sinochem.crude.trade.shipping.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.shipping.domain.Agreement;
//import org.apache.ibatis.annotations.Mapper;
import com.sinochem.crude.trade.shipping.model.query.AgreementQuery;
import com.sinochem.crude.trade.shipping.model.query.WechatAgreementQuery;

//@Mapper
public interface AgreementMapper {

	public int insertRecord(Agreement entity);
	
	public int deleteById( @Param("agreementId") Long agreementId);
	
	public int deleteRecords(Agreement entity);
	
	public int updateRecordById(Agreement entity);
	
	public int updateRecordByUuid(Agreement entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public Agreement findByPrimaryKey( Long agreementId);	
	
	public Agreement findByUuid(String uuid);	
	
	//返回对象的List
	public List<Agreement> queryByEntitys(Agreement entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	public int confirmAgreement(Agreement entity);

	public List<Agreement> queryAgreemenrByEntitys(AgreementQuery query);

	public List<Agreement> queryDemandsByEntitys(AgreementQuery query);

	public List<Agreement> queryDemandsQuanlianByEntitys(AgreementQuery query);

	public List<Agreement> queryDemandsQuanlianByEntityswx(AgreementQuery query);

	//微信API 查询协议列表
	public List<Agreement> queryAgreementList(WechatAgreementQuery query);

	//微信API 查询协议列表(贸易商)
	public List<Agreement> queryAgreementListtrader(WechatAgreementQuery query);

	/**
	 * 更新记录为航次开始状态
	 * @param agreement
	 * @return
	 */
	int updateStatusByAgreementId(Agreement agreement);

	int updateFileByOrderId(Agreement agreement);
}

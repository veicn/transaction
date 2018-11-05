package com.sinochem.crude.trade.transport.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.transport.dao.AgreementStatementsMapper;
import com.sinochem.crude.trade.transport.dao.PactStatementsMapper;
import com.sinochem.crude.trade.transport.domain.AgreementStatements;
import com.sinochem.crude.trade.transport.domain.PactStatements;
import com.sinochem.crude.trade.transport.query.StatementsQuery;
import com.sinochem.crude.trade.transport.service.PactStatementsService;

@Service
public class PactStatementsServiceImpl implements PactStatementsService {
	@Autowired
	private PactStatementsMapper _PactStatementsMapper;
	
	@Autowired
	private AgreementStatementsMapper _AgreementStatementsMapper;
	
	public PactStatementsMapper getMapper(){
		return _PactStatementsMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<PactStatements> queryByEntitys(PactStatements pactstatements){
		return  _PactStatementsMapper.queryByEntitys(pactstatements);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public PactStatements findByPrimaryKey(Long statementsId){
		return  _PactStatementsMapper.findByPrimaryKey(statementsId);	
	}
	/**
	 * 根据UUID-查询对象
	 */
	public PactStatements findByUuid(String uuid){
		PactStatements ps = _PactStatementsMapper.findByUuid(uuid);	
		return ps;
	} 
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(PactStatements pactstatements) {
		 _PactStatementsMapper.updateRecord(pactstatements);
	}
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(Long statementsId  , Long deleteUser) {
		 _PactStatementsMapper.deleteRecordByKey(statementsId , deleteUser);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(PactStatements pactstatements){
		 _PactStatementsMapper.insertRecord(pactstatements);
	}
	
	/*
	 * 根据主键删除数据 
	public void deleteRecordByKey(Long statementsId){
		 _PactStatementsMapper.deleteRecordByKey(statementsId);
	}
	*/
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return _PactStatementsMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _PactStatementsMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return _PactStatementsMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map){
		_PactStatementsMapper.deleteRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_PactStatementsMapper.updateRecords(map);
	}

	
	//**************************以下方法为开发者补充*********************************/
	/**
	 * 通过油合同号查找对象
	 * */
	@Override
	public Page<Map<String, Object>> findByPactCode(Map<String, Object> map, SimplePageInfo pageInfo, StatementsQuery query) {
		Page<Map<String,Object>> page = this.queryRecords(map, pageInfo);
		
		// 遍历合同
		for (Map<String,Object> maps : page) {
		 AgreementStatements agr = new AgreementStatements();
		 agr.setShipPactUuid(String.valueOf(maps.get("shipPactUuid")));
		 agr.setAgreementCode(query.getAgreementCode());
		 agr.setReceiptCode(query.getReceiptCode());
		 String aa = String.valueOf(maps.get("agentepMemberId"));
		 agr.setAgentMemberId(Long.valueOf(aa));
		 
		 // 根据合同的UUID查询代理协议
		 List<AgreementStatements> list = _AgreementStatementsMapper.queryByEntitys(agr);
		 List<AgreementStatements> list2 = new ArrayList<AgreementStatements>();
		 for (AgreementStatements agreementStatements : list) {
			list2.add(agreementStatements);
		}
		 maps.put("list", list2);
		}
		return page;
	}

	/**
	 * 结算单（对账列表）
	 */
	@Override
	public Page<Map<String, Object>> findDzdList(Map<String, Object> map2,
			SimplePageInfo pageInfo) {
		Page<Map<String, Object>> page = this.queryRecords(map2, pageInfo);
		for (Map<String, Object> map : page) {
			AgreementStatements entity = new AgreementStatements();
			entity.setShipPactUuid((String)map.get("uuid"));
			List<AgreementStatements> list = _AgreementStatementsMapper.queryByEntitys(entity);
			map.put("AsList", list);
		}
		return page;
	}

}

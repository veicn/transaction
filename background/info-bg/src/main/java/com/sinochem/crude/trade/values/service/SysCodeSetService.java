package com.sinochem.crude.trade.values.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.values.dao.SysCodeSetMapper;
import com.sinochem.crude.trade.values.domain.SysCodeSet;
import com.sinochem.crude.trade.values.domain.SysCodeSetForUpdate;
import com.sinochem.crude.trade.values.domain.SysCodeSetOnlyGroup;

public interface SysCodeSetService {
	
	SysCodeSetMapper getMapper();
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<SysCodeSet> queryByEntitys(SysCodeSet syscodeset);
	
	/**
	 * 根据主键-查询对象
	 */
	SysCodeSet findByPrimaryKey(String id);
 
	/**
	 * 根据主键-修改对象
	 */
	void updateRecord(SysCodeSet syscodeset);
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	void deleteRecordByKey(String id, String deleteUser);
	
	/**
	 * 新增
	 */
	void insertRecord(SysCodeSet syscodeset);
	
	/*
	 * 根据主键删除数据
	 */
	//public abstract void deleteRecordByKey(String id) throws Exception;
	
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
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	void deleteRecords(Map<String, Object> map);

	/**
	 * 根据条件-批量修改数据
	 */
	void updateRecords(Map<String, Object> map);

	//**************************以下方法为开发者补充*********************************/
    /**
     * TODO 临时，传入值集代码 返回值集列表
     * @param map
     * @return
     */
    List<Map<String, Object>> selParam(Map<String, Object> map);

    /**
     * TODO 临时，传入值集代码、值 返回值集名称
     * @param map
     * @return
     */
    String selParamToName(Map<String, Object> map);

    String selParamToName(String setCode, String itemCode);

    /**
     * 为集合添加代码名称
     * @param maps
     * @param strs
     */
    void addCodeName(List<Map<String, Object>> maps, String[][] strs, String langVer);

    /**
     * 为map添加代码名称
     * @param map
     * @param strs
     */
    void addCodeName(Map<String, Object> map, String[][] strs, String langVer);

    /**
     * 根据查询对象获取值集类别列表
     * Add by Huang.Weijie | 2017-02-06
     * @param query 查询条件对象
     * @param page 分页信息
     * @return 值集类别列表
     */
    Page<SysCodeSetOnlyGroup> queryCodeSet(SysCodeSet query, SimplePageInfo page);

    /**
     * 根据查询对象获取值集项目列表
     * Add by Huang.Weijie | 2017-02-08
     * @param query 查询条件对象
     * @return 值集项目列表
     */
    List<SysCodeSet> queryCodeItems(SysCodeSet query);

    /**
     * 查询是否可编辑
     * Add by Huang.Weijie | 2017-02-09
     * @param query 查询条件对象
     * @return 值集项目列表
     */
    String queryEditable(SysCodeSet query);

    /**
     * 查询是否可编辑
     * Add by Huang.Weijie | 2017-02-09
     * @param query 查询条件对象
     * @return 值集项目列表
     */
    void updateSetCode(SysCodeSetForUpdate query);

	Object getByCodeSet(SysCodeSet code);
}

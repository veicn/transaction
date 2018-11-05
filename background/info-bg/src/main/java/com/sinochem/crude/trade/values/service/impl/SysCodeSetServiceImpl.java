package com.sinochem.crude.trade.values.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.utils.ValueSetUtils;
import com.sinochem.crude.trade.common.values.CodeValue;
import com.sinochem.crude.trade.commons.exceptions.OperationException;
import com.sinochem.crude.trade.values.dao.SysCodeSetMapper;
import com.sinochem.crude.trade.values.domain.SysCodeSet;
import com.sinochem.crude.trade.values.domain.SysCodeSetForUpdate;
import com.sinochem.crude.trade.values.domain.SysCodeSetOnlyGroup;
import com.sinochem.crude.trade.values.service.SysCodeSetService;

@Service
public class SysCodeSetServiceImpl implements SysCodeSetService {
	@Autowired
	private SysCodeSetMapper sysCodeSetMapper;

	public SysCodeSetMapper getMapper() {
		return sysCodeSetMapper;
	}

	/**
	 * 根据对象-查询对象列表
	 */
	public List<SysCodeSet> queryByEntitys(SysCodeSet syscodeset) {
		return sysCodeSetMapper.queryByEntitys(syscodeset);
	}

	/**
	 * 根据主键-查询对象
	 */
	public SysCodeSet findByPrimaryKey(String id) {
		return sysCodeSetMapper.findByPrimaryKey(id);
	}

	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(SysCodeSet syscodeset) {
		if (syscodeset.getId() == null) {
			throw new OperationException(OperationException.TYPE.OPBA015);
		}
		sysCodeSetMapper.updateRecord(syscodeset);
	}

	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(String id, String deleteUser) {
		if (id == null) {
			throw new OperationException(OperationException.TYPE.OPBA015);
		}
		sysCodeSetMapper.deleteRecordByKey(id, deleteUser);
	}

	/**
	 * 新增
	 */
	public void insertRecord(SysCodeSet syscodeset) {
		sysCodeSetMapper.insertRecord(syscodeset);
	}

	/*
	 * 根据主键删除数据 public void deleteRecordByKey(String id) throws Exception { if (
	 * id == null ) { throw new Exception("id 为空，不能删除 "); }
	 * _SysCodeSetImmutableMapper.deleteRecordByKey(id); }
	 */
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map) {
		return sysCodeSetMapper.queryRecords(map);
	}

	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map,
			SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(),
				pageInfo.getCount());
		return (Page<Map<String, Object>>) sysCodeSetMapper.queryRecords(map);
	}

	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map) {
		return sysCodeSetMapper.countRecords(map);
	}

	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map) {
		sysCodeSetMapper.deleteRecords(map);
	}

	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		sysCodeSetMapper.updateRecords(map);
	}

	// **************************以下方法为开发者补充*********************************/

	public List<Map<String, Object>> selParam(Map<String, Object> map) {
		List<Map<String, Object>> resListMap = Lists.newArrayList();
		if (Strings.isNullOrEmpty((String) map.get("setCode")))
			return null;
		Map<String, CodeValue> valuesByGroup = ValueSetUtils
				.getValuesByGroup(map.get("setCode").toString());
		for (Map.Entry<String, CodeValue> codeMap : valuesByGroup.entrySet()) {
			Map<String, Object> resMap = Maps.newHashMap();
			resMap.put(codeMap.getKey(), codeMap.getValue());
			resListMap.add(resMap);
		}
		return resListMap;
		// return _SysCodeSetMapper.queryRecords(map);
	}

	public String selParamToName(String setCode, String itemCode) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("setCode", setCode);
		map.put("itemCode", itemCode);

		return selParamToName(map);
	}

	public String selParamToName(Map<String, Object> map) {
		String res = "";
		if (Strings.isNullOrEmpty((String) map.get("setCode"))
				|| Strings.isNullOrEmpty((String) map.get("itemCode")))
			return null;
		CodeValue value = ValueSetUtils.getValue(map.get("setCode").toString(),
				map.get("itemCode").toString());
		if (value != null)
			res = value.getValue();
		return res;
	}

	public void addCodeName(List<Map<String, Object>> maps, String[][] strs,
			String langVer) {
		for (Map<String, Object> temp : maps) {
			addCodeName(temp, strs, langVer);
		}
	}

	public void addCodeName(Map<String, Object> map, String[][] strs,
			String langVer) {
		for (String[] strings : strs) {
			Map<String, Object> pram = Maps.newHashMap();
			pram.put("setCode", strings[0]);
			pram.put("itemCode", map.get(strings[1]));
			pram.put("langVer", langVer);
			map.put(strings[1] + "Desc", selParamToName(pram));
		}
	}

	@Override
	public Page<SysCodeSetOnlyGroup> queryCodeSet(SysCodeSet query,
			SimplePageInfo page) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getCount());
		return (Page<SysCodeSetOnlyGroup>) sysCodeSetMapper
				.queryDistinctGroup(query);
	}

	@Override
	public List<SysCodeSet> queryCodeItems(SysCodeSet query) {
		return sysCodeSetMapper.queryInGroup(query);
	}

	@Override
	public String queryEditable(SysCodeSet query) {
		return sysCodeSetMapper.queryEditable(query);
	}

	@Override
	public void updateSetCode(SysCodeSetForUpdate query) {
		sysCodeSetMapper.updateSetCode(query);
	}

	@Override
	public List<String> getByCodeSet(SysCodeSet code) {
		return sysCodeSetMapper.getByCodeSet(code);
	}
}

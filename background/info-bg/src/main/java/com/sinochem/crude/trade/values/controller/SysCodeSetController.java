package com.sinochem.crude.trade.values.controller;

import static com.sinochem.crude.trade.URLMapping.ADD_CODE_SET;
import static com.sinochem.crude.trade.URLMapping.CODE_ITEM_LIST;
import static com.sinochem.crude.trade.URLMapping.CODE_SET_MANAGE_LIST;
import static com.sinochem.crude.trade.URLMapping.DEL_CODE_ITEM;
import static com.sinochem.crude.trade.URLMapping.DEL_CODE_SET;
import static com.sinochem.crude.trade.URLMapping.EDIT_CODE_ITEM;
import static com.sinochem.crude.trade.URLMapping.EDIT_CODE_SET;
import static com.sinochem.crude.trade.URLMapping.CODE_LIST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eyeieye.melody.web.locale.VisitorLocale;
import com.github.pagehelper.Page;
import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sinochem.crude.trade.URLMapping;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.common.utils.ValueSetUtils;
import com.sinochem.crude.trade.common.values.CodeValue;
import com.sinochem.crude.trade.commons.exceptions.OperationException;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.values.domain.SysCodeSet;
import com.sinochem.crude.trade.values.domain.SysCodeSetForUpdate;
import com.sinochem.crude.trade.values.domain.SysCodeSetOnlyGroup;
import com.sinochem.crude.trade.values.service.SysCodeSetService;
import com.sinochem.crude.trade.values.vo.CodeItemQuery;
import com.sinochem.crude.trade.values.vo.CodeItemResult;
import com.sinochem.crude.trade.values.vo.CodeSetQuery;
import com.sinochem.crude.trade.values.vo.CodeSetQueryForUpdate;
import com.sinochem.crude.trade.values.vo.CodeSetRes;
import com.sinochem.crude.trade.values.vo.CodeSetResult;
import com.sinochem.crude.trade.values.vo.CodeSetVO;
import com.sinochem.crude.trade.values.vo.SysCodeSetVO;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.WithoutAccess;

@Controller
public class SysCodeSetController {

	private SysCodeSetService codeSetService;

	private static final Log log = LogFactory.getLog(SysCodeSetController.class);

	public static final String NOT_EDITABLE_MSG = "此项目不可编辑";

	private final static String APPLICATION_JSON_UTF8_VALUE = MediaType.APPLICATION_OCTET_STREAM.toString()
 + ";charset=UTF-8";

	@Autowired
	public SysCodeSetController(SysCodeSetService codeSetService) {
		this.codeSetService = codeSetService;
	}

	/**
	 * 页面启动
	 */
	@RequestMapping(value = "/om/sysCodeSet/sysCodeSet.htm")
	public void unit(CurrentUser user, ModelMap modelMap) {
		
	}
	
	/**
	 * Query code set items. Edit by Huang.Weijie | 2017-02-06
	 * 
	 * @param query
	 *            condition info
	 * @return list of groups.
	 */
	@RightAccess(1162)
	@RequestMapping(value = CODE_SET_MANAGE_LIST)
	@ResponseBody
	public ResultDatas<List<CodeSetResult>> queryCodeSetList(@RequestBody CodeSetQuery query) {

		ResultDatas<List<CodeSetResult>> result = new ResultDatas<>();
		// return false if no condition info
		if (null == query) {
			result.setFail("");
			return result;
		}

		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageSize(query.getPageSize());
		pageInfo.setPageNum(query.getPageNum());

		SysCodeSet queryEntity = new SysCodeSet();
		queryEntity.setSetName(query.getSetName());
		queryEntity.setModelCode(query.getModule());

		Page<SysCodeSetOnlyGroup> sets;
		try {
			sets = codeSetService.queryCodeSet(queryEntity, pageInfo);
		} catch (Exception e) {
			throw new OperationException(OperationException.TYPE.OPBA008, e);
		}

		result.setPageNum(sets.getPageNum());
		result.setPageSize(sets.getPageSize());
		result.setPageCount(sets.getPages());
		result.setTotal(sets.getTotal());

		result.setDatas(Lists.transform(sets,
				new Function<SysCodeSetOnlyGroup, CodeSetResult>() {
					@Override
					public CodeSetResult apply(SysCodeSetOnlyGroup sysCodeSet) {
						return entityToGroup(sysCodeSet);
					}
				}));
		return result;
	}

	/**
	 * Query code set groups. Edit by Huang.Weijie | 2017-02-08
	 * 
	 * @param query
	 *          后台列表专用前台请勿使用此方法
	 * @return list of items.
	 */
	@WithoutAccess
	@RequestMapping(value = CODE_ITEM_LIST)
	@ResponseBody
	public ResultDatas<List<CodeItemResult>> queryItemList(@RequestBody CodeItemQuery query) {
		ResultDatas<List<CodeItemResult>> result = new ResultDatas<>();
		// return false if no condition info
		if (null == query) {
			result.setFail("");
			return result;
		}

		SysCodeSet queryEntity = new SysCodeSet();
		queryEntity.setSetCode(query.getSetCode());
		Locale current = VisitorLocale.getCurrent();
		if(StringUtils.isNotBlank(current.getLanguage())){
			queryEntity.setLangVer(current.getLanguage());
		}else{
			queryEntity.setLangVer("zh");
		}
		List<SysCodeSet> items;
		try {
			items = codeSetService.queryCodeItems(queryEntity);
		} catch (Exception e) {
			throw new OperationException(OperationException.TYPE.OPBA009, e);
		}

		result.setDatas(Lists.transform(items,
				new Function<SysCodeSet, CodeItemResult>() {
					@Override
					public CodeItemResult apply(SysCodeSet sysCodeSet) {
						return entityToItem(sysCodeSet);
					}
				}));

		return result;
	}
	/**
	 * Query code set groups. Edit by Huang.Weijie | 2017-02-08
	 * 
	 * @param query
	 *          后台值集列表专用前台请勿使用此方法
	 * @return list of items.
	 */
	@RightAccess(1164)
	@WithoutAccess
	@RequestMapping(value = CODE_LIST)
	@ResponseBody
	public ResultDatas<List<CodeItemResult>> queryList(@RequestBody CodeItemQuery query) {
		ResultDatas<List<CodeItemResult>> result = new ResultDatas<>();
		// return false if no condition info
		if (null == query) {
			result.setFail("");
			return result;
		}

		SysCodeSet queryEntity = new SysCodeSet();
		queryEntity.setSetCode(query.getSetCode());
		List<SysCodeSet> items;
		try {
			items = codeSetService.queryCodeItems(queryEntity);
		} catch (Exception e) {
			throw new OperationException(OperationException.TYPE.OPBA009, e);
		}

		result.setDatas(Lists.transform(items,
				new Function<SysCodeSet, CodeItemResult>() {
					@Override
					public CodeItemResult apply(SysCodeSet sysCodeSet) {
						return entityToItem(sysCodeSet);
					}
				}));

		return result;
	}

	@RightAccess(1163)
	@RequestMapping(value = ADD_CODE_SET)
	@ResponseBody
	public ResultDatas<Void> addCodeSet(@RequestBody SysCodeSetVO codeSet) {
		ResultDatas<Void> result = new ResultDatas<>();
		try {
			
			if ("1".equals(codeSet.getAddflag())) {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("modelCode", codeSet.getModelCode());
				map.put("setCode", codeSet.getSetCode());
				
				int count = codeSetService.countRecords(map);
				
				if (count != 0) {
					result.setFail("该值集已存在");
					return result;
				}
			} else {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("modelCode", codeSet.getModelCode());
				map.put("setCode", codeSet.getSetCode());
				map.put("itemCode", codeSet.getItemCode());  
				map.put("langVer", codeSet.getLangVer());  
				
				int count = codeSetService.countRecords(map);
				
				if (count != 0) {
					result.setFail("该值集已存在");
					return result;
				}
			}
			
			SysCodeSet entity = BeanConvertUtils.beanToBean(codeSet, SysCodeSet.class);
			entity.setId(KeyGenUtils.newUuid());
			entity.setCreateDate(DateTimeUtils.currentDate());
			entity.setStatus("10");
			entity.setIsEdit("1");
			entity.setAliveFlag("1");
			codeSetService.insertRecord(entity);
		} catch (Exception e) {
			throw new OperationException(OperationException.TYPE.OPBA010, e);
		}

		return result;
	}
	
	@RightAccess(1167)
	@RequestMapping(value = DEL_CODE_SET)
	@ResponseBody
	public ResultDatas<Void> delCodeSet(@RequestBody CodeSetQuery query) {

		ResultDatas<Void> resultDTO = new ResultDatas<>();

		SysCodeSetVO queryVo = new SysCodeSetVO();
		String module = query.getModule();
		String setCode = query.getSetCode();
		if (Strings.isNullOrEmpty(module) || Strings.isNullOrEmpty(setCode)) {
			resultDTO.setFail("值集组不存在");
			return resultDTO;
		}
		queryVo.setModelCode(module);
		queryVo.setSetCode(setCode);
		boolean editable = codeItemEditable(queryVo);
		if (!editable) {
			resultDTO.setFail(NOT_EDITABLE_MSG);
			return resultDTO;
		}
		Map<String, Object> qMap = Maps.newHashMap();
		qMap.put("modelCode", module);
		qMap.put("setCode", setCode);
		try {
			codeSetService.deleteRecords(qMap);
		} catch (Exception e) {
			throw new OperationException(OperationException.TYPE.OPBA012, e);
		}

		return resultDTO;
	}
	
	@RightAccess(1169)
	@RequestMapping(value = DEL_CODE_ITEM)
	@ResponseBody
	public ResultDatas<Void> delCodeItem(@RequestBody String id) {

		ResultDatas<Void> resultDTO = new ResultDatas<>();

		SysCodeSetVO query = new SysCodeSetVO();
		query.setId(id);
		boolean editable = codeItemEditable(query);
		if (!editable) {
			resultDTO.setFail(NOT_EDITABLE_MSG);
			return resultDTO;
		}

		try {
			codeSetService.deleteRecordByKey(id, "SYS");
		} catch (Exception e) {
			throw new OperationException(OperationException.TYPE.OPBA012, e);
		}

		return resultDTO;
	}

	@RightAccess(1166)
	@RequestMapping(value = EDIT_CODE_SET)
	@ResponseBody
	public ResultDatas<Void> editCodeSet(@RequestBody CodeSetQueryForUpdate data) {

		ResultDatas<Void> resultDTO = new ResultDatas<>();

		SysCodeSetVO queryVo = new SysCodeSetVO();
		queryVo.setModelCode(data.getOldModule());
		queryVo.setSetCode(data.getOldSetCode());
		boolean editable = codeItemEditable(queryVo);
		if (!editable) {
			resultDTO.setFail(NOT_EDITABLE_MSG);
			return resultDTO;
		}

		SysCodeSetForUpdate entity = new SysCodeSetForUpdate();
		entity.setUpdateDate(DateTimeUtils.currentDate());
		entity.setSetCode(data.getSetCode());
		entity.setOldSetCode(data.getOldSetCode());
		entity.setOldModelCode(data.getOldModule());
		entity.setModelCode(data.getModule());
		entity.setSetName(data.getSetName());
		try {
			codeSetService.updateSetCode(entity);
		} catch (Exception e) {
			throw new OperationException(OperationException.TYPE.OPBA014, e);
		}

		return resultDTO;
	}
	
	@RightAccess(1168)
	@RequestMapping(value = EDIT_CODE_ITEM)
	@ResponseBody
	public ResultDatas<Void> editCodeItem(@RequestBody CodeItemResult data) {

		ResultDatas<Void> resultDTO = new ResultDatas<>();

		SysCodeSetVO query = new SysCodeSetVO();
		query.setId(data.getId());
		boolean editable = codeItemEditable(query);
		if (!editable) {
			resultDTO.setFail(NOT_EDITABLE_MSG);
			return resultDTO;
		}

		SysCodeSetForUpdate entity = new SysCodeSetForUpdate();
		entity.setUpdateDate(DateTimeUtils.currentDate());
		entity.setId(data.getId());
		entity.setItemCode(data.getItemCode());
		entity.setItemName(data.getItemName());
		entity.setItemSort(data.getItemSort());
		entity.setLangVer(data.getLangVer());
		entity.setExt1(data.getExt1());
		try {
			codeSetService.updateSetCode(entity);
		} catch (Exception e) {
			throw new OperationException(OperationException.TYPE.OPBA014, e);
		}

		return resultDTO;
	}

	private boolean codeItemEditable(SysCodeSetVO query) {

		try {
			String editable = codeSetService.queryEditable(query);
			return "1".equals(editable);
		} catch (Exception e) {
			throw new OperationException(OperationException.TYPE.OPBA013, e);
		}
	}

	private CodeItemResult entityToItem(SysCodeSet entity) {

		CodeItemResult itemResult = new CodeItemResult();
		String isEditStr = entity.getIsEdit();
		boolean isEdit;
		isEdit = "1".equals(isEditStr);
		itemResult.setEditable(isEdit);
		itemResult.setItemCode(entity.getItemCode());
		itemResult.setItemName(entity.getItemName());
		itemResult.setItemSort(entity.getItemSort());
		itemResult.setId(entity.getId());
		itemResult.setModule(entity.getModelCode());
		itemResult.setLangVer(entity.getLangVer());
		itemResult.setExt1(entity.getExt1());
		return itemResult;
	}

	private CodeSetResult entityToGroup(SysCodeSetOnlyGroup entity) {

		CodeSetResult codeSetResult = new CodeSetResult();
		codeSetResult.setSetName(entity.getSetName());
		codeSetResult.setModule(entity.getModule());
		codeSetResult.setSetCode(entity.getSetCode());
		codeSetResult.setEditable("1".equals(entity.getIsEditable()));

		return codeSetResult;
	}
	
	/**
	 * 根据代码集代码查询值集
	 * @param request
	 * @param vo
	 * @return
	 */
	@WithoutAccess
	@RequestMapping(value = URLMapping.CMS_QUERYCODESETLIST, method = RequestMethod.POST)
	@ResponseBody
	public ResultDatas<List<CodeSetRes>> queryCodeSetList(HttpServletRequest request,@RequestBody CodeSetVO vo){

		ResultDatas<List<CodeSetRes>> res = new ResultDatas<List<CodeSetRes>>();

		try {
			List<CodeSetRes> codeSetResList = new ArrayList<CodeSetRes>();
			if(StringUtils.isBlank(vo.getSetCode())){
				throw new OperationException(OperationException.TYPE.SOSM080);
			}

			Map<String, CodeValue> list = ValueSetUtils.getValuesByGroup(vo.getSetCode());
			if(!list.isEmpty()){
				for (Map.Entry<String, CodeValue> e : list.entrySet()) {
					CodeValue value = e.getValue();
					if(value.getGroup().equals(vo.getSetCode())){
						CodeSetRes codeSetRes = new CodeSetRes();
						codeSetRes.setItemCode(value.getCode());
						codeSetRes.setItemName(value.getValue());
						codeSetRes.setId(value.getId());
						codeSetResList.add(codeSetRes);
					}
				}
				res.setDatas(codeSetResList);
			}
		} catch (Exception e) {
			if (log.isInfoEnabled()) {
				log.info(e);
			}
			res.setFail(e.getMessage());
		}
		return res;
	}
	
}

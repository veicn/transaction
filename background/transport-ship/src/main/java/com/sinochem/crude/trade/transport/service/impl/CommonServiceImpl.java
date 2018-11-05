package com.sinochem.crude.trade.transport.service.impl;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.eyeieye.melody.web.locale.VisitorLocale;
import com.mysql.jdbc.StringUtils;
import com.sinochem.crude.trade.common.utils.ValueSetUtils;
import com.sinochem.crude.trade.common.values.CodeValue;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.model.res.ValueSetName;
import com.sinochem.crude.trade.transport.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService {
	private static Log log = LogFactory.getLog(CommonServiceImpl.class);
	@Autowired
	private EnterpriseService enterpriseService;

	@Override
	public EnterpriseVo queryUserByEpMemberId(Long epMemberId) {
		EnterpriseVo vo = new EnterpriseVo();
		try {
			EnterpriseVo vos = enterpriseService.getByMemberId(epMemberId);
			if (vos != null){
				BeanUtils.copyProperties(vos, vo);
			}
		} catch (Exception e) {
			log.error("会员根据epMemberI查询企业信息接口异常，企业id="+epMemberId,e);
			throw new TransportException(TransportException.TYPE.ITSH555);
		}
		return vo;
	}

	@Override
	public String findNameByEpMemberId(Long epMemberId) {
		String name = null;
		try {
			EnterpriseVo vos = enterpriseService.getByMemberId(epMemberId);
			
			Locale current = VisitorLocale.getCurrent();
			String string = current.getLanguage();
			if ("en".equals(string)){
				name = vos.getEnglishName();
			} else {
				name = vos.getFullName();
			}
		} catch (Exception e) {
			log.error("会员根据epMemberI查询企业信息接口异常，企业id="+epMemberId,e);
		}
		return name;
	}

	@Override
	public Map<Long, String> findNameByEpMemberIds(Long[] epMemberId) {
		Locale current = VisitorLocale.getCurrent();
		Map<Long, String> map = new HashMap<>();
		try {
			 map = enterpriseService.getEnterpriseName(epMemberId, current);
		} catch (Exception e) {
			log.error("会员根据多个epMemberI查询企业信息接口异常，企业id="+epMemberId,e);
		}
		return map;
	}

	@Override
	public ValueSetName findNameByCode(String type, String code) {
		ValueSetName name = new ValueSetName(); 
		try {
			String setGroup ="";
			String zhNames = "";
			String enNames = "";
			switch (type) {
			case "1":
				setGroup = Constants.VALUE_SET_5;
				break;
			case "2":
				setGroup = Constants.VALUE_SET_4;
				break;
			case "3":
				setGroup = Constants.VALUE_SET_10;
				break;
			default:
				break;
			}
			CodeValue zhVal = ValueSetUtils.getValue(setGroup, code, "zh");
			if (zhVal != null) {
				zhNames = zhVal.getValue();
			}
			CodeValue enVal = ValueSetUtils.getValue(setGroup, code, "en");
			if (enVal != null) {
				enNames = enVal.getValue();
			}
			if (StringUtils.isNullOrEmpty(zhNames)) {
				zhNames = code;
			}
			if (StringUtils.isNullOrEmpty(enNames)) {
				enNames = code;
			}
			name.setZhName(zhNames);
			name.setEnName(enNames);
			
		} catch (Exception e) {
			log.error("查询值集名称异常",e);
		}
		return name;
	}

	@Override
	public String findNameByCodeAndLang(String type, String code) {
		String name = ""; 
		try {
			Locale current = VisitorLocale.getCurrent();
			String lang = current.getLanguage();
			
			String setGroup ="";
			switch (type) {
			case "1":
				setGroup = Constants.VALUE_SET_5;
				break;
			case "2":
				setGroup = Constants.VALUE_SET_4;
				break;
			case "3":
				setGroup = Constants.VALUE_SET_10;
				break;
			default:
				break;
			}
			CodeValue zhVal = ValueSetUtils.getValue(setGroup, code, lang);
			if (zhVal != null) {
				name = zhVal.getValue();
			}
			if (StringUtils.isNullOrEmpty(name)) {
				name = code;
			}
		} catch (Exception e) {
			log.error("查询值集名称异常",e);
		}
		return name;
	}

}

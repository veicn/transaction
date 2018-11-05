package com.sinochem.crude.trade.listed.valid;

import com.sinochem.crude.trade.listed.domain.DemandRelevanter;
import com.sinochem.crude.trade.listed.domain.DemandShip;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;

import com.sinochem.crude.trade.listed.domain.Demand;
import com.sinochem.crude.trade.member.user.CurrentUser;

public class Validator {
	
	public static boolean validPublishProductOilDemand(Demand demand,
            DemandShip ship,
            DemandRelevanter provider,
            DemandRelevanter merchant,
            ModelMap model, CurrentUser user){
		String errorMsg = "";
		boolean valid = true;
		 
		Long epMemberId = user.getEpMemberId();
		if(merchant==null || merchant.getEMemberId()==null){
			if(provider == null || provider.getEMemberId()==null){
				errorMsg = "供应商信息为必填项";
			}else if(!epMemberId.equals(provider.getEMemberId())){
				errorMsg = "供应商企业名称必须为当前登陆用户企业信息";
			}
		}else{
			if(!epMemberId.equals(provider.getEMemberId()) && !epMemberId.equals(merchant.getEMemberId())){
				errorMsg = "供应商和代理商必须有一个为当前登陆用户信息";
			}
		}
		
		if(StringUtils.isNotBlank(errorMsg)){
			valid = false;
			model.addAttribute("demand", demand);
            model.addAttribute("ship", ship);
             
		}
		model.addAttribute("errorMsg", errorMsg);
        model.addAttribute("valid", valid);
		 
		
		return valid;
		
	}

}

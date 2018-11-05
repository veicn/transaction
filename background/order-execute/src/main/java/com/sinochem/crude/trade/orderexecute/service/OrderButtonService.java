package com.sinochem.crude.trade.orderexecute.service;

import java.util.List;

import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.dao.OrderButtonMapper;
import com.sinochem.crude.trade.orderexecute.domain.Order;
//import com.runyi.ryplat.api.commons.SimplePageInfo;

public interface OrderButtonService {
	
	public abstract OrderButtonMapper getMapper(); 
	
	/**
	 * 取得可以显示的按钮列表
	 */
	List<String> getButtonList(Order order, CurrentUser user, boolean isSeller);

}

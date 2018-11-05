package com.sinochem.crude.trade.order.controller;

import com.sinochem.it.b2b.common.page.PageInfoWithParams;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 通用的分页组件
 * 
 * @author : ccav
 * @group : sinochem
 * @Date : 2016年11月11日
 * @Comments : 通用的分页组件
 * @Version : 1.0.0
 */
@Controller
public class PageController {
	/**
	 * 可复写的分页组件weight 如果有其他样式，或者分页策略，可以写一个新的Mapping
	 * 并且使用_pageInfo或者将pageInfo放进modelMap来获取对应的所有信息
	 * 
	 * @JavaDoc
	 */
	@RequestMapping("/common/page")
	public void page(PageInfoWithParams<?> pageInfo, ModelMap modelMap) {
		modelMap.put("_pageInfo",pageInfo);
	}
}

package com.sinochem.crude.trade.shipping.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.sinochem.crude.trade.common.constant.HttpRequestAttributes;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.constant.UrlMapping;
import com.sinochem.it.b2b.common.result.ResultDatas;

@Controller
@WithoutAccess
@RequestMapping(UrlMapping.PRODUCT_BACK_VOYAGETRACKING)
public class VoyageTrackingController {

	@RequestMapping(UrlMapping.INDEX)
	public ModelAndView index(){
		
		ModelAndView mav = new ModelAndView();
		
		System.out.println("111111111111111");
		mav.addObject("msg","12312123");
		return mav;
	}
	
	 @RequestMapping(UrlMapping.INDEX)
	    public void index(CurrentUser currentUser) {

	    }

	    @RequestMapping()
	    public void example1(CurrentUser currentUser, ModelMap modelMap) {
	        modelMap.put("message", "success"); //这里，“success”是魔法值，是不允许在正式代码中出现的
	    }

	    /**
	     * 尽量都用这种方法书写
	     * @param currentUser
	     * @param request
	     * @return
	     */
	    @RequestMapping()
	    public ModelAndView example2(CurrentUser currentUser, HttpServletRequest request) {
	        ModelAndView mav = new ModelAndView();
	        	//判断是否提交的是json格式 
	        if (((List<?>) request.getAttribute(HttpRequestAttributes.MEDIA_TYPES)).contains(MediaType.APPLICATION_JSON)) {
	            ResultDatas resultDatas = new ResultDatas();
	            resultDatas.setDatas("success");

	            mav.addAllObjects((Map<String, ?>) resultDatas.toMap());
	            mav.setView(new MappingJacksonJsonView());
	        } else {
	            mav.addObject("message", "success");
	        }

	        return mav;
	    }
}

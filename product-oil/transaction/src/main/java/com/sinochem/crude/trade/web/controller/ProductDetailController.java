package com.sinochem.crude.trade.web.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.constant.UrlMapping;
import com.sinochem.crude.trade.transaction.domain.DemandSheetCombine;
import com.sinochem.crude.trade.transaction.domain.SaleSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.*;
import com.sinochem.crude.trade.transaction.model.vo.*;
import com.sinochem.crude.trade.transaction.service.*;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.member.access.WithoutAccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 商品详情
 * 
 * @author Yichen Zhao date: 20180303
 */
@Controller
@RequestMapping(UrlMapping.PRODUCT_DETAIL)
public class ProductDetailController {

    @Autowired
    private EnterpriseService enterpriseService;

	@Autowired
	private SaleSheetService saleSheetService;

    @Autowired
    private DemandSheetService demandSheetService;

	@Value("${id.singapore}")
	private Long idSingapore;

	@Value("${id.quanzhou}")
	private Long quanzhou;

	/**
	 * 成品油资源列表
	 * @return
	 */
	@RequestMapping(value = UrlMapping.INDEX)
	public ModelAndView index(@RequestParam("uuid") String uuid , CurrentUser currentUser)
			throws BizException {

		ModelAndView mav = new ModelAndView();

		mav.addObject("user", currentUser);

		SaleSheet saleSheet = saleSheetService.getSaleSheetByUuid(currentUser, uuid);
		SaleSheetCombine saleSheetCombine = saleSheetService.getSaleSheetCombine(currentUser, saleSheet);

		mav.addObject("idSingapore", idSingapore);
		boolean idquanzhou = quanzhou.equals(currentUser.getEpMemberId());
		mav.addObject("idquanzhou", idquanzhou);

		mav.addObject("saleSheetVO", new SaleSheetVO(saleSheetCombine.getSaleSheet()));
		mav.addObject("productInfoVO", new ProductInfoVO(saleSheetCombine.getProductInfo()));
		mav.addObject("pricingInfoVO", new PricingInfoVO(saleSheetCombine.getPricingInfo()));
		mav.addObject("sellerInfoVO", new StakeHolderInfoVO(saleSheetCombine.getSalerInfo()));
		mav.addObject("transportInfoVO", new TransportInfoVO(saleSheetCombine.getTransportInfo()));
		mav.addObject("otherInfoVO", new OtherInfoVO(saleSheetCombine.getOtherInfo()));
		mav.addObject("userId",currentUser.getEpMemberId());
		if(null!=saleSheet&&saleSheet.getEnterpriseId()==100008253)
            mav.addObject("agentEnterpriseVo",enterpriseService.getByMemberId(100008254L));
		return mav;
	}

    /**
     * 成品油采购列表
     * @return
     */
    @RequestMapping(value = UrlMapping.DEMAND_INDEX)
    public ModelAndView demandIndex(@RequestParam("uuid") String uuid , CurrentUser currentUser)
            throws BizException {

        ModelAndView mav = new ModelAndView();

        mav.addObject("user", currentUser);

        DemandSheet demandSheet = demandSheetService.getDemandSheetByUuid(currentUser, uuid);
        DemandSheetCombine demandSheetCombine = demandSheetService.getDemandSheetCombine(currentUser, demandSheet);

        mav.addObject("demandSheetVO", new DemandSheetVO(demandSheetCombine.getDemandSheet()));
        mav.addObject("productInfoVO", new ProductInfoVO(demandSheetCombine.getProductInfo()));
        mav.addObject("pricingInfoVO", new PricingInfoVO(demandSheetCombine.getPricingInfo()));
        mav.addObject("buyerInfoVO", new StakeHolderInfoVO(demandSheetCombine.getBuyerInfo()));
        mav.addObject("transportInfoVO", new TransportInfoVO(demandSheetCombine.getTransportInfo()));
        mav.addObject("otherInfoVO", new OtherInfoVO(demandSheetCombine.getOtherInfo()));
        mav.addObject("userId",currentUser.getEpMemberId());
        return mav;
    }
}

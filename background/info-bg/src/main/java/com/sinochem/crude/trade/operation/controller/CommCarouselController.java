package com.sinochem.crude.trade.operation.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.URLMapping;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.commons.exceptions.CarouselException;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.operation.domain.OpeAdImageSmem;
import com.sinochem.crude.trade.operation.service.OpeAdImageSmemService;
import com.sinochem.crude.trade.operation.vo.AdImageSave;
import com.sinochem.crude.trade.operation.vo.CarouselQuery;
import com.sinochem.crude.trade.operation.vo.OpeAdImage;
import com.sinochem.it.b2b.member.access.ApiSafeAccess;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.WithoutAccess;

@Controller
public class CommCarouselController {

	@Autowired
	private OpeAdImageSmemService commCarouselService;
	private static Log log = LogFactory.getLog(CommCarouselController.class);

	/**
	 * 页面启动
	 */
	@RequestMapping(value = "/om/adImage/adImage.htm")
	public void unit(CurrentUser user, ModelMap modelMap) {
		
	}
	
	/**
	 * update adImagel
	 * 
	 * @param opeAdImage
	 * @param request
	 * @return
	 */
	@RightAccess(1172)
	@RequestMapping(value = URLMapping.UPDATE_AD_IMAGE_L, method = RequestMethod.POST)
	@ResponseBody
	public ResultDatas<Void> updateAdImagel(CurrentUser user, @RequestBody OpeAdImage opeAdImage, HttpServletRequest request) {
		ResultDatas<Void> res = new ResultDatas<>();
		try {
			OpeAdImageSmem opeAdImageSmem = BeanConvertUtils.beanToBean(opeAdImage, OpeAdImageSmem.class);
			opeAdImageSmem.setUpdateUser(ObjectUtils.toString(user.getMemberId()));
			opeAdImageSmem.setUpdateDate(DateTimeUtils.currentDate());
			commCarouselService.updateRecord(opeAdImageSmem);

		} catch (CarouselException e) {
			log.error(e);
			res.setFail(e.getMessage());
			res.setCode(e.getCode());
		}
		return res;
	}

	/**
	 * carousel update
	 * 
	 * @param request
	 * @param vo
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = URLMapping.UPDATE_AD_IMAGE, method = RequestMethod.POST)
	@ResponseBody
	public ResultDatas<Void> updateAdImage(CurrentUser user, @RequestBody AdImageSave opeAdImage, HttpServletRequest request) {
		ResultDatas<Void> res = new ResultDatas<>();
		try {
			OpeAdImageSmem opeAdImageSmem = new OpeAdImageSmem();
			opeAdImageSmem.setImageUrl(opeAdImage.getImageUrl());
			opeAdImageSmem.setImageId(opeAdImage.getImageId());
			opeAdImageSmem.setUpdateDate(DateTimeUtils.currentDate());
			opeAdImageSmem.setUpdateUser(ObjectUtils.toString(user.getMemberId()));
			commCarouselService.updateRecord(opeAdImageSmem);		
		} catch (CarouselException e) {
			log.error(e);
			res.setFail(e.getMessage());
			res.setCode(e.getCode());
		}
		return res;
	}

	/**
	 * get carousel by id
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = URLMapping.CAROUSEL_QUERY_ONE, method = RequestMethod.POST)
	@ResponseBody
	public ResultDatas<OpeAdImage> queryCarouselByKey(@RequestBody String id,
			HttpServletRequest request) {
		ResultDatas<OpeAdImage> res = new ResultDatas<>();
		try {
			OpeAdImage opeAdImage = new OpeAdImage();
			OpeAdImageSmem adImage = commCarouselService.findByPrimaryKey(id);	
			if (adImage != null) {
				opeAdImage.setImageId(adImage.getImageId());
				opeAdImage.setTypeCode(adImage.getTypeCode());
				opeAdImage.setImageDes(adImage.getImageDes());
				opeAdImage.setSortCode(adImage.getSortCode());
				opeAdImage.setImageUrl(adImage.getImageUrl());
				opeAdImage.setGotoUrl(adImage.getGotoUrl());
				opeAdImage.setPageCode(adImage.getPageCode());
			}
			
			res.setDatas(opeAdImage);
		} catch (CarouselException e) {
			log.error(e);
			res.setFail(e.getMessage());
			res.setCode(e.getCode());
		}
		return res;
	}

	/**
	 * get typecode
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = URLMapping.QUERY_TYPE_CODE, method = RequestMethod.POST)
	@ResponseBody
	public ResultDatas<List<String>> queryTypeCode(HttpServletRequest request) {
		ResultDatas<List<String>> res = new ResultDatas<>();
		try {
			List<String> lt = commCarouselService.queryTypeCode();

			res.setDatas(lt);;
		} catch (CarouselException e) {
			log.error(e);
			res.setFail(e.getMessage());
			res.setCode(e.getCode());
		}
		return res;
	}

	/**
	 * get imagedes
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = URLMapping.QUERY_IMAGE_DES, method = RequestMethod.POST)
	@ResponseBody
	public ResultDatas<List<String>> queryImageDes(HttpServletRequest request) {
		ResultDatas<List<String>> res = new ResultDatas<>();
		try {
			List<String> lt = commCarouselService.queryImageDes();
			res.setDatas(lt);
		} catch (CarouselException e) {
			log.error(e);
			res.setFail(e.getMessage());
			res.setCode(e.getCode());
		}
		return res;
	}
	
	/**
	 * get pageCode
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = URLMapping.QUERY_PAGE_CODE, method = RequestMethod.POST)
	@ResponseBody
	public ResultDatas<List<String>> queryPageCode(@RequestBody OpeAdImage opeAdImage, HttpServletRequest request) {
		ResultDatas<List<String>> res = new ResultDatas<>();
		try {
			List<String> lt = commCarouselService.queryPageCode(opeAdImage.getTypeCode());
			res.setDatas(lt);
		} catch (CarouselException e) {
			log.error(e);
			res.setFail(e.getMessage());
			res.setCode(e.getCode());
		}
		return res;
	}

	/**
	 * get carousels by condition.
	 * 
	 * @param request
	 * @param vo
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	@RightAccess(1170)
	@RequestMapping(value = URLMapping.QUERY_CAROUSELS, method = RequestMethod.POST)
	@ResponseBody
	@WithoutAccess
	public ResultDatas<List<OpeAdImage>> queryCarousels(@RequestBody CarouselQuery carouselQuery, HttpServletRequest request) {
		ResultDatas<List<OpeAdImage>> res = new ResultDatas<>();
		try {
			SimplePageInfo pageInfo = new SimplePageInfo();
			pageInfo.setPageNum(carouselQuery.getPageNum());
			pageInfo.setPageSize(carouselQuery.getPageSize());
			Page<Map<String, Object>> pageMap = commCarouselService.queryCarousels(carouselQuery, pageInfo);
			List<OpeAdImage> list = BeanConvertUtils.mapToBeanInList(pageMap, OpeAdImage.class);
			res.setDatas(list);
			res.setPageNum(pageMap.getPageNum());
			res.setPageSize(pageMap.getPageSize());
			res.setTotal(pageMap.getTotal());
		} catch (CarouselException e) {
			log.error(e);
			res.setFail(e.getMessage());
			res.setCode(e.getCode());
		}
		return res;
	}

	/**
	 * add adImagel
	 * 
	 * @param opeAdImage
	 * @param request
	 * @return
	 */
	@RightAccess(1171)
	@RequestMapping(value = URLMapping.ADD_AD_IMAGE_L, method = RequestMethod.POST)
	@ResponseBody
	public ResultDatas<Void> addAdImagel(CurrentUser user, @RequestBody OpeAdImage opeAdImage, HttpServletRequest request) {
		ResultDatas<Void> res = new ResultDatas<>();
		try {
			OpeAdImageSmem opeAdImageSmem = BeanConvertUtils.beanToBean(opeAdImage, OpeAdImageSmem.class);
			opeAdImageSmem.setImageId(KeyGenUtils.newUuid());
			opeAdImageSmem.setCreateDate(DateTimeUtils.currentDate());
			opeAdImageSmem.setCreateUser(ObjectUtils.toString(user.getMemberId()));
			opeAdImageSmem.setUpdateDate(DateTimeUtils.currentDate());
			opeAdImageSmem.setUpdateUser(ObjectUtils.toString(user.getMemberId()));
			opeAdImageSmem.setAliveFlag(Constants.ALIEVE_FLAG);
			opeAdImageSmem.setLangVer(Constants.LANG_VER_ZH);
			commCarouselService.insertRecord(opeAdImageSmem);

		} catch (CarouselException e) {
			log.error(e);
			res.setFail(e.getMessage());
			res.setCode(e.getCode());
		}
		return res;
	}

	/**
	 * add Imagel
	 * 
	 * @param opeAdImage
	 * @param request
	 * @return
	 */
	@RequestMapping(value = URLMapping.ADD_AD_IMAGE, method = RequestMethod.POST)
	@ResponseBody
	public ResultDatas<Boolean> addImagel(CurrentUser user, @RequestBody AdImageSave opeAdImage, HttpServletRequest request) {
		ResultDatas<Boolean> res = new ResultDatas<>();
		try {
			OpeAdImageSmem opeAdImageSmem = BeanConvertUtils.beanToBean(opeAdImage, OpeAdImageSmem.class);
			opeAdImageSmem.setImageId(KeyGenUtils.newUuid());
			opeAdImageSmem.setCreateDate(DateTimeUtils.currentDate());
			opeAdImageSmem.setCreateUser(ObjectUtils.toString(user.getMemberId()));
			opeAdImageSmem.setUpdateDate(DateTimeUtils.currentDate());
			opeAdImageSmem.setUpdateUser(ObjectUtils.toString(user.getMemberId()));
			commCarouselService.insertRecord(opeAdImageSmem);	
			
		} catch (CarouselException e) {
			log.error(e);
			res.setFail(e.getMessage());
			res.setCode(e.getCode());
		}
		return res;
	}

	/**
	 * delete adImagel
	 * 
	 * @param opeAdImage
	 * @param request
	 * @return
	 */
	@RightAccess(1173)
	@RequestMapping(value = URLMapping.DELETE_CAROUSEL, method = RequestMethod.POST)
	@ResponseBody
	public ResultDatas<Boolean> deleteAdImagel(CurrentUser user, @RequestBody String imageId, HttpServletRequest request) {
		ResultDatas<Boolean> res = new ResultDatas<>();
		try {
			commCarouselService.deleteRecordByKey(imageId, ObjectUtils.toString(user.getMemberId()));
			
		} catch (CarouselException e) {
			log.error(e);
			res.setFail(e.getMessage());
			res.setCode(e.getCode());
		}
		return res;
	}
}

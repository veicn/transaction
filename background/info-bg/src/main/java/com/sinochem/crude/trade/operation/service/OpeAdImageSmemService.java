package com.sinochem.crude.trade.operation.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.operation.dao.OpeAdImageSmemMapper;
import com.sinochem.crude.trade.operation.domain.OpeAdImageSmem;
import com.sinochem.crude.trade.operation.vo.CarouselQuery;
import com.sinochem.crude.trade.operation.vo.OpeAdImageRes;
import com.sinochem.crude.trade.operation.vo.OpeAdImageSetIn;

public interface OpeAdImageSmemService {

	public abstract OpeAdImageSmemMapper getMapper();

	public abstract List<OpeAdImageSmem> queryByEntitys(OpeAdImageSmem opeadimagesmem);

	public abstract OpeAdImageSmem findByPrimaryKey(String imageId);

	public abstract void updateRecord(OpeAdImageSmem opeadimagesmem);

	public abstract void deleteRecordByKey(String imageId, String deleteUser);

	public abstract void insertRecord(OpeAdImageSmem opeadimagesmem);

	public abstract List<Map<String, Object>> queryRecords(Map<String, Object> map);

	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo);

	public abstract int countRecords(Map<String, Object> map);

	public abstract void deleteRecords(Map<String, Object> map);

	public void updateRecords(Map<String, Object> map);

	// **********************************************************/
	// 查询去重系统类型
	public abstract List<String> queryImageDes();

	// 查询去重 版本类型
	public abstract List<String> queryTypeCode();
	
	// 查询去重区域
	public abstract List<String> queryPageCode(String typeCode);

	// 条件查询 轮播图
	public abstract Page<Map<String, Object>> queryCarousels(CarouselQuery iCarouselQuery, SimplePageInfo pageInfo);

	/** 根据条件查询广告位下的图片 */
	public abstract List<OpeAdImageRes> queryAdimageListByOpeAdImage(OpeAdImageSetIn input);

	/** 查询广告图 */
	public abstract Page<Map<String, Object>> adImagelList(Map<String, Object> beanToMap, SimplePageInfo pageInfo);

}

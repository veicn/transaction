package com.sinochem.crude.trade.goods.remote;

import java.util.List;

/**
 * Created by liuzuobin on 27/11/2017.
 */
public interface CrudeOilInfoService {
	
	/**
	 * 查询原油信息
	 * @param crudeName 原油名称，模糊查询，支持中英文
	 * @param catagoryId 原油种类id
	 * @return
	 */
	public List<CrudeOilInfoVO> findCrudeOilInfos(String crudeName, Long catagoryId);
	
	/**
	 * 查询原油信息ById
	 * @param id
	 * @return
	 */
	public CrudeOilInfoVO findCrudeOilInfoById(Long id);
	
	/**
	 * 查询原油信息ByUUID
	 * @param uuid
	 * @return
	 */
	public CrudeOilInfoVO findCrudeOilInfoByUUID(String uuid);
	
	/**
	 * 增加原油
	 * @param crudeOilInfoVO
	 * @return
	 */
	public int addCrudeOilInfo(CrudeOilInfoVO crudeOilInfoVO);

	/**
	 * 根据英文名称和地区id查询
	 */
	public CrudeOilInfoVO findByENameAndAreaId(Long areaId);
}

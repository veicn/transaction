package com.sinochem.crude.trade.info.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.info.dao.MesticProductPriceMapper;
import com.sinochem.crude.trade.info.domain.MesticProductPrice;
import com.sinochem.crude.trade.info.service.MesticProductPriceService;

@Service
public class MesticProductPriceServiceImpl implements MesticProductPriceService {
	@Autowired
	private MesticProductPriceMapper mesticProductPriceMapper;
	
	public MesticProductPriceMapper getMapper(){
		return mesticProductPriceMapper;
	} 
	
	private static final Log log = LogFactory.getLog(MesticProductPriceServiceImpl.class);
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(MesticProductPrice mesticproductprice){
		
		String uuid = UUID.randomUUID().toString().replace("-", "");
		mesticproductprice.setUuid(uuid);
		mesticproductprice.setCreateDate(DateTimeUtils.currentDate());
		mesticproductprice.setUpdateDate(DateTimeUtils.currentDate());
		//TODO 拿到 创建人ID
		mesticproductprice.setCreateUser("1");
		mesticproductprice.setUpdateUser("1");
		
		return mesticProductPriceMapper.insertRecord(mesticproductprice);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return mesticProductPriceMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(String uuid){
    	return mesticProductPriceMapper.deleteRecords(uuid);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(MesticProductPrice mesticProductPrice) throws BizException{
		if ( mesticProductPrice.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return mesticProductPriceMapper.updateRecordById(mesticProductPrice);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(MesticProductPrice mesticProductPrice){
		return mesticProductPriceMapper.updateRecords(mesticProductPrice);
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public MesticProductPrice findByPrimaryKey(Long id){
		return  mesticProductPriceMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public MesticProductPrice findByUuid(String uuid){
		return  mesticProductPriceMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<MesticProductPrice> queryByEntitys(MesticProductPrice mesticProductPrice){
		return  mesticProductPriceMapper.primateKey(mesticProductPrice);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return mesticProductPriceMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) mesticProductPriceMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return mesticProductPriceMapper.countRecords(map);
	}

	@Override
	public List<MesticProductPrice> findTotal(MesticProductPrice mesticproductprice) {
		return mesticProductPriceMapper.findTotal(mesticproductprice);
	}
	
	//**************************以下方法为开发者补充*********************************/

	
	
	
}

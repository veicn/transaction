package com.sinochem.crude.trade.info.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.dao.FuturesPriceMapper;
import com.sinochem.crude.trade.info.domain.FuturesPrice;
import com.sinochem.crude.trade.info.model.PIMSVo;
import com.sinochem.crude.trade.info.service.FuturesPriceService;
import com.sinochem.it.b2b.common.exception.BizException;

@Service
public class FuturesPriceServiceImpl implements FuturesPriceService {
	@Autowired
	private FuturesPriceMapper futuresPriceMapper;
	
	public FuturesPriceMapper getMapper(){
		return futuresPriceMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(FuturesPrice futuresprice){
		 return futuresPriceMapper.insertRecord(futuresprice);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return futuresPriceMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(FuturesPrice  record){
    	return futuresPriceMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(FuturesPrice futuresPrice) throws BizException{
		if ( futuresPrice.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return futuresPriceMapper.updateRecordById(futuresPrice);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return futuresPriceMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(FuturesPrice futuresPrice){
		return futuresPriceMapper.updateRecords(futuresPrice.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public FuturesPrice findByPrimaryKey(Long id){
		return  futuresPriceMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public FuturesPrice findByUuid(String uuid){
		return  futuresPriceMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<FuturesPrice> queryByEntitys(FuturesPrice futuresPrice){
		return  futuresPriceMapper.queryByEntitys(futuresPrice);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return futuresPriceMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) futuresPriceMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return futuresPriceMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 获取最新的一条记录
	 * @return
	 */
	public FuturesPrice getLatest(Long crudeId){
		FuturesPrice param = new FuturesPrice();
		param.setCrudeId(crudeId);
		param.setLatestFlag("1");
		param.setAliveFlag(Constants.ALIVE_FLAG_1);
		if(CollectionUtils.isNotEmpty(this.queryByEntitys(param)))
			return this.queryByEntitys(param).get(0);
		else 
			return null;
	}
	
	/**
	 * 新增数据，处理最新标志字段
	 * @param agio
	 * @return
	 * @throws Exception
	 */
	public int insertFuturesPrice(FuturesPrice futuresPrice) throws Exception{
		//设置插入初始值
		futuresPrice.setId(null);
		futuresPrice.setUuid(UUID.randomUUID().toString().replace("-", ""));
		futuresPrice.setCreateDate(new Date());
		futuresPrice.setAliveFlag("1");
		
		FuturesPrice latest = this.getLatest(futuresPrice.getCrudeId());
		
		if(latest==null){
			futuresPrice.setLatestFlag("1");
			return this.insertRecord(futuresPrice);
		}else{
			if(latest.getPricingDate().before(futuresPrice.getPricingDate())){
				latest.setLatestFlag("0");
				this.updateRecordById(latest);
				
				futuresPrice.setLatestFlag("1");
				return this.insertRecord(futuresPrice);
			}else{
				futuresPrice.setLatestFlag("0");
				return this.insertRecord(futuresPrice);
			}
		}
	}
	/**
	 * PMIS查询期货结算价格
	 */
	@Override
	public List<Map<String, Object>> queryFuturePrice(PIMSVo vo) {
		return futuresPriceMapper.queryFuturePrice(vo);
	}
}

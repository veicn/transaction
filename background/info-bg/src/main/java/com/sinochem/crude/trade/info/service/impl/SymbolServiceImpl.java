package com.sinochem.crude.trade.info.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.dao.SymbolMapper;
import com.sinochem.crude.trade.info.domain.Symbol;
import com.sinochem.crude.trade.info.model.SymbolVO;
import com.sinochem.crude.trade.info.service.SymbolService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.xxl.SymbolPriceHandler;

@Service
public class SymbolServiceImpl implements SymbolService {
	@Autowired
	private SymbolMapper symbolMapper;
	
	public SymbolMapper getMapper(){
		return symbolMapper;
	} 
	
    private static final Log log = LogFactory.getLog(SymbolPriceHandler.class);

	/**
	 * 新增
	 */
	@Override
	public int insertRecord(Symbol symbol){
		symbol.setUuid(KeyGenUtils.newUuid());
		symbol.setAliveFlag(Constants.ALIEVE_FLAG);
		symbol.setCreateDate(DateTimeUtils.currentDate());
		if(StringUtils.isEmpty(symbol.getSymbol())){
			StringBuilder sb=new StringBuilder();
			sb.append(symbol.getSymbolName());
			sb.append("-");
			sb.append(symbol.getCommodity());
			sb.append("-");
			sb.append(symbol.getPriceSource());
			sb.append("-");
			sb.append(symbol.getMarket());
			symbol.setSymbol(sb.toString());
		}
		int total = symbolMapper.insertRecord(symbol);
		/*Symbol updateSymbol=new Symbol();
		updateSymbol.setSymbol(String.format("%06d", symbol.getId()));
		updateSymbol.setId(symbol.getId());
		this.updateRecordById(updateSymbol);*/
		return total;
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return symbolMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(Symbol  record){
    	return symbolMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(Symbol symbol) throws BizException{
		if ( symbol.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return symbolMapper.updateRecordById(symbol);
	}
	
	/**
	 * 根据uuid-修改对象
	 */
	@Override
	public int updateRecordByUuid(Symbol symbol) throws BizException{
		if ( symbol.getUuid() == null) {
			throw new BizException("uuid为空，不能修改 ");
		}
		return symbolMapper.updateRecordByUuid(symbol);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return symbolMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Symbol symbol){
		return symbolMapper.updateRecords(symbol.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public Symbol findByPrimaryKey(Long id){
		return  symbolMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public Symbol findByUuid(String uuid){
		return  symbolMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<Symbol> queryByEntitys(Symbol symbol){
		return  symbolMapper.queryByEntitys(symbol);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return symbolMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) symbolMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return symbolMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 新增或更新合约
	 */
	@Override
	public Result saveOrUpdateSymbol(Symbol symbol,CurrentUser user) {
		Result res=new Result();
		if(StringUtils.isBlank(symbol.getUuid())) {
			//新增
			symbol.setCreateUser(user.getName());
			if(this.insertRecord(symbol) == 1){
				res.setMessage("合约新增成功");
			}
			return res;
		}else {
			if(this.updateRecordByUuid(symbol) == 1){
				res.setMessage("合约修改成功");
			}
			return res;
		}
		
	}

	@Override
	public List<Map<String, Object>> getSymbol(SymbolVO vo) {
		return symbolMapper.getSymbol(vo);
	}
	
	/**
	 * 获取标的商品列表
	 * @return
	 */
	@Override
	public List<String> getCommodityList(Map<String, Object> map){
		return symbolMapper.getCommodityList(map);
	}
	
	/**
	 * 获取价格源列表
	 * @return
	 */
	@Override
	public List<String> getPriceSourceList(Map<String, Object> map){
		return symbolMapper.getPriceSourceList(map);
	}
	
	/**
	 * 获取市场列表
	 * @return
	 */
	@Override
	public List<String> getMarketList(Map<String, Object> map){
		return symbolMapper.getMarketList(map);
	}

	@Override
	public int updateSymbolNameBySymbol(Symbol symbol) {
		return symbolMapper.updateSymbolNameBySymbol(symbol);
	}

	@Override
	public Integer querySymbol(Symbol symbol) {
		return symbolMapper.querySymbol(symbol);
	}
	/**
	 * 根据合约代码取合约名称
	 * @return
	 */
	@Override
	public String queryBySymbol(String crudeName) {
		return symbolMapper.queryBySymbol(crudeName);
	}

	//更新合约名称
	@Override
	public void editSymbolName(String symbolCode, String symbolName) {
		Symbol symbol=new Symbol();
		symbol.setSymbol(symbolCode);
		List<Symbol> datas = this.queryByEntitys(symbol);
		if(!CollectionUtils.isEmpty(datas)){
			if(!datas.get(0).getSymbolName().equals(symbolName)){
				symbol.setSymbolName(symbolName);
				int total=this.updateSymbolNameBySymbol(symbol);
				if(total == 1){
					log.info(symbolCode+"--合约名称修改为--" +symbolName);
				}
			}
		}
	}
	/**
	 * 更新金凯讯合约代码
	 * 
	 * @return
	 */
	@Override
	public void saveOrUpdateExtend1(String symbolCode,String jkxCode) {
		Symbol symbol=new Symbol();
		symbol.setSymbol(symbolCode);
		List<Symbol> datas = this.queryByEntitys(symbol);
		if(!CollectionUtils.isEmpty(datas)){
			if(StringUtils.isEmpty(datas.get(0).getExtend1())){
				symbol.setExtend1(jkxCode);
				int total=this.updateSymbolNameBySymbol(symbol);
				if(total == 1){
					log.info(symbolCode+"--金凯讯合约代码修改为--" +jkxCode);
				}
			}else if(!datas.get(0).getExtend1().equals(jkxCode)){
				symbol.setExtend1(jkxCode);
				int total=this.updateSymbolNameBySymbol(symbol);
				if(total == 1){
					log.info(symbolCode+"--金凯讯合约代码修改为--" +jkxCode);
				}
			}
		}
	}
	
	
}

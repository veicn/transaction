package com.sinochem.crude.trade.shiprefueling.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.eyeieye.melody.util.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shiprefueling.constants.Constants;
import com.sinochem.crude.trade.shiprefueling.controller.common.ResultDatas;
import com.sinochem.crude.trade.shiprefueling.controller.common.SimplePageInfo;
import com.sinochem.crude.trade.shiprefueling.dao.GoryMapper;
import com.sinochem.crude.trade.shiprefueling.dao.RSupplyMapper;
import com.sinochem.crude.trade.shiprefueling.domain.RSupplyCombine;
import com.sinochem.crude.trade.shiprefueling.domain.po.Gory;
import com.sinochem.crude.trade.shiprefueling.domain.po.RSupply;
import com.sinochem.crude.trade.shiprefueling.model.query.QueryRSupply;
import com.sinochem.crude.trade.shiprefueling.model.vo.QueryRSupplyVO;
import com.sinochem.crude.trade.shiprefueling.service.RSupplyService;
import com.sinochem.it.b2b.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;


@Service
public class RSupplyServiceImpl implements RSupplyService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RSupplyMapper rSupplyMapper;
	@Autowired
    private GoryMapper goryMapper;
	
	public RSupplyMapper getMapper(){
		return rSupplyMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(RSupply rsupply){
		 return rSupplyMapper.insertRecord(rsupply);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long orderId) throws BizException{
		if (orderId == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return rSupplyMapper.deleteById(orderId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(RSupply  record){
    	return rSupplyMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(RSupply rSupply) throws BizException{
		if ( rSupply.getOrderId() == null  ) {
			throw new BizException("orderId 为空，不能修改 ");
		}
		return rSupplyMapper.updateRecordById(rSupply);
	}
	
	/**
	 * 根据uuid-修改对象
	 */
	@Override
	public int updateRecordByUuid(RSupply rSupply) throws BizException {
		if ( rSupply.getUuid() == null) {
			throw new BizException("uuid为空，不能修改 ");
		}
		return rSupplyMapper.updateRecordByUuid(rSupply);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return rSupplyMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(RSupply rSupply){
		return rSupplyMapper.updateRecords(rSupply.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public RSupply findByPrimaryKey(Long orderId){
		return  rSupplyMapper.findByPrimaryKey(orderId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public RSupply findByUuid(String uuid){
		return  rSupplyMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<RSupply> queryByEntitys(RSupply rSupply){
		return  rSupplyMapper.queryByEntitys(rSupply);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return rSupplyMapper.queryRecords(map);
	}

	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return rSupplyMapper.countRecords(map);
	}



	//**************************以下方法为开发者补充*********************************/

	/**
	 * 船供订单分页列表
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	@Override
	public ResultDatas<List<Map<String, Object>>> querySupplyRecords(Map<String, Object> map, SimplePageInfo pageInfo) throws ParseException {
		logger.info("下游订单分页查询,查询参数:{}", JSONObject.toJSONString(map));
		this.queryTimeConvert(map);

		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), "orderTime DESC,createDate DESC");

        List<Map<String, Object>> orderList = rSupplyMapper.querySupplyRecords(map);
        if(orderList != null && !orderList.isEmpty()) {
            for (Map<String, Object> orderMap : orderList) {
				HashMap query = new HashMap();
				query.put("orderId", orderMap.get("orderId"));
				query.put("oilClassification", map.get("oilClassification"));
				query.put("oilVarieties", map.get("oilVarieties"));
				query.put("orderType", 2);
                List<Gory> goryList = goryMapper.findByOrder(query);
                orderMap.put("goryList", goryList);
            }
        }

        ResultDatas<List<Map<String,Object>>> res=new ResultDatas<>();
        Page<Map<String, Object>> page = new Page<>();
        page = (Page<Map<String, Object>>) orderList;
        res.setDatas(page);
        res.setPageNum(page.getPageNum());
        res.setPageSize(page.getPageSize());
        res.setTotal(page.getTotal());
        res.setPageCount(page.getPages());
        return res;
	}

    @Override
    public ResultDatas<List<Map<String, Object>>> queryBuySupplyGroupByCompany(Map<String, Object> map, SimplePageInfo pageInfo) {
		logger.info("买家按交易对手展示,查询参数:{};分页参数:{}", JSONObject.toJSONString(map), JSONObject.toJSONString(pageInfo));
		this.queryTimeConvert(map);
		ResultDatas<List<Map<String, Object>>> res = new ResultDatas<>();
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
        // 分页统计买家订单
        Page<Map<String, Object>> page = (Page<Map<String, Object>>) rSupplyMapper.queryBuyCompanyOrder(map);
        res.setDatas(page);
        res.setPageNum(page.getPageNum());
        res.setPageSize(page.getPageSize());
        res.setTotal(page.getTotal());
        res.setPageCount(page.getPages());
        return res;
    }

    @Override
    public ResultDatas<List<Map<String, Object>>> querySellSupplyGroupByCompany(Map<String, Object> map, SimplePageInfo pageInfo) {
		logger.info("卖家按交易对手展示,查询参数:{};分页参数:{}", JSONObject.toJSONString(map), JSONObject.toJSONString(pageInfo));
		this.queryTimeConvert(map);
		ResultDatas<List<Map<String, Object>>> res = new ResultDatas<>();
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
        // 分页统计卖家订单
        Page<Map<String, Object>> page = (Page<Map<String, Object>>) rSupplyMapper.querySellCompanyOrder(map);
        res.setDatas(page);
        res.setPageNum(page.getPageNum());
        res.setPageSize(page.getPageSize());
        res.setTotal(page.getTotal());
        res.setPageCount(page.getPages());
        return res;
    }

    @Override
    public List<Map<String, Object>> queryOrderListtByCompany(Map<String, Object> map) {
		logger.info("按交易对手展示展开,查询参数:{}", JSONObject.toJSONString(map));
		this.queryTimeConvert(map);
        List<Map<String, Object>> list = rSupplyMapper.querySupplyRecords(map);
        for (Map<String, Object> stringObjectMap : list) {
            List<Gory> goryList = goryMapper.findByOrder(map);
            stringObjectMap.put("goryList", goryList);
        }
        return list;
    }

    @Override
	public String buyImportRSupply(List<RSupplyCombine> list, CurrentUser user){
		String mess = "";
		Integer all = 0;
		Integer suceessFn = 0;
		Integer falseFn = 0;
		all = list.size();
		for (RSupplyCombine combine : list) {
			try {
				RSupply rSupply = combine.getrSupply();
				Long buyCompanyId = rSupply.getBuyCompanyId();
				Long sellCompanyId = rSupply.getSellCompanyId();
				if(buyCompanyId != null && buyCompanyId.equals(user.getEpMemberId())){
					rSupply.setUuid(KeyGenUtils.newUuid());
					rSupply.setLangVer(Constants.LANG_VER);
					rSupply.setAliveFlag(Constants.ALIEVE_FLAG_YES);
					rSupply.setCreateDate(new Date());
					rSupply.setUpdateDate(new Date());
					rSupply.setUpdateUser(user.getMemberId());
					rSupply.setCreateUser(user.getMemberId());
					rSupply.setVersion("1");
                    String uuid = KeyGenUtils.newUuid();
                    rSupply.setUuid(uuid);

					this.insertRecord(rSupply);
					for (Gory gory: combine.getList()) {
						if(  !StringUtil.isEmpty(gory.getOilClassification())
								&& gory.getNumber() != null && gory.getUnitPrice() != null){
							gory.setUuid(KeyGenUtils.newUuid());
							gory.setLangVer(Constants.LANG_VER);
							gory.setAliveFlag(Constants.ALIEVE_FLAG_YES);
							gory.setCreateDate(new Date());
							gory.setUpdateDate(new Date());
							gory.setUpdateUser(user.getMemberId());
							gory.setCreateUser(user.getMemberId());
							gory.setVersion("1");
							gory.setOrderType("1");
							gory.setOrderUuid(uuid);
							gory.setOrderId(rSupply.getOrderId());
							goryMapper.insertRecord(gory);
						}else{
							throw new RuntimeException("数据不符合");
						}
					}
					suceessFn++;
				}else{
					falseFn++;
				}

			} catch (Exception e) {
				falseFn++;
				e.printStackTrace();
			}
		}
		mess = "本次导入共"+all+"条，其中成功"+suceessFn+"条，失败"+falseFn+"条";
		return mess;
	}
	@Override
	public String sellerImportRSupply(List<RSupplyCombine> list, CurrentUser user){
		String mess = "";
		Integer all = 0;
		Integer suceessFn = 0;
		Integer falseFn = 0;
		all = list.size();
		for (RSupplyCombine combine : list) {
			try {
				RSupply rSupply = combine.getrSupply();
				Long buyCompanyId = rSupply.getBuyCompanyId();
				Long sellCompanyId = rSupply.getSellCompanyId();
				if(sellCompanyId != null && sellCompanyId.equals(user.getEpMemberId())){
					rSupply.setUuid(KeyGenUtils.newUuid());
					rSupply.setLangVer(Constants.LANG_VER);
					rSupply.setAliveFlag(Constants.ALIEVE_FLAG_YES);
					rSupply.setCreateDate(new Date());
					rSupply.setUpdateDate(new Date());
					rSupply.setUpdateUser(user.getMemberId());
					rSupply.setCreateUser(user.getMemberId());
					rSupply.setVersion("1");
                    String uuid = KeyGenUtils.newUuid();
                    rSupply.setUuid(uuid);
					this.insertRecord(rSupply);
					for (Gory gory: combine.getList()) {
						if(  !StringUtil.isEmpty(gory.getOilClassification())
								&& gory.getNumber() != null && gory.getUnitPrice() != null){
							gory.setUuid(KeyGenUtils.newUuid());
							gory.setLangVer(Constants.LANG_VER);
							gory.setAliveFlag(Constants.ALIEVE_FLAG_YES);
							gory.setCreateDate(new Date());
							gory.setUpdateDate(new Date());
							gory.setUpdateUser(user.getMemberId());
							gory.setCreateUser(user.getMemberId());
							gory.setVersion("1");
							gory.setOrderType("1");
							gory.setOrderUuid(uuid);
							gory.setOrderId(rSupply.getOrderId());
							goryMapper.insertRecord(gory);
						}else{
							throw new RuntimeException("数据不符合");
						}
					}
					suceessFn++;
				}else{
					falseFn++;
				}

			} catch (Exception e) {
				falseFn++;
				e.printStackTrace();
			}
		}
		mess = "本次导入共"+all+"条，其中成功"+suceessFn+"条，失败"+falseFn+"条";
		return mess;
	}
	@Override
	public String omImportRSupply(List<RSupplyCombine> list, CurrentUser user){
		String mess = "";
		Integer all = 0;
		Integer suceessFn = 0;
		Integer falseFn = 0;
		all = list.size();
		for (RSupplyCombine combine : list) {
			try {
				RSupply rSupply = combine.getrSupply();
				Long buyCompanyId = rSupply.getBuyCompanyId();
				Long sellCompanyId = rSupply.getSellCompanyId();
				rSupply.setUuid(KeyGenUtils.newUuid());
				rSupply.setLangVer(Constants.LANG_VER);
				rSupply.setAliveFlag(Constants.ALIEVE_FLAG_YES);
				rSupply.setCreateDate(new Date());
				rSupply.setUpdateDate(new Date());
				rSupply.setUpdateUser(user.getMemberId());
				rSupply.setCreateUser(user.getMemberId());
				rSupply.setVersion("1");
                String uuid = KeyGenUtils.newUuid();
                rSupply.setUuid(uuid);
				this.insertRecord(rSupply);
				for (Gory gory: combine.getList()) {
					if( !StringUtil.isEmpty(gory.getOilClassification())
							&& gory.getNumber() != null && gory.getUnitPrice() != null){
						gory.setUuid(KeyGenUtils.newUuid());
						gory.setLangVer(Constants.LANG_VER);
						gory.setAliveFlag(Constants.ALIEVE_FLAG_YES);
						gory.setCreateDate(new Date());
						gory.setUpdateDate(new Date());
						gory.setUpdateUser(user.getMemberId());
						gory.setCreateUser(user.getMemberId());
						gory.setVersion("1");
						gory.setOrderType("1");
						gory.setOrderUuid(uuid);
						gory.setOrderId(rSupply.getOrderId());
						goryMapper.insertRecord(gory);
					}else{
						throw new RuntimeException("数据不符合");
					}
				}
				suceessFn++;
			} catch (Exception e) {
				falseFn++;
				e.printStackTrace();
			}
		}
		mess = "本次导入共"+all+"条，其中成功"+suceessFn+"条，失败"+falseFn+"条";
		return mess;
	}

	@Override
    public int updateRecordsAliveFlag(Long orderId, String flag) {
		return rSupplyMapper.updateRecordsAliveFlag(orderId, flag);
	}

	@Override
	public List<RSupply> queryByQueryRSupply(QueryRSupplyVO queryRSupplyVO){
		QueryRSupply queryRSupply = queryRSupplyVO.getQueryRSupply();
		if(queryRSupply != null){
			return rSupplyMapper.queryByQueryRSupply(queryRSupply);
		}else{
			return null;
		}
	}

	private void queryTimeConvert(Map<String, Object> map) {
        String orderTimeStart = map.get("orderTimeStart") != null &&
                StringUtil.isNotBlank(String.valueOf(map.get("orderTimeStart"))) ?
                String.valueOf(map.get("orderTimeStart") + " 00:00:00") : null;
        String orderTimeEnd =  map.get("orderTimeEnd") != null &&
                StringUtil.isNotBlank(String.valueOf(map.get("orderTimeEnd"))) ?
                String.valueOf(map.get("orderTimeEnd") + " 23:59:59") : null;
        map.put("orderTimeStart", orderTimeStart);
        map.put("orderTimeEnd", orderTimeEnd);
	}
}

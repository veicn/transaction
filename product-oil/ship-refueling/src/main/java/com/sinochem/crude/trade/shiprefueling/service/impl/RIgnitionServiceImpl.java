package com.sinochem.crude.trade.shiprefueling.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.eyeieye.melody.util.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shiprefueling.constants.Constants;
import com.sinochem.crude.trade.shiprefueling.controller.common.ResultDatas;
import com.sinochem.crude.trade.shiprefueling.controller.common.SimplePageInfo;
import com.sinochem.crude.trade.shiprefueling.dao.ChmentsMapper;
import com.sinochem.crude.trade.shiprefueling.dao.GoryMapper;
import com.sinochem.crude.trade.shiprefueling.dao.RIgnitionMapper;
import com.sinochem.crude.trade.shiprefueling.domain.RIgnitionCombine;
import com.sinochem.crude.trade.shiprefueling.domain.po.Chments;
import com.sinochem.crude.trade.shiprefueling.domain.po.Gory;
import com.sinochem.crude.trade.shiprefueling.domain.po.RIgnition;
import com.sinochem.crude.trade.shiprefueling.enums.BusinessType;
import com.sinochem.crude.trade.shiprefueling.enums.DeliveryModeEnums;
import com.sinochem.crude.trade.shiprefueling.enums.OilCrudeTypeOfShippingEnums;
import com.sinochem.crude.trade.shiprefueling.enums.TypeOfShippingEnums;
import com.sinochem.crude.trade.shiprefueling.model.query.QueryRIgnition;
import com.sinochem.crude.trade.shiprefueling.model.vo.ChmentsVO;
import com.sinochem.crude.trade.shiprefueling.model.vo.QueryRIgnitionVO;
import com.sinochem.crude.trade.shiprefueling.service.RIgnitionService;
import com.sinochem.it.b2b.common.exception.BizException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.text.ParseException;
import java.util.*;


@Service
public class RIgnitionServiceImpl implements RIgnitionService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RIgnitionMapper rIgnitionMapper;
    @Autowired
    private GoryMapper goryMapper;
    @Autowired
	private ChmentsMapper chmentsMapper;

    @Override
	public RIgnitionMapper getMapper(){
		return rIgnitionMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(RIgnition rignition){
		 return rIgnitionMapper.insertRecord(rignition);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long orderId) throws BizException{
		if (orderId == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return rIgnitionMapper.deleteById(orderId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(RIgnition  record){
    	return rIgnitionMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(RIgnition rIgnition) throws BizException{
		if ( rIgnition.getOrderId() == null  ) {
			throw new BizException("orderId 为空，不能修改 ");
		}
		return rIgnitionMapper.updateRecordById(rIgnition);
	}
	
	/**
	 * 根据uuid-修改对象
	 */
	@Override
	public int updateRecordByUuid(RIgnition rIgnition) throws BizException {
		if ( rIgnition.getUuid() == null) {
			throw new BizException("uuid为空，不能修改 ");
		}
		return rIgnitionMapper.updateRecordByUuid(rIgnition);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return rIgnitionMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(RIgnition rIgnition){
		return rIgnitionMapper.updateRecords(rIgnition.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public RIgnition findByPrimaryKey(Long orderId){
		return  rIgnitionMapper.findByPrimaryKey(orderId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public RIgnition findByUuid(String uuid){
		return  rIgnitionMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<RIgnition> queryByEntitys(RIgnition rIgnition){

		return  rIgnitionMapper.queryByEntitys(rIgnition);
	}

	@Override
	public List<RIgnition> queryByQueryRIgnition(QueryRIgnitionVO queryRIgnitionVO){
		QueryRIgnition queryRIgnition = queryRIgnitionVO.getQueryRIgnition();
		if(queryRIgnition != null){
			return rIgnitionMapper.queryByQueryRIgnition(queryRIgnition);
		}else{
			return null;
		}
	}

	@Override
	public int uploadInvoice(List<Map<String , String>> invoiceList , long orderId , CurrentUser user) throws BizException{
		if(invoiceList == null || invoiceList.size() < 1){
			return 0;
		}

		Chments deleter = new Chments();
		deleter.setId(orderId);
		deleter.setType(BusinessType.IGNITION.getCode());
		deleter.setFileType(Constants.FILE_INVOICE_TYPE);
		deleter.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		chmentsMapper.deleteRecords(deleter);

//		chmentsMapper  name path 需要前端传入
		List<Chments> updaterList = Lists.newArrayList();
		for(Map<String , String> chments : invoiceList){
			if(StringUtil.isEmpty(chments.get("path"))){
				throw new BizException("附件path不能为空");
			}
			updaterList.add(wrapperChments(chments.get("path") , chments.get("name") ,BusinessType.IGNITION.getCode() , Constants.FILE_INVOICE_TYPE , user , orderId) );
		}
		return chmentsMapper.batchInsert(updaterList);
	}

	@Override
	public int uploadOrderFile(List<Map<String , String>> fileList , String type , String fileType
			, long orderId , CurrentUser user) throws BizException{
		int num = 0;
		// 删除附件信息
		Chments deleter = new Chments();
		deleter.setId(orderId);
		deleter.setType(type);
		deleter.setFileType(fileType);
		deleter.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		chmentsMapper.deleteRecords(deleter);

		if(CollectionUtils.isEmpty(fileList)){
			return num;
		}

		// 插入附件信息
		List<Chments> fileInsertList = Lists.newArrayList();
		for(Map<String , String> chments : fileList){
			if(StringUtil.isEmpty(chments.get("path"))){
				throw new BizException("附件path不能为空");
			}
			fileInsertList.add(wrapperChments(chments.get("path") , chments.get("name") ,type , fileType , user , orderId));
			num++;
		}

		chmentsMapper.batchInsert(fileInsertList);
		return num;
	}

	private Chments wrapperChments(String path , String name , String type , String fileType , CurrentUser user , long businessId){
		Chments chments = new Chments();
		chments.setPath(path);
		chments.setName(name);
		chments.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		chments.setType(type);
		chments.setFileType(fileType);
		chments.setCreateDate(new Date());
		chments.setUpdateDate(new Date());
		chments.setCreateUser(user.getMemberId());
		chments.setUpdateUser(user.getMemberId());
		chments.setUuid(KeyGenUtils.newUuid());
		chments.setId(businessId);
		return chments;
	}

	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return rIgnitionMapper.queryRecords(map);
	}
	

	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return rIgnitionMapper.countRecords(map);
	}


	//**************************以下方法为开发者补充*********************************/
	
	@Override
	public ResultDatas<List<Map<String, Object>>> queryIgnitionRecords(Map<String, Object> map, SimplePageInfo pageInfo) throws ParseException {
		logger.info("上游订单分页查询,查询参数:{}", JSONObject.toJSONString(map));
		this.queryTimeConvert(map);

        ResultDatas<List<Map<String, Object>>> res = new ResultDatas<>();
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), "orderTime DESC,createDate DESC");
        List<Map<String, Object>> list = rIgnitionMapper.queryIgnitionRecords(map);
        for (Map<String, Object> stringObjectMap : list) {
            HashMap query = new HashMap(5);
            query.put("orderId", stringObjectMap.get("orderId"));
            //query.put("oilClassification", map.get("oilClassification"));
            //query.put("oilVarieties", map.get("oilVarieties"));
            query.put("orderType", map.get("infoType"));
            logger.info("############queryIgnitionRecords+infoType"+ map.get("infoType"));
            List<Gory> goryList = goryMapper.findByOrder(query);
            stringObjectMap.put("goryList", goryList);
            if(null!=stringObjectMap.get("deliveryWay")&&!stringObjectMap.get("deliveryWay").equals(""))
                stringObjectMap.put("deliveryWay", DeliveryModeEnums.getByCode(stringObjectMap.get("deliveryWay").toString()).getZhName()  );
            if(null!=stringObjectMap.get("transportWay")&&!stringObjectMap.get("transportWay").equals(""))
            {
                if(null!=map.get("infoType")&&!map.get("infoType").equals("9"))
                    stringObjectMap.put("transportWay", TypeOfShippingEnums.getByCode(stringObjectMap.get("transportWay").toString()).getZhName()  );
                else if(null!=map.get("infoType")&&map.get("infoType").equals("9"))
                    stringObjectMap.put("transportWay", OilCrudeTypeOfShippingEnums.getByCode(stringObjectMap.get("transportWay").toString()).getZhName()  );


            }


        }

        Page<Map<String, Object>> page = (Page<Map<String, Object>>) list;
        res.setDatas(page);
        res.setPageNum(page.getPageNum());
        res.setPageSize(page.getPageSize());
        res.setTotal(page.getTotal());
        res.setPageCount(page.getPages());
        return res;
	}

	/**
	 * 买家统计卖家的订单数
	 * @param map
	 * @param pageInfo
	 * @return
	 */
    @Override
    public ResultDatas<List<Map<String, Object>>> querySellIgnitionGroupByCompany(Map<String, Object> map, SimplePageInfo pageInfo) {
    	logger.info("买家按交易对手展示,查询参数:{};分页参数:{}", JSONObject.toJSONString(map), JSONObject.toJSONString(pageInfo));
		this.queryTimeConvert(map);
        ResultDatas<List<Map<String, Object>>> res = new ResultDatas<>();
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
        // 分页统计买家订单
        Page<Map<String, Object>> page = (Page<Map<String, Object>>) rIgnitionMapper.querySellCompanyOrder(map);
        res.setDatas(page);
        res.setPageNum(page.getPageNum());
        res.setPageSize(page.getPageSize());
        res.setTotal(page.getTotal());
        res.setPageCount(page.getPages());
        return res;
    }

    /**
	 * 卖家统计买家的订单数
	 * @param map
	 * @param pageInfo
	 * @return
	 */
    @Override
    public ResultDatas<List<Map<String, Object>>> queryBuyIgnitionGroupByCompany(Map<String, Object> map, SimplePageInfo pageInfo) {
		logger.info("卖家按交易对手展示,查询参数:{};分页参数:{}", JSONObject.toJSONString(map), JSONObject.toJSONString(pageInfo));
		this.queryTimeConvert(map);
        ResultDatas<List<Map<String, Object>>> res = new ResultDatas<>();
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
        // 分页统计卖家订单
        Page<Map<String, Object>> page = (Page<Map<String, Object>>) rIgnitionMapper.queryBuyCompanyOrder(map);
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
		List<Map<String, Object>> list = rIgnitionMapper.queryIgnitionRecords(map);
        for (Map<String, Object> stringObjectMap : list) {
            List<Gory> goryList = goryMapper.findByOrder(map);
            stringObjectMap.put("goryList", goryList);
        }
        return list;
    }

	@Override
	public int updateRecordsAliveFlag(Long orderId, String flag) {
		return rIgnitionMapper.updateRecordsAliveFlag(orderId, flag);
	}


	@Override
	public String omImportRIgnition(List<RIgnitionCombine> list, CurrentUser user,String oilType){
		String mess = "";
		Integer all = 0;
		Integer suceessFn = 0;
		Integer falseFn = 0;
		all = list.size();
		for (RIgnitionCombine combine : list) {
			try {
				RIgnition rIgnition = combine.getrIgnition();
				Long buyCompanyId = rIgnition.getBuyCompanyId();
				Long sellCompanyId = rIgnition.getSellCompanyId();
				rIgnition.setUuid(KeyGenUtils.newUuid());
				rIgnition.setLangVer(Constants.LANG_VER);
				rIgnition.setAliveFlag(Constants.ALIEVE_FLAG_YES);
				rIgnition.setCreateDate(new Date());
				rIgnition.setUpdateDate(new Date());
				rIgnition.setUpdateUser(user.getMemberId());
				rIgnition.setCreateUser(user.getMemberId());
				rIgnition.setVersion("1");
                String uuid = KeyGenUtils.newUuid();
                rIgnition.setUuid(uuid);
				this.insertRecord(rIgnition);
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
						if(null!=oilType&&!oilType.equals("")){
							gory.setOrderType(oilType);
						}
						gory.setOrderUuid(uuid);
						gory.setOrderId(rIgnition.getOrderId());
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
	public String buyImportRIgnition(List<RIgnitionCombine> list, CurrentUser user,String oilType){
		String mess = "";
		Integer all = 0;
		Integer suceessFn = 0;
		Integer falseFn = 0;
		all = list.size();
		for (RIgnitionCombine combine : list) {
			try {
				RIgnition rIgnition = combine.getrIgnition();
				Long buyCompanyId = rIgnition.getBuyCompanyId();
				Long sellCompanyId = rIgnition.getSellCompanyId();
                logger.info("#######buyCompanyId######"+buyCompanyId);
                logger.info("#######getEpMemberId######"+user.getEpMemberId());
                if(buyCompanyId != null && buyCompanyId.equals(user.getEpMemberId())){

                    rIgnition.setUuid(KeyGenUtils.newUuid());
					rIgnition.setLangVer(Constants.LANG_VER);
					rIgnition.setAliveFlag(Constants.ALIEVE_FLAG_YES);
					rIgnition.setCreateDate(new Date());
					rIgnition.setUpdateDate(new Date());
					rIgnition.setUpdateUser(user.getMemberId());
					rIgnition.setCreateUser(user.getMemberId());
					rIgnition.setVersion("1");
                    String uuid = KeyGenUtils.newUuid();
                    rIgnition.setUuid(uuid);
                    this.insertRecord(rIgnition);
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
                            if(null!=oilType&&!oilType.equals("")){
								gory.setOrderType(oilType);
							}
							gory.setOrderUuid(uuid);
							gory.setOrderId(rIgnition.getOrderId());
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
	public String sellerImportRIgnition(List<RIgnitionCombine> list, CurrentUser user,String oilType){
		String mess = "";
		Integer all = 0;
		Integer suceessFn = 0;
		Integer falseFn = 0;
		all = list.size();
		for (RIgnitionCombine combine : list) {
			try {
				RIgnition rIgnition = combine.getrIgnition();
				Long buyCompanyId = rIgnition.getBuyCompanyId();
				Long sellCompanyId = rIgnition.getSellCompanyId();
                logger.info("#######sellCompanyId######"+sellCompanyId);
                logger.info("#######getEpMemberId######"+user.getEpMemberId());
				if(sellCompanyId != null && sellCompanyId.equals(user.getEpMemberId()) ){
					rIgnition.setUuid(KeyGenUtils.newUuid());
					rIgnition.setLangVer(Constants.LANG_VER);
					rIgnition.setAliveFlag(Constants.ALIEVE_FLAG_YES);
					rIgnition.setCreateDate(new Date());
					rIgnition.setUpdateDate(new Date());
					rIgnition.setUpdateUser(user.getMemberId());
					rIgnition.setCreateUser(user.getMemberId());
					rIgnition.setVersion("1");
                    String uuid = KeyGenUtils.newUuid();
                    rIgnition.setUuid(uuid);
					this.insertRecord(rIgnition);
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
                            if(StringUtils.isNotBlank(oilType)) {
								gory.setOrderType(oilType);
							}
							gory.setOrderUuid(uuid);
							gory.setOrderId(rIgnition.getOrderId());
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

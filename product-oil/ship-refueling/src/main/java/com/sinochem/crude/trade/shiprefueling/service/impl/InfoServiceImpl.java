package com.sinochem.crude.trade.shiprefueling.service.impl;

import com.eyeieye.melody.util.DateUtil;
import com.eyeieye.melody.util.StringUtil;
import com.eyeieye.melody.web.url.URLBroker;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseDetailVO;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shiprefueling.constants.Constants;
import com.sinochem.crude.trade.shiprefueling.controller.common.KeyGenUtils;
import com.sinochem.crude.trade.shiprefueling.controller.common.ResultDatas;
import com.sinochem.crude.trade.shiprefueling.controller.common.SimplePageInfo;
import com.sinochem.crude.trade.shiprefueling.dao.InfoMapper;
import com.sinochem.crude.trade.shiprefueling.dao.IryQuotationMapper;
import com.sinochem.crude.trade.shiprefueling.domain.po.Chments;
import com.sinochem.crude.trade.shiprefueling.domain.po.Info;
import com.sinochem.crude.trade.shiprefueling.domain.po.IryQuotation;
import com.sinochem.crude.trade.shiprefueling.enums.*;
import com.sinochem.crude.trade.shiprefueling.model.query.InfoQuery;
import com.sinochem.crude.trade.shiprefueling.model.query.IryQuotationQuery;
import com.sinochem.crude.trade.shiprefueling.model.vo.ChmentsVO;
import com.sinochem.crude.trade.shiprefueling.model.vo.InfoVO;
import com.sinochem.crude.trade.shiprefueling.service.ChmentsService;
import com.sinochem.crude.trade.shiprefueling.service.InfoService;
import com.sinochem.crude.trade.shiprefueling.service.IryQuotationService;
import com.sinochem.crude.trade.shiprefueling.service.OssService;
import com.sinochem.crude.trade.shiprefueling.utils.DictoryUtils;
import com.sinochem.it.b2b.common.exception.BizException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.enums.EnumUtils;
import org.apache.http.client.CredentialsProvider;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.*;


@Service
public class InfoServiceImpl implements InfoService {
	@Autowired
	private InfoMapper infoMapper;
	@Autowired
	private IryQuotationMapper iryQuotationMapper;
	@Autowired
	private IryQuotationService iryQuotationService;
	@Autowired
	private OssService ossService;
	@Autowired
	private ChmentsService chmentsService;
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private URLBroker docServer;
	public InfoMapper getMapper(){
		return infoMapper;
	}

	/**
	 * 新增
	 */
	@Override
	public int insertRecord(Info info){
		return infoMapper.insertRecord(info);
	}

	/**
	 * 新增
	 */
	@Override
	public int insertRecord(Info info , List<ChmentsVO> chmentsVOList , CurrentUser user){
		//企业信息查询并插入
		info.setCreateDate(new Date());
		info.setUpdateDate(new Date());
		info.setLangVer(Constants.LANG_VER);
		info.setUuid(KeyGenUtils.newUuid());
		info.setAliveFlag(Constants.SAVE_FLAG);
		info.setUpdateUser(user.getMemberId());
		info.setCreateUser(user.getMemberId());
		info.setStatus(Info.STATUS_PUBLISHED);
		int result = infoMapper.insertRecord(info);
		//销售信息插入
		if(chmentsVOList == null || chmentsVOList.size() < 1){
			return result;
		}
		//附件插入
		for(ChmentsVO chmentsVO : chmentsVOList){
			String url = chmentsVO.getPath();
			Long id = chmentsVO.getId();
			//如果不存在path则不插入
			if(StringUtils.isBlank(url)) continue;
			Chments chments = new Chments();
			chments.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			chments.setId(info.getInfoId());
			chments.setCreateDate(new Date());
			chments.setCreateUser(user.getMemberId());
			chments.setName(url.substring(url.lastIndexOf("/")+1 , url.lastIndexOf(".")));
			chments.setPath(url);
			chments.setUuid(KeyGenUtils.newUuid());
			chments.setFileType(Constants.FILE_PRODUCT_TYPE);
			chments.setType(BusinessType.SALE.getCode());
			chmentsService.insertRecord(chments);
		}
		return result;
	}

	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long infoId) throws BizException{
		if (infoId == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return infoMapper.deleteById(infoId);
	}

	/**
	 * 根据条件-物理删除对象执行delete语句, 慎用！！！
	 */
	@Override
	public int deleteRecords(Info  record){
		return infoMapper.deleteRecords(record);
	}

	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(Info info) throws BizException{
		if ( info.getInfoId() == null  ) {
			throw new BizException("infoId 为空，不能修改 ");
		}
		return infoMapper.updateRecordById(info);
	}

	/**
	 * 根据uuid-修改对象
	 */
	@Override
	public int updateRecordByUuid(Info info) throws BizException {
		if ( info.getUuid() == null) {
			throw new BizException("uuid为空，不能修改 ");
		}
		return infoMapper.updateRecordByUuid(info);
	}

	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return infoMapper.updateRecords(map);
	}

	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Info info){
		return infoMapper.updateRecords(info.toMap());
	}

	/**
	 * 根据主键-查询对象
	 */
	@Override
	public Info findByPrimaryKey(Long infoId){
		return  infoMapper.findByPrimaryKey(infoId);
	}

	/**
	 * 根据uuid查询对象
	 */
	@Override
	public Info findByUuid(String uuid){
		return  infoMapper.findByUuid(uuid);
	}

	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<Info> queryByEntitys(Info info){
		return  infoMapper.queryByEntitys(info);
	}

	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return infoMapper.queryRecords(map);
	}


	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return infoMapper.countRecords(map);
	}


	//**************************以下方法为开发者补充*********************************/
	@Override
	public Page<Map<String, Object>> queryByUserId(InfoQuery infoQuery, SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), "S.RELEASED_DATE DESC, S.CREATE_DATE DESC" );
		Page<Map<String , Object>> page = (Page<Map<String, Object>>) infoMapper.queryByUserId(infoQuery);

        //ADD_提货方式_wdh_20180801_Start
        Map<String , String> deliveryModeMap = new HashMap<String,String>();
        deliveryModeMap = DeliveryModeEnums.toMap();
        String deliveryModeStr = "";
        //ADD_提货方式_wdh_20180801_End

		for(Map<String , Object> map : page){
			//运输方式   原料油为：  成品油：OilCrudeTypeOfShippingEnums
			if(map.get("DELIVERY_WAY") != null){
				if(Constants.INFO_TYPE_CRUDE.equals(infoQuery.getInfoType())){
					for(OilCrudeTypeOfShippingEnums value : OilCrudeTypeOfShippingEnums.values()){
						if(value.getCode().equals(String.valueOf(map.get("DELIVERY_WAY")))){
							map.put("DELIVERY_WAY", value.getZhName());
							break;
						}
					}
				}else {
					for(TypeOfShippingEnums value : TypeOfShippingEnums.values()){
						if(value.getCode().equals(String.valueOf(map.get("DELIVERY_WAY")))){
							map.put("DELIVERY_WAY", value.getZhName());
							break;
						}
					}
				}

			}
			//油品类别oilClassification
			if(map.get("OIL_CLASSIFICATION") != null){
				for(OilTypeEnums value : OilTypeEnums.values()){
					if(value.getCode().equals(String.valueOf(map.get("OIL_CLASSIFICATION")))){
						map.put("OIL_CLASSIFICATION", value.getZhName());
						break;
					}
				}
			}
			//规格
			if(map.get("OIL_VARIETIES") != null){
				for(FuelOilEnums value : FuelOilEnums.values()){
					if(value.getCode().equals(String.valueOf(map.get("OIL_VARIETIES")))){
						map.put("OIL_VARIETIES", value.getZhName());
						break;
					}
				}
			}

			//ADD_运输方式_wdh_20180801_Start
            deliveryModeStr = "" + map.get("TRANSPORT_WAY");
            if(deliveryModeMap.containsKey(deliveryModeStr)){
                map.put("TRANSPORT_WAY", deliveryModeMap.get(deliveryModeStr));
            }
            //ADD_运输方式wdh_20180801_End

			//状态
			if(map.get("STATUS") != null){
				for(SaleStatusEnums value : SaleStatusEnums.values()){
					if(value.getCode().equals(String.valueOf(map.get("STATUS")))){
						map.put("STATUS", value.getZhName());
						break;
					}
				}
			}

			//LOGO
			String logo = "";
			Long epMemberId = (Long)map.get("EP_MEMBER_ID");
			if(epMemberId != null){
				EnterpriseVo enterpriseVo = enterpriseService.getByMemberId(epMemberId);
				if(enterpriseVo != null && StringUtil.isNotBlank(enterpriseVo.getEpLogo())){
					logo = docServer.get("/img/"+enterpriseVo.getEpLogo() +".img_56_56.img").toString();
				}
			}
			map.put("epLogo" , logo);

			//发布日期字符串
			if(map.get("RELEASED_DATE") != null){
				Timestamp timeStamp = (Timestamp)map.get("RELEASED_DATE");
				String releasedDateStr = DateUtil.convertDateToString(new Date(timeStamp.getTime()));
				map.put("RELEASED_DATE_STR" , releasedDateStr);
			}

		}
		return  page;
	}

	/**
	 * 根据uuid修改状态
	 * @param info
	 * @return
	 */
	@Override
	public int updateByUuidStatus(Info info) {
		int code= infoMapper.updateByUuidStatus(info);
		return code;
	}

	/**
	 * 根据uuid删除销售订单
	 * @param uuid
	 * @return
	 */
	@Override
	public int deleteByuuid(String uuid) {
		int code= infoMapper.deleteByuuid(uuid);
		return code;
	}
	@Override
	public List<InfoVO> selectNewSellInfoList(Integer size) {
		PageHelper.startPage(1,size);
		List<Info> list = infoMapper.selectNewSellInfoList();
		List<InfoVO> voList = new ArrayList<>(list.size());
		for (Info info:list) {
			InfoVO infovo = new InfoVO();
			infovo = infovo.toInfovo(info);
			voList.add(infovo);
		}
		return voList;
	}

	/**
	 * 根据销售单 查询询价
	 * @param iryQuotationQuery
	 * @param pageInfo
	 * @return
	 */
	@Override
	public Page<Map<String, Object>> findByidInquiry(IryQuotationQuery iryQuotationQuery, SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
		//List<IryQuotation> iryQuotationList = iryQuotationMapper.findByInfoSelectAll(iryQuotationQuery);

		return (Page<Map<String, Object>>)iryQuotationMapper.findByInfoSelectAll(iryQuotationQuery);
	}

	/**
	 * 根据询价表修改询价状态
	 * @param iryQuotation
	 * @return
	 */
	@Override
	public int updateByuuidInquiry(IryQuotation iryQuotation) {
		int code = iryQuotationMapper.updateByuuidStatus(iryQuotation);
		return code;
	}


	/**
	 * 根据uuid逻辑删除信息
	 * @param uuid
	 * @return
	 */
	public int deleteRecordByUuid(String uuid){
		return infoMapper.deleteRecordByUuid(uuid);
	}

	/**
	 * 逻辑删除销售详情和询价信息
	 * @param uuid
	 */
	public void deleteInfoAndIqByUUid(String uuid)throws BizException{
		Info info = infoMapper.findByUuid(uuid);
		if(info == null){
			return;
		}
		//删除销售详情
		Info upInfo = new Info();
		upInfo.setAliveFlag(Constants.DELE_FLAG);
		upInfo.setUuid(uuid);
		infoMapper.updateRecordByUuid(upInfo);
		//删除报价详情
		iryQuotationService.updateRecordByNeedUUid(uuid , Constants.QUOTATION_TYPE_INQUIRY);
		//删除图片信息
		Chments chments = new Chments();
		chments.setId(info.getInfoId());
		chments.setType(BusinessType.SALE.getCode());
		chments.setFileType(Constants.FILE_PRODUCT_TYPE);
		chments.setAliveFlag(Constants.DELE_FLAG);
		chmentsService.updateAvFlag(chments);
	}


	/**
	 * 根据UUID查询销售信息，企业信息，附件信息
	 * @param uuid
	 * @return
	 */
	public Map<String , Object> getInfoEpDetail(String uuid) {
		//返回结果
		Map<String, Object> result = Maps.newHashMap();

		//销售信息基本信息查询以及vo转化
		Info info = findByUuid(uuid);
		InfoVO infoVO = new InfoVO();
		infoVO = infoVO.toInfovo(info);

		//查询附件信息
		Map<String, Object> epMap = Maps.newHashMap();
		if (info != null && info.getInfoId() > 0) {
			Chments queryCh = new Chments();
			queryCh.setId(info.getInfoId());
			queryCh.setType(BusinessType.SALE.getCode());
			queryCh.setFileType(Constants.FILE_PRODUCT_TYPE);
			List<ChmentsVO> chmentsVOList = chmentsService.findChmentsVoByChments(queryCh);

			infoVO.setChmentsVOList(chmentsVOList);
			//企业信息设置
			String epName = null, epFullName = null, epLogo = null, epType = null, epScope = null, description = null, resCre = "";
			if (info.getEpMemberId() != null) {
				EnterpriseVo enterpriseVo = enterpriseService.getByMemberId(info.getEpMemberId());
				String[] creEnums = DictoryUtils.getCredentials(enterpriseVo.getCredentialsCode());
				if (creEnums != null && creEnums.length > 0) {
					for (String e : creEnums) {
						if (StringUtil.isNotBlank(e)) {
							resCre = resCre + e + " ";
						}
					}
				}
				epName = enterpriseVo.getName();
				epFullName = enterpriseVo.getFullName();
				epLogo = enterpriseVo.getEpLogo();
				epType = enterpriseVo.getEptype() == null ? "" : enterpriseVo.getEptype().intValue() == 1 ? "境外" : "境内";
				epScope = enterpriseVo.getScope();
				description = enterpriseVo.getDescription();
			}
			epMap.put("epName", epName); //企业名称
			epMap.put("epFullName", epFullName);//企业全称
			epMap.put("epLogo", epLogo);//企业logo图片ID
			epMap.put("epType", epType);//类型,0=境内,1=境外
			epMap.put("epScope", epScope);//经营范围
			epMap.put("description", description);//描述
			epMap.put("credentials", resCre);
		}
		result.put("infoVO", infoVO);
		result.put("enterprise", epMap);
		return result;
	}

    //ADD_ 船油销售订单查询首页展示_wdh_20180731_Start
	//info_type
	private final static String INFO_TYPE = "info_type";
	//datas
	private final static String DATAS = "datas";
	//船燃贸易
	private final static String MARINE_TRADE = "1";
	//船燃贸易(内贸)
	private final static String INTERNAL_TRADE  = "2";
	//船燃贸易(保税)
	private final static String BONDED = "3";

    /**
     * 船油销售订单查询
     * @param pageInfo
     * @return
     */
	@Override
	public Page<Map<String,Object>>  queryList(SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), "RELEASED_DATE DESC" );
        //查询信息类型为船燃贸易，船燃贸易(内贸)，船燃贸易(保税)中各自类型得三个不同企业的最新一条信息
		Page<Map<String , Object>> page = (Page<Map<String, Object>>) infoMapper.queryList();
		//
		Page<Map<String, Object>> tmp = new Page<Map<String, Object>>();

		//返回用
		Page<Map<String,Object>> res = new Page<Map<String,Object>>();

		//船燃贸易 返回用
		Map<String, Object> tmpMapMarine = new HashMap<String, Object>();
		tmpMapMarine.put(INFO_TYPE,MARINE_TRADE);
		tmpMapMarine.put(DATAS,null);

		//船燃贸易(内贸) 返回用
		Map<String, Object> tmpMapInternal = new HashMap<String, Object>();
		tmpMapInternal.put(INFO_TYPE,INTERNAL_TRADE);
		tmpMapInternal.put(DATAS,null);

		//船燃贸易(保税) 返回用
		Map<String, Object> tmpMapBonded = new HashMap<String, Object>();
		tmpMapBonded.put(INFO_TYPE,BONDED);
		tmpMapBonded.put(DATAS,null);

		//运输方式
		Map<String,String> typeOfShippingEnumsMap = new HashMap<String ,String>();
		for(TypeOfShippingEnums value : TypeOfShippingEnums.values()){
			typeOfShippingEnumsMap.put(value.getCode(),value.getZhName());
		}

		//油品类别oilClassification
		Map<String,String> oilTypeEnumsMap = new HashMap<String ,String>();
		for(OilTypeEnums value : OilTypeEnums.values()){
			oilTypeEnumsMap.put(value.getCode(),value.getZhName());
		}

		//规格
		Map<String,String> FuelOilEnumsMap = new HashMap<String ,String>();
		for(FuelOilEnums value : FuelOilEnums.values()){
			FuelOilEnumsMap.put(value.getCode(),value.getZhName());
		}
		//提货方式
		Map<String,String> deliveryModeEnums = DeliveryModeEnums.toMap();

		for(Map<String , Object> map : page){
			//运输方式
			if(typeOfShippingEnumsMap.containsKey(""+map.get("DELIVERY_WAY"))){
				map.put("DELIVERY_WAY", typeOfShippingEnumsMap.get(""+map.get("DELIVERY_WAY")));
			}
			/*//油品类别oilClassification
			if(oilTypeEnumsMap.containsKey(""+map.get("OIL_CLASSIFICATION"))){
				map.put("OIL_CLASSIFICATION", oilTypeEnumsMap.get(""+map.get("OIL_CLASSIFICATION")));
			}
			//规格
			if(FuelOilEnumsMap.containsKey(""+map.get("OIL_VARIETIES"))){
				map.put("OIL_VARIETIES", FuelOilEnumsMap.get(""+map.get("OIL_VARIETIES")));
			}*/
			//提货方式
			if(deliveryModeEnums.containsKey(""+map.get("TRANSPORT_WAY"))){
				map.put("TRANSPORT_WAY", deliveryModeEnums.get(""+map.get("TRANSPORT_WAY")));
			}
			//LOGO
			String logo = "";
			Long epMemberId = (Long)map.get("EP_MEMBER_ID");
			if(epMemberId != null){
				EnterpriseVo enterpriseVo = enterpriseService.getByMemberId(epMemberId);
				if(enterpriseVo != null && StringUtil.isNotBlank(enterpriseVo.getEpLogo())){
					logo = docServer.get("/img/"+enterpriseVo.getEpLogo() +".img_56_56.img").toString();
				}
			}
			map.put("epLogo" , logo);
			//发布日期字符串
			if(map.get("RELEASED_DATE") != null){
				Timestamp timeStamp = (Timestamp)map.get("RELEASED_DATE");
				String releasedDateStr = DateUtil.convertDateToString(new Date(timeStamp.getTime()));
				map.put("RELEASED_DATE_STR" , releasedDateStr);
			}

			//船燃贸易
			if(MARINE_TRADE.equals( ""+ map.get("INFO_TYPE"))){
				tmp = tmpMapMarine.get(DATAS)==null? new Page<Map<String, Object>>(): (Page<Map<String, Object>>) tmpMapMarine.get(DATAS);
				tmp.add(map);
				tmpMapMarine.put(DATAS,tmp);
				//船燃贸易(内贸)
			}else if(INTERNAL_TRADE.equals( ""+ map.get("INFO_TYPE"))){
				tmp = tmpMapInternal.get(DATAS)==null?new Page<Map<String, Object>>(): (Page<Map<String, Object>>) tmpMapInternal.get(DATAS);
				tmp.add(map);
				tmpMapInternal.put(DATAS,tmp);
				//船燃贸易(保税)
			}else if(BONDED.equals( ""+ map.get("INFO_TYPE"))){
				tmp = tmpMapBonded.get(DATAS)==null?new Page<Map<String, Object>>(): (Page<Map<String, Object>>) tmpMapBonded.get(DATAS);
				tmp.add(map);
				tmpMapBonded.put(DATAS,tmp);
			}

		}

		//设置返回值
		res.add(tmpMapMarine);
		res.add(tmpMapInternal);
		res.add(tmpMapBonded);

		return  res;
	}
    //ADD_ 船油销售订单查询_wdh_20180731_End

}

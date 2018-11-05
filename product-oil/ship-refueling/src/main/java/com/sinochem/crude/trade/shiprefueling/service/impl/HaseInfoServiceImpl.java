package com.sinochem.crude.trade.shiprefueling.service.impl;

import com.eyeieye.melody.util.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.shiprefueling.constants.Constants;
import com.sinochem.crude.trade.shiprefueling.controller.common.BeanConvertUtils;
import com.sinochem.crude.trade.shiprefueling.controller.common.KeyGenUtils;
import com.sinochem.crude.trade.shiprefueling.controller.common.SimplePageInfo;
import com.sinochem.crude.trade.shiprefueling.dao.HaseInfoMapper;
import com.sinochem.crude.trade.shiprefueling.domain.po.Chments;
import com.sinochem.crude.trade.shiprefueling.domain.po.HaseInfo;
import com.sinochem.crude.trade.shiprefueling.domain.po.Info;
import com.sinochem.crude.trade.shiprefueling.domain.po.IryQuotation;
import com.sinochem.crude.trade.shiprefueling.enums.*;
import com.sinochem.crude.trade.shiprefueling.model.query.HaseInfoQuery;
import com.sinochem.crude.trade.shiprefueling.model.vo.ChmentsVO;
import com.sinochem.crude.trade.shiprefueling.model.vo.HaseInfoVO;
import com.sinochem.crude.trade.shiprefueling.model.vo.ShipEnterpriseVo;
import com.sinochem.crude.trade.shiprefueling.service.ChmentsService;
import com.sinochem.crude.trade.shiprefueling.service.HaseInfoService;
import com.sinochem.crude.trade.shiprefueling.service.IryQuotationService;
import com.sinochem.crude.trade.shiprefueling.utils.DictoryUtils;
import com.sinochem.it.b2b.common.exception.BizException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class HaseInfoServiceImpl implements HaseInfoService {
	@Autowired
	private HaseInfoMapper haseInfoMapper;
	
	@Autowired
	private ChmentsService chmentsService;

	@Autowired
	private IryQuotationService iryQuotationService;

	@Autowired
	private EnterpriseService enterpriseService;
	
	public HaseInfoMapper getMapper(){
		return haseInfoMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(HaseInfo haseinfo){
		 return haseInfoMapper.insertRecord(haseinfo);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long infoId) throws BizException{
		if (infoId == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return haseInfoMapper.deleteById(infoId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(HaseInfo  record){
    	return haseInfoMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(HaseInfo haseInfo) throws BizException{
		if ( haseInfo.getInfoId() == null  ) {
			throw new BizException("infoId 为空，不能修改 ");
		}
		return haseInfoMapper.updateRecordById(haseInfo);
	}
	
	/**
	 * 根据uuid-修改对象
	 */
	@Override
	public int updateRecordByUuid(HaseInfo haseInfo) throws BizException {
		if ( haseInfo.getUuid() == null) {
			throw new BizException("uuid为空，不能修改 ");
		}
		return haseInfoMapper.updateRecordByUuid(haseInfo);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return haseInfoMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(HaseInfo haseInfo){
		return haseInfoMapper.updateRecords(haseInfo.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public HaseInfo findByPrimaryKey(Long infoId){
		return  haseInfoMapper.findByPrimaryKey(infoId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public HaseInfo findByUuid(String uuid){
		return  haseInfoMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<HaseInfo> queryByEntitys(HaseInfo haseInfo){
		return  haseInfoMapper.queryByEntitys(haseInfo);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return haseInfoMapper.queryRecords(map);
	}
	

	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return haseInfoMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	
	/**
	 * 新增采购信息
	 */
	@Transactional
	public int insertHaseInfoRecord(HaseInfoVO vo/*,CurrentUser user*/){
		String uuid = KeyGenUtils.newUuid();
		vo.setUuid(uuid);
		vo.setCreateDate(new Date());
		vo.setUpdateDate(new Date());
		vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		vo.setStatus(HaseInfo.STATUS_PUBLISHED);
		int i = haseInfoMapper.insertRecord(vo);
		HaseInfo info =haseInfoMapper.findByUuid(uuid);
		if(i==1){
			 List<ChmentsVO> list = vo.getChments();
			if(list!=null&&list.size()>0){
				for(ChmentsVO c:list){
					try {
						String url = c.getPath();
						if (StringUtil.isBlank(url)) continue;
						c.setFileType(Constants.FILE_PRODUCT_TYPE);
						c.setType(BusinessType.PURCHASE.getCode());
						c.setId(info.getInfoId());
						c.setName(url.substring(url.lastIndexOf("/")+1 , url.lastIndexOf(".")));
						c.setCreateUser(vo.getEpMemberId());
						chmentsService.insertAttachmentsRecord(c);
					} catch (BizException e) {
						e.printStackTrace();
					}
				}
			}
		}
		 return i;
	}

	 /**
	  * 修改采购信息
	  */
	@Transactional
	public int updateHaseInfoRecordByUuid(HaseInfoVO vo)
			throws BizException {
		if ( vo.getUuid() == null) {
			throw new BizException("uuid为空，不能修改 ");
		}
		String uuid = vo.getUuid();
		vo.setUpdateDate(new Date());
		vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		
		int i = haseInfoMapper.updateRecordByUuid(vo);

		if(i==1){
			 List<ChmentsVO> list = vo.getChments();
			if(list!=null&&list.size()>0){
				for(ChmentsVO c:list){
					try {
						//附件上传逻辑
						//id , path 都不为空则不进行操作
						//id为空,path不为空则 新增
						//id不为空,path为空则 删除
						String path =  c.getPath();
						Long id = c.getId();
						if(StringUtils.isNotBlank(path) && id != null && id > 0){
							continue;
						}
						if(StringUtils.isNotBlank(path)){
							c.setAliveFlag(Constants.ALIEVE_FLAG_YES);
							c.setId(vo.getInfoId());
							c.setCreateDate(new Date());
							c.setCreateUser(vo.getCreateUser());
							c.setName(path.substring(path.lastIndexOf("/")+1 , path.lastIndexOf(".")));
							c.setPath(path);
							c.setUuid(KeyGenUtils.newUuid());
							c.setVersion("1");
							c.setCreateUser(vo.getCreateUser());
							c.setFileType(Constants.FILE_PRODUCT_TYPE);
							c.setType(BusinessType.PURCHASE.getCode());
							chmentsService.insertRecord(c);
						}else{
							chmentsService.deleteById(c.getAttachmentId());
						}


						if("".equals(c.getUuid())) chmentsService.insertAttachmentsRecord(c);
						else chmentsService.updateAttachmentsRecordByUuid(c);
						
					} catch (BizException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		return i;
	}

	/**
	 * 删除采购信息
	 */
	public int deleteRecordByUuid(String uuid) throws BizException {
		HaseInfo haseInfo = haseInfoMapper.findByUuid(uuid);
		if(haseInfo == null ){
			return 0;
		}
		//删除采购信息
		iryQuotationService.updateRecordByNeedUUid(uuid , Constants.QUOTATION_TYPE_OFFER);
		//删除图片信息
		Chments chments = new Chments();
		chments.setId(haseInfo.getInfoId());
		chments.setType(BusinessType.PURCHASE.getCode());
		chments.setFileType(Constants.FILE_PRODUCT_TYPE);
		chments.setAliveFlag(Constants.DELE_FLAG);
		chmentsService.updateAvFlag(chments);

		return haseInfoMapper.deleteRecordByUuid(uuid);
	}

	/*
	 * 分页列表
	 */
	@Override
	public Page<Map<String, Object>> queryHaseInfoList(HaseInfoVO haseInfo,
			SimplePageInfo pageInfo) {
		Map<String , Object> map = BeanConvertUtils.beanToMap(haseInfo);
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), "releasedDate desc,createDate desc");
		String issueTimeStart = (String) map.get("issueTimeStart");
		String issueTimeEnd = (String) map.get("issueTimeEnd");
		String infoType = (String) map.get("infoType");
        String status = (String) map.get("status");
		if(StringUtils.isNotBlank(issueTimeStart)){
			map.put("issueTimeStart",issueTimeStart + " 00:00:00");
		}
		if(StringUtil.isNotBlank(issueTimeEnd)){
			map.put("issueTimeEnd",issueTimeEnd + " 23:59:59");
		}
		if(StringUtils.isNotBlank(infoType)){
			List<String> infoTypeList = Lists.newArrayList();
			infoTypeList.addAll(Arrays.asList(infoType.split(",")));
			map.put("infoTypeList" , infoTypeList);
		}
        if(StringUtils.isNotBlank(status)){
            List<String> statusList = Lists.newArrayList();
            statusList.addAll(Arrays.asList(status.split(",")));
            map.put("statusList" , statusList);
        }
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) haseInfoMapper.queryHaseInfoRecords(map);

		//Add_增加提货方式和信息类型_wdh_20180801_Start
		//提货方式
		Map<String,String> deliveryModeEnums = DeliveryModeEnums.toMap();
        //信息类型
        Map<String,String> infoTypeWEnums = SaleInfoTypeEnums.toMap();
		//Add_增加提货方式和信息类型_wdh_20180801_End

		if(page != null && page.size() > 0){
			for(Map<String , Object> hiMap : page){
				//运输方式
				if(hiMap.get("deliveryWay") != null){
                    if(null!=infoType&&!infoType.equals("9")) {
                        for (TypeOfShippingEnums value : TypeOfShippingEnums.values()) {
                            if (value.getCode().equals(String.valueOf(hiMap.get("deliveryWay")))) {
                                hiMap.put("deliveryWay", value.getZhName());
                                break;
                            }
                        }
                    }
                    if(null!=infoType&&infoType.equals("9")) {
                        for (OilCrudeTypeOfShippingEnums value : OilCrudeTypeOfShippingEnums.values()) {
                            if (value.getCode().equals(String.valueOf(hiMap.get("deliveryWay")))) {
                                hiMap.put("deliveryWay", value.getZhName());
                                break;
                            }
                        }
                    }

				}

				//状态
				if(hiMap.get("status") != null){
					for(PurchaseStatusEnums value : PurchaseStatusEnums.values()){
						if(value.getCode().equals(String.valueOf(hiMap.get("status")))){
							hiMap.put("status", value.getZhName());
							break;
						}
					}
				}

                //Add_增加提货方式和信息类型_wdh_20180801_Start
				//提货方式
				if(deliveryModeEnums.containsKey(""+hiMap.get("transportWay"))){
				    hiMap.put("transportWay", deliveryModeEnums.get(""+hiMap.get("transportWay")));
				}
				//信息类型
                if(infoTypeWEnums.containsKey(""+hiMap.get("infoType"))){
					hiMap.put("infoType", infoTypeWEnums.get(""+hiMap.get("infoType")));
                }
                //Add_增加提货方式和信息类型_wdh_20180801_End
			}
		}
		return page;
	}

	//region 前台查询最新的几条记录 -武刚鹏-2018年5月28日17:44:59
	/**
	 * 前台查询最新的几条记录 -武刚鹏-2018年5月28日17:44:59
	 * @param size
	 * @return
	 */
	@Override
	public List<HaseInfoVO> selectNewHashInfoList(int size) {
		PageHelper.startPage(1,size);
		List<HaseInfo> list = haseInfoMapper.selectNewHashInfoList();
		List<HaseInfoVO> voList = new ArrayList<>(list.size());
		for (HaseInfo info:list) {
			HaseInfoVO haseInfoVO = new HaseInfoVO();
			haseInfoVO.domainToVo(info);
			voList.add(haseInfoVO);
		}
		return voList;
	}

	@Override
	public PageInfo<HaseInfoVO> queryFrontHaseInfoList(HaseInfoQuery haseInfoQuery) {
		PageHelper.startPage(haseInfoQuery.getPageInfo().getPageNum(),haseInfoQuery.getPageInfo().getPageSize() ,"RELEASED_DATE DESC");
		List<HaseInfo> list = haseInfoMapper.queryByEntitysByPage(haseInfoQuery);
		PageInfo pageInfo = new PageInfo<>(list);
		List<HaseInfoVO> voList = new ArrayList<>();
		if(list != null && list.size()>0){
			for (HaseInfo info:list) {
				HaseInfoVO vo = new HaseInfoVO();
				vo.domainToVo(info);
				voList.add(vo);
			}
		}
		pageInfo.setList(voList);
		return pageInfo;
	}

	@Override
	public HaseInfoVO findVoByUuid(String uuid) {
		HaseInfoVO haseInfoVO = new HaseInfoVO();
		HaseInfo haseInfo = findByUuid(uuid);
		haseInfoVO.domainToVo(haseInfo);
		wrapperConversionVo(haseInfoVO);
		return haseInfoVO;
	}


	/**
	 * 查询数据库原始信息
	 * @param uuid
	 * @return
	 */
	@Override
	public HaseInfoVO findMetaVoByUuid(String uuid) {
		HaseInfoVO haseInfoVO = new HaseInfoVO();
		HaseInfo haseInfo = findByUuid(uuid);
		BeanUtils.copyProperties(haseInfo , haseInfoVO);
		wrapperConversionVo(haseInfoVO);
		return haseInfoVO;
	}


	private HaseInfoVO wrapperConversionVo(HaseInfoVO haseInfoVO){
		//查询公司信息
		if(haseInfoVO.getEpMemberId() != null){
			EnterpriseVo enterpriseVo = enterpriseService.getByMemberId(haseInfoVO.getEpMemberId());
			if(enterpriseVo != null && enterpriseVo.getMemberId()!=null){
				ShipEnterpriseVo shipEnterpriseVo = new ShipEnterpriseVo();
				BeanUtils.copyProperties(enterpriseVo,shipEnterpriseVo);
				String[] objs = DictoryUtils.getCredentials(enterpriseVo.getCredentialsCode());
				shipEnterpriseVo.setCredentials(objs);
				if(shipEnterpriseVo.getEptype() != null){
					if(shipEnterpriseVo.getEptype() == 1){
						shipEnterpriseVo.setRegisterAddress("境外");
					}else{
						shipEnterpriseVo.setRegisterAddress("境内");
					}
				}
				haseInfoVO.setShipEnterpriseVo(shipEnterpriseVo);
			}else{
				ShipEnterpriseVo shipEnterpriseVo = new ShipEnterpriseVo();
				haseInfoVO.setShipEnterpriseVo(shipEnterpriseVo);
			}
		}else{
			haseInfoVO.setShipEnterpriseVo(new ShipEnterpriseVo());
		}

		//给Chments附上图片的路径信息
		Chments query = new Chments();
		query.setId(haseInfoVO.getInfoId());
		query.setFileType(Constants.FILE_PRODUCT_TYPE);
		query.setType(BusinessType.PURCHASE.getCode());
		List<ChmentsVO> voList = chmentsService.findChmentsVoByChments( query);
		haseInfoVO.setChments(voList);

		//給urls 数组附上 图片的路径信息
		List<String> strList = new ArrayList<>();
		if(voList!=null && voList.size()>0){
			Iterator<ChmentsVO> it = voList.iterator();
			while (it.hasNext()){
				ChmentsVO chmentsVO = it.next();
				if(chmentsVO != null && chmentsVO.getPath() != null){
					strList.add(chmentsVO.getPath());
				}
			}
			haseInfoVO.setUrls(strList);
		}

		return haseInfoVO;
	}


}

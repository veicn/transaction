package com.sinochem.crude.trade.shipping.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.shipping.constant.Constants;
import com.sinochem.crude.trade.shipping.constant.ExternalApi;
import com.sinochem.crude.trade.shipping.domain.Agreement;
import com.sinochem.crude.trade.shipping.domain.ConfirmationSheet;
import com.sinochem.crude.trade.shipping.domain.po.MessageSheet;
import com.sinochem.crude.trade.shipping.enums.ExceptionEnum;
import com.sinochem.crude.trade.shipping.helper.NotificationHelper;
import com.sinochem.crude.trade.shipping.service.AgreementService;
import com.sinochem.crude.trade.shipping.service.ConfirmationSheetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.dao.TransitUnloadingMapper;
import com.sinochem.crude.trade.shipping.domain.TransitUnloading;
import com.sinochem.crude.trade.shipping.service.SimplePageInfo;
import com.sinochem.crude.trade.shipping.service.TransitUnloadingService;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.transaction.annotation.Transactional;
//import com.runyi.ryplat.api.commons.SimplePageInfo;

@Service
public class TransitUnloadingServiceImpl implements TransitUnloadingService {


	@Value("${id.quanzhou}")
	private Long producterId;
	@Autowired
	private TransitUnloadingMapper transitUnloadingMapper;

	@Autowired
	private ConfirmationSheetService confirmationSheetService;
	@Autowired
	private AgreementService agreementService;

	@Autowired
	private NotificationHelper notificationHelper;

	private final Logger log = LoggerFactory.getLogger(TransitUnloadingServiceImpl.class);



	/**
	 * 添加 卸港前在途信息 -武刚鹏-2018年3月24日15:51:16
	 */
	@Override
	@Transactional
	public Integer insertRecord(TransitUnloading transitUnloading,CurrentUser currentUser)throws BizException{
		if (transitUnloading == null || StringUtil.isBlank(transitUnloading.getShipConfirmationSheetId().toString())) {
			BizException bizException = new BizException();
			bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
			throw bizException;
		}
		int flag=0;
		ConfirmationSheet confirmationSheet = new ConfirmationSheet();
		confirmationSheet.setConfirmationSheetId(transitUnloading.getShipConfirmationSheetId());
		List<ConfirmationSheet> list = confirmationSheetService.queryByEntitys(confirmationSheet);
		if (list != null) {
			confirmationSheet =list.get(0);
			//确认单信息为以装港才修改在途中
			if(  Integer.valueOf(confirmationSheet.getStatus())<Integer.valueOf(ExternalApi.AGREEMENT_50)) {
				Agreement agreement = new Agreement();
				agreement.setAgreementId(confirmationSheet.getShipAgreementId());
				//状态改为已装港，才能在接下来的service修改为在途中
				agreement.setStatus(ExternalApi.AGREEMENT_50);
				agreementService.updateStatusByAgreementId(agreement, currentUser);
				confirmationSheet.setStatus(ExternalApi.CONFIRM_50);
				confirmationSheetService.updateStatusByconfirmationId(confirmationSheet, currentUser);
			}
			if (StringUtil.isEmpty(transitUnloading.getUuid())) {
				transitUnloading.setUuid(KeyGenUtils.newUuid());
			}
			transitUnloading.setUpdateUser(currentUser.getMemberId());
			transitUnloading.setCreateUser(currentUser.getMemberId());
			transitUnloading.setAliveFlag(Constants.SAVE_FLAG);
			 flag =  transitUnloadingMapper.insertRecord(transitUnloading);
			if(flag>0){
				this.sendTransitMessage(confirmationSheet);
			}
		}
		return flag;
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long transitUnloadingId) throws BizException{
		if (transitUnloadingId == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return transitUnloadingMapper.deleteById(transitUnloadingId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(TransitUnloading  record){
    	return transitUnloadingMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(TransitUnloading transitUnloading) throws BizException{
		if ( transitUnloading.getTransitUnloadingId() == null  ) {
			throw new BizException("transitUnloadingId 为空，不能修改 ");
		}
		return transitUnloadingMapper.updateRecordById(transitUnloading);
	}
	
	/**
	 * 根据uuid-修改对象
	 */
	@Override
	public int updateRecordByUuid(TransitUnloading transitUnloading) throws BizException{
		if ( transitUnloading.getUuid() == null) {
			throw new BizException("uuid为空，不能修改 ");
		}
		int flag =  transitUnloadingMapper.updateRecordByUuid(transitUnloading);

		if(flag>0){
			ConfirmationSheet confirmationSheet = confirmationSheetService.findByPrimaryKey(transitUnloading.getShipConfirmationSheetId());
			this.sendTransitMessage(confirmationSheet);
		}
		return flag;
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return transitUnloadingMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(TransitUnloading transitUnloading){
		return transitUnloadingMapper.updateRecords(transitUnloading.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public TransitUnloading findByPrimaryKey(Long transitUnloadingId){
		return  transitUnloadingMapper.findByPrimaryKey(transitUnloadingId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public TransitUnloading findByUuid(String uuid){
		return  transitUnloadingMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<TransitUnloading> queryByEntitys(TransitUnloading transitUnloading){
		return  transitUnloadingMapper.queryByEntitys(transitUnloading);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return transitUnloadingMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) transitUnloadingMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return transitUnloadingMapper.countRecords(map);
	}

	@Override
	public int insertRecordByCurrentUser(TransitUnloading transitUnloading, CurrentUser currentUser) {
		// TODO Auto-generated method stub
		return transitUnloadingMapper.insertRecord(transitUnloading);
	}

	
	//**************************以下方法为开发者补充*********************************/


	//region 根据确认单ID查询卸港在途信息-武刚鹏-2018年3月19日19:34:46
	/**
	 * 根据确认单ID查询卸港在途信息-武刚鹏-2018年3月19日19:34:46
	 * @param confirmationSheetId
	 * @return
	 */
	@Override
	public List<TransitUnloading> findByConfirmationSheetId(Long confirmationSheetId){

		return transitUnloadingMapper.findByConfirmationSheetId(confirmationSheetId);
	}
	//endregion

	//region 根据uuid删除船舶在途信息 -武刚鹏-2018年3月19日19:33:45
	/**
	 * 根据uuid删除船舶在途信息 -武刚鹏-2018年3月19日19:33:45
	 * @param uuid
	 * @return
	 */
    @Override
    public Boolean deleteRecordsByUuid(String uuid)throws BizException {
		BizException bizException = new BizException();
		if (null == uuid) {
			bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
			throw bizException;
		}
    	return  transitUnloadingMapper.deleteRecordsByUuid(uuid)>0?true:false;
    }
	//endregion


	private void sendTransitMessage(ConfirmationSheet confirmationSheet){
		MessageSheet messageSheet = new MessageSheet();
		messageSheet.setProduct(confirmationSheet.getProduct());
		messageSheet.setQuantity(confirmationSheet.getQuantity());
		messageSheet.setVesselName(confirmationSheet.getVesselName());
		messageSheet.setConfirmationSheetCd(confirmationSheet.getConfirmationSheetCd());
//			{confirmationSheetCd,vesselname,product,quantity}
		Long[] memberIds = new Long[]{
				confirmationSheet.getBuyerId(),
				this.producterId,
				confirmationSheet.getSellerId(),
				null
		};
		String[] urls = new String[]{
				Constants.BUYER_CONFIRMATION_URL,
			Constants.SELLER_CHARTER_PARTY_URL,
				Constants.SELLER_CHARTER_PARTY_URL,
				null

		};

		//在线 就是租船代理发送
		if(Constants.COMFIRM_ONLINE_DEFULT.equals(confirmationSheet.getConfirmOnline())){
			//发送给贸易商
			memberIds[3]=confirmationSheet.getSellerId();
			urls[3]=Constants.SELLER_CHARTER_PARTY_URL;
			try{
				int index = 0;
				for (Long memberId:memberIds) {
					if(memberId!=null){
						messageSheet.setMemberId(memberId);
						messageSheet.setHyperlink(urls[index]);
						notificationHelper.sendProDisponentOwnerUpdateTransit(messageSheet);
					}
					index++;
				}
			}catch (Exception e){
				log.error("租船代理发送在途信息 消息失败",e);
			}
		}else{
			Agreement agreement = agreementService.findByPrimaryKey(confirmationSheet.getShipAgreementId());
			if(agreement!=null){
				memberIds[3]=agreement.getEpMemberId();
				urls[3] = Constants.AGENT_CONFIRMATION_URL;
			}
			try{
				int index = 0;
				for (Long memberId:memberIds) {
					if(memberId!=null){
						messageSheet.setMemberId(memberId);
						messageSheet.setHyperlink(urls[index]);
						notificationHelper.sendProMerchantUpdateTransit(messageSheet);
					}
					index++;
				}
			}catch (Exception e){
				log.error("租船代理发送在途信息 消息失败",e);
			}
		}
	}
}

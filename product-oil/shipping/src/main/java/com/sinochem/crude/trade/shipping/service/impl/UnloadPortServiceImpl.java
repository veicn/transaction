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
import com.sinochem.crude.trade.shipping.enums.AgreementStatusEnums;
import com.sinochem.crude.trade.shipping.enums.ConfirmStatusEnums;
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
import com.sinochem.crude.trade.shipping.dao.UnloadPortMapper;
import com.sinochem.crude.trade.shipping.domain.UnloadPort;
import com.sinochem.crude.trade.shipping.service.SimplePageInfo;
import com.sinochem.crude.trade.shipping.service.UnloadPortService;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.transaction.annotation.Transactional;
//import com.runyi.ryplat.api.commons.SimplePageInfo;

@Service
public class UnloadPortServiceImpl implements UnloadPortService {

	private final Logger  logger = LoggerFactory.getLogger(UnloadPortServiceImpl.class);

	@Value("${id.quanzhou}")
	private Long producterId;
	@Autowired
	private UnloadPortMapper unloadPortMapper;
	@Autowired
	private ConfirmationSheetService confirmationSheetService;
	@Autowired
	private AgreementService agreementService;

	@Autowired
	private NotificationHelper notificationHelper;

	/**
	 * 插入卸港记录 武刚鹏-2018年3月21日15:58:23
	 * @param unloadport
	 * @param currentUser
	 * @return
	 * @throws BizException
	 */
	@Override
	@Transactional
	public Integer insertRecord(UnloadPort unloadport,CurrentUser currentUser)throws BizException{

		if (unloadport == null || StringUtil.isBlank(unloadport.getShipConfirmationSheetId().toString())) {
			BizException bizException = new BizException();
			bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
			throw bizException;
		}
		ConfirmationSheet confirmationSheet = new ConfirmationSheet();
		confirmationSheet.setConfirmationSheetId(unloadport.getShipConfirmationSheetId());

		List<ConfirmationSheet> list = confirmationSheetService.queryByEntitys(confirmationSheet);
		if (list != null) {
			confirmationSheet = list.get(0);
			UnloadPort domainUnlaodPort = unloadPortMapper.findByShipConfirmationSheetId(confirmationSheet.getConfirmationSheetId());
			if(domainUnlaodPort==null){
				Agreement agreement = new Agreement();
				agreement.setAgreementId(confirmationSheet.getShipAgreementId());
				agreement.setStatus(ExternalApi.AGREEMENT_60);
				agreementService.updateStatusByAgreementId(agreement, currentUser);
				confirmationSheet.setStatus(ExternalApi.CONFIRM_60);
				confirmationSheetService.updateStatusByconfirmationId(confirmationSheet, currentUser);
				if(!StringUtil.isNotBlank(unloadport.getUuid())){
					unloadport.setUuid(UUID.randomUUID().toString());
				}
				unloadport.setUuid(KeyGenUtils.newUuid());
				unloadport.setCreateUser(currentUser.getMemberId());
				unloadport.setUpdateUser(currentUser.getMemberId());
				if (unloadport.getCreateDate() == null) {
					unloadport.setCreateDate(new Date());
				}
				if (unloadport.getUpdateDate() == null) {
					unloadport.setUpdateDate(new Date());
				}
				unloadport.setAliveFlag(Constants.SAVE_FLAG);
				return unloadPortMapper.insertRecord(unloadport);
			}else{
				return 0;
			}

		}else{
			return Integer.valueOf(0);
		}


	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long unloadPortId) throws BizException{
		if (unloadPortId == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return unloadPortMapper.deleteById(unloadPortId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(UnloadPort  record){
    	return unloadPortMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(UnloadPort unloadPort) throws BizException{
		if ( unloadPort.getUnloadPortId() == null  ) {
			throw new BizException("unloadPortId 为空，不能修改 ");
		}
		return unloadPortMapper.updateRecordById(unloadPort);
	}
	
	/**
	 * 根据uuid-修改对象
	 */
	@Override
	public Integer updateRecordByUuid(UnloadPort unloadPort,CurrentUser currentUser) throws BizException{
		if ( unloadPort.getUuid() == null || currentUser == null) {
			throw new BizException("uuid为空，不能修改 ");
		}
		unloadPort.setUpdateUser(currentUser.getMemberId());
		return unloadPortMapper.updateRecordByUuid(unloadPort);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return unloadPortMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(UnloadPort unloadPort){
		return unloadPortMapper.updateRecords(unloadPort.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public UnloadPort findByPrimaryKey(Long unloadPortId){
		return  unloadPortMapper.findByPrimaryKey(unloadPortId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public UnloadPort findByUuid(String uuid){
		return  unloadPortMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<UnloadPort> queryByEntitys(UnloadPort unloadPort){
		return  unloadPortMapper.queryByEntitys(unloadPort);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return unloadPortMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) unloadPortMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return unloadPortMapper.countRecords(map);
	}

	@Override
	public Integer insertRecordByCurrentUser(UnloadPort unloadPort, CurrentUser currentUser) {
		// TODO Auto-generated method stub
		return unloadPortMapper.insertRecord(unloadPort);
	}

	
	//**************************以下方法为开发者补充*********************************/
	//查询船舶卸港信息
	@Override
	public UnloadPort findByShipConfirmationSheetId(Long confirmationSheetId) {
		return unloadPortMapper.findByShipConfirmationSheetId(confirmationSheetId);
	}



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
		String[] urls = new String[] {
				Constants.BUYER_CONFIRMATION_URL,
				Constants.SELLER_CHARTER_PARTY_URL,
				Constants.SELLER_CHARTER_PARTY_URL,
				null
		};

		//在线 就是租船代理发送 船代
		if(Constants.COMFIRM_ONLINE_DEFULT.equals(confirmationSheet.getConfirmOnline())){
			//发送给贸易商
			memberIds[3]=confirmationSheet.getShippingAgentId();
			urls[3]=Constants.SELLER_CHARTER_PARTY_URL;
			try{
				int index = 0;
				for (Long memberId:memberIds) {
					if(memberId!=null){
						messageSheet.setMemberId(memberId);
						messageSheet.setHyperlink(urls[index]);
						notificationHelper.sendProDisponentOwnerUpdateUnloading(messageSheet);
					}
					index++;
				}
			}catch (Exception e){
				logger.error("租船代理发送卸港信息消息失败",e);
			}
		}else{
			Agreement agreement = agreementService.findByPrimaryKey(confirmationSheet.getShipAgreementId());
			if(agreement!=null){
				memberIds[3]=agreement.getEpMemberId();
				urls[3]=Constants.AGENT_CONFIRMATION_URL;
			}
			try{
				int index = 0;
				for (Long memberId:memberIds) {
					if(memberId!=null){
						messageSheet.setMemberId(memberId);
						messageSheet.setHyperlink(urls[index]);
						notificationHelper.sendProMerchantUpdateUnloading(messageSheet);
					}
					index++;
				}
			}catch (Exception e){
				logger.error("租船代理发送卸港信息 消息失败",e);
			}
		}
	}
}

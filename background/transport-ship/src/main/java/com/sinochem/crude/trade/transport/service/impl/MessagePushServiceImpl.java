package com.sinochem.crude.trade.transport.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eyeieye.melody.web.url.URLBroker;
import com.mysql.jdbc.StringUtils;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.transport.commons.constants.MessageTypeConstants;
import com.sinochem.crude.trade.transport.commons.constants.MessageTypeConstants.TYPE;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.commons.utils.MessageHttpUtil;
import com.sinochem.crude.trade.transport.domain.Agency;
import com.sinochem.crude.trade.transport.domain.Agreement;
import com.sinochem.crude.trade.transport.domain.AgreementStatements;
import com.sinochem.crude.trade.transport.domain.Clause;
import com.sinochem.crude.trade.transport.domain.Intention;
import com.sinochem.crude.trade.transport.domain.Pallet;
import com.sinochem.crude.trade.transport.domain.ShipPact;
import com.sinochem.crude.trade.transport.domain.ShipPlate;
import com.sinochem.crude.trade.transport.domain.SysShip;
import com.sinochem.crude.trade.transport.domain.Waybill;
import com.sinochem.crude.trade.transport.model.MemberInfoVO;
import com.sinochem.crude.trade.transport.service.AgencyService;
import com.sinochem.crude.trade.transport.service.AgreementService;
import com.sinochem.crude.trade.transport.service.AgreementStatementsService;
import com.sinochem.crude.trade.transport.service.ClauseService;
import com.sinochem.crude.trade.transport.service.IntentionService;
import com.sinochem.crude.trade.transport.service.MemberInfoService;
import com.sinochem.crude.trade.transport.service.MessagePushService;
import com.sinochem.crude.trade.transport.service.PalletService;
import com.sinochem.crude.trade.transport.service.ShipPactService;
import com.sinochem.crude.trade.transport.service.ShipPlateService;
import com.sinochem.crude.trade.transport.service.SysShipService;
import com.sinochem.crude.trade.transport.service.WaybillService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;

@Service
public class MessagePushServiceImpl implements MessagePushService {
	private static final Log log = LogFactory.getLog(MessagePushServiceImpl.class);
	
	@Autowired
	private HttpConnectionManager httpConnectionManager;
	@Autowired
	private URLBroker systemServer;
	@Autowired
	private URLBroker messageServer;
	@Autowired
	private URLBroker shipServer;
	/*@Autowired
	private URLBroker memberServer;*/
	@Autowired
	private ShipPactService pactService;
	@Autowired
	private PalletService palletService;
	@Autowired
	private WaybillService waybillService;
	@Autowired
	private AgreementService agreementService;
	@Autowired
	private ShipPlateService shipPlateService;
	@Autowired
	private IntentionService intentionService;
	@Autowired
	private ClauseService clauseService;
	@Autowired
	private SysShipService sysService;
	@Autowired
	private AgreementStatementsService agreementStatementsService;
	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private ShipPactService shipPactService;
	@Autowired
	private AgencyService agencyService;
	
	/**货主物流跟踪页面*/
	private  String address_001="page/#/transport?agreementUuid="; 
	/**货主租船协议列表*/
	private  String address_002="pallet/agreementList.htm"; 
	/**船东/经纪人船盘列表*/
	private  String address_003="shipPlate/shipPlateList.htm"; 
	/**转租船东船盘列表*/
	private  String address_004="shipPlate/shipAgentShipPlateList.htm"; 
	/**转租船东租船需求列表*/
	private  String address_005="pallet/palletQueryListShipowner.htm"; 
	/**货主租船需求列表*/
	private  String address_006="pallet/palletQueryList.htm"; 
	/**船代船合同管理列表*/
	private  String address_007="shipPactNew/shipPactListAgency.htm"; 
	/**货主结算单管理列表*/
	private  String address_008="shipperstatements/shipperagreementsStatementsList.htm"; 
	/**船舶/经纪人船舶管理列表*/
	private  String address_009="shipOwnerSysShip/sysShipList.htm"; 
	/**登录*/
	private  String address_010="login.htm"; 
	
	/*type-id==>1航次开始-船合同；2装港-船合同；3在途-船合同；4卸港-船合同；5航次结束-船合同；6上传租船协议-租船协议；7船盘失效-船盘；8询盘-询盘；9还盘-询盘；
    10确认租船-询盘；11船盘发布人终止询盘-询盘；12询盘人终止询盘-询盘；13报盘-报盘；14确认报盘-报盘；15终止报盘-报盘；
    16船舶审核通过-船舶；17船舶审核驳回-船舶；18指定船代装港 -船合同；19指定船代卸港 -船合同；20已结算-结算单21申请租船指定二船东-货盘*/
	@Override
	public void messagePush(int type,Long id,Long fromMemberId) {
		try {
			switch (type) {
			case 1://航次开始
				sendLogistics(fromMemberId, id, MessageTypeConstants.TYPE.ITRS001);
				break;
			case 2://装港
				sendLogistics(fromMemberId, id, MessageTypeConstants.TYPE.ITRS002);
				break;
			case 3://在途
				sendLogistics(fromMemberId, id,  MessageTypeConstants.TYPE.ITRS003);
				break;
			case 4://卸港
				sendLogistics(fromMemberId, id,  MessageTypeConstants.TYPE.ITRS004);
				break;
			case 5://航次结束
				sendLogistics(fromMemberId, id,  MessageTypeConstants.TYPE.ITRS005);
				break;
			case 6://上传租船协议
				sendAgreement(fromMemberId, id,  MessageTypeConstants.TYPE.ITRS006);
				break;
			case 7://船盘失效
				sendShipPlate(fromMemberId, id,  MessageTypeConstants.TYPE.ITRS007);
				break;
			case 21://申请租船
				sendPallet(fromMemberId, id,  MessageTypeConstants.TYPE.ITRS008);
				break;
			case 8://询盘
				sendIntention(fromMemberId, id,  MessageTypeConstants.TYPE.ITRS009);
				break;
			case 9://还盘
				sendCoIntention(fromMemberId, id,  MessageTypeConstants.TYPE.ITRS010);
				break;
			case 10://确认租船
				sendResIntention(fromMemberId, id,  MessageTypeConstants.TYPE.ITRS011);
				break;
			case 11://船盘发布人终止
				sendCoIntention(fromMemberId, id,  MessageTypeConstants.TYPE.ITRS012);
				break;
			case 12://询盘人终止
				sendResIntention(fromMemberId, id,  MessageTypeConstants.TYPE.ITRS013);
				break;
			case 13://报盘
				sendClause(fromMemberId, id,  MessageTypeConstants.TYPE.ITRS014);
				break;
			case 14://确认报盘
				sendResClause(fromMemberId, id,  MessageTypeConstants.TYPE.ITRS015);
				break;
			case 15://终止报盘
				sendResClause(fromMemberId, id,  MessageTypeConstants.TYPE.ITRS016);
				break;
			case 16://船舶审核通过
				sendAuditShip(fromMemberId, id,  MessageTypeConstants.TYPE.ITRS017);
				break;
			case 17://船舶审核驳回
				sendAuditShip(fromMemberId, id,  MessageTypeConstants.TYPE.ITRS018);
				break;
			case 18://指定装港船代
				sendAgency(fromMemberId, id,  MessageTypeConstants.TYPE.ITRS019,19);
				break;
			case 19://指定卸港船代
				sendAgency(fromMemberId, id,  MessageTypeConstants.TYPE.ITRS020,20);
				break;
			case 20://已结算
				sendBalance(fromMemberId, id,  MessageTypeConstants.TYPE.ITRS021);
				break;
			default:
				log.info("消息发送类型不匹配，类型===>"+type+",id===>"+id);
				break;
			}
			log.info("消息发送成功，类型===>"+type+",id===>"+id);
		} catch (Exception e) {
			log.error("消息发送失败，类型===>"+type+",id===>"+id,e);
		}
	}
	
	private void sendLogistics(Long fromMemberId ,Long id , TYPE type){
		ShipPact pact = pactService.findByPrimaryKey(id);
		if (pact != null){
			Waybill waybill = new Waybill();
			waybill.setShipPactId(pact.getShipPactId());
			waybill.setShipPactUuid(pact.getUuid());
			List<Waybill> list = waybillService.queryByEntitys(waybill);
			if (list != null && !list.isEmpty()){
				if (list.size() > 20){
					return ;
				}
				for (Waybill way : list) {
					String agreementUuid = way.getAgreementUuid();
					Pallet pallet = palletService.findByPrimaryKey(way.getPalletId());
					if (pallet != null){
						//站内信
					MemberInfoVO memberInfo = getPerson(pallet.getCreateUser());
					Map<String, Object> msgModel = new HashMap<>();
					msgModel.put("agreementId", way.getAgreementCode());
					msgModel.put("idLink", shipServer.get(address_001+agreementUuid).toString());
					msgModel.put("loginLink", systemServer.get(address_010).toString());
					pushToMessage(pallet.getCreateUser()+"", msgModel , msgModel, msgModel, msgModel,
							memberInfo.getMobile(), memberInfo.getEmail(), type);
					}
				}
			}
		}
	}
	private void sendAgreement(Long fromMemberId ,Long id , TYPE type){
		Agreement agreement = agreementService.findByPrimaryKey(id);
		if (agreement != null){
			Pallet pallet = palletService.findByPrimaryKey(agreement.getPalletId());
			if (pallet != null){
				MemberInfoVO memberInfo = getPerson(pallet.getCreateUser());
				Map<String, Object> msgModel = new HashMap<>();
				msgModel.put("epMemberName", agreement.getEpMemberName());
				MemberInfoVO company = getCompany(agreement.getEpMemberId());
				msgModel.put("epMemberNameEn", company.getEngLishName());
				msgModel.put("agreementId", agreement.getAgreementCode());
				msgModel.put("idLink", shipServer.get(address_002).toString());
				msgModel.put("loginLink", systemServer.get(address_010).toString());
				pushToMessage(pallet.getCreateUser()+"", msgModel , msgModel, msgModel, msgModel,
						memberInfo.getMobile(), memberInfo.getEmail(), type);
			}
		}
	}
	private void sendShipPlate(Long fromMemberId ,Long id , TYPE type){
	    ShipPlate shipPlate = shipPlateService.findByPrimaryKey(id);
		if (shipPlate != null){
			MemberInfoVO memberInfo = getPerson(shipPlate.getCreateUser());
			Map<String, Object> msgModel = new HashMap<>();
			msgModel.put("shipPlateId", shipPlate.getName());
				if ("1".equals(shipPlate.getRelType())){
					//船东发布
					msgModel.put("idLink", shipServer.get(address_003).toString());
				} else {
					//转租船东发布
					msgModel.put("idLink", shipServer.get(address_004).toString());
				}
				msgModel.put("loginLink", systemServer.get(address_010).toString());
				pushToMessage(shipPlate.getCreateUser()+"", msgModel , msgModel, msgModel, msgModel,
						memberInfo.getMobile(), memberInfo.getEmail(), type);
				
		}
	}
	private void sendPallet(Long fromMemberId ,Long id ,TYPE type){
		Pallet pallet = palletService.findByPrimaryKey(id);
		if (pallet != null){
			MemberInfoVO memberInfo = getCompany(pallet.getTraderId());
			Map<String, Object> msgModel = new HashMap<>();
			msgModel.put("epMemberName", pallet.getEpMemberName());
			MemberInfoVO company = getCompany(pallet.getEpMemberId());
			msgModel.put("epMemberNameEn", company.getEngLishName());
			msgModel.put("palletId", pallet.getPalletCode());
			msgModel.put("idLink", shipServer.get(address_005).toString());
			msgModel.put("loginLink", systemServer.get(address_010).toString());
			pushToMessage(pallet.getTraderId()+"", msgModel, msgModel, msgModel, msgModel,
					memberInfo.getMobile(),memberInfo.getEmail(), type);
		}
	}
	private void sendIntention(Long fromMemberId,Long id,TYPE type){
		Intention intention = intentionService.findByPrimaryKey(id);
		if (intention != null){
			ShipPlate shipPlate = shipPlateService.findByPrimaryKey(intention.getShipPlateId());
			if (shipPlate != null){
				MemberInfoVO memberInfo = getPerson(shipPlate.getCreateUser());
				Map<String, Object> msgModel = new HashMap<>();
				msgModel.put("epMemberName", intention.getEpMemberName());
				MemberInfoVO company = getCompany(intention.getEpMemberId());
				msgModel.put("epMemberNameEn", company.getEngLishName());
				msgModel.put("intentionCode", intention.getIntentionCode());
				msgModel.put("quantity", intention.getInMinQuantity());
				if ("1".equals(shipPlate.getRelType())){
					//船东发布
					msgModel.put("idLink", shipServer.get(address_003).toString());
				} else if ("2".equals(shipPlate.getRelType())){
					//转租船东发布
					msgModel.put("idLink", shipServer.get(address_004).toString());
				}
				msgModel.put("loginLink", systemServer.get(address_010).toString());
				pushToMessage(shipPlate.getCreateUser()+"", msgModel, msgModel, msgModel, msgModel,
						memberInfo.getMobile(),memberInfo.getEmail(), type);
				
			}
		}
	}
	private void sendCoIntention(Long fromMemberId ,Long id ,TYPE type){
		Intention intention = intentionService.findByPrimaryKey(id);
		if (intention != null){
			Pallet pallet = palletService.findByPrimaryKey(intention.getPalletId());
			if (pallet != null){
				MemberInfoVO memberInfo = getPerson(pallet.getCreateUser());
				Map<String, Object> msgModel = new HashMap<>();
				msgModel.put("intentionCode",intention.getIntentionCode());
				msgModel.put("intention", intention.getShipPlateMemberName());
				msgModel.put("idLink", shipServer.get(address_006).toString());
				msgModel.put("loginLink", systemServer.get(address_010).toString());
				pushToMessage(pallet.getCreateUser()+"", msgModel, msgModel, msgModel, msgModel,
						memberInfo.getMobile(),memberInfo.getEmail(), type);
			}
		}
	}
	private void sendResIntention(Long fromMemberId ,Long id ,TYPE type){
		Intention intention = intentionService.findByPrimaryKey(id);
		if (intention != null){
			ShipPlate shipPlate = shipPlateService.findByPrimaryKey(intention.getShipPlateId());
			if (shipPlate != null){
				MemberInfoVO memberInfo = getPerson(shipPlate.getCreateUser());
				Map<String, Object> msgModel = new HashMap<>();
				msgModel.put("intentionCode",intention.getIntentionCode());
				msgModel.put("intention", intention.getEpMemberName());
				if ("1".equals(shipPlate.getRelType())){
					//船东发布
					msgModel.put("idLink", shipServer.get(address_003).toString());
				} else if ("2".equals(shipPlate.getRelType())){
					//转租船东发布
					msgModel.put("idLink", shipServer.get(address_004).toString());
				}
				msgModel.put("loginLink", systemServer.get(address_010).toString());
				pushToMessage(shipPlate.getCreateUser()+"", msgModel, msgModel, msgModel, msgModel,
						memberInfo.getMobile(),memberInfo.getEmail(), type);
			}
		}
	}
	private void sendClause(Long fromMemberId ,Long id , TYPE type){
		Clause clause = clauseService.findByPrimaryKey(id);
		if (clause != null){
			Pallet pallet = palletService.findByPrimaryKey(clause.getPalletId());
			if (pallet != null){
				MemberInfoVO memberInfo = getPerson(pallet.getCreateUser());
				Map<String, Object> msgModel = new HashMap<>();
				msgModel.put("epMemberName", clause.getEpMemberName());
				MemberInfoVO company = getCompany(clause.getEpMemberId());
				msgModel.put("epMemberNameEn", company.getEngLishName());
				msgModel.put("ClauseCode", clause.getClauseCode());
				msgModel.put("quantity",clause.getMinQuantity());
				msgModel.put("idLink", shipServer.get(address_006).toString());
				msgModel.put("loginLink", systemServer.get(address_010).toString());
				pushToMessage(pallet.getCreateUser()+"", msgModel, msgModel, msgModel, msgModel,
						memberInfo.getMobile(),memberInfo.getEmail(), type);
			}
		}
	}
	private void sendResClause(Long fromMemberId ,Long id , TYPE type){
		Clause clause = clauseService.findByPrimaryKey(id);
		if (clause != null){
			Pallet pallet = palletService.findByPrimaryKey(clause.getPalletId());
			if (pallet != null){
				MemberInfoVO memberInfo = getPerson(clause.getCreateUser());
				Map<String, Object> msgModel = new HashMap<>();
				msgModel.put("ClauseCode", clause.getClauseCode());
				msgModel.put("epMemberName",clause.getPalletMemberName());
				MemberInfoVO company = getCompany(clause.getPalletMemberId());
				msgModel.put("epMemberNameFn",company.getEngLishName());
				msgModel.put("idLink", shipServer.get(address_005).toString());
				msgModel.put("loginLink", systemServer.get(address_010).toString());
				pushToMessage(clause.getCreateUser()+"", msgModel, msgModel, msgModel, msgModel,
						memberInfo.getMobile(),memberInfo.getEmail(), type);
			}
		}
	}
	private void sendAuditShip(Long fromMemberId ,Long id ,TYPE type){
		SysShip ship = sysService.findByPrimaryKey(id);
		if (ship != null){
			MemberInfoVO memberInfo = getPerson(ship.getCreateUser());
			Map<String, Object> msgModel = new HashMap<>();
			msgModel.put("sysShipId",ship.getName());
			if (!"1".equals(ship.getPublisherType())){
				msgModel.put("idLink", shipServer.get(address_009).toString());
			} else {
				msgModel.put("idLink", systemServer.get(address_010).toString());
			}
			msgModel.put("loginLink", systemServer.get(address_010).toString());
			pushToMessage(ship.getCreateUser()+"", msgModel, msgModel, msgModel, msgModel,
					memberInfo.getMobile(),memberInfo.getEmail(), type);
		}
	}
	private void sendBalance(Long fromMemberId ,Long id , TYPE type){
		AgreementStatements statements = agreementStatementsService.findByPrimaryKey(id);
		if (statements != null){
			Agreement agreement = agreementService.findByUuid(statements.getShipAgreementUuid());
			if (agreement != null){
				Pallet pallet = palletService.findByUuid(agreement.getPalletUuid());
				if (pallet != null){
					MemberInfoVO memberInfo = getPerson(pallet.getCreateUser());
					MemberInfoVO memberInfos = getCompany(statements.getAgentMemberId());
					Map<String, Object> msgModel = new HashMap<>();
					msgModel.put("agreementId", agreement.getAgreementCode());
					msgModel.put("agreement",memberInfos.getUsername());
					msgModel.put("calculate",statements.getReceiptCode());
					msgModel.put("idLink", shipServer.get(address_008).toString());
					msgModel.put("loginLink", systemServer.get(address_010).toString());
					pushToMessage(pallet.getCreateUser()+"", msgModel, msgModel, msgModel, msgModel,
							memberInfo.getMobile(),memberInfo.getEmail(), type);
				}
			}
		}
	}
	private void sendAgency(Long fromMemberId ,Long id , TYPE type ,int in){
		
		ShipPact shipPact = shipPactService.findByPrimaryKey(id);
		if (shipPact != null){
			Agency agency = new Agency();
			if (in == 19){
				agency.setType("1");
			} else {
				agency.setType("2");
			}
			agency.setShipPactUuid(shipPact.getUuid());
			agency.setShipPactId(shipPact.getShipPactId());
			List<Agency> list = agencyService.queryByEntitys(agency);
			if (list != null && !list.isEmpty()){
				List<String> lists = new ArrayList<>();
				if (lists.size() > 20){
					return;
				}
				for (Agency agencys : list) {
					String port = agencys.getPort();
					Long epMemberId = agencys.getEpMemberId();
					String str= port+epMemberId;
					if (!lists.contains(str)){
						MemberInfoVO memberInfo = getCompany(epMemberId);
						MemberInfoVO memberInfos = getCompany(shipPact.getEpMemberId());
						Map<String, Object> msgModel = new HashMap<>();
						msgModel.put("epMemberName", memberInfos.getUsername());
						msgModel.put("epMemberNameEn", memberInfos.getEngLishName());
						msgModel.put("shipName",shipPact.getShipName());
						msgModel.put("port",port);
						msgModel.put("idLink", shipServer.get(address_007).toString());
						msgModel.put("loginLink", systemServer.get(address_010).toString());
						pushToMessage(epMemberId+"", msgModel, msgModel, msgModel, msgModel,
								memberInfo.getMobile(),memberInfo.getEmail(), type);
						
						lists.add(str);
					}
				}
			}
		}
	}
	
	
	/**
	 * 
	 * @param toMemberId 接收方
	 * @param msgModel 站内信模板
	 * @param mailModel 邮件模板
	 * @param talModel 短信模板
	 * @param appModel APP模板
	 * @param mobile 手机号码
	 * @param emailAddress 邮件地址
	 * @param type 类型
	 */
	private void pushToMessage(String toMemberId,Map<String, Object> msgModel,
		Map<String, Object> mailModel,Map<String, Object> talModel,
		Map<String, Object> appModel,String mobile,String emailAddress,TYPE type) {
			//发送站内信
			try{	
				String tplName = type.getTplName_all();
				/* Locale current = VisitorLocale.getCurrent();
				 if (current != null){
					 String string = current.getLanguage();
					 if ("en".equals(string)){
						 tplName = type.getTplName_en();
					 }
				 }*/
				  String sendMessage = MessageHttpUtil.sendMessage(httpConnectionManager.getHttpClient(), messageServer.get(MessageHttpUtil.MESSAGE_URL).toString(),
				          MessageHttpUtil.generateMessageParams(tplName,toMemberId,"3",type.getTitle(),msgModel));
				  System.out.println("=======》站内信返回参数："+sendMessage);
			} catch(Exception e) {
				log.error("发送站内信失败",e);
			}
			 // 发送app推送
			 try {
				 String sendMessage = MessageHttpUtil.sendMessage(httpConnectionManager.getHttpClient(), messageServer.get(MessageHttpUtil.APP_URL).toString(),
						 MessageHttpUtil.generateAPPParams(type.getTplName_all(), toMemberId, appModel));
				 System.out.println("=======》app返回参数："+sendMessage);
			 } catch (BizException e) {
				 log.error("发送app推送失败",e);
			 }
			 // 发送邮件
			 if (!StringUtils.isNullOrEmpty(emailAddress)){
				 try {
					 
					 String sendMessage = MessageHttpUtil.sendMessage(httpConnectionManager.getHttpClient(), messageServer.get(MessageHttpUtil.MAIL_TPL_URL).toString(),
							 MessageHttpUtil.generateTplMailParams(type.getTplName_all(),type.getTitle(),emailAddress,mailModel));
					  System.out.println("=======》邮件返回参数："+sendMessage);
				 } catch (BizException e) {
					 log.error("发送邮件失败",e);
				 }
			 }
			 // 发送短信
			 if (!StringUtils.isNullOrEmpty(mobile)){
				 try {
					 String sendMessage = MessageHttpUtil.sendMessage(httpConnectionManager.getHttpClient(), messageServer.get(MessageHttpUtil.SMS_URL).toString(),
							 MessageHttpUtil.generateSmsParams(type.getTplName(),mobile,talModel));
					  System.out.println("=======》短信返回参数："+sendMessage);
				 } catch (BizException e) {
					 log.error("发送短信失败",e);
				 }
			 }
	}

	private MemberInfoVO getPerson(Long memberId){
		MemberInfoVO infoVO = new MemberInfoVO();
		try {
			MemberInfoVO memberInfo = memberInfoService.memberInfo(memberId);
			if ( memberInfo != null){
				BeanUtils.copyProperties(memberInfo, infoVO);
			}
		} catch (Exception e) {
			log.error("会员根据memberI查询个人信息接口异常，id="+memberId,e);
		}
		return infoVO;
	}
	private MemberInfoVO getCompany(Long epMemberId){
		MemberInfoVO infoVO = new MemberInfoVO();
		try {
			EnterpriseVo vos = enterpriseService.getByMemberId(epMemberId);
			if (vos != null){
				infoVO.setEmail(vos.getEmail());
				infoVO.setMobile(vos.getMobile());
				infoVO.setMemberId(vos.getMemberId());
				infoVO.setUsername(vos.getFullName());
				infoVO.setEngLishName(vos.getEnglishName());
			}
		} catch (Exception e) {
			log.error("会员根据epMemberI查询企业信息接口异常，企业id="+epMemberId,e);
			throw new TransportException(TransportException.TYPE.ITSH555);
		}
		return infoVO;
	}

	@Override
	public void testPush() {
		Map<String, Object> mailModel = new HashMap<String, Object>();
			mailModel.put("userName","我是接收人员");
		  mailModel.put("hyperlink","http://www.test.mycrudeoil.com/");
		  try {
			// 发送短信
			  Map<String, Object> model = new HashMap<String, Object>();
			  model.put("userName","小智成测试");
			 /* MessageHttpUtil.sendMessage(httpConnectionManager.getHttpClient(), messageServer.get(MessageHttpUtil.SMS_URL).toString(),
			          MessageHttpUtil.generateSmsParams("test","13141523148",model));*/
			MessageHttpUtil.sendMessage(httpConnectionManager.getHttpClient(), messageServer.get(MessageHttpUtil.MAIL_TPL_URL).toString(),
			          MessageHttpUtil.generateTplMailParams("test","测试邮件","1176821699@qq.com",mailModel));
		} catch (BizException e) {
			e.printStackTrace();
		}
		
	}
	
	
}

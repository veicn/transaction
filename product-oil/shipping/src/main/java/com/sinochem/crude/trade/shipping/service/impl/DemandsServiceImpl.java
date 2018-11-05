package com.sinochem.crude.trade.shipping.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.shipping.constant.Constants;
import com.sinochem.crude.trade.shipping.domain.po.MessageSheet;
import com.sinochem.crude.trade.shipping.helper.NotificationHelper;
import com.sinochem.crude.trade.shipping.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.shipping.dao.DemandsMapper;
import com.sinochem.crude.trade.shipping.domain.Demands;
import com.sinochem.crude.trade.shipping.model.query.DemandsQuery;
import com.sinochem.crude.trade.shipping.model.query.WechatDemandsQuery;
import com.sinochem.crude.trade.shipping.service.DemandsService;
import com.sinochem.crude.trade.shipping.service.SimplePageInfo;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.it.b2b.common.page.PageUtils;

@Service
public class DemandsServiceImpl implements DemandsService {
	private final Logger logger = LoggerFactory.getLogger(DemandsServiceImpl.class);
	@Autowired
	private DemandsMapper demandsMapper;

	@Autowired
	private NotificationHelper notificationHelper;

	@Autowired
	private MessageService messageService;

	@Autowired
	private URLBroker shippingServer;

	public DemandsMapper getMapper(){
		return demandsMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(Demands demands){

		int flag = demandsMapper.insertRecord(demands);

		if(flag>0){
			//插入成功就要发送消息 给二船东所有人
			this.sendMessage(demands);
		}

		 return flag;
	}

	private void sendMessage(Demands demands) {
		try{
			MessageSheet messageSheet = new MessageSheet();
			EnterpriseVo enterpriseVo = messageService.getByMemberId(demands.getBuyerId());
			messageSheet.setMemberId(demands.getCharteringAgentId());
			messageSheet.setBuyer(enterpriseVo.getName());
			messageSheet.setQuantity(demands.getQuantity());
			messageSheet.setProduct(demands.getProdoctNm());
			messageSheet.setDemandsCd(demands.getDemandsCd());
			messageSheet.setHyperlink(Constants.DISPONENT_OWER_DEMANDS_URL);
			if(messageSheet.getMemberId()!=null){
				notificationHelper.sendProCreateShipDemands(messageSheet);
			}
		}catch (Exception e){
			logger.error("新增需求发送二船东消息发送失败",e);
		}
	}

	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long demandsId) throws BizException{
		if (demandsId == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return demandsMapper.deleteById(demandsId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(Demands  record){
    	return demandsMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(Demands demands) throws BizException{
		if ( demands.getDemandsId() == null  ) {
			throw new BizException("demandsId 为空，不能修改 ");
		}
		return demandsMapper.updateRecordById(demands);
	}
	
	/**
	 * 根据uuid-修改对象
	 */
	@Override
	public int updateRecordByUuid(Demands demands) throws BizException{
		if ( demands.getUuid() == null) {
			throw new BizException("uuid为空，不能修改 ");
		}
		return demandsMapper.updateRecordByUuid(demands);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return demandsMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Demands demands){
		return demandsMapper.updateRecords(demands.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public Demands findByPrimaryKey(Long demandsId){
		return  demandsMapper.findByPrimaryKey(demandsId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public Demands findByUuid(String uuid){
		return  demandsMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<Demands> queryByEntitys(Demands demands){
		return  demandsMapper.queryByEntitys(demands);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return demandsMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) demandsMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return demandsMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 查询租船需求List
	 * @param demandsQuery
	 * @return
	 */
	@Override
	public List<Demands> queryByEntitysList(DemandsQuery demandsQuery) {
		return demandsMapper.queryByEntitysList(demandsQuery);
	}

	/**
	 * 查询租船需求List（泉炼）
	 * @param demandsQuery
	 * @return
	 */
	@Override
	public List<Demands> queryByEntitysQuanhuaList(DemandsQuery demandsQuery) {
		return demandsMapper.queryByEntitysQuanhuaList(demandsQuery);
	}

	/**
	 * 查询租船需求List-page
	 * @param demandsQuery
	 * @return
	 */
	@Override
	public PageInfoResult<Demands> queryByEntitysList(
			DemandsQuery demandsQuery, PageInfo pageInfo) {
		//PageUtils.page(pageInfo);
		List<Demands> rls = this.queryByEntitysList(demandsQuery);
		return new PageInfoResult(rls);
	}

	/**
	 * 查询租船需求List-page（泉炼）
	 * @param demandsQuery
	 * @return
	 */
	@Override
	public PageInfoResult<Demands> queryByEntitysQuanhuaList(
			DemandsQuery demandsQuery, PageInfo pageInfo) {
		//PageUtils.page(pageInfo);
		List<Demands> rls = this.queryByEntitysQuanhuaList(demandsQuery);
		return new PageInfoResult(rls);
	}

	@Override
	public List<Demands> getDemandsList(DemandsQuery wechatDemandsQuery) {
		return demandsMapper.getDemandsList(wechatDemandsQuery);
	}

	@Override
	public int checkAgreementIsExsit(Long orderID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderID);
		int rts = this.countRecords(map);
		return rts;
	}
}

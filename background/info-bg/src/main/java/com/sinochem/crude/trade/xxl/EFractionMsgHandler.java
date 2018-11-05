package com.sinochem.crude.trade.xxl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.domain.EFractionMsg;
import com.sinochem.crude.trade.info.domain.Oil;
import com.sinochem.crude.trade.info.service.EFractionMsgService;
import com.sinochem.crude.trade.info.service.OilService;
import com.sinochem.crude.trade.info.service.PriceIndexTemplateService;
import com.sinochem.crude.trade.info.util.DBUtil;
import com.sinochem.crude.trade.transport.remote.IDealPointsService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;

@Component
@JobHander("eFractionMsg")
public class EFractionMsgHandler  extends IJobHandler {
	@Autowired
	private EFractionMsgService eFractionMsgService;
	@Autowired
	private OilService oilService;
	@Autowired 
	PriceIndexTemplateService priceIndexTemplateService;
	@Autowired
	IDealPointsService iDealPointsService;
	@Autowired
	private DBUtil dBUtil;
	
	
	private static final Log log = LogFactory.getLog(EFractionMsgHandler.class);

	@Override
	public ReturnT<String> execute(String... params) throws Exception {
		log.info("eFractionMsgHandler   start ---->");
		List<Map<String, Object>> list = getRemoteData(fractionMsgSql(), null);
		if (CollectionUtils.isEmpty(list)) {
			throw new RuntimeException(" 远程数据为空");
		}
		for (Map<String, Object> items : list) {
			String crudeNameC = (String) items.get("CRUDE_NAME_C");
			String crudeNameE = (String) items.get("CRUDE_NAME_E");
			EFractionMsg eFractionMsg = new EFractionMsg();
			Oil oil = oilService.findByName(crudeNameE, crudeNameC);
			if(oil==null){
				oil = new Oil();
				oil.setUuid(UUID.randomUUID().toString().replace("-", ""));
				oil.setCrudeNameC(crudeNameC);
				oil.setCrudeNameE(crudeNameE);
				oil.setFullNameE((String) items.get("full_name"));
				oil.setOriginAreaId( (Long) items.get("origin_area_id"));
				oil.setOriginId((Long) items.get("ORIGIN_ID"));
				oil.setTransactionMode((String) items.get("TRANSACTION_MODE"));
				oil.setPriceBaseFlag((String)items.get("IS_PRIC_BASIS"));
				oil.setPriceMode((String)items.get("PRIC_MODE"));
				oil.setPriority((Integer) items.get("sort"));
				oil.setTonBucket(new BigDecimal(Double.parseDouble(items.get("ton_bucket").toString())));
				oilService.insertRecord(oil);
				eFractionMsg.setCrudeId(oil.getId());
			}else{
				eFractionMsg.setCrudeId(oil.getId());
			}
			// 置为最新
			String uuid = UUID.randomUUID().toString().replace("-", "");
			eFractionMsg.setUuid(uuid);
			eFractionMsg.setCrudeNameE((String)items.get("CRUDE_NAME_E"));
			eFractionMsg.setCrudeNameC((String)items.get("CRUDE_NAME_C"));
			eFractionMsg.setLightNaptha(new BigDecimal(Double.parseDouble(items.get("light_shi_nao_you").toString())));
			eFractionMsg.setMediumNaptha(new BigDecimal(Double.parseDouble(items.get("middle_shi_nao_you").toString())));
			eFractionMsg.setAviationFuel(new BigDecimal(Double.parseDouble(items.get("hang_mei").toString())));
			eFractionMsg.setGasOil(new BigDecimal(Double.parseDouble(items.get("wa_si_you").toString())));
			eFractionMsg.setVacuumGasOil(new BigDecimal(Double.parseDouble(items.get("jian_ya_wa_si_you").toString())));
			eFractionMsg.setHeavyFeliefOil(new BigDecimal(Double.parseDouble(items.get("zhong_zhi_jian_ya_you").toString())));
			eFractionMsg.setVacuumResidue(new BigDecimal(Double.parseDouble(items.get("jian_ya_zha_you").toString())));
			eFractionMsg.setCreatePerson((String)items.get("create_by"));
			eFractionMsg.setCreateDate(DateTimeUtils.toDate(items.get("create_date").toString(),"yyyy-MM-dd"));
			eFractionMsg.setSulphurContent(new BigDecimal(Double.parseDouble(items.get("sulfur").toString())));
			eFractionMsg.setAliveFlag(Constants.ALIEVE_FLAG);
			
			//判断数据是否存在   是 insert  否 update
			EFractionMsg eMsg = new EFractionMsg();
			eMsg.setCrudeNameE(eFractionMsg.getCrudeNameE());
			eMsg.setCrudeNameC(eFractionMsg.getCrudeNameC());
			List<EFractionMsg> eMsgList = eFractionMsgService.queryByEntitys(eMsg);
			
			if(CollectionUtils.isEmpty(eMsgList)){
				eFractionMsgService.insertRecord(eFractionMsg);
			}else {
				eFractionMsg.setId(eMsgList.get(0).getId());
				eFractionMsg.setModifyDate(new Date());
				eFractionMsgService.updateRecordById(eFractionMsg);
			}
		}
		
		log.error("eFractionMsgHandler   end ---->");
		return ReturnT.SUCCESS;
	}
	
	private List<Map<String, Object>> getRemoteData(String sql, Map<Integer, Object> conditionMap) {
		log.info("拉取 - 远程数据");
		return dBUtil.queryAll(sql.toString(), conditionMap);
	}

	private String fractionMsgSql(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT");
		sql.append(" s.CRUDE_NAME_E,");
		sql.append(" s.CRUDE_NAME_C,");
		sql.append(" s.ORIGIN_ID,");
		sql.append(" s.CRUDE_CATAGORY_ID,");
		sql.append(" s.TRANSACTION_MODE,");
		sql.append(" s.IS_PRIC_BASIS,");
		sql.append(" s.PRIC_MODE,");
		sql.append(" s.sort,");
		sql.append(" s.full_name,");
		sql.append(" s.origin_area_id,");
		sql.append(" s.ton_bucket,");
		sql.append(" p.light_shi_nao_you,");
		sql.append(" p.middle_shi_nao_you,");
		sql.append(" p.hang_mei,");
		sql.append(" p.wa_si_you,");
		sql.append(" p.jian_ya_wa_si_you,");
		sql.append(" p.zhong_zhi_jian_ya_you,");
		sql.append(" p.jian_ya_zha_you,");
		sql.append(" p.create_by,");
		sql.append(" p.create_date,");
		sql.append(" p.sulfur");
		sql.append(" FROM");
		sql.append(" pj_oil_fraction_msg p ");
		sql.append(" LEFT JOIN");
		sql.append(" sys_crude s");
		sql.append(" ON");
		sql.append(" p.crude_id = s.id");
		sql.append(" ORDER BY");
		sql.append(" s.CRUDE_NAME_E");
		return sql.toString();
		
	}
	
}

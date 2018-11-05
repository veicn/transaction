package com.sinochem.crude.trade.xxl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinochem.crude.trade.info.domain.InfoTiming;
import com.sinochem.crude.trade.info.service.InfoService;
import com.sinochem.crude.trade.info.service.InfoTimingService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;

@Component
@JobHander("infoTiming")
public class InfoTimingHandler extends  IJobHandler{

	@Autowired
	private InfoTimingService infoTimingService;
	@Autowired
	private InfoService infoService;
    private static final Log log = LogFactory.getLog(InfoTimingHandler.class);

	
	@Override
	public ReturnT<String> execute(String... params) throws Exception {
		log.error("infoTiming  start ---->");
		List<InfoTiming> list = infoTimingService.getTimingList(60L); // 60秒内
		for(InfoTiming item : list) {
			infoService.releaseInfo(item.getInfoId()); //发布资讯
			item.setStatus("10");
			infoTimingService.updateRecordById(item); //将定时任务置为 已处理
		}
		log.error("infoTiming  end ---->");
		return ReturnT.SUCCESS;
	}

}

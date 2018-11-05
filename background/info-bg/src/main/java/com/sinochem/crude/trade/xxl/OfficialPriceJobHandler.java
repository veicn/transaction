package com.sinochem.crude.trade.xxl;

import com.sinochem.crude.trade.info.job.RemoteJobService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@JobHander("officialPriceJob")
public class OfficialPriceJobHandler extends IJobHandler {
	
	@Autowired
	private RemoteJobService remoteJobService;

    private static final Log log = LogFactory.getLog(OfficialPriceJobHandler.class);

    @Override
    public ReturnT<String> execute(String... strings) throws Exception {
    	log.info("officialPriceJob  start ---->");
        remoteJobService.saveCrudeBasePrices(null);
        log.info("officialPriceJob  end ---->");
        return ReturnT.SUCCESS;
    }
}

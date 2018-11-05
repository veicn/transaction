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
@JobHander("futurePriceJob")
public class FuturePriceJobHandler extends IJobHandler {
	
	@Autowired
	private RemoteJobService remoteJobService;

    private static final Log log = LogFactory.getLog(FuturePriceJobHandler.class);

    @Override
    public ReturnT<String> execute(String... strings) throws Exception {
    	log.info("futurePriceJob  start ---->");
        remoteJobService.saveFutures(null);
        log.info("futurePriceJob  end ---->");
        return ReturnT.SUCCESS;
    }
}

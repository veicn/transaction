package com.sinochem.crude.trade.xxl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinochem.crude.trade.info.job.RemoteJobService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;

@Component
@JobHander("agioJob")
public class AgioJobHandler extends IJobHandler {
	
	@Autowired
	private RemoteJobService remoteJobService;

    private static final Log log = LogFactory.getLog(AgioJobHandler.class);

    @Override
    public ReturnT<String> execute(String... strings) throws Exception {
        log.info("agioJob  start ---->");
        remoteJobService.saveAgios(null);
        log.info("agioJob  end ---->");
        return ReturnT.SUCCESS;
    }
}

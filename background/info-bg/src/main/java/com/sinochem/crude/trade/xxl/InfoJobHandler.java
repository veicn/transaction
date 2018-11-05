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
@JobHander("infoJob")
public class InfoJobHandler extends IJobHandler {
	
	@Autowired
	private RemoteJobService remoteJobService;

    private static final Log log = LogFactory.getLog(InfoJobHandler.class);

    @Override
    public ReturnT<String> execute(String... strings) throws Exception {
    	log.info("infoJob  start ---->");
        remoteJobService.saveInfos(null);
        log.info("infoJob  end ---->");
        return ReturnT.SUCCESS;
    }
}

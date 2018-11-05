package com.sinochem.crude.trade.xxl;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
@JobHander("demo")
public class DemoJobHandler extends IJobHandler {

    private static final Log log = LogFactory.getLog(DemoJobHandler.class);

    @Override
    public ReturnT<String> execute(String... strings) throws Exception {
        log.info("XXL, Hello World.");
        return ReturnT.SUCCESS;
    }
}

package com.sinochem.crude.trade.xxl;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinochem.crude.trade.transport.service.ShipPlateService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;

@Component
@JobHander("shipPlateVaild")
public class ShipPlateVaildHandler extends IJobHandler {
	
	@Autowired
	private ShipPlateService shipPlateService;
	

    private static final Log log = LogFactory.getLog(ShipPlateVaildHandler.class);

    @Override
    public ReturnT<String> execute(String... strings) throws Exception {
        log.info("shipPlateVaild  start ---->"+new Date());
        shipPlateService.timingUpdateShipPlate();
        log.info("shipPlateVaild  end ---->"+new Date());
        return ReturnT.SUCCESS;
    }
}

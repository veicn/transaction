package com.sinochem.crude.trade.xxl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinochem.crude.trade.wechat.service.AccessTokenService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;

@Component
@JobHander("tokenDelJob")
public class AccessTokenDelJobHandler extends IJobHandler {

	@Autowired
	private AccessTokenService accessTokenService;

	private static final Log log = LogFactory.getLog(AccessTokenDelJobHandler.class);


	@Override
	public ReturnT<String> execute(String... arg0) throws Exception {
		log.info("tokenDelJob  start ---->");
		accessTokenService.deleteAccessToken();
		log.info("tokenDelJob  end ---->");
        return ReturnT.SUCCESS;
	}

}

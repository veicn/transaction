package com.sinochem.crude.trade.xxl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinochem.crude.trade.wechat.constant.AccessTokenUtil;
import com.sinochem.crude.trade.wechat.domain.AccessToken;
import com.sinochem.crude.trade.wechat.service.AccessTokenService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;

@Component
@JobHander("tokenGetJob")
public class AccessTokenGetJobHandler extends IJobHandler {

	@Autowired
	private AccessTokenService accessTokenService;

	private static final Log log = LogFactory.getLog(AccessTokenGetJobHandler.class);

	@Override
	public ReturnT<String> execute(String... arg0) throws Exception {
//		AccessToken accessToken = AccessTokenUtil.getAccessToken();
//		if(accessToken != null){
//			log.info("tokenGetJob  start ---->");
//			accessTokenService.saveAccessToken(accessToken);
//			log.info("tokenGetJob  end ---->");
//		}
        return ReturnT.SUCCESS;
	}

}

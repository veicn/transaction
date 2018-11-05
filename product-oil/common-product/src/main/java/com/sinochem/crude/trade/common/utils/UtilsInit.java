/*
 * Copyright (c) 2016. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2017/11/17. 帮助初始化工具类
 * @author Down
 *
 */
@Configuration
public class UtilsInit {
	private static final Log log = LogFactory.getLog(UtilsInit.class);

	@Autowired
	public void setSequenceManager(SequenceManager sequenceManager) {
        SequenceUtils.init(sequenceManager);
        log.info("Init  SequenceUtils's sequenceManager...");
	}
}

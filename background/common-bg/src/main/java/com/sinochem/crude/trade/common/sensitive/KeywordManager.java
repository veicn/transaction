/*
 * Copyright (c) 2016. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.common.sensitive;

import java.util.Collection;

/**
 * Created by HuangWj on 2016/11/1.
 */
public interface KeywordManager {

    String load();

    Collection<String> getKeywords();
}

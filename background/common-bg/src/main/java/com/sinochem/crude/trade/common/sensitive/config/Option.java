/*
 * Copyright (c) 2017. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.common.sensitive.config;

/**
 * Created by HuangWj on 2017/2/9.
 */
public interface Option {

    String DEFAULT_PREFIX = "<span style='color:red;'>";

    String DEFAULT_SUFFIX = "</span>";

    String DEFAULT_REPLACEMENT = "*";

    Option DECORATE = new DecorateOption(DEFAULT_PREFIX, DEFAULT_SUFFIX);

    Option REPLACE = new ReplaceOption(DEFAULT_REPLACEMENT);

    Option REMOVE = new RemoveOption();

    String getPrefix();

    String getReplacement();

    String getSuffix();

    Option prefix(String prefix);

    Option replacement(String replacement);

    Option suffix(String suffix);

    Option cloneOption();
}

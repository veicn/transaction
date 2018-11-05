/*
 * Copyright (c) 2017. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.common.sensitive.config;

/**
 * Created by HuangWj on 2017/2/9.
 */
class DecorateOption implements Option {

    private String prefix;
    private String suffix;

    DecorateOption(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    @Override
    public String getPrefix() {
        return prefix;
    }

    @Override
    public String getReplacement() {
        return null;
    }

    @Override
    public String getSuffix() {
        return suffix;
    }

    @Override
    public Option prefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    @Override
    public Option replacement(String replacement) {
        throw new UnsupportedOperationException("This is not a replacement option");
    }

    @Override
    public Option suffix(String suffix) {
        this.suffix = suffix;
        return this;
    }

    @Override
    public Option cloneOption() {
        return new DecorateOption(this.prefix, this.suffix);
    }
}

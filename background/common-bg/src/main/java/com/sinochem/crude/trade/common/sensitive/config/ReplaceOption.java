/*
 * Copyright (c) 2017. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.common.sensitive.config;

/**
 * Created by HuangWj on 2017/2/9.
 */
class ReplaceOption implements Option {

    private String replacement;

    ReplaceOption(String replacement) {
        this.replacement = replacement;
    }

    @Override
    public String getPrefix() {
        return null;
    }

    @Override
    public String getReplacement() {
        return replacement;
    }

    @Override
    public String getSuffix() {
        return null;
    }

    @Override
    public Option prefix(String prefix) {
        throw new UnsupportedOperationException("This is not a decorate option");
    }

    @Override
    public Option replacement(String replacement) {
        this.replacement = replacement;
        return this;
    }

    @Override
    public Option suffix(String suffix) {
        throw new UnsupportedOperationException("This is not a decorate option");
    }

    @Override
    public Option cloneOption() {
        return new ReplaceOption(this.replacement);
    }
}

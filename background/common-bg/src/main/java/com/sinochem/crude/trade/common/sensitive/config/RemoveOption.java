/*
 * Copyright (c) 2017. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.common.sensitive.config;

/**
 * Created by HuangWj on 2017/2/9.
 */
class RemoveOption implements Option {

    RemoveOption() {
    }

    @Override
    public String getPrefix() {
        return null;
    }

    @Override
    public String getReplacement() {
        return null;
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
        throw new UnsupportedOperationException("This is not a replacement option");
    }

    @Override
    public Option suffix(String suffix) {
        throw new UnsupportedOperationException("This is not a decorate option");
    }

    @Override
    public Option cloneOption() {
        return new RemoveOption();
    }
}

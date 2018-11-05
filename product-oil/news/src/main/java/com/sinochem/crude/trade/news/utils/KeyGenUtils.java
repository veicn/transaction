/*
 * Copyright (c) 2016. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.news.utils;

import java.util.UUID;

/**
 * Created by GHuang on 16/8/19.
 */
public final class KeyGenUtils {

    private KeyGenUtils() {}

    public static String newUuid(String separator) {
        return UUID.randomUUID().toString().replace("-", separator);
    }

    public static String newUuid() {
        return newUuid("");
    }
}

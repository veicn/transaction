/*
 * Copyright (c) 2017. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.common.sensitive.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by HuangWj on 2017/2/9. config object for keyword filter
 */
public class Config {

    private List<Option> defaultOptions = Lists.newArrayList();
    private List<Option> commonOptions = Lists.newArrayList();
    private Map<String, List<Option>> levelOptions = Maps.newHashMap();

    public void addDefaultOption(Option... options) {
        Collections.addAll(defaultOptions, cloneOptions(options));
    }

    public void addCommonOpetion(Option... options) {
        Collections.addAll(commonOptions, cloneOptions(options));
    }

    public void addOptionForLevel(String level, Option... options) {
        if (options.length > 0) {
            if (levelOptions.containsKey(level)) {
                Collections.addAll(levelOptions.get(level), cloneOptions(options));
            } else {
                levelOptions.put(level, Lists.newArrayList(cloneOptions(options)));
            }
        }
    }

    List<Option> getDefaultOptions() {
        return defaultOptions;
    }

    List<Option> getCommonOptions() {
        return commonOptions;
    }

    Map<String, List<Option>> getLevelOptions() {
        return levelOptions;
    }

    private Option[] cloneOptions(Option[] options) {
        Option[] dist = new Option[options.length];
        for (int i = 0; i < options.length; i++) {
            dist[i] = options[i].cloneOption();
        }

        return dist;
    }
}

/*
 * Copyright (c) 2016. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.common.sensitive;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.io.LineReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by HuangWj on 2016/11/17. Simple implement of KeywordManager, read keywords from keyword.dic
 */
public class SimpleKeywordManager extends AbstractKeywordManager {

    private Log log = LogFactory.getLog(KeywordManager.class);

    @Override
    public Collection<String> getKeywords() {
        Collection<String> keywords = Lists.newLinkedList();
        try {
            Enumeration<URL> resources
                    = Thread.currentThread().getContextClassLoader().getResources("keyword.dict");
            while (resources.hasMoreElements()) {
                URL element = resources.nextElement();
                InputStream is = element.openStream();
                LineReader reader = new LineReader(new InputStreamReader(is));
                String line;
                while ((line = reader.readLine()) != null) {
                    List<String> split = Splitter.on(',').omitEmptyStrings().trimResults().splitToList(line);
                    keywords.addAll(split);
                }
            }
        } catch (IOException e) {
            if (log.isErrorEnabled()) {
                log.error("Load keyword error.");
            }
        }
        return keywords;
    }
}

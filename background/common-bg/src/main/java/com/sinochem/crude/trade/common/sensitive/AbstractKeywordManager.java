/*
 * Copyright (c) 2016. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.common.sensitive;

import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Map;
import org.ahocorasick.trie.Trie;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by HuangWj on 2016/12/19. Core logic for sensitive words handler
 */
public abstract class AbstractKeywordManager implements KeywordManager {

    private static final Log log = LogFactory.getLog(AbstractKeywordManager.class);

    private Trie trie;

    private Map<String, Trie> priorityTries = Maps.newHashMap();

    private Trie.TrieBuilder builder;

    @Override
    public String load() {
        Trie.TrieBuilder localBuilder = Trie.builder();
        localBuilder.ignoreCase();
        Collection<String> keywords = getKeywords();
        Map<String, Trie.TrieBuilder> priorityBuilders = Maps.newHashMap();
        if (null != keywords) {
            for (String keyword : keywords) {
                if (keyword.startsWith("#")) {

                    // 增加优先级处理 - 2017-01-13 - HuangWj
                    String[] array = keyword.split("#");
                    if (array.length > 2) {
                        String priority = array[1];
                        String word = array[2];
                        if (priorityBuilders.containsKey(priority)) {
                            priorityBuilders.get(priority).addKeyword(word);
                        } else {
                            Trie.TrieBuilder pb = Trie.builder();
                            pb.addKeyword(word);
                            priorityBuilders.put(priority, pb);
                        }
                    }
                } else {
                    localBuilder.addKeyword(keyword);
                }
            }
        }
        // 按优先级分别生成Trie树
        for (Map.Entry<String, Trie.TrieBuilder> e : priorityBuilders.entrySet()) {
            if (null != e.getValue()) {
                priorityTries.put(e.getKey(), e.getValue().ignoreOverlaps().build());
            }
        }
        // 没有优先级的关键字进入默认Trie树
        trie = localBuilder.ignoreOverlaps().build();
        this.builder = localBuilder; // 向下兼容处理
        String result = "Loaded " + (null != keywords ? keywords.size() : 0) + " sensitive words. ";
        log.info(result);
        return result;
    }

    public Trie getTrie() {
        return trie;
    }

    public void setTrie(Trie trie) {
        this.trie = trie;
    }

    public Map<String, Trie> getPriorityTries() {
        return priorityTries;
    }

    public void setPriorityTries(Map<String, Trie> priorityTries) {
        this.priorityTries = priorityTries;
    }

    public Trie.TrieBuilder getBuilder() {
        return builder;
    }
}

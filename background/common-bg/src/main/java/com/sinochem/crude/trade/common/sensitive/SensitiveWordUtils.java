/*
 * Copyright (c) 2016. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.common.sensitive;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sinochem.crude.trade.common.sensitive.config.Config;
import com.sinochem.crude.trade.common.sensitive.config.FilterHandler;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by HuangWj on 2016/10/24. 敏感词过滤工具类
 */
public class SensitiveWordUtils {

    private static final Log log = LogFactory.getLog(SensitiveWordUtils.class);

    private static AbstractKeywordManager manager;

    private SensitiveWordUtils() {
    }

    private static AbstractKeywordManager getKeywordManager() {
        if (null == manager) {
            throw new IllegalStateException("KeywordUtils is not bean initiated.");
        }
        return manager;
    }

    static void init(AbstractKeywordManager manager) {
        SensitiveWordUtils.manager = manager;
        getKeywordManager().load();
    }

    /**
     * 敏感词替换为指定的字符
     *
     * @param src 需替换的字符串
     * @param replace 替换字符
     * @return 替换结果
     */
    public static String filter(String src, char replace) {
        StringBuilder sb = new StringBuilder(src);
        Map<String, Trie> priorityTries = getKeywordManager().getPriorityTries();
        if (null != priorityTries && priorityTries.size() > 0) {
            for (Map.Entry<String, Trie> e : priorityTries.entrySet()) {
                if (null != e.getValue()) {
                    sb = replaceEmits(e.getValue(), sb, replace);
                }
            }
        }
        if (null != getKeywordManager().getTrie()) {
            sb = replaceEmits(getKeywordManager().getTrie(), sb, replace);
        }
        return sb.toString();
    }

    /**
     * 按照提供的设置处理字符串敏感词
     *
     * @param src 需处理的字符串
     * @param config 设置对象
     * @return 处理结果
     */
    public static String filter(String src, Config config) {
        FilterHandler handler = new FilterHandler(config);
        return handler.handle(
                src, getKeywordManager().getPriorityTries(), getKeywordManager().getTrie());
    }

    /**
     * 判断是否包含敏感词
     *
     * @param src 待判断字符串
     * @return 判断结果
     */
    public static boolean isSensitive(String src) {
        Map<String, Trie> priorityTries = getKeywordManager().getPriorityTries();
        if (null != priorityTries && !priorityTries.isEmpty()) {
            for (Map.Entry<String, Trie> e : priorityTries.entrySet()) {
                if (null != e.getValue() && e.getValue().containsMatch(src)) {
                    return true;
                }
            }
        }
        return null != getKeywordManager().getTrie()
                && getKeywordManager().getTrie().containsMatch(src);
    }

    /**
     * 统计给出的字符串包含的敏感词级别
     *
     * @param src 给出的字符串
     * @return 敏感词级别列表
     */
    public static List<String> keyWordLevels(String src) {

        List<String> levels = Lists.newArrayListWithCapacity(5);

        Map<String, Trie> priorityTries = getKeywordManager().getPriorityTries();
        if (null != priorityTries && !priorityTries.isEmpty()) {
            for (Map.Entry<String, Trie> e : priorityTries.entrySet()) {
                if (null != e.getValue() && e.getValue().containsMatch(src)) {
                    levels.add(e.getKey());
                }
            }
        }

        return levels;
    }

    public static Set<String> countKeyWords(String src) {

        Set<String> words = Sets.newHashSet();

        Map<String, Trie> priorityTries = getKeywordManager().getPriorityTries();
        if (null != priorityTries && !priorityTries.isEmpty()) {
            for (Map.Entry<String, Trie> e : priorityTries.entrySet()) {
                if (null != e.getValue()) {
                    addToSet(words, e.getValue().parseText(src));
                }
            }
        }
        if (null != getKeywordManager().getTrie()) {
            Collection<Emit> emits = getKeywordManager().getTrie().parseText(src);
            addToSet(words, emits);
        }

        return words;

    }

    /**
     * 向词库中添加敏感词 不要使用，因为这个方法的效率非常低下
     *
     * @param keyword 敏感词
     * @deprecated 此方法只是为了测试以及向下兼容
     */
    @Deprecated
    public static void addKeyword(String keyword) {
        log.warn("This method is not recommended ot use.");
        getKeywordManager().getBuilder().addKeyword(keyword);
        getKeywordManager().setTrie(getKeywordManager().getBuilder().build());
    }

    private static StringBuilder replaceEmits(Trie trie, StringBuilder sb, char replace) {
        Collection<Emit> emits = trie.parseText(sb);
        for (Emit emit : emits) {
            int start = emit.getStart();
            int end = emit.getEnd();
            for (int i = start; i <= end; i++) {
                sb.setCharAt(i, replace);
            }
        }
        return sb;
    }

    private static void addToSet(Set<String> words, Collection<Emit> emits) {
        words.addAll(Collections2.transform(emits, new Function<Emit, String>() {
            @Override
            public String apply(Emit emit) {
                return emit.getKeyword();
            }
        }));
    }
}

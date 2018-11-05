/*
 * Copyright (c) 2017. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.common.sensitive.config;

import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.ahocorasick.trie.Token;
import org.ahocorasick.trie.Trie;

/**
 * Created by HuangWj on 2017/2/9. Filter handler for sensitive words.
 */
public class FilterHandler {

    private Config config;

    private Map<Class, TrieFunction> handlerMap = Maps.newHashMap();

    public FilterHandler(Config config) {
        this.config = config;
        handlerMap.put(DecorateOption.class, new TrieFunction() {
            @Override
            public StringBuilder apply(StringBuilder src, Trie trie, Option option) {
                Collection<Token> tokenize = trie.tokenize(src.toString());
                StringBuilder sb = new StringBuilder();
                for (Token token : tokenize) {
                    if (token.isMatch()) {
                        sb.append(option.getPrefix());
                    }
                    sb.append(token.getFragment());
                    if (token.isMatch()) {
                        sb.append(option.getSuffix());
                    }
                }
                return sb;
            }
        });
        handlerMap.put(ReplaceOption.class, new TrieFunction() {
            @Override
            public StringBuilder apply(StringBuilder src, Trie trie, Option option) {
                Collection<Token> tokenize = trie.tokenize(src.toString());
                StringBuilder sb = new StringBuilder();
                for (Token token : tokenize) {
                    if (token.isMatch()) {
                        sb.append(option.getReplacement());
                    } else {
                        sb.append(token.getFragment());
                    }
                }
                return sb;
            }
        });
        handlerMap.put(RemoveOption.class, new TrieFunction() {
            @Override
            public StringBuilder apply(StringBuilder src, Trie trie, Option option) {
                Collection<Token> tokenize = trie.tokenize(src.toString());
                StringBuilder sb = new StringBuilder();
                for (Token token : tokenize) {
                    if (!token.isMatch()) {
                        sb.append(token.getFragment());
                    }
                }
                return sb;
            }
        });
    }

    public String handle(String src, Map<String, Trie> priorityTries, Trie defaultTrie) {

        List<Option> commonOptions = config.getCommonOptions();
        List<Option> defaultOptions = config.getDefaultOptions();
        Map<String, List<Option>> levelOptions = config.getLevelOptions();

        StringBuilder sb = new StringBuilder(src);

        if (null != priorityTries && priorityTries.size() > 0) {
            for (Map.Entry<String, Trie> e : priorityTries.entrySet()) {
                if (null != e.getValue()) {
                    sb = optionHandle(sb, e.getValue(), commonOptions);
                    List<Option> levelOp = levelOptions.get(e.getKey());
                    sb = optionHandle(sb, e.getValue(), levelOp);
                }
            }
        }
        if (null != defaultTrie) {
            sb = optionHandle(sb, defaultTrie, defaultOptions);
        }

        return sb.toString();
    }

    private StringBuilder optionHandle(StringBuilder src, Trie trie, List<Option> options) {
        StringBuilder sb = src;
        if (null != options && !options.isEmpty()) {
            for (Option o : options) {
                TrieFunction handler = handlerMap.get(o.getClass());
                if (null != handler) {
                    sb = handler.apply(sb, trie, o);
                }
            }
        }
        return sb;
    }

    private interface TrieFunction {

        StringBuilder apply(StringBuilder src, Trie trie, Option option);
    }
}

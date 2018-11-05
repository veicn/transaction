/*
 * Copyright (c) 2016. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.common.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoader;

/**
 * Created by GHuang on 2016/11/7.
 */
public final class SequenceUtils {

    private static final Log log = LogFactory.getLog(SequenceUtils.class);
    private static SequenceManager sequenceManager;
    // record last tick.
    private static AtomicLong lastTick = new AtomicLong(-1L);

    private SequenceUtils() {
    }

    public static void init(SequenceManager sequenceManager) {
    	if (SequenceUtils.sequenceManager == null) {
    		SequenceUtils.sequenceManager = sequenceManager;
    	}
        log.info("load SequenceManager implement: " + sequenceManager.getClass().getName());
    }

    private static SequenceManager getSequenceManager() {
        if (null == sequenceManager) {
            throw new IllegalStateException("SequenceUtils is not bean initiated.");
        }
        return sequenceManager;
    }

    public static synchronized String nextSequence(String key) {
        long tick = getTicks();
        if (tick < lastTick.get()) {
            throw new RuntimeException("Clock moved backwards.");
        }
        long next;
        if (tick == lastTick.get()) {
            if (getSequenceManager().contains(key)) {
                next = getSequenceManager().increment(key) % 100;
                if (next == 0) {
                    tick = tillNextTick(lastTick.get());
                }
            } else {
                getSequenceManager().save(key, 1);
                next = 1L;
            }
        } else {
            getSequenceManager().save(key, 0);
            next = 0L;
        }
        lastTick.getAndSet(tick);
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
        NumberFormat nf = new DecimalFormat("#####");
        nf.setMinimumIntegerDigits(5);
        long seq = ((tick * 100 % 60000 + next + 5678) * 22137) % 100000;
        StringBuilder number = new StringBuilder(nf.format(seq));
        char digit3 = number.charAt(3);
        char digit4 = number.charAt(4);
        number.setLength(3);
        number.insert(1, digit3).insert(3, digit4);
        return key + sdf.format(new Date(tick * 100)) + number;
    }

    private static long tillNextTick(long lastTick) {
        long tick = getTicks();
        while (tick <= lastTick) {
            tick = getTicks();
        }
        return tick;
    }

    private static long getTicks() {
        return System.currentTimeMillis() / 100;
    }
}

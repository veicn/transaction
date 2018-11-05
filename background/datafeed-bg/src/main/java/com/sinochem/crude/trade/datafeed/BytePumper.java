package com.sinochem.crude.trade.datafeed;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * 数据帧处理器
 * 按照协议缓存和拆分数据帧
 *
 * @author HuangWj
 */
public class BytePumper {

    private Callback<String> callback;

    public static int BUCKET_SIZE = 1024;
    private int index = 0;
    private int offset = 0;

    private boolean frameStart = false;

    private List<byte[]> pool = Lists.newArrayList();

    public BytePumper() {
        pool.add(new byte[BUCKET_SIZE]);
    }

    /**
     * 设置数据帧处理器，当满足一个完整数据帧后会触发执行一次回调
     * @param callback 回调处理
     */
    public void setCallback(Callback<String> callback) {
        this.callback = callback;
    }

    private void push(byte b) {
        switch (b) {
            case 0: // frame start signal
                frameStart = true;
                break;
            case 1: // frame end signal
                if (frameStart) {
                    handlePool();
                    frameStart = false;
                }
                index = 0;
                offset = 0;
                pool = Lists.newArrayList();
                pool.add(new byte[BUCKET_SIZE]);
                break;
            default: // frame data
                if (frameStart) {
                    pool.get(index)[offset++] = b;
                    if (offset == BUCKET_SIZE) {
                        index++;
                        offset = 0;
                        pool.add(index, new byte[BUCKET_SIZE]);
                    }
                }
        }
    }

    private void handlePool() {
        if (index == 0) {
            callback.handle(new String(pool.get(index), 0, offset, Charset.forName("GBK")));
        } else {
            byte[] rst = Arrays.copyOf(pool.get(0), BUCKET_SIZE * index + offset);
            for (int i = 1; i < index; i++) {
                byte[] thisBytes = pool.get(i);
                System.arraycopy(thisBytes, 0, rst, BUCKET_SIZE * i, thisBytes.length);
            }
            byte[] lastBytes = pool.get(index);
            System.arraycopy(lastBytes, 0, rst, BUCKET_SIZE * index, offset);
            callback.handle(new String(rst, Charset.forName("GBK")));
        }
    }

    /**
     * 泵入数据
     * @param bytes 数据
     */
    public void push(byte[] bytes) {
        for (byte b : bytes) {
            push(b);
        }
    }
}

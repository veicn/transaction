/*
 * Copyright (c) 2016. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.common.aliyun.oss;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by HuangWj on 16/9/12. 文件管理器接口定义
 */
public interface FileManager {

    /**
     * 存储文件底层实现
     *
     * @param data 文件内容
     * @param group 文件所属组(bucket)
     * @param path 文件路径
     * @return 文件最终访问路径
     * @throws IOException 需要处理IO异常情况
     */
    String save(InputStream data, String group, String path) throws IOException;

    /**
     * 读取文件底层实现
     *
     * @param group 文件所属组(bucket)
     * @param path 文件路径
     * @return 文件最终访问路径
     * @throws IOException 需要处理IO异常情况
     */
    byte[] read(String group, String path) throws IOException;

    /**
     * 读取文件底层实现
     *
     * @param group 文件所属组(bucket)
     * @param path 文件路径
     * @param params 额外处理参数
     * @return 文件最终访问路径
     * @throws IOException 需要处理IO异常情况
     */
    byte[] read(String group, String path, String params) throws IOException;

    /**
     * 获取文件操作的临时权限
     *
     * @param group 文件所属组(bucket)
     * @return 临时权限认证数据
     */
    Map<String, String> getPolicy(String group, String dir);

    /**
     * 获取访问文件基础地址
     * @return 基础地址
     */
    String getBaseUrl(String group);

}

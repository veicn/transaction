package com.sinochem.crude.trade.common.utils;

import com.sinochem.crude.trade.common.aliyun.oss.FileManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public final class RemoteFileUtils {

    private RemoteFileUtils() { }

    private static FileManager fileManager;

    static void init(FileManager fileManager) {
        RemoteFileUtils.fileManager = fileManager;
    }

    public static String save(InputStream data, String group, String path) throws IOException {
        return fileManager.save(data, group, path);
    }

    public static byte[] read(String group, String path) throws IOException {
        return fileManager.read(group, path);
    }

    public static byte[] read(String group, String path, String params) throws IOException {
        return fileManager.read(group, path, params);
    }

    public static String getBaseUrl(String group) {
        return fileManager.getBaseUrl(group);
    }

    public static Map<String, String> getPolicy(String group, String dir) {
        return fileManager.getPolicy(group, dir);
    }
}

/*
 * Copyright (c) 2017. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.sinochem.crude.trade.common.exception.BizException;

/**
 * Created by GHuang on 2017/4/5.
 * 图片相关处理工具类
 */
public final class ImageFileUtils {

    private ImageFileUtils() {}

    /**
     * 获取图片的格式类型
     * @param data 图片二进制数据
     * @return 格式
     */
    public static ImageType getImageType(byte[] data) {
        if (data.length < 12) {
            return ImageType.NONE;
        }
        // Png test:
        if (data[1] == 'P' && data[2] == 'N' && data[3] == 'G') {
            return ImageType.PNG;
        }
        // Gif test:
        if (data[0] == 'G' && data[1] == 'I' && data[2] == 'F') {
            return ImageType.GIF;
        }
        // JPG test:
        if (data[6] == 'J' && data[7] == 'F' && data[8] == 'I'
                && data[9] == 'F') {
            return ImageType.JPEG;
        }
        // BMP test:
        if (data[0] == 'B' && data[1] == 'M') {
            return ImageType.BMP;
        }
        // WebP test:
        if (data[8] == 'W' && data[9] == 'E' && data[10] == 'B' && data[11] == 'P') {
            return ImageType.WEBP;
        }
        return ImageType.NONE;
    }

    /**
     * 获取图片的格式类型
     * @param inputStream 图片二进制流
     * @return 格式
     */
    public static ImageType getImageType(InputStream inputStream) {
        byte[] data = new byte[20];
        try {
            int read = inputStream.read(data);
            if (read < 12) {
                return ImageType.NONE;
            }
        } catch (IOException e) {
            throw new BizException("Image data inputStream read error.", e);
        }
        return getImageType(data);
    }

    /**
     * 获取图片的格式类型
     * @param file 图片文件
     * @return 格式
     */
    public static ImageType getImageType(File file) {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            return getImageType(inputStream);
        } catch (IOException e) {
            throw new BizException("Image file read error.", e);
        }
    }

    /**
     * 获取base64编码后的图片的格式类型
     * @param base64 base64编码后的图片数据
     * @return 格式
     */
    public static ImageType getImageTypeFromBase64(String base64) {
        byte[] data = EncryptUtils.base64DecodeBytes(base64);
        return getImageType(data);
    }

    /**
     * 图片格式枚举类，列举工具类支持的图片格式
     */
    public enum ImageType {

        PNG("png"), GIF("gif"), JPEG("jpeg"), BMP("bmp"), WEBP("webp"), NONE("none");

        private String name;

        ImageType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}

package com.sinochem.crude.trade.order.constart;

public interface UrlMapping {
    /****************************** 公用OSS上传下载 控制层 **************************************/
    String COMMON_OSS_GETPARAMS = "/common/oss/getParams.json";		/** OSS web直传时获取policy参数 */
    String COMMON_DOC_UPLOAD = "/common/doc/upload.json";		/** 上传文件公共方法（OSS） */
    String COMMON_DOC_DOWNLOAD = "/common/doc/download.htm";		/** 下载文件公共方法（OSS） */
}

package com.sinochem.crude.trade.shiprefueling.service;

import com.sinochem.it.b2b.common.exception.BizException;

import java.io.InputStream;
import java.util.List;

public interface OssService {

    String getPath(String suffix);

    String upload(InputStream inputStream) throws BizException;

    String upload(InputStream inputStream, String path) throws BizException;

    void deleteSingleObect(String path);

    void deleteBatchObect(List<String> paths);
}

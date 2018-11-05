package com.sinochem.crude.trade.shiprefueling.service;

import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shiprefueling.domain.po.Chments;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OrderUpAndDownService {
    void uploadFile(MultipartFile[] file, Long id, String businessType, String fileType, CurrentUser user) throws BizException;

    String uploadFile(MultipartFile file, Long id, String businessType, String fileType, CurrentUser user) throws BizException;

    List<Chments> queryExistsFile(Long id, String businessType);

    void deleteFileByAttachId(Long attachId) throws BizException;
}

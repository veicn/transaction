package com.sinochem.crude.trade.shiprefueling.service.impl;

import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shiprefueling.constants.Constants;
import com.sinochem.crude.trade.shiprefueling.controller.RIgnitionController;
import com.sinochem.crude.trade.shiprefueling.domain.po.Chments;
import com.sinochem.crude.trade.shiprefueling.model.vo.ChmentsVO;
import com.sinochem.crude.trade.shiprefueling.service.ChmentsService;
import com.sinochem.crude.trade.shiprefueling.service.OrderUpAndDownService;
import com.sinochem.it.b2b.common.exception.BizException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class OrderUpAndDownServiceImpl implements OrderUpAndDownService {
    public static final Log log = LogFactory.getLog(RIgnitionController.class);
    @Autowired
    private OssServiceImpl ossService;
    @Autowired
    private ChmentsService chmentsService;

    @Override
    public void uploadFile(MultipartFile[] files, Long id, String businessType, String fileType, CurrentUser user) throws BizException {
        for (MultipartFile file : files) {

        }
    }

    @Override
    public String uploadFile(MultipartFile file, Long id, String businessType, String fileType, CurrentUser user) throws BizException {
        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        ByteArrayInputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(file.getBytes());
        } catch (IOException e) {
            throw new BizException("上传文件流转换发生异常", e);
        }

        String path = ossService.getPath(suffix);

        String url = ossService.upload(inputStream, path);
        log.info("文件上传业务类型:" + businessType + ",业务主键:" + id + ",地址:" + url);

        // 保存文件信息
        ChmentsVO chments = new ChmentsVO();
        chments.setPath(url);
        chments.setId(id);
        chments.setType(businessType);
        chments.setVersion("1");
        chments.setFileType(fileType);
        chments.setName(file.getOriginalFilename());
        chments.setCreateUser(user.getMemberId());
        chmentsService.insertAttachmentsRecord(chments);

        return url;
    }

    /**
     * 船燃和船供订单上传单据需要判断是否已经上传
     * @param id
     * @param businessType
     * @return
     */
    @Override
    public List<Chments> queryExistsFile(Long id, String businessType) {
         return chmentsService.findByIdAndType(id, businessType);
    }

    @Override
    public void deleteFileByAttachId(Long attachId) throws BizException {
        Chments o = chmentsService.findByPrimaryKey(attachId);
        if(o != null) {
            o.setAliveFlag(Constants.DELE_FLAG);
            chmentsService.updateRecordById(o);

            // 删除oss文件
            ossService.deleteSingleObect(o.getPath());
        }
    }
}

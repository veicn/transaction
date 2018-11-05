package com.sinochem.crude.trade.transaction.remote.impl;

import com.sinochem.crude.trade.transaction.dao.BillCoreUploadFileMapper;
import com.sinochem.crude.trade.transaction.dao.ContractSheetMapper;
import com.sinochem.crude.trade.transaction.domain.po.ContractSheet;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.remote.BillCoreUploadRemoteService;
import com.sinochem.crude.trade.transaction.service.ContractSheetService;
import com.sinochem.crude.trade.transaction.vo.BillCoreRemoteUpload;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;


/**
 * Created by z1761 on 2018/5/7.
 */
@Service("billCoreUploadRemoteServiceImpl")
public class BillCoreUploadRemoteServiceImpl implements BillCoreUploadRemoteService {
    @Autowired
    private BillCoreUploadFileMapper billCoreUploadFileMapper;
    @Autowired
    private ContractSheetMapper contractSheetMapper;
    @Override
    public BillCoreRemoteUpload findbyOrderIdList(BillCoreRemoteUpload uploads) throws BizException {
        BillCoreRemoteUpload loads = billCoreUploadFileMapper.queryByOrderIdRemoteObject(uploads);

        return loads;
    }

    /**
     *  成功（1） 失败（0）
     * @param uplasd 文件名称，文件路径必传 订单Id
     * @return
     */
    @Override
    public Integer UpdateBillupload(BillCoreRemoteUpload uplasd) {
        BizException bizException = new BizException();
        Integer code = null;
        try {
            if (null != uplasd) {
                BillCoreRemoteUpload billCoreRemoteUpload = billCoreUploadFileMapper.queryByOrderIdRemoteObject(uplasd);
                //如果存在 走更新方法， 否则 insert
                if ( null != billCoreRemoteUpload) {
                    code = billCoreUploadFileMapper.RemoteUpdate(uplasd);
                } else {
                    uplasd.setDocumentFileUuid(UUID.randomUUID().toString());
                    code = billCoreUploadFileMapper.RemoteInsertUpload(uplasd);
                }
            }

            if (code != null && code>0) {

            } else {
                bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
                throw bizException;
            }
        } catch (BizException e) {

            code = 0;
        }
        return code;
    }


    /**删除
     * 成功（1） 失败（0）
     * @param uplasd
     * @return
     */
    @Override
    public Integer deleteRemoteUpload(BillCoreRemoteUpload uplasd) {
        Integer  code= null;
        try {
            if (null == uplasd ) {
                throw  new BizException();
            }
            HashMap<String, Object> stringObjectHashMap = new HashMap<>();
            stringObjectHashMap.put("orderId",uplasd.getOrderId());
            stringObjectHashMap.put("fileTypeCode",uplasd.getFileTypeCode());
             code= billCoreUploadFileMapper.deleteRemoteUpload(stringObjectHashMap);
            code=1;
        } catch (BizException e) {
            code =0;
        }

        return code;
    }
}

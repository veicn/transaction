package com.sinochem.crude.trade.transaction.remote;


//import com.runyi.ryplat.api.commons.SimplePageInfo;

import com.sinochem.crude.trade.transaction.vo.BillCoreRemoteUpload;
import com.sinochem.it.b2b.common.exception.BizException;

public interface BillCoreUploadRemoteService {

    /**
     *  查询票据中心列表
     * @param
     * @param uploads
     * @return
     */
    BillCoreRemoteUpload findbyOrderIdList(BillCoreRemoteUpload uploads) throws BizException;

    /**
     * 修改票据中心
     * @param uplasd
     * @return
     */
    Integer UpdateBillupload(BillCoreRemoteUpload uplasd);

    /**
     * 删除
     * @param uplasd
     * @return
     */
    Integer deleteRemoteUpload(BillCoreRemoteUpload uplasd);

}

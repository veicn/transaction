package com.sinochem.crude.trade.transaction.constant;

/**
 * 标识的常量类
 * @author Yichen Zhao
 * date: 20180227
 */
public class Mark {

    public static final int RESULT_DATA_SUCCESS = 0;
    public static final int RESULT_DATA_ERROR = 9999;
    /**
     * 新增成功
     */
    public static final String SAVE_TRU = "Deal Recap Created Successfully！";

    /**
     * 发布或保存成功
     */
    public static final String POST_OR_SAVE_SUCCESSFULLY = "Submitted Successfully";
    /**
     *
     *
     * 编辑成功
     */
    public static final String EDIT_SUCCESSFULLY = "Updated Successfully";

    /**
     *报价成功
     */
    public static final String SUBMIT_SUCCESSFULLY = "Submitted Successfully";

    /**
     *删除成功
     */
    public static final String DELETE_SUCCESSFULLY = "Delete Successfully";

    /**
     *下架成功
     */
    public static final String WITHDRAW_SUCCESSFULLY = "Withdraw Successfully";

    /**
     *上架成功
     */
    public static final String POST_SUCCESSFULLY = "Post Successfully";

    /**
     *流标成功
     */
    public static final String CANCEL_SUCCESSFULLY = "Cancel Successfully";

    /**
     * 该商品暂时不能报价
     */
    public static final String UNABLE_TO_QUOTE = "Fail To Quote";

   // 01 02 03 06 09 单个上传 覆盖更新
  // 04 05 07 08 00  多个上传 insert
    /**
     * 票据中心标识定义 deal recap
     */
    public static final String CORE_FLAG_00 = "00";
    /**
     * 票据中心标识定义 vessel acceptance
     */
    public static final String CORE_FLAG_01 = "01";
    /**
     * 票据中心标识定义 DI
     */
    public static final String CORE_FLAG_02 = "02";
    /**
     * 票据中心标识定义 stowage plan
     */
    public static final String CORE_FLAG_03 = "03";
    /**
     * 票据中心标识定义 Customs declaration
     */
    public static final String CORE_FLAG_04 = "04";
    /**
     * 票据中心标识定义 inspection report
     */
    public static final String CORE_FLAG_05 = "05";
    /**
     * 票据中心标识定义 bill of lading
     */
    public static final String CORE_FLAG_06 = "06";
    /**
     * 票据中心标识定义 loading cargo documents
     */
    public static final String CORE_FLAG_07 = "07";

    /**
     * 票据中心标识定义 discharge cargo documents
     */
    public static final String CORE_FLAG_08 = "08";

    /**
     * 票据中心标识定义 Q88
     */
    public static final String CORE_FLAG_09 = "09";

    /**
     * 票据中心标识定义 temp_invoice 临时发票  TempInvoice
     */
    public static final String TEMP_INVOICE = "10";

    /**
     * 票据中心标识定义 settle_invoice 结算发票 SettleInvoice
     */
    public static final String SETTLE_INVOICE = "11";


    /**
     * 票据中心标识定义 CONTRACT_FILE  合同
     */
    public static final String CONTRACT_FILE = "12";

    /**
     * 外贸合同号
     */
    public static final String PURCHASE_CONTRACT_NO = "13";
    /**
     * 代理协议号
     */
    public static final String AGENCY_AGREEMENT_NO = "14";

    /* 实体类的aliveFlag */
    public static final String ALIVE = "1"; //现存
    public static final String DELETED = "0"; //已删除
    /**
     * 返回标识
     */
    public static final int STATRU_SUCCESS = 0; //现存
     /**
     * 上传成功
     */
    public static final String UPLOAD_SUCCESS = "Upload Successfully"; //
    /**
     * 上传失败
     */
    public static final String UPLOAD_ERROR = "Upload Error"; //
}

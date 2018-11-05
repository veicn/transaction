package com.sinochem.crude.trade.member.contact;

/**
 * Created by wgp on 2018/7/25.
 */
public class UdbUrlMapping {

    /*账户管理模块接口地址*/
    public static final String ACCOUNT_URL = "/api/v1/accounts";


    //添加企业银行信息
    public static final String ENTERPRISE_BANK_ADD ="/api/v1/banks/entpBank";
    //修改企业银行信息
    public static final String ENTERPRISE_BANK_UPDATE ="/api/v1/banks/entpBank";



      /*企业相关接口*/
    /*企业信息添加接口  POST请求*/
    public static final String ENTERPRISE_ADD = "/api/v1/enterprises";
    //更新修改企业信息  PUT
    public static final String ENTERPRISE_UPDATE = "/api/v1/enterprises";
    //获取单个企业信息  GET
    public static final String ENTERPRISE_DETAILS = "/api/v1/enterprises/datails";
    //获取账户关联的企业列表  GET
    public static final String ENTERPRISE_ACCOUNTS_DETAILS = "/api/v1/accounts/enterprises";


    //修改企业资质的接口 POST
    public static final String ENTERPRISE_TYPE_UPDATE = "/api/v1/enterprises/type";
    //删除企业资质的接口  delete
    public static final String ENTERPRISE_TYPE_DELETE = "/api/v1/enterprises/type";

    //查询企业接口  GET
    public static final String ENTERPRISE_TYPE_GET="/api/v1/enterprises/type";






      /*企业账号的相关接口*/
    //添加/修改 企业管理员  PATCH
    public static final String ENTERPRISE_ADMIN = "/api/v1/entpAccounts/admin";

    public static final String OSS_POLICY = "/api/v1/oss/policy";



    /*企业联系人相关接口*/
    //删除企业联系人
    //添加/更新企业联系人
    public static final String ENTERPRISE_CONTACT_ADD = "/api/v1/contacts/entpContact";
    public static final String ENTERPRISE_CONTACT_UPDATE = "/api/v1/contacts/entpContact";
    public static final String ENTERPRISE_CONTACT_SELECT = "/api/v1/contacts/entpContact";
    private static final String ENTERPRISE_CONTACT_DELETE = "/api/v1/entpAccounts/admin";


    /*个人中心用户 控制层*/
    //添加个人信息
    public static final String PERSON="/api/v1/users";




}

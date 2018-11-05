package com.sinochem.crude.trade.member.contact;

@SuppressWarnings({ "all" })
public interface UrlMapping {
	/****************************** 移动端接口**************************************/
	String API_FILL = "/api/fill";		/** 注册用户 */
	String API_PERSON = "/api/person.json";		/** 获取个人信息 */
	String API_ENTERPRISE = "/api/enterprise.json";		/** 获取企业信息 */
	String API_CHANGEINFO = "/api/changeInfo";		/** 修改个人信息 */
	String API_CURRENTUSER = "/api/currentUser";		/** 修改登录信息 */


	/****************************** 单点登录控制层 **************************************/
	String SSO_ASO_REDIRECTTGS = "/sso/aso/redirectTgs.htm";		/** tgs 前端请求路径 需要传入请求系统的编码 */
	String SSO_ASO_TVS = "/sso/aso/tvs.htm";		/** tvs */

	/****************************** 个人中心用户 控制层 **************************************/
	String CENTER_CERTIFICATION = "/center/certification";		/** 企业认证 */
	String CENTER_FAST_REGISTER = "/center/fast_register";		/** 企业快速注册 */
	String CENTER_MY = "/center/my";		/** 企业详细信息 */
	String CENTER_TOADD = "/center/toadd";		/** 企业添加 */
	String CENTER_PERSONTOADD = "/center/personToadd";		/** 人员添加 */
	String CENTER_LISTMH = "/center/listMH";		/** 前台列表信息接口 */
	String CENTER_ENTERPRISEBYID = "/center/enterpriseById";		/** 根据前台id回显的数据 */
	String CENTER_INFO = "/center/info";		/** 企业，个人详情 */
	String CENTER_ACCOUNTDETAIL = "/center/accountDetail";		/** 账户详情 */
	String CENTER_HISTORYDATASTATISTICS = "/center/historyDataStatistics";		/** 历史数据 */
	String CENTER_ENTERPRISE_UPDATE = "/center/enterprise_update";		/** 企业修改页面 */

	/****************************** 公用模块 控制层 **************************************/
	String MOSKEN_CENTER_HEAD = "/mosken/center/head";		/** 个人中心头部 */
	String MOSKEN_CENTER_MENUS = "/mosken/center/menus";		/** 个人中心左侧菜单 */
	String CENTERCONTAIN_ENTERPRISELIST = "/centercontain/enterpriseList.htm";		/** 获取企业列表 */
	String CENTERCONTAIN_ENTERPRISELIST_JSON = "/centercontain/enterpriseList.json";		/** 获取企业列表 */
	String CENTERCONTAIN_EPLISTBYNAME_JSON = "/centercontain/epListByName.json";		/** 按名称模糊查询企业 */
	String CONTAIN_PERSONINFO = "/contain/personInfo.htm";		/** 获取个人信息 */
	String CONTAIN_ENTERPRISEINFO = "/contain/enterpriseInfo.htm";		/** 获取企业信息 */
	String CENTER_CHANGETHEME = "/center/changeTheme.htm";		/** 修改主题 */
	String MOSKEN_FINDPWD_HTMLHEAD = "/mosken/findpwd/htmlhead.htm";		/** 找回密码头部 */

	/****************************** 公用OSS上传下载 控制层 **************************************/
	String COMMON_OSS_GETPARAMS = "/common/oss/getParams.json";		/** OSS web直传时获取policy参数 */
	String COMMON_DOC_UPLOAD = "/common/doc/upload.json";		/** 上传文件公共方法（OSS） */
	String COMMON_DOC_DOWNLOAD = "/common/doc/download.htm";		/** 下载文件公共方法（OSS） */
	String UDB_FILE_UPLOAD = "/udb/file/upload.json";
	/****************************** 企业相关 控制层 **************************************/
	String ENTERPRISE_EDIT_GET = "/enterprise/edit";		/** 企业修改 get请求 */
	String ENTERPRISE_EDIT_POST = "/enterprise/edit";		/** 企业修改 post请求 */
	String ENTERPRISE_REGISTER = "/enterprise/register";		/** 企业注册 */
	String ENTERPRISE_FILL = "/enterprise/fill";		/** 企业编辑 */
	String ENTERPRISE_NAME = "/enterprise/name";		/** 根据环境获取企业名称 */

	/****************************** 企业扩展信息 控制层 **************************************/
	String DELETEENTERPRISECRUDE = "/deleteEnterpriseCrude";		/** 刪除企业扩展信息 */
	String CENTER_ENTERPRISECRUDE_GET = "/center/enterpriseCrude";		/** 查询企业扩展信息 */
	String CENTER_ENTERPRISECRUDE_POST = "/center/enterpriseCrude";		/** 保存企业扩展信息 */

	/****************************** 登录跳转 控制层 **************************************/
	String LOGINEX = "/loginEx";		/** 登录 */

	/****************************** 用户相关使用的controller 控制层 **************************************/
	String CENTER_MEMBER_ACCOUNTDETAIL = "/center/member/accountDetail";		/**  */
	String CENTER_MEMBER_ENTERPRISEFILL = "/center/member/enterpriseFill";		/** 企业信息补充页面 */
	String CENTER_MEMBER_ENTERPRISEINFO = "/center/member/enterpriseInfo";		/** 企业信息补充页面 */
	String CENTER_MEMBER_PERSONFILL = "/center/member/personFill";		/** 个人信息补充页面 */
	String CENTER_MEMBER_PROTOCOL = "/center/member/protocol.htm";		/** 注册协议静态页面 */
	String CENTER_MEMBER_ENTERPRISEPROTOCOL = "/center/member/enterpriseProtocol.htm";		/** 企业注册协议静态页面 */

	/****************************** OM 外系统账号关联 控制层 **************************************/
	String OM_EXOSYSTEMACC_LIST = "/om/exosystemAcc/list.htm";		/** 列表 */
	String OM_EXOSYSTEMACC_EDIT = "/om/exosystemAcc/edit.htm";		/** 修改 */
	String OM_EXOSYSTEMACC_SAVE = "/om/exosystemAcc/save.json";		/** 保存 */
	String OM_EXOSYSTEMACC_DELETE = "/om/exosystemAcc/delete.json";		/** 删除 */

	/****************************** OM 个人信息 控制层 **************************************/
	String OM_PERSON_LIST = "/om/person/list";		/** 列表 */
	String PERSON_MAIN_LIST = "/om/person/main_list";		/** 查看主账号 */
	String OM_PERSON_TOADD = "/om/person/toadd";		/** 人员添加 */
	String OM_PERSON_DELETEPERSON = "/om/person/deleteperson";		/** 刪除 */
	String OM_PERSON_TOUPDATE = "/om/person/toupdate";		/** 修改回显 */
	String OM_PERSON_UPDATE = "/om/person/update.htm";		/** 修改 */
	String OM_CHECK_MEMBER = "/om/person/change_member.json";		/** 完善资料 */

	/****************************** OM 合作伙伴 控制层 **************************************/
	String OM_PARTNER_LIST = "/om/partner/list"; /**列表**/
	String OM_PARTNER_UPDATE = "/om/partner/update"; /**修改页面**/
	String OM_PARTNER_SAVE = "/om/partner/save.json"; /**保存**/
	String OM_PARTNER_DELETE = "/om/partner/delete.json";/**删除**/

	/****************************** OM 消息推送 控制层 **************************************/
	String OM_MESSAGE_LOG_LIST = "/om/messageLog/list"; /**列表**/
	String OM_MESSAGE_LOG_ADD = "/om/messageLog/add"; /**新增页面**/
	String OM_MESSAGE_LOG_SAVE = "/om/messageLog/save.json"; /**新增接口**/
	String OM_MESSAGE_LOG_DETAIL = "/om/messageLog/detail"; /**详情接口**/
	String OM_MESSAGE_LOG_START_JOB= "/om/messageLog/start_job.json"; /**启动任务接口**/
	String OM_MESSAGE_LOG_IMG_UPLOAD= "/om/messageLog/img_upload.json"; /**富文本编辑器图片上传地址**/
	String OM_MESSAGE_LOG_RESEND= "/om/messageLog/resend.json"; /**重新发送**/
	String OM_MESSAGE_LOG_MEMBER_FITTER = "/om/messageLog/member_filter.json"; /**根据企业资质或者角色查询member**/

	/****************************** OM 企业信息 控制层 **************************************/
	String OM_ENTERPRISE_LISTMH = "/om/enterprise/listMH";		/** 前台列表信息接口 */
	String OM_ENTERPRISE_LIST = "/om/enterprise/list";		/** 列表信息 */
	String OM_ENTERPRISE_DELETEENTERPRISE = "/om/enterprise/deleteenterprise";		/** 刪除 */
	String OM_ENTERPRISE_TOUPDATE = "/om/enterprise/toupdate";		/** 修改回显 */
	String OM_ENTERPRISE_UPDATE = "/om/enterprise/update";		/** 修改 */
	String OM_ENTERPRISE_UPDATECRUDEINIT = "/om/enterprise/updateCrudeInit";		/** 企业补充页面 */
	String OM_ENTERPRISE_SAVECRUDE = "/om/enterprise/saveCrude";		/** 保存企业补充信息 */

	//OM正在使用
	String OM_ENTERPRISE_CRUDELIST = "/om/enterprise/crudeList";		/** 企业信息列表*/
	String OM_ENTERPRISE_UPDATECRUDE = "/om/enterprise/updateCrude";		/** 企业修改页面 */
	String OM_ENTERPRISE_SAVEENTERPRISE = "/om/enterprise/saveEnterprise";		/** 保存企业信息 */
	String OM_ENTERPRISE_AGENTREGISTER = "/om/enterprise/agentRegister";		/** 企业代注册页面 */
	String OM_ENTERPRISE_SAVEAGENTREGISTER = "/om/enterprise/saveAgentRegister";		/** 企业代注册保存 */


	/****************************** OM 企业资质审核 控制层 **************************************/
	String OM_SUALIFICATION_LIST = "/om/sualification/list";		/** 列表信息 */
	String OM_SUALIFICATION_AUDIT = "/om/sualification/audit";		/** 审核 */
	String OM_SUALIFICATION_AUDITSHOW = "/om/sualification/auditShow";		/** 审核页面 */
	String OM_SUALIFICATION_ADDENTERPRISESAUDIT = "/om/sualification/addEnterprisesAudit.json";		/** 添加企业审核信息 */
	String OM_SUALIFICATION_AUDIT_REJECTED = "/om/sualification/audit/rejected";		/** 审核驳回 */

	/****************************** APP版本管理 控制层 **************************************/
	String OM_APP_LIST = "/om/app/list.htm";		/** 查询所有列表 */
	String OM_APP_SELECTBYPRIMARYKEY = "/om/app/selectByPrimaryKey";		/** 获取版本详情 */
	String OM_APP_EDIT = "/om/app/edit";		/** 修改版本信息页面 */
	String OM_APP_UPDATE = "/om/app/update.json";		/** 修改版本信息 */
	String OM_APP_SAVE = "/om/app/save.json";		/** 保存版本信息 */
	String OM_APP_DELETE = "/om/app/delete.json";		/** 删除版本信息 */

	String OM_APP_GETNEWVERSION = "/om/app/getNewVersion.json";		/** 获取最新的版本信息 */


	/****************************** 有奖征集 控制层 **************************************/
	String OM_PRIZE_LIST = "/om/prize/list.htm";		/** 列表信息 */
	String OM_PRIZE_DELETE = "/om/prize/delete.json";		/** 根据 id删除 */

	String TRADEHALL_RC_PRIZE = "/tradeHall/rc_prize";		/** 有奖征集前台页面 */
	String PRIZE_SAVE = "/prize/save.json";		/** 有奖征集页面的保存方法 */

	/****************************** 个人详细信息 控制层 **************************************/
	String PERSON_PERSONDETAIL = "/person/personDetail";		/** 个人详细信息 */
	String PERSON_EDIT_GET = "/person/edit";		/** 个人信息修改页面 */
	String PERSON_EDIT_POST = "/person/edit";		/** 个人信息修改 */
	String PERSON_FILL = "/person/fill";		/** 个人信息编辑 */

	/****************************** 企业信息补充 控制层 **************************************/
	String REGISTER_ENTERPRISE = "/register/enterprise";		/** 企业信息补充页面 */

	/****************************** 企业资质 控制层 **************************************/
	String CENTER_SUALIFICATIONLIST = "/center/sualificationList";		/** 资质列表 */
	String CENTER_POTRERILLOSINFO = "/center/potrerillosInfo";		/** 开通炼厂资质页面 */
	String CENTER_TRADINGCOMPANYINFO = "/center/tradingCompanyInfo";		/** 开通贸易公司资质页面 */
	String CENTER_PROVIDERINFO = "/center/providerInfo";		/** 开通供应商资质页面 */
	String CENTER_CUSTOMERINFO = "/center/customerInfo";		/** 开通客户资质页面 */
	String CENTER_SHIPAGENCYINFO = "/center/shipAgencyInfo";		/** 开通船舶资质页面 */
	String CENTER_POTRERILLOSINFO_SAVE = "/center/potrerillosInfo";		/** 开通炼厂资质保存 */
	String CENTER_TRADINGCOMPANYINFO_SAVE = "/center/tradingCompanyInfo";		/** 贸易公司资质保存 */
	String CENTER_PROVIDERINFO_SAVE = "/center/providerInfo";		/** 供应商资质保存 */
	String CENTER_CUSTOMERINFO_SAVE = "/center/customerInfo";		/** 客户资质保存 */
	String CENTER_SHIPAGENCYINFO_SAVE = "/center/shipAgencyInfo";		/** 船舶资质保存 */
	String CENTER_REQUEST_SUALIFICATION = "/center/request_sualification";		/** 直接开通资质,不需要录入资质材料,直接使用企业资料申请 */
	String CENTER_CREDENTIALS_DELETE = "/center/credentials/delete";		/** 企业资质删除 */

	/****************************** 合作伙伴 表示层 **************************************/
	String PARTNER_LIST = "/tradeHall/partner/index_partner"; /**列表**/

	/****************************** 名片移动端接口 **************************************/
	String BUSINESSCARD = "/businessCard.json";/**获取个人名片*/
	String BUSINESSCARD_UPDATE = "/businessCard/update.json";/**编辑个人名片*/
	String BUSINESSCARD_SEND = "/businessCard/send.json";/**发送名片申请名片*/
	String BUSINESSCARDAPPLY_LAUNCH_LIST = "/businessCardApply/launch/list.json";/**发起的名片申请列表*/
	String BUSINESSCARDAPPLY_RECEIVED_LIST = "/businessCardApply/received/list.json";/**收到的名片申请列表*/
	String BUSINESSCARDAPPLY_CONFIRM = "/businessCardApply/confirm.json";/**确认名片申请（忽略或者同意）*/
	String BUSINESSCARDAPPLY_SEARCH = "/businessCardApply/search.json";/**搜索名片*/
	String BUSINESSCARDAPPLY_MY_SEARCH = "/businessCardApply/my/search.json";/**搜索我收藏的名片*/
	String BUSINESSCARDAPPLY_MY_DELETE = "/businessCardApply/my/delete.json";/**删除我收藏的名片*/


}

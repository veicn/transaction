-- 更新企业资质
update  member_credentials mc set mc.credentials_code=99 where mc.credentials_code =6;
-- 更换原船东资质为新的船东船代等
insert into member_credentials(member_id,credentials_code,audit) select member_id,4,mc.audit FROM member_credentials mc where mc.credentials_code =5;
insert into member_credentials(member_id,credentials_code,audit) select member_id,6,mc.audit FROM member_credentials mc where mc.credentials_code =5;
insert into member_credentials(member_id,credentials_code,audit) select member_id,7,mc.audit FROM member_credentials mc where mc.credentials_code =5;

-- 替换所有的角色列表
DELETE r from roles r;
INSERT INTO `roles` VALUES (11, 1, 1, 'enter_master', '企业负责人', '2017-12-9 09:22:21', 0, '2017-12-9 09:22:21', 0);
INSERT INTO `roles` VALUES (15, 1, 1, 'sales_trader', '销售交易员 ', '2017-12-9 09:22:01', 99999999, '2017-12-9 09:22:01', 0);
INSERT INTO `roles` VALUES (16, 1, 1, 'buy_trader', '采购交易员 ', '2017-12-9 09:22:01', 99999999, '2017-12-9 09:22:01', 0);
INSERT INTO `roles` VALUES (17, 1, 1, 'trade_executor', '执行人员', '2017-12-9 09:22:01', 99999999, '2017-12-9 09:22:01', 0);
INSERT INTO `roles` VALUES (18, 1, 1, 'optimization', '优化人员', '2017-12-9 09:22:01', 99999999, '2017-12-9 09:22:01', 0);
INSERT INTO `roles` VALUES (19, 1, 1, 'inspection', '商检', '2017-12-9 09:22:01', 99999999, '2017-12-9 09:22:01', 0);
INSERT INTO `roles` VALUES (20, 1, 1, 'ship_owner', '船东', '2017-12-9 09:22:01', 99999999, '2017-12-9 09:22:01', 0);
INSERT INTO `roles` VALUES (21, 1, 1, 'ship_broker', '经纪人', '2017-12-9 09:22:01', 99999999, '2017-12-9 09:22:01', 0);
INSERT INTO `roles` VALUES (22, 1, 1, 'depa_port', '装港船代', '2017-12-9 09:22:01', 99999999, '2017-12-9 09:22:01', 0);
INSERT INTO `roles` VALUES (23, 1, 1, 'arriv_port', '卸港船代', '2017-12-9 09:22:01', 99999999, '2017-12-9 09:22:01', 0);
INSERT INTO `roles` VALUES (24, 1, 1, 'ship_trader', '租船代理(交易）', '2017-12-9 09:22:01', 99999999, '2017-12-9 09:22:01', 0);
INSERT INTO `roles` VALUES (25, 1, 1, 'ship_executor', '租船代理(执行)', '2017-12-9 09:22:01', 99999999, '2017-12-9 09:22:01', 0);
INSERT INTO `roles` VALUES (26, 1, 1, 'enter_admin', '企业管理员', '2017-12-9 09:22:01', 99999999, '2017-12-9 09:22:01', 0);
INSERT INTO `roles` VALUES (27, 0, 1, 'admin', '系统管理员', '2017-12-9 09:22:01', 99999999, '2017-12-9 09:22:01', 0);
INSERT INTO `roles` VALUES (28, 0, 1, 'info_oper', '内容运维人员', '2017-12-9 09:22:01', 99999999, '2017-12-9 09:22:01', 0);

-- 删除原来的交易员
DELETE ar FROM  accounts_roles ar;
INSERT into accounts_roles(account_id,role) VALUES(1000356,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000356,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000356,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(1000358,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000358,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000358,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(1000359,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000359,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000359,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(1000360,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000360,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000360,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(1000361,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000361,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000361,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(1000362,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000362,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000362,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(1000363,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000363,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000363,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(1000364,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000364,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000364,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(1000365,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000365,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000365,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(1000367,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000367,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000367,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(1000368,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000368,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000368,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(1000369,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000369,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000369,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(1000370,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000370,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000370,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(1000371,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000371,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000371,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(1000374,'ship_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000376,'ship_trader');
INSERT into accounts_roles(account_id,role) VALUES(1000379,'ship_executor');
INSERT into accounts_roles(account_id,role) VALUES(1000380,'ship_executor');
INSERT into accounts_roles(account_id,role) VALUES(1000384,'ship_executor');
INSERT into accounts_roles(account_id,role) VALUES(1000385,'ship_executor');
INSERT into accounts_roles(account_id,role) VALUES(1000386,'ship_executor');
INSERT into accounts_roles(account_id,role) VALUES(100000005,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000005,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000005,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(100000006,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000006,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000006,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(100000012,'enter_admin ');
INSERT into accounts_roles(account_id,role) VALUES(100000012,'ship_executor');
INSERT into accounts_roles(account_id,role) VALUES(100000022,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000022,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000022,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(100000023,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000023,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000023,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(100000024,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000024,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000024,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(100000026,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000026,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000026,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(100000037,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000037,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000037,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(100000042,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000042,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000042,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(100000043,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000043,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000043,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(100000045,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000045,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000045,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(100000048,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000048,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000048,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(100000063,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000063,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000063,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(100000067,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000067,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000067,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(100000070,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000070,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000070,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(100000071,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000071,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000071,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(100000072,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000072,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000072,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(100000073,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000073,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000073,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(100000076,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000076,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000076,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(100000078,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000078,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000078,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(100000079,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000079,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000079,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(100000080,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000080,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000080,'trade_executor');
INSERT into accounts_roles(account_id,role) VALUES(100000081,'buy_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000081,'sales_trader');
INSERT into accounts_roles(account_id,role) VALUES(100000081,'trade_executor');



-- 更新表结构
SET FOREIGN_KEY_CHECKS=0;

ALTER TABLE `accounts` ADD COLUMN `activite_code`  varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `s_member_id`;

ALTER TABLE `accounts` ADD COLUMN `creater`  bigint(12) NOT NULL COMMENT '创建人' AFTER `create_time`;

ALTER TABLE `accounts` ADD COLUMN `updater`  bigint(12) NOT NULL COMMENT '修改人' AFTER `update_time`;

update `accounts` SET creater=`create_user`;

ALTER TABLE `accounts` DROP COLUMN `create_user`;
update `accounts` SET updater=`update_user`;

ALTER TABLE `accounts` DROP COLUMN `update_user`;

ALTER TABLE `accounts_roles` MODIFY COLUMN `account_id`  bigint(12) NULL DEFAULT NULL COMMENT '账户ID' FIRST ;

ALTER TABLE `accounts_roles` MODIFY COLUMN `role`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色Code' AFTER `account_id`;

ALTER TABLE `crude_customer_info` MODIFY COLUMN `id`  bigint(12) NOT NULL FIRST ;

ALTER TABLE `crude_customer_info` MODIFY COLUMN `member_id`  bigint(12) NULL DEFAULT NULL AFTER `id`;

ALTER TABLE `crude_customer_info` MODIFY COLUMN `last_year_operation_revenue`  decimal(20,4) NULL DEFAULT NULL COMMENT '上一年营业收入' AFTER `member_id`;

ALTER TABLE `crude_customer_info` MODIFY COLUMN `last_year_trade_volume`  decimal(20,4) NULL DEFAULT NULL COMMENT '上一年贸易额' AFTER `last_year_operation_revenue`;

ALTER TABLE `crude_customer_info` MODIFY COLUMN `master_company`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属母公司' AFTER `last_year_trade_volume`;

ALTER TABLE `crude_customer_info` MODIFY COLUMN `id`  bigint(12) NOT NULL AUTO_INCREMENT FIRST ;

ALTER TABLE `crude_potrerillos_info` MODIFY COLUMN `fixed_assets`  decimal(20,0) NULL DEFAULT NULL COMMENT '固定资产' AFTER `total_investment`;

ALTER TABLE `crude_potrerillos_info` MODIFY COLUMN `main_products`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主要产品' AFTER `fixed_assets`;

ALTER TABLE `crude_potrerillos_info` MODIFY COLUMN `device_description`  varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备说明' AFTER `main_products`;

ALTER TABLE `crude_potrerillos_info` MODIFY COLUMN `can_trading`  bit(1) NULL DEFAULT NULL COMMENT '是否有进出口权' AFTER `device_description`;

ALTER TABLE `crude_potrerillos_info` MODIFY COLUMN `main_import_product`  varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主要进口产品' AFTER `can_trading`;

ALTER TABLE `crude_potrerillos_info` MODIFY COLUMN `main_export_product`  varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主要出口产品' AFTER `main_import_product`;

ALTER TABLE `crude_provider_info` MODIFY COLUMN `ship_num`  int(11) NULL DEFAULT NULL COMMENT '船舶艘次' AFTER `member_id`;

ALTER TABLE `crude_provider_info` MODIFY COLUMN `ship_tonnage`  decimal(20,4) NULL DEFAULT NULL COMMENT '船舶净吨' AFTER `ship_num`;

ALTER TABLE `crude_provider_info` MODIFY COLUMN `volume_of_freight`  decimal(20,4) NULL DEFAULT NULL COMMENT '货运量' AFTER `ship_tonnage`;

ALTER TABLE `crude_provider_info` MODIFY COLUMN `container_num`  int(11) NULL DEFAULT NULL COMMENT '集装箱数' AFTER `volume_of_freight`;

ALTER TABLE `crude_provider_info` MODIFY COLUMN `total_revenue`  decimal(20,4) NULL DEFAULT NULL COMMENT '公司总收入' AFTER `container_num`;

ALTER TABLE `crude_provider_info` MODIFY COLUMN `ship_agency_revenue`  decimal(20,4) NULL DEFAULT NULL COMMENT '船代收入' AFTER `total_revenue`;

ALTER TABLE `crude_ship_agency_info` MODIFY COLUMN `ship_num`  int(11) NULL DEFAULT NULL COMMENT '船舶艘次' AFTER `member_id`;

ALTER TABLE `crude_ship_agency_info` MODIFY COLUMN `ship_tonnage`  decimal(20,4) NULL DEFAULT NULL COMMENT '船舶净吨' AFTER `ship_num`;

ALTER TABLE `crude_ship_agency_info` MODIFY COLUMN `volume_of_freight`  decimal(20,4) NULL DEFAULT NULL COMMENT '货运量' AFTER `ship_tonnage`;

ALTER TABLE `crude_ship_agency_info` MODIFY COLUMN `container_num`  int(11) NULL DEFAULT NULL COMMENT '集装箱数' AFTER `volume_of_freight`;

ALTER TABLE `crude_ship_agency_info` MODIFY COLUMN `total_revenue`  decimal(20,4) NULL DEFAULT NULL COMMENT '公司总收入' AFTER `container_num`;

ALTER TABLE `crude_ship_agency_info` MODIFY COLUMN `ship_agency_revenue`  decimal(20,4) NULL DEFAULT NULL COMMENT '船代收入' AFTER `total_revenue`;

ALTER TABLE `crude_trading_company_info` MODIFY COLUMN `can_trading`  bit(1) NULL DEFAULT NULL COMMENT '是否有进出口权' AFTER `member_id`;

ALTER TABLE `crude_trading_company_info` MODIFY COLUMN `trading_complay_code`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '进出口企业代码' AFTER `can_trading`;

ALTER TABLE `crude_trading_company_info` MODIFY COLUMN `main_import_product`  varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主要进口产品' AFTER `trading_complay_code`;

ALTER TABLE `crude_trading_company_info` MODIFY COLUMN `main_export_product`  varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主要出口产品' AFTER `main_import_product`;

ALTER TABLE `crude_trading_company_info` MODIFY COLUMN `last_year_operation_revenue`  decimal(20,4) NULL DEFAULT NULL COMMENT '上一年营业收入' AFTER `main_export_product`;

ALTER TABLE `crude_trading_company_info` MODIFY COLUMN `last_year_trade_volume`  decimal(20,4) NULL DEFAULT NULL COMMENT '上一年贸易额' AFTER `last_year_operation_revenue`;

DELETE e FROM email_active e ;

ALTER TABLE `email_active` MODIFY COLUMN `id`  bigint(12) NOT NULL COMMENT '邮箱激活编号' FIRST ;

ALTER TABLE `email_active` MODIFY COLUMN `member_id`  bigint(12) NULL DEFAULT NULL COMMENT '会员编号' AFTER `id`;

ALTER TABLE `email_active` ADD COLUMN `effective`  int(1) NOT NULL DEFAULT 0 COMMENT '这条邮件记录是否生效，0表示已失效' AFTER `email`;

ALTER TABLE `email_active` ADD COLUMN `click_url`  varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件中显示的地址' AFTER `effective`;

ALTER TABLE `email_active` ADD COLUMN `content`  varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '大概是邮件正文' AFTER `click_url`;

ALTER TABLE `email_active` ADD COLUMN `return_url`  varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '验证完成后返回的地址' AFTER `content`;

ALTER TABLE `email_active` MODIFY COLUMN `active_time`  datetime NULL DEFAULT NULL COMMENT '有效时间（秒）' AFTER `return_url`;

ALTER TABLE `email_active` ADD COLUMN `creater`  bigint(12) NOT NULL COMMENT '创建人' AFTER `create_time`;

ALTER TABLE `email_active` MODIFY COLUMN `update_time`  datetime NULL DEFAULT NULL COMMENT '修改时间' AFTER `creater`;

ALTER TABLE `email_active` ADD COLUMN `updater`  bigint(12) NULL DEFAULT NULL COMMENT '修改人' AFTER `update_time`;

ALTER TABLE `email_active` DROP COLUMN `create_user`;

ALTER TABLE `email_active` DROP COLUMN `update_user`;

ALTER TABLE `email_active` MODIFY COLUMN `id`  bigint(12) NOT NULL AUTO_INCREMENT COMMENT '邮箱激活编号' FIRST ;

ALTER TABLE `enterprises` ADD COLUMN `ep_type`  int(11) NULL DEFAULT NULL COMMENT '类型,0=境内,1=境外' AFTER `ep_logo`;

ALTER TABLE `enterprises` ADD COLUMN `scope`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经营范围' AFTER `ep_type`;

ALTER TABLE `enterprises` ADD COLUMN `use_social_credit_cert`  bit(1) NULL DEFAULT NULL COMMENT '是否使用社会信用统一证件' AFTER `scope`;

ALTER TABLE `enterprises` ADD COLUMN `country`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国家' AFTER `use_social_credit_cert`;

ALTER TABLE `enterprises` ADD COLUMN `country_code`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国家编码' AFTER `country`;

ALTER TABLE `enterprises` ADD COLUMN `register_cert`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册证明材料' AFTER `country_code`;

ALTER TABLE `enterprises` ADD COLUMN `bank_cert`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行证明材料' AFTER `register_cert`;

ALTER TABLE `enterprises` ADD COLUMN `bank_cert_type`  int(11) NULL DEFAULT NULL COMMENT '银行证明类型,0-银行开户证明,1-银行资信证明' AFTER `bank_cert`;

ALTER TABLE `enterprises` ADD COLUMN `social_credit_code`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '社会信用代码' AFTER `bank_cert_type`;

ALTER TABLE `enterprises_crude` MODIFY COLUMN `nature`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `business_license_file`;

ALTER TABLE `enterprises_crude` DROP COLUMN `register_fund_currency`;

ALTER TABLE `enterprises_crude` DROP COLUMN `trade_type`;

CREATE TABLE `enterprise_contact` (
  `id`  bigint(12) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `gender`  int(11) NULL DEFAULT NULL ,
  `name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名' ,
  `mobile`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机' ,
  `phone`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话' ,
  `mail`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱' ,
  `address`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通讯地址' ,
  `account_cert`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业账户注册授权委托书' ,
  `admin_cert`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '平台管理员证明' ,
  `enterprise_id`  bigint(12) NOT NULL ,
  `member_id`  bigint(12) NOT NULL ,
  PRIMARY KEY (`id`)
)
  ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
  ROW_FORMAT=Compact
;

CREATE TABLE `invitation_log` (
  `id`  bigint(20) NOT NULL AUTO_INCREMENT ,
  `inviter`  bigint(20) NULL DEFAULT NULL COMMENT '邀请人会员ID' ,
  `account`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '受邀手机号码/邮箱' ,
  `account_id`  bigint(20) NULL DEFAULT NULL COMMENT '账户ID' ,
  `invitation_code`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邀请码' ,
  `role_code`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '受邀角色' ,
  `invited`  bit(1) NULL DEFAULT b'0' COMMENT '受邀状态' ,
  `receiver`  bigint(20) NULL DEFAULT NULL COMMENT '接受邀请人' ,
  `create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
  `creater`  bigint(20) NULL DEFAULT NULL COMMENT '创建人' ,
  PRIMARY KEY (`id`)
)
  ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
  ROW_FORMAT=Compact
;

ALTER TABLE `login_log` MODIFY COLUMN `login_time`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '登录时间' AFTER `memberId`;

ALTER TABLE `login_log` ADD COLUMN `ip`  varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录ip' AFTER `type`;

ALTER TABLE `login_log` ADD COLUMN `browser`  varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录的浏览器' AFTER `ip`;

ALTER TABLE `login_log` ADD COLUMN `set`  varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录设备' AFTER `browser`;

ALTER TABLE `members` MODIFY COLUMN `password`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码' AFTER `type`;

ALTER TABLE `members` MODIFY COLUMN `mobile`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号' AFTER `password`;

ALTER TABLE `members` MODIFY COLUMN `head_url`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像url(已废弃)' AFTER `qq_open_id`;

ALTER TABLE `members` MODIFY COLUMN `locked`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否被锁定,一般系统输入密码超过5次，被锁定' AFTER `key_lost_time`;

ALTER TABLE `members` MODIFY COLUMN `disable`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否不可用，一般由系统管理员锁定' AFTER `locked`;

ALTER TABLE `members` ADD COLUMN `creater`  bigint(12) NOT NULL COMMENT '创建人' AFTER `create_time`;

ALTER TABLE `members` ADD COLUMN `updater`  bigint(12) NOT NULL COMMENT '修改人' AFTER `update_time`;

ALTER TABLE `members` ADD COLUMN `security_question1`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密保问题1' AFTER `updater`;

ALTER TABLE `members` ADD COLUMN `security_answer1`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密保答案1' AFTER `security_question1`;

ALTER TABLE `members` ADD COLUMN `security_question2`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密保问题2' AFTER `security_answer1`;

ALTER TABLE `members` ADD COLUMN `security_answer2`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密保答案2' AFTER `security_question2`;

ALTER TABLE `members` ADD COLUMN `security_question3`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密保问题3' AFTER `security_answer2`;

ALTER TABLE `members` ADD COLUMN `security_answer3`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密保答案4' AFTER `security_question3`;

ALTER TABLE `members` ADD COLUMN `sophon_open_id`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'OAuth:授权编号' AFTER `security_answer3`;

ALTER TABLE `members` ADD COLUMN `access_token`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'OAuth:(目前没用到)' AFTER `sophon_open_id`;

ALTER TABLE `members` ADD COLUMN `safety_score`  int(11) NULL DEFAULT NULL COMMENT '安全评分，总分100' AFTER `access_token`;
update `members` SET creater=`create_user`;

ALTER TABLE `members` DROP COLUMN `create_user`;
update `members` SET updater=`update_user`;

ALTER TABLE `members` DROP COLUMN `update_user`;

CREATE TABLE `members_log` (
  `id`  bigint(12) NOT NULL AUTO_INCREMENT COMMENT 'PK' ,
  `member_id`  bigint(12) NOT NULL COMMENT '会员id' ,
  `user_name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称' ,
  `code`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员代码UUID' ,
  `email`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱' ,
  `password`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码' ,
  `mobile`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号' ,
  `qq_no`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员QQ' ,
  `qq_open_id`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QQ免登id' ,
  `login_failure_count`  int(11) NULL DEFAULT 0 COMMENT '登录失败次数' ,
  `login_date`  datetime NULL DEFAULT NULL COMMENT '登录日期' ,
  `locked`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否被锁定,一般系统输入密码超过5次，被锁定' ,
  `disable`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否不可用，一般由系统管理员锁定' ,
  `security_question1`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密保问题1' ,
  `security_answer1`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密保答案1' ,
  `security_question2`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密保问题2' ,
  `security_answer2`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密保答案2' ,
  `security_question3`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密保问题3' ,
  `security_answer3`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密保答案3' ,
  `del_flg`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志' ,
  `create_time`  datetime NOT NULL COMMENT '创建时间' ,
  `creater`  bigint(20) NOT NULL COMMENT '创建人' ,
  `sophon_open_id`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'OAuth:授权编号' ,
  `access_token`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'OAuth:(目前没用到)' ,
  `safety_score`  int(11) NULL DEFAULT NULL COMMENT '安全评分，总分100' ,
  PRIMARY KEY (`id`)
)
  ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
  ROW_FORMAT=Compact
;

ALTER TABLE `member_credentials` MODIFY COLUMN `id`  bigint(12) NOT NULL COMMENT 'id' FIRST ;

ALTER TABLE `member_credentials` MODIFY COLUMN `member_id`  bigint(12) NOT NULL COMMENT 'member的Id' AFTER `id`;

ALTER TABLE `member_credentials` MODIFY COLUMN `credentials_code`  varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资质的Code' AFTER `member_id`;

ALTER TABLE `member_credentials` MODIFY COLUMN `audit`  bit(1) NOT NULL COMMENT '是否审核' AFTER `credentials_code`;

ALTER TABLE `member_credentials` MODIFY COLUMN `id`  bigint(12) NOT NULL AUTO_INCREMENT COMMENT 'id' FIRST ;

ALTER TABLE `permissions` DROP COLUMN `update_time`;

ALTER TABLE `permissions` DROP COLUMN `update_user`;

ALTER TABLE `persons_logs` MODIFY COLUMN `card_type`  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件类型' AFTER `update_user`;

ALTER TABLE `persons_logs` MODIFY COLUMN `card_no`  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件号码' AFTER `card_type`;

ALTER TABLE `roles` MODIFY COLUMN `description`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述' AFTER `code`;

ALTER TABLE `role_permission` MODIFY COLUMN `role_code`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID' FIRST ;

ALTER TABLE `role_permission` DROP COLUMN `update_time`;

ALTER TABLE `role_permission` DROP COLUMN `update_user`;

CREATE TABLE `sms_active` (
  `id`  bigint(20) NOT NULL AUTO_INCREMENT ,
  `member_id`  bigint(20) NULL DEFAULT NULL COMMENT '用户ID' ,
  `mobile`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码' ,
  `token`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '验证码' ,
  `valid_type`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '验证类型' ,
  `sender_ip`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送人ip地址' ,
  `sender_system`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送者系统类型' ,
  `active`  bit(1) NULL DEFAULT b'0' COMMENT '是否已激活' ,
  `create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
  PRIMARY KEY (`id`)
)
  ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
  ROW_FORMAT=Compact
;

ALTER TABLE `t_app_version` MODIFY COLUMN `url`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '下载地址' AFTER `content`;

ALTER TABLE `t_app_version` MODIFY COLUMN `create_time`  datetime NULL DEFAULT NULL COMMENT '创建时间' AFTER `url`;

ALTER TABLE `t_app_version` MODIFY COLUMN `create_user`  bigint(20) NULL DEFAULT NULL COMMENT '创建用户id' AFTER `create_time`;

ALTER TABLE `t_app_version` MODIFY COLUMN `update_time`  datetime NULL DEFAULT NULL COMMENT '更新时间' AFTER `create_user`;

ALTER TABLE `t_app_version` MODIFY COLUMN `update_user`  bigint(20) NULL DEFAULT NULL COMMENT '更新用户id' AFTER `update_time`;

ALTER TABLE `t_app_version` MODIFY COLUMN `file_name`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名称' AFTER `update_user`;


SET FOREIGN_KEY_CHECKS=1;

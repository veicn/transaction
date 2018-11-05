/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50549
Source Host           : 127.0.0.1:3306
Source Database       : crude

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2017-11-09 14:09:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `accounts`
-- ----------------------------
DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `m_member_id` bigint(12) DEFAULT NULL COMMENT '主会员id',
  `s_member_id` bigint(12) DEFAULT NULL COMMENT '子会员id，可以为空',
  `account_status` char(1) DEFAULT NULL COMMENT '账户状态 0正常，-1冻结',
  `note` varchar(1024) DEFAULT NULL COMMENT '备注',
  `del_flg` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `id_paths` varchar(500) DEFAULT NULL COMMENT '路径',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` bigint(12) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_user` bigint(12) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10002 DEFAULT CHARSET=utf8 COMMENT='子账户表';

-- ----------------------------
-- Records of accounts
-- ----------------------------
INSERT INTO `accounts` VALUES ('10001', '10002', '10001', '1', null, '0', '10002,10001', '2017-11-08 10:48:55', '10001', '2017-11-08 10:49:04', '10001');

-- ----------------------------
-- Table structure for `accounts_roles`
-- ----------------------------
DROP TABLE IF EXISTS `accounts_roles`;
CREATE TABLE `accounts_roles` (
  `account_id` bigint(12) DEFAULT NULL,
  `role` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of accounts_roles
-- ----------------------------

-- ----------------------------
-- Table structure for `email_active`
-- ----------------------------
DROP TABLE IF EXISTS `email_active`;
CREATE TABLE `email_active` (
  `id` bigint(12) NOT NULL COMMENT '邮箱激活编号',
  `member_id` bigint(12) NOT NULL COMMENT '会员编号',
  `email` varchar(100) NOT NULL COMMENT '邮箱地址',
  `active_time` int(11) NOT NULL COMMENT '有效时间（秒）',
  `del_flg` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` bigint(12) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_user` bigint(12) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of email_active
-- ----------------------------

-- ----------------------------
-- Table structure for `enterprises`
-- ----------------------------
DROP TABLE IF EXISTS `enterprises`;
CREATE TABLE `enterprises` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '企业编号',
  `code` varchar(32) NOT NULL COMMENT '企业编号UUID',
  `name` varchar(100) NOT NULL COMMENT '企业名称',
  `full_name` varchar(200) DEFAULT NULL COMMENT '企业全称',
  `english_name` varchar(200) DEFAULT NULL COMMENT '英文名称',
  `logo` varchar(100) DEFAULT NULL COMMENT '企业logo',
  `legal_name` varchar(50) NOT NULL COMMENT '法人姓名',
  `legal_certificate_type` char(1) DEFAULT NULL COMMENT '法人证件类型',
  `legal_certificate_code` varchar(50) DEFAULT NULL COMMENT '法人证件号码',
  `tel` varchar(50) DEFAULT NULL COMMENT '电话',
  `fax` varchar(50) DEFAULT NULL COMMENT '传真',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `website` varchar(200) DEFAULT NULL COMMENT '网址',
  `organization_code` varchar(50) DEFAULT NULL COMMENT '组织机构代码',
  `business_license_code` varchar(50) DEFAULT NULL COMMENT '工商执照号码',
  `tax_code` varchar(50) DEFAULT NULL COMMENT '税务登记号码',
  `credit_code` varchar(50) DEFAULT NULL COMMENT '企业信用号',
  `organization_file` varchar(100) DEFAULT NULL COMMENT '组织机构证件',
  `business_license_file` varchar(100) DEFAULT NULL COMMENT '工商执照证件',
  `tax_file` varchar(100) DEFAULT NULL COMMENT '税务登记证件',
  `credit_file` varchar(100) DEFAULT NULL COMMENT '企业信用证件',
  `register_time` datetime DEFAULT NULL COMMENT '企业注册时间',
  `register_fund` decimal(15,0) DEFAULT NULL COMMENT '注册资本',
  `description` longtext COMMENT '企业描述',
  `main_products` varchar(200) DEFAULT NULL COMMENT '主营产品',
  `country_code` varchar(30) DEFAULT '86' COMMENT '所在国家编码',
  `province_code` varchar(30) DEFAULT NULL COMMENT '所在省编码',
  `city_code` varchar(30) DEFAULT NULL COMMENT '所在市编码',
  `area_code` varchar(30) DEFAULT NULL COMMENT '所在地区编码',
  `address_detail` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `post_code` varchar(20) DEFAULT NULL COMMENT '邮政编码',
  `register_resource` char(4) DEFAULT NULL COMMENT '注册来源，1，自己注册，2后台录入',
  `is_can_buy` char(1) NOT NULL DEFAULT '0' COMMENT '是否可买',
  `is_can_sell` char(1) NOT NULL DEFAULT '0' COMMENT '是否可卖',
  `frozzen_status` varchar(2) NOT NULL DEFAULT '0' COMMENT '审核状态0正常，1冻结',
  `contact_name` varchar(50) DEFAULT NULL COMMENT '联系人姓名',
  `contact_mobile` varchar(50) DEFAULT NULL COMMENT '联系人电话',
  `tax_man_type` char(1) DEFAULT '0' COMMENT '纳税人类型（1:一般纳税人 2:小规模纳税人）',
  `tax_man_file` varchar(200) DEFAULT NULL COMMENT '纳税人资格证书',
  `billing_bank_name` varchar(50) DEFAULT NULL COMMENT '发票-开户银行',
  `billing_bank_account` varchar(50) DEFAULT NULL COMMENT '发票-开户账号',
  `is_active` char(1) DEFAULT NULL COMMENT '激活状态（0待激活 1已激活）',
  `del_flg` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` bigint(12) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_user` bigint(12) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业信息表';

-- ----------------------------
-- Records of enterprises
-- ----------------------------

-- ----------------------------
-- Table structure for `enterprises_logs`
-- ----------------------------
DROP TABLE IF EXISTS `enterprises_logs`;
CREATE TABLE `enterprises_logs` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `enterprise_id` bigint(12) NOT NULL COMMENT '企业编号',
  `name` varchar(100) NOT NULL COMMENT '企业名称',
  `code` varchar(32) NOT NULL COMMENT '企业编号UUID',
  `full_name` varchar(200) DEFAULT NULL COMMENT '企业全称',
  `english_name` varchar(200) DEFAULT NULL COMMENT '英文名称',
  `logo` varchar(100) DEFAULT NULL COMMENT '企业logo',
  `legal_name` varchar(50) NOT NULL COMMENT '法人姓名',
  `legal_certificate_type` char(1) DEFAULT NULL COMMENT '法人证件类型',
  `legal_certificate_code` varchar(50) DEFAULT NULL COMMENT '法人证件号码',
  `tel` varchar(50) DEFAULT NULL COMMENT '电话',
  `fax` varchar(50) DEFAULT NULL COMMENT '传真',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `website` varchar(200) DEFAULT NULL COMMENT '网址',
  `organization_code` varchar(50) DEFAULT NULL COMMENT '组织机构代码',
  `business_license_code` varchar(50) DEFAULT NULL COMMENT '工商执照号码',
  `tax_code` varchar(50) DEFAULT NULL COMMENT '税务登记号码',
  `credit_code` varchar(50) DEFAULT NULL COMMENT '企业信用号',
  `organization_file` varchar(100) DEFAULT NULL COMMENT '组织机构证件',
  `business_license_file` varchar(100) DEFAULT NULL COMMENT '工商执照证件',
  `tax_file` varchar(100) DEFAULT NULL COMMENT '税务登记证件',
  `credit_file` varchar(100) DEFAULT NULL COMMENT '企业信用证件',
  `register_time` datetime DEFAULT NULL COMMENT '企业注册时间',
  `register_fund` decimal(15,0) DEFAULT NULL COMMENT '注册资本',
  `description` longtext COMMENT '企业描述',
  `main_products` varchar(200) DEFAULT NULL COMMENT '主营产品',
  `country_code` varchar(30) DEFAULT '86' COMMENT '所在国家编码',
  `province_code` varchar(30) DEFAULT NULL COMMENT '所在省编码',
  `city_code` varchar(30) DEFAULT NULL COMMENT '所在市编码',
  `area_code` varchar(30) DEFAULT NULL COMMENT '所在地区编码',
  `address_detail` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `post_code` varchar(20) DEFAULT NULL COMMENT '邮政编码',
  `register_resource` char(4) DEFAULT NULL COMMENT '注册来源，1，自己注册，2后台录入',
  `is_can_buy` char(1) NOT NULL DEFAULT '0' COMMENT '是否可买',
  `is_can_sell` char(1) NOT NULL DEFAULT '0' COMMENT '是否可卖',
  `frozzen_status` varchar(2) NOT NULL DEFAULT '0' COMMENT '审核状态0正常，1冻结',
  `contact_name` varchar(50) DEFAULT NULL COMMENT '联系人姓名',
  `contact_mobile` varchar(50) DEFAULT NULL COMMENT '联系人电话',
  `tax_man_type` char(1) DEFAULT '0' COMMENT '纳税人类型（1:一般纳税人 2:小规模纳税人）',
  `tax_man_file` varchar(200) DEFAULT NULL COMMENT '纳税人资格证书',
  `billing_bank_name` varchar(50) DEFAULT NULL COMMENT '发票-开户银行',
  `billing_bank_account` varchar(50) DEFAULT NULL COMMENT '发票-开户账号',
  `is_active` char(1) DEFAULT NULL COMMENT '激活状态（0待激活 1已激活）',
  `del_flg` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` bigint(12) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_user` bigint(12) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业信息表';

-- ----------------------------
-- Records of enterprises_logs
-- ----------------------------

-- ----------------------------
-- Table structure for `members`
-- ----------------------------
DROP TABLE IF EXISTS `members`;
CREATE TABLE `members` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户名称',
  `code` varchar(32) DEFAULT NULL COMMENT '会员代码UUID',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `type` varchar(1) DEFAULT NULL COMMENT '0是个人，1是企业',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `mobile` varchar(32) NOT NULL COMMENT '手机号',
  `member_qq` varchar(32) DEFAULT NULL COMMENT '会员QQ',
  `open_id` varchar(64) DEFAULT NULL COMMENT 'QQ免登id',
  `head_url` varchar(100) DEFAULT NULL,
  `is_account_locked` char(1) DEFAULT '0' COMMENT '账户冻结标识',
  `login_failure_count` int(11) DEFAULT '0' COMMENT '登录失败次数',
  `login_date` datetime DEFAULT NULL COMMENT '登录日期',
  `password_recover_key` varchar(32) DEFAULT NULL COMMENT '密码重置秘钥',
  `key_lost_time` datetime DEFAULT NULL COMMENT '密钥失效时间(15分钟)',
  `is_forbidden` char(1) DEFAULT '0' COMMENT '是否禁用(0：正常 1：禁用)',
  `del_flg` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` bigint(12) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_user` bigint(12) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10003 DEFAULT CHARSET=utf8 COMMENT='会员';

-- ----------------------------
-- Records of members
-- ----------------------------
INSERT INTO `members` VALUES ('10001', 'username', '4028b8815f7a96af015f7a97dd1d0001', null, '0', '111111', '111111', null, null, null, '0', '0', '2017-11-02 10:38:13', null, null, '0', '0', '2017-11-02 10:38:13', '111111', '2017-11-02 10:38:13', '111111');
INSERT INTO `members` VALUES ('10002', 'username', '4028b8815f7a96af015f7a97dd1d0002', null, '1', '222222', '222222', null, null, null, '0', '0', null, null, null, '0', '0', '0000-00-00 00:00:00', '0', '0000-00-00 00:00:00', '0');

-- ----------------------------
-- Table structure for `permissions`
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `module_id` int(3) NOT NULL COMMENT '模块ID',
  `name` varchar(60) DEFAULT NULL COMMENT '权限名称',
  `del_flg` char(1) DEFAULT '0' COMMENT '删除标志',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` bigint(12) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_user` bigint(12) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `fk_ref_module` (`module_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of permissions
-- ----------------------------

-- ----------------------------
-- Table structure for `persons`
-- ----------------------------
DROP TABLE IF EXISTS `persons`;
CREATE TABLE `persons` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) DEFAULT NULL COMMENT '会员代码UUID',
  `name` varchar(100) DEFAULT NULL COMMENT '真实姓名',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `country_code` varchar(30) DEFAULT '86' COMMENT '所在国家编码',
  `province_code` varchar(30) DEFAULT NULL COMMENT '所在省编码',
  `city_code` varchar(30) DEFAULT NULL COMMENT '所在城市编码',
  `area_code` varchar(30) DEFAULT NULL COMMENT '所在地区编码',
  `address_detail` varchar(512) DEFAULT NULL COMMENT '详细地址',
  `zip_code` varchar(255) DEFAULT NULL COMMENT '邮编',
  `memo` varchar(1024) DEFAULT NULL COMMENT '备注',
  `birth` datetime DEFAULT NULL COMMENT '生日',
  `gender` char(1) DEFAULT '0' COMMENT '性别(0：未知 1：男 2：女)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` bigint(12) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_user` bigint(12) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of persons
-- ----------------------------

-- ----------------------------
-- Table structure for `persons_logs`
-- ----------------------------
DROP TABLE IF EXISTS `persons_logs`;
CREATE TABLE `persons_logs` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT,
  `person_id` bigint(12) NOT NULL,
  `code` varchar(32) DEFAULT NULL COMMENT '会员代码UUID',
  `name` varchar(100) DEFAULT NULL COMMENT '真实姓名',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `country_code` varchar(30) DEFAULT '86' COMMENT '所在国家编码',
  `province_code` varchar(30) DEFAULT NULL COMMENT '所在省编码',
  `city_code` varchar(30) DEFAULT NULL COMMENT '所在城市编码',
  `area_code` varchar(30) DEFAULT NULL COMMENT '所在地区编码',
  `address_detail` varchar(512) DEFAULT NULL COMMENT '详细地址',
  `zip_code` varchar(255) DEFAULT NULL COMMENT '邮编',
  `memo` varchar(1024) DEFAULT NULL COMMENT '备注',
  `birth` datetime DEFAULT NULL COMMENT '生日',
  `gender` char(1) DEFAULT '0' COMMENT '性别(0：未知 1：男 2：女)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` bigint(12) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_user` bigint(12) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of persons_logs
-- ----------------------------

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `role_id` bigint(12) NOT NULL COMMENT '角色ID',
  `permission_id` bigint(12) NOT NULL COMMENT '权限ID',
  `del_flg` char(1) DEFAULT '0' COMMENT '删除标志',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` bigint(12) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_user` bigint(12) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`permission_id`,`role_id`),
  KEY `AK_fk_role_permission_ref_role` (`role_id`) USING BTREE,
  KEY `AK_fk_role_permission_ref_permission` (`permission_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `roles`
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `module_id` int(3) NOT NULL COMMENT '模块ID',
  `order` int(11) DEFAULT NULL COMMENT '顺序',
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `del_flg` char(1) DEFAULT '0' COMMENT '删除标志',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` bigint(12) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_user` bigint(12) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of roles
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_sms_code`
-- ----------------------------
DROP TABLE IF EXISTS `sys_sms_code`;
CREATE TABLE `sys_sms_code` (
  `sms_code_id` varchar(96) DEFAULT NULL,
  `mobile` varchar(60) DEFAULT NULL,
  `sms_code` varchar(60) DEFAULT NULL,
  `valid_type` varchar(150) DEFAULT NULL,
  `sms_content` varchar(1500) DEFAULT NULL,
  `active_time` int(11) DEFAULT NULL,
  `status` char(3) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `sender_ip` varchar(3072) DEFAULT NULL,
  `sender_system` varchar(3072) DEFAULT NULL,
  `del_flg` char(3) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_sms_code
-- ----------------------------

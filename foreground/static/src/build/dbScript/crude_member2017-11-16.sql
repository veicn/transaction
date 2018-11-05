/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : crude

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2017-11-16 14:38:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `accounts`
-- ----------------------------
DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts` (
  `m_member_id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '主会员id',
  `s_member_id` bigint(12) DEFAULT NULL COMMENT '子会员id，可以为空',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `module_no` bigint(12) DEFAULT NULL,
  `del_flg` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `id_paths` varchar(500) DEFAULT NULL COMMENT '路径',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` bigint(12) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_user` bigint(12) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`m_member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000000 DEFAULT CHARSET=utf8 COMMENT='子账户表';

-- ----------------------------
-- Records of accounts
-- ----------------------------
INSERT INTO `accounts` VALUES ('999999', '999999', '1', null, '0', '10002,10001', '2017-11-08 10:48:55', '10001', '2017-11-08 10:49:04', '10001');

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
  `qq_no` varchar(32) DEFAULT NULL COMMENT '会员QQ',
  `qq_open_id` varchar(64) DEFAULT NULL COMMENT 'QQ免登id',
  `head_url` varchar(100) DEFAULT NULL,
  `login_failure_count` int(11) DEFAULT '0' COMMENT '登录失败次数',
  `login_date` datetime DEFAULT NULL COMMENT '登录日期',
  `password_recover_key` varchar(32) DEFAULT NULL COMMENT '密码重置秘钥',
  `key_lost_time` datetime DEFAULT NULL COMMENT '密钥失效时间(15分钟)',
  `locked` char(1) DEFAULT '0' COMMENT '是否被锁定,一般系统输入密码超过5次，被锁定',
  `disable` char(1) DEFAULT NULL,
  `del_flg` char(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` bigint(12) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_user` bigint(12) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000005 DEFAULT CHARSET=utf8 COMMENT='会员';

-- ----------------------------
-- Records of members
-- ----------------------------
INSERT INTO `members` VALUES ('10001', 'username1111', '402888e85fc377a7015fc37b7ca20003', 'ddddd1', '0', '11111111', 'ddddd1', 'gtt111111', null, null, '0', '2017-11-16 14:19:30', null, null, '0', '0', '0', '2017-11-16 14:19:30', '111111', '2017-11-02 10:38:13', '111111');
INSERT INTO `members` VALUES ('10002', 'username', '4028b8815f7a96af015f7a97dd1d0002', null, '1', '222222', '222222', null, null, null, '0', '2017-11-15 19:22:55', null, null, '0', '0', '0', '2017-11-15 19:42:57', '0', '2017-11-15 19:43:13', '0');
INSERT INTO `members` VALUES ('999999', '系统管理员', '4028b8815f7a96af015f7a97dd1d9999', 'admin@sinochem.com', '0', '999999', '999999', null, null, null, '0', '2017-11-15 19:22:56', null, null, '0', '0', '0', '2017-11-10 09:41:03', '999999', '2017-11-10 09:40:59', '999999');
INSERT INTO `members` VALUES ('1000000', '1111', '402888e85fc356cf015fc35703340001', '1', null, '111111', '1', '11', null, null, '0', '2017-11-16 13:39:40', null, null, '0', '0', '1', '2017-11-16 13:39:40', '0', '2017-11-16 13:36:06', '0');
INSERT INTO `members` VALUES ('1000001', '11111', '402888e85fc377a7015fc377d5ac0001', '111', null, '1111111', '11', '1111', null, null, '0', '2017-11-16 14:15:31', null, null, '0', '0', '0', '2017-11-16 14:15:31', '0', '2017-11-16 14:15:31', '0');
INSERT INTO `members` VALUES ('1000002', '1111', '402888e85fc377a7015fc37ba13b0004', '11', null, '1111111', '111', '11', null, null, '0', '2017-11-16 14:19:40', null, null, '0', '0', '0', '2017-11-16 14:19:40', '0', '2017-11-16 14:19:40', '0');
INSERT INTO `members` VALUES ('1000003', '123321', '402888e85fc377a7015fc37bd95d0005', '1', null, '123321', '1', '1', null, null, '0', '2017-11-16 14:19:54', null, null, '0', '0', '0', '2017-11-16 14:19:54', '0', '2017-11-16 14:19:54', '0');
INSERT INTO `members` VALUES ('1000004', '3333', '402888e85fc377a7015fc37d84d50006', '123', '1', '333333', '123', '123', null, null, '0', '2017-11-16 14:21:44', null, null, '0', '0', '0', '2017-11-16 14:21:44', '0', '2017-11-16 14:21:44', '0');

-- ----------------------------
-- Table structure for `permissions`
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `module_no` int(3) NOT NULL COMMENT '模块ID',
  `code` int(11) DEFAULT NULL COMMENT '各模块自定义的权限编号，从1-256',
  `description` varchar(60) DEFAULT NULL COMMENT '权限名称',
  `del_flg` char(1) DEFAULT '0' COMMENT '删除标志',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` bigint(12) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_user` bigint(12) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `fk_ref_module` (`module_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of permissions
-- ----------------------------
INSERT INTO `permissions` VALUES ('1', '0', '1', '系统管理', '0', '2017-11-10 09:18:02', '1', '2017-11-10 09:18:09', '1');
INSERT INTO `permissions` VALUES ('2', '1', '1', '用户管理', '0', '2017-11-10 09:18:45', '1', '2017-11-10 09:18:50', '1');

-- ----------------------------
-- Table structure for `roles`
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `module_no` int(3) NOT NULL COMMENT '模块ID',
  `order_index` int(11) DEFAULT NULL COMMENT '顺序',
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` bigint(12) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_user` bigint(12) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('1', '1', '1', 'super_admin', null, '0', '2017-11-09 17:35:20', '100001', '2017-11-09 17:35:29', '100001');

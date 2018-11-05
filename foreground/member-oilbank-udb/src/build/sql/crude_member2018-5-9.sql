/*
Navicat MySQL Data Transfer

Source Server         : rm-2ze1emscmwth2ldalo.mysql.rds.aliyuncs.com
Source Server Version : 50634
Source Host           : rm-2ze1emscmwth2ldalo.mysql.rds.aliyuncs.com:3306
Source Database       : crude-trade-member

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2018-05-09 14:15:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `business_card_apply`
-- ----------------------------
DROP TABLE IF EXISTS `business_card_apply`;
CREATE TABLE `business_card_apply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员id',
  `apply_member_id` bigint(20) DEFAULT NULL COMMENT '申请会员名片id',
  `apply_status` char(1) DEFAULT '0' COMMENT '申请状态 （0：申请中 ，1：同意，2：忽略）',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `del_flg` char(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `creater` bigint(20) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `updater` bigint(20) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='名片申请表';


-- ----------------------------
-- Table structure for `business_card_collect`
-- ----------------------------
DROP TABLE IF EXISTS `business_card_collect`;
CREATE TABLE `business_card_collect` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员id',
  `collect_member_id` bigint(20) DEFAULT NULL COMMENT '收藏名片会员id',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `del_flg` char(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `creater` bigint(20) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `updater` bigint(20) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='名片收藏表';


-- ----------------------------
-- Table structure for `business_cards`
-- ----------------------------
DROP TABLE IF EXISTS `business_cards`;
CREATE TABLE `business_cards` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员id',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `org_name` varchar(50) DEFAULT NULL COMMENT '公司名称',
  `professional_name` varchar(50) DEFAULT NULL COMMENT '职称',
  `nick_name_en` varchar(50) DEFAULT NULL COMMENT '英文昵称',
  `org_name_en` varchar(50) DEFAULT NULL COMMENT '英文公司名',
  `professional_name_en` varchar(50) DEFAULT NULL COMMENT '英文职称',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `del_flg` char(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `creater` bigint(20) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `updater` bigint(20) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='名片表';


-- ----------------------------
-- Table structure for `member_credentials_logs`
-- ----------------------------
DROP TABLE IF EXISTS `member_credentials_logs`;
CREATE TABLE `member_credentials_logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_credentials_id` bigint(20) DEFAULT NULL COMMENT '会员资质id',
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员id',
  `credentials_code` varchar(2) DEFAULT NULL COMMENT '资质的Code',
  `audit` int(11) DEFAULT NULL COMMENT '审核状态：0-申请中 1-审批通过 2-驳回 3-删除',
  `content` varchar(500) DEFAULT NULL COMMENT '内容',
  `creater` bigint(20) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='会员资质日志表';

--member_credentials 修改会员资质表 audit数据类型
ALTER TABLE member_credentials MODIFY COLUMN audit int(11) COMMENT '审核状态：0-申请中 1-审批通过 2-驳回';
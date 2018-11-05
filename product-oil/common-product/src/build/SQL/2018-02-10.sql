USE `crude-trade-listed`;



ALTER TABLE demand ADD inspection_fee_sharing_type INT; 

ALTER TABLE demand MODIFY inspection_fee_sharing_type INT COMMENT '商检费分摊类型 1-买家全付 2-卖家全付 3-各一半 4-所有参与方平分';

CREATE TABLE `demand_bidding_history` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `demand_id` bigint(12) DEFAULT NULL COMMENT '采购单信息',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '变更时间',
  `updater` bigint(12) DEFAULT NULL COMMENT '变更人',
  `item` varchar(50) DEFAULT NULL COMMENT '变更字段',
  `value_old` varchar(1000) DEFAULT NULL COMMENT '变更前',
  `value_new` varchar(1000) DEFAULT NULL COMMENT '变更后',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;



USE `crude-trade-order`;



ALTER TABLE contract ADD inspection_fee_sharing_type INT; 
ALTER TABLE contract MODIFY inspection_fee_sharing_type INT COMMENT '商检费分摊类型 1-买家全付 2-卖家全付 3-各一半 4-所有参与方平分';



USE `crude-trade-member`;


CREATE TABLE `t_app_version` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `version_code` bigint(20) NOT NULL COMMENT '版本号',
  `version_name` varchar(32) NOT NULL COMMENT '版本名称',
  `content` varchar(255) DEFAULT NULL COMMENT '更新内容',
  `url` varchar(255) NOT NULL COMMENT '下载地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建用户id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '更新用户id',
  `file_name` varchar(32) DEFAULT NULL COMMENT '文件名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE `t_prize_collection` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `content` varchar(500) DEFAULT NULL COMMENT '内容',
  `enclosure_url` varchar(100) DEFAULT NULL COMMENT '附件路径',
  `enclosure_name` varchar(100) DEFAULT NULL COMMENT '附件名称',
  `contact_user` varchar(50) DEFAULT NULL COMMENT '联系人',
  `contact_telephone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `contact_email` varchar(50) DEFAULT NULL COMMENT '联系邮箱',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;






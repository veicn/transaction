INSERT INTO member_credentials(member_id,credentials_code,audit) select id,'6',1 from members m where m.type=1

ALTER TABLE `enterprises_crude` DROP COLUMN `code`;

ALTER TABLE `enterprises_crude` DROP COLUMN `name`;

ALTER TABLE `enterprises_crude` DROP COLUMN `full_name`;

ALTER TABLE `enterprises_crude` DROP COLUMN `english_name`;

ALTER TABLE `enterprises_crude` DROP COLUMN `logo`;

ALTER TABLE `enterprises_crude` DROP COLUMN `organization_code`;

ALTER TABLE `enterprises_crude` DROP COLUMN `business_license_code`;

ALTER TABLE `enterprises_crude` DROP COLUMN `tax_code`;

ALTER TABLE `enterprises_crude` DROP COLUMN `credit_code`;

ALTER TABLE `enterprises_crude` DROP COLUMN `organization_file`;

ALTER TABLE `enterprises_crude` DROP COLUMN `license_no`;

ALTER TABLE `enterprises_crude` DROP COLUMN `tax_file`;

ALTER TABLE `enterprises_crude` DROP COLUMN `credit_file`;

ALTER TABLE `enterprises_crude` DROP COLUMN `is_can_buy`;

ALTER TABLE `enterprises_crude` DROP COLUMN `is_can_sell`;

ALTER TABLE `enterprises_crude` DROP COLUMN `frozzen_status`;

ALTER TABLE `enterprises_crude` DROP COLUMN `is_active`;

ALTER TABLE `enterprises_crude_logs` MODIFY COLUMN `enterprise_id`  bigint(20) NULL DEFAULT NULL COMMENT '企业编号' AFTER `id`;

ALTER TABLE `enterprises_crude_logs` ADD COLUMN `member_id`  bigint(20) NULL DEFAULT NULL COMMENT '关联企业会员Id' AFTER `enterprise_id`;

ALTER TABLE `enterprises_crude_logs` DROP COLUMN `name`;

ALTER TABLE `enterprises_crude_logs` DROP COLUMN `code`;

ALTER TABLE `enterprises_crude_logs` DROP COLUMN `full_name`;

ALTER TABLE `enterprises_crude_logs` DROP COLUMN `english_name`;

ALTER TABLE `enterprises_crude_logs` DROP COLUMN `logo`;

ALTER TABLE `enterprises_crude_logs` DROP COLUMN `organization_code`;

ALTER TABLE `enterprises_crude_logs` DROP COLUMN `business_license_code`;

ALTER TABLE `enterprises_crude_logs` DROP COLUMN `tax_code`;

ALTER TABLE `enterprises_crude_logs` DROP COLUMN `credit_code`;

ALTER TABLE `enterprises_crude_logs` DROP COLUMN `organization_file`;

ALTER TABLE `enterprises_crude_logs` DROP COLUMN `business_license_file`;

ALTER TABLE `enterprises_crude_logs` DROP COLUMN `tax_file`;

ALTER TABLE `enterprises_crude_logs` DROP COLUMN `credit_file`;

ALTER TABLE `enterprises_crude_logs` DROP COLUMN `is_can_buy`;

ALTER TABLE `enterprises_crude_logs` DROP COLUMN `is_can_sell`;

ALTER TABLE `enterprises_crude_logs` DROP COLUMN `frozzen_status`;

ALTER TABLE `enterprises_crude_logs` DROP COLUMN `is_active`;

ALTER TABLE `members` MODIFY COLUMN `nick_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称' AFTER `user_name`;

ALTER TABLE `members` MODIFY COLUMN `mobile`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号' AFTER `password`;


CREATE TABLE `sys_mail_code` (
  `id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键编号' ,
  `email`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号码' ,
  `code`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '短信验证码' ,
  `valid_type`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '验证码类型' ,
  `mail_content`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '短信内容' ,
  `active_time`  int(11) NULL DEFAULT NULL COMMENT '有效时间（秒）' ,
  `status`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态' ,
  `send_time`  datetime NOT NULL COMMENT '发送时间' ,
  `del_flg`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志' ,
  `create_time`  datetime NOT NULL COMMENT '创建时间' ,
  PRIMARY KEY (`id`)
)
  ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
  ROW_FORMAT=Compact
;

ALTER TABLE `t_member_account` MODIFY COLUMN `id`  bigint(20) NOT NULL COMMENT '主键' FIRST ;

SET FOREIGN_KEY_CHECKS=1;


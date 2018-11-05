ALTER TABLE `accounts`
  CHANGE COLUMN `create_user` `creater`  bigint(12) NOT NULL COMMENT '创建人' AFTER `create_time`,
  CHANGE COLUMN `update_user` `updater`  bigint(12) NOT NULL COMMENT '修改人' AFTER `update_time`;

ALTER TABLE `email_active`
  ADD COLUMN `effective`  int(1) NOT NULL AFTER `email`;

ALTER TABLE `email_active`
  ADD COLUMN `content`  varchar(1000) NULL AFTER `effective`;


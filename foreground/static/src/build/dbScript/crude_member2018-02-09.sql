CREATE TABLE `sms_active` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `member_id` bigint(12) DEFAULT NULL COMMENT '关联的member的id',
  `mobile` varchar(32) NOT NULL COMMENT '验证码对应的手机号',
  `token` varchar(32) NOT NULL COMMENT '验证码',
  `valid_type` varchar(10) DEFAULT NULL COMMENT '验证类型',
  `sender_ip` varchar(64) DEFAULT NULL COMMENT '发送者ip地址',
  `senderSystem` varchar(64) DEFAULT NULL COMMENT '发送者系统类型',
  `active` char(1) NOT NULL COMMENT '是否已被使用',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `member_tags` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `code` int(11) NOT NULL COMMENT '标识',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `owner` varchar(20) NOT NULL COMMENT '业务主体',
  `member_id` bigint(12) DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=768 DEFAULT CHARSET=utf8;


INSERT INTO member_tags(code,owner,member_id) select '1','reg',member_id from enterprises;
INSERT INTO member_tags(code,owner,member_id) select '0','reg',member_id from persons;
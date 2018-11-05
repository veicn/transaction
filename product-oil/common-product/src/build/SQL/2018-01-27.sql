USE `crude-trade-listed`;

CREATE TABLE `demand_specify_enterprise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `demand_id` bigint(20) NOT NULL COMMENT '需求单id',
  `ep_memberid` bigint(20) NOT NULL COMMENT '企业memberid',
  `enterprise_name` varchar(100) DEFAULT NULL COMMENT '企业名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `creater` bigint(20) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8



ALTER TABLE demand ADD bidding_type INT; 
ALTER TABLE demand MODIFY bidding_type INT COMMENT '投标类型 1-意向投标 2-实际投标';
ALTER TABLE demand ADD publish_type INT; 
ALTER TABLE demand MODIFY publish_type INT COMMENT '发布类型 1-按油种 2-按性质'; 
ALTER TABLE demand MODIFY updater INT;
ALTER TABLE demand ADD load_and_discharge_permitted_timespan VARCHAR(255);
ALTER TABLE demand MODIFY load_and_discharge_permitted_timespan VARCHAR(255) COMMENT '允许装卸货时间';
ALTER TABLE demand ADD specified INT; 
ALTER TABLE demand MODIFY specified INT COMMENT '是否指定发布(1-是，0-否)';
ALTER TABLE demand ADD contract_kind VARCHAR(255);
ALTER TABLE demand MODIFY contract_kind VARCHAR(255) COMMENT '合约种类';

USE `crude-trade-order`;

ALTER TABLE contract ADD load_and_discharge_permitted_timespan VARCHAR(255);
ALTER TABLE contract MODIFY load_and_discharge_permitted_timespan VARCHAR(255) COMMENT '允许装卸货时间';

ALTER TABLE contract ADD measure_mode INT; 
ALTER TABLE contract MODIFY measure_mode INT COMMENT '计量方式';

ALTER TABLE contract ADD contract_kind VARCHAR(255); 
ALTER TABLE contract MODIFY contract_kind VARCHAR(255) COMMENT '合约种类';

USE `crude-trade-member`;

ALTER TABLE members ADD COLUMN `nick_name` VARCHAR(100) NULL   COMMENT '昵称或姓名' AFTER `user_name`;
ALTER TABLE `members`
  CHANGE `mobile` `mobile` VARCHAR(32) CHARSET utf8 COLLATE utf8_general_ci NULL   COMMENT '手机号';
delete a from accounts a where a.m_member_id=a.s_member_id;
UPDATE members m set m.password= MD5(CONCAT(MD5(m.`password`),'SinoB2B'));

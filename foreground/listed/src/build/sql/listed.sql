-- ----------------------------
-- Table structure for demand
-- ----------------------------
DROP TABLE IF EXISTS `demand`;
CREATE TABLE `demand` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '����',
  `num` bigint(12) DEFAULT NULL COMMENT '����',
  `numFloat` varchar(100) DEFAULT NULL COMMENT '����ƫ��',
  `trade_ltem` int(12) DEFAULT NULL COMMENT 'ó������',
  `agio` bigint(12) DEFAULT NULL COMMENT '��ˮ',
  `valuation_base` varchar(100) DEFAULT NULL COMMENT '�Ƽۻ�׼',
  `valuation_proid` varchar(100) DEFAULT NULL COMMENT '�Ƽ���',
  `pay_item` int(12) DEFAULT NULL COMMENT '��������',
  `pay_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `purchase_type` int(12) DEFAULT NULL COMMENT '�ɹ�����',
  `creater` bigint(12) DEFAULT NULL COMMENT '������',
  `create_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `updater` datetime DEFAULT NULL COMMENT '������',
  `updater_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '����',
  `valuation_formula` varchar(100) DEFAULT NULL COMMENT '�Ƽ۹�ʽ',
  `type` varchar(100) DEFAULT NULL COMMENT '��������',
  `deal_type` varchar(100) DEFAULT NULL COMMENT '��������',
  `biz_type` varchar(100) DEFAULT NULL COMMENT 'ҵ������',
  `product_oil_id` varchar(100) DEFAULT NULL COMMENT '��Ʒ��',
  `other_item` varchar(100) DEFAULT NULL COMMENT '��������',
  `auth_item` int(12) DEFAULT NULL COMMENT '��������',
  `business_check_org` varchar(100) DEFAULT NULL,
  `export_conf_file` varchar(100) DEFAULT NULL COMMENT '���������ļ�',
  `remark` varchar(255) DEFAULT NULL,
  `tender_out_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `stop_bid_time` datetime DEFAULT NULL COMMENT '�ر�ʱ��',
  `response_start_time` datetime DEFAULT NULL COMMENT '��Ӧ��ʼʱ��',
  `response_end_time` datetime DEFAULT NULL COMMENT '��Ӧ����ʱ��',
  `uuid` varchar(32) DEFAULT NULL COMMENT 'uuid ���',
  `crude_oil_options` varchar(255) DEFAULT NULL COMMENT 'ԭ��-����',
  `product_oil_classify` int(12) DEFAULT NULL COMMENT '��Ʒ�ͷ���',
  `product_oil_kind` int(12) DEFAULT NULL COMMENT '��Ʒ������',
  `product_oil_specs` int(12) DEFAULT NULL COMMENT '��Ʒ�͹��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for demand_detail
-- ----------------------------
DROP TABLE IF EXISTS `demand_detail`;
CREATE TABLE `demand_detail` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '����',
  `demand_id` bigint(12) NOT NULL COMMENT '�ɹ�����Ϣ',
  `desc` varchar(500) DEFAULT NULL COMMENT '���ı��༭���ڵ����ݣ��д���ȶ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for demand_images
-- ----------------------------
DROP TABLE IF EXISTS `demand_images`;
CREATE TABLE `demand_images` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '����',
  `demand_id` bigint(12) NOT NULL,
  `images` varchar(255) NOT NULL COMMENT 'ͼƬ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for demand_relevanter
-- ----------------------------
DROP TABLE IF EXISTS `demand_relevanter`;
CREATE TABLE `demand_relevanter` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '����',
  `e_member_id` bigint(12) DEFAULT NULL COMMENT '��Ӧ��ҵid',
  `demand_id` bigint(12) DEFAULT NULL COMMENT '�ɹ�����Ϣ',
  `contacter` varchar(100) DEFAULT NULL COMMENT '��ϵ��',
  `phone_no` varchar(100) DEFAULT NULL COMMENT '��ϵ�绰',
  `famil` varchar(100) DEFAULT NULL COMMENT '����',
  `fax` varchar(100) DEFAULT NULL COMMENT '����',
  `share` tinyint(4) DEFAULT NULL COMMENT '������ϵ����Ϣ�Ƿ񹫿�',
  `type` varchar(100) DEFAULT NULL COMMENT '����',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for demand_ship
-- ----------------------------
DROP TABLE IF EXISTS `demand_ship`;
CREATE TABLE `demand_ship` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '����',
  `transport_modes` int(12) NOT NULL COMMENT '���䷽ʽ',
  `demand_id` bigint(12) NOT NULL COMMENT '�ɹ�����Ϣ',
  `discharge_port` varchar(100) DEFAULT NULL COMMENT 'ж����',
  `shipment_port` varchar(100) DEFAULT NULL COMMENT 'װ����',
  `shipment_end_time` datetime DEFAULT NULL COMMENT 'װ������ʱ��',
  `discharge_end_time` datetime DEFAULT NULL COMMENT 'ж������ʱ��',
  `shipment_start_time` datetime DEFAULT NULL COMMENT 'װ����ʼʱ��',
  `discharge_start_time` datetime DEFAULT NULL COMMENT 'ж����ʼʱ��',
  `ship_type` int(10) DEFAULT NULL COMMENT '����',
  `creater` bigint(12) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `updater` bigint(12) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for demand_ship_berth
-- ----------------------------
DROP TABLE IF EXISTS `demand_ship_berth`;
CREATE TABLE `demand_ship_berth` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '����',
  `berth_name` varchar(100) DEFAULT NULL COMMENT '��λ����',
  `berth_grade` int(12) DEFAULT NULL COMMENT '��λ�ȼ�',
  `draft_limitation` int(12) DEFAULT NULL COMMENT '��ˮ����',
  `ship_type` int(12) DEFAULT NULL COMMENT '����',
  `carrying_capacity_min` int(12) DEFAULT NULL COMMENT '��С����',
  `carrying_capacity_max` int(12) DEFAULT NULL COMMENT '�������',
  `buying_leads_id` bigint(12) NOT NULL COMMENT '�ɹ�����Ϣ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
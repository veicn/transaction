
-----2017-12-12-----

ALTER TABLE `contract`
MODIFY COLUMN `double_sign`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '双签，00，没签，01买签，10卖签，11双签' AFTER `double_status`;

ALTER TABLE `contract`
DROP COLUMN `resource_id`,
DROP COLUMN `crude_oil_options`,
DROP COLUMN `product_oil_classify`,
DROP COLUMN `product_oil_kind`,
DROP COLUMN `product_oil_specs`,
DROP COLUMN `start_year`,
DROP COLUMN `end_year`,
DROP COLUMN `start_month`,
DROP COLUMN `end_month`;

ALTER TABLE `crude_oil_resource`
ADD COLUMN `contract_id`  bigint NULL COMMENT '订单ID' AFTER `order_index`;

ALTER TABLE `product_oil_resource`
ADD COLUMN `contract_id`  bigint NULL COMMENT '订单ID' AFTER `version`;

ALTER TABLE `contract_ship`
CHANGE COLUMN `shipment_start` `shipment_start_time`  datetime NULL DEFAULT NULL COMMENT '装货开始时间' AFTER `shipment_port`,
CHANGE COLUMN `shipment_end` `shipment_end_time`  datetime NULL DEFAULT NULL COMMENT '装货结束时间' AFTER `shipment_start_time`,
CHANGE COLUMN `discharge_start` `discharge_start_time`  datetime NULL DEFAULT NULL COMMENT '到货开始时间' AFTER `shipment_end_time`,
CHANGE COLUMN `discharge_end` `discharge_end_time`  datetime NULL DEFAULT NULL COMMENT '到货结束时间' AFTER `discharge_start_time`;





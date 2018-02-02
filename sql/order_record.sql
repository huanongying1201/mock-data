CREATE TABLE `order_record` (
  `order_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `order_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '订单日期',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '客户编号',
  `goods_id` bigint(20) unsigned NOT NULL COMMENT '商品编号',
  `amount` bigint(20) unsigned NOT NULL COMMENT '交易金额',
  `order_status` tinyint(3) unsigned NOT NULL COMMENT '订单状态，0-失效，1-交易成功',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`order_id`),
  KEY `idx_date_good_amount` (`order_date`,`amount`,`goods_id`) USING BTREE,
  KEY `idx_date` (`user_id`,`order_date`) USING BTREE,
  KEY `idx_date_good` (`order_date`,`amount`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100001 DEFAULT CHARSET=latin1 COMMENT='订单表';
/*
Navicat MySQL Data Transfer

Source Server         : MySql
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : rushshop

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-02-14 16:51:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `product_id` int(11) NOT NULL DEFAULT '0',
  `quantity` int(11) NOT NULL DEFAULT '0',
  `product_price` decimal(20,2) NOT NULL DEFAULT '0.00',
  `order_price` decimal(20,2) NOT NULL DEFAULT '0.00',
  `promotion_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('2019021300000000', '4', '1', '1', '9999.99', '9999.99', '0');
INSERT INTO `order_info` VALUES ('2019021300000100', '4', '2', '1', '10990.00', '10990.00', '0');
INSERT INTO `order_info` VALUES ('2019021300000200', '4', '2', '1', '10990.00', '10990.00', '0');
INSERT INTO `order_info` VALUES ('2019021300000300', '4', '2', '1', '10990.00', '10990.00', '0');
INSERT INTO `order_info` VALUES ('2019021300000400', '4', '1', '1', '9999.99', '9999.99', '0');
INSERT INTO `order_info` VALUES ('2019021300000700', '4', '1', '1', '9999.99', '9999.99', '0');
INSERT INTO `order_info` VALUES ('2019021300000800', '4', '2', '1', '10990.00', '10990.00', '0');
INSERT INTO `order_info` VALUES ('2019021300000900', '4', '2', '1', '5000.00', '5000.00', '2');
INSERT INTO `order_info` VALUES ('2019021300001000', '4', '1', '1', '8000.00', '8000.00', '1');
INSERT INTO `order_info` VALUES ('2019021300001100', '4', '3', '1', '200.00', '200.00', '0');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `price` decimal(20,2) NOT NULL DEFAULT '0.00',
  `description` varchar(500) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `sales` int(11) NOT NULL DEFAULT '0',
  `img_url` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', 'Iphone X', '9999.99', 'Awesome Mobile Phone', '103', 'https://staticshop.o2.co.uk/product/images/iphone-x-space-grey-sku-header.png?cb=25dc5afb0412fc40a28aa29d82cb53d0');
INSERT INTO `product` VALUES ('2', 'Iphone X MAX', '10990.00', 'Expensive Mobile Phone', '84', 'https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/image/AppleInc/aos/published/images/i/ph/iphone/xs/iphone-xs-max-select-2018-group?wid=578&hei=982&fmt=jpeg&qlt=80&op_usm=0.5,0.5&.v=1536616752354');
INSERT INTO `product` VALUES ('3', 'XiaoMi Phone', '200.00', 'Cheap Phone', '1', 'https://cdn2.gsmarena.com/vv/bigpic/xiaomi-mi-8-lite-.jpg');
INSERT INTO `product` VALUES ('4', 'Galaxy S9', '3000.00', 'sumsang phone', '0', 'https://cdn2.gsmarena.com/vv/bigpic/samsung-galaxy-s9-.jpg');

-- ----------------------------
-- Table structure for product_stock
-- ----------------------------
DROP TABLE IF EXISTS `product_stock`;
CREATE TABLE `product_stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stock` int(11) NOT NULL DEFAULT '0',
  `product_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of product_stock
-- ----------------------------
INSERT INTO `product_stock` VALUES ('1', '96', '1');
INSERT INTO `product_stock` VALUES ('2', '994', '2');
INSERT INTO `product_stock` VALUES ('3', '99', '3');
INSERT INTO `product_stock` VALUES ('4', '100', '4');

-- ----------------------------
-- Table structure for promotion
-- ----------------------------
DROP TABLE IF EXISTS `promotion`;
CREATE TABLE `promotion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `promotion_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `start_time` datetime NOT NULL DEFAULT '1000-01-01 00:00:00',
  `end_time` datetime NOT NULL DEFAULT '1000-01-01 00:00:00',
  `product_id` int(11) NOT NULL DEFAULT '0',
  `promotion_price` decimal(20,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of promotion
-- ----------------------------
INSERT INTO `promotion` VALUES ('1', 'iphoneX promotion', '2019-02-13 12:00:00', '2019-02-14 00:00:00', '1', '8000.00');
INSERT INTO `promotion` VALUES ('2', 'iphoneX MX promotion', '2019-02-13 18:39:45', '2019-02-15 18:12:10', '2', '5000.00');

-- ----------------------------
-- Table structure for sequence_info
-- ----------------------------
DROP TABLE IF EXISTS `sequence_info`;
CREATE TABLE `sequence_info` (
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `current_value` int(11) NOT NULL DEFAULT '0',
  `step` int(11) NOT NULL DEFAULT '0',
  `max_value` int(11) NOT NULL DEFAULT '0',
  `init_value` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sequence_info
-- ----------------------------
INSERT INTO `sequence_info` VALUES ('order_seq', '12', '1', '999999', '0');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `gender` tinyint(4) NOT NULL DEFAULT '0' COMMENT '//1.male 2.female',
  `age` int(11) NOT NULL DEFAULT '0',
  `telephone` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `register_mode` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '//by telephone, by email, by wechat, by alipay',
  `third_party_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `telephone_unique_index` (`telephone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'jingtao', '1', '38', '1388888888', 'byphone', '');
INSERT INTO `user_info` VALUES ('4', 'admin', '1', '20', '13812341234', 'byPhone', '');

-- ----------------------------
-- Table structure for user_password
-- ----------------------------
DROP TABLE IF EXISTS `user_password`;
CREATE TABLE `user_password` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `encrypt_password` varchar(128) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `user_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_password
-- ----------------------------
INSERT INTO `user_password` VALUES ('1', 'qewwqrewqr', '1');
INSERT INTO `user_password` VALUES ('4', '4QrcOUm6Wau+VuBX8g+IPg==', '4');

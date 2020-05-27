/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : test1

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2016-11-05 21:17:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `userName` varchar(32) DEFAULT NULL COMMENT '用户名',
  `hashPassword` varchar(32) DEFAULT NULL COMMENT '密码',
  `user_sex` varchar(32) DEFAULT NULL,
  `salt` varchar(32) DEFAULT NULL,
  `nick_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

----------------------
-- 商品表
----------------------
DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `name` varchar(16) DEFAULT NULL COMMENT '商品名称',
  `title` varchar(64) DEFAULT NULL COMMENT '商品标题',
  `img` varchar(64) DEFAULT NULL COMMENT '商品的图片',
  `detail` longtext COMMENT '商品的详情介绍',
  `price` NUMERIC(10,2) DEFAULT '0.00' COMMENT '商品单价',
  `stock` int(11) DEFAULT '0' COMMENT '商品库存，-1表示没有限制',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product_seckill`
--

DROP TABLE IF EXISTS `product_seckill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_seckill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '秒杀的商品表',
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品Id',
  `price` NUMERIC(10,2) DEFAULT '0.00' COMMENT '秒杀价',
  `stock_count` int(11) DEFAULT NULL COMMENT '库存数量',
  `start_date` datetime DEFAULT NULL COMMENT '秒杀开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '秒杀结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--- 购物车
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '购物车ID',
`user_id` bigint(20) NOT NULL COMMENT '用户ID',
`product_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
`price` NUMERIC(10,2) DEFAULT '0.00' COMMENT '商品价格',
`quantity` int(11) DEFAULT NULL COMMENT '商品数量',
`create_date` datetime DEFAULT NULL COMMENT '创建时间',
`update_date` datetime DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

--- 订单表
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
`user_id` bigint(20) NOT NULL COMMENT '用户ID',
`product_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
`price` NUMERIC(10,2) DEFAULT '0.00' COMMENT '商品价格',
`product_count` int(11) DEFAULT NULL COMMENT '商品数量',
`order_price` NUMERIC(10,2) DEFAULT NULL COMMENT '订单总金额',
`state` bigint(5) DEFAULT NULL COMMENT '订单状态 0:未支付｜1:微信支付｜2:支付宝支付｜3:银行卡支付',
`create_date` datetime DEFAULT NULL COMMENT '创建时间',
`end_date` datetime DEFAULT NULL COMMENT '结束时间',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
-- 用于一个订单多个产品
--DROP TABLE IF EXISTS `order_detail`;


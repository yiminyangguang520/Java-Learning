/*
Navicat MariaDB Data Transfer

Source Server         : 192.168.70.114 - mariadb
Source Server Version : 100121
Source Host           : 192.168.70.114:3306
Source Database       : crawler

Target Server Type    : MariaDB
Target Server Version : 100121
File Encoding         : 65001

Date: 2017-05-27 08:29:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for beautiful_pictures
-- ----------------------------
DROP TABLE IF EXISTS `beautiful_pictures`;
CREATE TABLE `beautiful_pictures` (
  `id` varchar(255) NOT NULL COMMENT '美女图片爬取',
  `title` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `pictureurls_num` int(11) DEFAULT NULL,
  `zan` int(11) DEFAULT NULL,
  `biaoqian` varchar(255) DEFAULT NULL,
  `keywords` varchar(255) DEFAULT NULL,
  `last_update_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for picture
-- ----------------------------
DROP TABLE IF EXISTS `picture`;
CREATE TABLE `picture` (
  `id` varchar(255) NOT NULL COMMENT '每张图片的地址',
  `pictures_id` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `last_update_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

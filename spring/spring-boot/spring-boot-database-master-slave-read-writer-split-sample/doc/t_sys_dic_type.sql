/*
Navicat MySQL Data Transfer

Source Server         : docker-master
Source Server Version : 50717
Source Host           : 192.168.249.128:3307
Source Database       : db-test

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-03-07 10:13:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sys_dic_type
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dic_type`;
CREATE TABLE `t_sys_dic_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) NOT NULL DEFAULT '',
  `name` varchar(64) NOT NULL DEFAULT '',
  `status` int(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `code_index` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_dic_type
-- ----------------------------
INSERT INTO `t_sys_dic_type` VALUES ('1', 'goodCategory', '物品分类', '1');
INSERT INTO `t_sys_dic_type` VALUES ('2', 'express', '快递', '1');

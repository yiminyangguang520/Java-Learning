/*
Navicat MySQL Data Transfer

Source Server         : localhost - mysql
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : lee_hikari

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-05-10 15:35:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `ID` int(10) NOT NULL,
  `NAME` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `AGE` int(10) NOT NULL,
  `DEPT` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', 'JIP1', '10', 'IT');
INSERT INTO `employee` VALUES ('2', 'JIP2', '20', 'IT');
INSERT INTO `employee` VALUES ('3', 'JIP3', '30', 'IT');
INSERT INTO `employee` VALUES ('4', 'JIP4', '40', 'IT');

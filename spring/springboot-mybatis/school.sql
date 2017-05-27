/*
Navicat MySQL Data Transfer

Source Server         : localhost - mysql
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : school

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-05-27 14:13:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `gender` char(10) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `teacher_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `teacher` (`teacher_id`),
  CONSTRAINT `teacher` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '梁英飞', '男', '计算机系统结构', '2011', '2');
INSERT INTO `student` VALUES ('2', '付少红', '女', '计算机软件与理论', '2011', '1');
INSERT INTO `student` VALUES ('3', '刘丹', '女', '计算机软件与理论', '2011', '1');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` char(10) DEFAULT NULL,
  `research_area` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', '王念滨', '男', '数据库与数据挖掘', '教授');
INSERT INTO `teacher` VALUES ('2', '张健沛', '男', '数据库', '教授');

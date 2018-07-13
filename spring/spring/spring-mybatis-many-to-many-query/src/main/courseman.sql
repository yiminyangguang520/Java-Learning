/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : courseman

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2014-04-23 22:43:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_code` varchar(20) NOT NULL COMMENT '课程编号',
  `course_name` varchar(50) NOT NULL COMMENT '课程名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', 'zj01', '数据结构');
INSERT INTO `course` VALUES ('2', 'zj02', '离散数学');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `gender` char(1) NOT NULL,
  `major` varchar(20) NOT NULL,
  `grade` char(4) NOT NULL,
  `supervisor_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '陈一斌', '男', '计算机科学与技术', '2011', null);
INSERT INTO `student` VALUES ('6', '李林', '男', '计算机科学与技术', '2011', '1');
INSERT INTO `student` VALUES ('7', '王芳', '女', '计算机科学与技术', '2011', '2');
INSERT INTO `student` VALUES ('11', '方灵', '女', '电子信息工程', '2012', '1');
INSERT INTO `student` VALUES ('12', '马洋', '男', '计算机科学与技术', '2013', null);

-- ----------------------------
-- Table structure for `student_course`
-- ----------------------------
DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL COMMENT '选课学生id',
  `course_id` int(11) NOT NULL COMMENT '所选课程的id',
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `student_course_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_course
-- ----------------------------
INSERT INTO `student_course` VALUES ('2', '6', '1');
INSERT INTO `student_course` VALUES ('3', '7', '1');
INSERT INTO `student_course` VALUES ('11', '7', '2');

-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `gender` char(1) NOT NULL,
  `research_area` varchar(20) NOT NULL,
  `title` varchar(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', '张伟', '男', '软件工程', '讲师');
INSERT INTO `teacher` VALUES ('2', '陈义', '男', '网络工程', '副教授');
INSERT INTO `teacher` VALUES ('3', '马均', '男', '多媒体技术', '副教授');
INSERT INTO `teacher` VALUES ('4', '卢亮', '男', '软件工程', '讲师');

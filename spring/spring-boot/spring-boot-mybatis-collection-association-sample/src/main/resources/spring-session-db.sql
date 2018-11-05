/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.162.138
 Source Server Type    : MariaDB
 Source Server Version : 50556
 Source Host           : 192.168.162.138:3306
 Source Schema         : SpringSessionDB

 Target Server Type    : MariaDB
 Target Server Version : 50556
 File Encoding         : 65001

 Date: 05/11/2018 20:59:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `account_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '账户名称',
  `account_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '账号',
  `account_type` tinyint(1) NULL DEFAULT NULL COMMENT '账户类型：1-一般账户 2-基本账户 3-虚拟账户 4-恒丰银行平台户',
  `account_purpose` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '账户用途',
  `deposit_bank` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '' COMMENT '开户行全称',
  `account_state` tinyint(1) NULL DEFAULT NULL COMMENT '1-已审批  2-已开户 3-销户中 4-已销户 5-等同于2,3,4',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除：1删除；0未删除，默认值为0',
  `customer_id` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '顾客id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `account_num`(`account_num`) USING BTREE,
  INDEX `customer_id`(`customer_id`) USING BTREE,
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `gcf_customer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '账户信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, '账户信息001', '001', 1, '我不带钱、我不带钱、我不带钱、我不带钱、我不带钱、我不带钱、我不带钱、我不带钱、我不带钱、我不带钱、我不带钱、我不带钱、', '北京小贷', NULL, '2018-11-01 16:36:04', '2018-11-01 19:24:40', 0, 11);
INSERT INTO `account` VALUES (2, '账户信息002', '002', 1, 'madongxi', '北京小贷', NULL, '2018-11-01 19:27:52', '2018-11-02 10:24:01', 0, 11);
INSERT INTO `account` VALUES (3, '张三', '69851465', 0, '贷款', '民生银行', NULL, '2018-11-02 13:53:59', '2018-11-02 13:53:59', 0, 45);
INSERT INTO `account` VALUES (4, '1111', '1111', 1, '1111', '', NULL, '2018-11-02 14:06:16', '2018-11-02 14:06:16', 0, 45);
INSERT INTO `account` VALUES (5, '账户信息1', '111111111111', 1, '账户用途账户用途账户用途账户用途账户用途账户用途账户用途账户用途账户用途账户用途账户用途账户用途账户用途账户用途账户用途账户用途账户用途账户用途账户用途账户用途账户用途账户用途账户用途账户用途账户用途账户用途账户用途账户用途账户用途账户用途账户用途', '账户用途', NULL, '2018-11-02 14:38:43', '2018-11-02 14:38:43', 0, 46);
INSERT INTO `account` VALUES (6, '账户信息003', '003', 1, '1', '2', NULL, '2018-11-02 17:36:10', '2018-11-02 21:30:40', 0, 11);
INSERT INTO `account` VALUES (7, '账户信息004', '004', 1, '开户为了赚钱', '民生银行', NULL, '2018-11-02 21:32:10', '2018-11-02 21:32:10', 0, 11);

-- ----------------------------
-- Table structure for account_bill
-- ----------------------------
DROP TABLE IF EXISTS `account_bill`;
CREATE TABLE `account_bill`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `statistics_date` date NULL DEFAULT NULL COMMENT '统计月份',
  `borrow_money` decimal(22, 2) NULL DEFAULT NULL COMMENT '金额借',
  `loan_money` decimal(22, 2) NULL DEFAULT NULL COMMENT '金额贷',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted_date` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除：1删除；0未删除，默认值为0',
  `account_id` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '账户id',
  `customer_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '客户id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `account_id`(`account_id`) USING BTREE,
  INDEX `customer_id`(`customer_id`) USING BTREE,
  CONSTRAINT `account_bill_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `gcf_customer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `account_bill_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '账户流水合计' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of account_bill
-- ----------------------------
INSERT INTO `account_bill` VALUES (1, '2018-01-01', 990.00, 99.00, '2018-11-01 17:27:17', '2018-11-01 20:16:30', '2018-11-02 21:31:38', 1, 1, 11);
INSERT INTO `account_bill` VALUES (2, '2018-01-01', 102.00, 100.00, '2018-11-01 19:46:23', '2018-11-01 20:06:58', '2018-11-02 17:33:13', 1, 2, 11);
INSERT INTO `account_bill` VALUES (3, '2018-05-01', 9999.00, 77477.00, '2018-11-01 20:17:03', '2018-11-01 20:17:03', NULL, 0, 1, 11);
INSERT INTO `account_bill` VALUES (4, '2018-11-01', 888.00, 8888.00, '2018-11-01 21:37:18', '2018-11-01 21:37:20', NULL, 0, 1, 11);
INSERT INTO `account_bill` VALUES (5, '2018-02-01', 103.00, 102.00, '2018-11-02 09:21:44', '2018-11-02 09:44:25', NULL, 0, 2, 11);
INSERT INTO `account_bill` VALUES (6, '2018-03-01', 104.00, 104.00, '2018-11-02 09:50:22', '2018-11-02 09:50:22', NULL, 0, 2, 11);
INSERT INTO `account_bill` VALUES (7, '2018-05-01', 105.00, NULL, '2018-11-02 09:57:05', '2018-11-02 10:04:47', '2018-11-02 10:18:58', 1, 2, 11);
INSERT INTO `account_bill` VALUES (10, '2018-08-01', NULL, NULL, '2018-11-02 10:04:46', '2018-11-02 10:04:46', '2018-11-02 10:18:56', 1, 2, NULL);
INSERT INTO `account_bill` VALUES (11, '2018-05-01', 1.00, 2.00, '2018-11-02 10:20:47', '2018-11-02 10:20:47', '2018-11-02 17:36:22', 1, 2, NULL);
INSERT INTO `account_bill` VALUES (12, '2018-02-01', 2.00, 3.00, '2018-11-02 10:22:53', '2018-11-02 10:22:53', NULL, 1, 1, NULL);
INSERT INTO `account_bill` VALUES (13, '2018-06-01', 1.00, NULL, '2018-11-02 10:34:03', '2018-11-02 10:34:03', '2018-11-02 10:41:35', 1, 2, NULL);
INSERT INTO `account_bill` VALUES (14, '2018-05-01', 4545.00, 7523.00, '2018-11-02 10:35:51', '2018-11-02 10:35:51', '2018-11-02 17:33:17', 1, 2, NULL);
INSERT INTO `account_bill` VALUES (15, '2018-11-02', 250.00, 369.00, '2018-11-02 14:15:16', '2018-11-02 14:15:18', NULL, 0, 5, NULL);
INSERT INTO `account_bill` VALUES (16, '2018-01-01', 1.00, 2.00, '2018-11-02 14:39:00', '2018-11-02 14:39:00', NULL, 0, 5, NULL);
INSERT INTO `account_bill` VALUES (17, '2018-02-01', 1.00, 3.00, '2018-11-02 14:39:57', '2018-11-02 14:40:24', NULL, 0, 5, NULL);
INSERT INTO `account_bill` VALUES (18, '2018-05-01', 2.00, 3.00, '2018-11-02 14:58:05', '2018-11-02 14:58:05', NULL, 0, 5, NULL);
INSERT INTO `account_bill` VALUES (19, '2018-06-01', 2.00, 4.00, '2018-11-02 14:58:05', '2018-11-02 14:58:05', NULL, 0, 5, NULL);
INSERT INTO `account_bill` VALUES (20, '2018-05-01', 1.00, 2.00, '2018-11-02 17:36:34', '2018-11-02 17:36:34', '2018-11-02 17:37:17', 1, 6, NULL);
INSERT INTO `account_bill` VALUES (21, '2018-02-01', 555.00, 6666.00, '2018-11-02 21:31:19', '2018-11-02 21:31:19', '2018-11-02 21:32:06', 1, 6, NULL);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `display_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `category` int(11) NULL DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT 1,
  `last_update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '/admin/dashboard', 'Admin Dashboard', '/admin/dashboard', NULL, 'Admin Dashboard', 1, '2018-11-03 11:09:58');
INSERT INTO `menu` VALUES (2, '/admin/profile', 'Admin Profile', '/admin/profile', NULL, 'Admin Profile', 1, '2018-11-03 11:09:58');
INSERT INTO `menu` VALUES (3, '/user/dashboard', 'User Dashboard', '/user/dashboard', NULL, 'User Dashboard', 1, '2018-11-03 11:09:58');
INSERT INTO `menu` VALUES (4, '/user/profile', 'User Profile', '/user/profile', NULL, 'User Profile', 1, '2018-11-03 11:09:58');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT 1,
  `last_update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `NAME`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'ROLE_ADMIN', 'Administrator', 1, '2018-11-03 11:09:38');
INSERT INTO `role` VALUES (2, 'ROLE_USER', 'User', 1, '2018-11-03 11:09:38');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `last_update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_id`(`role_id`, `menu_id`) USING BTREE,
  INDEX `fk_role_menu_xref_menu_id`(`menu_id`) USING BTREE,
  CONSTRAINT `role_menu_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_menu_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (1, 1, 1, '2018-11-03 11:10:15');
INSERT INTO `role_menu` VALUES (2, 1, 2, '2018-11-03 11:10:15');
INSERT INTO `role_menu` VALUES (3, 2, 3, '2018-11-03 11:10:15');
INSERT INTO `role_menu` VALUES (4, 2, 4, '2018-11-03 11:10:15');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT 1,
  `expired` tinyint(1) NOT NULL DEFAULT 1,
  `locked` tinyint(1) NOT NULL DEFAULT 1,
  `last_update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `comment` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin', 1, 0, 0, '2018-11-05 19:41:52', NULL);
INSERT INTO `user` VALUES (2, 'user', 'password', 1, 0, 0, '2018-11-05 19:41:52', NULL);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `last_update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fK_user_role_xref_user_id_user_id`(`user_id`) USING BTREE,
  INDEX `fk_user_role_xref_role_id_role_id`(`role_id`) USING BTREE,
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1, '2018-11-05 19:46:10');
INSERT INTO `user_role` VALUES (2, 2, 2, '2018-11-05 19:46:10');

SET FOREIGN_KEY_CHECKS = 1;

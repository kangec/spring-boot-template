/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : vcms_v2

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 18/11/2020 09:37:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问编号',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `ip` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录地点',
  `browser` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浏览器',
  `system` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  `login_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录状态',
  `login_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作信息',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '登录日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operate_log`;
CREATE TABLE `sys_operate_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `runTime` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `logType` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `log_function` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口',
  `log_focus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志主诉',
  `log_summary` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志概要(暂存Ip)',
  `returnValue` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '返回值',
  `log_args_record` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求参数记录',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 547628 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_operate_log
-- ----------------------------
INSERT INTO `sys_operate_log` VALUES (547616, NULL, NULL, '2020-10-16 11:10:30', '17', 'SELECT', NULL, 'INFO', '测试', 'void', '{session=org.apache.catalina.session.StandardSessionFacade@44b7236b}');
INSERT INTO `sys_operate_log` VALUES (547617, NULL, NULL, '2020-10-16 11:10:36', '0', 'SELECT', NULL, 'INFO', '测试', 'void', '{session=org.apache.catalina.session.StandardSessionFacade@44b7236b}');
INSERT INTO `sys_operate_log` VALUES (547618, NULL, NULL, '2020-10-25 13:36:40', '111', 'UNKNOWN', NULL, 'INFO', ' ', 'java.lang.Object', 'Null');
INSERT INTO `sys_operate_log` VALUES (547619, NULL, NULL, '2020-10-25 13:36:45', '1', 'UNKNOWN', NULL, 'INFO', ' ', 'java.lang.Object', 'Null');
INSERT INTO `sys_operate_log` VALUES (547620, NULL, NULL, '2020-10-26 02:46:27', '271', 'UNKNOWN', NULL, 'INFO', ' ', 'java.lang.Object', 'Null');
INSERT INTO `sys_operate_log` VALUES (547621, NULL, NULL, '2020-10-26 03:19:36', '1', 'UNKNOWN', NULL, 'INFO', ' ', 'java.lang.Object', 'Null');
INSERT INTO `sys_operate_log` VALUES (547622, NULL, NULL, '2020-10-26 04:16:02', '1', 'UNKNOWN', NULL, 'INFO', ' ', 'java.lang.Object', 'Null');
INSERT INTO `sys_operate_log` VALUES (547623, NULL, NULL, '2020-11-03 15:11:38', '662', 'UNKNOWN', NULL, 'INFO', ' ', 'java.lang.Object', 'Null');
INSERT INTO `sys_operate_log` VALUES (547624, NULL, NULL, '2020-11-05 13:22:50', '18', 'DELETE', NULL, 'INFO', '', 'com.kangec.vcms.entity.ResultResponse', '{roleId=1}');
INSERT INTO `sys_operate_log` VALUES (547625, NULL, NULL, '2020-11-16 12:56:03', '4', 'SELECT', NULL, 'INFO', '测试', 'void', '{session=org.apache.catalina.session.StandardSessionFacade@6716ee36}');
INSERT INTO `sys_operate_log` VALUES (547626, NULL, NULL, '2020-11-16 13:00:30', '3', 'SELECT', NULL, 'INFO', '测试', 'void', '{session=org.apache.catalina.session.StandardSessionFacade@35d7d308}');
INSERT INTO `sys_operate_log` VALUES (547627, NULL, NULL, '2020-11-16 13:17:57', '3', 'SELECT', NULL, 'INFO', '测试', 'void', '{session=org.apache.catalina.session.StandardSessionFacade@61748829}');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'ROLE_ADMIN');
INSERT INTO `sys_role` VALUES (2, 'ROLE_USER');
INSERT INTO `sys_role` VALUES (3, 'ROLE_DRIVER');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(5) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (3, 'admin', '123456', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (4, 'admin2', '123456', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (5, 'user', '123456', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (6, 'driver', '123456', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `FK_ROLE_ID`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (3, 1);
INSERT INTO `sys_user_role` VALUES (3, 2);
INSERT INTO `sys_user_role` VALUES (4, 2);
INSERT INTO `sys_user_role` VALUES (5, 2);
INSERT INTO `sys_user_role` VALUES (3, 3);
INSERT INTO `sys_user_role` VALUES (4, 3);
INSERT INTO `sys_user_role` VALUES (6, 3);

SET FOREIGN_KEY_CHECKS = 1;

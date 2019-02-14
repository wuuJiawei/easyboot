/*
 Navicat Premium Data Transfer

 Source Server         : localhost-3306
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : joauth2

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 14/02/2019 17:14:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appkey` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `appsecret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` int(255) NULL DEFAULT NULL,
  `disabled` int(255) NULL DEFAULT NULL,
  `orders` int(255) NULL DEFAULT NULL,
  `ctime` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `begin_time` datetime(0) NULL DEFAULT NULL,
  `intervals` int(255) NULL DEFAULT NULL,
  `verify_status` int(255) NULL DEFAULT NULL,
  `verify_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `creator` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for app_record
-- ----------------------------
DROP TABLE IF EXISTS `app_record`;
CREATE TABLE `app_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ctime` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `appid` int(11) NULL DEFAULT NULL COMMENT 'APP ID',
  `endtime` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '应用授权记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `ctime` datetime(0) NULL DEFAULT NULL,
  `endtime` datetime(0) NULL DEFAULT NULL,
  `exctime` float NULL DEFAULT NULL,
  `level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `logger_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `host` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `thread` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `clasz` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `line_number` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `throwables` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1, '2019-02-13 21:47:17', '2019-02-13 21:47:17', 0, NULL, NULL, NULL, '0:0:0:0:0:0:0:1', NULL, 'com.easyboot.controller.LoginController', 'doLogin', '18018266280,admin,null,false,', NULL, '登录', NULL, '/login', 1);
INSERT INTO `sys_log` VALUES (2, '2019-02-13 21:47:18', '2019-02-13 21:47:18', 0, NULL, NULL, NULL, '0:0:0:0:0:0:0:1', NULL, 'com.easyboot.controller.IndexController', 'index', '', NULL, '访问主页', NULL, '/index', 1);
INSERT INTO `sys_log` VALUES (3, '2019-02-13 22:22:27', '2019-02-13 22:22:27', 0, NULL, NULL, NULL, '0:0:0:0:0:0:0:1', NULL, 'com.easyboot.controller.IndexController', 'index', '', NULL, '访问主页', NULL, '/index', 1);
INSERT INTO `sys_log` VALUES (4, '2019-02-13 22:22:33', '2019-02-13 22:22:33', 0, NULL, NULL, NULL, '0:0:0:0:0:0:0:1', NULL, 'com.easyboot.controller.UserController', 'query', 'com.easyboot.common.layui.LayTableArg@5c9b687c,', NULL, '查询a1测试系统日志测试用户用户', NULL, '/user/query', 1);
INSERT INTO `sys_log` VALUES (5, '2019-02-13 22:22:35', '2019-02-13 22:22:35', 0, NULL, NULL, NULL, '0:0:0:0:0:0:0:1', NULL, 'com.easyboot.controller.IndexController', 'index', '', NULL, '访问主页', NULL, '/index', 1);
INSERT INTO `sys_log` VALUES (6, '2019-02-13 22:22:36', '2019-02-13 22:22:36', 0, NULL, NULL, NULL, '0:0:0:0:0:0:0:1', NULL, 'com.easyboot.controller.UserController', 'query', 'com.easyboot.common.layui.LayTableArg@7a53034,', NULL, '查询a1测试系统日志测试用户用户', NULL, '/user/query', 1);
INSERT INTO `sys_log` VALUES (7, '2019-02-13 22:26:21', '2019-02-13 22:26:21', 0.001, NULL, NULL, NULL, '0:0:0:0:0:0:0:1', NULL, 'com.easyboot.controller.IndexController', 'index', '', NULL, '访问主页', NULL, '/index', 1);
INSERT INTO `sys_log` VALUES (8, '2019-02-13 22:26:22', '2019-02-13 22:26:22', 0, NULL, NULL, NULL, '0:0:0:0:0:0:0:1', NULL, 'com.easyboot.controller.UserController', 'query', 'com.easyboot.common.layui.LayTableArg@5f289bc5,', NULL, '查询a1测试系统日志测试用户用户', NULL, '/user/query', 1);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `disabled` int(255) NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `pid` int(11) NULL DEFAULT NULL,
  `orders` int(255) NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, '/', 0, 0, NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES (2, '操作日志', 0, '/log/', 1, 6, NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES (3, '用户管理', 0, '/user/', 1, 1, NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES (4, '添加用户', 0, '/', 3, 0, NULL, 'user:add', 0);
INSERT INTO `sys_menu` VALUES (5, '编辑用户', 0, '/', 3, 1, NULL, 'user:edit', 0);
INSERT INTO `sys_menu` VALUES (6, '删除用户', 0, '/', 3, 2, NULL, 'user:delete', 0);
INSERT INTO `sys_menu` VALUES (7, '应用管理', 0, '/', 0, 2, NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES (8, '应用列表', 0, '/app/', 7, 0, NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES (9, '创建应用', 0, '/app/apply', 7, 1, NULL, NULL, 1);
INSERT INTO `sys_menu` VALUES (10, '数据统计', 0, '/app/record', 7, 2, NULL, NULL, 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ctime` datetime(0) NULL DEFAULT NULL,
  `disabled` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`roleId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `rm_id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NULL DEFAULT NULL,
  `menuId` int(11) NULL DEFAULT NULL,
  `ctime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`rm_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ctime` datetime(0) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `locked` int(255) NULL DEFAULT NULL,
  `last_login_time` datetime(0) NULL DEFAULT NULL,
  `orders` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '18018266280', '437e3173a94c46ad32162919891f85ae', '武佳伟', NULL, NULL, NULL, 0, NULL, 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NULL DEFAULT NULL,
  `roleId` int(11) NULL DEFAULT NULL,
  `ctime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

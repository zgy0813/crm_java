/*
 Navicat Premium Data Transfer

 Source Server         : 47.103.73.141
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : 47.103.73.141:9306
 Source Schema         : website

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 24/10/2019 09:50:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_auth
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth`;
CREATE TABLE `sys_auth`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `permission` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '权限标识',
  `action` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '权限动作',
  `state` int(4) NULL DEFAULT NULL COMMENT '状态',
  `remark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_auth
-- ----------------------------
INSERT INTO `sys_auth` VALUES (1, '查看用户', 'user', 'read', 0, '查看用户数据的权限');
INSERT INTO `sys_auth` VALUES (2, '新增用户', 'user', 'add', 0, '新增用户数据的权限');
INSERT INTO `sys_auth` VALUES (3, '修改用户', 'user', 'update', 0, '修改用户数据的权限');
INSERT INTO `sys_auth` VALUES (4, '删除用户', 'user', 'delete', 1, '删除用户数据的权限');
INSERT INTO `sys_auth` VALUES (5, '新增视频', 'video', 'add', 0, '新增视频数据的权限');
INSERT INTO `sys_auth` VALUES (6, '查看视频', 'video', 'read', 0, '查看视频数据的权限');
INSERT INTO `sys_auth` VALUES (7, '修改视频', 'video', 'update', 0, '修改视频数据的权限');
INSERT INTO `sys_auth` VALUES (8, '删除视频', 'video', 'delete', 1, '删除视频数据的权限');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `code` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '角色编码',
  `state` int(4) NULL DEFAULT 0 COMMENT '状态',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_role_code`(`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 'admin', 0, '管理员角色');
INSERT INTO `sys_role` VALUES (2, '用户', 'user', 0, '普通用户角色');

-- ----------------------------
-- Table structure for sys_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_auth`;
CREATE TABLE `sys_role_auth`  (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) NULL DEFAULT NULL,
  `auth_id` bigint(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_auth
-- ----------------------------
INSERT INTO `sys_role_auth` VALUES (1, 1, 1);
INSERT INTO `sys_role_auth` VALUES (2, 1, 2);
INSERT INTO `sys_role_auth` VALUES (3, 1, 3);
INSERT INTO `sys_role_auth` VALUES (4, 1, 4);
INSERT INTO `sys_role_auth` VALUES (5, 1, 5);
INSERT INTO `sys_role_auth` VALUES (6, 1, 6);
INSERT INTO `sys_role_auth` VALUES (7, 1, 7);
INSERT INTO `sys_role_auth` VALUES (8, 1, 8);
INSERT INTO `sys_role_auth` VALUES (9, 2, 1);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `sex` int(4) NULL DEFAULT NULL COMMENT '性别',
  `avatar` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '头像地址',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `account` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '账户名称',
  `password` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '密码',
  `create_time` date NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` date NULL DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '管理员', 123, NULL, 'group1/M00/00/00/rBN-X11p8NeAY7yfAAAHONsHdZU156.png', 'weqee@163.com', 'admin', '038bdaf98f2037b31f1e75b5b4c9b26e', NULL, NULL);
INSERT INTO `sys_user` VALUES (2, '张三', 20, NULL, NULL, 'test2@baomidou.com', 'zhang', '2823ef8d65a3b6ba7b1da8b2aa63eff5', NULL, NULL);
INSERT INTO `sys_user` VALUES (3, 'Tom', 28, NULL, NULL, 'test3@baomidou.com', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (4, 'Sandy', 21, NULL, NULL, 'test4@baomidou.com', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (5, 'Billie', 24, NULL, NULL, 'test5@baomidou.com', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (6, 'Jone', 18, NULL, NULL, 'test1@baomidou.com', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (7, 'x', 12, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (8, 't', 13, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (9, 'y', 14, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (10, 'u', 15, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (11, 'i', 16, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (12, 'o', 17, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (13, 'p', 23, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (14, 'w', 22, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (1164789884206972930, 'zhang', 12, NULL, NULL, 'lqf@163.com', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (1164790062037086209, 'zhang', 12, NULL, NULL, 'lqf@163.com', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (1164790247525998594, 'zhang', 12, NULL, NULL, 'lqf@163.com', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `role_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2, 2);
INSERT INTO `sys_user_role` VALUES (1171623788406706177, 1171623788385734657, 2);
INSERT INTO `sys_user_role` VALUES (1171631392453369857, 1171631383821492225, 2);
INSERT INTO `sys_user_role` VALUES (1171631549488111617, 1171631549462945793, 2);
INSERT INTO `sys_user_role` VALUES (1174609322347368450, 1174609322318008322, 2);
INSERT INTO `sys_user_role` VALUES (1174612768895025153, 1174612768874053634, 2);

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '手机号',
  `mail` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '邮箱',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `sex` int(4) NULL DEFAULT NULL COMMENT '性别',
  `avatar` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '头像地址',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `account` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '账户名称',
  `password` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '密码',
  `invite_id` bigint(255) NULL DEFAULT NULL COMMENT '邀请码id',
  `watch_count` int(10) NULL DEFAULT NULL COMMENT '每天观影次数',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_user_phone`(`phone`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES (1, '管理员', '17718177074', NULL, 123, NULL, 'group1/M00/00/00/rBN-X11p8NeAY7yfAAAHONsHdZU156.png', 'weqee@163.com', 'admin', '038bdaf98f2037b31f1e75b5b4c9b26e', NULL, NULL, NULL, '2019-09-26 09:58:51');

-- ----------------------------
-- Table structure for tbl_website
-- ----------------------------
DROP TABLE IF EXISTS `tbl_website`;
CREATE TABLE `tbl_website`  (
  `id` bigint(20) NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实网址',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网址密文标识',
  `pic_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '二维码图片地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_website
-- ----------------------------
INSERT INTO `tbl_website` VALUES (1182572015370358785, 'http://www.jianshu.com/', 'tmgf7vw0', '/pic/tmgf7vw0.PNG');
INSERT INTO `tbl_website` VALUES (1182572582427676673, 'http://www.jianshu.com/', 'pzjufwi3', '/pic/pzjufwi3.PNG');
INSERT INTO `tbl_website` VALUES (1182706813908799489, 'http://www.udp2p.com/', 's6i7kfn5', '/pic/s6i7kfn5.PNG');
INSERT INTO `tbl_website` VALUES (1182707429682958338, 'http://www.udp2p.com/', 'jj64c0wj', '/pic/jj64c0wj.PNG');
INSERT INTO `tbl_website` VALUES (1182711020044931074, 'http://www.baidu.com', '4x90e2db', '/pic/4x90e2db.PNG');
INSERT INTO `tbl_website` VALUES (1182711217105915905, 'http://www.77689.com', 'f995qf68', '/pic/f995qf68.PNG');
INSERT INTO `tbl_website` VALUES (1182711255546712066, 'http://77689.com', 'd27w6txx', '/pic/d27w6txx.PNG');
INSERT INTO `tbl_website` VALUES (1182712408896425986, 'http://baidu.com', 'jn8z2if0', '/pic/jn8z2if0.PNG');
INSERT INTO `tbl_website` VALUES (1183704335754186753, 'https://www.vs53z.com', '1a1db1wy', '/pic/1a1db1wy.PNG');
INSERT INTO `tbl_website` VALUES (1183705032709099521, 'http://www.vs53z.com', 'cmd1g587', '/pic/cmd1g587.PNG');
INSERT INTO `tbl_website` VALUES (1186386889842085890, 'https://www-209.com/', '9r06w8t7', '/pic/9r06w8t7.PNG');
INSERT INTO `tbl_website` VALUES (1186386950399447042, 'https://www-209.com/', 'ffd54d4e', '/pic/ffd54d4e.PNG');

SET FOREIGN_KEY_CHECKS = 1;

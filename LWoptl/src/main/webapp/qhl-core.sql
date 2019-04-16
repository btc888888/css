/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : qhl-core

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 03/01/2019 18:14:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for data_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `data_dictionary`;
CREATE TABLE `data_dictionary`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '主键',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '字典编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '字典名称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '备注',
  `order_num` int(11) NULL DEFAULT 1 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_croatian_ci COMMENT = '数据字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of data_dictionary
-- ----------------------------
INSERT INTO `data_dictionary` VALUES ('40288ae76812ae55016812bc5be9002c', 'logType', '日志类型', '系统日志，用于演示快速引用业务字典', 1);

-- ----------------------------
-- Table structure for data_dictionary_value
-- ----------------------------
DROP TABLE IF EXISTS `data_dictionary_value`;
CREATE TABLE `data_dictionary_value`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '主键',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '数据值',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '数据名称',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '排序',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '备注',
  `dictionary_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '字典编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_croatian_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of data_dictionary_value
-- ----------------------------
INSERT INTO `data_dictionary_value` VALUES ('40288ae76812ae55016812bcc5060037', '操作日志', '操作日志', 1, '字典编号：logType', 'logType');
INSERT INTO `data_dictionary_value` VALUES ('40288ae76812ae55016812bcfc740039', '数据日志', '数据日志', 2, '字典编号：logType', 'logType');
INSERT INTO `data_dictionary_value` VALUES ('40288ae76812ae55016812bd288b003b', '登录日志', '登录日志', 3, '字典编号：logType', 'logType');

-- ----------------------------
-- Table structure for file_uploaded
-- ----------------------------
DROP TABLE IF EXISTS `file_uploaded`;
CREATE TABLE `file_uploaded`  (
  `id` bigint(20) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `file_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_size` bigint(20) NULL DEFAULT NULL COMMENT 'KB',
  `save_path` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `objectId` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '附件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_function
-- ----------------------------
DROP TABLE IF EXISTS `sys_function`;
CREATE TABLE `sys_function`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `func_name` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '功能名称',
  `is_stop` int(11) NULL DEFAULT NULL COMMENT '是否启用(0:是,1:否)',
  `link_page` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '功能url',
  `parent_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级编号',
  `parent_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级名称',
  `func_type` int(11) NULL DEFAULT NULL COMMENT '功能类型(0:菜单,1:按钮)',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `order_no` int(11) NULL DEFAULT NULL COMMENT '排序',
  `descript` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注释',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统功能表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_function
-- ----------------------------
INSERT INTO `sys_function` VALUES ('data_dictionary', '业务字典', 0, '/portal/core/dictionary', 'sys_manager', '系统管理', 0, NULL, 5, NULL);
INSERT INTO `sys_function` VALUES ('data_dictionary_add', '添加', 0, '/portal/core/dictionary/save', 'data_dictionary', '数据字典', 1, NULL, 1, NULL);
INSERT INTO `sys_function` VALUES ('data_dictionary_delete', '删除', 0, '/portal/core/dictionary/delete', 'data_dictionary', '数据字典', 1, NULL, 3, NULL);
INSERT INTO `sys_function` VALUES ('data_dictionary_update', '修改', 0, '/portal/core/dictionary/update', 'data_dictionary', '数据字典', 1, NULL, 2, NULL);
INSERT INTO `sys_function` VALUES ('data_dictionary_value', '字典数据值', 0, NULL, 'data_dictionary', '数据字典', 1, NULL, 4, NULL);
INSERT INTO `sys_function` VALUES ('data_dictionary_value_add', '添加', 0, '/portal/core/dictionary/value/add', 'data_dictionary_value', '字典数据值', 1, NULL, 5, NULL);
INSERT INTO `sys_function` VALUES ('data_dictionary_value_delete', '删除', 0, '/portal/core/dictionary/value/delete', 'data_dictionary_value', '字典数据值', 1, NULL, 7, NULL);
INSERT INTO `sys_function` VALUES ('data_dictionary_value_update', '修改', 0, '/portal/core/dictionary/update', 'data_dictionary_value', '字典数据值', 1, NULL, 6, NULL);
INSERT INTO `sys_function` VALUES ('frame_main_view', '主页', 0, 'http://www.meiwenhome.com/qinhaisenlin/xiaoxi/love.html', 'frame', 'JFinal极速开发企业应用管理系统', 0, 'layui-icon-home', 2, NULL);
INSERT INTO `sys_function` VALUES ('pub_fileList', '附件列表', 0, '/portal/getFileList', 'sys', '网站后台管理', 0, 'layui-icon-read', 5, NULL);
INSERT INTO `sys_function` VALUES ('sys', '网站后台管理', 0, NULL, 'frame', 'JFinal极速开发企业应用管理系统', 0, NULL, 1, NULL);
INSERT INTO `sys_function` VALUES ('sys_druid', '性能监控', 0, '/assets/druid', 'sys', '网站后台管理', 0, 'layui-icon-engine', 3, NULL);
INSERT INTO `sys_function` VALUES ('sys_echart', 'Echart图表', 0, '/portal/echart', 'sys', '网站后台管理', 0, 'layui-icon-chart', 2, NULL);
INSERT INTO `sys_function` VALUES ('sys_func_add', '添加', 0, '/portal/core/sysFunc/save', 'sys_func_manager', '功能管理', 1, NULL, 1, NULL);
INSERT INTO `sys_function` VALUES ('sys_func_delete', '删除', 0, '/portal/core/sysFunc/delete', 'sys_func_manager', '功能管理', 1, NULL, 3, NULL);
INSERT INTO `sys_function` VALUES ('sys_func_manager', '功能管理', 0, '/portal/core/sysFunc', 'sys_manager', '系统管理', 0, 'layui-icon-wu', 1, NULL);
INSERT INTO `sys_function` VALUES ('sys_func_update', '修改', 0, '/portal/core/sysFunc/update', 'sys_func_manager', '功能管理', 1, NULL, 2, NULL);
INSERT INTO `sys_function` VALUES ('sys_log', '系统日志', 0, '/portal/core/sysLog', 'sys_manager', '系统管理', 0, NULL, 6, NULL);
INSERT INTO `sys_function` VALUES ('sys_manager', '系统管理', 0, NULL, 'sys', '网站后台管理', 0, 'layui-icon-set', 1, NULL);
INSERT INTO `sys_function` VALUES ('sys_org_add', '添加', 0, '/portal/core/sysOrg/add', 'sys_org_manager', '部门管理', 1, NULL, 1, NULL);
INSERT INTO `sys_function` VALUES ('sys_org_delete', '删除', 0, '/portal/core/sysOrg/delete', 'sys_org_manager', '部门管理', 1, NULL, 3, NULL);
INSERT INTO `sys_function` VALUES ('sys_org_manager', '部门管理', 0, '/portal/core/sysOrg', 'sys_manager', '系统管理', 0, 'layui-icon-wu', 4, NULL);
INSERT INTO `sys_function` VALUES ('sys_org_update', '修改 ', 0, '/portal/core/sysOrg/update', 'sys_org_manager', '部门管理', 1, NULL, 2, NULL);
INSERT INTO `sys_function` VALUES ('sys_pub_upload', '上传页面', 0, '/portal/toUpload', 'sys', '网站后台管理', 0, 'layui-icon-upload-drag', 4, NULL);
INSERT INTO `sys_function` VALUES ('sys_role_add', '添加', 0, '/portal/core/sysRole/save', 'sys_role_manager', '角色管理', 1, NULL, 1, NULL);
INSERT INTO `sys_function` VALUES ('sys_role_delete', '删除', 0, '/portal/core/sysRole/delete', 'sys_role_manager', '角色管理', 1, NULL, 3, NULL);
INSERT INTO `sys_function` VALUES ('sys_role_manager', '角色管理', 0, '/portal/core/sysRole', 'sys_manager', '系统管理', 0, 'layui-icon-wu', 2, NULL);
INSERT INTO `sys_function` VALUES ('sys_role_update', '修改', 0, '/portal/core/sysRole/update', 'sys_role_manager', '角色管理', 1, NULL, 2, NULL);
INSERT INTO `sys_function` VALUES ('sys_user_add', '添加', 0, '/portal/core/sysUser/save', 'sys_user_manager', '用户管理', 1, NULL, 1, NULL);
INSERT INTO `sys_function` VALUES ('sys_user_delete', '删除', 0, '/portal/core/sysUser/delete', 'sys_user_manager', '用户管理', 1, NULL, 3, NULL);
INSERT INTO `sys_function` VALUES ('sys_user_manager', '用户管理', 0, '/portal/core/sysUser', 'sys_manager', '系统管理', 0, 'layui-icon-wu', 3, NULL);
INSERT INTO `sys_function` VALUES ('sys_user_reset', '重置密码', 0, '/portal/core/sysUser/reset', 'sys_user_manager', '用户管理', 1, NULL, 5, NULL);
INSERT INTO `sys_function` VALUES ('sys_user_role', '角色', 0, '/portal/core/sysUser/userRole', 'sys_user_manager', '用户管理', 1, NULL, 6, NULL);
INSERT INTO `sys_function` VALUES ('sys_user_update', '修改', 0, '/portal/core/sysUser/update', 'sys_user_manager', '用户管理', 1, NULL, 2, NULL);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL,
  `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL,
  `data` text CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `user_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL,
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_croatian_ci COMMENT = '系统日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('40288ae768132a540168132f5aa50003', '/pub/login/submit', '登录', '{password=[6e5ee632b5020f7ec3b7f1007a8ab4a09f1c6a64410ffbdffc0198d7cd1e025220ffac783ac7884e20124fbc3d48fcb76d17be558605cf9fe4bddf6f7558927d0b3a63f330fd476836459ee0ce921456c97d95cb3789b2490fa1e4779f647180fd237a0085cb75a66aaf79378b31226562f2998b239b1608112cf1aca2c49424],verifyCode=[],returnUrl=[/],userCode=[admin]}', '2019-01-03 18:08:17', 'admin(管理员)', '登录日志', '192.168.10.103');
INSERT INTO `sys_log` VALUES ('40288ae768132a540168132f649b0005', '/portal/core/sysLog', '系统日志', '{}', '2019-01-03 18:08:20', 'admin(管理员)', '操作日志(系统管理)', '192.168.10.103');
INSERT INTO `sys_log` VALUES ('40288ae768132a5401681332f6360008', '/portal/getFileList', '附件列表', '{}', '2019-01-03 18:12:13', 'admin(管理员)', '操作日志(网站后台管理)', '192.168.10.103');
INSERT INTO `sys_log` VALUES ('40288ae768132a540168133305ca0009', '/portal/delete', '删除', '{}', '2019-01-03 18:12:17', 'admin(管理员)', '数据日志', '192.168.10.103');
INSERT INTO `sys_log` VALUES ('40288ae768132a5401681333080d000a', '/portal/getFileList', '附件列表', '{}', '2019-01-03 18:12:18', 'admin(管理员)', '操作日志(网站后台管理)', '192.168.10.103');
INSERT INTO `sys_log` VALUES ('40288ae768132a5401681333bd70000b', '/portal/core/sysUser', '用户管理', '{}', '2019-01-03 18:13:04', 'admin(管理员)', '操作日志(系统管理)', '192.168.10.103');

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `org_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门编号和主键默认',
  `parentid` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级部门编号',
  `parentid_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级部门名称',
  `org_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名称',
  `chief` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门负责人姓名',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门负责人电话',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门负责人手机号',
  `email` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门负责人邮件',
  `descript` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  `level` decimal(8, 0) NULL DEFAULT NULL COMMENT '级别',
  `isstop` decimal(8, 0) NULL DEFAULT NULL COMMENT '是否停用\r\n1：停用；\r\n0：启用；',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES ('40288ae767ee75f70167ee7bae0a0001', '40288ae767ee75f70167ee7bae0a0001', 'ff808081616e1efd01616e2c89af0000', '绘宇智能', '人事部', NULL, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_org` VALUES ('40288ae767ee75f70167ee7c06ba0002', '40288ae767ee75f70167ee7c06ba0002', 'ff808081616e1efd01616e2c89af0000', '绘宇智能', '财务部', NULL, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_org` VALUES ('40288ae767ee75f70167ee7c94220003', '40288ae767ee75f70167ee7c94220003', 'ff80808161e4629f0161e49ac1d30001', '系统集成部', '研发部', NULL, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_org` VALUES ('40288ae767ee75f70167ee7cb5120004', '40288ae767ee75f70167ee7cb5120004', 'ff80808161e4629f0161e49ac1d30001', '系统集成部', '项目部', NULL, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_org` VALUES ('ff808081616e1efd01616e2c89af0000', 'ff808081616e1efd01616e2c89af0000', 'sys', '组织机构', '绘宇智能', '一木森', NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `sys_org` VALUES ('ff80808161e4629f0161e49ac1d30001', 'ff80808161e4629f0161e49ac1d30001', 'ff808081616e1efd01616e2c89af0000', '绘宇智能', '系统集成部', NULL, NULL, NULL, NULL, NULL, NULL, 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `descript` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_stop` int(11) NOT NULL,
  `orgid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `visit_view` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('40288ae7677c6ab801677c6c30ac0001', NULL, 1, NULL, 'superadmin', 'admin', '管理员', NULL, NULL);
INSERT INTO `sys_role` VALUES ('40288ae767ab41450167ab8d9a1f0001', NULL, 1, NULL, 'admin', 'zjl', '总经理', NULL, NULL);
INSERT INTO `sys_role` VALUES ('40288ae767afdfee0167afe17c9c0001', NULL, 1, NULL, 'superadmin', 'xzb', '行政部', 'superadmin', NULL);
INSERT INTO `sys_role` VALUES ('40288ae767afdfee0167afe1c8c00002', NULL, 1, NULL, 'superadmin', 'jsb', '技术部', 'superadmin', NULL);
INSERT INTO `sys_role` VALUES ('40288ae767afdfee0167afe225390003', NULL, 1, NULL, 'jsb', 'yf', '研发部', 'superadmin', NULL);
INSERT INTO `sys_role` VALUES ('40288ae767afdfee0167afe269e80004', NULL, 1, NULL, 'jsb', 'xmb', '项目部', 'superadmin', NULL);
INSERT INTO `sys_role` VALUES ('40288ae767afdfee0167afe2e1410005', NULL, 1, NULL, 'yf', 'pt', '平台研发', 'superadmin', NULL);
INSERT INTO `sys_role` VALUES ('40288ae767afdfee0167afe33a480006', NULL, 1, NULL, 'yf', 'gis', 'gis研发', 'superadmin', NULL);
INSERT INTO `sys_role` VALUES ('40288ae767ed898c0167ed8c1e110001', NULL, 1, NULL, 'admin', 'admin3', '人事部', 'admin', NULL);
INSERT INTO `sys_role` VALUES ('40288ae767ee75f70167ee7fe5720005', NULL, 1, NULL, 'admin', 'xtjcb', '系统集成部', 'admin', NULL);
INSERT INTO `sys_role` VALUES ('40288ae767eeb06b0167eeb185410001', NULL, 1, NULL, 'xtjcb', 'yfb', '研发部', 'admin', NULL);
INSERT INTO `sys_role` VALUES ('ff80808161f5012f0161f53666ef0000', '管理系统所有角色', 1, 'ff808081616e1efd01616e2c89af0000', 'sys', 'superadmin', '超级管理员', 'admin', NULL);

-- ----------------------------
-- Table structure for sys_role_function
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_function`;
CREATE TABLE `sys_role_function`  (
  `id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `function_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色功能关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_function
-- ----------------------------
INSERT INTO `sys_role_function` VALUES ('admin-data_dictionary', 'data_dictionary', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-data_dictionary_add', 'data_dictionary_add', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-data_dictionary_delete', 'data_dictionary_delete', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-data_dictionary_update', 'data_dictionary_update', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-data_dictionary_value', 'data_dictionary_value', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-data_dictionary_value_add', 'data_dictionary_value_add', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-data_dictionary_value_delete', 'data_dictionary_value_delete', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-data_dictionary_value_update', 'data_dictionary_value_update', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-frame', 'frame', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-pub_fileList', 'pub_fileList', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-sys', 'sys', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-sys_echart', 'sys_echart', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-sys_func_add', 'sys_func_add', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-sys_func_delete', 'sys_func_delete', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-sys_func_manager', 'sys_func_manager', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-sys_func_update', 'sys_func_update', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-sys_log', 'sys_log', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-sys_manager', 'sys_manager', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-sys_org_add', 'sys_org_add', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-sys_org_delete', 'sys_org_delete', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-sys_org_manager', 'sys_org_manager', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-sys_org_update', 'sys_org_update', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-sys_pub_upload', 'sys_pub_upload', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-sys_role_add', 'sys_role_add', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-sys_role_delete', 'sys_role_delete', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-sys_role_manager', 'sys_role_manager', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-sys_role_update', 'sys_role_update', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-sys_user_add', 'sys_user_add', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-sys_user_delete', 'sys_user_delete', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-sys_user_manager', 'sys_user_manager', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-sys_user_reset', 'sys_user_reset', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-sys_user_role', 'sys_user_role', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin-sys_user_update', 'sys_user_update', 'admin');
INSERT INTO `sys_role_function` VALUES ('admin3-frame', 'frame', 'admin3');
INSERT INTO `sys_role_function` VALUES ('admin3-sys', 'sys', 'admin3');
INSERT INTO `sys_role_function` VALUES ('admin3-sys_func_add', 'sys_func_add', 'admin3');
INSERT INTO `sys_role_function` VALUES ('admin3-sys_func_delete', 'sys_func_delete', 'admin3');
INSERT INTO `sys_role_function` VALUES ('admin3-sys_func_manager', 'sys_func_manager', 'admin3');
INSERT INTO `sys_role_function` VALUES ('admin3-sys_func_update', 'sys_func_update', 'admin3');
INSERT INTO `sys_role_function` VALUES ('admin3-sys_manager', 'sys_manager', 'admin3');
INSERT INTO `sys_role_function` VALUES ('jsb_frame', 'frame', 'jsb');
INSERT INTO `sys_role_function` VALUES ('jsb_frame_main_view', 'frame_main_view', 'jsb');
INSERT INTO `sys_role_function` VALUES ('jsb_sys', 'sys', 'jsb');
INSERT INTO `sys_role_function` VALUES ('jsb_sys_druid', 'sys_druid', 'jsb');
INSERT INTO `sys_role_function` VALUES ('jsb_sys_echart', 'sys_echart', 'jsb');
INSERT INTO `sys_role_function` VALUES ('jsb_sys_func_add', 'sys_func_add', 'jsb');
INSERT INTO `sys_role_function` VALUES ('jsb_sys_func_delete', 'sys_func_delete', 'jsb');
INSERT INTO `sys_role_function` VALUES ('jsb_sys_func_manager', 'sys_func_manager', 'jsb');
INSERT INTO `sys_role_function` VALUES ('jsb_sys_func_update', 'sys_func_update', 'jsb');
INSERT INTO `sys_role_function` VALUES ('jsb_sys_manager', 'sys_manager', 'jsb');
INSERT INTO `sys_role_function` VALUES ('jsb_sys_org_add', 'sys_org_add', 'jsb');
INSERT INTO `sys_role_function` VALUES ('jsb_sys_org_delete', 'sys_org_delete', 'jsb');
INSERT INTO `sys_role_function` VALUES ('jsb_sys_org_manager', 'sys_org_manager', 'jsb');
INSERT INTO `sys_role_function` VALUES ('jsb_sys_org_update', 'sys_org_update', 'jsb');
INSERT INTO `sys_role_function` VALUES ('jsb_sys_role_add', 'sys_role_add', 'jsb');
INSERT INTO `sys_role_function` VALUES ('jsb_sys_role_delete', 'sys_role_delete', 'jsb');
INSERT INTO `sys_role_function` VALUES ('jsb_sys_role_manager', 'sys_role_manager', 'jsb');
INSERT INTO `sys_role_function` VALUES ('jsb_sys_role_update', 'sys_role_update', 'jsb');
INSERT INTO `sys_role_function` VALUES ('jsb_sys_user_add', 'sys_user_add', 'jsb');
INSERT INTO `sys_role_function` VALUES ('jsb_sys_user_delete', 'sys_user_delete', 'jsb');
INSERT INTO `sys_role_function` VALUES ('jsb_sys_user_manager', 'sys_user_manager', 'jsb');
INSERT INTO `sys_role_function` VALUES ('jsb_sys_user_reset', 'sys_user_reset', 'jsb');
INSERT INTO `sys_role_function` VALUES ('jsb_sys_user_update', 'sys_user_update', 'jsb');
INSERT INTO `sys_role_function` VALUES ('pt-frame', 'frame', 'pt');
INSERT INTO `sys_role_function` VALUES ('pt-sys', 'sys', 'pt');
INSERT INTO `sys_role_function` VALUES ('pt-sys_func_add', 'sys_func_add', 'pt');
INSERT INTO `sys_role_function` VALUES ('pt-sys_func_delete', 'sys_func_delete', 'pt');
INSERT INTO `sys_role_function` VALUES ('pt-sys_func_manager', 'sys_func_manager', 'pt');
INSERT INTO `sys_role_function` VALUES ('pt-sys_func_update', 'sys_func_update', 'pt');
INSERT INTO `sys_role_function` VALUES ('pt-sys_manager', 'sys_manager', 'pt');
INSERT INTO `sys_role_function` VALUES ('pt-sys_role_add', 'sys_role_add', 'pt');
INSERT INTO `sys_role_function` VALUES ('pt-sys_role_delete', 'sys_role_delete', 'pt');
INSERT INTO `sys_role_function` VALUES ('pt-sys_role_manager', 'sys_role_manager', 'pt');
INSERT INTO `sys_role_function` VALUES ('pt-sys_role_update', 'sys_role_update', 'pt');
INSERT INTO `sys_role_function` VALUES ('pt-sys_user_add', 'sys_user_add', 'pt');
INSERT INTO `sys_role_function` VALUES ('pt-sys_user_delete', 'sys_user_delete', 'pt');
INSERT INTO `sys_role_function` VALUES ('pt-sys_user_manager', 'sys_user_manager', 'pt');
INSERT INTO `sys_role_function` VALUES ('pt-sys_user_reset', 'sys_user_reset', 'pt');
INSERT INTO `sys_role_function` VALUES ('pt-sys_user_update', 'sys_user_update', 'pt');
INSERT INTO `sys_role_function` VALUES ('superadmin-frame', 'frame', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin-frame_main_view', 'frame_main_view', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin-sys', 'sys', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin-sys_druid', 'sys_druid', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin-sys_echart', 'sys_echart', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin-sys_func_add', 'sys_func_add', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin-sys_func_delete', 'sys_func_delete', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin-sys_func_manager', 'sys_func_manager', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin-sys_func_update', 'sys_func_update', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin-sys_manager', 'sys_manager', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin-sys_org_add', 'sys_org_add', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin-sys_org_delete', 'sys_org_delete', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin-sys_org_manager', 'sys_org_manager', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin-sys_org_update', 'sys_org_update', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin-sys_role_add', 'sys_role_add', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin-sys_role_delete', 'sys_role_delete', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin-sys_role_manager', 'sys_role_manager', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin-sys_role_update', 'sys_role_update', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin-sys_user_add', 'sys_user_add', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin-sys_user_delete', 'sys_user_delete', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin-sys_user_manager', 'sys_user_manager', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin-sys_user_reset', 'sys_user_reset', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin-sys_user_update', 'sys_user_update', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin_data_dictionary', 'data_dictionary', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin_data_dictionary_add', 'data_dictionary_add', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin_data_dictionary_delete', 'data_dictionary_delete', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin_data_dictionary_update', 'data_dictionary_update', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin_data_dictionary_value', 'data_dictionary_value', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin_data_dictionary_value_add', 'data_dictionary_value_add', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin_data_dictionary_value_delete', 'data_dictionary_value_delete', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin_data_dictionary_value_update', 'data_dictionary_value_update', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin_pub_fileList', 'pub_fileList', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin_sys_log', 'sys_log', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin_sys_pub_upload', 'sys_pub_upload', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('superadmin_sys_user_role', 'sys_user_role', 'superadmin');
INSERT INTO `sys_role_function` VALUES ('sys_data_dictionary', 'data_dictionary', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_data_dictionary_add', 'data_dictionary_add', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_data_dictionary_delete', 'data_dictionary_delete', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_data_dictionary_update', 'data_dictionary_update', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_data_dictionary_value', 'data_dictionary_value', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_data_dictionary_value_add', 'data_dictionary_value_add', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_data_dictionary_value_delete', 'data_dictionary_value_delete', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_data_dictionary_value_update', 'data_dictionary_value_update', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_frame', 'frame', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_frame_main_view', 'frame_main_view', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_pub_fileList', 'pub_fileList', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_sys', 'sys', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_sys_druid', 'sys_druid', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_sys_echart', 'sys_echart', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_sys_func_add', 'sys_func_add', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_sys_func_delete', 'sys_func_delete', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_sys_func_manager', 'sys_func_manager', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_sys_func_update', 'sys_func_update', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_sys_log', 'sys_log', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_sys_manager', 'sys_manager', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_sys_org_add', 'sys_org_add', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_sys_org_delete', 'sys_org_delete', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_sys_org_manager', 'sys_org_manager', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_sys_org_update', 'sys_org_update', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_sys_pub_upload', 'sys_pub_upload', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_sys_role_add', 'sys_role_add', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_sys_role_delete', 'sys_role_delete', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_sys_role_manager', 'sys_role_manager', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_sys_role_update', 'sys_role_update', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_sys_user_add', 'sys_user_add', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_sys_user_delete', 'sys_user_delete', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_sys_user_manager', 'sys_user_manager', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_sys_user_reset', 'sys_user_reset', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_sys_user_role', 'sys_user_role', 'sys');
INSERT INTO `sys_role_function` VALUES ('sys_sys_user_update', 'sys_user_update', 'sys');
INSERT INTO `sys_role_function` VALUES ('xmb_frame', 'frame', 'xmb');
INSERT INTO `sys_role_function` VALUES ('xmb_sys', 'sys', 'xmb');
INSERT INTO `sys_role_function` VALUES ('xmb_sys_druid', 'sys_druid', 'xmb');
INSERT INTO `sys_role_function` VALUES ('xmb_sys_echart', 'sys_echart', 'xmb');
INSERT INTO `sys_role_function` VALUES ('xtjcb-frame', 'frame', 'xtjcb');
INSERT INTO `sys_role_function` VALUES ('xtjcb-sys', 'sys', 'xtjcb');
INSERT INTO `sys_role_function` VALUES ('xtjcb-sys_func_add', 'sys_func_add', 'xtjcb');
INSERT INTO `sys_role_function` VALUES ('xtjcb-sys_func_delete', 'sys_func_delete', 'xtjcb');
INSERT INTO `sys_role_function` VALUES ('xtjcb-sys_func_manager', 'sys_func_manager', 'xtjcb');
INSERT INTO `sys_role_function` VALUES ('xtjcb-sys_func_update', 'sys_func_update', 'xtjcb');
INSERT INTO `sys_role_function` VALUES ('xtjcb-sys_manager', 'sys_manager', 'xtjcb');
INSERT INTO `sys_role_function` VALUES ('xtjcb-sys_org_add', 'sys_org_add', 'xtjcb');
INSERT INTO `sys_role_function` VALUES ('xtjcb-sys_org_delete', 'sys_org_delete', 'xtjcb');
INSERT INTO `sys_role_function` VALUES ('xtjcb-sys_org_manager', 'sys_org_manager', 'xtjcb');
INSERT INTO `sys_role_function` VALUES ('xtjcb-sys_org_update', 'sys_org_update', 'xtjcb');
INSERT INTO `sys_role_function` VALUES ('xtjcb-sys_role_add', 'sys_role_add', 'xtjcb');
INSERT INTO `sys_role_function` VALUES ('xtjcb-sys_role_delete', 'sys_role_delete', 'xtjcb');
INSERT INTO `sys_role_function` VALUES ('xtjcb-sys_role_manager', 'sys_role_manager', 'xtjcb');
INSERT INTO `sys_role_function` VALUES ('xtjcb-sys_role_update', 'sys_role_update', 'xtjcb');
INSERT INTO `sys_role_function` VALUES ('xtjcb-sys_user_add', 'sys_user_add', 'xtjcb');
INSERT INTO `sys_role_function` VALUES ('xtjcb-sys_user_delete', 'sys_user_delete', 'xtjcb');
INSERT INTO `sys_role_function` VALUES ('xtjcb-sys_user_manager', 'sys_user_manager', 'xtjcb');
INSERT INTO `sys_role_function` VALUES ('xtjcb-sys_user_reset', 'sys_user_reset', 'xtjcb');
INSERT INTO `sys_role_function` VALUES ('xtjcb-sys_user_role', 'sys_user_role', 'xtjcb');
INSERT INTO `sys_role_function` VALUES ('xtjcb-sys_user_update', 'sys_user_update', 'xtjcb');
INSERT INTO `sys_role_function` VALUES ('xzb_frame', 'frame', 'xzb');
INSERT INTO `sys_role_function` VALUES ('xzb_sys', 'sys', 'xzb');
INSERT INTO `sys_role_function` VALUES ('xzb_sys_manager', 'sys_manager', 'xzb');
INSERT INTO `sys_role_function` VALUES ('xzb_sys_user_add', 'sys_user_add', 'xzb');
INSERT INTO `sys_role_function` VALUES ('xzb_sys_user_delete', 'sys_user_delete', 'xzb');
INSERT INTO `sys_role_function` VALUES ('xzb_sys_user_manager', 'sys_user_manager', 'xzb');
INSERT INTO `sys_role_function` VALUES ('xzb_sys_user_reset', 'sys_user_reset', 'xzb');
INSERT INTO `sys_role_function` VALUES ('xzb_sys_user_update', 'sys_user_update', 'xzb');
INSERT INTO `sys_role_function` VALUES ('yf_frame', 'frame', 'yf');
INSERT INTO `sys_role_function` VALUES ('yf_sys', 'sys', 'yf');
INSERT INTO `sys_role_function` VALUES ('yf_sys_func_add', 'sys_func_add', 'yf');
INSERT INTO `sys_role_function` VALUES ('yf_sys_func_delete', 'sys_func_delete', 'yf');
INSERT INTO `sys_role_function` VALUES ('yf_sys_func_manager', 'sys_func_manager', 'yf');
INSERT INTO `sys_role_function` VALUES ('yf_sys_func_update', 'sys_func_update', 'yf');
INSERT INTO `sys_role_function` VALUES ('yf_sys_manager', 'sys_manager', 'yf');
INSERT INTO `sys_role_function` VALUES ('yf_sys_org_add', 'sys_org_add', 'yf');
INSERT INTO `sys_role_function` VALUES ('yf_sys_org_delete', 'sys_org_delete', 'yf');
INSERT INTO `sys_role_function` VALUES ('yf_sys_org_manager', 'sys_org_manager', 'yf');
INSERT INTO `sys_role_function` VALUES ('yf_sys_org_update', 'sys_org_update', 'yf');
INSERT INTO `sys_role_function` VALUES ('yf_sys_role_add', 'sys_role_add', 'yf');
INSERT INTO `sys_role_function` VALUES ('yf_sys_role_delete', 'sys_role_delete', 'yf');
INSERT INTO `sys_role_function` VALUES ('yf_sys_role_manager', 'sys_role_manager', 'yf');
INSERT INTO `sys_role_function` VALUES ('yf_sys_role_update', 'sys_role_update', 'yf');
INSERT INTO `sys_role_function` VALUES ('yf_sys_user_add', 'sys_user_add', 'yf');
INSERT INTO `sys_role_function` VALUES ('yf_sys_user_delete', 'sys_user_delete', 'yf');
INSERT INTO `sys_role_function` VALUES ('yf_sys_user_manager', 'sys_user_manager', 'yf');
INSERT INTO `sys_role_function` VALUES ('yf_sys_user_reset', 'sys_user_reset', 'yf');
INSERT INTO `sys_role_function` VALUES ('yf_sys_user_update', 'sys_user_update', 'yf');
INSERT INTO `sys_role_function` VALUES ('zjl_frame', 'frame', 'zjl');
INSERT INTO `sys_role_function` VALUES ('zjl_sys', 'sys', 'zjl');
INSERT INTO `sys_role_function` VALUES ('zjl_sys_func_add', 'sys_func_add', 'zjl');
INSERT INTO `sys_role_function` VALUES ('zjl_sys_func_delete', 'sys_func_delete', 'zjl');
INSERT INTO `sys_role_function` VALUES ('zjl_sys_func_manager', 'sys_func_manager', 'zjl');
INSERT INTO `sys_role_function` VALUES ('zjl_sys_func_update', 'sys_func_update', 'zjl');
INSERT INTO `sys_role_function` VALUES ('zjl_sys_manager', 'sys_manager', 'zjl');
INSERT INTO `sys_role_function` VALUES ('zjl_sys_org_add', 'sys_org_add', 'zjl');
INSERT INTO `sys_role_function` VALUES ('zjl_sys_org_delete', 'sys_org_delete', 'zjl');
INSERT INTO `sys_role_function` VALUES ('zjl_sys_org_manager', 'sys_org_manager', 'zjl');
INSERT INTO `sys_role_function` VALUES ('zjl_sys_org_update', 'sys_org_update', 'zjl');
INSERT INTO `sys_role_function` VALUES ('zjl_sys_role_add', 'sys_role_add', 'zjl');
INSERT INTO `sys_role_function` VALUES ('zjl_sys_role_delete', 'sys_role_delete', 'zjl');
INSERT INTO `sys_role_function` VALUES ('zjl_sys_role_manager', 'sys_role_manager', 'zjl');
INSERT INTO `sys_role_function` VALUES ('zjl_sys_role_update', 'sys_role_update', 'zjl');
INSERT INTO `sys_role_function` VALUES ('zjl_sys_user_add', 'sys_user_add', 'zjl');
INSERT INTO `sys_role_function` VALUES ('zjl_sys_user_delete', 'sys_user_delete', 'zjl');
INSERT INTO `sys_role_function` VALUES ('zjl_sys_user_manager', 'sys_user_manager', 'zjl');
INSERT INTO `sys_role_function` VALUES ('zjl_sys_user_reset', 'sys_user_reset', 'zjl');
INSERT INTO `sys_role_function` VALUES ('zjl_sys_user_update', 'sys_user_update', 'zjl');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `user_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户编号默认跟ID一样',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `passwd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `org_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门编号',
  `post` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职务',
  `sex` int(11) NULL DEFAULT NULL COMMENT '1;男,0女',
  `tel` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `mobile` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'EMAIL',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭地址',
  `allow_login` int(11) NULL DEFAULT NULL COMMENT '允许登录',
  `deleted` int(11) NULL DEFAULT 0 COMMENT '删除操作（1：删除未审核）',
  `allow_login_time` datetime(0) NULL DEFAULT NULL COMMENT '允许登录时间或最后登录时间',
  `failure_number` int(11) NULL DEFAULT NULL COMMENT '登录错误次数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_1_sys_user_orgid`(`org_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('admin', 'admin', '管理员', 'E10ADC3949BA59ABBE56E057F20F883E', 'ff808081616e1efd01616e2c89af0000', NULL, 1, NULL, NULL, NULL, NULL, 0, 0, '2019-01-03 18:08:17', 0);
INSERT INTO `sys_user` VALUES ('superadmin', 'superadmin', '超级管理员', 'E10ADC3949BA59ABBE56E057F20F883E', 'ff808081616e1efd01616e2c89af0000', NULL, 1, NULL, NULL, NULL, NULL, 0, 0, '2019-01-02 18:12:29', 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('13-superadmin', '13', 'superadmin');
INSERT INTO `sys_user_role` VALUES ('admin-admin', 'admin', 'admin');
INSERT INTO `sys_user_role` VALUES ('admin3-admin', 'admin3', 'admin');
INSERT INTO `sys_user_role` VALUES ('jsb-123', 'jsb', '123');
INSERT INTO `sys_user_role` VALUES ('qinhaisenlin-admin', 'admin', 'qinhaisenlin');
INSERT INTO `sys_user_role` VALUES ('superadmin-superadmin', 'superadmin', 'superadmin');
INSERT INTO `sys_user_role` VALUES ('xmb-linwei', 'xmb', 'linwei');
INSERT INTO `sys_user_role` VALUES ('xtjcb-admin', 'xtjcb', 'admin');
INSERT INTO `sys_user_role` VALUES ('xzb-123', 'xzb', '123');
INSERT INTO `sys_user_role` VALUES ('xzb-linwei', 'xzb', 'linwei');
INSERT INTO `sys_user_role` VALUES ('xzb-qweq', 'xzb', 'qweq');
INSERT INTO `sys_user_role` VALUES ('yfb-admin', 'yfb', 'admin');
INSERT INTO `sys_user_role` VALUES ('zjl-linwei', 'zjl', 'linwei');
INSERT INTO `sys_user_role` VALUES ('zjl-qweq', 'zjl', 'qweq');

SET FOREIGN_KEY_CHECKS = 1;
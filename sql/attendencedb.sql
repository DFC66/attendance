/*
Navicat MySQL Data Transfer

Source Server         : hgtools01
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : attendencedb

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-02-23 18:25:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for attendence_student_info
-- ----------------------------
DROP TABLE IF EXISTS `attendence_student_info`;
CREATE TABLE `attendence_student_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '变更时间',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(32) DEFAULT NULL COMMENT '修改人',
  `is_deleted` varchar(1) NOT NULL DEFAULT 'n' COMMENT '删除标记',
  `student_name` varchar(255) DEFAULT NULL COMMENT '学生姓名',
  `number` varchar(255) DEFAULT NULL COMMENT '学生学号',
  `openid` varchar(128) DEFAULT NULL COMMENT '微信或QQ唯一id值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attendence_student_info
-- ----------------------------
INSERT INTO `attendence_student_info` VALUES ('1', '2020-02-04 17:21:16', '2020-02-04 17:21:16', null, null, 'n', null, null, '0BC89AB4B775441D7E82BF9B75973334');
INSERT INTO `attendence_student_info` VALUES ('2', '2020-02-04 22:16:20', '2020-02-04 22:16:20', null, null, 'n', null, null, 'asd');

-- ----------------------------
-- Table structure for attendence_user_info
-- ----------------------------
DROP TABLE IF EXISTS `attendence_user_info`;
CREATE TABLE `attendence_user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '变更时间',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(32) DEFAULT NULL COMMENT '修改人',
  `is_deleted` varchar(1) NOT NULL DEFAULT 'n' COMMENT '删除标记',
  `name` varchar(128) DEFAULT NULL COMMENT '姓名',
  `login_name` varchar(128) DEFAULT NULL COMMENT '登录名',
  `pwd` varchar(100) NOT NULL COMMENT '登录密码',
  `role_id` bigint(64) NOT NULL COMMENT '所属角色id',
  `role_name` varchar(128) DEFAULT NULL COMMENT '角色名',
  `user_type` varchar(20) NOT NULL DEFAULT 'person' COMMENT '账号类型：person 个人、 operator 运营',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`),
  KEY `is_deleted` (`is_deleted`),
  KEY `creator` (`creator`),
  KEY `gmt_create` (`gmt_create`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8 COMMENT='执行系统用户信息表';

-- ----------------------------
-- Records of attendence_user_info
-- ----------------------------
INSERT INTO `attendence_user_info` VALUES ('102', '2019-04-08 14:43:15', '2019-11-28 19:05:29', '运营', '张三丰', 'n', '张三丰', 'sanfeng', 'CBA3CE074D4CF6A6EBA462C64335E3BB', '8', '运营', 'operator');
INSERT INTO `attendence_user_info` VALUES ('103', '2019-04-08 20:27:31', '2019-04-08 22:53:58', '奇瑞测试账号', '奇瑞测试账号', 'y', '1', '1', '6B027D2B1D42FB553F3888E8758AFD7A', '8', null, 'person');
INSERT INTO `attendence_user_info` VALUES ('104', '2019-04-08 20:34:46', '2019-04-09 00:08:40', '奇瑞测试账号', '奇瑞测试账号', 'y', '2', '2', '0E3ADA19C809902DA3C13552ECB697C7', '8', null, 'operator');
INSERT INTO `attendence_user_info` VALUES ('105', '2019-04-08 20:38:38', '2019-04-09 00:08:42', '奇瑞测试账号', '奇瑞测试账号', 'y', '3', '3', '8DDCC2961AD3542F79BE061AB8F15402', '9', '运营1', 'person');
INSERT INTO `attendence_user_info` VALUES ('106', '2019-04-09 10:16:41', '2019-05-13 16:12:59', '奇瑞测试账号', '姜子牙', 'n', '姜子牙', 'xiaojiang', 'AE9448D6A7FD9BB0F021B500393CED11', '12', '开发', 'operator');
INSERT INTO `attendence_user_info` VALUES ('107', '2019-04-09 21:12:42', '2019-05-13 16:13:08', '张三丰1', '姜子牙', 'n', 'fy1', 'fy1', 'AE9448D6A7FD9BB0F021B500393CED11', '9', '立案法官', 'judge');
INSERT INTO `attendence_user_info` VALUES ('108', '2019-04-23 16:02:27', '2019-04-23 16:02:27', '姜子牙', '姜子牙', 'n', '阿杜', 'du', 'AE9448D6A7FD9BB0F021B500393CED11', '12', '开发', 'operator');
INSERT INTO `attendence_user_info` VALUES ('109', '2019-05-13 16:29:57', '2019-05-13 16:29:57', '姜子牙', '姜子牙', 'n', 'fy2', 'fy2', 'AE9448D6A7FD9BB0F021B500393CED11', '13', '执行法官', 'judge');
INSERT INTO `attendence_user_info` VALUES ('110', '2019-12-24 11:13:18', '2019-12-24 11:53:01', '张三丰', '张三丰', 'y', '张无忌', 'wuji', 'CBA3CE074D4CF6A6EBA462C64335E3BB', '8', '运营', 'operator');
INSERT INTO `attendence_user_info` VALUES ('111', '2019-12-24 11:27:10', '2019-12-24 11:53:02', '张三丰', '张三丰', 'y', '周芷若', 'zhiruo', 'CBA3CE074D4CF6A6EBA462C64335E3BB', '12', '开发', 'judge');
INSERT INTO `attendence_user_info` VALUES ('112', '2019-12-24 11:30:44', '2019-12-24 11:30:44', '张三丰', '张三丰', 'n', '赵四', 'zhaosi', 'CBA3CE074D4CF6A6EBA462C64335E3BB', '9', '立案法官', 'judge');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `course_code` int(6) DEFAULT NULL,
  `img_path` varchar(255) DEFAULT NULL,
  `name` varchar(64) NOT NULL COMMENT '课程的名字',
  `teacher_name` varchar(16) DEFAULT NULL,
  `start_time` varchar(64) DEFAULT NULL COMMENT '课程的开始时间',
  `end_time` varchar(64) DEFAULT NULL COMMENT '课程的结束时间',
  `today` varchar(16) DEFAULT NULL COMMENT '课程星期几',
  `room` varchar(64) DEFAULT NULL COMMENT '上课教室',
  `longitude` varchar(32) DEFAULT NULL COMMENT '教室的经度坐标',
  `latitude` varchar(32) DEFAULT NULL COMMENT '教室的纬度坐标',
  `classes` varchar(128) DEFAULT NULL COMMENT '上课班级',
  `classess` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `course_code` (`course_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('6', '8816', null, '数学', '爸爸', '15:14', '15:49', null, '8-218', '113.12602', '22.933378', null, null);
INSERT INTO `course` VALUES ('7', '3964', null, '语文', '妈妈', '16:30', '16:59', null, '固特异', '113.126002', '22.933378', null, null);
INSERT INTO `course` VALUES ('8', '5346', null, '英语', '妈妈', '16:30', '16:59', null, '固特异', '113.126097', '22.933386', null, null);

-- ----------------------------
-- Table structure for course_result
-- ----------------------------
DROP TABLE IF EXISTS `course_result`;
CREATE TABLE `course_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_code` int(11) DEFAULT NULL,
  `number` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_result
-- ----------------------------
INSERT INTO `course_result` VALUES ('1', '8816', '654321');
INSERT INTO `course_result` VALUES ('3', '3964', '654321');
INSERT INTO `course_result` VALUES ('6', '5346', '654321');
INSERT INTO `course_result` VALUES ('8', '8816', '666666');
INSERT INTO `course_result` VALUES ('9', '3964', '666666');
INSERT INTO `course_result` VALUES ('21', '5346', '666666');

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('9');
INSERT INTO `hibernate_sequence` VALUES ('9');
INSERT INTO `hibernate_sequence` VALUES ('9');
INSERT INTO `hibernate_sequence` VALUES ('9');

-- ----------------------------
-- Table structure for signin
-- ----------------------------
DROP TABLE IF EXISTS `signin`;
CREATE TABLE `signin` (
  `id` int(11) NOT NULL,
  `classes` varchar(255) DEFAULT NULL,
  `course_name` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `room` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of signin
-- ----------------------------

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classes` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `openid` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `openid` (`openid`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '计算机6班', 'FFF', '123456', 'asd');
INSERT INTO `student` VALUES ('2', '', '大风车', '666666', '0BC89AB4B775441D7E82BF9B75973334');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '123', '202cb962ac59075b964b07152d234b70', 'hahaha');

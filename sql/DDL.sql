/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : hospital_register

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-12-09 22:08:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_patient
-- ----------------------------
DROP TABLE IF EXISTS `t_patient`;
CREATE TABLE `t_patient` (
  `patient_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '就诊人id',
  `real_name` varchar(20) NOT NULL COMMENT '就诊人姓名',
  `id_card` varchar(32) DEFAULT NULL COMMENT '身份证',
  `birthday` date NOT NULL COMMENT '出生日期',
  `sex` int(1) NOT NULL COMMENT '性别:  1:男，0：女',
  `phone` varchar(20) NOT NULL COMMENT '手机号',
  `open_id` varchar(64) DEFAULT NULL COMMENT '微信openid-唯一标识',
  `remark` varchar(100) DEFAULT NULL,
  `score` int(2) DEFAULT NULL COMMENT '积分',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
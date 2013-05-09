/*
 Navicat Premium Data Transfer

 Source Server         : MAMPLocal
 Source Server Type    : MySQL
 Source Server Version : 50529
 Source Host           : localhost
 Source Database       : vasalocal

 Target Server Type    : MySQL
 Target Server Version : 50529
 File Encoding         : utf-8

 Date: 05/09/2013 13:06:44 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `checkin`
-- ----------------------------
DROP TABLE IF EXISTS `checkin`;
CREATE TABLE `checkin` (
  `user` varchar(255) NOT NULL,
  `time` varchar(255) NOT NULL,
  `latitude` varchar(255) NOT NULL,
  `longitude` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `data`
-- ----------------------------
DROP TABLE IF EXISTS `data`;
CREATE TABLE `data` (
  `inventario` varchar(255) NOT NULL,
  `key` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  PRIMARY KEY (`inventario`,`key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `data_general`
-- ----------------------------
DROP TABLE IF EXISTS `data_general`;
CREATE TABLE `data_general` (
  `key` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `data_general`
-- ----------------------------
BEGIN;
INSERT INTO `data_general` VALUES ('a', '1'), ('b', '2'), ('c', '3'), ('a', '2'), ('b', '5');
COMMIT;

-- ----------------------------
--  Table structure for `inventario`
-- ----------------------------
DROP TABLE IF EXISTS `inventario`;
CREATE TABLE `inventario` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `media`
-- ----------------------------
DROP TABLE IF EXISTS `media`;
CREATE TABLE `media` (
  `inventario` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `key` varchar(255) NOT NULL,
  `value` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `storage`
-- ----------------------------
DROP TABLE IF EXISTS `storage`;
CREATE TABLE `storage` (
  `id` varchar(255) NOT NULL,
  `data` longblob NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `user_roles`
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `USER_ROLE_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USER_ID` int(10) unsigned NOT NULL,
  `AUTHORITY` varchar(45) NOT NULL,
  PRIMARY KEY (`USER_ROLE_ID`),
  KEY `FK_user_roles` (`USER_ID`),
  CONSTRAINT `FK_user_roles` FOREIGN KEY (`USER_ID`) REFERENCES `web_users` (`USER_ID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user_roles`
-- ----------------------------
BEGIN;
INSERT INTO `user_roles` VALUES ('1', '1', 'ROLE_USER'), ('2', '2', 'ROLE_USER'), ('3', '3', 'ROLE_USER'), ('4', '5', 'ROLE_ADMIN'), ('5', '5', 'ROLE_USER');
COMMIT;

-- ----------------------------
--  Table structure for `web_users`
-- ----------------------------
DROP TABLE IF EXISTS `web_users`;
CREATE TABLE `web_users` (
  `USER_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  `ENABLED` tinyint(1) NOT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `web_users`
-- ----------------------------
BEGIN;
INSERT INTO `web_users` VALUES ('1', 'ramon', '123', '1'), ('2', 'felix', '123', '1'), ('3', 'ftarazona', '123', '1'), ('4', 'rclark', '123', '1'), ('5', 'admin', 'admin', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

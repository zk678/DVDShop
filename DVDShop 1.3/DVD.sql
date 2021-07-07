/*
 Navicat Premium Data Transfer

 Source Server         : 华为
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : 117.78.*.*:3306
 Source Schema         : DVD

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 04/07/2021 23:20:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for borrowdvd
-- ----------------------------
DROP TABLE IF EXISTS `borrowdvd`;
CREATE TABLE `borrowdvd`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `readerid` char(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DVDID` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `borrowdate` date NULL DEFAULT NULL,
  `returndate` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `readerid`(`readerid`) USING BTREE,
  INDEX `ISBN`(`DVDID`) USING BTREE,
  CONSTRAINT `borrowdvd_ibfk_1` FOREIGN KEY (`readerid`) REFERENCES `reader` (`readerid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `borrowdvd_ibfk_2` FOREIGN KEY (`DVDID`) REFERENCES `dvd` (`dvdid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of borrowdvd
-- ----------------------------
INSERT INTO `borrowdvd` VALUES (6, '110', '233124', '2021-07-03', '2021-07-03');
INSERT INTO `borrowdvd` VALUES (7, '110', '11123', '2021-07-04', '2021-07-04');
INSERT INTO `borrowdvd` VALUES (8, '123', '123', '2021-07-04', '2021-07-04');

-- ----------------------------
-- Table structure for dvd
-- ----------------------------
DROP TABLE IF EXISTS `dvd`;
CREATE TABLE `dvd`  (
  `dvdid` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `typeid` int(11) NULL DEFAULT NULL,
  `dvdname` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `author` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `publish` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`dvdid`) USING BTREE,
  INDEX `typeid`(`typeid`) USING BTREE,
  CONSTRAINT `dvd_ibfk_1` FOREIGN KEY (`typeid`) REFERENCES `dvdtype` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of dvd
-- ----------------------------
INSERT INTO `dvd` VALUES ('11123', 1, '少时诵诗书', '十三点', '时代的', 1);
INSERT INTO `dvd` VALUES ('1199773', 3, '李小龙', '踩踩踩', '塞俄', 1);
INSERT INTO `dvd` VALUES ('1223', 3, 'dadada', '水水水', '啥的', 1);
INSERT INTO `dvd` VALUES ('123', 2, '等', '大', '顶', 1);
INSERT INTO `dvd` VALUES ('12345678', 1, '计算机科学与技术', '程要亮', '北京出版社', 1);
INSERT INTO `dvd` VALUES ('233124', 2, '这是一个爱情片', '程要亮', '三生三世十里桃花', 1);
INSERT INTO `dvd` VALUES ('953111123', 1, '三体', '刘慈欣', '长江文学', 0);

-- ----------------------------
-- Table structure for dvdtype
-- ----------------------------
DROP TABLE IF EXISTS `dvdtype`;
CREATE TABLE `dvdtype`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typename` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of dvdtype
-- ----------------------------
INSERT INTO `dvdtype` VALUES (1, '科幻');
INSERT INTO `dvdtype` VALUES (2, '爱情');
INSERT INTO `dvdtype` VALUES (3, '动作');
INSERT INTO `dvdtype` VALUES (5, '战争');
INSERT INTO `dvdtype` VALUES (7, '恐怖');
INSERT INTO `dvdtype` VALUES (8, '搞笑');
INSERT INTO `dvdtype` VALUES (9, '测试');

-- ----------------------------
-- Table structure for reader
-- ----------------------------
DROP TABLE IF EXISTS `reader`;
CREATE TABLE `reader`  (
  `readerid` char(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `regdate` date NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`readerid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of reader
-- ----------------------------
INSERT INTO `reader` VALUES ('110', '程要亮', '男', '1234', '2021-05-26', 1);
INSERT INTO `reader` VALUES ('111', '李华', '女', '21341234', '2021-05-27', 1);
INSERT INTO `reader` VALUES ('112', '李明', '男', '13343121', '2021-05-27', 1);
INSERT INTO `reader` VALUES ('113', '任善吉', '女', '1234123', '2021-05-27', 1);
INSERT INTO `reader` VALUES ('123', '123', '男', '123333', '2021-07-04', 0);
INSERT INTO `reader` VALUES ('1233', '啊啊啊啊', '男', '1111122', '2021-07-04', 1);
INSERT INTO `reader` VALUES ('334', '成都市', '女', NULL, '2021-07-04', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (3, 'admin', 'admin');
INSERT INTO `user` VALUES (4, '1', '1');

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat MySQL Dump SQL

 Source Server         : MySQL9
 Source Server Type    : MySQL
 Source Server Version : 90001 (9.0.1)
 Source Host           : localhost:33061
 Source Schema         : s_c_sc

 Target Server Type    : MySQL
 Target Server Version : 90001 (9.0.1)
 File Encoding         : 65001

 Date: 29/11/2024 14:41:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `Cno` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Cname` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Ccredit` smallint NULL DEFAULT NULL,
  `Cpno` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`Cno`) USING BTREE,
  INDEX `Cpno`(`Cpno` ASC) USING BTREE,
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`Cpno`) REFERENCES `course` (`Cno`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('81001', '程序设计基础与C语言', 4, NULL);
INSERT INTO `course` VALUES ('81002', '数据结构', 4, '81001');
INSERT INTO `course` VALUES ('81003', '数据库系统概论', 4, '81002');
INSERT INTO `course` VALUES ('81004', '信息系统概论', 4, '81003');
INSERT INTO `course` VALUES ('81005', '操作系统', 4, '81001');
INSERT INTO `course` VALUES ('81006', 'Python语言', 3, '81002');
INSERT INTO `course` VALUES ('81007', '离散数学', 4, NULL);
INSERT INTO `course` VALUES ('81008', '大数据技术概论', 4, '81003');
INSERT INTO `course` VALUES ('81009', '思想道德与法治', 2, NULL);
INSERT INTO `course` VALUES ('81010', '习近平新时代中国特色社会主义思想概论', 2, '81009');
INSERT INTO `course` VALUES ('81011', '测试2', 3, '81005');

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login`  (
  `name` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login
-- ----------------------------
INSERT INTO `login` VALUES ('20250002', '123456');
INSERT INTO `login` VALUES ('20250003', '123456');
INSERT INTO `login` VALUES ('20250004', '123456');
INSERT INTO `login` VALUES ('20250005', '123456');
INSERT INTO `login` VALUES ('20250006', '123456');
INSERT INTO `login` VALUES ('20250007', '123456');
INSERT INTO `login` VALUES ('20250001', '123456');
INSERT INTO `login` VALUES ('T0001', '123456');

-- ----------------------------
-- Table structure for sc
-- ----------------------------
DROP TABLE IF EXISTS `sc`;
CREATE TABLE `sc`  (
  `Sno` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Cno` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Grade` smallint NULL DEFAULT NULL,
  `Semester` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Teachingclass` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`Sno`, `Cno`) USING BTREE,
  INDEX `Cno`(`Cno` ASC) USING BTREE,
  CONSTRAINT `sc_ibfk_1` FOREIGN KEY (`Sno`) REFERENCES `student` (`Sno`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sc_ibfk_2` FOREIGN KEY (`Cno`) REFERENCES `course` (`Cno`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sc
-- ----------------------------
INSERT INTO `sc` VALUES ('20250001', '81001', 85, '20251', '81001-01');
INSERT INTO `sc` VALUES ('20250001', '81002', 96, '20252', '81002-01');
INSERT INTO `sc` VALUES ('20250001', '81003', 87, '20261', '81003-01');
INSERT INTO `sc` VALUES ('20250002', '81001', 80, '20251', '81001-02');
INSERT INTO `sc` VALUES ('20250002', '81002', 98, '20252', '81002-01');
INSERT INTO `sc` VALUES ('20250002', '81003', 71, '20261', '81003-02');
INSERT INTO `sc` VALUES ('20250003', '81001', 81, '20251', '81002-02');
INSERT INTO `sc` VALUES ('20250004', '81001', 56, '20251', '81001-02');
INSERT INTO `sc` VALUES ('20250004', '81003', 97, '20261', '81002-02');
INSERT INTO `sc` VALUES ('20250205', '81003', 68, '20261', '81003-01');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `Sno` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Sname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Ssex` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Sbirthdate` date NULL DEFAULT NULL,
  `Smajor` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`Sno`) USING BTREE,
  UNIQUE INDEX `Sname`(`Sname` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('20250001', '李勇', '男', '2006-03-08', '信息安全');
INSERT INTO `student` VALUES ('20250002', '刘晨', '女', '2005-09-01', '计算机科学与技术');
INSERT INTO `student` VALUES ('20250003', '王敏', '女', '2004-08-01', '计算机科学与技术');
INSERT INTO `student` VALUES ('20250004', '张立', '男', '2005-01-08', '计算机科学与技术');
INSERT INTO `student` VALUES ('20250205', '陈新奇', '男', '2006-11-01', '信息管理与信息系统');
INSERT INTO `student` VALUES ('20250306', '赵明', '男', '2006-06-12', '数据科学与大数据技术');
INSERT INTO `student` VALUES ('20250307', '王佳佳', '女', '2005-12-07', '数据科学与大数据技术');

SET FOREIGN_KEY_CHECKS = 1;

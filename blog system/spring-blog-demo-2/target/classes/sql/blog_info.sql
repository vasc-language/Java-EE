/*
 Navicat Premium Dump SQL

 Source Server         : locallhost-20250410
 Source Server Type    : MySQL
 Source Server Version : 80033 (8.0.33)
 Source Host           : localhost:3306
 Source Schema         : java_blog_spring

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001

 Date: 11/05/2025 12:27:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_info
-- ----------------------------
DROP TABLE IF EXISTS `blog_info`;
CREATE TABLE `blog_info`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `user_id` int NULL DEFAULT NULL,
  `delete_flag` tinyint NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '博客表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_info
-- ----------------------------
INSERT INTO `blog_info` VALUES (1, '第一篇博客', '# 111我是博客正文我是博客正文我是博客正文\n## 111我是博客正文我是博客正文我是博客正文\nhahahahhaha', 1, 0, '2025-05-04 21:08:52', '2025-05-11 11:18:31');
INSERT INTO `blog_info` VALUES (2, '第二篇博客', '222我是博客正文我是博客正文我是博客正文', 2, 0, '2025-05-04 21:08:52', '2025-05-04 21:08:52');
INSERT INTO `blog_info` VALUES (3, '第三篇博客', '333我是博客正文我是博客正文我是博客正文', 3, 0, '2025-05-10 11:37:08', '2025-05-10 11:37:08');
INSERT INTO `blog_info` VALUES (4, '我是修改后的第四篇博客', '我是修改后的第四篇博客', 1, 1, '2025-05-10 11:52:44', '2025-05-11 09:35:08');
INSERT INTO `blog_info` VALUES (5, '这是我写的第五篇博客', '## 这是我写的第五篇博客\n **我说天道酬勤，信不信**', 1, 1, '2025-05-11 10:23:45', '2025-05-11 11:18:59');
INSERT INTO `blog_info` VALUES (6, '李四的第五篇博客', '##在这里写下一篇博客\n## 东风夜放花千树，下一句是什么？', 2, 0, '2025-05-11 10:52:24', '2025-05-11 10:52:24');
INSERT INTO `blog_info` VALUES (7, 'hahah', '##在这里写下一篇博客\n\n你是谁？？？', 1, 1, '2025-05-11 11:18:50', '2025-05-11 11:18:55');
INSERT INTO `blog_info` VALUES (8, 'aaa', '##在这里写下一篇博客aaaa', 1, 0, '2025-05-11 12:14:07', '2025-05-11 12:14:07');
INSERT INTO `blog_info` VALUES (9, '李四的第六篇博客', '##在这里写下一篇博客\n**醉里挑灯看剑，梦回吹角连营**', 2, 0, '2025-05-11 12:23:45', '2025-05-11 12:23:45');

SET FOREIGN_KEY_CHECKS = 1;

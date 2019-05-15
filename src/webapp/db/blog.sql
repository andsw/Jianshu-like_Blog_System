/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : 47.101.223.154:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 14/05/2019 16:18:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `article_no` int(1) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '序号',
  `user_no` int(1) UNSIGNED NOT NULL COMMENT '作者序号',
  `article_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '标题',
  `article_summary` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '概要',
  `article_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '缩略图',
  `article_release_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新时间就是发布时间',
  `article_corpus_id` int(10) UNSIGNED NOT NULL COMMENT '所属文集',
  `article_type` bit(2) NOT NULL DEFAULT b'0' COMMENT '文章类型',
  `article_commentable` bit(1) NOT NULL DEFAULT b'1' COMMENT '可否评论',
  `article_private` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否私密文章',
  `article_read_num` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '阅读数',
  `article_like_num` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '喜欢数',
  `article_comment_num` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '评论数',
  `article_collect_num` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '被收藏数',
  `article_word_num` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '字数',
  `article_tag_num` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '标签数',
  PRIMARY KEY (`article_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for article_content
-- ----------------------------
DROP TABLE IF EXISTS `article_content`;
CREATE TABLE `article_content`  (
  `article_no` int(15) NOT NULL COMMENT '对应那篇文章',
  `content` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  PRIMARY KEY (`article_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection`  (
  `user_no` int(10) UNSIGNED NOT NULL COMMENT '收藏用户',
  `article_no` int(10) UNSIGNED NOT NULL COMMENT '收藏文章',
  PRIMARY KEY (`user_no`, `article_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `comment_no` int(11) NOT NULL COMMENT '评论序号',
  `comment_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '内容，无论这里设为多长，前台都要限制字数！',
  `like_num` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '评论点赞数',
  `user_no` int(10) UNSIGNED NOT NULL COMMENT '所属用户序号',
  `article_no` int(10) UNSIGNED NOT NULL COMMENT '这个可建立索引，因为通过文章no查找评论的操作很多！',
  `comment_time` timestamp(0) NOT NULL COMMENT '评论时间，类型时间戳，默认当前时区的时间，前台是用框架显示相对时间',
  `reply_comment_no` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '不为0表示此评论是回复某个评论的，为0表示直接评论某文章',
  `comment_level` int(10) UNSIGNED NOT NULL COMMENT '表示在文章中的评论楼层',
  PRIMARY KEY (`comment_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for corpus
-- ----------------------------
DROP TABLE IF EXISTS `corpus`;
CREATE TABLE `corpus`  (
  `corpus_no` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '文集序号，主键值',
  `corpus_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '文集名字，需去重',
  `user_no` int(10) UNSIGNED NOT NULL COMMENT '所属用户id',
  `blog_num` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '文集文章数',
  PRIMARY KEY (`corpus_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of corpus
-- ----------------------------
INSERT INTO `corpus` VALUES (1, 'java', 1, 0);
INSERT INTO `corpus` VALUES (2, 'c++', 1, 0);
INSERT INTO `corpus` VALUES (3, '计算机网络', 1, 0);
INSERT INTO `corpus` VALUES (4, 'linux', 1, 0);
INSERT INTO `corpus` VALUES (5, '随笔', 1, 0);
INSERT INTO `corpus` VALUES (6, 'java', 121, 0);
INSERT INTO `corpus` VALUES (7, 'c++', 121, 0);
INSERT INTO `corpus` VALUES (8, '计算机网络', 121, 0);
INSERT INTO `corpus` VALUES (9, 'linux', 121, 0);
INSERT INTO `corpus` VALUES (10, '随笔', 121, 0);
INSERT INTO `corpus` VALUES (11, '操作系统', 1, 0);

-- ----------------------------
-- Table structure for follow
-- ----------------------------
DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow`  (
  `followed_user_no` int(10) UNSIGNED NOT NULL COMMENT '表示被关注的人',
  `follower_user_no` int(10) UNSIGNED NOT NULL COMMENT '关注者，粉丝序号',
  PRIMARY KEY (`followed_user_no`, `follower_user_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `tag_no` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '标签序号',
  `tag_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '标签名称',
  `tag_article_num` int(11) NOT NULL COMMENT '具有此标签的文章数目',
  PRIMARY KEY (`tag_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_no` int(15) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户的序号，从0开始，自增',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '用户名，标识用户和登录',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '有效邮箱号，需验证，可用来登录，需要去重',
  `tel` varchar(20) CHARACTER SET ascii COLLATE ascii_bin NOT NULL DEFAULT '' COMMENT '有效电话，需验证，可用来登录，需要去重',
  `password` varchar(255) CHARACTER SET armscii8 COLLATE armscii8_bin NOT NULL DEFAULT '' COMMENT '由电话为盐值加密的密码，为ascII码可见字符。',
  `avatar` varchar(255) CHARACTER SET armscii8 COLLATE armscii8_bin NOT NULL DEFAULT '' COMMENT '头像图片链接。默认值为服务器中的一张图片的资源定位符',
  `self_summary` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '个人简介，默认是空字符串，前台检测到是空就直接显示默认值',
  `gender` bit(2) NOT NULL DEFAULT b'10' COMMENT '性别0W,1M，2保密',
  `follow_num` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `follower_num` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `blog_num` int(4) UNSIGNED NOT NULL DEFAULT 0,
  `word_num` bigint(20) UNSIGNED NOT NULL DEFAULT 0,
  `like_num` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `github` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '个人github网址',
  `wechat_qr_img_link` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '微信二维码图片地址',
  PRIMARY KEY (`user_no`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 128 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '账号信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'heshaowen', '985934131@qq.com', '19876543211', 'aTIFiYAGDyyRMLWf1TsGOw==', 'www.baidu.com/imagte/1.png', '金风玉露一相逢，便胜却人间无数。', b'01', 0, 0, 0, 0, 0, 'www.github.com/hswbug', 'wechat23333.png');
INSERT INTO `user` VALUES (103, 'user', '2127804711@qq.com', '18779340049', 'KYuYKcNbCBagq+AtDBF7Wg==', '456789', '456456456', b'10', 0, 0, 0, 0, 0, '', '');
INSERT INTO `user` VALUES (121, 'hajiwon', '', '12345678900', 'Bohb6IOr/MPgGNOCNp1cXg==', 'hajiwon.png', '琵琶弦上说相思，当时明月在，曾照彩云归！', b'01', 0, 0, 0, 0, 0, 'https://www.github.com/hesw', 'wechat.png');
INSERT INTO `user` VALUES (125, 'user1', '1111111@qq,com', '1821111111', '123123123', '123456788', '', b'10', 0, 0, 0, 0, 0, '', '');
INSERT INTO `user` VALUES (127, '123123', '123456789@qq.com', '', 'S4rjznXYtp4Nqq70GBGtGA==', '9.png', '', b'10', 0, 0, 0, 0, 0, '', '');

SET FOREIGN_KEY_CHECKS = 1;

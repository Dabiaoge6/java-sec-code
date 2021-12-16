-- ----------------------------
-- Table structure for applicant
-- ----------------------------
create schema if not exists h2db;
DROP TABLE IF EXISTS `applicant`;
CREATE TABLE IF NOT EXISTS `applicant`  (
                              `id` int(10) NOT NULL AUTO_INCREMENT,
                              `uuid` varchar(225) NOT NULL,
                              `name` varchar(225) NOT NULL,
                              `age` int(10) NOT NULL,
                              `mobile` varchar(11) NOT NULL,
                              `email` varchar(25) NOT NULL,
                              `address` varchar(225) NULL DEFAULT NULL,
                              `status` varchar(10) NOT NULL DEFAULT '0',
                              PRIMARY KEY (`id`)
) ;
-- ----------------------------
-- Table structure for user_t
-- ----------------------------
DROP TABLE IF EXISTS `user_t`;
CREATE TABLE IF NOT EXISTS `user_t`  (
                           `id` varchar(255) NOT NULL,
                           `user_name` varchar(40) NOT NULL,
                           `password` varchar(255) NOT NULL,
                           `age` int(4) NOT NULL,
                           PRIMARY KEY (`id`)
);
-- ----------------------------
-- Table structure for contact
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE IF NOT EXISTS `contact`  (
                            `id` varchar(225) NOT NULL COMMENT '联系方式',
                            `mobile` varchar(11) NULL DEFAULT NULL,
                            `qq` varchar(20) NULL DEFAULT NULL,
                            `email` varchar(255) NULL DEFAULT NULL);
-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence`  (
    `next_val` bigint(20) NULL DEFAULT NULL
);
-- ----------------------------
-- Table structure for interview_result
-- ----------------------------
DROP TABLE IF EXISTS `interview_result`;
CREATE TABLE IF NOT EXISTS `interview_result`  (
                                     `id` int(10) NOT NULL AUTO_INCREMENT,
                                     `uuid` varchar(225) NOT NULL,
                                     `name` varchar(255) NOT NULL,
                                     `result` varchar(10) NOT NULL COMMENT '面试结果',
                                     PRIMARY KEY (`id`)
);
DROP TABLE IF EXISTS `app1_user`;
CREATE TABLE IF NOT EXISTS `app1_user` (
                             `id` varchar(10) NOT NULL,
                             `username` varchar(255) NOT NULL,
                             `pwd` varchar(255) NOT NULL,
                             PRIMARY KEY (`id`)
);
-- ----------------------------
-- Table structure for permission_t
-- ----------------------------
--DROP TABLE IF EXISTS `permission_t`;
CREATE TABLE IF NOT EXISTS `permission_t`  (
                                 `id` int(10) NOT NULL,
                                 `permissionname` varchar(45) NOT NULL,
                                 `role_id` int(10) NOT NULL,
                                 PRIMARY KEY (`id`)
);
-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE IF NOT EXISTS `person`  (
                           `id` varchar(255) NOT NULL DEFAULT 'NULL',
                           `idCard` varchar(32) NOT NULL DEFAULT 'NULL',
                           `name` varchar(32) NOT NULL DEFAULT 'NULL',
                           `phone` varchar(32) NOT NULL DEFAULT 'NULL',
                           `province` varchar(255) NOT NULL,
                           `city` varchar(255) NOT NULL,
                           `country` varchar(255) NULL DEFAULT '',
                           PRIMARY KEY (`id`)
);
-- ----------------------------
-- Table structure for role_t
-- ----------------------------
DROP TABLE IF EXISTS `role_t`;
CREATE TABLE IF NOT EXISTS `role_t`  (
                           `id` int(10) NOT NULL AUTO_INCREMENT,
                           `rolename` varchar(45) NOT NULL,
                           PRIMARY KEY (`id`)
);
-- ----------------------------
-- Table structure for t_group
-- ----------------------------
DROP TABLE IF EXISTS `t_group`;
CREATE TABLE IF NOT EXISTS `t_group`  (
                            id int(11) NOT NULL AUTO_INCREMENT,
                            group_id varchar(64) NOT NULL ,
                            group_name varchar(255) NULL DEFAULT NULL,
                            org_id varchar(64) NOT NULL,
                            create_time timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
                            modify_time timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
                            PRIMARY KEY (id)
);
-- ----------------------------
-- Table structure for t_page
-- ----------------------------
DROP TABLE IF EXISTS `t_page`;
CREATE TABLE IF NOT EXISTS `t_page`  (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `content_type` varchar(255) NULL DEFAULT NULL,
                           `description` varchar(255) NULL DEFAULT NULL,
                           `inLeftMenu` bit(1) NULL DEFAULT NULL,
                           `inTopMenu` bit(1) NULL DEFAULT NULL,
                           `_order` int(11) NULL DEFAULT NULL,
                           `status` bit(1) NULL DEFAULT NULL,
                           `title` varchar(255) NULL DEFAULT NULL,
                           `link_id` int(11) NULL DEFAULT NULL,
                           `pid` int(11) NULL DEFAULT NULL,
                           `richtext_id` int(11) NULL DEFAULT NULL,
                           PRIMARY KEY (`id`)
);
-- ----------------------------
-- Table structure for t_link
-- ----------------------------
DROP TABLE IF EXISTS `t_link`;
CREATE TABLE IF NOT EXISTS `t_link`  (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `title` varchar(255) NULL DEFAULT NULL,
                           `url` varchar(255) NULL DEFAULT NULL,
                           `page_id` int(11) NULL DEFAULT NULL,
                           PRIMARY KEY (`id`)
);
-- ----------------------------
-- Table structure for t_organization
-- ----------------------------
DROP TABLE IF EXISTS `t_organization`;
CREATE TABLE IF NOT EXISTS `t_organization`  (
                                   `id` int(11)  NOT NULL AUTO_INCREMENT,
                                   `org_id` varchar(64) NOT NULL,
                                   `org_name` varchar(255) NULL DEFAULT NULL,
                                   `admin_id` varchar(64) NOT NULL,
                                   `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
                                   `modify_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
                                   PRIMARY KEY (`id`)
);
-- ----------------------------
-- Table structure for t_richtext
-- ----------------------------
DROP TABLE IF EXISTS `t_richtext`;
CREATE TABLE IF NOT EXISTS `t_richtext`  (
                               `id` int(11) NOT NULL,
                               `content` varchar(255) NULL DEFAULT NULL,
                               `page_id` int(11) NULL DEFAULT NULL,
                               PRIMARY KEY (`id`)
);
-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE IF NOT EXISTS `t_role`  (
                           `id` int(32) NOT NULL AUTO_INCREMENT,
                           `role_id` varchar(64) NOT NULL,
                           `role` varchar(64) NULL DEFAULT NULL,
                           `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
                           `modify_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
                           PRIMARY KEY (`id`)
);
-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE IF NOT EXISTS `t_user`  (
                           `id` int(32) NOT NULL AUTO_INCREMENT,
                           `user_id` varchar(64) NOT NULL,
                           `group_id` varchar(64) NULL DEFAULT NULL,
                           `role_id` varchar(64) NOT NULL,
                           `email` varchar(255) NOT NULL,
                           `user_name` varchar(255) NOT NULL,
                           `password` varchar(255) NOT NULL,
                           `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
                           PRIMARY KEY (`id`)
);
-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE IF NOT EXISTS `test`  (
                         `num1` int(10) NOT NULL DEFAULT 0,
                         `num2` int(10) NOT NULL DEFAULT 0,
                         `num4` int(10) NOT NULL DEFAULT 0,
                         `num3` int(10) NOT NULL DEFAULT 0,
                         `str1` varchar(32) NULL DEFAULT NULL,
                         `str2` varchar(255) NULL DEFAULT NULL,
                         `str3` varchar(32) NULL DEFAULT NULL,
                         `isboolean` tinyint(1) NULL DEFAULT NULL
);
-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role`  (
                              `role_id` int(10) NOT NULL,
                              `user_id` varchar(255) NOT NULL,
                              PRIMARY KEY (`role_id`, `user_id`)
);
INSERT INTO `user_t` VALUES('65d86e3a95a945f9bf56eed2c340e9c2','liu','123',1);
INSERT INTO `user_t` VALUES('65d86e3a95a945f9bf56eed2c340e9c3','liu1','123',1);
INSERT INTO `user_t` VALUES('65d86e3a95a945f9bf56eed2c340e9c4','liu2','123',1);
INSERT INTO `user_t` VALUES('65d86e3a95a945f9bf56eed2c340e9c5','liu3','123',1);
INSERT INTO `user_t` VALUES('65d86e3a95a945f9bf56eed2c340e9c6','liu4','123',1);
INSERT INTO `user_t` VALUES('65d86e3a95a945f9bf56eed2c340e9c7','liu5','123',1);
INSERT INTO `user_t` VALUES('65d86e3a95a945f9bf56eed2c340e9c8','liu6','123',1);
INSERT INTO `user_t` VALUES('65d86e3a95a945f9bf56eed2c340e9c9','liu7','123',1);
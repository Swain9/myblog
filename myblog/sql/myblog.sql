DROP DATABASE IF EXISTS `myblog`;
CREATE DATABASE `myblog`;
use `myblog`;

DROP TABLE IF EXISTS t_attach;
CREATE TABLE t_attach (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    fname VARCHAR(100) NOT NULL COMMENT '姓名',
    ftype VARCHAR(50) COMMENT '类型',
    fkey VARCHAR(100) NOT NULL COMMENT '关键字',
    author_id INT NOT NULL COMMENT '作者ID',
    created INT NOT NULL COMMENT '创建日期'
)  ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='附件';

-- 表：t_comments
DROP TABLE IF EXISTS t_comments;
CREATE TABLE t_comments (
    coid INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    cid INT DEFAULT 0 NOT NULL COMMENT '回复主键',
    created INT NOT NULL COMMENT '创建日期',
    author VARCHAR(200) NOT NULL COMMENT '作者',
    author_id INT DEFAULT 0 COMMENT '作者ID',
    owner_id INT DEFAULT 0 COMMENT '拥有着ID',
    mail VARCHAR(200) NOT NULL COMMENT '邮箱',
    url VARCHAR(200) COMMENT '链接',
    ip VARCHAR(64) COMMENT 'ip地址',
    agent VARCHAR(200) COMMENT '代理',
    content TEXT NOT NULL COMMENT '内容',
    `type` VARCHAR(16) COMMENT '类型',
    `status` VARCHAR(16) COMMENT '状态',
    parent INT DEFAULT 0 COMMENT '上级'
)  ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='回复';

-- 表：t_contents
DROP TABLE IF EXISTS t_contents;
CREATE TABLE t_contents (
    cid INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    title VARCHAR(255) NOT NULL COMMENT '标题',
    slug VARCHAR(255) UNIQUE COMMENT '内容缩略名',
    thumb_img VARCHAR(255) COMMENT '文章缩略图',
    created INT NOT NULL COMMENT '创建日期',
    modified INT COMMENT '修改日期',
    content TEXT COMMENT '内容',
    author_id INT NOT NULL COMMENT '作者id',
    `type` VARCHAR(16) NOT NULL COMMENT '类型',
    `status` VARCHAR(16) NOT NULL COMMENT '状态',
    fmt_type VARCHAR(16) DEFAULT 'markdown' COMMENT '解析类型',
    tags VARCHAR(200) COMMENT '标签',
    categories VARCHAR(200) COMMENT '分类列表',
    hits INT DEFAULT 0 COMMENT '点击率',
    comments_num INT DEFAULT 0 COMMENT '评论数',
    allow_comment TINYINT(1) DEFAULT 1 COMMENT '是否允许评论',
    allow_ping TINYINT(1) COMMENT '是否允许ping',
    allow_feed TINYINT(1) COMMENT '是否允许出现在聚合中'
)  ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='文章内容';

ALTER TABLE `myblog`.`t_contents`
    ADD COLUMN `allow_see` TINYINT(1) NULL DEFAULT 1 AFTER `allow_comment`;

-- 表：t_logs
DROP TABLE IF EXISTS t_logs;
CREATE TABLE t_logs (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `action` VARCHAR(100) NOT NULL COMMENT '产生的动作',
    `data` VARCHAR(2000) COMMENT '产生的数据',
    author_id INT NOT NULL COMMENT '产生的id',
    ip VARCHAR(20) COMMENT 'ip地址',
    created INT(10) NOT NULL COMMENT '记录日期'
)  ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='日志记录';

-- 表：t_metas
DROP TABLE IF EXISTS t_metas;
CREATE TABLE t_metas (
    `mid` INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `name` VARCHAR(200) NOT NULL COMMENT '分类名称',
    slug VARCHAR(200) COMMENT '分类缩写',
    `type` VARCHAR(32) NOT NULL COMMENT '分类类型',
    description VARCHAR(255) COMMENT '描述',
    sort TINYINT DEFAULT 0 COMMENT '排序',
    parent INT DEFAULT 0 COMMENT '上级'
)  ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='分类';
INSERT INTO t_metas (`mid`, `name`, slug, `type`, description, sort, parent) VALUES (1, '默认分类', NULL, 'category', NULL, 0, 0);

-- 表：t_options
DROP TABLE IF EXISTS t_options;
CREATE TABLE t_options (
    `name` VARCHAR(100) PRIMARY KEY NOT NULL COMMENT '配置名称',
    `value` TEXT COMMENT '配置值',
    description VARCHAR(255) COMMENT '配置描述'
)  ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='配置选项';

-- 表：t_relationships
DROP TABLE IF EXISTS t_relationships;
CREATE TABLE t_relationships (
    cid INT NOT NULL COMMENT '内容主键',
    `mid` INT NOT NULL COMMENT '项目主键'
)  ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='数据关系';


-- 表：t_users
DROP TABLE IF EXISTS t_users;
CREATE TABLE t_users (
    uid INT PRIMARY KEY AUTO_INCREMENT NOT NULL COMMENT '主键',
    username VARCHAR(64) UNIQUE NOT NULL COMMENT '用户名',
    `password` VARCHAR(64) NOT NULL COMMENT '密码',
    email VARCHAR(100) COMMENT '邮箱',
    home_url VARCHAR(255) COMMENT '链接',
    screen_name VARCHAR(100) COMMENT '显示的名称',
    created INT NOT NULL COMMENT '注册日期',
    activated INT COMMENT '最后活动日期',
    logged INT COMMENT '上次登陆日期',
    group_name VARCHAR(16) COMMENT '用户组'
)  ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='用户';
insert into t_users(username,`password`,created)values('admin','123456',123456);


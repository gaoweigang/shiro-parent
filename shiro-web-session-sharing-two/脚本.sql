--创建数据dev
CREATE DATABASE dev;

--切换到数据库dev
USE dev;

--查看当前正在使用的数据库
select DATABASE();

--查看当前库中存在哪些表
show tables;

--创建表
--登陆日志表

create table `tbl_login_log` (
  `id` bigint(64) not null auto_increment comment '主键流水号',
  `client_ip` varchar(64) default null comment '客户端登陆ip',
  `login_time` timestamp null default null comment '登入时间',
  `logout_time` timestamp null default null comment '登出时间',
  `server_ip` varchar(64) default null comment '服务器ip',
  `server_port` varchar(64) default null comment '服务器端口',
  `service_id` varchar(64) default null comment '服务系统 0 业务系统 1支撑系统',
  `user_id` varchar(64) default null comment '用户id',
  `username` varchar(64) default null comment '用户姓名',
   create_time timestamp NULL DEFAULT NULL comment '创建时间',
   modify_time timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
   creator varchar(32) default null comment '创建人',
   modifier varchar(32) default null comment '修改人',
   desc varchar(255) default null comment '描述',
  primary key (`id`),
  key `id` (`id`) using btree
) engine=innodb default charset=utf8 comment='登陆信息表';


--用户角色关联表
CREATE TABLE `tbl_user_role` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(20) DEFAULT NULL COMMENT '角色id',
  `role_code` varchar(20) DEFAULT NULL COMMENT '角色编码',
  PRIMARY KEY (`BID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

CREATE TABLE `tbl_role` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_code` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `valid_flag` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0: 无效， 1有效',
   create_time timestamp NULL DEFAULT NULL comment '创建时间',
   modify_time timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
   creator varchar(32) default null comment '创建人',
   modifier varchar(32) default null comment '修改人',
   desc varchar(255) default null comment '描述',
  PRIMARY KEY (`id`),
  unique key `unique_role_code` (`role_code`) using btree
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='角色表';

--角色资源关联表
CREATE TABLE `tbl_role_resource` (
   id bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_code` int(11) NOT NULL comment '角色编码',
  `res_code` int(11) NOT NULL comment '资源编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源关联表';

--资源表
CREATE TABLE `tbl_resource` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `res_code` varchar(255) DEFAULT NULL COMMENT '资源名称',
  `res_name` varchar(255) DEFAULT NULL COMMENT '资源名称-英文',
  `res_url` varchar(255) DEFAULT NULL COMMENT '资源url',
  `type` int(11) DEFAULT NULL COMMENT '资源类型   1:菜单    2：按钮',
  `pid` int(11) DEFAULT NULL COMMENT '父资源',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `valid_flag` varchar(1) default null comment '有效标志 1：有效，0：无效 表示停用',
   create_time timestamp NULL DEFAULT NULL comment '创建时间',
   modify_time timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
   creator varchar(32) default null comment '创建人',
   modifier varchar(32) default null comment '修改人',
   desc varchar(255) default null comment '描述',
  PRIMARY KEY (`id`)
  unique key `unique_res_code` (`res_code`) using btree
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='资源表';


--用户登陆信息表
CREATE TABLE `tbl_account` (
  `id` bigint(32) not null auto_increment comment '主键',
  `user_id` varchar(20) not null comment '用户登录id',
  `errpwdcount` double default null comment '密码连续错误次数',
  `lastlogintime` timestamp null default null comment '最近成功登陆时间',
  `lastupdatepwdtime` timestamp null default null comment '最近密码更换时间',
  `password` varchar(20) default null comment '密码',
  `start_date` timestamp null default null comment '用户起用日期',
  `stop_date` timestamp null default null comment '用户停用日期',
  `user_status` varchar(1) default null comment '用户当前状态(0：正常，1：密码过期，2：账户已冻结，3：已销户)',
  `valid_flag` varchar(1) default null comment '有效标志 1：有效，0：无效 表示停用',
   create_time timestamp NULL DEFAULT NULL comment '创建时间',
   modify_time timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
   creator varchar(32) default null comment '创建人',
   modifier varchar(32) default null comment '修改人',
   desc varchar(255) default null comment '描述',
  primary key (`id`),
  unique key `unique_user_id` (`user_id`) using btree
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录信息表';

--人员信息表,仅仅是人员信息，公司在职人员并不一定可以登陆系统
create table `tbl_user` (
  `id` bigint(32) not null auto_increment comment '主键',
  `user_id` varchar(20) not null comment '用户id',
  `username` varchar(40) default null comment '员工姓名',
  `sex` varchar(10) default null comment '性别',
   birthday timestamp NULL DEFAULT NULL COMMENT '出生日期',
   card_no varchar(32) DEFAULT NULL COMMENT '身份证号',
  `email` varchar(40) default null comment '邮箱',
  `mobile` varchar(20) default null comment '电话',
  `position` varchar(10) default null comment '职位',
  `valid_flag` tinyint(1) default null comment '记录是否有效 1:有效, 0:无效(销户)',
  `entry_time` timestamp not null default current_timestamp comment '入职时间',
  `resign_time` datetime default null comment '离职日期',
   create_time timestamp NULL DEFAULT NULL comment '创建时间',
   modify_time timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
   creator varchar(32) default null comment '创建人',
   modifier varchar(32) default null comment '修改人',
   desc varchar(255) default null comment '描述',
  primary key (`id`),
  unique key `unique_user_id` (`user_id`) using btree
) engine=innodb default charset=utf8 comment='人员信息表';

--查询表
SELECT * from tbl_user;
--账号表
SELECT * from tbl_account;
SELECT * from tbl_user_role;
SELECT * from tbl_role;
SELECT * from tbl_role_resource;
SELECT * from tbl_resource;
SELECT * from tbl_login_log;


--往表中插入记录
INSERT INTO `tbl_login_log`(CLIENTIP, EXTNO,LOGINTIME , LOGOUTTIME,SERVERIP,SERVERPORT,SERVICEID,TEAMID,USERID,USERNAME) VALUES ( NULL, NULL, '2018-6-8 19:09:00', '2018-6-8 19:09:00', NULL, NULL, NULL, NULL, '00000013', '测试电销');
INSERT INTO `tbl_login_log`(CLIENTIP, EXTNO,LOGINTIME , LOGOUTTIME,SERVERIP,SERVERPORT,SERVICEID,TEAMID,USERID,USERNAME) VALUES ( NULL, NULL, '2018-6-8 18:38:47', '2018-6-8 18:38:47', NULL, NULL, NULL, NULL, '00000012', '测试小强新');


INSERT INTO `tbl_user_role`(ROLEID, USERID) VALUES ( 'ADMIN', '00000012');
INSERT INTO `tbl_user_role`(ROLEID, USERID) VALUES ( 'TL', '00000013');
INSERT INTO `tbl_user_role`(ROLEID, USERID) VALUES ( 'TSR', '00000013');


INSERT INTO `tbl_resource` VALUES (1, '系统组织管理', 'systemPage', '/systemPage', 1, 0, 0, 0, NULL, '2018-6-4 11:32:30', NULL);
INSERT INTO `tbl_resource` VALUES (2, '用户管理', 'usersPage', '/usersPage', 1, 1, 2, 0, NULL, '2018-6-4 11:32:35', NULL);
INSERT INTO `tbl_resource` VALUES (3, '角色管理', 'rolesPage', '/rolesPage', 1, 1, 3, 0, NULL, '2018-6-4 11:32:52', NULL);
INSERT INTO `tbl_resource` VALUES (5, '添加用户', 'usersAdd', '/users/add', 2, 2, 5, 0, NULL, '2018-6-4 11:33:02', NULL);
INSERT INTO `tbl_resource` VALUES (6, '删除用户', 'usersDelete', '/users/delete', 2, 2, 6, 0, NULL, '2018-6-4 11:33:11', NULL);
INSERT INTO `tbl_resource` VALUES (7, '添加角色', 'rolesAdd', '/roles/add', 2, 3, 7, 0, NULL, '2018-6-4 11:33:21', NULL);
INSERT INTO `tbl_resource` VALUES (8, '删除角色', 'rolesDelete', '/roles/delete', 2, 3, 8, 0, NULL, '2018-6-4 11:33:32', NULL);
INSERT INTO `tbl_resource` VALUES (9, '添加资源', 'resourcesAdd', '/resources/add', 2, 4, 9, 1, NULL, '2018-6-4 11:33:42', NULL);
INSERT INTO `tbl_resource` VALUES (10, '删除资源', 'resourcesDelete', '/resources/delete', 2, 4, 10, 1, NULL, '2018-6-4 11:33:51', NULL);
INSERT INTO `tbl_resource` VALUES (11, '分配角色', 'usersSaveUserRoles', '/users/saveUserRoles', 2, 2, 11, 0, NULL, '2018-6-4 11:34:02', NULL);
INSERT INTO `tbl_resource` VALUES (13, '分配权限', 'rolesSaveRoleResources', '/roles/saveRoleResources', 2, 3, 12, 0, NULL, '2018-6-4 11:34:12', NULL);
INSERT INTO `tbl_resource` VALUES (14, '待审核', 'auditPage', '/auditPage', 1, 0, 13, 0, NULL, '2018-6-4 11:34:16', NULL);
INSERT INTO `tbl_resource` VALUES (15, '机构管理', 'organizePage', '/organizePage', 1, 1, 1, 0, NULL, '2018-6-4 11:34:20', NULL);
INSERT INTO `tbl_resource` VALUES (17, '复核', 'auditEdit', '/audit/edit', 2, 14, 16, 0, NULL, '2018-6-4 11:34:31', NULL);
INSERT INTO `tbl_resource` VALUES (18, '复核查看', 'auditRead', '/audit/read', 2, 14, 17, 0, NULL, '2018-6-4 11:34:53', NULL);


INSERT INTO `tbl_role_resource`(role_id,resource_id) VALUES (1, 1);
INSERT INTO `tbl_role_resource`(role_id,resource_id) VALUES (1, 2);
INSERT INTO `tbl_role_resource`(role_id,resource_id) VALUES (1, 3);
INSERT INTO `tbl_role_resource`(role_id,resource_id) VALUES (1, 5);
INSERT INTO `tbl_role_resource`(role_id,resource_id) VALUES (1, 6);
INSERT INTO `tbl_role_resource`(role_id,resource_id) VALUES (1, 7);
INSERT INTO `tbl_role_resource`(role_id,resource_id) VALUES (1, 8);
INSERT INTO `tbl_role_resource`(role_id,resource_id) VALUES (1, 11);
INSERT INTO `tbl_role_resource`(role_id,resource_id) VALUES (1, 13);
INSERT INTO `tbl_role_resource`(role_id,resource_id) VALUES (1, 14);
INSERT INTO `tbl_role_resource`(role_id,resource_id) VALUES (1, 15);
INSERT INTO `tbl_role_resource`(role_id,resource_id) VALUES (1, 18);
INSERT INTO `tbl_role_resource`(role_id,resource_id) VALUES (2, 1);
INSERT INTO `tbl_role_resource`(role_id,resource_id) VALUES (2, 2);
INSERT INTO `tbl_role_resource`(role_id,resource_id) VALUES (2, 3);
INSERT INTO `tbl_role_resource`(role_id,resource_id) VALUES (2, 4);
INSERT INTO `tbl_role_resource`(role_id,resource_id) VALUES (2, 9);
INSERT INTO `tbl_role_resource`(role_id,resource_id) VALUES (3, 2);
INSERT INTO `tbl_role_resource`(role_id,resource_id) VALUES (3, 3);
INSERT INTO `tbl_role_resource`(role_id,resource_id) VALUES (3, 4);
INSERT INTO `tbl_role_resource`(role_id,resource_id) VALUES (3, 5);
INSERT INTO `tbl_role_resource`(role_id,resource_id) VALUES (3, 7);
INSERT INTO `tbl_role_resource`(role_id,resource_id) VALUES (3, 8);
INSERT INTO `tbl_role_resource`(role_id,resource_id) VALUES (3, 9);
INSERT INTO `tbl_role_resource`(role_id,resource_id) VALUES (3, 10);



INSERT INTO `tbl_user`(USERID,BRANCHID,BYNAME,CREATETIME,EMAIL,MOBILE,OFFICEPHONE,POSITION,SEX,SKILLLEVEL,USERNAME,VALIDCHGREASON,VALIDFLAG,ACDQUEUE,ACDUSERID,ACDUSERPWD,ACWSECONDS,USERTYPE,trial,orderNo,entryMonth,entryTime,destoryTime) VALUES ('00000012', NULL, NULL, '2018-3-12 16:40:00', '', '', NULL, NULL, NULL, '1', '测试小强新', NULL, '1', NULL, NULL, NULL, NULL, '51101', NULL, NULL, 2, '2018-1-4 00:00:00', NULL);
INSERT INTO `tbl_user`(USERID,BRANCHID,BYNAME,CREATETIME,EMAIL,MOBILE,OFFICEPHONE,POSITION,SEX,SKILLLEVEL,USERNAME,VALIDCHGREASON,VALIDFLAG,ACDQUEUE,ACDUSERID,ACDUSERPWD,ACWSECONDS,USERTYPE,trial,orderNo,entryMonth,entryTime,destoryTime) VALUES ('00000013', NULL, NULL, '2018-3-12 17:10:05', '', '', NULL, NULL, NULL, '1', '测试电销', NULL, '1', NULL, NULL, NULL, NULL, '51101', NULL, NULL, 0, '2018-3-12 00:00:00', NULL);


INSERT INTO `tbl_user_account`(USERID,ERRPWDCOUNT,LASTLOGINTIME,LASTUPDATEPWDTIME,PASSWORD,STARTDATE,STOPTDATE,USERSTATUS,VALIDFLAG,codeSendTime) VALUES ('00000012', 0, NULL, '2018-3-12 00:00:00', '1', '2018-3-12 16:39:03', '2018-3-12 16:39:03', '0', '1', '2018-5-10 16:03:01');
INSERT INTO `tbl_user_account`(USERID,ERRPWDCOUNT,LASTLOGINTIME,LASTUPDATEPWDTIME,PASSWORD,STARTDATE,STOPTDATE,USERSTATUS,VALIDFLAG,codeSendTime)  VALUES ('00000013', 0, NULL, '2018-3-12 00:00:00', '1', '2018-3-12 17:09:37', '2018-3-12 17:09:37', '0', '1', '2018-3-12 17:12:16');


INSERT INTO `tbl_role` VALUES (1, 'TSR', '电话营销代表', 00, '2018-5-3 13:26:32', '2018-5-3 13:26:32', NULL, NULL);
INSERT INTO `tbl_role` VALUES (2, 'TL', '团队主管', 01, '2018-5-8 13:53:57', '2018-5-3 13:26:34', NULL, NULL);
INSERT INTO `tbl_role` VALUES (3, 'SUPERADMIN', '超级管理员', 01, '2018-5-8 13:54:04', '2018-5-3 13:27:31', NULL, NULL);
INSERT INTO `tbl_role` VALUES (5, 'ADMIN', '管理员', 01, '2018-5-7 22:02:54', '2018-5-3 13:26:36', NULL, NULL);


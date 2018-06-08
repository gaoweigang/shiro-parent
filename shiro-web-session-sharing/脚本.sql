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

CREATE TABLE `tbl_login_log` (
  `ID` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键流水号',
  `CLIENTIP` varchar(255) DEFAULT NULL COMMENT '客户端登陆IP',
  `EXTNO` varchar(255) DEFAULT NULL COMMENT '分机号',
  `LOGINTIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '登入时间',
  `LOGOUTTIME` timestamp NULL DEFAULT NULL COMMENT '登出时间',
  `SERVERIP` varchar(255) DEFAULT NULL COMMENT '服务器IP',
  `SERVERPORT` varchar(255) DEFAULT NULL COMMENT '服务器端口',
  `SERVICEID` varchar(255) DEFAULT NULL COMMENT '服务系统 0 业务系统 1支撑系统',
  `TEAMID` varchar(255) DEFAULT NULL COMMENT '团队ID',
  `USERID` varchar(255) DEFAULT NULL COMMENT '坐席工号',
  `USERNAME` varchar(255) DEFAULT NULL COMMENT '坐席姓名',
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登陆信息表';


--用户角色关联表
CREATE TABLE `tbl_user_role` (
  `BID` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ROLEID` varchar(20) DEFAULT NULL COMMENT '角色id',
  `USERID` varchar(20) DEFAULT NULL COMMENT '座席编号',
  PRIMARY KEY (`BID`),
  KEY `BID` (`BID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

CREATE TABLE `tbl_role` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL,
  `role_desc` varchar(255) DEFAULT NULL,
  `is_deleted` int(2) unsigned zerofill DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_by` varchar(32) DEFAULT NULL,
  `modify_by` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='角色表';

--角色资源关联表
CREATE TABLE `tbl_role_resource` (
   id bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源关联表';

--资源表
CREATE TABLE `tbl_resource` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '资源名称',
  `name_en` varchar(255) DEFAULT NULL COMMENT '资源名称-英文',
  `res_url` varchar(255) DEFAULT NULL COMMENT '资源url',
  `type` int(11) DEFAULT NULL COMMENT '资源类型   1:菜单    2：按钮',
  `pid` int(11) DEFAULT NULL COMMENT '父资源',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `is_deleted` int(10) DEFAULT '0' COMMENT '是否删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `modify_by` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='资源表';


--用户登陆信息表
CREATE TABLE `tbl_user_account` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USERID` varchar(20) NOT NULL COMMENT '用户登录id',
  `ERRPWDCOUNT` double DEFAULT NULL COMMENT '密码连续错误次数',
  `LASTLOGINTIME` timestamp NULL DEFAULT NULL COMMENT '最近成功登陆时间',
  `LASTUPDATEPWDTIME` timestamp NULL DEFAULT NULL COMMENT '最近密码更换时间',
  `PASSWORD` varchar(20) DEFAULT NULL COMMENT '密码',
  `STARTDATE` timestamp NULL DEFAULT NULL COMMENT '用户起用日期',
  `STOPTDATE` timestamp NULL DEFAULT NULL COMMENT '用户停用日期',
  `USERSTATUS` varchar(1) DEFAULT NULL COMMENT '用户当前状态(0：正常，1：密码过期，2：账户已冻结，3：已销户)',
  `VALIDFLAG` varchar(1) DEFAULT NULL COMMENT '有效标志 1：有效，0：无效 表示停用',
  `codeSendTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '口令发送时间',
  PRIMARY KEY (`id`),
  KEY `USERID_INDEX` (`USERID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录信息表';

--人员信息表,仅仅是人员信息，公司在职人员并不一定可以登陆系统
CREATE TABLE `tbl_user` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USERID` varchar(20) NOT NULL COMMENT '用户id',
  `BRANCHID` varchar(10) DEFAULT NULL COMMENT '机构id',
  `BYNAME` varchar(40) DEFAULT NULL COMMENT '坐席别名',
  `CREATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `EMAIL` varchar(40) DEFAULT NULL COMMENT '邮箱',
  `MOBILE` varchar(20) DEFAULT NULL COMMENT '电话',
  `OFFICEPHONE` varchar(20) DEFAULT NULL COMMENT '办公电话',
  `POSITION` varchar(10) DEFAULT NULL COMMENT '职位',
  `SEX` varchar(10) DEFAULT NULL COMMENT '性别',
  `SKILLLEVEL` varchar(10) DEFAULT NULL COMMENT '坐席等级（12345对应ABCDE, 存储于字典表中。DICTYPEID=''SKILLLEVELDIC''）',
  `USERNAME` varchar(40) DEFAULT NULL COMMENT '座席姓名',
  `VALIDCHGREASON` varchar(50) DEFAULT NULL COMMENT 'acd技能组',
  `VALIDFLAG` varchar(1) DEFAULT NULL COMMENT '可用标志 1:有效, 0:无效(销户)',
  `ACDQUEUE` varchar(20) DEFAULT NULL COMMENT '缺省的aftercallwork时长(秒)',
  `ACDUSERID` varchar(20) DEFAULT NULL COMMENT '电话中心参数定义',
  `ACDUSERPWD` varchar(20) DEFAULT NULL COMMENT '密码',
  `ACWSECONDS` varchar(5) DEFAULT NULL COMMENT '变更原因',
  `USERTYPE` varchar(20) DEFAULT NULL COMMENT '是否是班长',
  `trial` varchar(2) DEFAULT NULL COMMENT '是否初审（1为要初审，0为不初审可直接提核）',
  `orderNo` varchar(40) DEFAULT NULL COMMENT '订单号',
  `entryMonth` int(20) DEFAULT NULL COMMENT '记录 有效的 职工 的 入职月份',
  `entryTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入职时间',
  `destoryTime` datetime DEFAULT NULL COMMENT '注销时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `Userid` (`USERID`) USING BTREE,
  KEY `SKILLLEVEL` (`SKILLLEVEL`),
  KEY `USERNAME` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员信息表';

--查询表
SELECT * from tbl_user;
SELECT * from tbl_user_account;
SELECT * from tbl_user_role;
SELECT * from tbl_role;
SELECT * from tbl_role_resource;
SELECT * from tbl_resource;
SELECT * from tbl_login_log;


--往表中插入记录
INSERT INTO `tbl_login_log`(CLIENTIP, EXTNO,LOGINTIME , LOGOUTTIME,SERVERIP,SERVERPORT,SERVICEID,TEAMID,USERID,USERNAME) VALUES ( NULL, NULL, '2018-6-8 19:09:00', '2018-6-8 19:09:00', NULL, NULL, NULL, NULL, '00000013', '测试电销');
INSERT INTO `tbl_login_log`(CLIENTIP, EXTNO,LOGINTIME , LOGOUTTIME,SERVERIP,SERVERPORT,SERVICEID,TEAMID,USERID,USERNAME) VALUES ( NULL, NULL, '2018-6-8 18:38:47', '2018-6-8 18:38:47', NULL, NULL, NULL, NULL, '00000012', '测试小强新');


INSERT INTO `tbl_user_role`(ROLEID, USERID) VALUES ( 'FLP', '00000012');
INSERT INTO `tbl_user_role`(ROLEID, USERID) VALUES ( 'TSR', '00000012');
INSERT INTO `tbl_user_role`(ROLEID, USERID) VALUES ( 'FLP', '00000013');
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


INSERT INTO `tbl_role` VALUES (1, 'TSR', '管理员', 00, '2018-5-3 13:26:32', '2018-5-3 13:26:32', NULL, NULL);
INSERT INTO `tbl_role` VALUES (2, 'FLP', '普通用户', 01, '2018-5-8 13:53:57', '2018-5-3 13:26:34', NULL, NULL);
INSERT INTO `tbl_role` VALUES (3, 'SUPERADMIN', '超级管理员', 01, '2018-5-8 13:54:04', '2018-5-3 13:27:31', NULL, NULL);
INSERT INTO `tbl_role` VALUES (5, 'ADMIN', '管理员', 01, '2018-5-7 22:02:54', '2018-5-3 13:26:36', NULL, NULL);


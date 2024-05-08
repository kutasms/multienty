/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


--
-- Table structure for table `mt_calendar`
--
DROP TABLE IF EXISTS `mt_calendar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_calendar` (
  `day` date NOT NULL COMMENT '日期',
  PRIMARY KEY (`day`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='日历';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mt_dict`
--
DROP TABLE IF EXISTS `mt_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_dict` (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编号',
  `pid` bigint(20) NOT NULL COMMENT '父级编号',
  `label` varchar(50) NOT NULL COMMENT '标签',
  `alias` varchar(50) DEFAULT NULL COMMENT '别名',
  `value` varchar(200) NOT NULL COMMENT '字典值',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `value_type` varchar(50) DEFAULT NULL COMMENT '值类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deletable` bit(1) DEFAULT b'1' COMMENT '是否可删除',
  `status` varchar(20) DEFAULT NULL COMMENT '状态',
  `version` bigint(20) NOT NULL DEFAULT '0' COMMENT '乐观锁版本号',
  `encrypted` bit(1) DEFAULT NULL COMMENT '是否已加密',
  PRIMARY KEY (`dict_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='数据字典';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mt_tenant`
--
DROP TABLE IF EXISTS `mt_tenant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mt_tenant` (
  `tenant_id` bigint NOT NULL COMMENT '租户编号',
  `tenant_no` char(6) DEFAULT NULL COMMENT '租户号码',
  `company_name` varchar(100) DEFAULT NULL COMMENT '公司名称',
  `phone_number` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `liaison` varchar(48) DEFAULT NULL COMMENT '联系人',
  `contact_number` varchar(12) DEFAULT NULL COMMENT '联系电话',
  `token` varchar(512) DEFAULT NULL COMMENT '令牌',
  `avatar` varchar(512) DEFAULT NULL COMMENT '头像地址',
  `type` tinyint DEFAULT NULL COMMENT '租户类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` varchar(20) DEFAULT NULL COMMENT '状态',
  `deleted` bit(1) DEFAULT b'0' COMMENT '是否已删除',
  PRIMARY KEY (`tenant_id`) USING BTREE,
  UNIQUE KEY `IX_TENANT_NO_UNI` (`tenant_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='租户';
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `mt_tenant_sub_account`
--
DROP TABLE IF EXISTS `mt_tenant_sub_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_tenant_sub_account` (
  `sub_account_id` bigint NOT NULL COMMENT '子账号编号',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户编号',
  `phone_number` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `name` varchar(32) NOT NULL COMMENT '姓名',
  `status` varchar(32) NOT NULL COMMENT '状态',
  `version` bigint NOT NULL COMMENT '乐观锁版本号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`sub_account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='租户子账号';
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `mt_upload_file`
--
DROP TABLE IF EXISTS `mt_uploaded_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_uploaded_file` (
  `file_id` bigint NOT NULL AUTO_INCREMENT COMMENT '文件编号',
  `path` varchar(256) DEFAULT NULL COMMENT '文件路径',
  `url` varchar(256) DEFAULT NULL COMMENT '文件URL地址',
  `type` tinyint DEFAULT NULL COMMENT '文件类型',
  `name` varchar(256) DEFAULT NULL COMMENT '文件名称',
  `extension` varchar(4) DEFAULT NULL COMMENT '文件扩展名',
  `meta_id` bigint DEFAULT NULL COMMENT '关联编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` varchar(64) DEFAULT NULL COMMENT '状态',
  `org_file_name` varchar(128) DEFAULT NULL COMMENT '原始文件名',
  PRIMARY KEY (`file_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='已上传文件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mt_setting`
--
DROP TABLE IF EXISTS `mt_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_setting` (
  `setting_id` bigint NOT NULL AUTO_INCREMENT COMMENT '配置编号',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `value` varchar(256) DEFAULT NULL COMMENT '值',
  `default_value` varchar(256) DEFAULT NULL COMMENT '默认值',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `owner` bigint DEFAULT NULL COMMENT '拥有者',
  PRIMARY KEY (`setting_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统配置';
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `mt_setting`
--
DROP TABLE IF EXISTS `mt_json_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_json_config` (
  `config_id` bigint NOT NULL AUTO_INCREMENT COMMENT '配置编号',
  `alias` varchar(32) DEFAULT NULL COMMENT '别名',
  `remark` varchar(256) DEFAULT NULL COMMENT '说明',
  `data` json DEFAULT NULL COMMENT 'JSON数据',
  `default_data` json DEFAULT NULL COMMENT '默认数据',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='Json配置';
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `mt_label`
--
DROP TABLE IF EXISTS `mt_label`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_label` (
  `label_id` bigint NOT NULL AUTO_INCREMENT COMMENT '标签编号',
  `label` varchar(50) DEFAULT NULL COMMENT '标签',
  `type` smallint DEFAULT NULL COMMENT '类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` varchar(50) DEFAULT NULL COMMENT '状态',
  `deleted` bit(1) DEFAULT NULL COMMENT '是否已删除',
  `fail_reason` varchar(128) DEFAULT NULL COMMENT '审核失败原因',
  PRIMARY KEY (`label_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='标签';
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `mt_notice`
--
DROP TABLE IF EXISTS `mt_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_notice` (
  `notice_id` bigint NOT NULL AUTO_INCREMENT COMMENT '通知编号',
  `type` tinyint(4) NOT NULL COMMENT '通知类型',
  `jump_mode` tinyint(4) DEFAULT NULL COMMENT '跳转类型',
  `receiver_type` tinyint(4) DEFAULT '1' COMMENT '接收者类型',
  `user_id` bigint DEFAULT NULL COMMENT '接收者id',
  `subject` varchar(30) NOT NULL COMMENT '标题',
  `message` longtext NOT NULL COMMENT '通知内容',
  `jump_meta_id` bigint DEFAULT NULL COMMENT '跳转元标识',
  `main_image` varchar(255) DEFAULT NULL COMMENT '图片',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  `deleted` bit(1) DEFAULT b'0' COMMENT '是否已删除',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='通知';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mt_num`
--
DROP TABLE IF EXISTS `mt_num`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_num` (
  `i` int DEFAULT NULL,
  `y` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='数字';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mt_user`
--
DROP TABLE IF EXISTS `mt_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(20) NOT NULL COMMENT '账号',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `sex` tinyint DEFAULT NULL COMMENT '性别',
  `phone` varchar(20) DEFAULT NULL COMMENT '账户手机号',
  `password` varchar(128) DEFAULT NULL COMMENT '登录密码',
  `app_id` tinyint DEFAULT '1' COMMENT '应用编号',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `token` varchar(512) DEFAULT NULL COMMENT '令牌',
  `avatar` varchar(512) DEFAULT NULL COMMENT '头像地址',
  `version` bigint DEFAULT NULL COMMENT '乐观锁版本号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  `deleted` bit(1) DEFAULT b'0' COMMENT '是否已删除',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE KEY `IX_PHONE_UNI` (`phone`) USING BTREE COMMENT '手机号码唯一'
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `mt_user_role`
--
DROP TABLE IF EXISTS `mt_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_user_role` (
  `ur_id` bigint NOT NULL AUTO_INCREMENT COMMENT '关联编号',
  `user_id` bigint NOT NULL COMMENT '账户id',
  `role_id` bigint NOT NULL COMMENT '关联角色表id',
  PRIMARY KEY (`ur_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='账户角色关联数据';
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `mt_role`
--
DROP TABLE IF EXISTS `mt_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `name` varchar(20) NOT NULL COMMENT '角色名称',
  `alias` varchar(20) DEFAULT NULL COMMENT '别名',
  `description` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `owner` bigint DEFAULT NULL COMMENT '拥有者',
  `editable` bit(1) DEFAULT b'1' COMMENT '可编辑',
  `app_id` tinyint DEFAULT '1' COMMENT '应用编号',
  `version` bigint DEFAULT NULL COMMENT '乐观锁版本号',
  `deletable` bit(1) DEFAULT NULL COMMENT '可删除',
  `super_admin` bit(1) DEFAULT NULL COMMENT '是否超级管理员',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` varchar(50) DEFAULT NULL COMMENT '状态',
  `deleted` bit(1) DEFAULT b'0' COMMENT '是否已删除',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mt_role_permission`
--
DROP TABLE IF EXISTS `mt_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_role_permission` (
  `rp_id` bigint NOT NULL COMMENT '关联编号',
  `role_id` bigint NOT NULL COMMENT '角色编号',
  `permission_id` bigint NOT NULL COMMENT '权限编号',
  PRIMARY KEY (`rp_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色权限关联数据';
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `mt_permission`
--
DROP TABLE IF EXISTS `mt_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_permission` (
  `permission_id` bigint NOT NULL AUTO_INCREMENT COMMENT '权限编号',
  `pid` bigint NOT NULL COMMENT '父节点id',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `path` varchar(255) DEFAULT NULL COMMENT '路径',
  `component` varchar(100) DEFAULT NULL COMMENT '组件',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标地址',
  `description` varchar(50) DEFAULT NULL COMMENT '文字描述',
  `owner` bigint DEFAULT NULL COMMENT '拥有者',
  `type` tinyint NOT NULL COMMENT '权限类型',
  `sort` int NOT NULL COMMENT '排序号',
  `app_id` tinyint DEFAULT '1' COMMENT '应用id',
  `hidden` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否隐藏菜单',
  `redirect` varchar(255) DEFAULT NULL COMMENT '重定向',
  `alias` varchar(50) DEFAULT NULL COMMENT '别名',
  `affix` bit(1) DEFAULT NULL COMMENT '是否固定到导航条',
  `hierarchy` varchar(255) DEFAULT NULL COMMENT '层级',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `version` bigint DEFAULT NULL COMMENT '乐观锁版本号',
  `status` varchar(50) DEFAULT NULL COMMENT '状态',
  `deleted` bit(1) DEFAULT b'0' COMMENT '是否已删除',
  PRIMARY KEY (`permission_id`) USING BTREE,
  KEY `IX_COMPONENT` (`app_id`,`owner`,`status`,`type`) USING BTREE,
  KEY `IX_PID` (`pid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='权限';
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `mt_rabbit_log`
--
DROP TABLE IF EXISTS `mt_rabbit_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_rabbit_log` (
  `rid` bigint NOT NULL AUTO_INCREMENT COMMENT '发送记录编号',
  `key` varchar(20) DEFAULT NULL COMMENT '键',
  `meta_id` bigint DEFAULT NULL COMMENT '元编号',
  `sent_time` datetime DEFAULT NULL COMMENT '发送时间',
  `message` varchar(5000) DEFAULT NULL COMMENT '消息',
  `timestamp` bigint DEFAULT NULL COMMENT '预定发送时间刻度数',
  `delay_time` bigint DEFAULT NULL COMMENT '延迟时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` varchar(20) DEFAULT NULL COMMENT '状态',
  `error_msg` varchar(5000) DEFAULT NULL COMMENT '错误消息',
  `routing_key` varchar(100) DEFAULT NULL COMMENT '路由键',
  `data_type` varchar(20) DEFAULT NULL COMMENT '数据类型',
  `bo_type` varchar(50) DEFAULT NULL COMMENT '业务类型',
  `delayed` bit(1) DEFAULT NULL COMMENT '是否延迟消息',
  `idempotent` bit(1) DEFAULT NULL COMMENT '是否独立模式',
  `version` bigint DEFAULT NULL COMMENT '乐观锁版本号',
  `half_mode` bit(1) DEFAULT NULL COMMENT '是否半开模式',
  PRIMARY KEY (`rid`) USING BTREE,
  KEY `IX_KEY` (`key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='RabbitMQ日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mt_web_log`
--
DROP TABLE IF EXISTS `mt_web_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_web_log` (
  `log_id` bigint NOT NULL AUTO_INCREMENT COMMENT '请求记录id',
  `name` varchar(50) DEFAULT NULL COMMENT '请求用户名',
  `uid` bigint DEFAULT NULL COMMENT '用户编号',
  `url` varchar(50) DEFAULT NULL COMMENT '请求接口地址',
  `api` varchar(50) DEFAULT NULL COMMENT '接口名称',
  `args` mediumtext COMMENT '请求接口参数',
  `app_name` varchar(20) DEFAULT NULL COMMENT '请求服务平台',
  `ip` varchar(15) DEFAULT NULL COMMENT 'IP地址',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `browser` varchar(20) DEFAULT NULL COMMENT '浏览器',
  `time` bigint DEFAULT NULL COMMENT '耗时',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `meta_id` bigint DEFAULT NULL COMMENT '关联数据编号',
  `meta_id_2` bigint DEFAULT NULL COMMENT '关联数据编号(扩展2)',
  `meta_id_3` bigint DEFAULT NULL COMMENT '关联数据编号(扩展3)',
  `type` smallint DEFAULT NULL COMMENT '日志类型',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='web请求记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mt_word`
--

DROP TABLE IF EXISTS `mt_word`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_word` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '关键词编号',
  `keyword` varchar(20) NOT NULL COMMENT '关键词内容',
  `status` varchar(20) NOT NULL DEFAULT '1' COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `type` varchar(20) DEFAULT NULL COMMENT '类型',
  `deleted` bit(1) DEFAULT NULL COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='关键词';
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `mt_chinese_city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_chinese_city` (
  `city_id` bigint NOT NULL COMMENT '城市编号',
  `city_pid` bigint NOT NULL COMMENT '父级编号',
  `city_name` varchar(20) DEFAULT NULL COMMENT '城市名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`city_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='中国城市';
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `mt_tenant_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_tenant_role` (
  `tr_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '关联编号',
  `tenant_id` BIGINT NULL COMMENT '租户编号',
  `role_id` BIGINT NULL COMMENT '角色编号',
  PRIMARY KEY (`tr_id`))
ENGINE = InnoDB AUTO_INCREMENT = 100 COMMENT = '租户角色关联';
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `mt_tenant_sub_account_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_tenant_sub_account_role` (
  `sar_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '关联编号',
  `sub_account_id` BIGINT NULL COMMENT '租户子账号编号',
  `role_id` BIGINT NULL COMMENT '角色编号',
  PRIMARY KEY (`sar_id`))
ENGINE = InnoDB AUTO_INCREMENT = 100 COMMENT = '租户子账号角色关联';
/*!40101 SET character_set_client = @saved_cs_client */;



DROP TABLE IF EXISTS `mt_wechat_app`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_wechat_app` (
  `program_id` bigint NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint DEFAULT NULL COMMENT '租户编号',
  `mini_app_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '小程序应用编号',
  `native_app_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Native应用编号',
  `secret` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密钥',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '状态',
  `deleted` bit(1) DEFAULT NULL COMMENT '是否已删除',
  `access_token` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商户接口调用凭证',
  `pre_auth_code` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '预授权码',
  `authorization_code` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '授权码',
  `authorizer_access_token` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '授权方令牌',
  `authorizer_refresh_token` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '刷新令牌',
  `authorizer_token_time` datetime DEFAULT NULL COMMENT '授权token最后更新时间',
  `authorizer_token_expires_in` int DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`program_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='微信应用';
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `mt_wechat_pay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_wechat_pay` (
  `wx_pay_id` bigint NOT NULL AUTO_INCREMENT COMMENT '微信支付编号',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户编号',
  `mch_id` varchar(12) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商户号',
  `cert_path` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '证书路径',
  `private_key_path` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商户私钥路径',
  `serial_number` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商户证书序列号',
  `api_v3_key` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'v3版本key',
  `api_v2_key` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'v2版本key',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '状态',
  `deleted` bit(1) DEFAULT NULL COMMENT '是否已删除',
  PRIMARY KEY (`wx_pay_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='微信支付';
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `mt_wechat_official_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_wechat_official_account` (
  `woa_id` bigint NOT NULL AUTO_INCREMENT COMMENT '微信公众号账户编号',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户编号',
  `app_id` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '应用编号',
  `app_secret` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '应用密钥',
  `token` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '令牌',
  `aes_key` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'AES密钥',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '状态',
  `deleted` bit(1) DEFAULT NULL COMMENT '是否已删除',
  PRIMARY KEY (`woa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='微信公众号账户';
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `mt_wechat_mpp_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_wechat_mpp_template` (
  `mpp_template_id` bigint NOT NULL AUTO_INCREMENT COMMENT '微信小程序模版编号',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户编号',
  `type` smallint DEFAULT NULL COMMENT '模版类型',
  `program_id` bigint DEFAULT NULL COMMENT '微信小程序编号',
  `template_id` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '模版编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '状态',
  `deleted` bit(1) DEFAULT NULL COMMENT '是否已删除',
  PRIMARY KEY (`mpp_template_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='微信小程序模版';
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `mt_wechat_app_func_scope`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_wechat_app_func_scope` (
  `fs_id` bigint NOT NULL AUTO_INCREMENT COMMENT '权限集关联编号',
  `program_id` bigint NOT NULL COMMENT 'app编号',
  `func_scope_id` int DEFAULT NULL COMMENT '权限集id',
  `type` int DEFAULT NULL COMMENT '权限集类型',
  PRIMARY KEY (`fs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='权限集';
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `mt_wechat_mpp_nick_name_audit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_wechat_mpp_nick_name_audit` (
  `audit_id` bigint NOT NULL COMMENT '审核单编号',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户编号',
  `status` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '状态',
  `app_id` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '小程序appid',
  `wording` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '材料说明',
  `nick_name` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '昵称',
  `reason` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '驳回原因',
  PRIMARY KEY (`audit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='微信小程序昵称审核单';
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `mt_wechat_mpp_code_audit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_wechat_mpp_code_audit` (
  `audit_id` bigint NOT NULL COMMENT '审核单编号',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户编号',
  `app_id` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商家小程序编号',
  `error_code` int DEFAULT NULL COMMENT '错误码',
  `error_msg` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `success_time` datetime DEFAULT NULL COMMENT '审核成功时间',
  `fail_time` datetime DEFAULT NULL COMMENT '失败时间',
  `delay_time` datetime DEFAULT NULL COMMENT '审核延后时间',
  `status` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '状态',
  `reason` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '审核不通过的原因',
  `screen_shot` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '截图',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`audit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='微信小程序代码审核单';

DROP TABLE IF EXISTS `mt_wechat_mpp_register_audit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_wechat_mpp_register_audit` (
  `audit_id` bigint NOT NULL AUTO_INCREMENT COMMENT '审核编号',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户编号',
  `task_id` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '任务编号',
  `app_id` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商家小程序编号',
  `error_code` int DEFAULT NULL COMMENT '错误码',
  `error_msg` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '错误信息',
  `auth_url` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '授权链接',
  `status` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '状态',
  `task_status` int DEFAULT NULL COMMENT '任务状态码',
  `apply_status` int DEFAULT NULL COMMENT '审核单状态',
  `message` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '提示信息',
  `provider` varchar(48) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '审核机构名称',
  `contact` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '审核机构联系方式',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `dispatch_time` datetime DEFAULT NULL COMMENT '派单时间',
  PRIMARY KEY (`audit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='微信小程序代码审核单';



/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
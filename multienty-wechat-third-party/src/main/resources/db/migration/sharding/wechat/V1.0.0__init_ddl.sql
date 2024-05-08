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
  `tenant_id` bigint DEFAULT NULL COMMENT '租户编号',
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
/*!40101 SET character_set_client = @saved_cs_client */;

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
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
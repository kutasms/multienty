DROP TABLE IF EXISTS `mt_wechat_beta_mpp_register_audit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_wechat_beta_mpp_register_audit` (
  `audit_id` bigint NOT NULL AUTO_INCREMENT COMMENT '审核编号',
  `tenant_id` bigint NOT NULL COMMENT '租户编号',
  `unique_id` varchar(64)  NOT NULL COMMENT '任务编号',
  `app_id` varchar(64) DEFAULT NULL COMMENT '商家小程序编号',
  `task_status` int DEFAULT NULL COMMENT '任务状态码',
  `name` varchar(32) NOT NULL COMMENT '小程序名称',
  `error_code` int DEFAULT NULL COMMENT '错误码',
  `error_msg` varchar(128) DEFAULT NULL COMMENT '错误信息',
  `authorize_url` varchar(256) NOT NULL COMMENT '授权链接',
  `open_id` varchar(128) NOT NULL COMMENT '微信用户openid',
  `union_id` varchar(128) NOT NULL COMMENT '微信用户unionid',
  `message` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '消息',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`audit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='微信试用小程序注册审核单';
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `mt_wechat_beta_mpp_auth_audit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_wechat_beta_mpp_auth_audit` (
  `audit_id` bigint NOT NULL AUTO_INCREMENT COMMENT '审核编号',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户编号',
  `unique_id` varchar(64)  DEFAULT NULL COMMENT '任务编号',
  `app_id` varchar(64) DEFAULT NULL COMMENT '商家小程序编号',
  `task_status` int DEFAULT NULL COMMENT '任务状态码',
  `params` json NOT NULL COMMENT '参数',
  `error_code` int DEFAULT NULL COMMENT '错误码',
  `error_msg` varchar(128) DEFAULT NULL COMMENT '错误信息',
  `message` varchar(128) DEFAULT NULL COMMENT '消息',
  `legal_persona_wechat` varchar(64) DEFAULT NULL COMMENT '法人微信号',
  `legal_persona_name` varchar(32) DEFAULT NULL COMMENT '法人姓名',
  `component_phone` varchar(32) DEFAULT NULL COMMENT '第三方联系电话',
  `company_name` varchar(32) DEFAULT NULL COMMENT '企业名称',
  `company_code` varchar(64) DEFAULT NULL COMMENT '企业代码',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`audit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='微信试用小程序转正审核单';
/*!40101 SET character_set_client = @saved_cs_client */;
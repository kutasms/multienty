DROP TABLE IF EXISTS `mt_wechat_mpp_auth_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_wechat_mpp_auth_task` (
  `task_id` bigint NOT NULL COMMENT '任务编号',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户编号',
  `app_id` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商家小程序编号',
  `task_status` int DEFAULT NULL COMMENT '状态码',
  `apply_status` int DEFAULT NULL COMMENT '审核单状态',
  `wx_task_id` varchar(64) NOT NULL COMMENT '微信任务编号',
  `params` json NOT NULL COMMENT '参数',
  `dispatch_info_provider` varchar(64) DEFAULT NULL COMMENT '审核机构名称',
  `dispatch_info_contact` varchar(128) DEFAULT NULL COMMENT '失败时间',
  `dispatch_info_dispatch_time` bigint(20) DEFAULT NULL COMMENT '派单时间戳（秒）',
  `status` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '状态',
  `message` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '提示信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='微信小程序认证任务';
/*!40101 SET character_set_client = @saved_cs_client */;
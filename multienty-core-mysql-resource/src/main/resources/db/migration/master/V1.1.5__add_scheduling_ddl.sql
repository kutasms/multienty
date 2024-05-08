--
-- Table structure for table `mt_scheduling_job`
--
DROP TABLE IF EXISTS `mt_scheduling_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mt_scheduling_job` (
  `job_id` bigint(20) NOT NULL COMMENT '任务编号',
  `job_name` varchar(64) NOT NULL COMMENT '任务名称',
  `bean_name` varchar(128) NOT NULL COMMENT '类名称',
  `method_name` varchar(128) NOT NULL COMMENT '方法名称',
  `params` varchar(1024) DEFAULT NULL COMMENT '参数',
  `cron_exp` varchar(255) NOT NULL COMMENT 'cron表达式',
  `remark` varchar(512) NOT NULL COMMENT '备注',
  `running_state` tinyint(4) NOT NULL COMMENT '运行状态',
  `status` varchar(32) NOT NULL COMMENT '状态',
  `version` bigint NOT NULL COMMENT '乐观锁版本号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='调度任务';
/*!40101 SET character_set_client = @saved_cs_client */;
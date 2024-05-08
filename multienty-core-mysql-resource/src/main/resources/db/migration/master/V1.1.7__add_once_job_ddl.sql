ALTER TABLE `saas_master`.`mt_scheduling_job`
ADD COLUMN `type` TINYINT(4) NULL COMMENT '任务类型' AFTER `update_time`,
ADD COLUMN `start_time` DATETIME NULL COMMENT '开始时间' AFTER `type`;
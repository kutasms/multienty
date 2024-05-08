ALTER TABLE `mt_scheduling_job`
ADD COLUMN `param_types` VARCHAR(256) NULL COMMENT '参数类型列表' AFTER `params`;

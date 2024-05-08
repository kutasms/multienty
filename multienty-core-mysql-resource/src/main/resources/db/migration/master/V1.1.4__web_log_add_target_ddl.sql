ALTER TABLE `saas_master`.`mt_web_log`
ADD COLUMN `target` VARCHAR(64) NULL COMMENT '目标对象' AFTER `url`;
ALTER TABLE `mt_tenant`
ADD COLUMN `username` VARCHAR(64) NULL COMMENT '帐号' AFTER `tenant_no`,
ADD COLUMN `password` VARCHAR(256) NULL COMMENT '密码' AFTER `username`,
ADD COLUMN `expired` BIT NULL COMMENT '是否已过期' AFTER `password`,
ADD COLUMN `locked` BIT NULL COMMENT '是否已锁定' AFTER `expired`;
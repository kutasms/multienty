ALTER TABLE `saas_master`.`mt_tenant_sub_account`
ADD COLUMN `create_time` DATETIME NULL COMMENT '创建时间' AFTER `version`,
ADD COLUMN `update_time` DATETIME NULL COMMENT '更新时间' AFTER `create_time`,
CHANGE COLUMN `username` `username` VARCHAR(32) NOT NULL COMMENT '账户' ,
CHANGE COLUMN `password` `password` VARCHAR(64) NOT NULL COMMENT '密码' ;

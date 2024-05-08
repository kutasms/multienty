ALTER TABLE `mt_wechat_app`
ADD COLUMN `create_mode` TINYINT NULL COMMENT '创建方式' AFTER `unique_id`,
CHANGE COLUMN `pre_auth_code` `pre_auth_code` VARCHAR(128) CHARACTER SET 'utf8mb4' NULL DEFAULT NULL COMMENT '预授权码' ;

ALTER TABLE `mt_wechat_app`
CHANGE COLUMN `access_token` `access_token` VARCHAR(256) CHARACTER SET 'utf8mb4' NULL DEFAULT NULL COMMENT '商户接口调用凭证' ,
CHANGE COLUMN `authorization_code` `authorization_code` VARCHAR(256) CHARACTER SET 'utf8mb4' NULL DEFAULT NULL COMMENT '授权码' ,
CHANGE COLUMN `authorizer_access_token` `authorizer_access_token` VARCHAR(256) CHARACTER SET 'utf8mb4' NULL DEFAULT NULL COMMENT '授权方令牌' ,
CHANGE COLUMN `authorizer_refresh_token` `authorizer_refresh_token` VARCHAR(256) CHARACTER SET 'utf8mb4' NULL DEFAULT NULL COMMENT '刷新令牌' ,
ADD COLUMN `realname_status` BIT NULL COMMENT '实名验证状态' AFTER `create_mode`,
ADD COLUMN `account_type` TINYINT NULL COMMENT '帐号类型' AFTER `realname_status`,
ADD COLUMN `principal_name` VARCHAR(128) NULL COMMENT '主体名称' AFTER `account_type`,
ADD COLUMN `qualification_verify` BIT NULL COMMENT '是否资质认证' AFTER `principal_name`,
ADD COLUMN `head_image_url` VARCHAR(256) NULL COMMENT '头像' AFTER `qualification_verify`;
ALTER TABLE `mt_wechat_mpp_register_audit`
DROP COLUMN `dispatch_time`,
DROP COLUMN `contact`,
DROP COLUMN `provider`,
DROP COLUMN `apply_status`,
DROP COLUMN `task_status`,
DROP COLUMN `task_id`,
ADD COLUMN `code` VARCHAR(64) NULL COMMENT '企业代码' AFTER `name`,
ADD COLUMN `code_type` INT NULL COMMENT '企业代码类型' AFTER `code`,
ADD COLUMN `legal_persona_wechat` VARCHAR(64) NULL COMMENT '法人微信号' AFTER `code_type`,
ADD COLUMN `legal_persona_name` VARCHAR(32) NULL COMMENT '法人姓名' AFTER `legal_persona_wechat`,
ADD COLUMN `component_phone` VARCHAR(32) NULL COMMENT '第三方联系电话' AFTER `legal_persona_name`,
ADD COLUMN `error_code` int NULL COMMENT '错误代码' AFTER `component_phone`,
ADD COLUMN `error_msg` VARCHAR(128) NULL COMMENT '错误信息' AFTER `error_code`,
CHANGE COLUMN `error_code` `wx_status` INT NULL DEFAULT NULL COMMENT '错误码' ,
CHANGE COLUMN `error_msg` `auth_code` VARCHAR(64) CHARACTER SET 'utf8mb4' NULL DEFAULT NULL COMMENT '错误信息' ,
CHANGE COLUMN `open_audit_id` `name` VARCHAR(64) NULL DEFAULT NULL COMMENT '企业名称' ;

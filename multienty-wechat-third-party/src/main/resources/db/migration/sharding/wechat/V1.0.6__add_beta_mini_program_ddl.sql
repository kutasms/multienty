ALTER TABLE `mt_wechat_app`
ADD COLUMN `unique_id` VARCHAR(128) NULL COMMENT '唯一识别号' AFTER `app_nick_name`;

ALTER TABLE `mt_wechat_mpp_auth_task`
DROP COLUMN `wx_task_id`;

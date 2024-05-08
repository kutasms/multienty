ALTER TABLE `mt_wechat_app`
ADD COLUMN `app_nick_name` VARCHAR(64) NULL COMMENT '应用昵称' AFTER `authorizer_token_expires_in`;

ALTER TABLE `mt_wechat_mpp_nick_name_audit`
ADD COLUMN `open_audit_id` VARCHAR(64) NULL COMMENT '微信返回审核编号' AFTER `reason`;

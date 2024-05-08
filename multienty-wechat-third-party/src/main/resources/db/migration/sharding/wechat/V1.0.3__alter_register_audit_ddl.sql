ALTER TABLE `mt_wechat_mpp_register_audit`
ADD COLUMN `open_audit_id` VARCHAR(64) NULL COMMENT '微信审核单编号' AFTER `dispatch_time`, COMMENT = '微信小程序注册审核单' ;

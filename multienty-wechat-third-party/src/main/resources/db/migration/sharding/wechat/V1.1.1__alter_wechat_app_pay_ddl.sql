ALTER TABLE `mt_wechat_app`
ADD COLUMN `sub_mch_id` VARCHAR(32) NULL COMMENT '微信支付商户号' AFTER `head_image_url`;

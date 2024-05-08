ALTER TABLE `mt_wechat_pay`
ADD COLUMN `program_id` BIGINT NULL COMMENT '程序编号' AFTER `private_key_path`;

ALTER TABLE `saas_master`.`mt_tenant_sub_account`
ADD COLUMN `username` VARCHAR(32) NOT NULL AFTER `tenant_id`,
ADD COLUMN `password` VARCHAR(45) NOT NULL AFTER `username`,
CHANGE COLUMN `tenant_id` `tenant_id` BIGINT NOT NULL COMMENT '租户编号' ,
CHANGE COLUMN `phone_number` `phone_number` VARCHAR(11) NOT NULL COMMENT '手机号码' ;

ALTER TABLE `saas_master`.`mt_app`
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ,
CHANGE COLUMN `status` `status` VARCHAR(20) NOT NULL COMMENT '状态' ;

ALTER TABLE `saas_master`.`mt_app_instance`
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ,
CHANGE COLUMN `status` `status` VARCHAR(20) NOT NULL COMMENT '状态' ;

ALTER TABLE `saas_master`.`mt_chinese_city`
CHANGE COLUMN `city_name` `city_name` VARCHAR(20) NOT NULL COMMENT '城市名称' ,
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ;

ALTER TABLE `saas_master`.`mt_dict`
CHANGE COLUMN `value_type` `value_type` VARCHAR(50) NOT NULL COMMENT '值类型' ,
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ,
CHANGE COLUMN `deletable` `deletable` BIT(1) NOT NULL DEFAULT b'1' COMMENT '是否可删除' ,
CHANGE COLUMN `status` `status` VARCHAR(20) NOT NULL COMMENT '状态' ,
CHANGE COLUMN `encrypted` `encrypted` BIT(1) NOT NULL COMMENT '是否已加密' ;

ALTER TABLE `saas_master`.`mt_json_config`
CHANGE COLUMN `data` `data` JSON NOT NULL COMMENT 'JSON数据' ,
CHANGE COLUMN `default_data` `default_data` JSON NOT NULL COMMENT '默认数据' ,
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ,
CHANGE COLUMN `status` `status` VARCHAR(32) NOT NULL COMMENT '状态' ;

ALTER TABLE `saas_master`.`mt_label`
CHANGE COLUMN `label` `label` VARCHAR(50) NOT NULL COMMENT '标签' ,
CHANGE COLUMN `type` `type` SMALLINT NOT NULL COMMENT '类型' ,
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ,
CHANGE COLUMN `status` `status` VARCHAR(50) NOT NULL COMMENT '状态' ,
CHANGE COLUMN `deleted` `deleted` BIT(1) NOT NULL COMMENT '是否已删除' ;

ALTER TABLE `saas_master`.`mt_notice`
CHANGE COLUMN `receiver_type` `receiver_type` TINYINT NOT NULL DEFAULT '1' COMMENT '接收者类型' ,
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ,
CHANGE COLUMN `status` `status` VARCHAR(32) NOT NULL COMMENT '状态' ,
CHANGE COLUMN `deleted` `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否已删除' ;

ALTER TABLE `saas_master`.`mt_permission`
CHANGE COLUMN `path` `path` VARCHAR(255) NOT NULL COMMENT '路径' ,
CHANGE COLUMN `component` `component` VARCHAR(100) NOT NULL COMMENT '组件' ,
CHANGE COLUMN `icon` `icon` VARCHAR(255) NULL COMMENT '图标地址' ,
CHANGE COLUMN `owner` `owner` BIGINT NOT NULL COMMENT '拥有者' ,
CHANGE COLUMN `hierarchy` `hierarchy` VARCHAR(255) NOT NULL COMMENT '层级' ,
CHANGE COLUMN `version` `version` BIGINT NOT NULL COMMENT '乐观锁版本号' ,
CHANGE COLUMN `status` `status` VARCHAR(50) NOT NULL COMMENT '状态' ,
CHANGE COLUMN `deleted` `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否已删除' ;

ALTER TABLE `saas_master`.`mt_plugin`
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ,
CHANGE COLUMN `status` `status` VARCHAR(20) NOT NULL COMMENT '状态' ;

ALTER TABLE `saas_master`.`mt_plugin_contract`
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ,
CHANGE COLUMN `status` `status` VARCHAR(20) NOT NULL COMMENT '状态' ;

ALTER TABLE `saas_master`.`mt_plugin_dependency`
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ;

ALTER TABLE `saas_master`.`mt_plugin_price_strategy`
CHANGE COLUMN `price` `price` DECIMAL(12,2) NOT NULL COMMENT '价格' ,
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ,
CHANGE COLUMN `update_time` `update_time` DATETIME NULL COMMENT '更新时间' ,
CHANGE COLUMN `status` `status` VARCHAR(20) NOT NULL COMMENT '状态' ;

ALTER TABLE `saas_master`.`mt_plugin_res`
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ;

ALTER TABLE `saas_master`.`mt_plugin_stat`
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ;

ALTER TABLE `saas_master`.`mt_rabbit_log`
CHANGE COLUMN `key` `key` VARCHAR(20) NOT NULL COMMENT '键' ,
CHANGE COLUMN `meta_id` `meta_id` BIGINT NOT NULL COMMENT '元编号' ,
CHANGE COLUMN `message` `message` VARCHAR(5000) NOT NULL COMMENT '消息' ,
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ,
CHANGE COLUMN `status` `status` VARCHAR(20) NOT NULL COMMENT '状态' ,
CHANGE COLUMN `routing_key` `routing_key` VARCHAR(100) NOT NULL COMMENT '路由键' ,
CHANGE COLUMN `data_type` `data_type` VARCHAR(20) NOT NULL COMMENT '数据类型' ,
CHANGE COLUMN `bo_type` `bo_type` VARCHAR(50) NOT NULL COMMENT '业务类型' ,
CHANGE COLUMN `delayed` `delayed` BIT(1) NOT NULL COMMENT '是否延迟消息' ,
CHANGE COLUMN `idempotent` `idempotent` BIT(1) NOT NULL COMMENT '是否独立模式' ,
CHANGE COLUMN `version` `version` BIGINT NOT NULL COMMENT '乐观锁版本号' ,
CHANGE COLUMN `half_mode` `half_mode` BIT(1) NOT NULL COMMENT '是否半开模式' ;

ALTER TABLE `saas_master`.`mt_role`
CHANGE COLUMN `owner` `owner` BIGINT NOT NULL COMMENT '拥有者' ,
CHANGE COLUMN `editable` `editable` BIT(1) NOT NULL DEFAULT b'1' COMMENT '可编辑' ,
CHANGE COLUMN `version` `version` BIGINT NOT NULL COMMENT '乐观锁版本号' ,
CHANGE COLUMN `deletable` `deletable` BIT(1) NOT NULL COMMENT '可删除' ,
CHANGE COLUMN `super_admin` `super_admin` BIT(1) NOT NULL COMMENT '是否超级管理员' ,
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ,
CHANGE COLUMN `status` `status` VARCHAR(50) NOT NULL COMMENT '状态' ,
CHANGE COLUMN `deleted` `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否已删除' ;

ALTER TABLE `saas_master`.`mt_secret_auth`
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ,
CHANGE COLUMN `status` `status` VARCHAR(20) NOT NULL COMMENT '状态' ,
CHANGE COLUMN `deleted` `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否已删除' ;

ALTER TABLE `saas_master`.`mt_setting`
CHANGE COLUMN `name` `name` VARCHAR(32) NOT NULL COMMENT '名称' ,
CHANGE COLUMN `value` `value` VARCHAR(256) NOT NULL COMMENT '值' ,
CHANGE COLUMN `default_value` `default_value` VARCHAR(256) NOT NULL COMMENT '默认值' ,
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ,
CHANGE COLUMN `owner` `owner` BIGINT NOT NULL COMMENT '拥有者' ;

ALTER TABLE `saas_master`.`mt_tenant`
CHANGE COLUMN `username` `username` VARCHAR(64) NOT NULL COMMENT '帐号' ,
CHANGE COLUMN `password` `password` VARCHAR(256) NOT NULL COMMENT '密码' ,
CHANGE COLUMN `expired` `expired` BIT(1) NOT NULL COMMENT '是否已过期' ,
CHANGE COLUMN `locked` `locked` BIT(1) NOT NULL COMMENT '是否已锁定' ,
CHANGE COLUMN `company_name` `company_name` VARCHAR(100) NOT NULL COMMENT '公司名称' ,
CHANGE COLUMN `phone_number` `phone_number` VARCHAR(11) NOT NULL COMMENT '手机号码' ,
CHANGE COLUMN `liaison` `liaison` VARCHAR(48) NOT NULL COMMENT '联系人' ,
CHANGE COLUMN `type` `type` TINYINT NOT NULL COMMENT '租户类型' ,
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ,
CHANGE COLUMN `status` `status` VARCHAR(20) NOT NULL COMMENT '状态' ,
CHANGE COLUMN `deleted` `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否已删除'  ;

ALTER TABLE `saas_master`.`mt_tenant_role`
CHANGE COLUMN `tenant_id` `tenant_id` BIGINT NOT NULL COMMENT '租户编号' ,
CHANGE COLUMN `role_id` `role_id` BIGINT NOT NULL COMMENT '角色编号' ;

ALTER TABLE `saas_master`.`mt_tenant_secret`
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ,
CHANGE COLUMN `status` `status` VARCHAR(20) NOT NULL COMMENT '状态' ,
CHANGE COLUMN `deleted` `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否已删除' ;

ALTER TABLE `saas_master`.`mt_tenant_sub_account_role`
CHANGE COLUMN `sub_account_id` `sub_account_id` BIGINT NOT NULL COMMENT '租户子账号编号' ,
CHANGE COLUMN `role_id` `role_id` BIGINT NOT NULL COMMENT '角色编号' ,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`sar_id`, `sub_account_id`, `role_id`);

ALTER TABLE `saas_master`.`mt_uploaded_file`
CHANGE COLUMN `path` `path` VARCHAR(256) NOT NULL COMMENT '文件路径' ,
CHANGE COLUMN `url` `url` VARCHAR(256) NOT NULL COMMENT '文件URL地址' ,
CHANGE COLUMN `type` `type` TINYINT NOT NULL COMMENT '文件类型' ,
CHANGE COLUMN `name` `name` VARCHAR(256) NOT NULL COMMENT '文件名称' ,
CHANGE COLUMN `extension` `extension` VARCHAR(4) NOT NULL COMMENT '文件扩展名' ,
CHANGE COLUMN `meta_id` `meta_id` BIGINT NULL COMMENT '关联编号' ,
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ,
CHANGE COLUMN `status` `status` VARCHAR(64) NOT NULL COMMENT '状态' ,
CHANGE COLUMN `org_file_name` `org_file_name` VARCHAR(128) NOT NULL COMMENT '原始文件名' ;

ALTER TABLE `saas_master`.`mt_user`
CHANGE COLUMN `phone` `phone` VARCHAR(20) NOT NULL COMMENT '账户手机号' ,
CHANGE COLUMN `password` `password` VARCHAR(256) NOT NULL COMMENT '登录密码' ,
CHANGE COLUMN `avatar` `avatar` VARCHAR(512) NOT NULL COMMENT '头像地址' ,
CHANGE COLUMN `version` `version` BIGINT NOT NULL COMMENT '乐观锁版本号' ,
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ,
CHANGE COLUMN `status` `status` VARCHAR(32) NOT NULL COMMENT '状态' ,
CHANGE COLUMN `deleted` `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否已删除' ,
CHANGE COLUMN `expired` `expired` BIT(1) NOT NULL COMMENT '是否已过期' ,
CHANGE COLUMN `locked` `locked` BIT(1) NOT NULL COMMENT '是否已锁定' ;

ALTER TABLE `saas_master`.`mt_web_log`
CHANGE COLUMN `name` `name` VARCHAR(50) NOT NULL COMMENT '请求用户名' ,
CHANGE COLUMN `uid` `uid` BIGINT NOT NULL COMMENT '用户编号' ,
CHANGE COLUMN `url` `url` VARCHAR(50) NOT NULL COMMENT '请求接口地址' ,
CHANGE COLUMN `api` `api` VARCHAR(50) NOT NULL COMMENT '接口名称' ,
CHANGE COLUMN `args` `args` MEDIUMTEXT NOT NULL COMMENT '请求接口参数' ,
CHANGE COLUMN `app_name` `app_name` VARCHAR(20) NOT NULL COMMENT '请求服务平台' ,
CHANGE COLUMN `ip` `ip` VARCHAR(15) NOT NULL COMMENT 'IP地址' ,
CHANGE COLUMN `browser` `browser` VARCHAR(20) NOT NULL COMMENT '浏览器' ,
CHANGE COLUMN `time` `time` BIGINT NOT NULL COMMENT '耗时' ,
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ,
CHANGE COLUMN `type` `type` SMALLINT NOT NULL COMMENT '日志类型' ;

ALTER TABLE `saas_master`.`mt_wechat_app`
CHANGE COLUMN `tenant_id` `tenant_id` BIGINT NOT NULL COMMENT '租户编号' ,
CHANGE COLUMN `secret` `secret` VARCHAR(64) CHARACTER SET 'utf8mb4' NOT NULL COMMENT '密钥' ,
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ,
CHANGE COLUMN `status` `status` VARCHAR(32) CHARACTER SET 'utf8mb4' NOT NULL COMMENT '状态' ,
CHANGE COLUMN `deleted` `deleted` BIT(1) NOT NULL COMMENT '是否已删除' ,
CHANGE COLUMN `access_token` `access_token` VARCHAR(128) CHARACTER SET 'utf8mb4' NULL COMMENT '商户接口调用凭证' ,
CHANGE COLUMN `pre_auth_code` `pre_auth_code` VARCHAR(64) CHARACTER SET 'utf8mb4' NULL COMMENT '预授权码' ;

ALTER TABLE `saas_master`.`mt_wechat_app_func_scope`
CHANGE COLUMN `func_scope_id` `func_scope_id` INT NOT NULL COMMENT '权限集id' ,
CHANGE COLUMN `type` `type` INT NOT NULL COMMENT '权限集类型' ;

ALTER TABLE `saas_master`.`mt_wechat_mpp_code_audit`
CHANGE COLUMN `tenant_id` `tenant_id` BIGINT NOT NULL COMMENT '租户编号' ,
CHANGE COLUMN `app_id` `app_id` VARCHAR(64) CHARACTER SET 'utf8mb4' NOT NULL COMMENT '商家小程序编号' ,
CHANGE COLUMN `status` `status` VARCHAR(32) CHARACTER SET 'utf8mb4' NOT NULL COMMENT '状态' ,
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ;

ALTER TABLE `saas_master`.`mt_wechat_mpp_nick_name_audit`
CHANGE COLUMN `tenant_id` `tenant_id` BIGINT NOT NULL COMMENT '租户编号' ,
CHANGE COLUMN `status` `status` VARCHAR(32) CHARACTER SET 'utf8mb4' NOT NULL COMMENT '状态' ,
CHANGE COLUMN `app_id` `app_id` VARCHAR(64) CHARACTER SET 'utf8mb4' NOT NULL COMMENT '小程序appid' ,
CHANGE COLUMN `nick_name` `nick_name` VARCHAR(32) CHARACTER SET 'utf8mb4' NULL COMMENT '昵称' ;

ALTER TABLE `saas_master`.`mt_wechat_mpp_register_audit`
CHANGE COLUMN `tenant_id` `tenant_id` BIGINT NOT NULL COMMENT '租户编号' ,
CHANGE COLUMN `task_id` `task_id` VARCHAR(64) CHARACTER SET 'utf8mb4' NOT NULL COMMENT '任务编号' ,
CHANGE COLUMN `app_id` `app_id` VARCHAR(64) CHARACTER SET 'utf8mb4' NOT NULL COMMENT '商家小程序编号' ,
CHANGE COLUMN `status` `status` VARCHAR(32) CHARACTER SET 'utf8mb4' NOT NULL COMMENT '状态' ,
CHANGE COLUMN `task_status` `task_status` INT NOT NULL COMMENT '任务状态码' ,
CHANGE COLUMN `apply_status` `apply_status` INT NOT NULL COMMENT '审核单状态' ,
CHANGE COLUMN `provider` `provider` VARCHAR(48) CHARACTER SET 'utf8mb4' NOT NULL COMMENT '审核机构名称' ,
CHANGE COLUMN `contact` `contact` VARCHAR(32) CHARACTER SET 'utf8mb4' NOT NULL COMMENT '审核机构联系方式' ,
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ,
CHANGE COLUMN `dispatch_time` `dispatch_time` DATETIME NOT NULL COMMENT '派单时间' ;

ALTER TABLE `saas_master`.`mt_wechat_mpp_template`
CHANGE COLUMN `tenant_id` `tenant_id` BIGINT NOT NULL COMMENT '租户编号' ,
CHANGE COLUMN `type` `type` SMALLINT NOT NULL COMMENT '模版类型' ,
CHANGE COLUMN `program_id` `program_id` BIGINT NOT NULL COMMENT '微信小程序编号' ,
CHANGE COLUMN `template_id` `template_id` VARCHAR(128) CHARACTER SET 'utf8mb4' NOT NULL COMMENT '模版编号' ,
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ,
CHANGE COLUMN `status` `status` VARCHAR(32) CHARACTER SET 'utf8mb4' NOT NULL COMMENT '状态' ,
CHANGE COLUMN `deleted` `deleted` BIT(1) NOT NULL COMMENT '是否已删除' ;

ALTER TABLE `saas_master`.`mt_wechat_official_account`
CHANGE COLUMN `tenant_id` `tenant_id` BIGINT NOT NULL COMMENT '租户编号' ,
CHANGE COLUMN `app_id` `app_id` VARCHAR(64) CHARACTER SET 'utf8mb4' NOT NULL COMMENT '应用编号' ,
CHANGE COLUMN `app_secret` `app_secret` VARCHAR(64) CHARACTER SET 'utf8mb4' NOT NULL COMMENT '应用密钥' ,
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ,
CHANGE COLUMN `status` `status` VARCHAR(32) CHARACTER SET 'utf8mb4' NOT NULL COMMENT '状态' ,
CHANGE COLUMN `deleted` `deleted` BIT(1) NOT NULL COMMENT '是否已删除' ;

ALTER TABLE `saas_master`.`mt_wechat_pay`
CHANGE COLUMN `tenant_id` `tenant_id` BIGINT NOT NULL COMMENT '租户编号' ,
CHANGE COLUMN `mch_id` `mch_id` VARCHAR(12) CHARACTER SET 'utf8mb4' NOT NULL COMMENT '商户号' ,
CHANGE COLUMN `cert_path` `cert_path` VARCHAR(128) CHARACTER SET 'utf8mb4' NOT NULL COMMENT '证书路径' ,
CHANGE COLUMN `private_key_path` `private_key_path` VARCHAR(128) CHARACTER SET 'utf8mb4' NOT NULL COMMENT '商户私钥路径' ,
CHANGE COLUMN `serial_number` `serial_number` VARCHAR(128) CHARACTER SET 'utf8mb4' NULL COMMENT '商户证书序列号' ,
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ,
CHANGE COLUMN `status` `status` VARCHAR(32) CHARACTER SET 'utf8mb4' NOT NULL COMMENT '状态' ,
CHANGE COLUMN `deleted` `deleted` BIT(1) NOT NULL COMMENT '是否已删除' ;

ALTER TABLE `saas_master`.`mt_word`
CHANGE COLUMN `create_time` `create_time` DATETIME NOT NULL COMMENT '创建时间' ,
CHANGE COLUMN `type` `type` VARCHAR(20) NOT NULL COMMENT '类型' ,
CHANGE COLUMN `deleted` `deleted` BIT(1) NOT NULL COMMENT '是否已删除' ;
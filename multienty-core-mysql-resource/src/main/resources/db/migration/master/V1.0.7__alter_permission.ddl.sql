ALTER TABLE `mt_permission`
ADD COLUMN `api` VARCHAR(128) NULL COMMENT '后端api接口' AFTER `path`;

INSERT INTO `mt_permission` (`pid`, `name`, `path`, `api`, `component`, `description`, `owner`, `type`, `sort`, `app_id`, `hidden`, `affix`, `hierarchy`, `create_time`, `version`, `status`, `deleted`)
VALUES (0, 'OAuth获取用户信息', '/', '/oauth/get_info', 'oauth-get-info', 'OAuth获取用户信息', 1, 2, 3, '1', b'1', b'0', '/', '2024-03-08 17:44:00', '1', 'NORMAL', 0);
DELETE FROM `mt_user` WHERE `phone` = '13880985300';
INSERT INTO `mt_user` (`username`, `name`, `sex`, `phone`, `password`, `app_id`, `email`, `version`, `create_time`, `status`, `deleted`) VALUES ('root', 'root', 1, '13880985300', 'e76a34889b48a4682e48e41697b59ce29e1325038087cc5f', 1, '7437280@qq.com', 1, now(), 'NORMAL', 0);
DELETE FROM `mt_role` WHERE `name` = 'administrator';
INSERT INTO `mt_role` (`name`, `alias`, `description`, `owner`, `editable`, `app_id`, `version`, `deletable`, `super_admin`, `create_time`, `status`, `deleted`) VALUES ('administrator', 'admin', '超级管理员', 1, 0, 1, 1, 0, 1, now(), 'NORMAL', 0);
DELETE FROM `mt_user_role` WHERE `user_id` = 100 AND `role_id` = 100;
INSERT INTO `mt_user_role` (`user_id`, `role_id`) VALUES(100, 100);

INSERT INTO `mt_permission` (`pid`, `name`, `path`, `component`, `icon`, `description`, `owner`, `type`, `sort`, `app_id`, `hidden`, `redirect`, `affix`, `hierarchy`, `version`, `status`, `deleted`,`create_time`)
VALUES
(0, '系统设置', '/system', 'Layout', 'cogs', '系统设置', 1, 1, 9999, 1, 0, 'noRedirect', 0, '/', b'1', 'NORMAL', 0, now()),
(100, '字典菜单', 'dict', 'system/dict', 'el-icon-place', '字典菜单', 1, 1, 1, 1, 0, NULL, 0, '/100', b'1', 'NORMAL', 0, now()),
(100, '角色管理', 'role', 'system/role', 'el-icon-warning-outline', '角色管理', 1, 1, 2, 1, 0, NULL, 0, '/100', b'1', 'NORMAL', 0, now()),
(100, '功能管理', 'menu', 'system/menu', 'el-icon-location-outline', '功能管理', 1, 1, 3, 1, 0, NULL, 0, '/100', b'1', 'NORMAL', 0, now()),
(100, '用户管理', 'user', 'system/user', 'el-icon-zoom-in', '用户管理', 1, 1, 4, 1, 0, NULL, 0, '/100', b'1', 'NORMAL', 0, now()),
(100, '更新日志', 'updateLog', 'system/updateLog', '', '更新日志', 1, 1, 5, 1, 0, NULL, 0, '/100', b'1', 'NORMAL', 0, now()),
(100, '图标', 'icon', 'system/icon', '', '图标', 1, 1, 6, 1, 0, NULL, 0, '/100', b'1', 'NORMAL', 0, now()),
(0, '首页', '/', 'Layout', 'border-all', '首页', 1, 1, 0, 1, 0, NULL, 0, '/', b'1', 'NORMAL', 0, now()),
(107, 'Home', 'index', 'index/index', 'book-open', '首页', 1, 1, 1, 1, 0, NULL, 0, '/107', b'1', 'NORMAL', 0, now()),
(101, '新增父级字典', '/', 'add-parent-dict', '', '新增父级字典', 1, 2, 1, 1, 0, NULL, 0, '/100/101', b'1', 'NORMAL', 0, now()),
(101, '编辑父级字典', '/', 'edit-parent-dict', '', '编辑父级字典', 1, 2, 2, 1, 0, NULL, 0, '/100/101', b'1', 'NORMAL', 0, now()),
(101, '删除父级字典', '/', 'delete-parent-dict', '', '删除父级字典', 1, 2, 3, 1, 0, NULL, 0, '/100/101', b'1', 'NORMAL', 0, now()),
(101, '新增子级字典', '/', 'add-child-dict', '', '新增子级字典', 1, 2, 4, 1, 0, NULL, 0, '/100/101', b'1', 'NORMAL', 0, now()),
(101, '编辑子级字典', '/', 'edit-child-dict', '', '编辑子级字典', 1, 2, 5, 1, 0, NULL, 0, '/100/101', b'1', 'NORMAL', 0, now()),
(101, '删除子级字典', '/', 'delete-child-dict', '', '删除子级字典', 1, 2, 6, 1, 0, NULL, 0, '/100/101', b'1', 'NORMAL', 0, now()),
(102, '新增角色', '/', 'add-role', '', '新增角色', 1, 2, 1, 1, 0, NULL, 0, '/100/102', b'1', 'NORMAL', 0, now()),
(102, '编辑角色', '/', 'edit-role', '', '编辑角色', 1, 2, 2, 1, 0, NULL, 0, '/100/102', b'1', 'NORMAL', 0, now()),
(102, '删除角色', '/', 'delete-role', '', '删除角色', 1, 2, 3, 1, 0, NULL, 0, '/100/102', b'1', 'NORMAL', 0, now()),
(102, '禁用角色', '/', 'disable-role', '', '禁用角色', 1, 2, 4, 1, 0, NULL, 0, '/100/102', b'1', 'NORMAL', 0, now()),
(102, '启用角色', '/', 'enable-role', '', '启用角色', 1, 2, 5, 1, 0, NULL, 0, '/100/102', b'1', 'NORMAL', 0, now()),
(102, '查看角色日志', '/', 'view-role-log', '', '查看角色日志', 1, 2, 6, 1, 0, NULL, 0, '/100/102', b'1', 'NORMAL', 0, now()),
(103, '新增顶层菜单', '/', 'add-top-menu', '', '新增顶层菜单', 1, 2, 1, 1, 0, NULL, 0, '/100/103', b'1', 'NORMAL', 0, now()),
(103, '新增子级菜单', '/', 'add-child-menu', '', '新增子级菜单', 1, 2, 2, 1, 0, NULL, 0, '/100/103', b'1', 'NORMAL', 0, now()),
(103, '查看功能详情', '/', 'view-function-detail', '', '查看功能详情', 1, 2, 3, 1, 0, NULL, 0, '/100/103', b'1', 'NORMAL', 0, now()),
(103, '编辑功能', '/', 'edit-function', '', '编辑功能', 1, 2, 4, 1, 0, NULL, 0, '/100/103', b'1', 'NORMAL', 0, now()),
(103, '删除功能', '/', 'delete-function', '', '删除功能', 1, 2, 5, 1, 0, NULL, 0, '/100/103', b'1', 'NORMAL', 0, now()),
(103, '禁用功能', '/', 'disable-function', '', '禁用功能', 1, 2, 6, 1, 0, NULL, 0, '/100/103', b'1', 'NORMAL', 0, now()),
(103, '启用功能', '/', 'enable-function', '', '启用功能', 1, 2, 7, 1, 0, NULL, 0, '/100/103', b'1', 'NORMAL', 0, now()),
(103, '查看功能日志', '/', 'view-function-log', '', '查看功能日志', 1, 2, 8, 1, 0, NULL, 0, '/100/103', b'1', 'NORMAL', 0, now()),
(104, '新增用户', '/', 'add-user', '', '新增用户', 1, 2, 1, 1, 0, NULL, 0, '/100/104', b'1', 'NORMAL', 0, now()),
(104, '编辑用户', '/', 'edit-user', '', '编辑用户', 1, 2, 2, 1, 0, NULL, 0, '/100/104', b'1', 'NORMAL', 0, now()),
(104, '删除用户', '/', 'delete-user', '', '删除用户', 1, 2, 3, 1, 0, NULL, 0, '/100/104', b'1', 'NORMAL', 0, now()),
(104, '禁用用户', '/', 'disable-user', '', '禁用用户', 1, 2, 4, 1, 0, NULL, 0, '/100/104', b'1', 'NORMAL', 0, now()),
(104, '启用用户', '/', 'enable-user', '', '启用用户', 1, 2, 5, 1, 0, NULL, 0, '/100/104', b'1', 'NORMAL', 0, now()),
(104, '查看用户日志', '/', 'view-user-log', '', '查看用户日志', 1, 2, 6, 1, 0, NULL, 0, '/100/104', b'1', 'NORMAL', 0, now());

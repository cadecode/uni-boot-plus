-- 系统用户组
insert sys_user_group (id, group_name, status, parent_id, order_num, ancestors, remark)
values (1, '测试组织1', 1, null, 1, null, '用于测试的组织数据');
insert sys_user_group (id, group_name, status, parent_id, order_num, ancestors, remark)
values (2, '测试部门1-1', 1, null, 11, '1', '用于测试的组织数据');
insert sys_user_group (id, group_name, status, parent_id, order_num, ancestors, remark)
values (3, '测试部门1-2', 1, null, 12, '1', '用于测试的组织数据');
insert sys_user_group (id, group_name, status, parent_id, order_num, ancestors, remark)
values (4, '测试科室1-1-1', 2, null, 111, '1,2', '用于测试的组织数据');

-- 系统用户
-- 默认密码 123456
insert sys_user(id, username, nickname, group_id, password, status)
values (1, 'admin', '管理员', 1, '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 1);
insert sys_user(id, username, nickname, group_id, password, status)
values (2, 'manager1', '高级用户1', 3, '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 1);
insert sys_user(id, username, nickname, group_id, password, status)
values (3, 'normal1', '普通用户1', 4, '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 1);

-- 系统角色
insert sys_role(id, role_code, role_name, status, remark)
values (1, 'admin', '管理员', 1, '管理员角色');
insert sys_role(id, role_code, role_name, status, remark)
values (2, 'manager', '高级用户', 1, '高级用户角色');
insert sys_role(id, role_code, role_name, status, remark)
values (3, 'normal', '普通用户', 1, '普通用户角色');

-- 系统角色用户关系
insert sys_role_user(role_id, user_id)
values (1, 1);
insert sys_role_user(role_id, user_id)
values (2, 2);
insert sys_role_user(role_id, user_id)
values (3, 3);

-- 系统权限
insert sys_permission(id, permission_type, permission_code, permission_name, status, parent_id, route_type, order_num,
                      route_path, component_path, redirect, icon, show_link)
values (1, 'ROUTE', 'SystemManagement', '系统管理', 1, null, 'MENU', 1, '/system', null, null, 'ri:settings-3-line', 1);
insert sys_permission(id, permission_type, permission_code, permission_name, status, parent_id, route_type, order_num,
                      route_path, component_path, redirect, icon, show_link)
values (2, 'ROUTE', 'UserManagement', '用户管理', 1, 1, 'MENU', 11, '/system/user/index', null, null, 'ri:admin-line',
        1);
insert sys_permission(id, permission_type, permission_code, permission_name, status, parent_id, route_type, order_num,
                      route_path, component_path, redirect, icon, show_link)
values (3, 'ROUTE', 'UserGroupManagement', '组织管理', 1, 1, 'MENU', 12, '/system/user_group/index', null, null,
        'ri:git-branch-line', 1);
insert sys_permission(id, permission_type, permission_code, permission_name, status, parent_id, route_type, order_num,
                      route_path, component_path, redirect, icon, show_link)
values (4, 'ROUTE', 'RoleManagement', '角色管理', 1, 1, 'MENU', 13, '/system/role/index', null, null, 'ri:admin-fill',
        1);
insert sys_permission(id, permission_type, permission_code, permission_name, status, parent_id, route_type, order_num,
                      route_path, component_path, redirect, icon, show_link)
values (5, 'ROUTE', 'PermissionManagement', '权限管理', 1, 1, 'MENU', 14, '/system/permission/index', null, null,
        'ep:menu', 1);

insert sys_permission(id, permission_type, permission_code, permission_name, status, parent_id, route_type, order_num,
                      route_path, component_path, redirect, icon, show_link)
values (7, 'ROUTE', 'SystemMonitor', '系统监控', 1, null, 'MENU', 2, '/monitor', null, null, 'ep:monitor', 1);
insert sys_permission(id, permission_type, permission_code, permission_name, status, parent_id, route_type, order_num,
                      route_path, component_path, redirect, icon, show_link)
values (8, 'ROUTE', 'OnlineUser', '在线用户', 1, 7, 'MENU', 21, '/monitor/online-user', 'monitor/online-user/index',
        null, 'ri:user-voice-line', 1);
insert sys_permission(id, permission_type, permission_code, permission_name, status, parent_id, route_type, order_num,
                      route_path, component_path, redirect, icon, show_link)
values (9, 'ROUTE', 'SystemLog', '系统日志', 1, 7, 'MENU', 22, '/monitor/system-logs', 'monitor/logs/system/index',
        null, 'ri:file-search-line', 1);

insert sys_permission(id, permission_type, permission_code, permission_name, status, parent_id, route_type, order_num,
                      route_path, component_path, redirect, icon, show_link)
values (10, 'ROUTE', 'PermissionExample', '权限示例', 1, null, 'MENU', 4, '/permission', null, null, 'ep:lollipop', 1);
insert sys_permission(id, permission_type, permission_code, permission_name, status, parent_id, route_type, order_num,
                      route_path, component_path, redirect, icon, show_link)
values (11, 'ROUTE', 'PermissionPage', '页面权限', 1, 10, 'MENU', 41, '/permission/page/index', null, null, null, 1);
insert sys_permission(id, permission_type, permission_code, permission_name, status, parent_id, route_type, order_num,
                      route_path, component_path, redirect, icon, show_link)
values (12, 'ROUTE', 'PermissionButton', '按钮权限', 1, 10, 'MENU', 42, '/permission/button/index', null, null, null,
        1);
insert sys_permission(id, permission_type, permission_code, permission_name, status, parent_id, route_type, order_num,
                      route_path, component_path, redirect, icon, show_link)
values (13, 'API', 'permission:btn:add', 'btn添加', 1, 12, 'MENU', 421, null, null, null, null, null);
insert sys_permission(id, permission_type, permission_code, permission_name, status, parent_id, route_type, order_num,
                      route_path, component_path, redirect, icon, show_link)
values (14, 'API', 'permission:btn:edit', 'btn编辑', 1, 12, 'MENU', 422, null, null, null, null, null);
insert sys_permission(id, permission_type, permission_code, permission_name, status, parent_id, route_type, order_num,
                      route_path, component_path, redirect, icon, show_link)
values (15, 'API', 'permission:btn:delete', 'btn删除', 1, 12, 'MENU', 423, null, null, null, null, null);

-- 系统角色权限关系
insert sys_role_permission(role_id, permission_id)
values (1, 1);
insert sys_role_permission(role_id, permission_id)
values (1, 2);
insert sys_role_permission(role_id, permission_id)
values (1, 3);
insert sys_role_permission(role_id, permission_id)
values (1, 4);
insert sys_role_permission(role_id, permission_id)
values (1, 5);
insert sys_role_permission(role_id, permission_id)
values (1, 6);
insert sys_role_permission(role_id, permission_id)
values (1, 7);
insert sys_role_permission(role_id, permission_id)
values (1, 8);
insert sys_role_permission(role_id, permission_id)
values (1, 9);
insert sys_role_permission(role_id, permission_id)
values (1, 10);
insert sys_role_permission(role_id, permission_id)
values (1, 11);
insert sys_role_permission(role_id, permission_id)
values (1, 12);
insert sys_role_permission(role_id, permission_id)
values (1, 13);
insert sys_role_permission(role_id, permission_id)
values (1, 14);
insert sys_role_permission(role_id, permission_id)
values (1, 15);

insert sys_role_permission(role_id, permission_id)
values (2, 10);
insert sys_role_permission(role_id, permission_id)
values (2, 11);
insert sys_role_permission(role_id, permission_id)
values (2, 12);
insert sys_role_permission(role_id, permission_id)
values (2, 13);
insert sys_role_permission(role_id, permission_id)
values (2, 14);
insert sys_role_permission(role_id, permission_id)
values (2, 15);

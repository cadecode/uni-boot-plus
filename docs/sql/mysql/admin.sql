-- 系统用户
CREATE TABLE IF NOT EXISTS sys_user
(
    id          BIGINT UNSIGNED COMMENT 'ID',
    username    VARCHAR(50)     NOT NULL COMMENT '用户名',
    nickname    VARCHAR(50)     NOT NULL COMMENT '昵称',
    group_id    BIGINT UNSIGNED NOT NULL COMMENT '用户组 ID',
    password    VARCHAR(100)    NOT NULL COMMENT '密码',
    status      TINYINT         NOT NULL COMMENT '状态',
    avatar      VARCHAR(200)    NULL COMMENT '头像',
    sex         CHAR(1)         NULL COMMENT '性别',
    mail        VARCHAR(50)     NULL COMMENT '邮箱',
    phone       VARCHAR(50)     NULL COMMENT '电话',
    login_ip    VARCHAR(50)     NULL COMMENT '登录IP',
    login_date  DATETIME        NULL COMMENT '登录时间',

    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    update_user VARCHAR(100)    NULL COMMENT '更新人',
    PRIMARY KEY (id),
    UNIQUE KEY uk_username (username),
    INDEX idx_nickname (nickname)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT '系统用户';

-- 系统用户组
CREATE TABLE IF NOT EXISTS sys_user_group
(
    id            BIGINT UNSIGNED COMMENT 'ID',
    group_name    VARCHAR(50)     NOT NULL COMMENT '用户组名',
    status        TINYINT         NOT NULL COMMENT '状态',
    parent_id     BIGINT UNSIGNED NULL COMMENT '父级 ID',
    order_num     INT             NULL COMMENT '排序',
    ancestors     VARCHAR(200)    NULL COMMENT '祖先 ID',
    leader        VARCHAR(50)     NULL COMMENT '领导',
    mail          VARCHAR(50)     null comment '邮箱',
    phone         VARCHAR(50)     null comment '电话',
    first_tenant  VARCHAR(50)     NULL COMMENT '第一租户标识（作用于增删改查）',
    other_tenants VARCHAR(100)    NULL COMMENT '其他租户标识（作用于删改查）',
    remark        VARCHAR(500)    NULL COMMENT '备注',

    create_time   DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time   DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    update_user   VARCHAR(100)    NULL COMMENT '更新人',
    PRIMARY KEY (id),
    INDEX idx_group_name (group_name),
    INDEX idx_parent_id (parent_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT '系统用户组';

-- 系统角色
CREATE TABLE IF NOT EXISTS sys_role
(
    id        BIGINT UNSIGNED COMMENT 'ID',
    role_code VARCHAR(50)  NOT NULL COMMENT '角色代码',
    role_name   VARCHAR(50)  NOT NULL COMMENT '角色名称',
    status    TINYINT      NOT NULL COMMENT '状态',
    remark    VARCHAR(500) NULL COMMENT '备注',

    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    update_user VARCHAR(100) NULL COMMENT '更新人',
    PRIMARY KEY (id),
    UNIQUE KEY uk_role_code (role_code),
    INDEX idx_role_name (role_name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT '系统角色';

-- 系统权限
CREATE TABLE IF NOT EXISTS sys_permission
(
    id               BIGINT UNSIGNED COMMENT 'ID',
    permission_type  VARCHAR(50)     NOT NULL COMMENT '权限类型',
    permission_code  VARCHAR(100)    NOT NULL COMMENT '权限代码',
    permission_name  VARCHAR(50)     NOT NULL COMMENT '权限名称',
    status           TINYINT         NOT NULL COMMENT '状态',
    parent_id        BIGINT UNSIGNED NULL COMMENT '父级 ID',
    route_type       VARCHAR(50)     NULL COMMENT '路由类型',
    order_num        INT             NULL COMMENT '排序',
    route_path       VARCHAR(100)    NULL COMMENT '路由路径',
    component_path   VARCHAR(100)    NULL COMMENT '组件路径',
    redirect         VARCHAR(100)    NULL COMMENT '路由重定向',
    icon             VARCHAR(50)     NULL COMMENT '菜单图标',
    extra_icon       VARCHAR(50)     NULL COMMENT '菜单名称右侧的额外图标',
    show_link        TINYINT         NULL COMMENT '是否展示菜单',
    show_parent      TINYINT         NULL COMMENT '是否展示父级菜单',
    keep_alive       TINYINT         NULL COMMENT '是否启用缓存',
    frame_src        VARCHAR(200)    NULL COMMENT 'iframe src',
    frame_loading    TINYINT         NULL COMMENT '是否启用 iframe loading',
    enter_transition VARCHAR(50)     NULL COMMENT '进场动画',
    leave_transition VARCHAR(50)     NULL COMMENT '离场动画',
    hidden_tag       TINYINT         NULL COMMENT '是否展示标签',
    dynamic_level    INT             NULL COMMENT '最大标签数',
    active_path      VARCHAR(100)    NULL COMMENT '激活路径',
    remark           VARCHAR(500)    NULL COMMENT '备注',

    create_time      DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time      DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    update_user      VARCHAR(100)    NULL COMMENT '更新人',
    PRIMARY KEY (id),
    UNIQUE KEY uk_permission_type_code (permission_type, permission_code),
    INDEX idx_permission_name (permission_name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT '系统权限';

-- 系统角色用户关系
CREATE TABLE IF NOT EXISTS sys_role_user
(
    role_id BIGINT UNSIGNED NOT NULL COMMENT '角色 ID',
    user_id BIGINT UNSIGNED NOT NULL COMMENT '用户 ID',
    PRIMARY KEY (role_id, user_id),
    INDEX idx_user_id (user_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT '角色用户关系';

-- 系统角色权限关系
CREATE TABLE IF NOT EXISTS sys_role_permission
(
    role_id       BIGINT UNSIGNED NOT NULL COMMENT '角色 ID',
    permission_id BIGINT UNSIGNED NOT NULL COMMENT '权限 ID',
    PRIMARY KEY (role_id, permission_id),
    INDEX idx_permission_id (permission_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT '角色权限关系';

-- 系统用户
CREATE TABLE IF NOT EXISTS sys_user
(
    user_id       VARCHAR(50)  NOT NULL COMMENT '用户 ID',
    password      VARCHAR(100) NOT NULL COMMENT '密码',
    user_name     VARCHAR(50)  NOT NULL COMMENT '用户名称',
    user_group_id VARCHAR(50)  NOT NULL COMMENT '用户组 ID',
    enable_flag   TINYINT      NOT NULL COMMENT '是否启用',
    sex           CHAR(1)      NULL COMMENT '性别',
    mail          VARCHAR(50)  NULL COMMENT '邮箱',
    phone         VARCHAR(50)  NULL COMMENT '电话',
    login_ip      VARCHAR(50)  NULL COMMENT '登录IP',
    login_date    DATETIME     NULL COMMENT '登录时间',

    create_time   DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time   DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    update_user   VARCHAR(100) NULL COMMENT '更新人',
    PRIMARY KEY (user_id),
    INDEX idx_user_name (user_name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT '系统用户';

-- 系统用户组
CREATE TABLE IF NOT EXISTS sys_user_group
(
    user_group_id   VARCHAR(50)   NOT NULL COMMENT '用户组 ID',
    parent_group_id VARCHAR(50)   NOT NULL COMMENT '父级 ID',
    user_group_name VARCHAR(50)   NOT NULL COMMENT '用户组名称',
    order_num       INT           NULL COMMENT '排序',
    ancestors       VARCHAR(2000) NULL COMMENT '祖先部门',
    leader          VARCHAR(500)  NULL COMMENT '领导名',
    mail            VARCHAR(50)   null comment '邮箱',
    phone           VARCHAR(50)   null comment '电话',
    first_tenant    VARCHAR(50)   NULL COMMENT '第一租户标识（作用于增删改查）',
    other_tenants   VARCHAR(1000) NULL COMMENT '其他租户标识（作用于删改查）',

    create_time     DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time     DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    update_user     VARCHAR(100)  NULL COMMENT '更新人',
    PRIMARY KEY (user_group_id),
    INDEX idx_user_group_name (user_group_name),
    INDEX idx_parent_group_id (parent_group_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT '系统用户组';

-- 系统角色
CREATE TABLE IF NOT EXISTS sys_role
(
    role_id     VARCHAR(50)  NOT NULL COMMENT '角色 ID',
    role_name   VARCHAR(50)  NOT NULL COMMENT '角色名称',
    description VARCHAR(100) COMMENT '角色描述',

    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    update_user VARCHAR(100) NULL COMMENT '更新人',
    PRIMARY KEY (role_id),
    INDEX idx_role_name (role_name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT '系统角色';

-- 系统权限
CREATE TABLE IF NOT EXISTS sys_permission
(
    permission_id   VARCHAR(50)  NOT NULL COMMENT '权限 ID',
    permission_name VARCHAR(50)  NOT NULL COMMENT '权限名称',
    description     VARCHAR(100) COMMENT '权限描述',

    create_time     DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time     DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    update_user     VARCHAR(100) NULL COMMENT '更新人',
    PRIMARY KEY (permission_id),
    INDEX idx_permission_name (permission_name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT '系统权限';

-- 系统角色用户关系
CREATE TABLE IF NOT EXISTS sys_role_user
(
    role_id VARCHAR(50) NOT NULL COMMENT '角色 ID',
    user_id VARCHAR(50) NOT NULL COMMENT '用户 ID',
    PRIMARY KEY (role_id, user_id),
    INDEX idx_user_id (user_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT '角色用户关系';

-- 系统角色权限关系
CREATE TABLE IF NOT EXISTS sys_role_permission
(
    role_id       VARCHAR(50) NOT NULL COMMENT '角色 ID',
    permission_id VARCHAR(50) NOT NULL COMMENT '权限 ID',
    PRIMARY KEY (role_id, permission_id),
    INDEX idx_permission_id (permission_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT '角色权限关系';

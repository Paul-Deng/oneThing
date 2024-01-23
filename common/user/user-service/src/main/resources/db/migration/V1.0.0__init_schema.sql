-- 用户表
CREATE TABLE public.tbl_users(
    id bigint NOT NULL PRIMARY KEY,
    openId VARCHAR(255) NOT NULL,         -- 开放id
    username VARCHAR(50) NOT NULL,         -- 用户名
    password VARCHAR(255) NOT NULL,        -- 密码
    nickname VARCHAR(255) NOT NULL,        -- 昵称
    gender SMALLINT DEFAULT 0,             -- 性别：0-位置；1-男; 2-女
    email VARCHAR(100) NOT NULL,           -- 邮箱
    phone VARCHAR(20) NOT NULL,            -- 手机号
    avatar VARCHAR(255),                   -- 头像
    deleted SMALLINT DEFAULT 0,            -- 逻辑删除标志：0-未删除；1-已删除
    status SMALLINT DEFAULT 0,             -- 状态：0-启用；1-禁用
    createdTime BIGINT NOT NULL,       -- 创建时间
    updatedTime BIGINT NOT NULL,       -- 更新时间
    updatedBy BIGINT NOT NULL, -- 被谁更新
    createdBy BIGINT NOT NULL -- 被谁创建
);

-- 角色表
CREATE TABLE public.tbl_roles
(
    id BIGINT NOT NULL PRIMARY KEY,        -- 角色ID
    name VARCHAR(50) NOT NULL,             -- 角色名称
    description VARCHAR(255),              -- 描述
    deleted SMALLINT DEFAULT 0,            -- 逻辑删除标志：0-未删除；1-已删除
    status SMALLINT DEFAULT 0,             -- 状态：0-启用；1-禁用
    createdTime BIGINT NOT NULL,       -- 创建时间
    updatedTime BIGINT NOT NULL,       -- 更新时间
    updatedBy BIGINT NOT NULL, -- 被谁更新
    createdBy BIGINT NOT NULL -- 被谁创建
);

-- 用户角色关系表
CREATE TABLE public.tbl_user_roles
(
    userId INTEGER REFERENCES tbl_users (id),
    roleId INTEGER REFERENCES tbl_roles (id),
    deleted SMALLINT DEFAULT 0,            -- 逻辑删除标志：0-未删除；1-已删除
    status SMALLINT DEFAULT 0,             -- 状态：0-禁用；1-启用
    createdTime BIGINT NOT NULL,       -- 创建时间
    updatedTime BIGINT NOT NULL,       -- 更新时间
    updatedBy BIGINT NOT NULL, -- 被谁更新
    createdBy BIGINT NOT NULL, -- 被谁创建
    PRIMARY KEY (userId, roleId)
);

-- 权限表
CREATE TABLE public.tbl_permissions (
    id BIGINT NOT NULL PRIMARY KEY, -- 主键ID
    name VARCHAR(255) NOT NULL, -- 权限名
    parentId BIGINT NOT NULL, -- 父类菜单的id
    css VARCHAR(255), -- css
    path VARCHAR(255), -- path
    method SMALLINT NOT NULL, -- 请求方法，1：GET，2：POST，3：PUT，4：DELETE
    url VARCHAR(255) NOT NULL, -- 请求地址
    sort SMALLINT, -- 顺序
    type SMALLINT, -- 类型
    hidden BOOLEAN, -- 类型
    description VARCHAR(255), -- 权限描述
    deleted SMALLINT NOT NULL, -- 是否删除，0：未删除，1：已删除
    status SMALLINT NOT NULL, -- 权限状态，0：正常，1：禁用，2：过期
    createdTime BIGINT NOT NULL, -- 创建时间
    updatedTime BIGINT NOT NULL, -- 更新时间
    updatedBy BIGINT NOT NULL, -- 被谁更新
    createdBy BIGINT NOT NULL, -- 被谁创建
    CONSTRAINT permission_unique_method_url_button UNIQUE(method, url) -- 唯一索引
);

-- 角色权限关系表
CREATE TABLE public.tbl_role_permissions
(
    roleId INTEGER REFERENCES tbl_roles (id),
    permissionId INTEGER REFERENCES tbl_permissions (id),
    deleted SMALLINT DEFAULT 0,            -- 逻辑删除标志：0-未删除；1-已删除
    status SMALLINT DEFAULT 0,             -- 状态：0-禁用；1-启用
    createdTime BIGINT NOT NULL,       -- 创建时间
    updatedTime BIGINT NOT NULL,       -- 更新时间
    updatedBy BIGINT NOT NULL, -- 被谁更新
    createdBy BIGINT NOT NULL, -- 被谁创建
    PRIMARY KEY (roleId, permissionId)
);

-- 第三方登录表
CREATE TABLE public.tbl_user_third_party
(
    id bigint NOT NULL PRIMARY KEY,
    userId INTEGER REFERENCES tbl_users(id),
    providerId VARCHAR(255) NOT NULL,
    openId VARCHAR(255) NOT NULL,
    status SMALLINT NOT NULL,
    createdTime BIGINT NOT NULL, -- 创建时间
    updatedTime BIGINT NOT NULL, -- 更新时间
    updatedBy BIGINT NOT NULL, -- 被谁更新
    createdBy BIGINT NOT NULL -- 被谁创建
);
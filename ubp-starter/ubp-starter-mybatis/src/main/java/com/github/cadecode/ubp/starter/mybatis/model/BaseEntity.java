package com.github.cadecode.ubp.starter.mybatis.model;

import java.time.LocalDateTime;

/**
 * 基础实体接口
 * <p>方便处理 create_time,update_time,update_user 字段
 *
 * @author Cade Li
 * @since 2024/4/28
 */
public interface BaseEntity {

    LocalDateTime getCreateTime();

    void setCreateTime(LocalDateTime createTime);

    LocalDateTime getUpdateTime();

    void setUpdateTime(LocalDateTime updateTime);

    String getUpdateUser();

    void setUpdateUser(String updateUser);

}

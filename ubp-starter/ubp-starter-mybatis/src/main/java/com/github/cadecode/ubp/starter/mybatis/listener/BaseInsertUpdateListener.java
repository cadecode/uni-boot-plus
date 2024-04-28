package com.github.cadecode.ubp.starter.mybatis.listener;

import com.github.cadecode.ubp.starter.mybatis.model.BaseFieldOperable;
import com.mybatisflex.annotation.InsertListener;
import com.mybatisflex.annotation.UpdateListener;

import java.time.LocalDateTime;

/**
 * 基础监听器
 * <p>
 * 添加、更新时，填充 create_time,update_time,update_user
 *
 * @author Cade Li
 * @since 2024/4/28
 */
public class BaseInsertUpdateListener implements InsertListener, UpdateListener {

    @Override
    public void onInsert(Object entity) {
        if (entity instanceof BaseFieldOperable baseFieldOperable) {
            baseFieldOperable.setCreateTime(LocalDateTime.now());
        }
    }

    @Override
    public void onUpdate(Object entity) {
        if (entity instanceof BaseFieldOperable baseFieldOperable) {
            baseFieldOperable.setUpdateTime(LocalDateTime.now());
        }
    }
}

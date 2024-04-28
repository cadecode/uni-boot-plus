package com.github.cadecode.ubp.framework.starter.mybatis;

import com.github.cadecode.ubp.starter.mybatis.listener.BaseInsertUpdateListener;
import org.springframework.stereotype.Component;

/**
 * 监听器
 *
 * @author Cade Li
 * @since 2024/4/28
 */
@Component
public class InsertUpdateListener extends BaseInsertUpdateListener {

    @Override
    public void onInsert(Object entity) {
        super.onInsert(entity);
        // TODO updateUser 字段填充
    }

    @Override
    public void onUpdate(Object entity) {
        super.onUpdate(entity);
        // TODO updateUser 字段填充
    }
}

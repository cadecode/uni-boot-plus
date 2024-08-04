package com.github.cadecode.ubp.framework.starter.mybatis;

import cn.dev33.satoken.stp.StpUtil;
import com.github.cadecode.ubp.starter.mybatis.listener.BaseFieldOperationListener;
import com.github.cadecode.ubp.starter.mybatis.model.BaseFieldOperable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 字段操作监听器
 *
 * @author Cade Li
 * @since 2024/4/28
 */
@Component
public class FieldOperationListener extends BaseFieldOperationListener {

    @Override
    public void onInsert(Object entity) {
        if (entity instanceof BaseFieldOperable baseFieldOperable) {
            baseFieldOperable.setCreateTime(LocalDateTime.now());
            setLoginId(baseFieldOperable);
        }
    }

    @Override
    public void onUpdate(Object entity) {
        if (entity instanceof BaseFieldOperable baseFieldOperable) {
            baseFieldOperable.setUpdateTime(LocalDateTime.now());
            setLoginId(baseFieldOperable);
        }
    }

    /**
     * 获取登录的 ID
     */
    private void setLoginId(BaseFieldOperable baseFieldOperable) {
        // 从 SaToken 获取
        Object loginId = StpUtil.getLoginIdDefaultNull();
        if (Objects.nonNull(loginId)) {
            baseFieldOperable.setUpdateUser(String.valueOf(loginId));
        }
    }
}

package com.github.cadecode.ubp.admin.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.mybatisflex.annotation.EnumValue;
import lombok.Getter;

/**
 * 权限类型枚举
 *
 * @author Cade Li
 * @since 2024/5/29
 */
@Getter
public enum PermissionTypeEnum {

    /**
     * 路由权限
     */
    ROUTE("ROUTE"),

    /**
     * 接口权限
     */
    API("API"),
    ;

    @EnumValue
    @JsonValue
    private final String type;

    PermissionTypeEnum(String type) {
        this.type = type;
    }
}

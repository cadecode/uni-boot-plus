package com.github.cadecode.ubp.admin.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.mybatisflex.annotation.EnumValue;
import lombok.Getter;

/**
 * 路由类型枚举
 *
 * @author Cade Li
 * @since 2024/5/29
 */
@Getter
public enum RouteTypeEnum {

    /**
     * 菜单
     */
    MENU("MENU"),

    /**
     * iframe
     */
    IFRAME("IFRAME"),

    /**
     * 外链
     */
    EXTERNAL("EXTERNAL");

    @EnumValue
    @JsonValue
    private final String type;

    RouteTypeEnum(String type) {
        this.type = type;
    }
}

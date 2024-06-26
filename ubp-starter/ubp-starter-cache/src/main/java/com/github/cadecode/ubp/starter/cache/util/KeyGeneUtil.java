package com.github.cadecode.ubp.starter.cache.util;

/**
 * Redis key 命名生成器
 *
 * @author Cade Li
 * @since 2022/5/29
 */
public class KeyGeneUtil {

    public static final String SEPARATOR = ":";

    /**
     * 生成 ： 分割的 redis key
     *
     * @param prefix 前缀
     * @param extra  其他字符串
     * @return redis key
     */
    public static String key(String prefix, Object... extra) {
        StringBuilder prefixBuilder = new StringBuilder(prefix);
        for (Object obj : extra) {
            prefixBuilder.append(SEPARATOR).append(obj);
        }
        return prefixBuilder.toString();
    }

    public static String lockKey(Object... extra) {
        return key("lock", extra);
    }

}

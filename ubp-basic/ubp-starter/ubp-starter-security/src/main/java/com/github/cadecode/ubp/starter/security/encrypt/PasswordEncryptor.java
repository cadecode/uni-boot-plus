package com.github.cadecode.ubp.starter.security.encrypt;

import java.util.Objects;

/**
 * 密码加密器
 *
 * @author Cade Li
 * @since 2024/5/17
 */
public interface PasswordEncryptor {

    /**
     * 获取加密后的密码
     *
     * @param password 密码明文
     * @param secret 密钥
     * @return 密码密文
     */
    String encrypt(String password, String secret);

    /**
     * 校验密码是否一致
     *
     * @param truePassword 真实密码密文
     * @param secret 密钥
     * @param inputPassword 输入密码明文
     * @return 是否一致
     */
    default boolean validate(String truePassword, String secret, String inputPassword) {
        return Objects.equals(truePassword, encrypt(inputPassword, secret));
    }

}

package com.github.cadecode.ubp.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 后台服务启动类
 *
 * @author Cade Li
 * @since 2024/4/22
 */
@MapperScan({"com.github.cadecode.**.mapper"})
@SpringBootApplication
public class UniBootAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniBootAdminApplication.class, args);
    }
}

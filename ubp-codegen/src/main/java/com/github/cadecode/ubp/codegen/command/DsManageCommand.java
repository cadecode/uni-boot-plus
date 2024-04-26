package com.github.cadecode.ubp.codegen.command;

import cn.hutool.core.util.StrUtil;
import com.mybatisflex.core.datasource.DataSourceKey;
import com.mybatisflex.core.datasource.FlexDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.sql.DataSource;

/**
 * 数据源管理命令
 *
 * @author Cade Li
 * @since 2024/4/25
 */
@RequiredArgsConstructor
@ShellComponent("ds")
@ShellCommandGroup("datasource manage")
public class DsManageCommand implements InitializingBean {

    private final DataSource dataSource;

    /**
     * mybatis-flex 多数据源封装
     */
    private static FlexDataSource FLEX_DS;

    public static FlexDataSource getDs() {
        return FLEX_DS;
    }

    @Override
    public void afterPropertiesSet() {
        if (dataSource instanceof FlexDataSource flexDataSource) {
            FLEX_DS = flexDataSource;
        }
    }

    @ShellMethod(value = "list all datasource", key = "ds ls")
    public String listDs() {
        return FLEX_DS.getDataSourceMap().keySet().toString();
    }

    @ShellMethod(value = "get current datasource", key = "ds curr")
    public String currentDs() {
        return StrUtil.nullToDefault(DataSourceKey.get(), FLEX_DS.getDefaultDataSourceKey());
    }

    @ShellMethod(value = "change datasource", key = "ds ch")
    public String changeDs(@ShellOption(help = "数据源 key") String ds) {
        if (!FLEX_DS.getDataSourceMap().containsKey(ds)) {
            return "No such datasource key!";
        }
        DataSourceKey.use(ds);
        return null;
    }
}

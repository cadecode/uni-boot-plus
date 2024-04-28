package com.github.cadecode.ubp.codegen.command;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.mybatisflex.core.datasource.DataSourceKey;
import com.mybatisflex.core.datasource.FlexDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;

/**
 * 数据库文档生成命令
 *
 * @author Cade Li
 * @since 2024/4/27
 */
@ShellComponent("dbdoc")
@ShellCommandGroup("database doc generate")
public class DbDocCommand implements EnvironmentAware {

    private Environment environment;

    private static final String BASE_CONFIG_VERSION_KEY = "uni-boot.base-config.version";


    @ShellMethod(value = "db doc generate", key = "dbdoc")
    public String dbdoc(@ShellOption(help = "file type", value = {"file-type", "ft"}) String fileType,
                        @ShellOption(help = "file name", value = {"file-name", "fn"}) String fileName,
                        @ShellOption(help = "version",
                                value = {"version", "v"},
                                defaultValue = "") String version,
                        @ShellOption(help = "description",
                                value = {"description", "desc"},
                                defaultValue = "数据库设计文档生成") String description,
                        @ShellOption(help = "table prefix",
                                value = {"table-prefix", "tp"},
                                defaultValue = "") String tablePrefix,
                        @ShellOption(help = "table suffix",
                                value = {"table-suffix", "ts"},
                                defaultValue = "") String tableSuffix,
                        @ShellOption(help = "table names",
                                value = {"table-names", "t"},
                                defaultValue = "",
                                arity = 1000) String[] tableNames) {
        EngineConfig engineConfig = getEngineConfig(fileType, fileName);

        // 指定生成逻辑
        // 当存在指定表、指定表前缀、指定表后缀时，将生成指定表，其余表不生成、并跳过忽略表配置
        ProcessConfig processConfig = ProcessConfig.builder()
                // 根据名称指定表生成
                .designatedTableName(Arrays.asList(tableNames))
                // 根据表前缀生成
                .designatedTablePrefix(StrUtil.isEmpty(tablePrefix) ? Collections.emptyList() : Collections.singletonList(tablePrefix))
                // 根据表后缀生成
                .designatedTableSuffix(StrUtil.isEmpty(tableSuffix) ? Collections.emptyList() : Collections.singletonList(tableSuffix))
                // 忽略表名
                .ignoreTableName(Collections.emptyList())
                // 忽略表前缀
                .ignoreTablePrefix(Collections.emptyList())
                // 忽略表后缀
                .ignoreTableSuffix(Collections.emptyList())
                .build();
        // 配置
        Configuration config = getConfiguration(version, description, engineConfig, processConfig);
        // 执行生成
        new DocumentationExecute(config).execute();
        return null;
    }

    @ShellMethod(value = "db doc generate by ignore", key = "dbdoc ignore")
    public String dbdocIgnore(@ShellOption(help = "file type", value = {"file-type", "ft"}) String fileType,
                              @ShellOption(help = "file name", value = {"file-name", "fn"}) String fileName,
                              @ShellOption(help = "version",
                                      value = {"version", "v"},
                                      defaultValue = "") String version,
                              @ShellOption(help = "description",
                                      value = {"description", "desc"},
                                      defaultValue = "数据库设计文档生成") String description,
                              @ShellOption(help = "ignore table prefix",
                                      value = {"ignore-table-prefix", "itp"},
                                      defaultValue = "") String ignoreTablePrefix,
                              @ShellOption(help = "ignore table suffix",
                                      value = {"ignore-table-suffix", "its"},
                                      defaultValue = "") String ignoreTableSuffix,
                              @ShellOption(help = "ignore table names",
                                      value = {"ignore-table-names", "it"},
                                      defaultValue = "",
                                      arity = 1000) String[] ignoreTableNames) {
        EngineConfig engineConfig = getEngineConfig(fileType, fileName);

        // 指定生成逻辑
        // 当存在指定表、指定表前缀、指定表后缀时，将生成指定表，其余表不生成、并跳过忽略表配置
        ProcessConfig processConfig = ProcessConfig.builder()
                // 根据名称指定表生成
                .designatedTableName(Collections.emptyList())
                // 根据表前缀生成
                .designatedTablePrefix(Collections.emptyList())
                // 根据表后缀生成
                .designatedTableSuffix(Collections.emptyList())
                // 忽略表名
                .ignoreTableName(Arrays.asList(ignoreTableNames))
                // 忽略表前缀
                .ignoreTablePrefix(StrUtil.isEmpty(ignoreTablePrefix) ? Collections.emptyList() : Collections.singletonList(ignoreTablePrefix))
                // 忽略表后缀
                .ignoreTableSuffix(StrUtil.isEmpty(ignoreTableSuffix) ? Collections.emptyList() : Collections.singletonList(ignoreTableSuffix))
                .build();
        // 配置
        Configuration config = getConfiguration(version, description, engineConfig, processConfig);
        // 执行生成
        new DocumentationExecute(config).execute();
        return null;
    }

    private Configuration getConfiguration(String version, String description,
                                           EngineConfig engineConfig, ProcessConfig processConfig) {
        FlexDataSource flexDataSource = DsManageCommand.getDs();
        String currKey = StrUtil.nullToDefault(DataSourceKey.get(), flexDataSource.getDefaultDataSourceKey());
        DataSource dataSource = flexDataSource.getDataSourceMap().get(currKey);
        HikariDataSource hikariDataSource;
        // 兼容 HikariDataSource 和其他连接池
        if (dataSource instanceof HikariDataSource hDataSource) {
            hikariDataSource = hDataSource;
        } else {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl(flexDataSource.getUrl());
            hikariConfig.setUsername((String) ReflectUtil.getFieldValue(dataSource, "username"));
            hikariConfig.setPassword((String) ReflectUtil.getFieldValue(dataSource, "password"));
            hikariDataSource = new HikariDataSource(hikariConfig);
        }
        // 从配置中读取版本号
        String versionProperty = StrUtil.nullToDefault(environment.getProperty(BASE_CONFIG_VERSION_KEY), "");
        return Configuration.builder()
                .version(StrUtil.emptyToDefault(version, versionProperty))
                .description(description)
                .dataSource(hikariDataSource)
                .engineConfig(engineConfig)
                .produceConfig(processConfig)
                .build();
    }

    private EngineConfig getEngineConfig(String fileType, String fileName) {
        return EngineConfig.builder()
                .fileOutputDir(System.getProperty("user.dir") + "/docs/db/")
                // 关闭，打开文件目录存在兼容性问题
                .openOutputDir(false)
                .fileType(EngineFileType.valueOf(fileType))
                .produceType(EngineTemplateType.freemarker)
                // 自定义文件名称
                .fileName(fileName)
                .build();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}

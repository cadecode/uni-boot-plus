package com.github.cadecode.ubp.codegen.command;

import cn.hutool.core.date.DateUtil;
import com.github.cadecode.ubp.codegen.generator.EntityConvertGenerator;
import com.github.cadecode.ubp.starter.mybatis.model.BaseFieldOperable;
import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.EntityConfig.SwaggerVersion;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.codegen.config.PackageConfig;
import com.mybatisflex.codegen.config.StrategyConfig;
import com.mybatisflex.codegen.generator.GeneratorFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 代码生成器命令
 *
 * @author Cade Li
 * @since 2024/4/25
 */
@RequiredArgsConstructor
@ShellComponent("codegen")
@ShellCommandGroup("code generate")
public class CodegenCommand {

    private final GlobalConfig globalConfig = new GlobalConfig();

    {
        // javadoc
        globalConfig.getJavadocConfig()
                // 使用 -Duser.name="Cade Li" 来自定义，否则取系统用户名
                .setAuthor(System.getProperty("user.name"))
                .setSince(DateUtil.format(LocalDateTime.now(), "yyyy/M/dd"));

        // 注意：生成代码不覆盖现有文件！！
        // entity
        globalConfig.enableEntity()
                // 开启 lombok
                .setWithLombok(true)
                // 设置 jdk 版本
                .setJdkVersion(17)
                // 开始 swagger doc 版本
                .setWithSwagger(true)
                .setSwaggerVersion(SwaggerVersion.DOC)
                // interface
                .setImplInterfaces(BaseFieldOperable.class, Serializable.class)
                .setOverwriteEnable(false);
        // mapper
        globalConfig.enableMapper()
                // 开启 mapper 注解
                .setMapperAnnotation(true)
                .setOverwriteEnable(false);
        globalConfig.enableMapperXml()
                .setOverwriteEnable(false);
        // service
        globalConfig.enableService()
                .setOverwriteEnable(false);
        globalConfig.enableServiceImpl()
                .setOverwriteEnable(false);
        // controller
        globalConfig.enableController()
                .setOverwriteEnable(false);

        // 模板配置
        globalConfig.getTemplateConfig()
                .setController("/template/enjoy/controller.tpl")
                .setEntity("/template/enjoy/entity.tpl")
                .setMapper("/template/enjoy/mapper.tpl")
                .setMapperXml("/template/enjoy/mapperXml.tpl")
                .setService("/template/enjoy/service.tpl")
                .setServiceImpl("/template/enjoy/serviceImpl.tpl");

        // 自定义生成器
        // entityConvert
        GeneratorFactory.registerGenerator("entityConvert", new EntityConvertGenerator());
    }

    @ShellMethod(value = "code generate", key = "codegen")
    public String codegen(@ShellOption(help = "module dir", value = {"module-dir", "m"}) String moduleDir,
                          @ShellOption(help = "base package",
                                  value = {"base-package", "p"},
                                  defaultValue = "com.github.cadecode.ubp") String basePackage,
                          @ShellOption(help = "table prefix",
                                  value = {"table-prefix", "tp"},
                                  defaultValue = "") String tablePrefix,
                          @ShellOption(help = "table names",
                                  value = {"table-names", "t"},
                                  arity = 1000) String[] tableNames) {
        // 策略配置
        StrategyConfig strategyConfig = globalConfig.getStrategyConfig();
        strategyConfig.setTablePrefix(tablePrefix);
        strategyConfig.setGenerateTable(tableNames);

        // 包配置
        PackageConfig packageConfig = globalConfig.getPackageConfig();
        String completedSourceDir = System.getProperty("user.dir") + "/" + moduleDir;
        packageConfig.setSourceDir(completedSourceDir + "/src/main/java");
        packageConfig.setBasePackage(basePackage);
        packageConfig.setMapperXmlPath(completedSourceDir + "/src/main/resources/mapper");
        // 按本项目风格定制
        packageConfig.setEntityPackage(packageConfig.getBasePackage() + ".bean.entity");
        packageConfig.setServiceImplPackage(packageConfig.getBasePackage() + ".serviceimpl");

        Generator generator = new Generator(DsManageCommand.getDs(), globalConfig);
        generator.generate();
        return null;
    }
}

package com.easyboot.config.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MpGenerator {

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        // 设置模板引擎
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 全局设置
        GlobalConfig gc = new GlobalConfig();
        gc.setAuthor("武佳伟")
            .setBaseResultMap(true)
            .setEnableCache(false)
            .setBaseColumnList(true)
            // 自定义文件名,%s 会自动填充表实体属性
            .setMapperName("%sDao")
            .setXmlName("%sMapper")
            .setServiceImplName("%sServiceImpl")
            // 直接输出到项目中
            .setOutputDir("src\\main\\java")
            .setFileOverride(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.jdbc.Driver")
                .setUsername("root")
                .setPassword("root")
                .setUrl("jdbc:mysql://localhost:3307/joauth2?characterEncoding=utf8");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig sc = new StrategyConfig();
        sc.setSuperControllerClass("com.joauth2.controller.BaseController")
                // 需要生成的表名
                .setInclude(new String[]{"sys_test"})
                // 类名使用驼峰
                .setNaming(NamingStrategy.underline_to_camel)
                // 表前缀
                .setTablePrefix(new String[]{"sys_"});
        mpg.setStrategy(sc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.joauth2")
                .setController("controller")
                .setEntity("bean")
                .setMapper("dao")
                .setXml("mapper")
                .setService("service")
                .setServiceImpl("service.impl");
        mpg.setPackageInfo(pc);


        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                map.put("basePath", "${ctx}");
                this.setMap(map);
            }
        };

        // 自定义文件生成
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        focList.add(new FileOutConfig("/generator/list.html.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return "src/main/resources/templates/" + tableInfo.getEntityPath()+ ".html";
            }
        });
        focList.add(new FileOutConfig("/generator/test.txt.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return "src/main/resources/templates/test.txt";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 自定义模板配置
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);
        tc.setController("generator/controller.java");
        tc.setEntity("generator/entity.java");
        mpg.setTemplate(tc);



        // 执行生成
        mpg.execute();
    }

}
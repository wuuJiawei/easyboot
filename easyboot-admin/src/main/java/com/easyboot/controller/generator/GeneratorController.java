package com.easyboot.controller.generator;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.easyboot.bean.Menu;
import com.easyboot.bean.enums.YesOrNo;
import com.easyboot.common.Constants;
import com.easyboot.common.ResultBean;
import com.easyboot.common.layui.LayTable;
import com.easyboot.common.layui.LayTableArg;
import com.easyboot.config.log.annotation.Log;
import com.easyboot.support.BaseController;
import com.easyboot.service.IGeneratorService;
import com.easyboot.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GeneratorController
 *
 * @name: GeneratorController
 * @author: 12093
 * @date: 2019/1/17
 * @time: 9:36
 */
@Controller
@RequestMapping("generator")
public class GeneratorController extends BaseController{

    @Autowired
    private IGeneratorService service;
    @Autowired
    private IMenuService menuService;

    @GetMapping("/")
    public String page(){
        return "generator/page";
    }

    @PostMapping("query/table")
    @ResponseBody
    public LayTable<Map<String, Object>> queryTables(LayTableArg arg) {
        Page<Map<String, Object>> page = new Page<>(arg.getPage(), arg.getLimit());
        IPage<Map<String, Object>> list = service.queryList(page, arg.getParam());
        return new LayTable<>(list.getRecords(), (int)list.getTotal());
    }

    @PostMapping("query/column")
    @ResponseBody
    public LayTable<Map<String, Object>> queryColumn(LayTableArg arg) {
        Page<Map<String, Object>> page = new Page<>(arg.getPage(), arg.getLimit());
        IPage<Map<String, Object>> list = service.queryColumns(page, arg.getParam());
        return new LayTable<>(list.getRecords(), (int)list.getTotal());
    }

    @Log("生成代码")
    @PostMapping("create")
    @ResponseBody
    public ResultBean create(String tableName, String tablePrefix, Boolean override, String projectName, String files){
        String[] tableNameArr = StrUtil.split(tableName, ",");
        String[] tablePrefixArr = StrUtil.split(tablePrefix, ",");
        generate(override, tableNameArr, tablePrefixArr, projectName, StrUtil.split(files, ","));

        return ResultBean.ok();
    }

    @GetMapping("createTable")
    public String createTablePage(){
        return "generator/createTable";
    }

    @PostMapping("createTable")
    @ResponseBody
    @Log("新建表")
    public ResultBean createTable(String tableName, String tableComment, String fields){
        return ResultBean.ok();
    }


    /**
     * 代码生成
     * @param override 是否覆盖原文件
     * @param tableName 表名
     * @param tablePrefix 表名前缀
     * @param projectName 项目名
     */
    private void generate(boolean override, String[] tableName, String[] tablePrefix, String projectName, String[] files){
        AutoGenerator mpg = new AutoGenerator();
        // 设置模板引擎
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 全局设置
        GlobalConfig gc = new GlobalConfig();
        gc.setAuthor("wujiawei0926@yeah.net")
                .setBaseResultMap(true)
                .setEnableCache(false)
                .setBaseColumnList(true)
                // 自定义文件名,%s 会自动填充表实体属性
                .setMapperName("%sDao")
                .setXmlName("%sMapper")
                .setServiceImplName("%sServiceImpl")
                // 直接输出到项目中
                .setOutputDir("src\\main\\java")
                .setFileOverride(override);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL)
                .setDriverName(Constants.application.getProperty("spring.datasource.driver-class-name"))
                .setUsername(Constants.application.getProperty("spring.datasource.username"))
                .setPassword(Constants.application.getProperty("spring.datasource.password"))
                .setUrl(Constants.application.getProperty("spring.datasource.url"));
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig sc = new StrategyConfig();
        sc.setSuperControllerClass("com.joauth2.common.support.BaseCrudController")
                // 需要生成的表名
                .setInclude(tableName)
                // 类名使用驼峰
                .setNaming(NamingStrategy.underline_to_camel)
                // 表前缀
                .setTablePrefix(tablePrefix);
        mpg.setStrategy(sc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com." + projectName)
                .setController("controller")
                .setEntity("bean")
                .setMapper("dao")
                .setXml("mapper")
                .setService("service")
                .setServiceImpl("service.impl");
        mpg.setPackageInfo(pc);

        // 注入自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("basePath", "${ctx}");
                this.setMap(map);
            }
        };

        // 自定义文件生成
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        if (ArrayUtil.contains(files, "list")){
            focList.add(new FileOutConfig("/generator/list.html.ftl") {
                @Override
                public String outputFile(TableInfo table) {
                    createMenu(table);
                    return "src/main/resources/templates/" + table.getEntityPath() + "/list.html";
                }
            });
        }
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 自定义模板配置
        mpg.setTemplate(configTemplateFiles(files));

        // 执行生成
        mpg.execute();
    }

    /**
     *
     * @param table
     * @return
     */
    private boolean createMenu(TableInfo table){
        // 删除menu
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.eq("menu_name", table.getComment())
                .eq("url", "/" + table.getEntityPath() + "/")
                .eq("type", 1);
        menuService.remove(wrapper);

        // 插入menu
        Menu menu = new Menu();
        menu.setDisabled(YesOrNo.NO);
        menu.setIcon("layui-icon layui-icon-app");
        menu.setMenuName(table.getComment());
        menu.setOrders(1);
        menu.setPid(0);
        menu.setType(1);
        menu.setUrl("/" + table.getEntityPath() + "/");
        return menuService.save(menu);
    }

    /**
     * 配置是否生成模板文件
     * @param files
     * @return
     */
    private TemplateConfig configTemplateFiles(String[] files){
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);
        tc.setEntity(null);
        tc.setController(null);
        tc.setMapper(null);
        tc.setService(null);
        tc.setServiceImpl(null);
        for (String file : files) {
            if ("controller".equals(file)) {
                tc.setController("generator/controller.java");
            }
            if ("xml".equals(file)) {
                tc.setXml("generator/mapper.xml");
            }
            if ("service".equals(file)) {
                tc.setService("generator/service.java");
                tc.setServiceImpl("generator/serviceImpl.java");
            }
            if ("dao".equals(file)) {
                tc.setMapper("generator/mapper.java");
            }
            if ("entity".equals(file)) {
                tc.setEntity("generator/entity.java");
            }
        }
        return tc;
    }

}
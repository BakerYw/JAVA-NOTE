package com.example.managersystem.config;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

public class CodeGenerator {
    public static void main(String[] args) {
        // 1.创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2.全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir"); //获取当前项目的根目录
        gc.setOutputDir(projectPath + "/src/main/java");  //生成路径
        gc.setAuthor("cheese");//设置作者
        gc.setOpen(true); //生成后是否打开资源管理器
        gc.setFileOverride(true); //重新生成时文件是否覆盖
        gc.setServiceName("%sService"); //去掉Service接口的首字母I
        gc.setIdType(IdType.AUTO);//自增
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(false);//开启Swagger2模式
        mpg.setGlobalConfig(gc);

        // 3.数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/manager_system?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");  //设置连接数据库的用户名
        dsc.setPassword("root");   //设置连接数据库的密码
        mpg.setDataSource(dsc);
        // 4.包配置
        PackageConfig pc = new PackageConfig();
       // pc.setModuleName(scanner("模块名"));
        pc.setParent("com.example.managersystem");
        pc.setEntity("entity");
        pc.setController("controller");
        pc.setService("service");
        pc.setMapper("mapper");
        pc.setServiceImpl("service.impl");
        mpg.setPackageInfo(pc);

        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        //5.策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);// 数据库表映射到实体的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true)
        strategy.setLogicDeleteFieldName("is_deleted");//逻辑删除字段名
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);//去掉布尔值的is_前缀
        strategy.setCapitalMode(true); //全局大写命名
        strategy.setInclude("treemenu");  //设置需要生成的表名
//        strategy.setExclude("admin","icon","log","role","role_treemenu","treemenu");//排查不要的表
        mpg.setStrategy(strategy);


        //6.自动填充
        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        strategy.setTableFillList(tableFills);
        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符
        mpg.setStrategy(strategy);


        mpg.execute();
    }
}

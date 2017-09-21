package org.mybatis.generator.plugin;

import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Mybatis 生成工具类
 */
public class MybatisGeneratorUtil {

    public static void generate(String fileName) throws Throwable {
        if (null == fileName) {
            return;
        }

        List<String> warnings = new ArrayList<String>();
        // 项目地址
        String filePath = new File("./src/main/resources/config/").getAbsolutePath() + "/" + fileName;
        System.out.println("filePath:" + filePath);
        File configFile = new File(filePath);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        MybatisShellCallback callback = new MybatisShellCallback();
        MybatisGenerator myBatisGenerator = new MybatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);

        System.out.println("生成结束");
    }

    public static void main(String[] args) {
        Object obj = null;
        System.out.println("".equals(obj));
    }
}

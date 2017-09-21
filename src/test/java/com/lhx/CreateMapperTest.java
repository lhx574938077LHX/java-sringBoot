package com.lhx;


import org.mybatis.generator.plugin.MybatisGeneratorUtil;

/**
 * @date 2017-06-16
 */
public class CreateMapperTest {

	public static void main(String[] args) throws Throwable {
        System.setProperty("user.dir", "D:\\workspace\\java-springBoot");
		MybatisGeneratorUtil.generate(
                "mysqlGeneratorConfig.xml"
		);
	}
}

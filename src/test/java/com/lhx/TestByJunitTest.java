package com.lhx;

import com.lhx.common.exception.BusinessException;
import com.lhx.dao.entity.AccountInfo;
import com.lhx.dao.entity.City;
import com.lhx.service.LoginServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author lihongxiang
 * @date
 */
@RunWith(SpringRunner.class)// SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = Application.class) // 指定我们SpringBoot工程的Application启动类
public class TestByJunitTest {

    @Autowired
    private LoginServiceImpl loginService;

    @Test
    public void testLogin() {
        String name = "%北%";
        List<City> cites = loginService.getCityList(name);
        for (City city : cites) {
            System.out.println("city:"+city.getCityName());
        }
    }

}

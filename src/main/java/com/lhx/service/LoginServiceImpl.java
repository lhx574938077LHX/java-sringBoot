package com.lhx.service;

import com.lhx.common.constant.ResultConstant;
import com.lhx.common.exception.BusinessException;
import com.lhx.common.result.AUDResult;
import com.lhx.common.tool.MD5Util;
import com.lhx.common.tool.ResultBuild;
import com.lhx.dao.entity.AccountInfo;
import com.lhx.dao.entity.AccountInfoExample;
import com.lhx.dao.entity.City;
import com.lhx.dao.entity.CityExample;
import com.lhx.dao.mapper.AccountInfoMapper;
import com.lhx.dao.mapper.CityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author lihongxiang
 * @date
 */
@Service
public class LoginServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private AccountInfoMapper accountInfoMapper;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    public AccountInfo login(String userNmae, String password) throws BusinessException {
        List<AccountInfo> accountInfos = new ArrayList<>();

        AccountInfoExample accountInfoExample = new AccountInfoExample();
        String passwordMd5 = "";
        try {
            passwordMd5 = MD5Util.getMD5(password);
        } catch (Exception e) {
            logger.error("addInquirySellerInfo：生成MD5串异常", e);
        }
        AccountInfoExample.Criteria criteria = accountInfoExample.createCriteria();
        criteria.andPaswordEqualTo(passwordMd5);
        criteria.andUserNameEqualTo(userNmae);
        accountInfos = accountInfoMapper.selectByExample(accountInfoExample);
        if (accountInfos.size() == 0) {
            throw new BusinessException(ResultConstant.PARAMS_INAVAILABLE, "账号密码错误");
        }

        return accountInfos.get(0);
    }

    public List<City> getCityList(String name) {
        CityExample cityExample = new CityExample();
        CityExample.Criteria criteria = cityExample.createCriteria();
        criteria.andCityNameLike(name);
        List<City> cities = cityMapper.selectByExample(cityExample);
        return cities;
    }

    public void logout(HttpSession session) {
        AccountInfo accountInfo = (AccountInfo) session.getAttribute("user");
        String userNmae = accountInfo.getUserName();

        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(userNmae);
        System.out.println(hasKey);
        if(hasKey){
            String pasword = operations.get(userNmae);
            System.out.println("输出密码："+pasword);
            logger.info("输出密码："+pasword);
        }
        session.removeAttribute("user");
    }

    public void redisTest(HttpSession session) {
        AccountInfo accountInfo = (AccountInfo) session.getAttribute("user");
        // 测试redis--------------------------------------------------------------------
        // 设置redis 的 key 和 value
        ValueOperations<Serializable, String> operations = redisTemplate.opsForValue();
        operations.set(accountInfo.getUserName(), accountInfo.getPasword(), 60, TimeUnit.SECONDS);
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(accountInfo.getUserName());
        System.out.println(hasKey);
        if(hasKey){
            String pasword = operations.get(accountInfo.getUserName());
            System.out.println("输出密码："+pasword);
            logger.info("输出密码："+pasword);
        }
        // Redis 操作视图接口类用的是 ValueOperations，对应的是 Redis String/Value 操作。
        // 还有其他的操作视图，ListOperations、SetOperations、ZSetOperations 和 HashOperations 。
        // ValueOperations 插入缓存是可以设置失效时间，这里设置的失效时间是 10 s。
        // 测试redis--------------------------------------------------------------------
    }

}

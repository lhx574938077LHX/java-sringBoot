package com.lhx.controller;

import com.lhx.common.exception.BusinessException;
import com.lhx.common.result.AUDResult;
import com.lhx.common.tool.ResultBuild;
import com.lhx.dao.entity.AccountInfo;
import com.lhx.service.LoginServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author lihongxiang
 * @date
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginServiceImpl loginService;

    /**
     * 登录
     */
    @RequestMapping("/login")
    @ResponseBody
    public AUDResult login(HttpServletRequest request, String userName, String password) {
        AUDResult result = new AUDResult();
        try {
            System.out.println("用户名：" + userName);
            AccountInfo accountInfo = loginService.login(userName, password);
            result.setData(accountInfo);
            HttpSession session = request.getSession();
            session.setAttribute("user", accountInfo);

            ResultBuild.success(result);
        } catch (BusinessException e) {
            ResultBuild.exception(result, e);
        } catch (Exception e) {
            logger.error(userName + " login :" + e.getMessage());
            ResultBuild.exception(result, e);
        }
        return result;
    }

    /**
     * 退出
     */
    @RequestMapping("/logout")
    @ResponseBody
    public boolean logout(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            AccountInfo accountInfo = (AccountInfo) session.getAttribute("user");
            if (accountInfo == null) {
                System.out.println("session 没有用户信息");
                return true;
            }
            loginService.logout(session);
            System.out.println(accountInfo.getId());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 测试session get和redis
     */
    @RequestMapping("/redisTest")
    @ResponseBody
    public boolean redisTest(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            loginService.redisTest(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}

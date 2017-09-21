package com.lhx.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;


@Api(value = "首页", description = "访问首页的各种方法")
@Controller
@RequestMapping("/index")
public class IndexController {

    @Value("${waliwa}")
    private String waliwa;

    @ApiOperation(value = "首页跳转", notes = "")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        System.out.println(waliwa);
        return "index";
    }

    @ApiOperation(value = "首页跳转2", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "id2", value = "用户id2", required = false, dataType = "User")
    })
    @RequestMapping(value = "/in/{id}", method = RequestMethod.GET)
    public ModelAndView index2(@ApiParam("id，必选") @PathVariable(value = "id", required = true) String id
            , @ApiParam("id2，非必选") @RequestParam(value = "id2", required = false) String id2) {
        System.out.println(id);
        System.out.println(id2);
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @ApiOperation(value = "存sessionId", notes = "")
    @RequestMapping(value = "/uid", method = RequestMethod.GET)
    @ResponseBody
    public void uid(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        System.out.println(sessionId);
        session.setAttribute("SpringBoot", sessionId);
    }

    @ApiOperation(value = "获取sessionId", notes = "")
    @ApiImplicitParam(name = "userName", value = "用户详细实体userName", required = false, dataType = "String")
    @RequestMapping(value = "/getuid", method = RequestMethod.POST)
    @ResponseBody
    public void getuid(HttpServletRequest request, @RequestParam(value = "userName", required = false) String userName) {
        HttpSession session = request.getSession();
        String springBoot = (String) session.getAttribute("SpringBoot");
        System.out.println("输出我保存到session中的值：" + springBoot);
    }

}
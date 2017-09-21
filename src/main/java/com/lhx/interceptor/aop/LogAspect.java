/**   
 * Copyright © 2016 猪八戒网. All rights reserved.
 * 
 * @Title: LogAspect.java 
 * @Prject: mobile-wap-activity
 * @Package: com.zbj.mobile.activitywap.web.aspect 
 * @Description: 
 * @author: lidaming   
 * @date: 2016年7月30日 下午7:49:15 
 * @version: V1.0   
 */
package com.lhx.interceptor.aop;

import com.google.gson.Gson;
import com.lhx.common.tool.IpUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 日志切面
 */
@Aspect // FOR AOP
@Order(-100) // 控制多个Aspect的执行顺序，越小越先执行
@Component
public class LogAspect {

	private static Logger logger = LoggerFactory.getLogger(LogAspect.class);
	/**
	 * RequestMapping 方法切入
	 */
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	private void aspect() {}
	@Around("aspect()")
	public Object around(JoinPoint joinPoint) throws Throwable {
		Date start = new Date();
		Object object = ((ProceedingJoinPoint) joinPoint).proceed();
		this.printRequest(joinPoint);
		this.printResponse(object, start, joinPoint);
		return object;
	}

	/**
	 * @Description: 打印请求参数
	 */
	private void printRequest(JoinPoint joinPoint) {
		try {
			logger.info("----------------------------开始输出日志-------------------------------------------------");
			RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = null;
			Gson gson = new Gson();
			if (attributes != null) {
				request = ((ServletRequestAttributes) attributes).getRequest();
			}else{
				request = (HttpServletRequest) this.getTargetObject(joinPoint.getArgs(), HttpServletRequest.class);
			}
			if (request != null) {
				String url = request.getRequestURI();
				logger.info("|url=" + url);
				String ip = IpUtil.getIpAddr(request);
				logger.info("|ip=" + ip);
				logger.info("|parameters=" + gson.toJson(request.getParameterMap()));
			}
		} catch (Throwable e) {
			logger.error("AccessLogAspect printRequest error", e);
		}
	}
	/**
	 * @Description: 打印返回参数
	 */
	private void printResponse(Object object, Date start,JoinPoint joinPoint) {
		try {
			Gson gson = new Gson();
			MethodSignature sig = (MethodSignature) joinPoint.getSignature();
			Object obj =  joinPoint.getTarget();
			Method method = AopUtils.getTargetClass(obj).getMethod(sig.getMethod().getName(),sig.getMethod().getParameterTypes());
			if (method.isAnnotationPresent(ResponseBody.class)){
				logger.info("|pesponse=" + (object == null? "" : gson.toJson(object)));
			}
			logger.info("|花费时间 : " + (new Date().getTime() - start.getTime()) + "ms");
			logger.info("----------------------------结束输出日志-------------------------------------------------");
		} catch (Throwable e) {
			logger.error("AccessLogAspect printResponse error", e);
		}
	}

	/** 
	 * @Description: 获取参数中目标对象
	 */
	private Object getTargetObject(Object[] objects, Class<?> clas){
		if (objects != null && objects.length > 0) {
			for (Object object2 : objects) {
				if (object2 != null && clas.isAssignableFrom(object2.getClass()) ) {
					return object2;
				}
			}
		}
		return null;
	}
}
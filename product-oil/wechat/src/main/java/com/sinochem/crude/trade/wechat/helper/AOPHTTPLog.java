//package com.sinochem.crude.trade.wechat.helper;
//
//import com.google.gson.Gson;
//import com.sinochem.crude.trade.wechat.domain.WechatLogs;
//import com.sinochem.crude.trade.wechat.service.WechatLogService;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//
//
///*
// * 切面 打印接口输入、输出日记、接口响应时间；
// */
//@Component
//@Aspect
//public class AOPHTTPLog {
//
//	@Autowired
//	WechatLogService wechatLogService;
//	private Logger logger = LoggerFactory.getLogger(getClass());
//	Gson gson = new Gson();
//	// @Pointcut("@within(xb.posservice.web.*)")
//	// @Pointcut("execution(* cn.us.service.impl.UserServiceImpl.*(..))")
//	@Pointcut("execution(* com.sinochem.crude.trade.wechat.controller.en..*(..))")
//	public void log() {
//	}
//	@Before("log()")
//	public void doBefore(JoinPoint joinPoint) {
//		try {
//
//			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//			HttpServletRequest request = attributes.getRequest();
//			StringBuilder sb = new StringBuilder();
//			//url
//			sb.append("【接口调用】：");
//			sb.append("[url]=" + request.getRequestURL());
//			//method
//			sb.append("[method]:" + request.getMethod());
//			//ip
//			String ip= request.getRemoteAddr();//HttpRequestUtil.getIpAddr(request);
//			sb.append("[ip]:" + ip);//request.getRemoteAddr());
//			//类方法
//			sb.append("[class_method]:" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//			//参数
//			sb.append("[args]:" + gson.toJson(request.getParameterMap()));
//			sb.append("[boby]:" + gson.toJson(joinPoint.getArgs()));
////			//记录访问次数；
////			rh.AddApinum(request.getRequestURL().toString(),ip);
//			logger.info(sb.toString());
//			long startTime = System.currentTimeMillis();
//			request.setAttribute("startTime", startTime);
//			request.setAttribute("httparg", sb.toString());
//			WechatLogs wechatLogs = new WechatLogs();
//			wechatLogs.setServicename(request.getRequestURL().toString());
//			wechatLogs.setDesc(sb.toString());
//			wechatLogs.setType(1);
//			wechatLogService.addlog(wechatLogs);
//
//		}
//		catch (Exception ex)
//		{
//			logger.error(ex.getMessage());
//		}
//	}
//	@Around("log()")
//	public Object arroundLog(ProceedingJoinPoint pjp) throws Throwable {
//		Object result = pjp.proceed();
//		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//		HttpServletRequest request = attributes.getRequest();
//		StringBuffer url = request.getRequestURL();
//		String str=url+"【接口返回】" +pjp.getSignature()+"返回值"+gson.toJson(result);
//		//logger.info(str);
//		long startTime = (Long) request.getAttribute("startTime");
//		long endTime = System.currentTimeMillis();
//		long executeTime = endTime - startTime;
//		//if (handler instanceof HandlerMethod) {
//		StringBuilder sb = new StringBuilder(1000);
//		sb.append( "[接口耗时:]").append(executeTime).append("ms").append("\n");
//		logger.info(str+sb.toString());
//
//		WechatLogs wechatLogs = new WechatLogs();
//		wechatLogs.setServicename(url.toString());
//		wechatLogs.setDesc(str+sb.toString());
//		wechatLogs.setType(2);
//		wechatLogService.addlog(wechatLogs);
//		return result;
//	}
//
//	//	@After("log()")
////	public void doAfter() {
////		logger.info("222222222222");
////	}
////
////	@AfterReturning(returning = "object", pointcut = "log()")
////	public void doAfterReturning(Object object) {
////		logger.info("response={}", object.toString());
////	}
//
//}

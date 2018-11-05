package com.sinochem.crude.trade.customs.utils;

import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/*
 * 切面 打印接口输入、输出日记、接口响应时间；
 */
@Component
@Aspect
public class AOPHTTPLogUtil {


    private Logger logger = LoggerFactory.getLogger("infoLog");
    private Logger errorlogger = LoggerFactory.getLogger("errorLog");
    Gson gson = new Gson();

    // @Pointcut("@within(xb.posservice.web.*)")
    // @Pointcut("execution(* cn.us.service.impl.UserServiceImpl.*(..))")
    @Pointcut("execution(* com.sinochem.crude.trade.customs.controllers..*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        try {

            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            StringBuilder sb = new StringBuilder();
            //url
            sb.append("【接口调用】：");
            sb.append("[url]=" + request.getRequestURL());
            //method
            sb.append("[method]:" + request.getMethod());
            //ip
            String ip = HttpRequestUtil.getIpAddr(request);
            sb.append("[ip]:" + ip);//request.getRemoteAddr());
            //类方法
            sb.append("[class_method]:" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            //参数
            sb.append("[args]:" + gson.toJson(request.getParameterMap()));

            if (joinPoint.getArgs() != null && joinPoint.getArgs().length ==1) {
                sb.append("[boby]:" + gson.toJson(joinPoint.getArgs()));

            }
//			//记录访问次数；
//			rh.AddApinum(request.getRequestURL().toString(),ip);
            logger.info(sb.toString());
            long startTime = System.currentTimeMillis();
            request.setAttribute("startTime", startTime);
            request.setAttribute("httparg", sb.toString());


        } catch (Exception ex) {
            errorlogger.error(ex.getMessage());
        }
    }

    @Around("log()")
    public Object arroundLog(ProceedingJoinPoint pjp) throws Throwable {
        Object result = pjp.proceed();
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                if (request != null) {
                    StringBuffer url = request.getRequestURL();
                    String str = url + "【接口返回】" + pjp.getSignature() + "返回值" + gson.toJson(result);
                    long startTime = (Long) request.getAttribute("startTime");
                    long endTime = System.currentTimeMillis();
                    long executeTime = endTime - startTime;
                    StringBuilder sb = new StringBuilder();
                    sb.append("[接口耗时:]").append(executeTime).append("ms").append("\n");
                    logger.info(str + sb.toString());
                }
            }
        }
        catch (Exception ex){
            errorlogger.error(ex.getMessage());
        }
        return result;
    }


}

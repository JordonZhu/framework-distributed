package com.framework.distributed.log;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Jingyan
 * @Time: 2017-11-24 15:06
 * @Description:
 */
@Aspect
@Component
@Order(-5)
public class LogAspect {

    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);
    private static final String POINT_CUT = "execution(public * com..*.controller.*.*(..)) && @annotation(org.springframework.stereotype.Controller))";

    /**
     * Created with Jingyan
     * Time: 2017-11-24 17:56
     * Description:  日志打印切点
     * 定义一个切入点 {  POINT_CUT = public 任意类型返回值 com.任意公司项目业务包.controller.任意类.任意方法.(任意多个参数)   }
     */
    @Pointcut(POINT_CUT)
    public void webLog() {

    }

    /**
     * Created with Jingyan
     * Time: 2017-11-24 16:30
     * Description: 入参
     */
    @Before(value = "webLog()")
    public void doBefore(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] strings = methodSignature.getParameterNames();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> paramNames = request.getParameterNames();
        Map<String, Object> map = new HashMap<>();
        while (paramNames.hasMoreElements()) {
            String paraName = paramNames.nextElement();
            map.put(paraName, request.getParameter(paraName));
        }
        logger.debug(" method={} 入参：, param={}", joinPoint.getSignature().getDeclaringTypeName()
                + "."
                + joinPoint.getSignature().getName(), JSONObject.toJSONString(map));
    }

    /**
     * Created with Jingyan
     * Time: 2017-11-24 16:30
     * Description: 出参
     */
    @AfterReturning(value = "webLog()", returning = "retVal")
    public void doAfterReturning(JoinPoint joinPoint, Object retVal) {
        logger.debug(" method={} 出参：, param={}", joinPoint.getSignature().getDeclaringTypeName()
                + "."
                + joinPoint.getSignature().getName(), JSONObject.toJSONString(retVal));
    }

}
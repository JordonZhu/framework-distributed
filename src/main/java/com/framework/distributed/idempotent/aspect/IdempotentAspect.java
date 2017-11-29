package com.framework.distributed.idempotent.aspect;

import com.framework.distributed.annotation.Idempotent;
import com.framework.distributed.common.base.BaseVo;
import com.framework.distributed.idempotent.service.IdempotentService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: Jingyan
 * @Time: 2017-11-28 14:32
 * @Description:
 */
@Aspect
@Component
@Order(1)
public class IdempotentAspect {

    private static Logger logger = LoggerFactory.getLogger(IdempotentAspect.class);
    @Resource
    private IdempotentService idempotentService;

    /**
     * Created with Jingyan
     * Time: 2017-11-28 18:04
     * Description: constructor
     */
    public IdempotentAspect() {

    }

    /**
     * Created with Jingyan
     * Time: 2017-11-28 18:45
     * Description:
     * 定义一个切入点 { public 任意类型返回值 com.任意业务包.service.impl.任意类.任意方法.(任意多个参数) && 有注解(Idempotent) }
     */
    @Before(value = "execution(public * com..*.service.impl.*.*(..)) && @annotation(idempotent)")
    public void doBefore(JoinPoint joinPoin, Idempotent idempotent) {
        logger.debug("doBefore(invokeIdempotent())");
        Object[] params = joinPoin.getArgs();
        if (null != params && params.length > 0) {
            for (Object obj : params) {
                //幂等判断
                if (obj instanceof BaseVo) {
                    BaseVo baseVo = (BaseVo) obj;
                    boolean consumeStatus = this.idempotentService.checkIsConsumed(baseVo.getUuid());
                    baseVo.setConsumeStatus(consumeStatus);
                }
            }
        }
    }


}
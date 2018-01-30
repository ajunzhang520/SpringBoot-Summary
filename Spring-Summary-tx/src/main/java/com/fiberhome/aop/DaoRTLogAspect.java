package com.fiberhome.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Description:定义一个切面，用于监测执行超过50毫秒的SQL。打印到日记中。
 * @author sjZhang
 * @date 2018年1月30日上午10:50:09
 */
@Aspect
@Component
public class DaoRTLogAspect {

    private static final Logger logger = LoggerFactory.getLogger("daoRTLog");

    /**定义一个切点，用表达式匹配哪些类切入操作。**/
    @Pointcut("execution(public * com.fiberhome.dao..*.*(..))")
    public void daoLog() {
    }

    /**环绕通知，具体逻辑实现。**/
    @Around("daoLog()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        String method = pjp.getSignature().toString();
        Long _startTime = System.currentTimeMillis();
        try {
            return pjp.proceed();
        } finally {
            Long _wasteTime = System.currentTimeMillis() - _startTime;
            if (_wasteTime > 50) {
                StringBuilder sb = new StringBuilder();
                sb.append("method=").append(method).append(",wasteTime=").append(_wasteTime);
                logger.info(sb.toString());
            }
        }
    }

}


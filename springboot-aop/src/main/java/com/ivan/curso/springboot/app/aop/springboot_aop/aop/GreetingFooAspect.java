package com.ivan.curso.springboot.app.aop.springboot_aop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Aspect
@Component
public class GreetingFooAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Before("GreetingServicePointcuts.greetingFooLoggerPointCut")
    public void loggerBefore(JoinPoint joinPoint) {
        
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Antes; " + method + " invocado con los parametros " + args);
    }

    @After("GreetingServicePointcuts.greetingFooLoggerPointCut")
    public void loggerAfter(JoinPoint joinPoint) {
        
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Despues; " + method + " con los parametros " + args);
    }
}

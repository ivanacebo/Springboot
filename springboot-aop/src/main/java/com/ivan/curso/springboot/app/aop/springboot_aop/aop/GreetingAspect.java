package com.ivan.curso.springboot.app.aop.springboot_aop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Aspect
@Component
public class GreetingAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("GreetingServicePointcuts.greetingLoggerPointCut")
    public void loggerBefore(JoinPoint joinPoint) {
        
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Antes; " + method + " con los argumentos " + args);
    }

    @After("GreetingServicePointcuts.greetingLoggerPointCut")
    public void loggerAfter(JoinPoint joinPoint) {
        
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Despues; " + method + " con los argumentos " + args);
    }

    @AfterReturning("GreetingServicePointcuts.greetingLoggerPointCut")
    public void loggerAfterReturning(JoinPoint joinPoint) {
        
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Despues de retornar; " + method + " con los argumentos " + args);
    }

    @AfterThrowing("GreetingServicePointcuts.greetingLoggerPointCut")
    public void loggerAfterThrowing(JoinPoint joinPoint) {
        
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Despues de lanzar la excepcion; " + method + " con los argumentos " + args);
    }

    @Around("GreetingServicePointcuts.greetingLoggerPointCut")
    public Object loggerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        Object result = null;
        try {
            logger.info("El metodo " + method + " con los parametros " + args);
            result = joinPoint.proceed();
            logger.info("El metodo " + method + "() retorna el resultado " + result);
            return null;
        }catch (Throwable e) {
            logger.error("Error en la llamada del metodo " + method + "()");
            throw e;
        }
        
    }

}

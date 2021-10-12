package com.team1.it2hospitalk.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingConfig {

    @Pointcut("within(@org.springframework.stereotype.Repository *) " +
            "|| within(@org.springframework.stereotype.Service *) " +
            "|| within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointCut() {

    }

    @Pointcut("within(com.team1.it2hospitalk.controller..*) " +
            "|| within(com.team1.it2hospitalk.service..*)")
    public void applicationPointCut() {

    }

    @AfterThrowing(pointcut = "applicationPointCut() && springBeanPointCut()", throwing = "e")
    public void logAfterThrow(JoinPoint joinPoint, Throwable e) {
        log.error("Exception in {}.{} with cause = {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                e.getMessage());
    }

    @Around("applicationPointCut() && springBeanPointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug("Enter: {}.{}() with arguments = {}",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    Arrays.toString(joinPoint.getArgs()));
        }

        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            log.error("Illegal argument: {} in {}.{}()",
                    Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName());
            throw e;
        }
    }
}

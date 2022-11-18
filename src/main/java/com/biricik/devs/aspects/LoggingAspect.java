package com.biricik.devs.aspects;

import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.biricik.devs.webApi.controllers.*.*(..))")
    public void forWebApiPackage() {
    };

    @Before("forWebApiPackage()")
    public void logToWebApiPackage(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        log.info("Entering in Method : {} , ClassName : {} , Arguments : {}",
                joinPoint.getSignature().toShortString(), joinPoint.getSignature().getDeclaringTypeName(),
                Arrays.toString(joinPoint.getArgs()));
        // log.info("Class Name: " + joinPoint.getSignature().getDeclaringTypeName());
        // log.info("Arguments: " + Arrays.toString(joinPoint.getArgs()));
        // log.info("Target Class: " + joinPoint.getTarget().getClass().getName());

        if (request != null) {
            log.info("Metohd Type: " + request.getMethod());

            Enumeration<String> headerNames = request.getHeaderNames();

            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String headerValue = request.getHeader(headerName);
                log.info("Header Name: " + headerName + " Header Value : " + headerValue);
            }
            log.debug("Request Path info :" + request.getServletPath());
        }

    }

    @AfterThrowing(pointcut = "forWebApiPackage()", throwing = "exception")
    public void logToWebApiPackageforAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        log.error("An exception has been thrown in: {} () , Message: {} ", joinPoint.getSignature().getName(),
                exception.getMessage());
        // log.error("Cause : " + exception.getCause());

    }

    @Around("@annotation(com.biricik.devs.annotation.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        final StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        Object proceed = joinPoint.proceed();

        stopWatch.stop();

        log.info("\"{}\" executed in {} ms", joinPoint.getSignature(), stopWatch.getTotalTimeMillis());

        return proceed;
    }

}

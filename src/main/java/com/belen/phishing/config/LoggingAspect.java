package com.belen.phishing.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String PACKAGE_NAME = "com.belen.phishing.";

    @Pointcut("execution(public * com.belen.phishing.*.*(..))")
    private void publicMethodsFromLoggingPackage() {
        // Pointcut for public methods in the logging package
    }

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    private void serviceMethods() {
        // Pointcut for service methods
    }

    @Pointcut("within(@org.springframework.stereotype.Repository *)")
    private void repositoryMethods() {
        // Pointcut for repository methods
    }

    @Before("serviceMethods()")
    public void logBeforeServiceMethod(JoinPoint joinPoint) {
        String methodSignature = joinPoint.getSignature().toString().replace(PACKAGE_NAME, "");
        logger.info("Entrando al método: {} con argumentos: {}", methodSignature, joinPoint.getArgs());
    }

    @After("serviceMethods()")
    public void logAfterServiceMethod(JoinPoint joinPoint) {
        String methodSignature = joinPoint.getSignature().toString().replace(PACKAGE_NAME, "");
        logger.info("Saliendo del método: {}", methodSignature);
    }

    @Before("repositoryMethods()")
    public void logBeforeRepositoryMethod(JoinPoint joinPoint) {
        String methodSignature = joinPoint.getSignature().toString().replace(PACKAGE_NAME, "");
        logger.info("Entrando al método: {} con argumentos: {}", methodSignature, joinPoint.getArgs());
    }

    @After("repositoryMethods()")
    public void logAfterRepositoryMethod(JoinPoint joinPoint) {
        String methodSignature = joinPoint.getSignature().toString().replace(PACKAGE_NAME, "");
        logger.info("Saliendo del método: {}", methodSignature);
    }
}
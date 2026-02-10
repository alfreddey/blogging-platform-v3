package com.example.demo.log;

import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.slf4j.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.example.demo.service..*(..))")
    public Object logServiceCalls(ProceedingJoinPoint jp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = jp.proceed();
        long time = System.currentTimeMillis() - start;

        System.out.printf("%s executed in %sms\n", jp.getSignature().toShortString(), time);

        return result;
    }
}

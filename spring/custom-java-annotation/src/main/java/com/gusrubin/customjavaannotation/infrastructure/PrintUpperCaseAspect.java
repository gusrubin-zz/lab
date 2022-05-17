package com.gusrubin.customjavaannotation.infrastructure;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class PrintUpperCaseAspect {

    @Around("@annotation(PrintUpperCase)")
    public Object print(ProceedingJoinPoint joinPoint) throws Throwable {

	log.info("Input :\n" + joinPoint.getArgs()[0]);

//        HttpServletRequest servletRequest = (HttpServletRequest) joinPoint.getArgs()[1];

//        System.out.println(servletRequest.getRemoteAddr());

	Object result = joinPoint.proceed();

	log.info(result.toString());
	
	System.out.println("printing");

	return result;
    }

}

package com.infy.wecare.utitity;

import java.text.DateFormat;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	@Before("execution(* com.infy.wecare.controller.* .*(..))")
	public void logBeforeAdvice(JoinPoint joinPoint) {
		System.out.println("hello");
		logger.info("In Before Advice, Joinpoint signature :{}", joinPoint.getSignature());
		long time = System.currentTimeMillis();
		String date = DateFormat.getDateTimeInstance().format(time);
		logger.info("At time:{}", date);
	}
}

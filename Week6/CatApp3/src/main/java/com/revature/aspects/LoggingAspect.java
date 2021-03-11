package com.revature.aspects;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	private Logger log;
	
	@Around("aspectHook()")
	public Object log(ProceedingJoinPoint pjp) throws Throwable {
		Object obj = null;
		log = Logger.getLogger(pjp.getTarget().getClass());
		log.trace("Method with signature: " + pjp.getSignature());
		log.trace("with arguments: " + Arrays.toString(pjp.getArgs()));
		try {
			obj = pjp.proceed();
		} catch (Throwable t) {
			log.error(t.getMessage());
			log.warn(Arrays.toString(t.getStackTrace()));
			throw t;
		}
		log.trace(pjp.getSignature() + " returned: " + obj);
		
		return obj;
	}
	
//	@Before("aspectHook()")
//	public void beforeEverything() {
//		System.out.println("this happens before all of the methods");
//	}
//	
//	@After("aspectHook()")
//	public void afterEverything() {
//		System.out.println("this happens after all of the methods");
//	}
//	
//	@AfterReturning("aspectHook()")
//	public void afterReturning() {
//		System.out.println("this only happens if the method returns (rather than throwing)");
//	}
//	
//	@AfterThrowing("aspectHook()")
//	public void afterThrowing() {
//		System.out.println("this only happens if the method throws a throwable (exception/error)");
//	}
//	
//	@Around("aspectHook()")
//	public Object aroundEverything(ProceedingJoinPoint pjp) throws Throwable {
//		System.out.println("this starts before the method, then we tell it to proceed");
//		pjp.proceed();
//		System.out.println("...it continues after the method as well");
//		return null;
//	}
	
	@Pointcut("execution(* com.revature..*(..) )")
	private void aspectHook() { }
}

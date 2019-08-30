package com.aopex;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

// 감시자
@Aspect
public class logAOP {

	@Around("within(com.aopex.*)")
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable {

		// 포인트가 들어왔다 , 문자열로 바꾸어 줌
		String signatureStr = joinpoint.getSignature().toShortString();

		System.out.println(signatureStr + " 시작 "); // 호출 전

		try {

			Object obj = joinpoint.proceed(); // 특정 함수가 실행되었을 때 기능 실행
			return obj;
		} finally {
			System.out.println("실행 후 : " + System.currentTimeMillis());
			System.out.println(signatureStr + " 종료"); // 호출 후
		}

	}
}

package com.aopex;

import org.aspectj.lang.ProceedingJoinPoint;

// 감시자
public class logAOP {

	// dto에 있는 어떤 함수(메소드)가 호출이 되면 감시자가 포착한다
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

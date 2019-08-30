package bit.com.a.aop;

import java.util.Date;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import bit.com.a.model.MemberDto;

@Aspect
@Component
public class LogAop {
	// 웹 소켓하고 AOP는 같은 패키지에 넣으면 안된다

	@Around("within(bit.com.a.controller.BitBbsController)")
//	@Around("within(bit.com.a.controller.*)")
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable {
		String signatureAtr = joinpoint.getSignature().toShortString();		
/*
		try {

			System.out.println("loggerAOP: " + signatureAtr + "메소드가 실행되었습니다");
		
			// 실행 전 처리
			Object obj = joinpoint.proceed();
			return obj;
		} finally {
			// 실행 후 처리
		}
*/		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		// request 취득 , 이게 받아 올 수 있는 방법 구글은 다 거짓말..
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();

		if (request != null) {

			HttpSession session = request.getSession();

			MemberDto dto = (MemberDto) session.getAttribute("login");

			if (dto == null) {
				// 세선의 기한 만료
				System.out.println("dto = null " + new Date());
				return "redirect:login";
			} 
			else {

				System.out.println(" dto != null " + new Date());
			}
		}
		Object obj = joinpoint.proceed();
		stopWatch.stop();
		System.out.println("loggerAOP: " + signatureAtr + "메소드가 실행되었습니다" + stopWatch.getTotalTimeMillis());
		
		return obj;
	}
}

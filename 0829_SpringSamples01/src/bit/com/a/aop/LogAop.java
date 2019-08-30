package bit.com.a.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import bit.com.a.model.MemberDto;

@Aspect
public class LogAop {

	@Around("within(bit.com.a.controller.*)")
	public Object loggerAop(ProceedingJoinPoint joinpoint)throws Throwable {
				
		String signatureAtr = joinpoint.getSignature().toShortString();
						
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
				
		// request 취득
		HttpServletRequest request 
			= ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		
		if(request != null) {			
			HttpSession session = request.getSession();			
			MemberDto dto = (MemberDto)session.getAttribute("login");			
			if(dto == null) {
				return "redirect:/login.do";
			}
		}
		else {
			System.out.println("request == null");
		}					
		
		Object obj = joinpoint.proceed();
		stopWatch.stop();
		
		System.out.println("loggerAOP:" + signatureAtr + "메소드호출 Performance time:" + stopWatch.getTotalTimeMillis());
		
		return obj;		
	}
}






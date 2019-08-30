package com.aopex;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class mainClass {

	public static void main(String[] args) {

		// java에서 xml 실행 시

		AbstractApplicationContext ctx 
		= new GenericXmlApplicationContext("bean.xml");
		// 자바에서 xml 을 실행 시켜준 것 이다.

		// bean.xml에서 생성된 객체를 취득
		Cat mycat = ctx.getBean("myCat", Cat.class);

		mycat.catInfo();
		
		mycat.getName();
		
		Cat youcat = new Cat("고양이",2,"검");
		
		youcat.catInfo();
	}

}

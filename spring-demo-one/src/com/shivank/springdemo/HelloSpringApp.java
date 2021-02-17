package com.shivank.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringApp {

	public static void main(String[] args) {
		
		//load the spring configuration file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanLifecycle-applicationContext.xml");
		
		//retrieve bean from spring container
//		CricketCoach theCoach = context.getBean("myCricketCoach", CricketCoach.class);
//		
//		//call method on bean
//		System.out.println(theCoach.getDailyWorkout());
//		System.out.println(theCoach.getDailyFortune());
//		System.out.println(theCoach.getEmailAddress());
//		System.out.println(theCoach.getName());
//	
		Coach theCoach = context.getBean("myCoach", Coach.class);
		
		System.out.println(theCoach.getDailyFortune());
		//close the context
		context.close();
	}

}

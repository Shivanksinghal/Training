package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			/*
			Instructor tempInstructor = new Instructor("John", "Dow", "John@gmail.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail("www.youtube.com/john", "Creating videos");
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			*/
			Instructor tempInstructor = new Instructor("Shivank", "Singhal", "shivank.singhal.970@gmail.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.youtube.com/channel/UCH-uH1rXCMTqm9v1cM5CAXQ", "Coding");
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			session.beginTransaction();
			
			System.out.println("saving object: " + tempInstructor);
			session.save(tempInstructor);
			
			session.getTransaction().commit();
			System.out.println("done !!");
		} finally {
			session.close();
		}
	}
}

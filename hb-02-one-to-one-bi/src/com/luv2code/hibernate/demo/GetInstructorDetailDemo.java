package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class GetInstructorDetailDemo {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			int id = 2;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, id);
			System.out.println("InstructorDetail: " + tempInstructorDetail);
			System.out.println("Instructor: " + tempInstructorDetail.getInstructor());
			
			session.getTransaction().commit();
			System.out.println("done !!");
		} finally {
			session.close();
		}
	}
}

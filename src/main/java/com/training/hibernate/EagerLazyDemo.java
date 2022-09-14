package com.training.hibernate;

import com.training.hibernate.entity.Course;
import com.training.hibernate.entity.Instructor;
import com.training.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class EagerLazyDemo {

    public static void main(String[] args) {


        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            int id = 1;

            session.beginTransaction();
            Instructor instructor = session.get(Instructor.class, id);
            System.out.println("Luv2Code Instructor: " + instructor);
            System.out.println("Luv2Code Courses: " + instructor.getCourses());
            session.getTransaction().commit();
            System.out.println("Luv2Code Done!");

        } finally {
            session.close();
            sessionFactory.close();
        }

    }

}
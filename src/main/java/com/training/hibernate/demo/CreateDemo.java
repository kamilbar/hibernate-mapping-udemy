package com.training.hibernate.demo;

import com.training.hibernate.entity.Course;
import com.training.hibernate.entity.Instructor;
import com.training.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateDemo {

    public static void main(String[] args) {


        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            Instructor instructor = new Instructor("Jan", "Nowak", "jannowak@mail.com");
            InstructorDetail instructorDetail = new InstructorDetail("jannowak.youtube.com", "fishing");
            Course course = new Course("Java course");

            instructor.setInstructorDetail(instructorDetail);

            System.out.println("Saving instructor: " + instructor);
            session.beginTransaction();
            session.save(instructor);

            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            session.close();
            sessionFactory.close();
        }

    }

}
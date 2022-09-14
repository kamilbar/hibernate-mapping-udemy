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
            // this line would fix the lazy loading issue below as we would load data while session is still open
            // System.out.println(instructor.getCourses());
            session.getTransaction().commit();
            session.close();
            System.out.println("\nLuv2Code: The session is now closed\n");
            // code below should fail (for LAZY fetch type) as we close the session in line above
            System.out.println("Luv2Code Courses: " + instructor.getCourses());
            System.out.println("Luv2Code Done!");

        } finally {
            session.close();
            sessionFactory.close();
        }

    }

}
package com.training.hibernate.demo;

import com.training.hibernate.entity.Course;
import com.training.hibernate.entity.Instructor;
import com.training.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class FetchJoinDemo {

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
            Query<Instructor> query
                    = session.createQuery("select i from Instructor i "
                                        + "JOIN FETCH i.courses "
                                        + "where i.id=:instructorId",
                    Instructor.class);

            query.setParameter("instructorId", id);

            Instructor instructor = query.getSingleResult();

            System.out.println("Luv2Code: Instructor: " + instructor);
            session.getTransaction().commit();
            session.close();
            System.out.println("\nLuv2Code: The session is now closed\n");

            System.out.println("Luv2Code Courses: " + instructor.getCourses());
            System.out.println("Luv2Code Done!");

        } finally {
            session.close();
            sessionFactory.close();
        }

    }

}
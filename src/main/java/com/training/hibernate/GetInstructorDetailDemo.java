package com.training.hibernate;

import com.training.hibernate.entity.Instructor;
import com.training.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class GetInstructorDetailDemo {

    public static void main(String[] args) {


        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            int id=2;

            session.beginTransaction();
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);
            System.out.println("Got instructor details: " + instructorDetail);
            System.out.println("The associated instructor: " + instructorDetail.getInstructor());

//            System.out.println("deleting instructor details...");
//            session.delete(instructorDetail);

            session.getTransaction().commit();

            System.out.println("Done!");

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }

}

package com.training.hibernate;

import com.training.hibernate.entity.Instructor;
import com.training.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteDemo {

    public static void main(String[] args) {


        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            int id = 1;
            session.beginTransaction();
            Instructor instructor = session.get(Instructor.class, id);

            System.out.println("Found instructor: " + instructor);

            // due to CascadeType.All associated instructor details will also be deleted
            if (instructor != null) {
                System.out.println("\nDeleting instructor...");
                session.delete(instructor);
            }

            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            session.close();
        }

    }

}

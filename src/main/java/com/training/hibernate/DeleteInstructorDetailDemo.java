package com.training.hibernate;

import com.training.hibernate.entity.Instructor;
import com.training.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.lang.invoke.MethodHandles;


public class DeleteInstructorDetailDemo {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            int id = 4;
            session.beginTransaction();
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

            if (instructorDetail != null) {
                System.out.println("Found instructor detail: " + instructorDetail);
                System.out.println("Related instructor: " + instructorDetail.getInstructor());

                System.out.println("Deleting instructor detail...");

                // remove the associated object reference
                // break bi-directional link
                instructorDetail.getInstructor().setInstructorDetail(null);

                session.delete(instructorDetail);
                System.out.println("Done!");
            } else {
                System.out.println("Instructor Details does not exist for selected id: " + id);
            }


            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }

}

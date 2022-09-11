package com.training.hibernate;

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
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            Instructor instructor = new Instructor("Jan", "Nowak", "jannowak@mail.com");
            InstructorDetail instructorDetail = new InstructorDetail("jannowak.youtube.com", "fishing");

            instructor.setInstructorDetailId(instructorDetail);

            System.out.println("Saving instructor: " + instructor);
            session.beginTransaction();
            session.save(instructor);


            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            session.close();
        }

    }

}

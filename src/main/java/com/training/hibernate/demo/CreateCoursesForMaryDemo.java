package com.training.hibernate.demo;

import com.training.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesForMaryDemo {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            // load student
            int studentId = 2;
            Student student = session.get(Student.class, studentId);
            System.out.println("\nLoaded student: " + student);
            System.out.println("Courses: " + student.getCourses());
//            session.getTransaction().commit();

            // create courses
            Course course1 = new Course("Spring course");
            Course course2 = new Course("MVC course");

            course1.addStudent(student);
            course2.addStudent(student);
            System.out.println("\nSaving Students...");
            session.save(course1);
            session.save(course2);
            System.out.println("Done!");

            session.getTransaction().commit();;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }

}

package com.training.hibernate.demo;

import com.training.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo {

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
            Course course = new Course("Hibernate Course");
            session.beginTransaction();
            session.save(course);
            System.out.println("Saved course: " + course);

            Student student1 = new Student("John", "Doe", "johndoe@luv2code.com");
            Student student2 = new Student("Mary", "James", "mary@luv2code.com");
            course.addStudent(student1);
            course.addStudent(student2);

            System.out.println("\nSaving students...");
            session.save(student1);
            session.save(student2);
            System.out.println("\nSaved students: " + course.getStudents());
            session.getTransaction().commit();

            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }

}

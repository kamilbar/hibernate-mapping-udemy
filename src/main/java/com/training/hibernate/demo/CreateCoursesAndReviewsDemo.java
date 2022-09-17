package com.training.hibernate.demo;

import com.training.hibernate.entity.Course;
import com.training.hibernate.entity.Instructor;
import com.training.hibernate.entity.InstructorDetail;
import com.training.hibernate.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateCoursesAndReviewsDemo {

    public static void main(String[] args) {


        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            Course course = new Course("Hibernate course");

            Review review = new Review("Good course!");
            Review review2 = new Review("I've learnt a lot!");
            Review review3 = new Review("Good structure of the course");

            course.addReview(review);
            course.addReview(review2);
            course.addReview(review3);

            session.beginTransaction();
            session.save(course);
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            session.close();
            sessionFactory.close();
        }

    }

}
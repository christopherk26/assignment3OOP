package com.example;

import com.example.entity.Customer;
import com.example.entity.Professor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainCreate {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Professor.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        try {
            // Create objects
            Customer customer = new Customer("John Doe", "123 Main St");
            Professor professor = new Professor("Room 101", "Computer Science");

            // Set up relationship
            professor.setCustomer(customer);
            customer.setProfessor(professor);

            // Start transaction
            session.beginTransaction();

            // Save customer (cascade will save professor)
            session.save(customer);

            // Commit transaction
            session.getTransaction().commit();

            System.out.println("Customer and Professor created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
            factory.close();
        }
    }
}
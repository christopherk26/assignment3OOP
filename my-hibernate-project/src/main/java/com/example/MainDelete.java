package com.example;

import com.example.entity.Customer;
import com.example.entity.Professor;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainDelete {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Professor.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        try {
            // Start transaction
            session.beginTransaction();

            // Get customer by ID (replace with actual ID)
            int customerId = 1;
            Customer customer = session.get(Customer.class, customerId);

            if (customer != null) {
                // Delete customer (cascade will delete professor)
                session.delete(customer);
                System.out.println("Customer and Professor deleted successfully!");
            } else {
                System.out.println("Customer not found!");
            }

            // Commit transaction
            session.getTransaction().commit();

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
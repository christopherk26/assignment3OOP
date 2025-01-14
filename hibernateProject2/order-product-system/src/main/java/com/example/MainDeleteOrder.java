
// MainDeleteOrder.java
package com.example;

import com.example.entity.Order;
import com.example.entity.Product;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainDeleteOrder {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        try {
            session.beginTransaction();

            // Get order by ID
            int orderId = 1;  // Replace with actual ID
            Order order = session.get(Order.class, orderId);

            if (order != null) {
                // Delete order
                session.delete(order);
                System.out.println("Order deleted successfully!");
            } else {
                System.out.println("Order not found!");
            }

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
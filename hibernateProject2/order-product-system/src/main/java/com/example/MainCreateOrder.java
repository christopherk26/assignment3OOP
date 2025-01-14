
package com.example;

import com.example.entity.Order;
import com.example.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Date;

public class MainCreateOrder {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        try {
            session.beginTransaction();

            Order order = new Order("John Doe", new Date());

            Product product = session.get(Product.class, 1); // Replace with actual product ID
            if (product != null) {
                order.addProduct(product);
            }

            session.save(order);
            session.getTransaction().commit();

            System.out.println("Order created successfully with ID: " + order.getId());

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
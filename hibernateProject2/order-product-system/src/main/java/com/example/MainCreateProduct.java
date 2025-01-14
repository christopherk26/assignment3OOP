
package com.example;

import com.example.entity.Product;
import com.example.entity.Order;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainCreateProduct {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        try {
            Product product = new Product("Sample Product");

            session.beginTransaction();

            session.save(product);

            session.getTransaction().commit();

            System.out.println("Product created successfully with ID: " + product.getId());

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

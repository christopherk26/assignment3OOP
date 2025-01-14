
package com.example;

import com.example.entity.Product;
import com.example.entity.Order;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainDeleteProduct {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        try {
            session.beginTransaction();

            // Get product by its ID using the get
            int productId = 1;  // Replace with the actual ID
            Product product = session.get(Product.class, productId);

            if (product != null) {
                // delete product using .delete
                session.delete(product);
                System.out.println("Product deleted successfully!");
            } else {
                System.out.println("Product not found!");
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

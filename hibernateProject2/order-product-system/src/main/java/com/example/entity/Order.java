package com.example.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")  // order is a reserved word in SQL
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    // many to many here
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "order_detail",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products = new HashSet<>();

    // Constructors
    public Order() {}

    public Order(String customerName, Date date) {
        this.customerName = customerName;
        this.date = date;
    }

    // getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    
    public Set<Product> getProducts() { return products; }
    public void setProducts(Set<Product> products) { this.products = products; }

    // Helper methods for product
    public void addProduct(Product product) {
        this.products.add(product);
        product.getOrders().add(this);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
        product.getOrders().remove(this);
    }
}
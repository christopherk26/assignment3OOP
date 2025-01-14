
package com.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "professor")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "office_number", nullable = false)
    private String officeNumber;

    @Column(name = "research_area", nullable = false)
    private String researchArea;

    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Professor() {}

    public Professor(String officeNumber, String researchArea) {
        this.officeNumber = officeNumber;
        this.researchArea = researchArea;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getOfficeNumber() { return officeNumber; }
    public void setOfficeNumber(String officeNumber) { this.officeNumber = officeNumber; }
    public String getResearchArea() { return researchArea; }
    public void setResearchArea(String researchArea) { this.researchArea = researchArea; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
}

package com.mlopezsoto.customer.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity that represents Customer data to be persisted.
 */
@Entity
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;


    /**
     * Default constructor.
     */
    public CustomerEntity() {
    }

    /**
     * Constructor that takes all properties as parameters.
     * @param id Customer unique ID
     * @param name Customer name
     * @param email Customer's email
     */
    public CustomerEntity(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }


    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

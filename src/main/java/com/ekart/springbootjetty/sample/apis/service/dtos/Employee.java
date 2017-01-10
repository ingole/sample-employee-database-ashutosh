package com.ekart.springbootjetty.sample.apis.service.dtos;

/**
 * Created by ingole.as on 05/01/17.
 */
public class Employee {

    private Long id;

    private String name;

    private Long contact;

    public Employee() {

    }

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

    public Long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }
}


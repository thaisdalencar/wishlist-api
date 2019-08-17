package com.thaisdalencar.wishlist.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class Client extends BaseEntity {

    @NotEmpty(message = "Name not be empty")
    private String name;

    @NotEmpty(message = "Email  not be empty")
    private String email;

    public Client() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

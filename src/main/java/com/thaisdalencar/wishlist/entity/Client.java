package com.thaisdalencar.wishlist.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class Client extends BaseEntity {

    @NotEmpty(message = "Name not be empty")
    private String name;

    @NotEmpty(message = "Email  not be empty")
    private String email;

    public Client(String name, String email) {
        this.name = name;
        this.email = email.toLowerCase();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

package com.thaisdalencar.wishlist.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
public class Client extends BaseEntity {

    @NotEmpty(message = "Name not be empty")
    private String name;

    @Pattern(regexp=".+@.+\\..+", message="Email should be valid")
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<WishListItem> wishList;

    public Client() {
    }

    public Client(@NotEmpty(message = "Name not be empty") String name, @Pattern(regexp = ".+@.+\\..+", message = "Email should be valid") String email) {
        this.name = name;
        this.email = email;
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

package com.thaisdalencar.wishlist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class WishListItem extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    private String productId;

    public WishListItem() {
    }

    public WishListItem(Client client, String productId) {
        this.client = client;
        this.productId = productId;
    }

    public Client getClient() {
        return client;
    }

    public String getProductId() {
        return productId;
    }
}

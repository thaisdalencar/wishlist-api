package com.thaisdalencar.wishlist.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class WishListItem extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String productId;

    public WishListItem() {
    }

    public WishListItem(Customer customer, String productId) {
        this.customer = customer;
        this.productId = productId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getProductId() {
        return productId;
    }
}

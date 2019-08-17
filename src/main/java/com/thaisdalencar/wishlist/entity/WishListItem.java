package com.thaisdalencar.wishlist.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class WishListItem extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @NotNull(message = "ProductId not be null")
    private long productId;

    public WishListItem() {
    }

    public Client getClient() {
        return client;
    }

    public long getProductId() {
        return productId;
    }
}

package com.thaisdalencar.wishlist.controller.request;

public class FavoriteProductRequest {

    private String productId;

    public FavoriteProductRequest() {
    }

    public FavoriteProductRequest(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }
}

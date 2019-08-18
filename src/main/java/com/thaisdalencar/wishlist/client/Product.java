package com.thaisdalencar.wishlist.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Product {

    private String id;
    private String title;
    private String image;
    private double price;
    @JsonInclude(Include.NON_NULL)
    private double reviewScore;

    public Product() {
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }

    public double getReviewScore() {
        return reviewScore;
    }
}


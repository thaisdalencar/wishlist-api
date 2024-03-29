package com.thaisdalencar.wishlist.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Product {

    private String id;
    private String title;
    private String image;
    private Double price;
    @JsonInclude(Include.NON_NULL)
    private Double reviewScore;

    public Product(String id, String title, String image, Double price, Double reviewScore) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.price = price;
        this.reviewScore = reviewScore;
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

    public Double getPrice() {
        return price;
    }

    public Double getReviewScore() {
        return reviewScore;
    }
}


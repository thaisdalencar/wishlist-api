package com.thaisdalencar.wishlist.client;

public class Product {

    private String id;
    private String title;
    private String image;
    private double price;

    public Product(String id, String title, String image, double price) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.price = price;
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
}


package com.thaisdalencar.wishlist.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "products", url = "http://challenge-api.luizalabs.com")
public interface ProductApiClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/product/1bf0f365-fbdd-4e21-9786-da459d78dd1f")
    Product getProduct();
}

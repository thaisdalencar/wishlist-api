package com.thaisdalencar.wishlist.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "products", url = "${clients.products.url}")
public interface ProductApiClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/product/{productId}")
    Product getById(@PathVariable("productId") String productId);
}

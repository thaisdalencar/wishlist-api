package com.thaisdalencar.wishlist.controller;

import com.thaisdalencar.wishlist.client.Product;
import com.thaisdalencar.wishlist.entity.WishListItem;
import com.thaisdalencar.wishlist.service.WishListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/clients/{clientId}/favorite-products")
public class WishListController {

    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @PostMapping("/{productId}")
    @ResponseStatus(CREATED)
    public WishListItem save(@PathVariable("clientId") long clientId, @PathVariable("productId") long productId) {
        return wishListService.save(clientId, productId);
    }

    @GetMapping
    public List<WishListItem> get(@PathVariable("clientId") long clientId) {
        return wishListService.findByClientId(clientId);
    }

    @GetMapping("/{productId}")
    public Product getItem(@PathVariable("clientId") long clientId, @PathVariable("productId") long productId) {
        return wishListService.findByClientIdAndProductId(clientId, productId);
    }

    @DeleteMapping("/{productId}")
    public void delete(@PathVariable("clientId") long clientId, @PathVariable("productId") long productId) {
        wishListService.deleteByClientIdAndProductId(clientId, productId);
    }
}

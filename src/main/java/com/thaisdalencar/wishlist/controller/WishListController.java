package com.thaisdalencar.wishlist.controller;

import com.thaisdalencar.wishlist.client.Product;
import com.thaisdalencar.wishlist.controller.request.FavoriteProductRequest;
import com.thaisdalencar.wishlist.controller.request.PaginationRequest;
import com.thaisdalencar.wishlist.entity.WishListItem;
import com.thaisdalencar.wishlist.service.WishListService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/clients/{clientId}/favorite-products")
public class WishListController {

    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public WishListItem save(@PathVariable("clientId") long clientId, @RequestBody FavoriteProductRequest favoriteProductRequest) {
        return wishListService.save(clientId, favoriteProductRequest);
    }

    @GetMapping
    public Page<Product> get(@PathVariable("clientId") long clientId, PaginationRequest page) {
        return wishListService.findByClientId(clientId, page);
    }

    @GetMapping("/{productId}")
    public Product getItem(@PathVariable("clientId") long clientId, @PathVariable("productId") String productId) {
        return wishListService.findByClientIdAndProductId(clientId, productId);
    }

    @DeleteMapping("/{productId}")
    public void delete(@PathVariable("clientId") long clientId, @PathVariable("productId") String productId) {
        wishListService.deleteByClientIdAndProductId(clientId, productId);
    }
}

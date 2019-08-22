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
@RequestMapping("/api/v1/customers/{customerId}/wish-list")
public class WishListController {

    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public WishListItem save(@PathVariable("customerId") long customerId, @RequestBody FavoriteProductRequest favoriteProductRequest) {
        return wishListService.save(customerId, favoriteProductRequest);
    }

    @GetMapping
    public Page<Product> get(@PathVariable("customerId") long customerId, PaginationRequest page) {
        return wishListService.findByCustomerId(customerId, page);
    }

    @GetMapping("/{productId}")
    public Product getItem(@PathVariable("customerId") long customerId, @PathVariable("productId") String productId) {
        return wishListService.findByCustomerIdAndProductId(customerId, productId);
    }

    @DeleteMapping("/{productId}")
    public void delete(@PathVariable("customerId") long customerId, @PathVariable("productId") String productId) {
        wishListService.deleteByCustomerIdAndProductId(customerId, productId);
    }
}

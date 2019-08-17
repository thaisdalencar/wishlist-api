package com.thaisdalencar.wishlist.controller;

import com.thaisdalencar.wishlist.entity.WishListItem;
import com.thaisdalencar.wishlist.service.WishListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/clients/{clientId}/wishes")
public class WishListController {

    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public WishListItem save(@RequestBody WishListItem wishListItem) {
        return wishListService.save(wishListItem);
    }

    @GetMapping
    public List<WishListItem> get(@PathVariable("clientId") long clientId) {
        return wishListService.findByClientId(clientId);
    }

    @GetMapping("/{id}")
    public WishListItem getItem(@PathVariable("clientId") long clientId, @PathVariable("id") long id) {
        return wishListService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("clientId") long clientId, @PathVariable("id") long id) {
        wishListService.deleteById(id);
    }

}

package com.thaisdalencar.wishlist.service;

import com.thaisdalencar.wishlist.client.Product;
import com.thaisdalencar.wishlist.client.ProductApiClient;
import com.thaisdalencar.wishlist.controller.request.FavoriteProductRequest;
import com.thaisdalencar.wishlist.controller.request.PaginationRequest;
import com.thaisdalencar.wishlist.entity.WishListItem;
import com.thaisdalencar.wishlist.exception.InvalidProductException;
import com.thaisdalencar.wishlist.exception.NotFoundException;
import com.thaisdalencar.wishlist.repository.WishListItemRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@Service
public class WishListService {

    private final WishListItemRepository wishListItemRepository;
    private final CustomerService customerService;
    private final ProductApiClient productApiClient;

    public WishListService(WishListItemRepository wishListItemRepository, CustomerService customerService, ProductApiClient productApiClient) {
        this.wishListItemRepository = wishListItemRepository;
        this.customerService = customerService;
        this.productApiClient = productApiClient;
    }

    public WishListItem save(long customerId, FavoriteProductRequest favoriteProductRequest) {
        try {
            var product = getValidProduct(favoriteProductRequest.getProductId());
            var customer = customerService.findById(customerId);  //todo: tem como melhorar isso? nao precisar fazer uma consulta no client
            var wishListItem = new WishListItem(customer, product.getId());
            return wishListItemRepository.save(wishListItem);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    private Product getValidProduct(String productId) {
        try {
            return productApiClient.getById(productId);
        } catch (RuntimeException e) {
            throw new InvalidProductException(String.format("ProductId: %s not exist", productId));
        }
    }

    public Page<Product> findByCustomerId(long customerId, PaginationRequest page) {
        var wishListItems = wishListItemRepository.findByCustomerId(customerId, page.getPagination());
        if (wishListItems.getTotalElements() == 0) {
            throw new NotFoundException("Not found favorite products");
        }

        var products = new ArrayList<Product>();
        wishListItems.forEach(item -> {
            var product = productApiClient.getById(item.getProductId());
            products.add(product);
        });

        return new PageImpl<Product>(products, page.getPagination(), page.getSize());
    }

    public Product findByCustomerIdAndProductId(long customerId, String productId) {
        var wishListItem = wishListItemRepository.findByCustomerIdAndProductId(customerId, productId)
                .orElseThrow(() -> new NotFoundException(String.format("Not found productId: %s in wish list of customerId: %d", productId, customerId)));

        return getValidProduct(wishListItem.getProductId());
    }

    public void deleteByCustomerIdAndProductId(long customerId, String productId) {
        var deleted = wishListItemRepository.deleteByCustomerIdAndProductId(customerId, productId);
        if (deleted == 0) {
           throw  new NotFoundException(String.format("Not found productId: %s in wish list of customerId: %d", productId, customerId));
        }
    }
}

package com.thaisdalencar.wishlist.service;

import com.thaisdalencar.wishlist.client.Product;
import com.thaisdalencar.wishlist.client.ProductApiClient;
import com.thaisdalencar.wishlist.entity.WishListItem;
import com.thaisdalencar.wishlist.exception.InvalidProductException;
import com.thaisdalencar.wishlist.exception.NotFoundException;
import com.thaisdalencar.wishlist.repository.WishListItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishListService {

    private final WishListItemRepository wishListItemRepository;
    private final ClientService clientService;
    private final ProductApiClient productApiClient;

    public WishListService(WishListItemRepository wishListItemRepository, ClientService clientService, ProductApiClient productApiClient) {
        this.wishListItemRepository = wishListItemRepository;
        this.clientService = clientService;
        this.productApiClient = productApiClient;
    }

    public WishListItem save(long clientId, String productId) {
        var product = getProductDetails(productId);
        var client = clientService.findById(clientId);  //todo: tem como melhorar isso? nao precisar fazer uma consulta no client
        var wishListItem = new WishListItem(client, product.getId());
        return wishListItemRepository.save(wishListItem);
    }

    private Product getProductDetails(String productId) {
        try {
            return productApiClient.getById(productId);
        } catch (RuntimeException e) {
            throw new InvalidProductException(String.format("ProductId: %s not exist", productId));
        }
    }

    public List<Product> findByClientId(long clientId) {
        var wishListItems = wishListItemRepository.findByClientId(clientId);
        var products = new ArrayList<Product>();
        wishListItems.forEach(item -> {
            var product = productApiClient.getById(item.getProductId());
            products.add(product);
        });

        return products;
    }

    public Product findByClientIdAndProductId(long clientId, String productId) {
        var wishListItem = wishListItemRepository.findByClientIdAndProductId(clientId, productId)
                .orElseThrow(() -> new NotFoundException(String.format("Not found productId: %s in wish list of clientId: %d", productId, clientId)));

        return getProductDetails(wishListItem.getProductId());
    }

    public Optional<Long> deleteByClientIdAndProductId(long clientId, String productId) {
        return wishListItemRepository.deleteByClientIdAndProductId(clientId, productId);
    }

}

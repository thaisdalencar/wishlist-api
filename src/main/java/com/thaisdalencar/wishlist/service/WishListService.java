package com.thaisdalencar.wishlist.service;

import com.thaisdalencar.wishlist.client.Product;
import com.thaisdalencar.wishlist.client.ProductApiClient;
import com.thaisdalencar.wishlist.entity.WishListItem;
import com.thaisdalencar.wishlist.repository.ClientRepository;
import com.thaisdalencar.wishlist.repository.WishListItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishListService {

    private final WishListItemRepository wishListItemRepository;
    private final ClientRepository clientRepository;
    private final ProductApiClient productApiClient;

    public WishListService(WishListItemRepository wishListItemRepository, ClientRepository clientRepository, ProductApiClient productApiClient) {
        this.wishListItemRepository = wishListItemRepository;
        this.clientRepository = clientRepository;
        this.productApiClient = productApiClient;
    }

    public WishListItem save(long clientId, String productId) {
        var isValid = validateProduct(productId);
        if (isValid) {
            var client = clientRepository.findById(clientId);
            var wishListItem = new WishListItem(client, productId); //todo: tem como melhorar isso? nao precisar fazer uma consulta no client
            return wishListItemRepository.save(wishListItem);
        }

        return null; //todo: throw 403 status
    }

    private boolean validateProduct(String productId) {
        var product = productApiClient.getById(productId);
        return product != null;
    }

    public List<WishListItem> findByClientId(long clientId) {
        return wishListItemRepository.findByClientId(clientId);
    }

    public Product findByClientIdAndProductId(long clientId, String productId) {
//        return wishListItemRepository.findByClientIdAndProductId(clientId, productId);
        return productApiClient.getById(productId);
    }

    public Optional<Long> deleteByClientIdAndProductId(long clientId, String productId) {
        return wishListItemRepository.deleteByClientIdAndProductId(clientId, productId);
    }

}

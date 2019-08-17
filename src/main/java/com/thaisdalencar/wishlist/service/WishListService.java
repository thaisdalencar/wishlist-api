package com.thaisdalencar.wishlist.service;

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

    public WishListService(WishListItemRepository wishListItemRepository, ClientRepository clientRepository) {
        this.wishListItemRepository = wishListItemRepository;
        this.clientRepository = clientRepository;
    }

    public WishListItem save(long clientId, long productId) {
        var client = clientRepository.findById(clientId);
        var wishListItem = new WishListItem(client, productId); //todo: tem como melhorar isso? nao precisar fazer uma consulta no client
        return wishListItemRepository.save(wishListItem);
    }

    public List<WishListItem> findByClientId(long clientId) {
        return wishListItemRepository.findByClientId(clientId);
    }

    public WishListItem findByClientIdAndProductId(long clientId, long productId) {
        return wishListItemRepository.findByClientIdAndProductId(clientId, productId);
    }

    public Optional<Long> deleteByClientIdAndProductId(long clientId, long productId) {
        return wishListItemRepository.deleteByClientIdAndProductId(clientId, productId);
    }

}

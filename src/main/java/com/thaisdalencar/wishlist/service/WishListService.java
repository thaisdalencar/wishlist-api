package com.thaisdalencar.wishlist.service;

import com.thaisdalencar.wishlist.entity.WishListItem;
import com.thaisdalencar.wishlist.repository.WishListItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishListService {

    private final WishListItemRepository wishListItemRepository;

    public WishListService(WishListItemRepository wishListItemRepository) {
        this.wishListItemRepository = wishListItemRepository;
    }

    public WishListItem save(WishListItem wishListItem) {
        return wishListItemRepository.save(wishListItem);
    }

    public List<WishListItem> findByClientId(long clientId) {
        return wishListItemRepository.findByClientId(clientId);
    }

    public WishListItem findById(long id) {
        return wishListItemRepository.findById(id);
    }

    public Optional<Long> deleteById(long id) {
        return wishListItemRepository.deleteById(id);
    }

}

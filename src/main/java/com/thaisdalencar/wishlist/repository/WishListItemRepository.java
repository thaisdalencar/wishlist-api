package com.thaisdalencar.wishlist.repository;

import com.thaisdalencar.wishlist.entity.WishListItem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishListItemRepository extends CrudRepository<WishListItem, Long> {

    @EntityGraph(attributePaths = { "client" })
    WishListItem findByClientIdAndProductId(long clientId, long productId);

    @EntityGraph(attributePaths = { "client" })
    List<WishListItem> findByClientId(long clientId);

    Optional<Long> deleteById(long id);
}

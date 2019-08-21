package com.thaisdalencar.wishlist.repository;

import com.thaisdalencar.wishlist.entity.WishListItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface WishListItemRepository extends PagingAndSortingRepository<WishListItem, Long> {

    @EntityGraph(attributePaths = { "client" })
    Optional<WishListItem> findByClientIdAndProductId(long clientId, String productId);

    @EntityGraph(attributePaths = { "client" })
    Page<WishListItem> findByClientId(long clientId, Pageable pageable);

    @Transactional
    @EntityGraph(attributePaths = { "client" })
    Long deleteByClientIdAndProductId(long clientId, String productId);
}

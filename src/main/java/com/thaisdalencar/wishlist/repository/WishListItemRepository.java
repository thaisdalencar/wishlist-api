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

    @EntityGraph(attributePaths = { "customer" })
    Optional<WishListItem> findByCustomerIdAndProductId(long customerId, String productId);

    @EntityGraph(attributePaths = { "customer" })
    Page<WishListItem> findByCustomerId(long customerId, Pageable pageable);

    @Transactional
    @EntityGraph(attributePaths = { "customer" })
    Long deleteByCustomerIdAndProductId(long customerId, String productId);
}

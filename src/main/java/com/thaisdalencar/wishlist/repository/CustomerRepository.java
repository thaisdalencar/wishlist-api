package com.thaisdalencar.wishlist.repository;

import com.thaisdalencar.wishlist.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

    Optional<Customer> findById(long id);

    Page<Customer> findAll(Pageable page);

    void deleteById(long id);
}

package com.thaisdalencar.wishlist.repository;

import com.thaisdalencar.wishlist.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository  extends PagingAndSortingRepository<Client, Long> {

    Optional<Client> findById(long id);

    Page<Client> findAll(Pageable page);

    void deleteById(long id);
}

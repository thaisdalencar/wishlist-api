package com.thaisdalencar.wishlist.repository;

import com.thaisdalencar.wishlist.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository  extends CrudRepository<Client, Long> {

    Optional<Client> findById(long id);

    List<Client> findAll();

    void deleteById(long id);
}

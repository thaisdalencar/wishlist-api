package com.thaisdalencar.wishlist.service;

import com.thaisdalencar.wishlist.controller.request.PaginationRequest;
import com.thaisdalencar.wishlist.entity.Customer;
import com.thaisdalencar.wishlist.exception.NotFoundException;
import com.thaisdalencar.wishlist.repository.CustomerRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer) {
        try {
            return customerRepository.save(customer);
        } catch (ConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    public Page<Customer> findAll(PaginationRequest page) {
        var customers = customerRepository.findAll(page.getPagination());
        if (customers.getTotalElements() == 0) {
            throw new NotFoundException("Not found clients");
        }

        return customers;
    }

    public Customer findById(long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Not found customerId: %d", id)));
    }

    public void deleteById(long id) {
        try {
            customerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(String.format("Not found customerId: %d", id));
        }
    }

    public Customer updateById(Customer customer, long id) {
        var savedCustomer = findById(id);
        savedCustomer.setName(customer.getName());
        return customerRepository.save(savedCustomer);
    }
}

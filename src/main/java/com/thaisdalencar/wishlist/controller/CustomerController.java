package com.thaisdalencar.wishlist.controller;

import com.thaisdalencar.wishlist.controller.request.PaginationRequest;
import com.thaisdalencar.wishlist.entity.Customer;
import com.thaisdalencar.wishlist.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;


@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Customer save(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @GetMapping
    public Page<Customer> getAll(PaginationRequest page) {
        return customerService.findAll(page);
    }

    @GetMapping("/{id}")
    public Customer get(@PathVariable("id") long id) {
        return customerService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        customerService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Customer update(@RequestBody Customer customer, @PathVariable("id") long id) {
        return customerService.updateById(customer, id);
    }
}

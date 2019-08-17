package com.thaisdalencar.wishlist.controller;

import com.thaisdalencar.wishlist.entity.Client;
import com.thaisdalencar.wishlist.service.ClientService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;


@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Client save(@RequestBody Client client) {
        return clientService.save(client);
    }

    @GetMapping("/{id}")
    public Client get(@PathVariable("id") long id) {
        return clientService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        clientService.deleteById(id);
    }
}

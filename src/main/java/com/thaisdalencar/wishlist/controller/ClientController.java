package com.thaisdalencar.wishlist.controller;

import com.thaisdalencar.wishlist.entity.Client;
import com.thaisdalencar.wishlist.service.ClientService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;


@RestController
@RequestMapping("/api/v2/clients")
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
}

package com.thaisdalencar.wishlist.api;

import com.thaisdalencar.wishlist.entity.Client;
import com.thaisdalencar.wishlist.repository.ClientRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ClientRepository clientRepository;

    @After
    public void tearDown() {
        clientRepository.deleteAll();
    }

    @Test
    public void given_request_by_clients_then_returns_all() throws Exception {
        saveMockClients();
        mvc.perform(get("/api/v1/clients"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].name", is("User A")))
                .andExpect(jsonPath("$.content[0].email", is("usera@email.com")));
    }

    private void saveMockClients() {
        var clientA = new Client("User A", "usera@email.com");
        var clientB = new Client("User B", "userb@email.com");
        clientRepository.saveAll(List.of(clientA, clientB));
    }
}

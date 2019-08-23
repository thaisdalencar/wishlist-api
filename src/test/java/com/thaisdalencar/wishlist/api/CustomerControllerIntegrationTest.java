package com.thaisdalencar.wishlist.api;

import com.thaisdalencar.wishlist.controller.request.JwtRequest;
import com.thaisdalencar.wishlist.controller.response.JwtResponse;
import com.thaisdalencar.wishlist.entity.Customer;
import com.thaisdalencar.wishlist.repository.CustomerRepository;
import com.thaisdalencar.wishlist.security.jwt.JwtTokenUtil;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @After
    public void tearDown() {
        customerRepository.deleteAll();
    }

    @Test
    public void given_request_by_customer_then_returns_all() throws Exception {
        saveMockClients();
        var auth = mockAuthentication();
        mvc.perform(get("/api/v1/customers")
                .header("Authorization", "Bearer " + auth.getToken()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].name", is("User A")))
                .andExpect(jsonPath("$.content[0].email", is("usera@email.com")));
    }

    private void saveMockClients() {
        var clientA = new Customer("User A", "usera@email.com");
        var clientB = new Customer("User B", "userb@email.com");
        customerRepository.saveAll(List.of(clientA, clientB));
    }

    @Test
    public void given_request_by_specific_customer_then_return_info_about_him() throws Exception {
        saveMockClients();
        var clients = customerRepository.findAll();
        var client = clients.iterator().next();
        var auth = mockAuthentication();
        mvc.perform(get(String.format("/api/v1/customers/%d", client.getId()))
                .header("Authorization", "Bearer " + auth.getToken()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name", is(client.getName())))
                .andExpect(jsonPath("$.email", is(client.getEmail())));
    }

    private JwtResponse mockAuthentication() {
        var login = new JwtRequest("admin", "admin");
        return new JwtResponse(jwtTokenUtil.generateToken(login));
    }

    @Test
    public void given_request_for_delete_customer_when_exist_then_return_ok() throws Exception {
        saveMockClients();
        var clients = customerRepository.findAll();
        var client = clients.iterator().next();
        var auth = mockAuthentication();
        mvc.perform(delete(String.format("/api/v1/customers/%d", client.getId()))
                .header("Authorization", "Bearer " + auth.getToken()))
                .andExpect(status().isOk());
    }

    @Test
    public void given_request_for_delete_customer_when_not_exist_then_return_not_found() throws Exception {
        var auth = mockAuthentication();
        mvc.perform(delete(String.format("/api/v1/customers/100"))
                .header("Authorization", "Bearer " + auth.getToken()))
                .andExpect(status().is4xxClientError());
    }
}

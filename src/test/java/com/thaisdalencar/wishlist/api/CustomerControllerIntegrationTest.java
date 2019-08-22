package com.thaisdalencar.wishlist.api;

import com.thaisdalencar.wishlist.entity.Customer;
import com.thaisdalencar.wishlist.repository.CustomerRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CustomerRepository customerRepository;

    @After
    public void tearDown() {
        customerRepository.deleteAll();
    }

    @Test
    public void given_request_by_customers_then_returns_all() throws Exception {
        saveMockCustomers();
        mvc.perform(get("/api/v1/customers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].name", is("User A")))
                .andExpect(jsonPath("$.content[0].email", is("usera@email.com")));
    }

    private void saveMockCustomers() {
        var clientA = new Customer("User A", "usera@email.com");
        var clientB = new Customer("User B", "userb@email.com");
        customerRepository.saveAll(List.of(clientA, clientB));
    }

    @Test
    public void given_request_by_specific_client_then_return_info_about_him() throws Exception {
        saveMockCustomers();
        var customers = customerRepository.findAll();
        var client = customers.iterator().next();
        mvc.perform(get(String.format("/api/v1/customers/%d", client.getId())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name", is(client.getName())))
                .andExpect(jsonPath("$.email", is(client.getEmail())));
    }

    @Test
    public void given_request_for_delete_client_when_exist_then_return_ok() throws Exception {
        saveMockCustomers();
        var customers = customerRepository.findAll();
        var client = customers.iterator().next();
        mvc.perform(delete(String.format("/api/v1/customers/%d", client.getId())))
                .andExpect(status().isOk());
    }

    @Test
    public void given_request_for_delete_client_when_not_exist_then_return_not_found() throws Exception {
        mvc.perform(delete(String.format("/api/v1/customers/100")))
                .andExpect(status().is4xxClientError());
    }
}

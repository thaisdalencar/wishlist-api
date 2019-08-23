package com.thaisdalencar.wishlist.api;

import com.thaisdalencar.wishlist.client.Product;
import com.thaisdalencar.wishlist.client.ProductApiClient;
import com.thaisdalencar.wishlist.controller.request.JwtRequest;
import com.thaisdalencar.wishlist.controller.response.JwtResponse;
import com.thaisdalencar.wishlist.entity.Customer;
import com.thaisdalencar.wishlist.entity.WishListItem;
import com.thaisdalencar.wishlist.repository.CustomerRepository;
import com.thaisdalencar.wishlist.repository.WishListItemRepository;
import com.thaisdalencar.wishlist.security.jwt.JwtTokenUtil;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WishListControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private WishListItemRepository wishListItemRepository;

    @MockBean
    private ProductApiClient productApiClient;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @After
    public void tearDown() {
        customerRepository.deleteAll();
    }

    @Test
    public void given_request_by_customer_wish_list_then_return() throws Exception {
        var client = saveMockClient();
        var productId = "ddeb989e-53c4-e68b-aa93-6e43afddb797";
        saveClientWishList(client, productId);
        var product = mockProductApi(productId);
        var auth = mockAuthentication();
        mvc.perform(get(String.format("/api/v1/customers/%d/wish-list", client.getId()))
                .header("Authorization", "Bearer " + auth.getToken()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].id", is(product.getId())))
                .andExpect(jsonPath("$.content[0].title", is(product.getTitle())))
                .andExpect(jsonPath("$.content[0].image", is(product.getImage())))
                .andExpect(jsonPath("$.content[0].price", is(product.getPrice())))
                .andExpect(jsonPath("$.content[0].reviewScore").doesNotExist());
    }

    private Customer saveMockClient() {
        var client = new Customer("User A", "usera@email.com");
        return customerRepository.save(client);
    }

    private void saveClientWishList(Customer customer, String productId) {
        var wishListItem = new WishListItem(customer, productId);
        wishListItemRepository.save(wishListItem);

        var wishListItemA = new WishListItem(customer, "de2911eb-ce5c-e783-1ca5-82d0ccd4e3d8");
        wishListItemRepository.save(wishListItemA);
    }

    private Product mockProductApi(String productId) {
        var product = new Product(productId, "Guitarra Original Ibanez DN 520", "http://challenge-api.luizalabs.com/images/2b505fab-d865-e164-345d-efbd4c2045b6.jpg", 6309.9, null);
        when(productApiClient.getById(anyString()))
                .thenAnswer(invocation -> product);
        return product;
    }

    @Test
    public void given_request_by_specific_customer_wish_list_then_return() throws Exception {
        var client = saveMockClient();
        var productId = "2b505fab-d865-e164-345d-efbd4c2045b6";
        saveClientWishList(client, productId);
        var product = mockProductApi(productId);
        var auth = mockAuthentication();
        mvc.perform(get(String.format("/api/v1/customers/%d/wish-list/%s", client.getId(), productId))
                .header("Authorization", "Bearer " + auth.getToken()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(product.getId())))
                .andExpect(jsonPath("$.title", is(product.getTitle())))
                .andExpect(jsonPath("$.image", is(product.getImage())))
                .andExpect(jsonPath("$.price", is(product.getPrice())))
                .andExpect(jsonPath("$.reviewScore").doesNotExist());
    }

    @Test
    public void given_request_for_delete_customer_when_exist_then_return_ok() throws Exception {
        var client = saveMockClient();
        var productId = "2b505fab-d865-e164-345d-efbd4c2045b6";
        saveClientWishList(client, productId);
        var auth = mockAuthentication();
        mvc.perform(delete(String.format("/api/v1/customers/%d/wish-list/%s", client.getId(), productId))
                .header("Authorization", "Bearer " + auth.getToken()))
                .andExpect(status().isOk());
    }

    @Test
    public void given_request_for_delete_customer_when_not_exist_then_return_not_found() throws Exception {
        var auth = mockAuthentication();
        mvc.perform(delete(String.format("/api/v1/customers/100/wish-list/abc-edf"))
                .header("Authorization", "Bearer " + auth.getToken()))
                .andExpect(status().is4xxClientError());
    }

    private JwtResponse mockAuthentication() {
        var login = new JwtRequest("admin", "admin");
        return new JwtResponse(jwtTokenUtil.generateToken(login));
    }
}

package com.thaisdalencar.wishlist.api;

import com.thaisdalencar.wishlist.client.Product;
import com.thaisdalencar.wishlist.client.ProductApiClient;
import com.thaisdalencar.wishlist.entity.Client;
import com.thaisdalencar.wishlist.entity.WishListItem;
import com.thaisdalencar.wishlist.repository.ClientRepository;
import com.thaisdalencar.wishlist.repository.WishListItemRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
    private ClientRepository clientRepository;

    @Autowired
    private WishListItemRepository wishListItemRepository;

    @MockBean
    private ProductApiClient productApiClient;

    @After
    public void tearDown() {
        clientRepository.deleteAll();
    }

    @Test
    public void given_request_by_client_wish_list_then_return() throws Exception {
        var client = saveMockClient();
        var productId = "ddeb989e-53c4-e68b-aa93-6e43afddb797";
        saveClientWishList(client, productId);
        var product = mockProductApi(productId);

        mvc.perform(get(String.format("/api/v1/clients/%d/favorite-products", client.getId())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].id", is(product.getId())))
                .andExpect(jsonPath("$.content[0].title", is(product.getTitle())))
                .andExpect(jsonPath("$.content[0].image", is(product.getImage())))
                .andExpect(jsonPath("$.content[0].price", is(product.getPrice())))
                .andExpect(jsonPath("$.content[0].reviewScore").doesNotExist());
    }

    private Client saveMockClient() {
        var client = new Client("User A", "usera@email.com");
        return clientRepository.save(client);
    }

    private void saveClientWishList(Client client, String productId) {
        var wishListItem = new WishListItem(client, productId);
        wishListItemRepository.save(wishListItem);

        var wishListItemA = new WishListItem(client, "de2911eb-ce5c-e783-1ca5-82d0ccd4e3d8");
        wishListItemRepository.save(wishListItemA);
    }

    private Product mockProductApi(String productId) {
        var product = new Product(productId, "Guitarra Original Ibanez DN 520", "http://challenge-api.luizalabs.com/images/2b505fab-d865-e164-345d-efbd4c2045b6.jpg", 6309.9, null);
        when(productApiClient.getById(anyString()))
                .thenAnswer(invocation -> product);
        return product;
    }

    @Test
    public void given_request_by_specific_client_wish_list_then_return() throws Exception {
        var client = saveMockClient();
        var productId = "2b505fab-d865-e164-345d-efbd4c2045b6";
        saveClientWishList(client, productId);
        var product = mockProductApi(productId);
        mvc.perform(get(String.format("/api/v1/clients/%d/favorite-products/%s", client.getId(), productId)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(product.getId())))
                .andExpect(jsonPath("$.title", is(product.getTitle())))
                .andExpect(jsonPath("$.image", is(product.getImage())))
                .andExpect(jsonPath("$.price", is(product.getPrice())))
                .andExpect(jsonPath("$.reviewScore").doesNotExist());
    }

    @Test
    public void given_request_for_delete_client_when_exist_then_return_ok() throws Exception {
        var client = saveMockClient();
        var productId = "2b505fab-d865-e164-345d-efbd4c2045b6";
        saveClientWishList(client, productId);
        mvc.perform(delete(String.format("/api/v1/clients/%d/favorite-products/%s", client.getId(), productId)))
                .andExpect(status().isOk());
    }

    @Test
    public void given_request_for_delete_client_when_not_exist_then_return_not_found() throws Exception {
        mvc.perform(delete(String.format("/api/v1/clients/100/favorite-products/abc-edf")))
                .andExpect(status().is4xxClientError());
    }
}

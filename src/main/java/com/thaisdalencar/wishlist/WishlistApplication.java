package com.thaisdalencar.wishlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients
public class WishlistApplication {

	public static void main(String[] args) {
		SpringApplication.run(WishlistApplication.class, args);
	}

}

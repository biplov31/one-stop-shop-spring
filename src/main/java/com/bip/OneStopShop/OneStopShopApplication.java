package com.bip.OneStopShop;

import com.bip.OneStopShop.models.Product;
import com.bip.OneStopShop.models.ProductCategory;
import com.bip.OneStopShop.models.Review;
import com.bip.OneStopShop.models.User;
import com.bip.OneStopShop.repositories.ProductRepository;
import com.bip.OneStopShop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class OneStopShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneStopShopApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository users, ProductRepository products) {
		return args -> {
			AggregateReference<User, Integer> johnRef = AggregateReference.to(users.save(new User("John", "Doe", "john123", "john7@gmail.com")).getId());

			Set<Review> jacketReviews = new HashSet<>();
			Review firstReview = new Review("Great jacket!", johnRef);
			jacketReviews.add(firstReview);
			Product jacket = new Product("Dapxy Jacket", "A torquise-colored puffer jacket.", 40.12, ProductCategory.MENS_CLOTHING.toString(), jacketReviews);
			products.save(jacket);

			Product jacketReloaded = products.findById(jacket.getId()).get();
			System.out.println(jacketReloaded);
		};
	}
}

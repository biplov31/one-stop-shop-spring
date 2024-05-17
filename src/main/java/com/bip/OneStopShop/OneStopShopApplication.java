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
import org.springframework.data.relational.core.sql.In;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class OneStopShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneStopShopApplication.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner commandLineRunner(UserRepository users, ProductRepository products) {
		return args -> {
			// AggregateReference<User, Integer> jimRef = AggregateReference.to(users.save(new User("Jim", "Jones", "jim123", "jim6@gmail.com")).getId());
			// AggregateReference<User, Integer> jimRef = AggregateReference.to(users.findById(12).get().getId()); // .get() is for Optional and .getId() to get the actual ID of a record
			//
			// Set<Review> jacketReviews = new HashSet<>();
			// Review firstReview = new Review("Nice, lightweight windcheater!!", jimRef);
			// jacketReviews.add(firstReview);
			//
			// // Product jacket = new Product("Kathmandu windcheater", "A lightweight water-resistant windcheater.", 50.62, ProductCategory.MENS_CLOTHING.toString(), jacketReviews);
			// Product jacket = products.findById(8).orElse(null);
			// jacket.setReviews(jacketReviews);
			// products.save(jacket);
			//
			// Product jacketReloaded = products.findById(jacket.getId()).get();
			// System.out.println(jacketReloaded);
		};
	}
}

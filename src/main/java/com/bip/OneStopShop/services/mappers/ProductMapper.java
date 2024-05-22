package com.bip.OneStopShop.services.mappers;

import com.bip.OneStopShop.models.Product;
import com.bip.OneStopShop.models.Review;
import com.bip.OneStopShop.models.User;
import com.bip.OneStopShop.models.dtos.ProductDetailDto;
import com.bip.OneStopShop.models.dtos.ProductDto;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductMapper {

    public Product convertProductDtoToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(productDto.getCategory());
        // product.setReviews(productDto.getReviews());

        if (productDto.getReview() != null) {
            product.addReview(productDto.getReview());
        }

        // convert int value to AggregateReference of userId
        // Review review = new Review();
        // review.setContent(productDto.getReview().getContent());
        // AggregateReference<User, Integer> ref = productDto.getReview().getUserId();
        // // AggregateReference<User, Integer> userRef = AggregateReference.to(productDto.getReview().getUserId().getId());
        // review.setUserId(ref);
        // product.addReview(review);

        return product;
    }

    public ProductDto convertProductToProductDto(Product product) {
        // return new ProductDto(product.getTitle(), product.getDescription(), product.getPrice(), product.getCategory());
        ProductDto productDto = new ProductDto();
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(product.getCategory());
        productDto.setPrice(product.getPrice());

        return productDto;
    }

    public ProductDetailDto convertProductToProductDetailDto(Product product, Map<String, String> reviews) {
        ProductDetailDto productDetailDto = new ProductDetailDto();
        productDetailDto.setTitle(product.getTitle());
        productDetailDto.setDescription(product.getDescription());
        productDetailDto.setPrice(product.getPrice());
        productDetailDto.setCategory(product.getCategory());
        productDetailDto.setReviews(reviews);

        return productDetailDto;
    }
}

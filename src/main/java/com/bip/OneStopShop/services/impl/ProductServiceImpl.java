package com.bip.OneStopShop.services.impl;

import com.bip.OneStopShop.exceptions.ProductNotFoundException;
import com.bip.OneStopShop.models.Product;
import com.bip.OneStopShop.models.User;
import com.bip.OneStopShop.models.dtos.ProductDetailDto;
import com.bip.OneStopShop.models.dtos.ProductDto;
import com.bip.OneStopShop.repositories.ProductRepository;
import com.bip.OneStopShop.repositories.UserRepository;
import com.bip.OneStopShop.services.ProductService;
import com.bip.OneStopShop.services.mappers.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.userRepository = userRepository;
    }

    public List<ProductDto> findProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::convertProductToProductDto)
                .collect(Collectors.toList());
    }

    public ProductDetailDto findProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product does not exist."));

        Map<String, String> reviews = new LinkedHashMap<>();
        product.getReviews().forEach((review -> {
            User author = userRepository.findById(review.getUserId().getId()).orElse(null);
            if (author != null) {
                reviews.put(review.getContent(), author.getEmail());
            }
        }));

        ProductDetailDto productDetailDto = productMapper.convertProductToProductDetailDto(product, reviews);
        return productDetailDto;
    }

    public ProductDto saveProduct(ProductDto productDto) {
        // Review review = new Review();
        // review.setContent(productDto.getReview().getContent());
        // User user = userRepository.findById(productDto.getReview().getUserId().getId()).orElse(null);
        // AggregateReference<User, Integer> userRef = AggregateReference.to(user.getId());
        // productDto.getReview().setUserId(userRef);
        // AggregateReference<User, Integer> userRef = AggregateReference.to(productDto.getReview().getUserId().getId());
        // review.setUserId(ref);
        // productDto.setReview(review);
        Product savedProduct = productRepository.save(productMapper.convertProductDtoToProduct(productDto));
        return productDto;
    }

    public ProductDto updateProduct(Integer id, ProductDto productDto) {
        if(!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product does not exist.");
        }
        Product productToUpdate = productMapper.convertProductDtoToProduct(productDto);
        productToUpdate.setId(id);
        Product updatedProduct = productRepository.save(productToUpdate);
        return productMapper.convertProductToProductDto(updatedProduct);
    }

    public void deleteProduct(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product does not exist.");
        }
        productRepository.deleteById(id);
    }

    // public void deleteReview(Integer productId, Integer userId) {
    //
    // }
}

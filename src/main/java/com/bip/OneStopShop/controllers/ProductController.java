package com.bip.OneStopShop.controllers;

import com.bip.OneStopShop.models.Product;
import com.bip.OneStopShop.models.User;
import com.bip.OneStopShop.models.dtos.ProductDetailDto;
import com.bip.OneStopShop.models.dtos.ProductDto;
import com.bip.OneStopShop.services.ProductService;
import com.bip.OneStopShop.services.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    Logger logger = LoggerFactory.getLogger(ProductController.class);

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> products = productService.findProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ProductDetailDto getProductById(@PathVariable Integer id) {
        // Product product = productService.getProductById(id).orElse(null);
        // Set<Review> reviews = new HashSet<>();
        // if (product != null) {
        //     reviews = product.getReviews();
        // }

        return productService.findProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto postProduct(@Valid @RequestBody ProductDto productDto) {
        // logger.info(productDto.toString());
        // AggregateReference<User, Integer> userId = productDto.getReview().getUserId();
        return productService.saveProduct(productDto);
    }

    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ProductDto updateProduct(@PathVariable("productId") Integer productId, @RequestBody ProductDto productDto) {
        System.out.println(productDto);
        return productService.updateProduct(productId, productDto);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("productId") Integer id) {
        productService.deleteProduct(id);
    }
}

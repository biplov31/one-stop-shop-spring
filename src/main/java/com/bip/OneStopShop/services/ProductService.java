package com.bip.OneStopShop.services;

import com.bip.OneStopShop.models.dtos.ProductDetailDto;
import com.bip.OneStopShop.models.dtos.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> findProducts();

    ProductDetailDto findProductById(Integer id);

    ProductDto saveProduct(ProductDto productDto);

    ProductDto updateProduct(Integer id, ProductDto productDto);

    void deleteProduct(Integer id);
}

package com.ecommerce.ecommerce_Backed.IMPL;

import com.ecommerce.ecommerce_Backed.DTO.CreateProductRequest;
import com.ecommerce.ecommerce_Backed.Exception.ProductException;
import com.ecommerce.ecommerce_Backed.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductServiceImpl {
    public ResponseEntity<Product>createProduct(CreateProductRequest createProductRequest)throws Exception;
    public ResponseEntity<String>deleteProduct(Long ProductId) throws ProductException;

    public ResponseEntity<Product > productUpdate(Long id,Product product)throws ProductException;

    public Product findProductById(Long id) throws ProductException;
    public ResponseEntity<List<Product>> findProductByCategory(String category)throws ProductException;
    public ResponseEntity< Page<Product>>getAllProduct(String category, List<String>color, List<String>sizes, Integer minPrice, Integer maxPrice,
                                       Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize)throws Exception;
}

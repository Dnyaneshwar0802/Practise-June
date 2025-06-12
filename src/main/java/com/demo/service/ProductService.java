package com.demo.service;

import com.demo.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO saveProduct(ProductDTO productDTO);

    List<ProductDTO> getAll();

    ProductDTO getProductByID(long id);

    ProductDTO updateProduct(long id, ProductDTO productDTO);

    void deleteProduct(long id);
}

package com.demo.serviceimpl;

import com.demo.dto.ProductDTO;
import com.demo.exception.ProductNotFoundException;
import com.demo.exception.ProductDeletionException;
import com.demo.model.Product;
import com.demo.repository.ProductRepository;
import com.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDiscription(productDTO.getDiscription());
        product.setPrice(productDTO.getPrice());
        product.setCatagory(productDTO.getCatagory());
        product.setUnit(productDTO.getUnit());

        Product savedProduct = productRepository.save(product);

        ProductDTO savedDTO = new ProductDTO();
        savedDTO.setName(savedProduct.getName());
        savedDTO.setDiscription(savedProduct.getDiscription());
        savedDTO.setPrice(savedProduct.getPrice());
        savedDTO.setCatagory(savedProduct.getCatagory());
        savedDTO.setUnit(savedProduct.getUnit());
        return savedDTO;
    }

    @Override
    public List<ProductDTO> getAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product p : products) {
            ProductDTO dto = new ProductDTO();
            dto.setName(p.getName());
            dto.setDiscription(p.getDiscription());
            dto.setPrice(p.getPrice());
            dto.setCatagory(p.getCatagory());
            dto.setUnit(p.getUnit());
            productDTOS.add(dto);
        }
        return productDTOS;
    }

    @Override
    public ProductDTO getProductByID(long id) {
        Product product = productRepository.findByProductId(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
        ProductDTO dto = new ProductDTO();
        dto.setName(product.getName());
        dto.setDiscription(product.getDiscription());
        dto.setPrice(product.getPrice());
        dto.setCatagory(product.getCatagory());
        dto.setUnit(product.getUnit());
        return dto;
    }

    @Override
    public ProductDTO updateProduct(long id, ProductDTO productDTO) {
        Product product = productRepository.findByProductId(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));

        if (productDTO.getName() != null && !productDTO.getName().isBlank()) {
            product.setName(productDTO.getName());
        }

        if (productDTO.getCatagory() != null && !productDTO.getCatagory().isBlank()) {
            product.setCatagory(productDTO.getCatagory());
        }

        if (productDTO.getDiscription() != null && !productDTO.getDiscription().isBlank()) {
            product.setDiscription(productDTO.getDiscription());
        }

        if (productDTO.getUnit() != null && !productDTO.getUnit().isBlank()) {
            product.setUnit(productDTO.getUnit());
        }

        if (productDTO.getPrice() > 0.0) {
            product.setPrice(productDTO.getPrice());
        }

        Product updated = productRepository.save(product);
        ProductDTO response = new ProductDTO();
        response.setName(updated.getName());
        response.setDiscription(updated.getDiscription());
        response.setCatagory(updated.getCatagory());
        response.setUnit(updated.getUnit());
        response.setPrice(updated.getPrice());

        return response;
    }

    @Override
    public void deleteProduct(long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductDeletionException("Product with ID " + id + " cannot be deleted because it does not exist");
        }
        productRepository.deleteById(id);
    }
}

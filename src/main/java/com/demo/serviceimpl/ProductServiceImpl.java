package com.demo.serviceimpl;

import com.demo.dto.ProductDTO;
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
        Product product=new Product();
        product.setName(productDTO.getName());
        product.setDiscription(productDTO.getDiscription());
        product.setPrice(productDTO.getPrice());
        product.setCatagory(productDTO.getCatagory());
        product.setUnit(productDTO.getUnit());
        System.out.println(product.getProductId());
        Product savedProduct=productRepository.save(product);
        if(savedProduct.getProductId()>1){
            System.out.println("Inside IF");
        }
        return productDTO;
    }

    @Override
    public List<ProductDTO> getAll() {
    List<Product> products=productRepository.findAll();
    List<ProductDTO> productDTOS=new ArrayList<ProductDTO>();
        System.out.println("FETCHING DATA FROM  DATABASE ");
        System.out.println(products);
    if(!products.isEmpty()){
      for(int i=0;i<products.size();i++){
          productDTOS.add(new ProductDTO(products.get(i).getName(),
                  products.get(i).getDiscription(),
                  products.get(i).getPrice(),
                  products.get(i).getCatagory(),
                  products.get(i).getUnit()));
      }
      return productDTOS;
           //productDTOS.add(product);
    }
        return List.of();
    }

    @Override
    public ProductDTO getProductByID(long id) {
        Optional<Product> prod=productRepository.findByProductId(id);
        return prod.map(product -> new ProductDTO(product.getName(),
                product.getDiscription(),
                product.getPrice(),
                product.getCatagory(),
                product.getUnit())).orElse(null);
    }

    @Override
    public ProductDTO updateProduct(long id, ProductDTO productDTO) {
        Optional<Product> prod=productRepository.findByProductId(id);
        if (prod.isEmpty()) {
       return null;
        }
        Product product=prod.get();
        if (productDTO.getName() != null && !productDTO.getName().isBlank()) {
            product.setName(productDTO.getName());
        } else  product.setName(prod.get().getName());

        if (productDTO.getCatagory() != null && !productDTO.getCatagory().isBlank()) {
            product.setCatagory(productDTO.getCatagory());
        } else product.setCatagory(prod.get().getCatagory());

        if (productDTO.getDiscription() != null && !productDTO.getDiscription().isBlank()) {
            product.setDiscription(productDTO.getDiscription());
        } else product.setDiscription(prod.get().getDiscription());

        if (productDTO.getUnit() != null && !productDTO.getUnit().isBlank()) {
            product.setUnit(productDTO.getUnit());
        } else product.setUnit(prod.get().getUnit());

        if (productDTO.getPrice() > 0.0) {
            product.setPrice(productDTO.getPrice());
        } else product.setPrice(prod.get().getPrice());

        Product updated=  productRepository.save(product);
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
        productRepository.deleteById(id);
    }

}

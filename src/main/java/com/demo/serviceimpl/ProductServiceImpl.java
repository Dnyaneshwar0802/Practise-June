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
        System.out.println(product.getProduct_id());
        Product savedProduct=productRepository.save(product);
        if(savedProduct.getProduct_id()>1){
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
       Optional<Product> products= Optional.of(productRepository.getReferenceById(id));
        return products.map(product -> new ProductDTO(product.getName(),
                product.getDiscription(),
                product.getPrice(),
                product.getCatagory(),
                product.getUnit())).orElse(null);
    }
}

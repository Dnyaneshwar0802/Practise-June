package com.demo.restcontroller;

import com.demo.dto.ProductDTO;
import com.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductRestController {
    @Autowired
    private ProductService productService;
    @PostMapping("/saveProduct")
    public ProductDTO saveProduct(@RequestBody ProductDTO productDTO){
        return productService.saveProduct(productDTO);
    }
    @GetMapping("/getAllProducts")
    public List<ProductDTO> getAllProduct(){
        return productService.getAll();
    }
    @GetMapping("/getProductByID/{id}")
    public ProductDTO getProductByID(@PathVariable ("id") long id){
        System.out.println("Entered ID is "+id);
        return productService.getProductByID(id);

    }
}

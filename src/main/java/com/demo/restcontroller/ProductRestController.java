package com.demo.restcontroller;

import com.demo.dto.ProductDTO;
import com.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductRestController {
    @Autowired
    private ProductService productService;
    @PostMapping("/saveProduct")
    public ResponseEntity<ProductDTO> saveProduct(@Valid @RequestBody ProductDTO productDTO){
        System.out.println("This input from user>>"+productDTO);
        if(productDTO == null){
            return ResponseEntity.status(HttpStatusCode.valueOf(501)).build();
        }
        ProductDTO savedProductDTO=productService.saveProduct(productDTO);
        System.out.println("Returned ID >>"+savedProductDTO);
        if(savedProductDTO==null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return new ResponseEntity<>(savedProductDTO, HttpStatus.CREATED);
               // productService.saveProduct(productDTO);
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
    @PutMapping("/updateProduct/{id}")
    public ProductDTO updateByID(@PathVariable ("id") long id,@RequestBody ProductDTO productDTO){
        return productService.updateProduct(id,productDTO);
    }
    @DeleteMapping("/deleteProduct/{id}")
    public  ResponseEntity<Object> deleteProduct(@PathVariable ("id") long id){
        productService.deleteProduct(id);
        return (ResponseEntity) ResponseEntity.status(204);
    }
}

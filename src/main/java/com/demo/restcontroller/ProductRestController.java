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
    public ResponseEntity<ProductDTO> saveProduct(@Valid @RequestBody ProductDTO productDTO) {
        ProductDTO savedProductDTO = productService.saveProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProductDTO);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<ProductDTO>> getAllProduct() {
        List<ProductDTO> products = productService.getAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getProductByID/{id}")
    public ResponseEntity<ProductDTO> getProductByID(@PathVariable("id") long id) {
        ProductDTO product = productService.getProductByID(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<ProductDTO> updateByID(@PathVariable("id") long id,
                                                 @RequestBody ProductDTO productDTO) {
        ProductDTO updated = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build(); // HTTP 204
    }
}

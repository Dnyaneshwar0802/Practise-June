package com.demo.serviceimpl;

import com.demo.exception.InventoryNotFoundException;
import com.demo.model.Inventory;
import com.demo.model.Product;
import com.demo.repository.InventoryRepository;
import com.demo.repository.ProductRepository;
import com.demo.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Inventory saveQuantity(Inventory entity) {
        Optional<Inventory> optional = inventoryRepository.findById(entity.getProductId());

        if (optional.isPresent()) {
            Inventory existing = optional.get();
            existing.setAvailableQuantity(entity.getAvailableQuantity());
            existing.setLastUpdated(entity.getLastUpdated());
            return inventoryRepository.save(existing);
        }

        Product product = productRepository.findById(entity.getProductId())
                .orElseThrow(() -> new InventoryNotFoundException("Product not found to link inventory"));

        entity.setProduct(product);
        return inventoryRepository.save(entity);
    }

    @Override
    public Inventory updateQuantity(Long productId, long newQuantity) {
        Inventory inventory = inventoryRepository.findById(productId)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory not found for product ID " + productId));

        inventory.setAvailableQuantity(newQuantity);
        inventory.setLastUpdated(LocalDateTime.now());

        return inventoryRepository.save(inventory);
    }

    @Override
    public java.util.List<Inventory> getAll() {
        return inventoryRepository.findAll();
    }
}

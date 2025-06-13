package com.demo.serviceimpl;

import com.demo.model.Inventory;
import com.demo.model.Product;
import com.demo.repository.InventoryRepository;
import com.demo.repository.ProductRepository;
import com.demo.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public Inventory saveQuantity(Inventory entity) {
//Not working cause need to fetch product first
      /* Optional<Inventory> inventory= inventoryRepository.findById(entity.getProductId());
       if(inventory.isPresent()){
           inventory.get().setAvailableQuantity(entity.getAvailableQuantity());
           inventory.get().setLastUpdated(entity.getLastUpdated());
           return inventoryRepository.save(inventory.get());
       }
        return inventoryRepository.save(entity);*/
        Optional<Inventory> optional = inventoryRepository.findById(entity.getProductId());

        if (optional.isPresent()) {
            Inventory existing = optional.get();
            existing.setAvailableQuantity(entity.getAvailableQuantity());
            existing.setLastUpdated(entity.getLastUpdated());
            return inventoryRepository.save(existing);
        }

        Product product = productRepository.findById(entity.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Inventory newInventory = new Inventory();
        newInventory.setProduct(product);
        newInventory.setAvailableQuantity(entity.getAvailableQuantity());
        newInventory.setLastUpdated(entity.getLastUpdated());

        return inventoryRepository.save(newInventory);
    }

    @Override
    public Inventory updateQuantity(Long productId, long newQuantity) {
        Inventory inventory = inventoryRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Inventory not found for product ID: " + productId));

        inventory.setAvailableQuantity(newQuantity);
        inventory.setLastUpdated(LocalDateTime.now());

        return inventoryRepository.save(inventory);
    }

    @Override
    public List<Inventory> getAll() {
        return inventoryRepository.findAll();
    }
}

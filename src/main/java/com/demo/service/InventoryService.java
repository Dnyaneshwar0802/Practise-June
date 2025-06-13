package com.demo.service;

import com.demo.dto.ProductDTO;
import com.demo.model.Inventory;

import java.util.List;

public interface InventoryService {
    Inventory saveQuantity(Inventory entity);

    Inventory updateQuantity(Long productId, long newQuantity);

    List<Inventory> getAll();
}

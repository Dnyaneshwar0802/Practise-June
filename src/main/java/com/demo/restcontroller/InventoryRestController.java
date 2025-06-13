package com.demo.restcontroller;

import com.demo.dto.InventoryDTO;
import com.demo.model.Inventory;
import com.demo.service.InventoryService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryRestController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/saveQuantity")
    public ResponseEntity<InventoryDTO> saveQuantity(@RequestBody InventoryDTO inventoryDTO) {
        Inventory entity = mapper.map(inventoryDTO, Inventory.class);
        Inventory saved = inventoryService.saveQuantity(entity);
        InventoryDTO response = mapper.map(saved, InventoryDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/updateQuantity/{productId}")
    public ResponseEntity<InventoryDTO> updateQuantity(
            @PathVariable Long productId,
            @RequestBody long newQuantity) {

        Inventory updated = inventoryService.updateQuantity(productId, newQuantity);
        InventoryDTO response = mapper.map(updated, InventoryDTO.class);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/viewAll")
    public ResponseEntity<List<InventoryDTO>> getAllInventories() {
        List<Inventory> inventoryList = inventoryService.getAll();
        List<InventoryDTO> dtoList = inventoryList.stream()
                .map(inventory -> mapper.map(inventory, InventoryDTO.class))
                .toList();
        return ResponseEntity.ok(dtoList);
    }
}


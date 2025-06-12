package com.demo.dto;

import com.demo.model.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
public class InventoryDTO {
    @NotNull
    private long product_Id;
    @Min(value = 0)
    private long availableQuantity;
    @LastModifiedDate
    private LocalDateTime lastUpdated;
}

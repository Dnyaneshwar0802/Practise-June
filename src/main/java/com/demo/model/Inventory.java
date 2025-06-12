package com.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Data
public class Inventory {
    @Id
    @Column(name = "product_id")
    private Long productId;
    @Min(value = 0)
    private long availableQuantity;
    @LastModifiedDate
    private LocalDateTime lastUpdated;
    @OneToOne
    @MapsId
    @JoinColumn(name = "product_id")
    private Product product;
}

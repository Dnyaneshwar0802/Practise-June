package com.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name="product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    @NotBlank
    private String name;
    @NotBlank
    private String discription;
    @Min(value = 0)
    private double price;
    @NotBlank
    private String catagory;
    @NotBlank
    private String unit;
    @OneToOne(mappedBy = "product")
    private Inventory inventory;
}

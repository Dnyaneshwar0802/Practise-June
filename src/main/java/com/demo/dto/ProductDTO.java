package com.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductDTO {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Description is required")
    private String discription;
    @Min(value = 1, message = "Price must be greater than 0")
    private double price;
    @NotBlank(message = "Category is required")
    private String catagory;
    @NotBlank(message = "Unit is required")
    private String unit;

    public ProductDTO() {
    }

    public ProductDTO(@NotBlank String name, @NotBlank String discription, @Min(value = 0) double price, @NotBlank String catagory, @NotBlank String unit) {
        this.name=name;
        this.discription=discription;
        this.price=price;
        this.catagory=catagory;
        this.unit=unit;
    }
}

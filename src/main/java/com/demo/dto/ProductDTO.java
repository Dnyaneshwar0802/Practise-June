package com.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductDTO {
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

    public ProductDTO(@NotBlank String name, @NotBlank String discription, @Min(value = 0) double price, @NotBlank String catagory, @NotBlank String unit) {
        this.name=name;
        this.discription=discription;
        this.price=price;
        this.catagory=catagory;
        this.unit=unit;
    }
}

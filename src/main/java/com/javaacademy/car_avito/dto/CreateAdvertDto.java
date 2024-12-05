package com.javaacademy.car_avito.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAdvertDto {

    private String nameBrand;
    private String color;
    private BigDecimal price;

}

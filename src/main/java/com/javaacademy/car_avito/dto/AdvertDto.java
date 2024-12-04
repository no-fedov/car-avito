package com.javaacademy.car_avito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class AdvertDto {

    private Integer numberId;
    private String nameBrand;
    private String color;
    private BigDecimal price;

}

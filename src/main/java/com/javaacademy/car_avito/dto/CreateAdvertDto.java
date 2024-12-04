package com.javaacademy.car_avito.dto;

import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
public class CreateAdvertDto {

    @NonNull
    private String nameBrand;

    @NonNull
    private String color;

    @NonNull
    private BigDecimal price;

}

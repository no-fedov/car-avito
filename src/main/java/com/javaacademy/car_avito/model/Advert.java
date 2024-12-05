package com.javaacademy.car_avito.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@EqualsAndHashCode(of = "numberId")
public class Advert {

    private Integer numberId;
    private String nameBrand;
    private String color;
    private BigDecimal price;

}

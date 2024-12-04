package com.javaacademy.car_avito.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(of = "numberId")
public class Advert {

    private Integer numberId;

    @NonNull
    private String nameBrand;

    @NonNull
    private String color;

    @NonNull
    private BigDecimal price;

}

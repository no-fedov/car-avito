package com.javaacademy.car_avito.controller;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SearchParameter {
    BRAND("brand"),
    COLOR("color"),
    PRICE("price");

    private final String name;

    SearchParameter(String name) {
        this.name = name;
    }

    public static void checkValidParameter(String inputParameter) {
        boolean result = Arrays.stream(values()).anyMatch(param -> param.name.equals(inputParameter));
        if (!result) {
            throw new RuntimeException("Нет такого парметра для поиска");
        }
    }
}

package com.javaacademy.car_avito.service;

import com.javaacademy.car_avito.dto.AdvertDto;
import com.javaacademy.car_avito.dto.CreateAdvertDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public interface AdvertService {

    void save(CreateAdvertDto createAdvertDto);

    AdvertDto getById(Integer numberId);

    boolean deleteById(Integer numberId);

    List<AdvertDto> getAllAdvert();

    Stream<AdvertDto> getAdvertByCondition(Map<String, String> parameters);

}

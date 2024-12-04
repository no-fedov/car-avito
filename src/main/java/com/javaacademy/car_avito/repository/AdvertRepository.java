package com.javaacademy.car_avito.repository;

import com.javaacademy.car_avito.model.Advert;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface AdvertRepository {

    void save(Advert advert);

    Optional<Advert> getById(Integer numberId);

    boolean deleteById(Integer numberId);

    List<Advert> getAllAdvert();

    Stream<Advert> getAdvertByCondition(Predicate<Advert> condition);

}

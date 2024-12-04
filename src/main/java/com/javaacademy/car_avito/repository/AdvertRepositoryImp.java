package com.javaacademy.car_avito.repository;

import com.javaacademy.car_avito.database.AdvertDatabase;
import com.javaacademy.car_avito.model.Advert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdvertRepositoryImp implements AdvertRepository {
    private final AdvertDatabase advertDatabase;

    @Override
    public void save(Advert advert) {
        advertDatabase.save(advert);
        log.info("New 'advert' has been successfully saved: {}", advert);
    }

    @Override
    public Optional<Advert> getById(Integer numberId) {
        log.info("Search 'advert' by id = {}", numberId);
        return advertDatabase.getById(numberId);
    }

    @Override
    public boolean deleteById(Integer numberId) {
        return advertDatabase.deleteById(numberId);
    }

    @Override
    public List<Advert> getAllAdvert() {
        return advertDatabase.getAllAdvert();
    }

    @Override
    public Stream<Advert> getAdvertByCondition(Predicate<Advert> condition) {
        return advertDatabase.getAdvertByCondition(condition);
    }

}

package com.javaacademy.car_avito.database;

import com.javaacademy.car_avito.model.Advert;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Component
public class AdvertDatabase {

    private final Map<Integer, Advert> storage = new HashMap<>();
    private int generatorId = 0;

    public void save(Advert advert) {
        advert.setNumberId(++generatorId);
        storage.put(generatorId, advert);
    }

    public Optional<Advert> getById(Integer numberId) {
        return Optional.ofNullable(storage.get(numberId));
    }

    public boolean deleteById(Integer numberId) {
        return Objects.nonNull(storage.remove(numberId));
    }

    public List<Advert> getAllAdvert() {
        return storage.values().stream().toList();
    }

    public Stream<Advert> getAdvertByCondition(Predicate<Advert> condition) {
        return storage.values().stream().filter(condition);
    }

}

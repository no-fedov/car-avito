package com.javaacademy.car_avito.service;

import com.javaacademy.car_avito.controller.SearchParameter;
import com.javaacademy.car_avito.dto.AdvertDto;
import com.javaacademy.car_avito.dto.AdvertMapper;
import com.javaacademy.car_avito.dto.CreateAdvertDto;
import com.javaacademy.car_avito.model.Advert;
import com.javaacademy.car_avito.repository.AdvertRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdvertServiceImp implements AdvertService {

    private final AdvertRepository advertRepository;
    private final AdvertMapper advertMapper;
    private final Map<String, Function<String, Predicate<Advert>>> conditionsForSearch = new HashMap<>();

    public void save(CreateAdvertDto advertDto) {
        log.info("Create new 'advert' from dto: {}", advertDto);
        Advert newAdvert = advertMapper.convertFromCreateAdvertDtoToAdvert(advertDto);
        advertRepository.save(newAdvert);
    }

    public AdvertDto getById(Integer numberId) {
        Advert advert = advertRepository.getById(numberId)
                .orElseThrow(() -> {
                    log.warn("Not found advert with id = {}", numberId);
                    return new RuntimeException("Not Found Entity");
                });
        return advertMapper.convertFromCreateAdvertDtoToAdvertDto(advert);
    }

    public boolean deleteById(Integer numberId) {
        log.info("Delete 'advert' wit id = {}", numberId);
        return advertRepository.deleteById(numberId);
    }

    public List<AdvertDto> getAllAdvert() {
        return advertMapper.convertFromCreateAdvertDtoToAdvertDto(advertRepository.getAllAdvert());
    }

    public Stream<AdvertDto> getAdvertByCondition(Map<String, String> parameters) {
        Optional<Predicate<Advert>> condition = prepareConditionSearch(parameters);
        if (condition.isEmpty()) {
            return Stream.empty();
        }
        return advertRepository.getAdvertByCondition(condition.get())
                .map(advertMapper::convertFromCreateAdvertDtoToAdvertDto);
    }

    private Optional<Predicate<Advert>> prepareConditionSearch(Map<String, String> requestParameters) {
        return requestParameters.entrySet().stream()
                .map(entry -> conditionsForSearch.get(entry.getKey()).apply(entry.getValue()))
                .reduce(Predicate::and);
    }

    @PostConstruct
    private void init() {
        conditionsForSearch.put(SearchParameter.BRAND.getName(),
                condition -> {
                    return advert -> Objects.equals(
                            advert.getNameBrand() == null ? "null" : advert.getNameBrand().toLowerCase(),
                            condition.toLowerCase()
                    );
                });
        conditionsForSearch.put(SearchParameter.COLOR.getName(),
                condition -> {
                    return advert -> Objects.equals(
                            advert.getColor() == null ? "null" : advert.getColor().toLowerCase(),
                            condition.toLowerCase()
                    );
                });
        conditionsForSearch.put(SearchParameter.PRICE.getName(),
                condition -> {
                    return advert -> Objects.equals(
                            advert.getPrice() == null ? "null" : advert.getPrice(),
                            new BigDecimal(condition));
                });
    }

}

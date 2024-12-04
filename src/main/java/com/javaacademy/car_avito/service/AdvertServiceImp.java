package com.javaacademy.car_avito.service;

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
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static com.javaacademy.car_avito.dto.AdvertMapper.convertFromCreateAdvertDtoToAdvert;
import static com.javaacademy.car_avito.dto.AdvertMapper.convertFromCreateAdvertDtoToAdvertDto;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdvertServiceImp implements AdvertService {
    private static final String BRAND_PARAM = "brand_name";
    private static final String COLOR_PARAM = "color";
    private static final String PRICE_PARAM = "price";

    private final AdvertRepository advertRepository;
    private final Map<String, Function<String, Predicate<Advert>>> conditionsForSearch = new HashMap<>();

    public void save(CreateAdvertDto advertDto) {
        log.info("Create new 'advert' from dto: {}", advertDto);
        Advert newAdvert = convertFromCreateAdvertDtoToAdvert(advertDto);
        advertRepository.save(newAdvert);
    }

    public AdvertDto getById(Integer numberId) {
        Advert advert = advertRepository.getById(numberId)
                .orElseThrow(() -> new RuntimeException("Not Found Entity"));
        return convertFromCreateAdvertDtoToAdvertDto(advert);
    }

    public boolean deleteById(Integer numberId) {
        return advertRepository.deleteById(numberId);
    }

    public List<AdvertDto> getAllAdvert() {
        return convertFromCreateAdvertDtoToAdvertDto(advertRepository.getAllAdvert());
    }

    public Stream<AdvertDto> getAdvertByCondition(Map<String, String> parameters) {
        return advertRepository.getAdvertByCondition(prepareConditionSearch(parameters))
                .map(AdvertMapper::convertFromCreateAdvertDtoToAdvertDto);
    }

    private Predicate<Advert> prepareConditionSearch(Map<String, String> requestParameters) {
        return requestParameters.entrySet().stream()
                .map(entry -> conditionsForSearch.get(entry.getKey()).apply(entry.getValue()))
                .reduce(Predicate::and).get();
    }

    @PostConstruct
    private void init() {
        conditionsForSearch.put(BRAND_PARAM,
                condition -> {
                    return advert -> Objects.equals(advert.getNameBrand(), condition);
                });
        conditionsForSearch.put(COLOR_PARAM,
                condition -> {
                    return advert -> Objects.equals(advert.getColor(), condition);
                });
        conditionsForSearch.put(PRICE_PARAM,
                condition -> {
                    return advert -> Objects.equals(advert.getPrice(), new BigDecimal(condition));
                });
    }

}

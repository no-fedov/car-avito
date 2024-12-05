package com.javaacademy.car_avito.controller;

import com.javaacademy.car_avito.dto.AdvertDto;
import com.javaacademy.car_avito.dto.CreateAdvertDto;
import com.javaacademy.car_avito.service.AdvertService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.javaacademy.car_avito.controller.AdvertControllerURLParameter.BRAND_PARAM;
import static com.javaacademy.car_avito.controller.AdvertControllerURLParameter.COLOR_PARAM;
import static com.javaacademy.car_avito.controller.AdvertControllerURLParameter.PRICE_PARAM;
import static com.javaacademy.car_avito.util.RequestParameterUtil.getParametersAsString;

@Slf4j
@RestController
@RequestMapping("/advert")
@RequiredArgsConstructor
public class AdvertController {

    private final AdvertService advertService;
    private final Set<String> urlParameters = new HashSet<>();

    @PostMapping
    public void save(@RequestBody CreateAdvertDto createAdvertDto) {
        log.info("POST: /advert , object: {}", createAdvertDto);
        advertService.save(createAdvertDto);
    }

    @GetMapping("/{id}")
    public AdvertDto getById(@PathVariable Integer id) {
        log.info("GET: /advert/{}", id);
        return advertService.getById(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Integer id) {
        log.info("DELETE: /advert/{}", id);
        return advertService.deleteById(id);
    }

    @GetMapping
    public List<AdvertDto> searchAdvertByCondition(@RequestParam Map<String, String> requestParameters) {
        for (String urlParameter : requestParameters.keySet()) {
            if (!urlParameters.contains(urlParameter)) {
                throw new RuntimeException("Unknown url parameter: %s".formatted(urlParameter));
            }
        }
        if (requestParameters.isEmpty()) {
            log.info("GET: /advert");
            return advertService.getAllAdvert();
        }
        log.info("GET: /advert?{}", getParametersAsString(requestParameters));
        return advertService.getAdvertByCondition(requestParameters).toList();
    }

    @PostConstruct
    private void init() {
        urlParameters.add(BRAND_PARAM);
        urlParameters.add(COLOR_PARAM);
        urlParameters.add(PRICE_PARAM);
    }

}

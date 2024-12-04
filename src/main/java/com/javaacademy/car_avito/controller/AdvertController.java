package com.javaacademy.car_avito.controller;

import com.javaacademy.car_avito.dto.AdvertDto;
import com.javaacademy.car_avito.dto.CreateAdvertDto;
import com.javaacademy.car_avito.service.AdvertService;
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

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/advert")
@RequiredArgsConstructor
public class AdvertController {

    private final AdvertService advertService;

    @PostMapping
    public void save(@RequestBody CreateAdvertDto createAdvertDto) {
        log.info("POST: /advert , object: {}", createAdvertDto);
        advertService.save(createAdvertDto);
    }

    @GetMapping("/{id}")
    public AdvertDto getById(@PathVariable Integer id) {
        return advertService.getById(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Integer id) {
        return advertService.deleteById(id);
    }

    @GetMapping
    public List<AdvertDto> searchAdvertByCondition(@RequestParam Map<String, String> requestParameters) {
        if (requestParameters.isEmpty()) {
            return advertService.getAllAdvert();
        }
        return advertService.getAdvertByCondition(requestParameters).toList();
    }

}

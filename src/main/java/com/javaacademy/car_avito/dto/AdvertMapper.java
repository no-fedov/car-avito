package com.javaacademy.car_avito.dto;

import com.javaacademy.car_avito.model.Advert;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdvertMapper {

    public Advert convertFromCreateAdvertDtoToAdvert(CreateAdvertDto advertDto) {
        return new Advert(null,
                advertDto.getNameBrand(),
                advertDto.getColor(),
                advertDto.getPrice()
        );
    }

    public AdvertDto convertFromCreateAdvertDtoToAdvertDto(Advert advert) {
        return new AdvertDto(advert.getNumberId(),
                advert.getNameBrand(),
                advert.getColor(),
                advert.getPrice());
    }

    public List<AdvertDto> convertFromCreateAdvertDtoToAdvertDto(List<Advert> advertDtoList) {
        return advertDtoList.stream()
                .map(this::convertFromCreateAdvertDtoToAdvertDto)
                .toList();
    }

}

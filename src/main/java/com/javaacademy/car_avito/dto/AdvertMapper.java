package com.javaacademy.car_avito.dto;

import com.javaacademy.car_avito.model.Advert;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class AdvertMapper {

    public static Advert convertFromCreateAdvertDtoToAdvert(CreateAdvertDto advertDto) {
        return new Advert(
                advertDto.getNameBrand(),
                advertDto.getColor(),
                advertDto.getPrice()
        );
    }

    public static AdvertDto convertFromCreateAdvertDtoToAdvertDto(Advert advert) {
        return new AdvertDto(advert.getNumberId(),
                advert.getNameBrand(),
                advert.getColor(),
                advert.getPrice());
    }

    public static List<AdvertDto> convertFromCreateAdvertDtoToAdvertDto(List<Advert> advertDtoList) {
        return advertDtoList.stream()
                .map(AdvertMapper::convertFromCreateAdvertDtoToAdvertDto)
                .toList();
    }

}

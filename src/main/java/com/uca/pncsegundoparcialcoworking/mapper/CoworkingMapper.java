package com.uca.pncsegundoparcialcoworking.mapper;

import com.uca.pncsegundoparcialcoworking.domain.entity.Coworking;
import com.uca.pncsegundoparcialcoworking.dto.request.CreateSpaceRequest;
import com.uca.pncsegundoparcialcoworking.dto.request.UpdateSpaceRequest;
import com.uca.pncsegundoparcialcoworking.dto.response.SpaceResponse;
import org.springframework.stereotype.Component;

@Component
public class CoworkingMapper {

    public Coworking toEntity(CreateSpaceRequest request) {
        return Coworking.builder()
                .name(request.getName())
                .description(request.getDescription())
                .type(request.getType())
                .capacity(request.getCapacity())
                .pricePerHour(request.getPricePerHour())
                .available(request.getAvailable())
                .floor(request.getFloor())
                .amenities(request.getAmenities())
                .build();
    }

    public SpaceResponse toDto(Coworking coworking) {
        return SpaceResponse.builder()
                .id(coworking.getId())
                .name(coworking.getName())
                .description(coworking.getDescription())
                .type(coworking.getType())
                .capacity(coworking.getCapacity())
                .pricePerHour(coworking.getPricePerHour())
                .available(coworking.getAvailable())
                .floor(coworking.getFloor())
                .amenities(coworking.getAmenities())
                .build();
    }
}
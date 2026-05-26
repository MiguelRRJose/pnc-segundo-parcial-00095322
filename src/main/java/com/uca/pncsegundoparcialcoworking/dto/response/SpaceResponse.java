package com.uca.pncsegundoparcialcoworking.dto.response;

import com.uca.pncsegundoparcialcoworking.domain.entity.SpaceType;
import lombok.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceResponse {
    private Long id;
    private String name;
    private String description;
    private SpaceType type;
    private Integer capacity;
    private BigDecimal pricePerHour;
    private Boolean available;
    private Integer floor;
    private String amenities;
}
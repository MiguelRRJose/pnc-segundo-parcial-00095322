package com.uca.pncsegundoparcialcoworking.dto.request;

import com.uca.pncsegundoparcialcoworking.domain.entity.SpaceType;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSpaceRequest {

    private String name;
    private String description;
    private SpaceType type;

    @Min(value = 1, message = "Capacity must be at least 1.")
    private Integer capacity;

    @DecimalMin(value = "0.01", message = "Price must be greater than 0.")
    private BigDecimal pricePerHour;

    private Boolean available;

    @Min(value = 0, message = "Floor must be 0 or greater.")
    private Integer floor;

    private String amenities;
}
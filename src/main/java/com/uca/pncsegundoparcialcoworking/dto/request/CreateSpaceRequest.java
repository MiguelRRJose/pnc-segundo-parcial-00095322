package com.uca.pncsegundoparcialcoworking.dto.request;

import com.uca.pncsegundoparcialcoworking.domain.entity.SpaceType;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSpaceRequest {

    @NotBlank(message = "Name is required.")
    private String name;

    private String description;

    @NotNull(message = "Type is required.")
    private SpaceType type;

    @NotNull(message = "Capacity is required.")
    @Min(value = 1, message = "Capacity must be at least 1.")
    private Integer capacity;

    @NotNull(message = "Price per hour is required.")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0.")
    private BigDecimal pricePerHour;

    @NotNull(message = "Available is required.")
    private Boolean available;

    @NotNull(message = "Floor is required.")
    @Min(value = 0, message = "Floor must be 0 or greater.")
    private Integer floor;

    private String amenities;
}
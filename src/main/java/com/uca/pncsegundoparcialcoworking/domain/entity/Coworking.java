package com.uca.pncsegundoparcialcoworking.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "spaces")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coworking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private SpaceType type;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "pricePerHour", nullable = false)
    private BigDecimal pricePerHour;

    @Column(name = "available", nullable = false)
    private Boolean available;

    @Column(name = "floor", nullable = false)
    private Integer floor;

    @Column(name = "amenities")
    private String amenities;
}

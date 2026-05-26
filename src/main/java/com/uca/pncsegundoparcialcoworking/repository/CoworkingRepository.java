package com.uca.pncsegundoparcialcoworking.repository;

import com.uca.pncsegundoparcialcoworking.domain.entity.Coworking;
import com.uca.pncsegundoparcialcoworking.domain.entity.SpaceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoworkingRepository extends JpaRepository<Coworking, Long> {
    boolean existsByNameIgnoreCase(String name);
    Optional<Coworking> findByNameIgnoreCase(String name);
    List<Coworking> findByType(SpaceType type);
    List<Coworking> findByAvailable(Boolean available);
    List<Coworking> findByTypeAndAvailable(SpaceType type, Boolean available);
}
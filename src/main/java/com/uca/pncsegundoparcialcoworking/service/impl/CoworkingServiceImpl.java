package com.uca.pncsegundoparcialcoworking.service.impl;

import com.uca.pncsegundoparcialcoworking.domain.entity.Coworking;
import com.uca.pncsegundoparcialcoworking.domain.entity.SpaceType;
import com.uca.pncsegundoparcialcoworking.dto.request.CreateSpaceRequest;
import com.uca.pncsegundoparcialcoworking.dto.request.UpdateSpaceRequest;
import com.uca.pncsegundoparcialcoworking.dto.response.SpaceResponse;
import com.uca.pncsegundoparcialcoworking.exceptions.BusinessRuleException;
import com.uca.pncsegundoparcialcoworking.exceptions.ResourceNotFoundException;
import com.uca.pncsegundoparcialcoworking.mapper.CoworkingMapper;
import com.uca.pncsegundoparcialcoworking.repository.CoworkingRepository;
import com.uca.pncsegundoparcialcoworking.service.CoworkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoworkingServiceImpl implements CoworkingService {

    private final CoworkingRepository coworkingRepository;
    private final CoworkingMapper coworkingMapper;

    @Override
    @Transactional
    public SpaceResponse createSpace(CreateSpaceRequest request) {
        if (coworkingRepository.existsByNameIgnoreCase(request.getName()))
            throw new BusinessRuleException("A space with the name '" + request.getName() + "' already exists.");

        return coworkingMapper.toDto(
                coworkingRepository.save(coworkingMapper.toEntity(request))
        );
    }

    @Override
    public List<SpaceResponse> getAllSpaces(SpaceType type, Boolean available) {
        List<Coworking> spaces;

        if (type != null && available != null)
            spaces = coworkingRepository.findByTypeAndAvailable(type, available);
        else if (type != null)
            spaces = coworkingRepository.findByType(type);
        else if (available != null)
            spaces = coworkingRepository.findByAvailable(available);
        else
            spaces = coworkingRepository.findAll();

        if (spaces.isEmpty())
            throw new ResourceNotFoundException("No spaces found.");

        return spaces.stream().map(coworkingMapper::toDto).toList();
    }

    @Override
    public SpaceResponse getSpaceById(Long id) {
        return coworkingMapper.toDto(
                coworkingRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Space not found with id: " + id))
        );
    }

    @Override
    @Transactional
    public SpaceResponse updateSpace(Long id, UpdateSpaceRequest request) {
        Coworking existing = coworkingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Space not found with id: " + id));

        if (request.getName() != null && !request.getName().equalsIgnoreCase(existing.getName()))
            if (coworkingRepository.existsByNameIgnoreCase(request.getName()))
                throw new BusinessRuleException("A space with the name '" + request.getName() + "' already exists.");

        if (request.getPricePerHour() != null && request.getPricePerHour().doubleValue() <= 0)
            throw new BusinessRuleException("Price per hour must be greater than 0.");

        if (request.getName() != null) existing.setName(request.getName());
        if (request.getDescription() != null) existing.setDescription(request.getDescription());
        if (request.getType() != null) existing.setType(request.getType());
        if (request.getCapacity() != null) existing.setCapacity(request.getCapacity());
        if (request.getPricePerHour() != null) existing.setPricePerHour(request.getPricePerHour());
        if (request.getAvailable() != null) existing.setAvailable(request.getAvailable());
        if (request.getFloor() != null) existing.setFloor(request.getFloor());
        if (request.getAmenities() != null) existing.setAmenities(request.getAmenities());

        return coworkingMapper.toDto(coworkingRepository.save(existing));
    }

    @Override
    @Transactional
    public void deleteSpace(Long id) {
        Coworking existing = coworkingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Space not found with id: " + id));

        if (!existing.getAvailable())
            throw new BusinessRuleException("Cannot delete a space that is unavailable or currently in use.");

        coworkingRepository.deleteById(id);
    }
}
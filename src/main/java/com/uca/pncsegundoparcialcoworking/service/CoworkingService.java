package com.uca.pncsegundoparcialcoworking.service;

import com.uca.pncsegundoparcialcoworking.domain.entity.SpaceType;
import com.uca.pncsegundoparcialcoworking.dto.request.CreateSpaceRequest;
import com.uca.pncsegundoparcialcoworking.dto.request.UpdateSpaceRequest;
import com.uca.pncsegundoparcialcoworking.dto.response.SpaceResponse;

import java.util.List;

public interface CoworkingService {
    SpaceResponse createSpace(CreateSpaceRequest request);
    List<SpaceResponse> getAllSpaces(SpaceType type, Boolean available);
    SpaceResponse getSpaceById(Long id);
    SpaceResponse updateSpace(Long id, UpdateSpaceRequest request);
    void deleteSpace(Long id);
}
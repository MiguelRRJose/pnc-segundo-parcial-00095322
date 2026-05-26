package com.uca.pncsegundoparcialcoworking.controllers;

import com.uca.pncsegundoparcialcoworking.domain.entity.SpaceType;
import com.uca.pncsegundoparcialcoworking.dto.request.CreateSpaceRequest;
import com.uca.pncsegundoparcialcoworking.dto.request.UpdateSpaceRequest;
import com.uca.pncsegundoparcialcoworking.dto.response.GeneralResponse;
import com.uca.pncsegundoparcialcoworking.dto.response.SpaceResponse;
import com.uca.pncsegundoparcialcoworking.service.impl.CoworkingServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/spaces")
@RequiredArgsConstructor
public class CoworkingController {

    private final CoworkingServiceImpl coworkingService;

    private ResponseEntity<GeneralResponse> buildResponse(Object data, String message, HttpStatus status, HttpServletRequest request) {
        return ResponseEntity.status(status).body(
                GeneralResponse.builder()
                        .data(data)
                        .message(message)
                        .status(status.value())
                        .timestamp(LocalDateTime.now())
                        .path(request.getRequestURI())
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<GeneralResponse> createSpace(
            @Valid @RequestBody CreateSpaceRequest request,
            HttpServletRequest httpRequest) {
        SpaceResponse response = coworkingService.createSpace(request);
        return buildResponse(response, "Space registered successfully.", HttpStatus.CREATED, httpRequest);
    }

    @GetMapping
    public ResponseEntity<GeneralResponse> getAllSpaces(
            @RequestParam(required = false) SpaceType type,
            @RequestParam(required = false) Boolean available,
            HttpServletRequest httpRequest) {
        List<SpaceResponse> response = coworkingService.getAllSpaces(type, available);
        return buildResponse(response, "Spaces retrieved successfully.", HttpStatus.OK, httpRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse> getSpaceById(
            @PathVariable Long id,
            HttpServletRequest httpRequest) {
        SpaceResponse response = coworkingService.getSpaceById(id);
        return buildResponse(response, "Space found.", HttpStatus.OK, httpRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponse> updateSpace(
            @PathVariable Long id,
            @Valid @RequestBody UpdateSpaceRequest request,
            HttpServletRequest httpRequest) {
        SpaceResponse response = coworkingService.updateSpace(id, request);
        return buildResponse(response, "Space updated successfully.", HttpStatus.OK, httpRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse> deleteSpace(
            @PathVariable Long id,
            HttpServletRequest httpRequest) {
        coworkingService.deleteSpace(id);
        return buildResponse(null, "Space deleted successfully.", HttpStatus.OK, httpRequest);
    }
}
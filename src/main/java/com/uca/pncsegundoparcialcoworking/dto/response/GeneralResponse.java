package com.uca.pncsegundoparcialcoworking.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralResponse {
    private String message;
    private int status;
    private LocalDateTime timestamp;
    private String path;
    private Object data;
}
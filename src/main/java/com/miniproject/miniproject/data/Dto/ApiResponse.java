package com.miniproject.miniproject.data.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private LocalDateTime timeStamp;
    private boolean isSuccessful;
}

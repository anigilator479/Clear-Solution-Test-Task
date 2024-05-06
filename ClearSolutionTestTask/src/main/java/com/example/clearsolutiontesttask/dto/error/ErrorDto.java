package com.example.clearsolutiontesttask.dto.error;

import java.time.LocalDateTime;

public record ErrorDto(
        LocalDateTime timestamp,
        Object errorPayload
) {
}

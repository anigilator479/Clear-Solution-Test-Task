package com.example.clearsolutiontesttask.dto.user;

import java.time.LocalDate;

public record DateRangeDto(
        LocalDate startDate,
        LocalDate endDate
) {
}

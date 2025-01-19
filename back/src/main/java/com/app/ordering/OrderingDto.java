package com.app.ordering;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record OrderingDto(
        Long id,

        @Size(min = 1, max = 255, message = "date is required length 1-255")
        @NotEmpty(message = "date is required")
        String date,
        @Min(value = 0, message = "price is required min 0.01")
        @Max(value = 1000000, message = "price is required max 1000000")
        float price,

        Long tourId,
        String tourName,
        String tourImg,

        String managerUsername

) {
}

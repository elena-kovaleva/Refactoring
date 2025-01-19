package com.app.client;

import com.app.ordering.OrderingDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ClientDto(
        Long id,

        @Size(min = 1, max = 255, message = "fio is required length 1-255")
        @NotEmpty(message = "fio is required")
        String fio,
        @Size(min = 1, max = 255, message = "tel is required length 1-255")
        @NotEmpty(message = "tel is required")
        String tel,
        String passport,
        String date,
        @Size(min = 1, max = 5000, message = "description is required length 1-5000")
        @NotEmpty(message = "description is required")
        String description,

        String category,
        String categoryName,

        String gender,
        String genderName,

        String tourType,
        String tourTypeName,

        String managerUsername,

        List<OrderingDto> orderings
) {
}

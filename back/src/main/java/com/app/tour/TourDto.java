package com.app.tour;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record TourDto(
        Long id,

        @Size(min = 1, max = 255, message = "name is required length 1-255")
        @NotEmpty(message = "name is required")
        String name,
        @Size(min = 1, max = 255, message = "country is required length 1-255")
        @NotEmpty(message = "country is required")
        String country,
        @Size(min = 1, max = 5000, message = "description is required length 1-5000")
        @NotEmpty(message = "description is required")
        String description,

        String type,
        String typeName,

        String season,
        String seasonName,

        String img,
        String file
) {
}

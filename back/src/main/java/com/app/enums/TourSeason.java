package com.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TourSeason {
    WINTER("Зимний"),
    SPRING("Весенний"),
    SUMMER("Летний"),
    AUTUMN("Осенний"),
    ;

    private final String name;
}


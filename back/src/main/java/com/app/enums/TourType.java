package com.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@Getter
public enum TourType {
    ACTIVE("Активный"),
    BEACH("Пляжный"),
    SPORT("Спортивный"),
    ;

    private final String name;
}


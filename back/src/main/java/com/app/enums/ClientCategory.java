package com.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ClientCategory {
    C18_20("18-20"),
    C21_25("21-25"),
    C26_35("26-35"),
    C36_50("36-50"),
    C50("50+"),
    ;

    private final String name;
}


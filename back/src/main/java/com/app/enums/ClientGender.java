package com.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ClientGender {
    MAN("Мужчина"),
    WOMAN("Женщина"),
    ;

    private final String name;
}


package com.mcb.commons.enums;

import lombok.Getter;

@Getter
public enum Category {
    APARTMENT(25000, "Apartment"),
    PBWM_HOUSING(25010, "PBWM Housing");
    private final int code;
    private final String description;

    Category(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}


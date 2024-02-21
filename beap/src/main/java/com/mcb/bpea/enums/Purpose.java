package com.mcb.bpea.enums;

public enum Purpose {
    REPARATION(1, "Reparation"),
    INHERITANCE(2, "Inheritance"),
    CONSTRUCTION(3, "Construction");

    private final int code;
    private final String description;

    Purpose(int code, String description) {
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

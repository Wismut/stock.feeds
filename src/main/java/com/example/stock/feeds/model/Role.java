package com.example.stock.feeds.model;

import lombok.Getter;
import org.springframework.lang.NonNull;

@Getter
public enum Role {
    USER("USER"),
    ADMIN("ADMIN");

    private final String value;

    Role(@NonNull String value) {
        this.value = value;
    }
}

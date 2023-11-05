package com.example.stock.feeds.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Long id;
    private String username;
    private String password;
    private String apiKey;
    private Role role;
}

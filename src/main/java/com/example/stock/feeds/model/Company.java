package com.example.stock.feeds.model;

import lombok.Builder;

@Builder
public record Company(Integer id, String name, String symbol) {

}

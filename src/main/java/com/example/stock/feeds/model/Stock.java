package com.example.stock.feeds.model;

import lombok.Builder;

import java.time.Instant;

@Builder
public record Stock(Integer id, float price, Integer companyId, Instant timeUpdate) {

}

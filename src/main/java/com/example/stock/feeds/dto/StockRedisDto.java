package com.example.stock.feeds.dto;

import lombok.Data;

@Data
public class StockRedisDto {
    private String symbol;
    private float price;
    private Integer companyId;
}

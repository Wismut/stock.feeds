package com.example.stock.feeds.service;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CompanyStockService {
    private final Map<String, String> stockCodeByCompanyName = new ConcurrentHashMap<>();

    public String getByCompanyName(@NonNull String companyName) {
        return stockCodeByCompanyName.get(companyName);
    }

    public void put(@NonNull String key, @NonNull String value) {
        stockCodeByCompanyName.put(key, value);
    }
}

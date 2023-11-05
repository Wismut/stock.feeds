package com.example.stock.feeds.service;

import com.example.stock.feeds.dto.StockRedisDto;
import com.example.stock.feeds.mapper.StockMapper;
import com.example.stock.feeds.model.Stock;
import com.example.stock.feeds.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;

@XSlf4j
@Service
@RequiredArgsConstructor
public class StockService {
    private final ReactiveRedisTemplate<String, StockRedisDto> reactiveRedisStockTemplate;
    private final StockRepository repository;
    private final StockMapper mapper;
    public static final String KEY = "stock:";

    public Mono<StockRedisDto> findByCode(@NonNull String code) {
        var key = KEY + code;
        return reactiveRedisStockTemplate.opsForValue().get(key);
    }

    public Flux<StockRedisDto> findAll() {
        return reactiveRedisStockTemplate.keys(KEY + "*")
                .flatMap(key -> reactiveRedisStockTemplate.opsForValue().get(key));
    }

    public Mono<Stock> save(@NonNull Stock stock) {
        repository.save(stock);
        return reactiveRedisStockTemplate.opsForValue().set(KEY + stock.companyId(), mapper.toDto(stock), Duration.ofSeconds(90)).thenReturn(stock);
    }

    public Mono<Boolean> deleteById(@NonNull Long id) {
        return reactiveRedisStockTemplate.opsForValue().delete(KEY + id);
    }

    public Mono<Boolean> saveAll(@NonNull Map<String, StockRedisDto> stockByKey) {
        return reactiveRedisStockTemplate.opsForValue().multiSet(stockByKey);
    }

    public Mono<Long> deleteAll() {
        return reactiveRedisStockTemplate.delete(reactiveRedisStockTemplate.keys(KEY + "*"));
    }
}

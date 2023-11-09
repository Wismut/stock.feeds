package com.example.stock.feeds.service;

import com.example.stock.feeds.dto.StockRedisDto;
import com.example.stock.feeds.mapper.StockMapper;
import com.example.stock.feeds.model.Stock;
import com.example.stock.feeds.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static java.util.Objects.isNull;
import static reactor.core.publisher.Mono.empty;

@XSlf4j
@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository repository;
    private final StockMapper mapper;

    public Mono<Stock> save(@NonNull Stock stock) {
        return repository.save(stock);
    }

    public Mono<StockRedisDto> findBySymbol(@NonNull String symbol) {
        var stock = repository.findBySymbol(symbol);
        if (isNull(stock)) {
            return empty();
        } else {
            return Mono.just(mapper.toDto(stock.block()));
        }
    }
}

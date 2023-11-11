package com.example.stock.feeds.service;

import com.example.stock.feeds.mapper.StockMapper;
import com.example.stock.feeds.model.Stock;
import com.example.stock.feeds.repository.CompanyRepository;
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
    private final StockMapper mapper;
    private final StockRepository stockRepository;
    private final CompanyRepository companyRepository;

    public Mono<Stock> save(@NonNull Stock stock) {
        return stockRepository.save(stock);
    }

    public Mono<Stock> findBySymbol(@NonNull String symbol) {
        var companyMono = companyRepository.findBySymbol(symbol);
        if (isNull(companyMono)) {
            return empty();
        } else {
            return Mono.just(stockRepository.findByCompanyId(companyMono.block().id()).sort((o1, o2) -> o1.timeUpdate().isAfter(o2.timeUpdate()) ? 1 : -1).blockFirst());
        }
    }
}

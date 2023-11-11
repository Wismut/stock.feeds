package com.example.stock.feeds.repository;

import com.example.stock.feeds.model.Stock;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface StockRepository extends R2dbcRepository<Stock, Integer> {

    Flux<Stock> findByCompanyId(Integer id);
}

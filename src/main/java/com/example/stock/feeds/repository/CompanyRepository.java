package com.example.stock.feeds.repository;

import com.example.stock.feeds.model.Company;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CompanyRepository extends R2dbcRepository<Company, Integer> {
    Mono<Company> findBySymbol(@NonNull String symbol);
}

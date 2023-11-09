package com.example.stock.feeds.service;

import com.example.stock.feeds.model.Company;
import com.example.stock.feeds.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository repository;

    public Flux<Company> findAll() {
        return repository.findAll();
    }

    public Mono<Company> save(@NonNull Company company) {
        return repository.save(company);
    }
}

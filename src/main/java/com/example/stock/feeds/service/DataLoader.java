package com.example.stock.feeds.service;

import com.example.stock.feeds.model.Company;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@XSlf4j
@Component
@RequiredArgsConstructor
public class DataLoader {
    private final CompanyService companyService;
    private final CompanyStockService companyStockService;

    @EventListener(ApplicationReadyEvent.class)
    public void createAndSaveCompanies() {
        final var now = LocalDateTime.now();
        Flux.range(0, 100_000)
                .flatMap(i -> createAndSaveCompany())
                .subscribe();
        log.exit("Was completed in " + ChronoUnit.SECONDS.between(now, LocalDateTime.now()) + " seconds");
    }

    private Mono<Company> createAndSaveCompany() {
        final var companyName = UUID.randomUUID().toString();
        final var stockSymbol = UUID.randomUUID().toString();
        companyStockService.put(companyName, stockSymbol);
        return companyService.save(Company.builder().name(companyName).build());
    }
}

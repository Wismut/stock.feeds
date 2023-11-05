package com.example.stock.feeds.service;

import com.example.stock.feeds.model.Company;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.XSlf4j;
import net.datafaker.Faker;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@XSlf4j
@Component
@RequiredArgsConstructor
public class DataLoader {
    private final CompanyService companyService;
    private final Faker faker = new Faker();

    @EventListener(ApplicationReadyEvent.class)
    public void createAndSaveCompanies() {
        final var now = LocalDateTime.now();
        Flux.range(0, 100_000)
                .map(i -> UUID.randomUUID().toString())
                .flatMap(symbol -> companyService.save(Company.builder().symbol(symbol).name(faker.company().name()).build()))
                .subscribe();
        log.exit("Was completed in " + ChronoUnit.SECONDS.between(now, LocalDateTime.now()) + " seconds");
    }
}

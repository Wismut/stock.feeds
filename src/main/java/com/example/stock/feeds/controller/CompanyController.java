package com.example.stock.feeds.controller;

import com.example.stock.feeds.model.Company;
import com.example.stock.feeds.service.CompanyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(CompanyController.PATH)
public record CompanyController(CompanyService service) {
    public static final String PATH = "/api/companies";

    @GetMapping
    public Flux<Company> findAll() {
        return service.findAll();
    }
}

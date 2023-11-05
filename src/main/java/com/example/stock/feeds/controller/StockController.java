package com.example.stock.feeds.controller;

import com.example.stock.feeds.model.Stock;
import com.example.stock.feeds.service.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(StockController.PATH)
public record StockController(StockService service) {
    public static final String PATH = "/api/stocks";

    @GetMapping("/{stock_code}/quote")
    public Mono<Stock> findByCode(@PathVariable("stock_code") String code) {
        return service.findByCode(code);
    }
}

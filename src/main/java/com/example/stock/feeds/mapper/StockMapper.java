package com.example.stock.feeds.mapper;

import com.example.stock.feeds.dto.StockRedisDto;
import com.example.stock.feeds.model.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface StockMapper {
    Stock toModel(StockRedisDto dto);

    StockRedisDto toDto(Stock model);
}

package com.example.stock.feeds.repository;

import com.example.stock.feeds.model.Company;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends R2dbcRepository<Company, Integer> {

}

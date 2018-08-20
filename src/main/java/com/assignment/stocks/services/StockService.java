package com.assignment.stocks.services;

import com.assignment.stocks.domain.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StockService {

    Page<Stock> getAllStocks(Pageable pageRequest);

    Optional<Stock> findStock(Long id);

    Stock save(Stock stock);
}

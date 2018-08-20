package com.assignment.stocks.services.impl;

import com.assignment.stocks.domain.Stock;
import com.assignment.stocks.repositories.StocksRepository;
import com.assignment.stocks.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StocksRepository stockRepository;

    public Page<Stock> getAllStocks(Pageable pageRequest) {
        return stockRepository.findAll(pageRequest);
    }

    public Optional<Stock> findStock(Long id) {
        return stockRepository.findById(id);
    }

    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }
}

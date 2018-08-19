package com.assignment.stocks.services;

import com.assignment.stocks.domain.Stock;

import java.util.List;

public interface StockService {

    List<Stock> getAllStocks();

    Stock findStock(Long id);

    Stock updateStock(Stock stock);

    Stock createStock(Stock stock);
}

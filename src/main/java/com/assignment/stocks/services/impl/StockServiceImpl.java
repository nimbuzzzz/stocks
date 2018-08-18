package com.assignment.stocks.services.impl;

import com.assignment.stocks.domain.Stock;
import com.assignment.stocks.dto.StockDTO;
import com.assignment.stocks.repositories.StockJdbcRepository;
import com.assignment.stocks.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockJdbcRepository stockRepository;

    public List<Stock> getAllStocks(){ //todo add pagination

        return stockRepository.findAll();
    }

    public Stock findStock(Long id){
        return  stockRepository.getOne(id);
    }

    public Stock updateStock(Stock stock){
        return stockRepository.save(stock);
    }

    public Stock createStock(Stock stock){
        return stockRepository.save(stock);
    }
}

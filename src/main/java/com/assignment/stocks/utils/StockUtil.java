package com.assignment.stocks.utils;

import com.assignment.stocks.domain.Stock;
import com.assignment.stocks.web.dto.StockDTO;

public class StockUtil {

    public static Stock stockDtoToStock(StockDTO stockDTO){
        return Stock.builder()
                .name(stockDTO.getName())
                .currentPrice(stockDTO.getCurrentPrice())
                .build();
    }

    public static StockDTO stockToStockDTO(Stock stock){

        return  StockDTO.builder()
                .id(stock.getId())
                .currentPrice(stock.getCurrentPrice())
                .name(stock.getName())
                .updated(stock.getUpdated())
                .build();
    }
}

package com.assignment.stocks.utils;

import com.assignment.stocks.domain.Stock;
import com.assignment.stocks.dto.StockDTO;

public class StockUtil {
    public static Stock stockDtoToStock(StockDTO stockDTO){
        return new Stock(stockDTO.getId(), stockDTO.getName(), stockDTO.getCurrentPrice(), stockDTO.getUpdated());
    }

    public static StockDTO stockToStockDTO(Stock stock){
        return new StockDTO(stock);
    }
}

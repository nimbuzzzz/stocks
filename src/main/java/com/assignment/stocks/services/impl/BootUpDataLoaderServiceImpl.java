package com.assignment.stocks.services.impl;

import com.assignment.stocks.domain.Stock;
import com.assignment.stocks.services.BootUpDataLoaderService;
import com.assignment.stocks.services.StockService;
import lombok.extern.slf4j.Slf4j;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class BootUpDataLoaderServiceImpl implements BootUpDataLoaderService {

    @Autowired
    private StockService stockService;

    @Override
    public void loadBootUpData() {

        log.info("Going to load boot up data....");

        DataFactory df = new DataFactory();
        for (int count = 1; count <= 10; count++) {
            Stock stock = Stock.builder()
                    .name(df.getFirstName())
                    .currentPrice(BigDecimal.valueOf(df.getNumberUpTo(1000)))
                    .build();
            log.info("Going to save stock : {}", stock);
            stockService.save(stock);
        }

    }
}

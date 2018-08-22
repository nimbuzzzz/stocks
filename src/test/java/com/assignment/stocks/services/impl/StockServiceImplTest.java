package com.assignment.stocks.services.impl;

import com.assignment.stocks.domain.Stock;
import com.assignment.stocks.repositories.StocksRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class StockServiceImplTest {

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StocksRepository stocksRepository;

    private Stock persisted = null;

    @Before
    public void init() {

        MockitoAnnotations.initMocks(this);
        persisted = getDummyPersistedStockModel();
    }

    @Test
    public void testSaveStock() {

        Stock request = Stock.builder().name("Facebook").currentPrice(new BigDecimal(10.0)).build();

        when(stocksRepository.save(any(Stock.class))).thenReturn(persisted);

        Stock persistedStock = stockService.save(request);

        assertThat(persistedStock.getName()).isEqualTo(request.getName());
        assertThat(persistedStock.getCurrentPrice()).isEqualTo(request.getCurrentPrice());
        assertThat(persistedStock.getId()).isNotNull();
        assertThat(persistedStock.getUpdated()).isBeforeOrEqualsTo(new Date());

    }

    @Test
    public void testUpdateStock() {

        Stock request = Stock.builder().name("Facebook").currentPrice(new BigDecimal(20.0)).id(1L).build();
        Stock response = Stock.builder().name("Facebook").currentPrice(new BigDecimal(20.0)).id(1L).updated(new Date()).build();

        when(stocksRepository.save(any(Stock.class))).thenReturn(response);

        Stock updatedStock = stockService.save(request);

        assertThat(updatedStock.getName()).isEqualTo(request.getName());
        assertThat(updatedStock.getCurrentPrice()).isEqualTo(request.getCurrentPrice());
        assertThat(updatedStock.getId()).isNotNull();
        assertThat(updatedStock.getUpdated()).isBeforeOrEqualsTo(new Date());

    }

    @Test
    public void testGetByCorrectId() {

        when(stocksRepository.findById(1L)).thenReturn(Optional.of(persisted));

        Optional<Stock> stock = stockService.findStock(1L);

        assertThat(stock.get()).isNotNull();
        assertThat(stock.get().getId()).isEqualTo(1L);
        assertThat(stock.get().getCurrentPrice()).isEqualTo(persisted.getCurrentPrice());
        assertThat(stock.get().getName()).isEqualTo(persisted.getName());
        assertThat(stock.get().getUpdated()).isEqualTo(persisted.getUpdated());

    }

    @Test
    public void testGetByWrongId() {

        when(stocksRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Stock> stock = stockService.findStock(2L);
        assertThat(stock.isPresent()).isFalse();

    }

    @Test
    public void testGetAllPageResponse() {

        when(stocksRepository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(Collections.singletonList(persisted)));

        Page<Stock> stockPage = stockService.getAllStocks(PageRequest.of(0, 10));

        assertThat(stockPage).isNotNull();
        assertThat(stockPage.getContent()).isNotNull();
        assertThat(stockPage.getContent().size()).isEqualTo(1);
        assertThat(stockPage.getContent().get(0).getName()).isEqualTo(persisted.getName());
        assertThat(stockPage.getContent().get(0).getCurrentPrice()).isEqualTo(persisted.getCurrentPrice());
        assertThat(stockPage.getContent().get(0).getId()).isEqualTo(persisted.getId());
        assertThat(stockPage.getContent().get(0).getUpdated()).isEqualTo(persisted.getUpdated());
    }


    private Stock getDummyPersistedStockModel() {
        return Stock.builder()
                .name("Facebook")
                .currentPrice(new BigDecimal(10.0))
                .id(1L)
                .updated(new Date())
                .build();
    }

}
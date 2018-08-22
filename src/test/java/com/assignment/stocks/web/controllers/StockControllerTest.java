package com.assignment.stocks.web.controllers;


import com.assignment.stocks.domain.Stock;
import com.assignment.stocks.services.StockService;
import com.assignment.stocks.web.dto.StockDTO;
import com.assignment.stocks.web.exceptions.StockNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

public class StockControllerTest {

    @InjectMocks
    private StockController stockController;

    @Mock
    private StockService stockService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void tesCreateStock() {

        StockDTO request = StockDTO.builder().name("Facebook").currentPrice(new BigDecimal(10.0)).build();
        Stock persistedStock = Stock.builder().name("Facebook").currentPrice(new BigDecimal(10.0)).id(1L).updated(new Date()).build();

        when(stockService.save(any(Stock.class))).thenReturn(persistedStock);

        ResponseEntity<StockDTO> response = stockController.createStock(request);

        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.CREATED);
        assertThat(response.getBody().getName()).isEqualTo(request.getName());
        assertThat(response.getBody().getCurrentPrice()).isEqualTo(request.getCurrentPrice());
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getUpdated()).isBeforeOrEqualsTo(new Date());

    }

    @Test
    public void testUpdateStock() {

        StockDTO request = StockDTO.builder().currentPrice(new BigDecimal(20.0)).build();
        Stock updatedStock = Stock.builder().name("Facebook").currentPrice(new BigDecimal(20.0)).id(1L).updated(new Date()).build();

        when(stockService.save(any(Stock.class))).thenReturn(updatedStock);
        when(stockService.findStock(anyLong())).thenReturn(Optional.of(updatedStock));

        ResponseEntity<StockDTO> response = stockController.updateStock(1L, request);

        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCurrentPrice()).isEqualTo(request.getCurrentPrice());
        assertThat(response.getBody().getId()).isEqualTo(1L);
        assertThat(response.getBody().getUpdated()).isBeforeOrEqualsTo(new Date());

    }


    @Test
    public void testGetStockByCorrectId() {

        Stock stock = Stock.builder().name("Facebook").currentPrice(new BigDecimal(20.0)).id(1L).updated(new Date()).build();

        when(stockService.findStock(anyLong())).thenReturn(Optional.of(stock));

        ResponseEntity<StockDTO> response = stockController.getStock(1L);


        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCurrentPrice()).isNotNull();
        assertThat(response.getBody().getName()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(1L);
        assertThat(response.getBody().getUpdated()).isBeforeOrEqualsTo(new Date());

    }

    @Test
    public void testGetStockByWrongId() {

        when(stockService.findStock(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> stockController.getStock(1L)).isInstanceOf(StockNotFoundException.class);

    }

    @Test
    public void tesGetAllPaginatedStocks(){

        Stock persistedStock = Stock.builder().name("Facebook").currentPrice(new BigDecimal(10.0)).id(1L).updated(new Date()).build();
        when(stockService.getAllStocks(any(PageRequest.class))).thenReturn(new PageImpl<>(Collections.singletonList(persistedStock)));

        ResponseEntity<Page<StockDTO>> response = stockController.getAllStocks(0,10);

        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getContent()).isNotNull();
        assertThat(response.getBody().getContent().size()).isEqualTo(1);
    }

}

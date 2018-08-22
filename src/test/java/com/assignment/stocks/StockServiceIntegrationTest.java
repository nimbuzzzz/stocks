package com.assignment.stocks;

import com.assignment.stocks.domain.Stock;
import com.assignment.stocks.services.impl.StockServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceIntegrationTest {

    @Autowired
    private StockServiceImpl stockService;

    @Test
    public void testSaveNewStock() {

        Stock request = Stock.builder().name("Facebook").currentPrice(new BigDecimal(10.0)).build();

        Stock savedStock = stockService.save(request);

        assertThat(savedStock).isNotNull();
        assertThat(savedStock.getId()).isNotNull();
        assertThat(savedStock.getName()).isEqualTo(request.getName());
        assertThat(savedStock.getCurrentPrice()).isEqualTo(request.getCurrentPrice());
        assertThat(savedStock.getUpdated()).isBeforeOrEqualsTo(new Date());
    }

    @Test
    public void testGetById() {

        Stock request = Stock.builder().name("Facebook").currentPrice(new BigDecimal(10.0)).build();
        Stock savedStock = stockService.save(request);

        Optional<Stock> retrievedStock = stockService.findStock(savedStock.getId());

        assertThat(retrievedStock.isPresent()).isTrue();
        assertThat(retrievedStock.get()).isNotNull();
        assertThat(retrievedStock.get().getName()).isEqualTo(savedStock.getName());
        assertThat(retrievedStock.get().getId()).isEqualTo(savedStock.getId());
        assertThat(retrievedStock.get().getCurrentPrice()).isNotNull();
        assertThat(retrievedStock.get().getUpdated()).isNotNull();

    }

    @Test
    public void testUpdateStock() {

        Stock saveRequest = Stock.builder().currentPrice(new BigDecimal(10.0)).name("Facebook").build();
        Stock savedStock = stockService.save(saveRequest);

        Stock updateRequest = Stock.builder().name(savedStock.getName()).currentPrice(new BigDecimal(20.0)).id(savedStock.getId()).build();

        Stock updatedStock = stockService.save(updateRequest);

        assertThat(updatedStock).isNotNull();
        assertThat(updatedStock.getName()).isEqualTo(updateRequest.getName());
        assertThat(updatedStock.getCurrentPrice()).isEqualTo(updateRequest.getCurrentPrice());
        assertThat(updatedStock.getId()).isEqualTo(updateRequest.getId());
        assertThat(updatedStock.getUpdated()).isBeforeOrEqualsTo(new Date());

    }

    @Test
    public void testGetAllPagedStocks() {

        Stock saveRequest = Stock.builder().currentPrice(new BigDecimal(10.0)).name("Facebook").build();
        Stock savedStock = stockService.save(saveRequest);

        Page<Stock> pagedStocks = stockService.getAllStocks(PageRequest.of(0, 20));

        assertThat(pagedStocks).isNotNull();
        assertThat(pagedStocks.getContent()).isNotNull();
        assertThat(pagedStocks.getContent().size()).isGreaterThanOrEqualTo(1);


        assertThat(findByIdInList(savedStock.getId(), pagedStocks.getContent())).isTrue();

    }

    private Boolean findByIdInList(Long id, List<Stock> stocks) {
        for (Stock stock : stocks) {
            if (stock.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}

package com.assignment.stocks;

import com.assignment.stocks.domain.Stock;
import com.assignment.stocks.repositories.StocksRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockRepositoryIntegrationTest {

    @Autowired
    private StocksRepository stocksRepository;

    @Test
    public void testSaveNewStock() {

        Stock request = Stock.builder().name("Facebook").currentPrice(new BigDecimal(10.0)).build();

        Stock savedStock = stocksRepository.save(request);

        assertThat(savedStock).isNotNull();
        assertThat(savedStock.getId()).isNotNull();
        assertThat(savedStock.getName()).isEqualTo(request.getName());
        assertThat(savedStock.getCurrentPrice()).isEqualTo(request.getCurrentPrice());
        assertThat(savedStock.getUpdated()).isBeforeOrEqualsTo(new Date());
    }

    @Test
    public void testGetById() {

        Stock request = Stock.builder().name("Facebook").currentPrice(new BigDecimal(10.0)).build();
        Stock savedStock = stocksRepository.save(request);

        Optional<Stock> retrievedStock = stocksRepository.findById(savedStock.getId());

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
        Stock savedStock = stocksRepository.save(saveRequest);

        Stock updateRequest = Stock.builder().name(savedStock.getName()).currentPrice(new BigDecimal(20.0)).id(savedStock.getId()).build();

        Stock updatedStock = stocksRepository.save(updateRequest);

        assertThat(updatedStock).isNotNull();
        assertThat(updatedStock.getName()).isEqualTo(updateRequest.getName());
        assertThat(updatedStock.getCurrentPrice()).isEqualTo(updateRequest.getCurrentPrice());
        assertThat(updatedStock.getId()).isEqualTo(updateRequest.getId());
        assertThat(updatedStock.getUpdated()).isBeforeOrEqualsTo(new Date());

    }

    @Test
    public void testGetAllPagedStocks() {

        Stock saveRequest = Stock.builder().currentPrice(new BigDecimal(10.0)).name("Facebook").build();
        Stock savedStock = stocksRepository.save(saveRequest);

        Page<Stock> pagedStocks = stocksRepository.findAll(PageRequest.of(0, 20));

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

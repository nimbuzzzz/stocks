package com.assignment.stocks;

import com.assignment.stocks.web.controllers.StockController;
import com.assignment.stocks.web.dto.StockDTO;
import com.assignment.stocks.web.exceptions.StockNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockControllerIntegrationTest {

    @Autowired
    private StockController stockController;

    @Test
    public void testSaveNewStock() {
        StockDTO request = StockDTO.builder().name("Facebook").currentPrice(new BigDecimal(10.0)).build();

        ResponseEntity<StockDTO> savedStock = stockController.createStock(request);

        assertThat(savedStock).isNotNull();
        assertThat(savedStock.getStatusCode()).isEqualByComparingTo(HttpStatus.CREATED);
        assertThat(savedStock.getBody().getId()).isNotNull();
        assertThat(savedStock.getBody().getName()).isEqualTo(request.getName());
        assertThat(savedStock.getBody().getCurrentPrice()).isEqualTo(request.getCurrentPrice());
        assertThat(savedStock.getBody().getUpdated()).isBeforeOrEqualsTo(new Date());
    }


    @Test
    public void testUpdateStock() {

        StockDTO newStock = createStock();
        newStock.setCurrentPrice(new BigDecimal(20));

        ResponseEntity<StockDTO> response = stockController.updateStock(newStock.getId(), newStock);

        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCurrentPrice()).isEqualTo(newStock.getCurrentPrice());
        assertThat(response.getBody().getId()).isEqualTo(newStock.getId());
        assertThat(response.getBody().getUpdated()).isBeforeOrEqualsTo(new Date());

    }


    @Test
    public void testGetStockByRightId() {

        StockDTO newStock = createStock();


        ResponseEntity<StockDTO> response = stockController.getStock(newStock.getId());


        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCurrentPrice()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(newStock.getName());
        assertThat(response.getBody().getId()).isEqualTo(newStock.getId());
        assertThat(response.getBody().getUpdated()).isBeforeOrEqualsTo(new Date());

    }

    @Test
    public void testGetStockByWrongId() {


        assertThatThrownBy(() -> stockController.getStock(-1L)).isInstanceOf(StockNotFoundException.class);

    }

    @Test
    public void tesGetAllPagedStocks() {

        StockDTO newStock = createStock();

        ResponseEntity<Page<StockDTO>> response = stockController.getAllStocks(0, 100);

        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getContent()).isNotNull();
        assertThat(response.getBody().getContent().size()).isGreaterThanOrEqualTo(1);
        assertThat(response.getBody().getContent().toString().contains(newStock.getName())).isTrue();
        assertThat(response.getBody().getContent().toString().contains(newStock.getId().toString())).isTrue();
    }

    private StockDTO createStock() {

        StockDTO saveStockRequest = StockDTO.builder().name("Facebook").currentPrice(new BigDecimal(10.0)).build();

        return stockController.createStock(saveStockRequest).getBody();

    }
}

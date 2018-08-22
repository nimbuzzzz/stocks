package com.assignment.stocks;

import com.assignment.stocks.helper.RestResponsePage;
import com.assignment.stocks.web.dto.StockDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase
public class StockAppRestIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testSaveStock() {
        StockDTO request = StockDTO.builder().name("Facebook").currentPrice(new BigDecimal(10)).build();

        ResponseEntity<StockDTO> response = restTemplate.postForEntity("/api/stocks", request, StockDTO.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.CREATED);
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(request.getName());
        assertThat(response.getBody().getCurrentPrice()).isEqualTo(request.getCurrentPrice());
        assertThat(response.getBody().getUpdated()).isBeforeOrEqualsTo(new Date());

    }

    @Test
    public void testSaveStockWithInvalidRequest() {
        StockDTO request = StockDTO.builder().name("Facebook").currentPrice(new BigDecimal(-11)).build();

        ResponseEntity<StockDTO> response = restTemplate.postForEntity("/api/stocks", request, StockDTO.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);

    }


    @Test
    public void testUpdateStock() {

        StockDTO initialStock = createStock();
        initialStock.setCurrentPrice(new BigDecimal(20));

        HttpEntity<StockDTO> httpEntity = new HttpEntity<>(initialStock);
        ResponseEntity<StockDTO> response = restTemplate.exchange("/api/stocks/" + initialStock.getId(), HttpMethod.PUT, httpEntity, StockDTO.class);

        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCurrentPrice()).isEqualTo(initialStock.getCurrentPrice());
        assertThat(response.getBody().getId()).isEqualTo(initialStock.getId());
        assertThat(response.getBody().getUpdated()).isBeforeOrEqualsTo(new Date());
    }

    @Test
    public void testUpdateStockByWrongId() {

        StockDTO initialStock = createStock();
        initialStock.setCurrentPrice(new BigDecimal(20));

        HttpEntity<StockDTO> httpEntity = new HttpEntity<>(initialStock);
        ResponseEntity<StockDTO> response = restTemplate.exchange("/api/stocks/" + Long.MAX_VALUE, HttpMethod.PUT, httpEntity, StockDTO.class);

        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGetByCorrectId() {

        StockDTO initialStock = createStock();

        ResponseEntity<StockDTO> response = restTemplate.getForEntity("/api/stocks/" + initialStock.getId(), StockDTO.class);


        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCurrentPrice()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(initialStock.getName());
        assertThat(response.getBody().getId()).isEqualTo(initialStock.getId());
        assertThat(response.getBody().getUpdated()).isBeforeOrEqualsTo(new Date());

    }

    @Test
    public void testGetByWrongId() {

        StockDTO initialStock = createStock();

        ResponseEntity<StockDTO> response = restTemplate.getForEntity("/api/stocks/" + Long.MAX_VALUE, StockDTO.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.NOT_FOUND);

    }

    private StockDTO createStock() {

        StockDTO request = StockDTO.builder().name("Facebook").currentPrice(new BigDecimal(10)).build();

        ResponseEntity<StockDTO> response = restTemplate.postForEntity("/api/stocks", request, StockDTO.class);
        return response.getBody();
    }


}

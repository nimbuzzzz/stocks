package com.assignment.stocks.utils;

import com.assignment.stocks.domain.Stock;
import com.assignment.stocks.web.dto.StockDTO;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class StockUtilTest {


    @Test
    public void testStockDtoToStock() {

        StockDTO dto = getDummyStockDTO();

        Stock stock = StockUtil.stockDtoToStock(dto);

        Assertions.assertThat(stock.getCurrentPrice()).isEqualTo(dto.getCurrentPrice());
        Assertions.assertThat(stock.getName()).isEqualTo(dto.getName());
    }

    @Test
    public void testStockToStockDTO() {

        Stock stock = getDummyStockModel();

        StockDTO dto = StockUtil.stockToStockDTO(stock);

        Assertions.assertThat(dto.getId()).isEqualTo(stock.getId());
        Assertions.assertThat(dto.getCurrentPrice()).isEqualTo(stock.getCurrentPrice());
        Assertions.assertThat(dto.getName()).isEqualTo(stock.getName());
        Assertions.assertThat(dto.getUpdated()).isEqualTo(stock.getUpdated());
    }



    private Stock getDummyStockModel(){
        return Stock.builder()
                .name("Facebook")
                .currentPrice(new BigDecimal(10.0))
                .id(1L)
                .updated(new Date())
                .build();
    }


    private StockDTO getDummyStockDTO(){
        return StockDTO.builder()
                .name("Facebook")
                .id(1L)
                .updated(new Date())
                .currentPrice(new BigDecimal(10.0))
                .build();
    }
}

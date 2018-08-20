package com.assignment.stocks;

import com.assignment.stocks.web.controllers.StockController;
import com.assignment.stocks.domain.Stock;
import com.assignment.stocks.web.dto.StockDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebMvcTest(value = StockController.class)
public class StocksControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StockController 	stockController;

	Stock mockStock = new Stock();//(1L, "MockName", BigDecimal.valueOf(2.22));

	String mockStockJSON = "{\n" +
			"    \"id\": 1,\n" +
			"    \"name\": \"First\",\n" +
			"    \"currentPrice\": 24.44,\n" +
			"    \"updated\": \"2018-08-19T04:57:11.355+0000\"\n" +
			"  }";
	@Test
	public void contextLoads() {
	}


	@Test
	public  void getAllStocks(){
		List<StockDTO>  allStocks  = new ArrayList<>();

		//given(stockController.getAllStocks(1,10)).willReturn(ResponseEntity.status(HttpStatus.OK).body(allStocks));
	}

}

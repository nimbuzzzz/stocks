package com.assignment.stocks;

import com.assignment.stocks.services.BootUpDataLoaderService;
import com.assignment.stocks.utils.StockUtil;
import com.assignment.stocks.web.controllers.StockController;
import com.assignment.stocks.domain.Stock;
import com.assignment.stocks.web.dto.StockDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(StockController.class)
public class StocksControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StockController 	stockController;

	@MockBean
    private BootUpDataLoaderService boot;



	//Stock mockStock = new Stock();//(1L, "MockName", BigDecimal.valueOf(2.22));

	String mockStockJSON = "{\n" +
			"    \"id\": 1,\n" +
			"    \"name\": \"First\",\n" +
			"    \"currentPrice\": 24.44,\n" +
			"    \"updated\": \"2018-08-19T04:57:11.355+0000\"\n" +
			"  }";
	/*@Test
	public void contextLoads() {
	}*/


	/*@Test
	public  void getAllStocks(){
		List<StockDTO>  allStocks  = new ArrayList<>();

		//given(stockController.getAllStocks(1,10)).willReturn(ResponseEntity.status(HttpStatus.OK).body(allStocks));
	}*/

	@Test
	public void findStock() throws Exception {

		StockDTO mockStockDTO = StockDTO.builder().id((long) 1).currentPrice(BigDecimal.valueOf(243.3)).name("FindMeById").updated(new Date()).build();
		given(stockController.getStock(mockStockDTO.getId())).willReturn(ResponseEntity.status(HttpStatus.OK).body(mockStockDTO));

        this.mockMvc.perform(get("/api/stocks/"+mockStockDTO.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(mockStockDTO.getName()))
                .andExpect(jsonPath("$.currentPrice").value(mockStockDTO.getCurrentPrice())); //TODO: should i check updated too, in all the tests??
	}

	@Test
	public void createStock() throws Exception {
	    Long id = Long.valueOf(1);
        StockDTO mockStockDTO = StockDTO.builder().id(id).currentPrice(BigDecimal.valueOf(243.3)).name("FindMeById").updated(new Date()).build();
        URI stockURI = UriComponentsBuilder
                .fromUriString("/api/stocks/"+id)
                .buildAndExpand(mockStockDTO.getId()).toUri();
        given(stockController.createStock(mockStockDTO)).willReturn(ResponseEntity.created(stockURI).body(mockStockDTO));

        this.mockMvc.perform(post("/api/stocks/")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("name").value(mockStockDTO.getName()))
        .andExpect(jsonPath("currentPrice").value(mockStockDTO.getCurrentPrice()));
    }

    @Test
    public void getAllStocks(){
        //StockDTO mockStockDTO = StockDTO.builder().id(id).currentPrice(BigDecimal.valueOf(243.3)).name("FindMeById").updated(new Date()).build();



    }

}

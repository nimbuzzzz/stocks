package com.assignment.stocks.web.controllers;

import com.assignment.stocks.domain.Stock;
import com.assignment.stocks.web.dto.StockDTO;
import com.assignment.stocks.services.StockService;
import com.assignment.stocks.utils.StockUtil;
import com.assignment.stocks.web.exceptions.StockNotFoundException;
import com.assignment.stocks.web.exceptions.InvalidRequestParamException;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;
import java.util.function.Predicate;

@RestController
@RequestMapping("/api/stocks")
@Api(value = "stocks", description = "Operations pertaining to Stocks", tags = {"stocks"})
@Slf4j
public class StockController {


    @Autowired
    private StockService stockService;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a Stock.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created Stock"),
            @ApiResponse(code = 400, message = "Invalid input")
    })
    public ResponseEntity<StockDTO> createStock(@RequestBody @Valid StockDTO stockDTO){

        log.debug("Request received to create a stock :  {}", stockDTO);

        Stock createdStock = stockService.save(StockUtil.stockDtoToStock(stockDTO));

        URI stockURI = UriComponentsBuilder
                .fromUriString("/api/stocks/{id}")
                .buildAndExpand(createdStock.getId()).toUri();

        return ResponseEntity.created(stockURI).body(StockUtil.stockToStockDTO(createdStock));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update a Stock.")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID/Stock supplied"),
                            @ApiResponse(code = 404, message = "Stock not found"),
                            @ApiResponse(code = 200, message = "Succesfully updated the Stock") })
    public ResponseEntity<StockDTO> updateStock(@ApiParam(value = "The ID of the existing Stock resource.", required = true)
                                @PathVariable("id") Long id, @RequestBody @Valid StockDTO stockDTO){
        log.debug("Request received to update stock price of stock {}", stockDTO);
        Stock persistedStock = findStock(id);
        persistedStock.setCurrentPrice(stockDTO.getCurrentPrice());
        Stock updatedStock = stockService.save(persistedStock);
        return ResponseEntity.ok(StockUtil.stockToStockDTO(updatedStock));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a single Stock.")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID/Stock supplied"),
                            @ApiResponse(code = 404, message = "Stock not found"),
                            @ApiResponse(code = 200, message = "Succesfully fetched the Stock")})
    public ResponseEntity<StockDTO> getStock(@ApiParam(value = "The ID of the Stock.", required = true)
                              @PathVariable("id") Long id){
        log.debug("Request received to get details of stock with id ", id);
        Stock stock = findStock(id);
        return ResponseEntity.ok(StockUtil.stockToStockDTO(stock));
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid Page number/Page size supplied"),
                            @ApiResponse(code = 200, message = "Successfully fetched Stocks") })
    public ResponseEntity<Page<StockDTO>> getAllStocks(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                                                       @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {

        log.debug("Request received to get list of stock with pageNumber : {} and pageSize: {}", pageNumber, pageSize);
        if (!greaterThanOrEqualToZero.test(pageNumber)) {
            log.debug("Wrong request param : pageNumber {}", pageNumber);
            throw new InvalidRequestParamException("page number should be greater than or equal to 0");
        }
        if (!greaterThanOrEqualToZero.test(pageSize)) {
            log.debug("Wrong request param : pageSize {}", pageSize);
            throw new InvalidRequestParamException("page size should be greater than or equal to 0");
        }
        Page<Stock> page = stockService.getAllStocks(PageRequest.of(pageNumber, pageSize));
        return ResponseEntity.ok(page.map(StockUtil::stockToStockDTO));
    }

    private Predicate<Integer> greaterThanOrEqualToZero = integer -> integer >= 0;

    private  Stock findStock(Long id) {
        Optional<Stock> stock = stockService.findStock(id);
        stock.orElseThrow(() -> new StockNotFoundException(Long.toString(id)));
        return stock.get();
    }


}

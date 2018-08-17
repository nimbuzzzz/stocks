package com.assignment.stocks.controllers;

import com.assignment.stocks.domain.Stock;
import com.assignment.stocks.services.StockService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {


    @Autowired
    private StockService stockService;


    @PostMapping(value = "",
            consumes = "application/json",
            produces = "application/json")
    @ApiOperation(value = "Create a Stock resource.")
    public ResponseEntity<Stock> createStock(@RequestBody Stock stock){
        Stock createdStock = stockService.createStock(stock);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStock);
    }


    @PutMapping(value = "/{id}",
            consumes = "application/json",
            produces = "application/json")
    //@ResponseStatus(HttpStatus.NO_CONTENT) todo: best way?
    @ApiOperation(value = "Update a Stock resource.")
    public ResponseEntity<Stock> updateStock(@ApiParam(value = "The ID of the existing Stock resource.", required = true)
                                @PathVariable("id") Long id, @RequestBody Stock stock){
        //todo : handle id not present

        return ResponseEntity.status(HttpStatus.OK).body(stockService.updateStock(stock));
    }

    @GetMapping(value = "/{id}",
            produces = "application/json")
    @ApiOperation(value = "Get a single Stock.")
    public ResponseEntity<Stock> getStock(@ApiParam(value = "The ID of the Stock.", required = true)
                              @PathVariable("id") Long id){
        //todo: same

        return ResponseEntity.status(HttpStatus.OK).body(stockService.findStock(id));
    }

    @GetMapping(value = "",
            produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllStocks(){
        return ResponseEntity.status(HttpStatus.OK).body(stockService.getAllStocks());
    }


}

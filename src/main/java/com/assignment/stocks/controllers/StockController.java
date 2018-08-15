package com.assignment.stocks.controllers;

import com.assignment.stocks.domain.Stock;
import com.assignment.stocks.services.StockService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {


    @Autowired
    private StockService stockService;


    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a Stock resource.")
    public Stock createStock(@RequestBody Stock stock){
        Stock createdStock = stockService.createStock(stock);
        return createdStock;
    }


    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = "application/json",
            produces = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update a Stock resource.")
    public Stock updateStock(@ApiParam(value = "The ID of the existing Stock resource.", required = true)
                                @PathVariable("id") Long id, @RequestBody Stock stock){
        //todo : handle id not present

        return stockService.updateStock(stock);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a single Stock.")
    public Stock getStock(@ApiParam(value = "The ID of the Stock.", required = true)
                              @PathVariable("id") Long id){
        //todo: same

        return stockService.findStock(id);
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Stock> getAllStocks(){
        return stockService.getAllStocks();
    }


}

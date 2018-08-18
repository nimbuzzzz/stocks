package com.assignment.stocks.controllers;

import com.assignment.stocks.domain.Stock;
import com.assignment.stocks.dto.StockDTO;
import com.assignment.stocks.services.StockService;
import com.assignment.stocks.utils.StockUtil;
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

    @PostMapping(value = "")
    @ApiOperation(value = "Create a Stock resource.")
    public ResponseEntity<StockDTO> createStock(@RequestBody StockDTO stockDTO){
        Stock createdStock = stockService.createStock(StockUtil.stockDtoToStock(stockDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(StockUtil.stockToStockDTO(createdStock));
    }

//TODO: add validation
    @PutMapping(value = "/{id}")
    //@ResponseStatus(HttpStatus.NO_CONTENT) todo: best way?
    @ApiOperation(value = "Update a Stock resource.")
    public ResponseEntity<StockDTO> updateStock(@ApiParam(value = "The ID of the existing Stock resource.", required = true)
                                @PathVariable("id") Long id, @RequestBody StockDTO stockDTO){
        //todo : handle id not present
        Stock stock = stockService.findStock(id);
        if (stock != null){
            stock.setCurrentPrice(stockDTO.getCurrentPrice());
            stock.setName(stockDTO.getName());
            return ResponseEntity.status(HttpStatus.OK).body(StockUtil.stockToStockDTO(stockService.updateStock(stock)));
        }

        return null;//TODO
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Get a single Stock.")
    public ResponseEntity<StockDTO> getStock(@ApiParam(value = "The ID of the Stock.", required = true)
                              @PathVariable("id") Long id){
        //todo: same

        return ResponseEntity.status(HttpStatus.OK).body(StockUtil.stockToStockDTO(stockService.findStock(id)));
    }

    @GetMapping(value = "")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllStocks(){
        return ResponseEntity.status(HttpStatus.OK).body(stockService.getAllStocks());//TODO : add Stream
    }


}

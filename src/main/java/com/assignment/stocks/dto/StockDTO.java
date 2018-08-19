package com.assignment.stocks.dto;

import com.assignment.stocks.domain.Stock;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class StockDTO {

    @ApiModelProperty("System generated id")
    private Long id;
    @ApiModelProperty("Stock name")
    private String name;
    @ApiModelProperty("Current price of the stock")
    private BigDecimal currentPrice;
    @ApiModelProperty("Time it was last updated on")
    private Date updated;

    public StockDTO() {
    }

    public StockDTO(Stock stock){
        this.id = stock.getId();
        this.name = stock.getName();
        this.currentPrice = stock.getCurrentPrice();
        this.updated = stock.getUpdated();
    }

    public StockDTO(Long id, String name, BigDecimal currentPrice, Date updated) {
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
        this.updated = updated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StockDTO{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", currentPrice=").append(currentPrice);
        sb.append(", updated=").append(updated);
        sb.append('}');
        return sb.toString();
    }
}

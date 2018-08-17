package com.assignment.stocks.dto;

import java.math.BigDecimal;

public class StockDTO {

    private Long id;

    private String name;

    private BigDecimal currentPrice;

    public StockDTO() {
    }

    public StockDTO(Long id, String name, BigDecimal currentPrice) {
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
    }

    public StockDTO(String name, BigDecimal currentPrice) {
        this.name = name;
        this.currentPrice = currentPrice;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StockDTO{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", currentPrice=").append(currentPrice);
        sb.append('}');
        return sb.toString();
    }
}

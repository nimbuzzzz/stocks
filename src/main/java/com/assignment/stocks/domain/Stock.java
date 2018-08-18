package com.assignment.stocks.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "stock")
public class Stock implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private BigDecimal currentPrice;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    public Stock() {
    }

    public Stock(Long id, String name, BigDecimal currentPrice) {
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
    }

    public Stock(Long id, String name, BigDecimal currentPrice, Date updated) {
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
        this.updated = updated == null ? new Date(): updated;
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
        final StringBuilder sb = new StringBuilder("Stock{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", currentPrice=").append(currentPrice);
        sb.append(", updated=").append(updated);
        sb.append('}');
        return sb.toString();
    }
}

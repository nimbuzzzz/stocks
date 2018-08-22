package com.assignment.stocks.web.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {

    @ApiModelProperty("System generated id")
    private Long id;

    @NotNull
    @Size(max=255)
    @ApiModelProperty("Stock name")
    private String name;

    @NotNull
    @PositiveOrZero
    @ApiModelProperty("Current price of the stock")
    private BigDecimal currentPrice;

    @ApiModelProperty("Time it was last updated on")
    private Date updated;

}

package com.sample.customer_statement_validator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@JsonNaming(value= PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class Record{
    @JsonProperty(value = "Start Balance")
    private BigDecimal startBalance;
    @JsonProperty(value = "End Balance")
    private BigDecimal endBalance;
    private String description;
    private Integer reference;
    private BigDecimal mutation;
    private String accountNumber;
}

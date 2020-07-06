package com.sample.customer_statement_validator.model;

import lombok.Data;

@Data
public class ErrorRecord
{
    private int reference;
    private String accountNumber;
}

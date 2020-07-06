package com.sample.customer_statement_validator.model;

import lombok.Data;

import java.util.List;

@Data
public class ResponseData
{
    private String result;
    private List<ErrorRecord> errorRecordList;
}


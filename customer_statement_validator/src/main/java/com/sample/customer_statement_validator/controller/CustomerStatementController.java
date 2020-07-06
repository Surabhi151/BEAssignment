package com.sample.customer_statement_validator.controller;

import com.sample.customer_statement_validator.exception.RecordNotFoundException;
import com.sample.customer_statement_validator.model.Record;
import com.sample.customer_statement_validator.model.ResponseData;
import com.sample.customer_statement_validator.service.StatementValidatorService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Slf4j
@RestController
public class CustomerStatementController {
    @Autowired
    StatementValidatorService validatorService;

    @PostMapping("/request")
    public ResponseData statementProcessController(@RequestBody List<Record> record) throws HttpMessageNotReadableException,RecordNotFoundException{
        log.info("Entry to Controller Page:statementProcessController()");
        if (record.isEmpty()) {
            throw new RecordNotFoundException("Request body record list is empty");
        }
        return validatorService.getValidatedRecord(record);
    }

}

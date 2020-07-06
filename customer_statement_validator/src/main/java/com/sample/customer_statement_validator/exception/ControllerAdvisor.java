package com.sample.customer_statement_validator.exception;

import com.sample.customer_statement_validator.Result;
import com.sample.customer_statement_validator.model.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;

@Slf4j
@ControllerAdvice
public class ControllerAdvisor  {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    private @ResponseBody  ResponseData handleHttpMessageNotReadble(final HttpMessageNotReadableException e) {
        log.error("HttpMessageNotReadable Exception details:" +e.getMessage());
        ResponseData responseData = new ResponseData();
        responseData.setResult(Result.BAD_REQUEST.name());
        responseData.setErrorRecordList(Collections.emptyList());
        return responseData;
    }
    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    private @ResponseBody  ResponseData handleRecordNotFound(final RecordNotFoundException e) {
        log.error("RecordNotFoundException details:" +e.getMessage());
        ResponseData responseData = new ResponseData();
        responseData.setResult(Result.RECORD_NOT_FOUND.name());
        responseData.setErrorRecordList(Collections.emptyList());
        return responseData;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    private @ResponseBody ResponseData handleException(final Exception e) {
        log.error("Exception details:"+e.getMessage());
        ResponseData responseData = new ResponseData();
        responseData.setResult(Result.INTERNAL_SERVER_ERROR.name());
        responseData.setErrorRecordList(Collections.emptyList());
        return responseData;
    }
}

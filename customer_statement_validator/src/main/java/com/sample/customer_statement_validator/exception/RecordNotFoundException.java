package com.sample.customer_statement_validator.exception;

public class RecordNotFoundException extends Exception {
    public RecordNotFoundException() {
        super();
    }

    public RecordNotFoundException(final String message) {
        super(message);
    }
}

package com.sample.customer_statement_validator;

import java.util.function.Function;

public enum Result {
    SUCCESSFUL,
    DUPLICATE_REFERENCE,
    INCORRECT_END_BALANCE,
    DUPLICATE_REFERENCE_INCORRECT_END_BALANCE,
    RECORD_NOT_FOUND,
    BAD_REQUEST,
    INTERNAL_SERVER_ERROR;
}

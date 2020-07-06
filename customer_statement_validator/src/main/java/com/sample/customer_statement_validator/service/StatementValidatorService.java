package com.sample.customer_statement_validator.service;

import com.sample.customer_statement_validator.Result;
import com.sample.customer_statement_validator.model.ErrorRecord;
import com.sample.customer_statement_validator.model.Record;
import com.sample.customer_statement_validator.model.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.stream.Stream;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Slf4j
@Service
public class StatementValidatorService {

    public ResponseData getValidatedRecord(List<Record> recordList) {
        log.info("Entry to GetValidated Record: getValidatedRecord()");
        ResponseData responseData = new ResponseData();
        final Map<String, List<Record>> duplicateReference = getValidatedReference(recordList);
        final Map<String, List<Record>> incorrectBalance =  getValidatedBalance(recordList);
        final String validatedStatus = getValidatedStatus(duplicateReference,incorrectBalance);
        final List<Record> validatedRecordList = getValidatedList(duplicateReference,incorrectBalance);

        responseData.setResult(validatedStatus);
        responseData.setErrorRecordList(getErrorRecord(validatedRecordList));
        return responseData;
    }

    private Map<String, List<Record>> getValidatedReference(List<Record> recordList)
    {
        log.info("Entry to ValidateReference Method: getValidatedReference()");
        Set<Integer> set = new HashSet<>();
        return recordList.stream().
                filter(record -> !set.add(record.getReference()))
                    .collect(groupingBy(this::duplicateKeyMapper));
    }
    private Map<String, List<Record>> getValidatedBalance(List<Record> recordList) {
        log.info("Entry to ValidateBalance Method: getValidatedBalance()");
        return recordList.stream()
                .filter(record -> !record.getStartBalance().add(record.getMutation()).equals(record.getEndBalance()))
                    .collect(groupingBy(this::incorrectBalanceKeyMapper));
    }
    private String duplicateKeyMapper(Record record) {
        return Result.DUPLICATE_REFERENCE.name();
    }
    private String incorrectBalanceKeyMapper(Record record) {
        return Result.INCORRECT_END_BALANCE.name();
    }

    private List<Record> getValidatedList(final Map<String, List<Record>> duplicateReferenceList, final Map<String, List<Record>> incorrectBalanceList) {
        log.info("Entry to GetValidatedList: getvalidatedList() ");
        return Stream.concat(duplicateReferenceList.entrySet().stream(),incorrectBalanceList.entrySet().stream())
                .map(x->x.getValue()).flatMap(x->x.stream()).distinct()
                .collect(Collectors.toList());
    }

    private String getValidatedStatus(final Map<String, List<Record>> duplicateReference, final Map<String, List<Record>> incorrectBalance) {
        log.info("Entry to GetValidatedStatus method: getValidatedStatus()");
        String validatedStatus = Stream.concat(duplicateReference.keySet().stream(),incorrectBalance.keySet().stream())
                .collect(Collectors.joining("_"));
        return (validatedStatus.isEmpty()) ? Result.SUCCESSFUL.name() : validatedStatus ;
    }
    private List<ErrorRecord> getErrorRecord (final List<Record> validatedRecordList) {
        log.info("Entry to GetErrorRecord Method: getErrorRecord()");
        List<ErrorRecord> errorRecordList = new ArrayList<>();
        for (Record record : validatedRecordList) {
            ErrorRecord errorRecord = new ErrorRecord();
            errorRecord.setReference(record.getReference());
            errorRecord.setAccountNumber(record.getAccountNumber());
            errorRecordList.add(errorRecord);
        }
        return errorRecordList;
    }
}

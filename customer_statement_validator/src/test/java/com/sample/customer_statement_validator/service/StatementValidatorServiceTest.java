package com.sample.customer_statement_validator.service;

import com.sample.customer_statement_validator.Result;
import com.sample.customer_statement_validator.model.Record;
import com.sample.customer_statement_validator.model.ResponseData;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class StatementValidatorServiceTest {

    @InjectMocks
    StatementValidatorService validatorService;

    @Test
    public void testValidatedBalance() {
        List<Record> mockRecordList = new ArrayList<>();
                mockRecordList.add(new Record(BigDecimal.valueOf(99.44),BigDecimal.valueOf(+41.23),"Clothes for Vincent Bakker",179430,BigDecimal.valueOf(14.67),"NL93ABNA0585619023"));
                ResponseData responseData = validatorService.getValidatedRecord(mockRecordList);
                assertThat(responseData.getResult()).isEqualTo(Result.INCORRECT_END_BALANCE.name());
    }

    @Test
    public void testValidatedReferenceandBalance() {
        List<Record> mockList = new ArrayList<>();
        mockList.add(new Record(BigDecimal.valueOf(90.83),BigDecimal.valueOf(-10.91),"Test",123456,BigDecimal.valueOf(79.92),"NL123"));
        mockList.add(new Record(BigDecimal.valueOf(90.83),BigDecimal.valueOf(-10.91),"Test",123456,BigDecimal.valueOf(79.92),"NL123"));
        mockList.add(new Record(BigDecimal.valueOf(90.83),BigDecimal.valueOf(-10.91),"Test",112606,BigDecimal.valueOf(79.92),"NL123"));
        ResponseData responseData = validatorService.getValidatedRecord(mockList);
        assertThat(responseData.getResult()).isEqualTo(Result.DUPLICATE_REFERENCE_INCORRECT_END_BALANCE.name());

    }
}

package com.sample.customer_statement_validator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.customer_statement_validator.model.Record;
import com.sample.customer_statement_validator.model.ResponseData;
import com.sample.customer_statement_validator.service.StatementValidatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CustomerStatementController.class)
public class CustomerStatementControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    StatementValidatorService validatorService;
    @MockBean
    ResponseData mockResponseData;


    @Test
    public void whenInValidInput_thenReturns400() throws  Exception{
        Record mockRecord = new Record(BigDecimal.valueOf(23.96),BigDecimal.valueOf(-27.43),"Clothes for Vincent Bakker",179430,BigDecimal.valueOf(-3.47),"NL93ABNA0585619023");
        mockMvc.perform(post("/request")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(mockRecord)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenValidInput_thenReturns200() throws  Exception{
        Record mockRecord = new Record(BigDecimal.valueOf(23.96),BigDecimal.valueOf(-27.43),
                "Clothes for Vincent Bakker",179430,BigDecimal.valueOf(-3.47),
                "NL93ABNA0585619023");
        List<Record> mockRecordList = new ArrayList<>();
        mockRecordList.add(mockRecord);
        mockMvc.perform(post("/request")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(mockRecordList)))
                .andExpect(status().isOk());
    }
    @Test
    public void whenEmptyInput() throws  Exception{
        List<Record> mockRecordList = new ArrayList<>();
        mockMvc.perform(post("/request")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(mockRecordList)))
                .andExpect(status().isNotFound());
    }
    @Test
    public void whenValidInput_thenMapToService() throws Exception {
        Record mockRecord = new Record(BigDecimal.valueOf(23.96),BigDecimal.valueOf(-27.43),"Clothes for Vincent Bakker",179430,BigDecimal.valueOf(-3.47),"NL93ABNA0585619023");
        List<Record> mockRecordList = new ArrayList<>();
        mockRecordList.add(mockRecord);
        assertThat(mockRecordList).isNotEmpty();
        Mockito.when(validatorService.getValidatedRecord(Mockito.anyList())).thenReturn(mockResponseData);
    }
}

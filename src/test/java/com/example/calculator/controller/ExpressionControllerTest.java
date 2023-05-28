package com.example.calculator.controller;

import com.example.calculator.exceptions.ExpressionServiceException;
import com.example.calculator.service.ExpressionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ExpressionController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class ExpressionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExpressionService expressionService;

    @Test
    public void testCalculate_whenGivenCorrectExpression() throws Exception {

        Mockito.when(expressionService.calculate(Mockito.anyString(), Mockito.anyString())).thenReturn(4);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/expression")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"userId\" : \"rav\", \"expression\" : \"1 + 3\"}")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is2xxSuccessful())
                .andExpect(result -> assertTrue(result.getResponse().getContentAsString().contains("4")));
    }

    @Test
    public void testCalculate_whenGivenNoUserId_ExpectException() throws Exception {

        Mockito.when(expressionService.calculate(isNull(), Mockito.anyString())).thenThrow(ExpressionServiceException.class);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/expression")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"expression\" : \"1 + 3\"}")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ExpressionServiceException))
                .andExpect(result -> assertTrue(result.getResolvedException().getMessage().contains("null or empty")));
    }

    @Test
    public void testCalculate_whenGivenExpressionIsNullOrEmpty_ExpectZero() throws Exception {

        Mockito.when(expressionService.calculate(Mockito.anyString(), isNull())).thenReturn(0);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/expression")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"userId\" : \"rav\"}")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().is2xxSuccessful())
                .andExpect(result -> assertTrue(result.getResponse().getContentAsString().contains("0")));
    }

    @Test
    public void testFrequent_whenGivenNoUserId_expectException() throws Exception {
        Mockito.when(expressionService.frequent(isNull())).thenThrow(ExpressionServiceException.class);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/frequent")
                .accept(MediaType.APPLICATION_JSON)
                .queryParam("userId", "")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ExpressionServiceException))
                .andExpect(result -> assertTrue(result.getResolvedException().getMessage().contains("null or empty")));
    }
}

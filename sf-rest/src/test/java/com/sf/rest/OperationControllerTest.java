package com.sf.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class OperationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetBalance() throws Exception {
        mockMvc.perform(get("/rest/v1/operations/balances")
                        .content("{\n" +
                        "    \"customerId\": 12\n" +
                        "}")
                        .param("customerId", "12"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}

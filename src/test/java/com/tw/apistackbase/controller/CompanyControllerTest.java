package com.tw.apistackbase.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {
    @Autowired
    private MockMvc mockMvc;
//    @Test
//    public void should_return_companies() throws Exception {
//        mockMvc.perform(get("/companies"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().json("[{\"employees\":[{\"name\":\"Khai\",\"id\":1},{\"name\":\"Gordon\",\"id\":2},{\"name\":\"Shoron\",\"id\":3},{\"name\":\"Will\",\"id\":5},{\"name\":\"Dillon\",\"id\":5}],\"name\":\"TX\",\"id\":1},{\"employees\":[{\"name\":\"Kar\",\"id\":6},{\"name\":\"Gox\",\"id\":7},{\"name\":\"Zed\",\"id\":8},{\"name\":\"Akri\",\"id\":9},{\"name\":\"Ryte\",\"id\":10}],\"name\":\"alibaba\",\"id\":2}]"));
//    }

    @Test
    public void should_return_companies_by_employeesNumber() throws Exception {
        mockMvc.perform(get("/companies/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"companyName\":\"TX\",\"employees\":[{\"name\":\"Khai\",\"id\":1},{\"name\":\"Gordon\",\"id\":2},{\"name\":\"Shoron\",\"id\":3},{\"name\":\"Will\",\"id\":5},{\"name\":\"Dillon\",\"id\":5}],\"empployeesNumber\":1}"));
    }

    @Test
    public void should_return_employees_by_employeesNumber() throws Exception {
        mockMvc.perform(get("/companies/1/employees"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"Khai\",\"id\":1},{\"name\":\"Gordon\",\"id\":2},{\"name\":\"Shoron\",\"id\":3},{\"name\":\"Will\",\"id\":5},{\"name\":\"Dillon\",\"id\":5}]"));
    }
}
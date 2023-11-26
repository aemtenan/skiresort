package com.example.skiresort;

import com.example.skiresort.controller.ResortController;
import com.example.skiresort.model.Resort;
import com.example.skiresort.repository.ResortRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ResortController.class)
public class ResortControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResortRepository resortRepository;

    @Test
    void createResort() throws Exception {
        Resort resort = new Resort("Snowy Hills", "Wiarton");
        mockMvc.perform( MockMvcRequestBuilders
                        .post("/api/v2/resorts")
                        .content(objectMapper.writeValueAsString(resort))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated());
    }
}

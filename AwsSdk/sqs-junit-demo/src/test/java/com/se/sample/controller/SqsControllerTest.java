package com.se.sample.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.sample.dto.Quote;
import com.se.sample.service.SqsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author Evgeniy Skiba on 04.06.2020
 * @project sqs-junit-demo
 */

@WebMvcTest
class SqsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QueueMessagingTemplate queueMessagingTemplate;

    @MockBean
    private SqsService sqsService;

    @Test
    public void sendQuoteToSqs() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Quote q = new Quote(
                "With the new day comes new strength and new thoughts.",
                "Eleanor Roosevelt"
        );
        String json = mapper.writeValueAsString(q);

        mockMvc.perform(post("/sqs/quotes")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
package com.burak.ticketreservation.controller;

import com.burak.ticketreservation.entity.Flight;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class FlightControllerIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    void testSaveFlight() throws Exception {
        String requestBodyJson = "{\"airline\":\"thy\", \"fromCity\":\"ankara\", \"toCity\":\"izmir\", " +
                "\"departureDate\":\"2023-02-03T16:45\", \"arrivalDate\":\"2023-02-03T18:45\", \"baseTicketPrice\":300," +
                " \"seatCapacity\":100 }";

        MvcResult result = mockMvc
                .perform(post("/api/flights").content(requestBodyJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        MockHttpServletResponse response = result.getResponse();
        String responseMessage = response.getContentAsString();

        Assertions.assertEquals("1", responseMessage);
    }

    @Test
    void testGetFlightById() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/flights/5")).andExpect(status().isOk()).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String responseMessage = response.getContentAsString();

        String expectedResponseMessage = "{\"id\":5,\"fromCity\":\"ankara\",\"toCity\":\"izmir\",\"departureDate\":\"2023-02-03T16:45:00\",\"arrivalDate\":\"2023-02-03T18:45:00\",\"baseTicketPrice\":300.0}";

        Assertions.assertEquals(responseMessage, expectedResponseMessage);
    }

    @Test
    void testGetPegasusFlights() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/pegasusflights")).andExpect(status().isOk()).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String responseMessage = response.getContentAsString();
        System.out.println(responseMessage);
        String expectedResponseMessage = "[{\"id\":2,\"fromCity\":\"ankara\",\"toCity\":\"istanbul\",\"departureDate\":\"2023-02-04T16:45:00\",\"arrivalDate\":\"2023-02-06T16:45:00\",\"baseTicketPrice\":235.4}]";

        Assertions.assertEquals(responseMessage, expectedResponseMessage);
    }




}

package com.github.edgarzed.topjavagraduation.web.rest;

import com.github.edgarzed.topjavagraduation.RestaurantTestData;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;

import java.time.LocalTime;
import java.util.Arrays;

import static com.github.edgarzed.topjavagraduation.VoteTestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VoteRestControllerTest extends AbstractControllerTest {

    @Test
    public void testGetBetween() throws Exception {
        mockMvc.perform(get(VoteRestController.REST_URL)
                .param("startDate", VOTE3.getDate().toString())
                .param("endDate", VOTE3.getDate().toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(Arrays.asList(VOTE1, VOTE2, VOTE3))))
                .andDo(print());
    }

    @Test
    @WithUserDetails("userAAA@yandex.ru")
    public void testCreateUpdateToday() throws Exception {
        mockMvc.perform(put(VoteRestController.REST_URL + "/todays/" + RestaurantTestData.RESTAURANT1.getId())
                .param("nowTime", LocalTime.of(10, 25).toString()))
                .andExpect(status().isAccepted());
    }

    @Test
    @WithUserDetails("userAAA@yandex.ru")
    public void testLateCreateUpdateToday() throws Exception {
        mockMvc.perform(put(VoteRestController.REST_URL + "/todays/" + RestaurantTestData.RESTAURANT1.getId())
                .param("nowTime", LocalTime.of(11, 25).toString()))
                .andExpect(status().isLocked());
    }

    @Test
    public void testUnAuthCreateUpdateToday() throws Exception {
        mockMvc.perform(put(VoteRestController.REST_URL + "/todays/" + RestaurantTestData.RESTAURANT1.getId()))
                .andExpect(status().isUnauthorized());
    }
}
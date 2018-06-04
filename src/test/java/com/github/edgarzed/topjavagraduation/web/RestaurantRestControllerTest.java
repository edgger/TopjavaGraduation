package com.github.edgarzed.topjavagraduation.web;

import com.github.edgarzed.topjavagraduation.RestaurantTestData;
import com.github.edgarzed.topjavagraduation.model.Restaurant;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestaurantRestControllerTest extends AbstractControllerTest {

    @Test
    public void testCreate() throws Exception {
        Restaurant expected = new Restaurant(null, "testR");
        String returnedString = mockMvc.perform(post(RestaurantRestController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expected)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Restaurant returned = objectMapper.readValue(returnedString, Restaurant.class);
        expected.setId(returned.getId());

        assertThat(returned).isEqualToComparingFieldByField(expected);
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(RestaurantRestController.REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(RestaurantTestData.RESTAURANTS)))
                .andDo(print());
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(RestaurantRestController.REST_URL+ "/" + RestaurantTestData.RESTAURANT1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(RestaurantTestData.RESTAURANT1)))
                .andDo(print());
    }
}
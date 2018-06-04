package com.github.edgarzed.topjavagraduation.web;

import com.github.edgarzed.topjavagraduation.model.Menu;
import com.github.edgarzed.topjavagraduation.service.MenuService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;

import static com.github.edgarzed.topjavagraduation.MenuTestData.*;
import static com.github.edgarzed.topjavagraduation.RestaurantTestData.RESTAURANT1;
import static com.github.edgarzed.topjavagraduation.RestaurantTestData.RESTAURANT2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MenuRestControllerTest extends AbstractControllerTest {

    @Autowired
    MenuService menuService;

    @Test
    public void testCreate() throws Exception {
        String response = mockMvc.perform(post(MenuRestController.REST_URL+"/"+RESTAURANT2.getId()+"/menus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(NEWMENU)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Menu returned = objectMapper.readValue(response, Menu.class);
        NEWMENU.setId(returned.getId());

        assertThat(returned).isEqualToIgnoringGivenFields(NEWMENU, "meals");
        assertThat(returned.getMeals()).usingElementComparatorIgnoringFields("id").isEqualTo(NEWMENU.getMeals());
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(MenuRestController.REST_URL + "/menus/" + MENU1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(MENU1)))
                .andDo(print());
    }

    @Test
    public void testGetBetween() throws Exception {
        mockMvc.perform(get(MenuRestController.REST_URL + "/menus")
                .param("startDate", MENU1.getDate().toString())
                .param("endDate", MENU1.getDate().toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(MENU1, MENU2, MENU3))))
                .andDo(print());
    }

    @Test
    public void testGetBetweenByRestaurant() throws Exception {
        mockMvc.perform(get(MenuRestController.REST_URL + "/" + RESTAURANT2.getId() + "/menus")
                .param("startDate", MENU5.getDate().toString())
                .param("endDate", MENU5.getDate().toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(Collections.singleton(MENU5))))
                .andDo(print());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void testGetAllTodays() throws Exception {
        MENUTODAYS.forEach(menu -> menuService.create(menu));

        mockMvc.perform(get(MenuRestController.REST_URL + "/menus/todays"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(MENUTODAYS)))
                .andDo(print());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void testGetTodaysByRestaurant() throws Exception {

        Menu todaysMenuRestaurant1 = menuService.create(MENUTODAY1);

        mockMvc.perform(get(MenuRestController.REST_URL + "/" + RESTAURANT1.getId() + "/menus/todays"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(todaysMenuRestaurant1)))
                .andDo(print());
    }
}
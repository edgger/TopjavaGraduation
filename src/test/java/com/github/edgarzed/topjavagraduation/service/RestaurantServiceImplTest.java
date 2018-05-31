package com.github.edgarzed.topjavagraduation.service;

import com.github.edgarzed.topjavagraduation.RestaurantTestData;
import com.github.edgarzed.topjavagraduation.model.Restaurant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantServiceImplTest extends AbstractServiceTest{

    @Autowired
    RestaurantService restaurantService;

    @Test
    public void create() {
        Restaurant expected = new Restaurant(null, "test");
        Restaurant created = restaurantService.create(expected);
        expected.setId(created.getId());

        assertThat(created).isEqualToComparingFieldByField(expected);
    }

    @Test
    public void get() {
        assertThat(restaurantService.get(1000)).isEqualToComparingFieldByField(RestaurantTestData.RESTAURANT1);
    }

    @Test
    public void getAll() {
        assertThat(restaurantService.getAll()).usingDefaultElementComparator().isEqualTo(RestaurantTestData.RESTAURANTS);
    }
}
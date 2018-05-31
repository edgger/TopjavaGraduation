package com.github.edgarzed.topjavagraduation;

import com.github.edgarzed.topjavagraduation.model.Restaurant;

import java.util.Arrays;
import java.util.List;

public class RestaurantTestData {
    private RestaurantTestData() {
    }

    public static final Restaurant RESTAURANT1 = new Restaurant(1000, "Zefir");
    public static final Restaurant RESTAURANT2 = new Restaurant(1001, "Magistrat");
    public static final Restaurant RESTAURANT3 = new Restaurant(1002, "Magesta");

    public static final List<Restaurant> RESTAURANTS = Arrays.asList(RESTAURANT3,RESTAURANT2,RESTAURANT1);
}

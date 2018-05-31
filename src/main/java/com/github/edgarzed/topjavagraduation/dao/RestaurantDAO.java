package com.github.edgarzed.topjavagraduation.dao;

import com.github.edgarzed.topjavagraduation.model.Restaurant;

import java.util.List;

public interface RestaurantDAO {

    Restaurant save(Restaurant restaurant);

    Restaurant get(int id);

    List<Restaurant> getAll();

    Restaurant getReference(int id);
}

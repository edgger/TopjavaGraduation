package com.github.edgarzed.topjavagraduation.service;

import com.github.edgarzed.topjavagraduation.dao.RestaurantDAO;
import com.github.edgarzed.topjavagraduation.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantDAO restaurantDAO;

    @Autowired
    public RestaurantServiceImpl(RestaurantDAO restaurantDAO) {
        this.restaurantDAO = restaurantDAO;
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        return restaurantDAO.save(restaurant);
    }

    @Override
    public Restaurant get(int id) {
        return restaurantDAO.get(id);
    }
    //TODO: restaurantCache
    @Override
    public List<Restaurant> getAll() {
        return restaurantDAO.getAll();
    }

    @Override
    public Restaurant getReference(int id){
        return restaurantDAO.getReference(id);
    }
}

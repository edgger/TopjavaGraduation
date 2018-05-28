package com.github.edgarzed.topjavagraduation.web;

import com.github.edgarzed.topjavagraduation.model.Restaurant;
import com.github.edgarzed.topjavagraduation.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantRestController {

    @Autowired
    RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Restaurant> create(Restaurant restaurant){
        return null;
    }

    @GetMapping
    public List<Restaurant> getAll(){
        return null;
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable("id") int id){
        return null;
    }
}
/*
    /restaurants --post(new)/get(all)
    /restaurants/{id} --/get(id)
*/
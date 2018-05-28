package com.github.edgarzed.topjavagraduation.web;

import com.github.edgarzed.topjavagraduation.model.Menu;
import com.github.edgarzed.topjavagraduation.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping
public class MenuRestController {

    @Autowired
    MenuService menuService;

    @PostMapping("/restaurants/{restaurantId}/menus")
    public ResponseEntity<Menu> create(@PathVariable("restaurantId") int restaurantId, Menu menu){
        return null;
    }

    @GetMapping("/restaurants/menus/{id}")
    public Menu get(@PathVariable("id") int id){
        return null;
    }

    @GetMapping("/restaurants/menus")
    public List<Menu> getBetween(@RequestParam(value = "startDate", required = false) LocalDate startDate,
                                 @RequestParam(value = "endDate", required = false) LocalDate endDate){
        return null;
    }

    @GetMapping("/restaurants/{restaurantId}/menus")
    public List<Menu> getBetweenByRestaurant(@PathVariable("restaurantId") int restaurantId,
                                             @RequestParam(value = "startDate", required = false) LocalDate startDate,
                                             @RequestParam(value = "endDate", required = false) LocalDate endDate){
        return null;
    }

    @GetMapping("/restaurants/menus/todays")
    public List<Menu> getAllTodays(){
        return null;
    }

    @GetMapping("/restaurants/{restaurantId}/menus/todays")
    public Menu getTodaysByRestaurant(@PathVariable("restaurantId") int restaurantId){
        return null;
    }

}

/*
    /restaurants/menus?startDate&endDate --get(all/filtered)
    /restaurants/menus/todays --get(all todays)
    /restaurants/menus/{id} --get(id)

    /restaurants/{id}/menus --post(new)
    /restaurants/{id}/menus?startDate&endDate --get(all/filtered & by restaurant)
    /restaurants/{id}/menus/todays --get(todays by restaurant)
*/
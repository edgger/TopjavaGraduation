package com.github.edgarzed.topjavagraduation.web.rest;

import com.github.edgarzed.topjavagraduation.model.Menu;
import com.github.edgarzed.topjavagraduation.model.Restaurant;
import com.github.edgarzed.topjavagraduation.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(RestaurantRestController.REST_URL)
public class MenuRestController {
    static final String REST_URL = "/rest/restaurants";

    @Autowired
    MenuService menuService;

    @PostMapping(value = "/{restaurantId}/menus", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> create(@PathVariable("restaurantId") int restaurantId,
                                       @RequestBody Menu menu) {
        Restaurant menuRestaurant = menu.getRestaurant();
        if (menuRestaurant == null || menuRestaurant.getId() != restaurantId) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
        menu.getMeals().forEach(meal -> meal.setId(null));

        Menu created = menuService.create(menu);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/menus/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(value = "/menus/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Menu get(@PathVariable("id") int id) {
        return menuService.get(id);
    }

    @GetMapping(value = "/menus", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getBetween(@RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                 @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        if (startDate == null && endDate == null) {
            return menuService.getAll();
        } else {
            return menuService.getFiltered(null, startDate, endDate);
        }
    }

    @GetMapping(value = "/{restaurantId}/menus", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getBetweenByRestaurant(@PathVariable("restaurantId") int restaurantId,
                                             @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                             @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return menuService.getFiltered(restaurantId, startDate, endDate);
    }

    @GetMapping(value = "/menus/todays", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getAllTodays() {
        return menuService.getFiltered(null, LocalDate.now(), LocalDate.now());
    }

    @GetMapping(value = "/{restaurantId}/menus/todays", produces = MediaType.APPLICATION_JSON_VALUE)
    public Menu getTodaysByRestaurant(@PathVariable("restaurantId") int restaurantId) {
        List<Menu> menus = menuService.getFiltered(restaurantId, LocalDate.now(), LocalDate.now());
        if (menus.size() > 0) {
            return menus.get(0);
        } else {
            return null;
        }
    }

}

/*
    /rest/restaurants/menus?startDate&endDate --get(all/filtered)
    /rest/restaurants/menus/todays --get(all todays)
    /rest/restaurants/menus/{id} --get(id)

    /rest/restaurants/{id}/menus --post(new)
    /rest/restaurants/{id}/menus?startDate&endDate --get(all/filtered & by restaurant)
    /rest/restaurants/{id}/menus/todays --get(todays by restaurant)
*/
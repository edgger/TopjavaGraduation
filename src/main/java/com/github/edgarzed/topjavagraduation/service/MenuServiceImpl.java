package com.github.edgarzed.topjavagraduation.service;

import com.github.edgarzed.topjavagraduation.dao.MenuDAO;
import com.github.edgarzed.topjavagraduation.model.Menu;
import com.github.edgarzed.topjavagraduation.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    private final MenuDAO menuDAO;
    private final RestaurantService restaurantService;

    @Autowired
    public MenuServiceImpl(MenuDAO menuDAO, RestaurantService restaurantService) {
        this.menuDAO = menuDAO;
        this.restaurantService = restaurantService;
    }

    @CacheEvict(value = "menus", allEntries = true)
    @Override
    public Menu create(Menu menu) {
        return menuDAO.save(menu);
    }

    @Override
    public Menu get(int id) {
        return menuDAO.get(id);
    }

    @Cacheable("menus")
    @Override
    public List<Menu> getAll() {
        return menuDAO.getAll();
    }

    @Cacheable("menus")
    @Override
    public List<Menu> getAllTodays() {
        return menuDAO.getFiltered(null, LocalDate.now(), LocalDate.now());
    }

    @Override
    public List<Menu> getFiltered(Integer restaurantId, LocalDate startDate, LocalDate endDate) {
        Restaurant restaurant = null;
        if (restaurantId!=null){
            restaurant = restaurantService.get(restaurantId);
        }
        return menuDAO.getFiltered(restaurant, startDate, endDate);
    }
}

package com.github.edgarzed.topjavagraduation.service;

import com.github.edgarzed.topjavagraduation.model.Menu;

import java.time.LocalDate;
import java.util.List;

public interface MenuService {

    Menu create(Menu menu);

    Menu get(int id);

    List<Menu> getAll();

    List<Menu> getFiltered(Integer restaurantId, LocalDate startDate, LocalDate endDate);
}

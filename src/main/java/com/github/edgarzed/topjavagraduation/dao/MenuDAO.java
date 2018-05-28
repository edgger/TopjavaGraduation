package com.github.edgarzed.topjavagraduation.dao;

import com.github.edgarzed.topjavagraduation.model.Menu;
import com.github.edgarzed.topjavagraduation.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface MenuDAO {

    Menu save(Menu menu);

    Menu get(int id);

    List<Menu> getAll();

    List<Menu> getFiltered(Restaurant restaurant, LocalDate startDate, LocalDate endDate);
}

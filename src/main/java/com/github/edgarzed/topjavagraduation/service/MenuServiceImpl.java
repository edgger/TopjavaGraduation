package com.github.edgarzed.topjavagraduation.service;

import com.github.edgarzed.topjavagraduation.model.Menu;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Override
    public Menu create(Menu menu) {
        return null;
    }

    @Override
    public Menu get(int id) {
        return null;
    }

    @Override
    public List<Menu> getAll() {
        return null;
    }

    @Override
    public List<Menu> getFiltered(Integer restaurantId, LocalDate startDate, LocalDate endDate) {
        return null;
    }
}

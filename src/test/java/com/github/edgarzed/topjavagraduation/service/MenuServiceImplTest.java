package com.github.edgarzed.topjavagraduation.service;

import com.github.edgarzed.topjavagraduation.model.Menu;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.github.edgarzed.topjavagraduation.MenuTestData.*;
import static org.assertj.core.api.Assertions.assertThat;


public class MenuServiceImplTest extends AbstractServiceTest {

    @Autowired
    private MenuService menuService;

    @Before
    public void setUp() {
        cacheManager.getCache("menus").clear();
    }

    @Test
    public void create() {
        Menu expected = new Menu(MENUTODAY1);
        Menu created = menuService.create(MENUTODAY1);
        expected.setId(created.getId());

        assertThat(created).isEqualToIgnoringGivenFields(expected, "meals");
        assertThat(created.getMeals()).usingElementComparatorIgnoringFields("id").isEqualTo(expected.getMeals());
    }

    @Test
    public void get() {
        Menu menu = menuService.get(1000);
        assertThat(menu).isEqualToIgnoringGivenFields(MENU1, "meals");
        assertThat(menu.getMeals()).usingFieldByFieldElementComparator().isEqualTo(MENU1.getMeals());
    }

    @Test
    public void getAll() {
        assertThat(menuService.getAll()).usingElementComparatorIgnoringFields("meals").isEqualTo(MENUS);
    }

    @Test
    public void getAllTodays() {
        MENUTODAYS.forEach(menu -> menuService.create(menu));
        assertThat(menuService.getAllTodays()).usingDefaultElementComparator().isEqualTo(MENUTODAYS);
    }

    @Test
    public void getFiltered() {
        List<Menu> actual = menuService.getFiltered(1000, LocalDate.of(2018, 4, 22), LocalDate.of(2018, 4, 23));
        List<Menu> expected = Arrays.asList(MENU4, MENU1);
        assertThat(actual).hasSize(2).usingElementComparatorIgnoringFields("restaurant", "meals").isEqualTo(expected);
        assertThat(actual.get(0).getMeals()).usingFieldByFieldElementComparator().isEqualTo(expected.get(0).getMeals());
        assertThat(actual.get(1).getMeals()).usingFieldByFieldElementComparator().isEqualTo(expected.get(1).getMeals());
    }
}
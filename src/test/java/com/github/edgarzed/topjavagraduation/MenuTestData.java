package com.github.edgarzed.topjavagraduation;

import com.github.edgarzed.topjavagraduation.model.Meal;
import com.github.edgarzed.topjavagraduation.model.Menu;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.github.edgarzed.topjavagraduation.RestaurantTestData.*;

public class MenuTestData {
    private MenuTestData() {
    }

    public static final Menu MENU1 = new Menu(1000, RESTAURANT1, LocalDate.of(2018, 4, 22));
    public static final Menu MENU2 = new Menu(1001, RESTAURANT2, LocalDate.of(2018, 4, 22));
    public static final Menu MENU3 = new Menu(1002, RESTAURANT3, LocalDate.of(2018, 4, 22));
    public static final Menu MENU4 = new Menu(1003, RESTAURANT1, LocalDate.of(2018, 4, 23));
    public static final Menu MENU5 = new Menu(1004, RESTAURANT2, LocalDate.of(2018, 4, 23));
    public static final Menu MENU6 = new Menu(1005, RESTAURANT3, LocalDate.of(2018, 4, 23));

    public static final Menu MENUTODAY1 = new Menu(null, RESTAURANT1, LocalDate.now());
    public static final Menu MENUTODAY2 = new Menu(null, RESTAURANT3, LocalDate.now());
    public static final Menu MENUTODAY3 = new Menu(null, RESTAURANT2, LocalDate.now());

    public static final Menu NEWMENU = new Menu(null, RESTAURANT2, LocalDate.now().minusDays(1));

    public static final List<Menu> MENUS = Arrays.asList(MENU1, MENU2, MENU3, MENU4, MENU5, MENU6);
    public static final List<Menu> MENUTODAYS = Arrays.asList(MENUTODAY1, MENUTODAY2, MENUTODAY3);

    private static final Meal MEAL1 = new Meal(1000, "Dish1 r1 22", 15000, MENU1);
    private static final Meal MEAL2 = new Meal(1001, "Dish2 r1 22", 20000, MENU1);
    private static final Meal MEAL3 = new Meal(1002, "Dish3 r1 22", 25000, MENU1);
    private static final Meal MEAL4 = new Meal(1003, "Dish1 r2 22", 27000, MENU2);
    private static final Meal MEAL5 = new Meal(1004, "Dish2 r2 22", 29000, MENU2);
    private static final Meal MEAL6 = new Meal(1005, "Dish3 r2 22", 38000, MENU2);
    private static final Meal MEAL7 = new Meal(1006, "Dish1 r3 22", 31500, MENU3);
    private static final Meal MEAL8 = new Meal(1007, "Dish2 r3 22", 14000, MENU3);
    private static final Meal MEAL9 = new Meal(1008, "Dish3 r3 22", 26000, MENU3);
    private static final Meal MEAL10 = new Meal(1009, "Dish1 r1 23", 23000, MENU4);
    private static final Meal MEAL11 = new Meal(1010, "Dish2 r1 23", 33000, MENU4);
    private static final Meal MEAL12 = new Meal(1011, "Dish3 r1 23", 31000, MENU4);
    private static final Meal MEAL13 = new Meal(1012, "Dish1 r2 23", 14500, MENU5);
    private static final Meal MEAL14 = new Meal(1013, "Dish1 r3 23", 50000, MENU6);
    private static final Meal MEAL15 = new Meal(1014, "Dish1 r3 23", 46000, MENU6);
    private static final Meal MEAL16 = new Meal(1015, "Dish2 r3 23", 12000, MENU6);

    private static final Meal MEALTODAY1 = new Meal(null, "Dish1 r1 now", 1000, MENUTODAY1);
    private static final Meal MEALTODAY2 = new Meal(null, "Dish2 r1 now", 5000, MENUTODAY1);
    private static final Meal MEALTODAY3 = new Meal(null, "Dish3 r1 now", 1200, MENUTODAY1);
    private static final Meal MEALTODAY4 = new Meal(null, "Dish1 r3 now", 7000, MENUTODAY2);
    private static final Meal MEALTODAY5 = new Meal(null, "Dish2 r3 now", 3000, MENUTODAY2);
    private static final Meal MEALTODAY6 = new Meal(null, "Dish1 r3 now", 7000, MENUTODAY3);
    private static final Meal MEALTODAY7 = new Meal(null, "Dish2 r3 now", 3000, MENUTODAY3);

    private static final Meal NEWMEAL1 = new Meal(null, "Dish1 r2 now-1", 100, NEWMENU);
    private static final Meal NEWMEAL2 = new Meal(null, "Dish2 r2 now-1", 200, NEWMENU);

    static {
        Stream.of(MEAL1, MEAL2, MEAL3).forEach(MENU1::addMeal);
        Stream.of(MEAL4, MEAL5, MEAL6).forEach(MENU2::addMeal);
        Stream.of(MEAL7, MEAL8, MEAL9).forEach(MENU3::addMeal);
        Stream.of(MEAL10, MEAL11, MEAL12).forEach(MENU4::addMeal);
        Stream.of(MEAL13).forEach(MENU5::addMeal);
        Stream.of(MEAL14, MEAL15, MEAL16).forEach(MENU6::addMeal);

        Stream.of(MEALTODAY1, MEALTODAY2, MEALTODAY3).forEach(MENUTODAY1::addMeal);
        Stream.of(MEALTODAY4, MEALTODAY5).forEach(MENUTODAY2::addMeal);
        Stream.of(MEALTODAY6, MEALTODAY7).forEach(MENUTODAY3::addMeal);

        Stream.of(NEWMEAL1, NEWMEAL2).forEach(NEWMENU::addMeal);
    }
}
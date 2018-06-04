package com.github.edgarzed.topjavagraduation.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id","date"}, name = "restaurant_date_idx")})
@NamedEntityGraph(name = "graph.Menu.mealsAndRestaurant", attributeNodes = {@NamedAttributeNode("meals"),@NamedAttributeNode("restaurant")})
public class Menu extends AbstractBaseEntity{

    @JoinColumn(name = "restaurant_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "menu")
    @JsonManagedReference
    private List<Meal> meals;

    public Menu() {
    }

    public Menu(Menu menu) {
        super(null);
        this.restaurant = menu.restaurant;
        this.date = menu.date;
        this.meals = new ArrayList<>();

        menu.getMeals().forEach(meal -> this.addMeal(new Meal(meal)));
    }

    public Menu(Integer id, Restaurant restaurant, LocalDate date) {
        super(id);
        this.restaurant = restaurant;
        this.date = date;
        this.meals = new ArrayList<>();
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void addMeal(Meal meal) {
        this.meals.add(meal);
        meal.setMenu(this);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}

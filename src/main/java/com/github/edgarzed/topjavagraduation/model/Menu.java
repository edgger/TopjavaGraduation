package com.github.edgarzed.topjavagraduation.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id","date"}, name = "restaurant_date_idx")})
public class Menu extends AbstractBaseEntity{

    @JoinColumn(name = "restaurant_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "menu")
    private List<Meal> meals;

    public Menu() {
    }

    public Menu(Integer id) {
        super(id);
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
}

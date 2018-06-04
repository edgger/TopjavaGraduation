package com.github.edgarzed.topjavagraduation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "meals")
public class Meal extends AbstractNamedEntity{

    @Column(name = "price", nullable = false)
    private long price;

    @JoinColumn(name = "menu_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Menu menu;

    public Meal() {
    }

    public Meal(Meal meal) {
        super(null, meal.name);
        this.price = meal.price;
        this.menu = meal.menu;
    }

    public Meal(Integer id, String name, long price, Menu menu) {
        super(id, name);
        this.price = price;
        this.menu = menu;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

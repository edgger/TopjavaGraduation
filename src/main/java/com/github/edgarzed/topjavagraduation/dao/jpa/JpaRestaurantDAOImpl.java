package com.github.edgarzed.topjavagraduation.dao.jpa;

import com.github.edgarzed.topjavagraduation.dao.RestaurantDAO;
import com.github.edgarzed.topjavagraduation.model.Restaurant;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaRestaurantDAOImpl implements RestaurantDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.isNew()) {
            em.persist(restaurant);
            return restaurant;
        } else {
            return em.merge(restaurant);
        }
    }

    @Override
    public Restaurant get(int id) {
        return em.find(Restaurant.class, id);
    }

    @Override
    public List<Restaurant> getAll() {
        Query query = em.createQuery("SELECT r FROM Restaurant r ORDER BY r.name", Restaurant.class);
        return query.getResultList();
    }

    @Override
    public Restaurant getReference(int id){
        return em.getReference(Restaurant.class, id);
    }
}

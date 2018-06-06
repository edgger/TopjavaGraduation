package com.github.edgarzed.topjavagraduation.dao.jpa;

import com.github.edgarzed.topjavagraduation.dao.MenuDAO;
import com.github.edgarzed.topjavagraduation.model.Menu;
import com.github.edgarzed.topjavagraduation.model.Restaurant;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional(readOnly = true)
public class JpaMenuDAOImpl implements MenuDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Menu save(Menu menu) {
        if (menu.isNew()) {
            em.persist(menu);
            return menu;
        } else {
            return em.merge(menu);
        }
    }

    @Override
    public Menu get(int id) {
        EntityGraph graph = em.getEntityGraph("graph.Menu.mealsAndRestaurant");
        Map<String, Object> hints = new HashMap<>();
        hints.put("javax.persistence.fetchgraph", graph);
        return em.find(Menu.class, id, hints);
    }

    @Override
    public List<Menu> getAll() {
        Query query = em.createQuery("SELECT DISTINCT m FROM Menu m LEFT JOIN FETCH m.restaurant LEFT JOIN FETCH m.meals", Menu.class);
        return query.getResultList();
    }

    @Override
    public List<Menu> getFiltered(Restaurant restaurant, LocalDate startDate, LocalDate endDate) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Menu> menuQuery = cb.createQuery(Menu.class);
        Root<Menu> root = menuQuery.from(Menu.class);
        root.fetch("restaurant");
        root.fetch("meals");
        List<Predicate> predicates = getPredicates(cb, root, restaurant, startDate, endDate);

        menuQuery.select(root);
        menuQuery.where(predicates.toArray(new Predicate[0]));
        menuQuery.orderBy(cb.desc(root.get("date")));
        menuQuery.distinct(true);
        return em.createQuery(menuQuery).getResultList();
    }

    private static List<Predicate> getPredicates(CriteriaBuilder cb, Root<Menu> root, Restaurant restaurant, LocalDate startDate, LocalDate endDate) {
        List<Predicate> predicates = new ArrayList<>();

        if (restaurant != null) {
            predicates.add(cb.equal(root.get("restaurant").get("id"), restaurant.getId()));
        }

        JpaUtil.getDatePredicates(cb, startDate, endDate, predicates, root.get("date"));

        return predicates;
    }


}

package com.github.edgarzed.topjavagraduation.dao.jpa;

import com.github.edgarzed.topjavagraduation.dao.VoteDAO;
import com.github.edgarzed.topjavagraduation.model.User;
import com.github.edgarzed.topjavagraduation.model.Vote;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaVoteDAOImpl implements VoteDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Vote save(Vote vote) {
        if (vote.isNew()) {
            em.persist(vote);
            return vote;
        } else {
            return em.merge(vote);
        }
    }

    @Override
    public List<Vote> getAll() {
        Query query = em.createQuery("SELECT v FROM Vote v LEFT JOIN FETCH v.restaurant LEFT JOIN FETCH v.user LEFT JOIN FETCH v.user.roles ORDER BY v.date DESC, v.restaurant.name ", Vote.class);
        return query.getResultList();
    }

    @Override
    public List<Vote> getFiltered(User user, LocalDate startDate, LocalDate endDate) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Vote> voteQuery = cb.createQuery(Vote.class);
        Root<Vote> root = voteQuery.from(Vote.class);
        root.fetch("restaurant");
        root.fetch("user").fetch("roles");
        List<Predicate> predicates = getPredicates(cb, root, user, startDate, endDate);

        voteQuery.select(root);
        voteQuery.where(predicates.toArray(new Predicate[0]));
        voteQuery.orderBy(cb.desc(root.get("date")));
        return em.createQuery(voteQuery).getResultList();
    }

    private static List<Predicate> getPredicates(CriteriaBuilder cb, Root<Vote> root, User user, LocalDate startDate, LocalDate endDate) {
        List<Predicate> predicates = new ArrayList<>();

        if (user != null) {
            predicates.add(cb.equal(root.get("user").get("id"), user.getId()));
        }

        JpaUtil.getDatePredicates(cb, startDate, endDate, predicates, root.get("date"));

        return predicates;
    }
}

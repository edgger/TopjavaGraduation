package com.github.edgarzed.topjavagraduation.dao.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.List;

class JpaUtil {
    private JpaUtil() {
    }

    static void getDatePredicates(CriteriaBuilder cb, LocalDate startDate, LocalDate endDate, List<Predicate> predicates, Path<LocalDate> date) {
        if (startDate != null && endDate != null) {
            predicates.add(cb.between(date, startDate, endDate));
        } else if (startDate != null) {
            predicates.add(cb.greaterThanOrEqualTo(date, startDate));
        } else if (endDate != null) {
            predicates.add(cb.lessThan(date, endDate));
        }
    }
}

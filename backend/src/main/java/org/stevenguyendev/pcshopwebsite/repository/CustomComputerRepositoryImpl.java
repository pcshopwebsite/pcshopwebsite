package org.stevenguyendev.pcshopwebsite.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.util.Strings;
import org.stevenguyendev.pcshopwebsite.model.Computer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CustomComputerRepositoryImpl implements CustomComputerRepository {

    private final EntityManager entityManager;

    public CustomComputerRepositoryImpl(
            EntityManager entityManager
    ) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Computer> getFilteredAndSortedComputers(
            String name,
            List<String> brands,
            List<String> categories,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Float minRating,
            String sortBy,
            String order,
            int page,
            int size
    ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Computer> cq = cb.createQuery(Computer.class);
        Root<Computer> computer = cq.from(Computer.class);
        List<Predicate> predicates = new ArrayList<>();
        /*
          Don't change this order of path in multiselect as it's the order
          presented in supported constructor of Computer entity. This order
          is to ensure that JPA CriteriaBuilder can map the result to a Computer
          object in return.
         */
        cq.multiselect(
                computer.get("id"),
                computer.get("name"),
                computer.get("description"),
                computer.get("price"),
                computer.get("rating"),
                computer.get("thumbnail"),
                computer.get("category"),
                computer.get("brand"),
                computer.get("createdAt"),
                computer.get("updatedAt")
        );
        if (Strings.isNotEmpty(name)) {
            predicates.add(cb.like(computer.get("name"), "%" + name + "%"));
        }
        if (brands != null && !brands.isEmpty()) {
            predicates.add(cb.in(computer.get("brand").get("name")).value(brands));
        }
        if (categories != null && !categories.isEmpty()) {
            predicates.add(cb.in(computer.get("category").get("name")).value(categories));
        }
        if (minPrice != null) {
            predicates.add(cb.greaterThanOrEqualTo(computer.get("price"), minPrice));
        }
        if (maxPrice != null) {
            predicates.add(cb.lessThanOrEqualTo(computer.get("price"), maxPrice));
        }
        if (minRating != null) {
            predicates.add(cb.greaterThanOrEqualTo(computer.get("rating"), minRating));
        }
        if (!predicates.isEmpty()) {
            cq.where(cb.and(predicates.toArray(new Predicate[0])));
        }
        if (Strings.isNotEmpty(sortBy)) {
            if (order.equals("asc")) {
                cq.orderBy(cb.asc(computer.get(sortBy)));
            } else {
                cq.orderBy(cb.desc(computer.get(sortBy)));
            }
        }

        return entityManager.createQuery(cq)
                            .setFirstResult(page * size)
                            .setMaxResults(size)
                            .getResultList();
    }
}

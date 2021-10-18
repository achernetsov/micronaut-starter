package com.prokurelabs.app.repository;


import com.prokurelabs.app.model.Order;
import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

// https://guides.micronaut.io/latest/micronaut-jpa-hibernate-gradle-java.html#solution
@Singleton
public class OrdersRepositoryImpl implements OrdersRepository {
    private static final Integer MAX_PAGE_SIZE = 50;

    private final EntityManager entityManager;

    private final static List<String> VALID_PROPERTY_NAMES = List.of("id");

    public OrdersRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @ReadOnly
    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Order.class, id));
    }

    @Override
    @Transactional
    public Order save(Order order) {
        entityManager.persist(order);
        return order;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        findById(id).ifPresent(entityManager::remove);
    }

    @Override
    public List<Order> findAll(@NotNull SortingAndOrderArguments args) {
        String qlString = "SELECT o FROM Order as o";
        if (args.getOrder().isPresent() && args.getSort().isPresent() && VALID_PROPERTY_NAMES.contains(args.getSort().get())) {
            qlString += " ORDER BY o." + args.getSort().get() + " " + args.getOrder().get().toLowerCase();
        }
        TypedQuery<Order> query = entityManager.createQuery(qlString, Order.class);
        query.setMaxResults(args.getMax().orElse(MAX_PAGE_SIZE));
        args.getOffset().ifPresent(query::setFirstResult);

        return query.getResultList();
    }
}

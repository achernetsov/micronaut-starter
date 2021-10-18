package com.prokurelabs.app.repository;

import com.prokurelabs.app.model.Order;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface OrdersRepository {
    Optional<Order> findById(@NotNull Long id);

    Order save(@NotNull Order order);

    void deleteById(@NotNull Long id);

    List<Order> findAll(@NotNull SortingAndOrderArguments args);
}

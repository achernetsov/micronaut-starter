package com.prokurelabs.app.repository;

import com.prokurelabs.app.model.OrderDraft;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface OrderDraftsRepository {
    Optional<OrderDraft> findById(@NotNull Long id);

    OrderDraft save(@NotNull OrderDraft order);

    void deleteById(@NotNull Long id);

    List<OrderDraft> findAll(@NotNull SortingAndOrderArguments args);
}

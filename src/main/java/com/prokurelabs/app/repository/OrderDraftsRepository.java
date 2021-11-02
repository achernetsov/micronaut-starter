package com.prokurelabs.app.repository;

import com.prokurelabs.app.model.OrderDraft;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * https://micronaut-projects.github.io/micronaut-data/latest/guide/
 *
 * instead of low-level https://guides.micronaut.io/latest/micronaut-jpa-hibernate-gradle-java.html#solution
 */
@Repository
public interface OrderDraftsRepository extends CrudRepository<OrderDraft, Long> {
}

package com.prokurelabs.app.repository;

import com.prokurelabs.app.model.OrderDraft;
import io.micronaut.context.BeanContext;
import io.micronaut.data.annotation.Query;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class OrdersRepositoryImplTest {

    @Inject
    OrderDraftsRepository repository;
    @Inject
    BeanContext beanContext;

    @Test
    public void testCrud() {
        OrderDraft order = new OrderDraft("ownerId", "description");
        Long savedId = repository.save(order).getId();

        OrderDraft saved = repository.findById(savedId).orElseThrow();
        assertEquals("ownerId", saved.getOwnerId());
    }

    @Test
    void testAnnotationMetadata() {
        String query = beanContext.getBeanDefinition(OrderDraftsRepository.class)
                .getRequiredMethod("findById", Long.class)
                .getAnnotationMetadata().stringValue(Query.class)
                .orElse(null);

        assertEquals(
                "SELECT orderDraft_ FROM com.prokurelabs.app.model.OrderDraft AS orderDraft_ " +
                        "WHERE (orderDraft_.id = :p1)", query);

    }
}
package com.prokurelabs.app.repository;

import com.prokurelabs.app.model.OrderDraft;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class OrdersRepositoryImplTest {

    @Inject
    OrderDraftsRepository repository;

    @Test
    public void testCrud() {
        OrderDraft order = new OrderDraft("ownerId", "description");
        Long savedId = repository.save(order).getId();

        OrderDraft saved = repository.findById(savedId).orElseThrow();
        assertEquals("ownerId", saved.getOwnerId());
    }
}
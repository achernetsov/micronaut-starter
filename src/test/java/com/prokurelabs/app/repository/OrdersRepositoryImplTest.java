package com.prokurelabs.app.repository;

import com.prokurelabs.app.model.Order;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class OrdersRepositoryImplTest {

    @Inject
    OrdersRepository repository;

    @Test
    public void testCrud() {
        Order order = new Order("ownerId");
        Long savedId = repository.save(order).getId();

        Order saved = repository.findById(savedId).orElseThrow();
        assertEquals("ownerId", saved.getOwnerId());
    }
}
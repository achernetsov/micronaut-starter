package com.prokurelabs.app.controller;

import com.prokurelabs.app.model.OrderDraft;
import io.micronaut.context.annotation.Property;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings({"rawtypes", "unchecked"})
@MicronautTest
@Property(name = "micronaut.security.enabled", value = "false")
class OrderDraftsControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Inject
    EntityManager entityManager;

    @Test
    public void supplyAnInvalidSortingOrderTriggersValidationFailure() {
        HttpClientResponseException thrown = assertThrows(HttpClientResponseException.class, () -> {
            client.toBlocking().exchange(HttpRequest.GET("/orderDrafts/list?order=foo"));
        });

        assertNotNull(thrown.getResponse());
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatus());
    }

    @Test
    public void testFindNonExistingOrderDraftReturns404() {
        HttpClientResponseException thrown = assertThrows(HttpClientResponseException.class, () -> {
            client.toBlocking().exchange(HttpRequest.GET("/orderDrafts/99"));
        });

        assertNotNull(thrown.getResponse());
        assertEquals(HttpStatus.NOT_FOUND, thrown.getStatus());
    }

    @Test
    public void testCrud() {
        HttpRequest request = HttpRequest.POST("/orderDrafts", new OrderDraftsController.OrderDraftSaveCommand("description"));
        HttpResponse response = client.toBlocking().exchange(request);
        Long savedOrderDraftId = entityId(response);

        assertEquals(HttpStatus.CREATED, response.getStatus());
        //noinspection ConstantConditions
        assertTrue(savedOrderDraftId>0);

        request = HttpRequest.GET("/orderDrafts/" + savedOrderDraftId);
        OrderDraft orderDraft = client.toBlocking().retrieve(request, OrderDraft.class);
        assertEquals("description", orderDraft.getDescription());
//
//        request = HttpRequest.GET("/orderDrafts/list");
//        List<OrderDraft> orderDrafts = client.toBlocking().retrieve(request, Argument.of(List.class, OrderDraft.class));
    }


    private Long entityId(HttpResponse response) {
        String path = "/orderDrafts/";
        String value = response.header(HttpHeaders.LOCATION);
        if (value == null) {
            return null;
        }
        int index = value.indexOf(path);
        if (index != -1) {
            return Long.valueOf(value.substring(index + path.length()));
        }
        return null;
    }
}
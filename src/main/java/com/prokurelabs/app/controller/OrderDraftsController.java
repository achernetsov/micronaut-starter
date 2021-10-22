package com.prokurelabs.app.controller;


import com.prokurelabs.app.model.OrderDraft;
import com.prokurelabs.app.repository.OrderDraftsRepository;
import com.prokurelabs.app.repository.SortingAndOrderArguments;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/orderDrafts")
public class OrderDraftsController {

    @Introspected
    record OrderDraftSaveCommand(String description) {
    }

    private final OrderDraftsRepository orderDraftsRepository;

    public OrderDraftsController(OrderDraftsRepository orderDraftsRepository) {
        this.orderDraftsRepository = orderDraftsRepository;
    }

    @Get("/{id}")
    public OrderDraft get(Long id) {
        return orderDraftsRepository.findById(id).orElse(null);
    }

    @Get(value = "/list{?args*}")
    public List<OrderDraft> list(@Valid SortingAndOrderArguments args) {
        return orderDraftsRepository.findAll(args);
    }

    @Post
    public HttpResponse<OrderDraft> save(@Body @Valid OrderDraftSaveCommand command) {
        // TODO get principal for ownerId
        OrderDraft orderDraft = new OrderDraft("ownerId", command.description);
        final OrderDraft saved = orderDraftsRepository.save(orderDraft);
        return HttpResponse
                .created(orderDraft)
                .headers(headers -> headers.location(location(saved)));
    }

    private URI location(OrderDraft orderDraft) {
        return URI.create("/orderDrafts/" + orderDraft.getId());
    }
}

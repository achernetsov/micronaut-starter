package com.prokurelabs.app.controller;


import com.prokurelabs.app.model.OrderDraft;
import com.prokurelabs.app.repository.OrderDraftsRepository;
import com.prokurelabs.app.repository.SortingAndOrderArguments;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.views.View;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RolesAllowed({"ROLE_CUSTOMER"})
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

    @Transactional
    @Get
    @View("orderDrafts")
    public Map<String, Object> orderDrafts() {
        Map<String, Object> model = new HashMap<>();
        SortingAndOrderArguments args = new SortingAndOrderArguments();
        List<OrderDraft> orderDrafts = orderDraftsRepository.findAll(args);
        model.put("orderDrafts", orderDrafts);
        model.put("loggedIn", true);
        return model;
    }

    @Get("/new")
    @View("orderDraft")
    public Map<String, Object> newOrderDraft(Authentication authentication) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("loggedIn", true);
        return data;
    }

    @Get("/{id}")
    public OrderDraft get(Long id) {
        return orderDraftsRepository.findById(id).orElse(null);
    }

    // TODO use this for ajax requests
    @Get(value = "/list{?args*}")
    public List<OrderDraft> list(@Valid SortingAndOrderArguments args) {
        return orderDraftsRepository.findAll(args);
    }

    // TODO use this for ajax requests
    @Post("/save")
    public HttpResponse<OrderDraft> save(@Body @Valid OrderDraftSaveCommand command) {
        // TODO get principal for ownerId
        OrderDraft orderDraft = new OrderDraft("ownerId", command.description);
        final OrderDraft saved = orderDraftsRepository.save(orderDraft);
        return HttpResponse
                .created(orderDraft)
                .headers(headers -> headers.location(location(saved)));
    }

    @Post(value = "/saveForm", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<String> saveForm(Authentication authentication, @Body Map<String, String> form) {
        OrderDraft orderDraft = new OrderDraft(authentication.getName(),
                form.get("description"));
        final OrderDraft saved = orderDraftsRepository.save(orderDraft);
        return HttpResponse.redirect(URI.create("/orderDrafts"));
    }

    private URI location(OrderDraft orderDraft) {
        return URI.create("/orderDrafts/" + orderDraft.getId());
    }
}

package com.prokurelabs.app.controller;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller
public class HomeController {
    @Get
//    @View("home")
    HttpResponse<String> index(@Nullable Authentication authentication) throws URISyntaxException {
//        Map<String, Object> data = new HashMap<>();
//        data.put("loggedIn", principal != null);
//        if (principal != null) {
//            data.put("username", principal.getName());
//        }
//        return data;

        if (Objects.isNull(authentication)) {
            return HttpResponse.redirect(new URI("/login/auth"));
        }
        if (authentication.getRoles().contains("ROLE_CUSTOMER")) {
            return HttpResponse.redirect(new URI("/orderDrafts"));
        }
        throw new RuntimeException("TODO");
    }
}

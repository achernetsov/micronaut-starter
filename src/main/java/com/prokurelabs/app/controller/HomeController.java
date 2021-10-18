package com.prokurelabs.app.controller;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.views.View;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller
public class HomeController {
    @Get
    @View("home")
    Map<String, Object> index(@Nullable Principal principal) { // <5>
        Map<String, Object> data = new HashMap<>();
        data.put("loggedIn", principal != null);
        if (principal != null) {
            data.put("username", principal.getName());
        }
        return data;
    }
}

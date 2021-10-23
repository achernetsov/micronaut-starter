package com.prokurelabs.app.security;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.Set;

//
@Singleton
public class AuthenticationProviderUserPassword implements AuthenticationProvider {
    private static Logger logger = LoggerFactory.getLogger(AuthenticationProvider.class);

    @Override
    public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest,
                                                          AuthenticationRequest<?, ?> authenticationRequest) {
        return Mono.create(emitter -> {
            Object identity = authenticationRequest.getIdentity();
            Object secret = authenticationRequest.getSecret();
            if (identity.equals("test") && secret.equals("123")) {
                emitter.success(
                        AuthenticationResponse.success(
                                (String) authenticationRequest.getIdentity(),
                                Set.of("ROLE_CUSTOMER"))
                );
            } else if (identity.equals("admin") && secret.equals("123")) {
                emitter.success(
                        AuthenticationResponse.success(
                                (String) authenticationRequest.getIdentity(),
                                Set.of("ROLE_ADMIN"))
                );
            } else {
                emitter.error(AuthenticationResponse.exception());
            }
        });
    }
}

package com.tistory.aircook.webflux.router;

import com.tistory.aircook.webflux.handler.HelloHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
@RequiredArgsConstructor
public class HelloRouter {

    private final HelloHandler helloHandler;

    @Bean
    public RouterFunction<ServerResponse> helloRoute() {
        return RouterFunctions.route(RequestPredicates.GET("/hello/{name}")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), helloHandler::hello);
    }

    @Bean
    public RouterFunction<ServerResponse> routes1() {
        return RouterFunctions.route(RequestPredicates.GET("/routes1"),
                req -> ok().body(Flux.just("Hello", "World1"), String.class));
    }

    @Bean
    public RouterFunction<ServerResponse> routes2() {
        return route(GET("/routes2"),
                req -> ok().body(Flux.just("Hello", "World2"), String.class));
    }


}

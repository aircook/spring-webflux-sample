package com.tistory.aircook.webflux.router;

import com.tistory.aircook.webflux.handler.RedisHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class RedisRouter {

    private final RedisHandler redisHandler;

    @Bean
    public RouterFunction<ServerResponse> basicRoute() {
        return RouterFunctions.route()
                .GET("/reactive-list", serverRequest ->
                        ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                        .body(redisHandler.findReactorList(), String.class))
                .GET("/normal-list", serverRequest ->
                        ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                        .body(redisHandler.findNormalList(), String.class))
                .GET("/load", serverRequest -> {
                    redisHandler.loadData();
                    return ServerResponse.ok()
                            .body(BodyInserters.fromValue("Load Data Completed"));
                })
                .build();
    }

}

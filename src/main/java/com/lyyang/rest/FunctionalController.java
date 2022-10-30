package com.lyyang.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Slf4j
public class FunctionalController {
    HandlerFunction<ServerResponse> timeFunction = request -> ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
            .body(Mono.just("Now is HIHI"),String.class);

    RouterFunction<ServerResponse> router = RouterFunctions.route(GET("fHello"),timeFunction);
}

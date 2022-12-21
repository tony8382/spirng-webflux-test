package com.lyyang.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ExampleWebFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange,
                             WebFilterChain webFilterChain) {

        log.info("hihi:{}", serverWebExchange.getRequest().getPath());

        if (serverWebExchange.getRequest().getPath().toString().equals("/fhello2")) {
            log.info("match");

            serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return serverWebExchange.getResponse().setComplete();

        }
        serverWebExchange.getResponse()
                .getHeaders().add("web-filter", "web-filter-test");

        Mono<Void> result = webFilterChain.filter(serverWebExchange);


        return result.doOnSuccess((c)->{
            log.info("HIHI S:{}", serverWebExchange.getResponse().getStatusCode());
        });
    }
}
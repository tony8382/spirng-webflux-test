package com.lyyang.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
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
        HttpHeaders headers = serverWebExchange.getResponse()
                .getHeaders();

        ServerHttpRequest s = serverWebExchange.getRequest();
        log.info("protocol:{}, host:{}, port:{}, path:{}", s.getURI().getScheme(), s.getURI().getHost(), s.getURI().getPort(), s.getPath().toString());

        log.info("domain:{}", s.getURI());
        setCorsHeader(headers, serverWebExchange.getRequest().getHeaders().getFirst("Origin"));

        Mono<Void> result = webFilterChain.filter(serverWebExchange);


        return result.doOnSuccess((c) -> {
            log.info("HIHI S:{}", serverWebExchange.getResponse().getStatusCode());
        });
    }

    private void setCorsHeader(HttpHeaders headers, String origin) {

        headers.add("access-control-allow-credentials", "true");
        headers.add("access-control-allow-headers", "Content-Type");
        headers.add("access-control-allow-methods", "*");
        headers.add("access-control-allow-origin", "https://tw.yahoo.com");
        headers.add("access-control-max-age", String.valueOf(24 * 60 * 60));

    }
}
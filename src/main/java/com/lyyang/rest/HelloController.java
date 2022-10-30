package com.lyyang.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class HelloController {
    @GetMapping("hello")
    public Mono<String> hello() {
        log.info("HGGGGH");

        return Mono.just("welcome to reactiveDDD web");
    }

    @GetMapping("hello")
    public String hello2() {
        log.info("HGGGGH");

        return "welcome to reactiveDDD2web";
    }
}

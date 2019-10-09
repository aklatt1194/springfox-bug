package com.example.springfoxbug.controller;

import com.example.springfoxbug.model.LatLong;
import com.example.springfoxbug.model.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/dummy")
public class DummyController {

    @RequestMapping(method = RequestMethod.GET, value = "", produces = "application/json")
    @ApiOperation(value = "do the thing", response = Response.class)
    public Mono<Response> get() {
        return Mono.just(Response.builder().latLong(new LatLong(1d, 10d)).dub(100d).build());
    }
}

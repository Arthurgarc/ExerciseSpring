package com.example.springclienteprojeto.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "movieMinimalRest", url = "${omdbapi.url}")
public interface MovieMinimalRestRepository {

    @GetMapping
    ResultSearch search(@RequestParam("s") String movieTitle);

    @GetMapping
    MovieDetail detail(@RequestParam("i") String imdbId);
}

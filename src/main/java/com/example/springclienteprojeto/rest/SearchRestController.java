package com.example.springclienteprojeto.rest;

import com.example.springclienteprojeto.client.MovieDetail;
import com.example.springclienteprojeto.client.MovieMinimal;
import com.example.springclienteprojeto.client.MovieMinimalRestRepository;
import com.example.springclienteprojeto.client.ResultSearch;
import com.example.springclienteprojeto.csv.CsvRecordRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Component
@Log4j2
@RestController
public class SearchRestController {

    private final MovieMinimalRestRepository rest;
    private final CsvRecordRepository csvRecordRepository;

    public SearchRestController(MovieMinimalRestRepository rest) {
        this.rest = rest;
        this.csvRecordRepository = new CsvRecordRepository();
    }

    @GetMapping("/search")
    public ResultSearch search(@RequestParam String title) {
        final List<MovieMinimal> movieMinimalList = this.csvRecordRepository.search(title);
        if (movieMinimalList.isEmpty()) {
            log.info("Não há resultados localmente para {}.", title);
            final ResultSearch busca = this.rest.search(title);
            if (busca.getResponse()) {
                log.debug("Atualizando arquivo");
                this.csvRecordRepository.escrever(title, busca);
            }
            return busca;
        } else {
            return new ResultSearch(movieMinimalList, movieMinimalList.size(), true);
        }
    }

    @GetMapping("/movies/{id}")
    public MovieDetail detail(@PathVariable String id) {
        log.debug("Detalhando o filme de id: {} na api remota.", id);
        return this.rest.detail(id);
    }
}

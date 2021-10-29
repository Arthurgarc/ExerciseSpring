package com.example.springclienteprojeto.csv;

import com.example.springclienteprojeto.client.MovieMinimal;
import com.example.springclienteprojeto.client.ResultSearch;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class CsvRecordRepository {

    private final String path;
    private List<CsvRecord> recordList;

    public CsvRecordRepository() {
        this.path = Optional.ofNullable(getClass().getClassLoader().getResource("cache.csv"))
                .map(url -> new File(url.getFile()).getPath())
                .orElseThrow();
        this.updateRecordList();
    }

    private void updateRecordList() {
        try (Stream<String> lines = Files.lines(Path.of(this.path))) {
            this.recordList = lines
                    .map(CsvRecord::fromLine)
                    .collect(Collectors.toList());
        } catch (IOException e) {
        }
    }

    //search no csv
    public List<MovieMinimal> search(String title) {
        return this.recordList.stream()
                .filter(cr -> cr.getTitle().equalsIgnoreCase(title) && cr.getResponse())
                .map(CsvRecord::getMovieMinimal)
                .collect(Collectors.toList());
    }

    public void escrever(String title, ResultSearch resultSearch) {
        int total = resultSearch.getTotal();
        boolean response = resultSearch.getResponse();
        resultSearch.getResultList()
                .forEach(mm -> this.escreverLinha(title, total, response, mm.getImdbId(), mm.getTitle(), mm.getYear()));
        this.updateRecordList();
    }

    private void escreverLinha(String title, int total, boolean response, String imdbId, String movieTitle,
                               Integer year) {
        String linha = title + ";" + total + ";" + response + ";" + imdbId + ";" + movieTitle + ";" + year + "\n";
        try {
            Files.writeString(Path.of(this.path), linha, StandardOpenOption.APPEND);
        } catch (IOException e) {
        }
    }

}

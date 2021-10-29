package com.example.springclienteprojeto.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;

@Value
public class MovieMinimal {

    String imdbId;
    String title;
    Integer year;

    @JsonCreator
    public MovieMinimal(String imdbId, String title, String year) {
        this.imdbId = imdbId;
        this.title = title;
        this.year = DateConverter.convertYear(year);
    }

}

package com.example.springclienteprojeto.client;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.Optional;
import lombok.Value;

@Value
public class MovieDetail {

    String imdbId;
    String title;
    Integer year;
    LocalDate released;
    Double rating;
    Integer votes;
    String type;

    public MovieDetail(String imdbId, String title, Integer year, LocalDate released, Double rating, Integer votes,
                       String type) {
        this.imdbId = imdbId;
        this.title = title;
        this.year = year;
        this.released = released;
        this.rating = rating;
        this.votes = votes;
        this.type = type;
    }

    @JsonCreator
    public MovieDetail(String imdbId, String title, String year, String released,
                       @JsonProperty("imdbRating") String rating, @JsonProperty("imdbVotes") String votes,
                       String type) {
        this(imdbId, title, DateConverter.convertYear(year), DateConverter.convertDate(released),
                Optional.ofNullable(rating).map(Double::parseDouble).orElse(0d),
                Optional.ofNullable(votes).map(s -> Integer.parseInt(s.replace(",", ""))).orElse(0), type);
    }

}

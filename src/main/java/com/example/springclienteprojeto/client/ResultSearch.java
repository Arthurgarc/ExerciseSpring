package com.example.springclienteprojeto.client;

import static java.lang.Boolean.valueOf;
import static java.lang.Integer.parseInt;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class ResultSearch {

    List<MovieMinimal> resultList;
    Integer total;
    Boolean response;

    @JsonCreator
    public ResultSearch(
            @JsonProperty("Busca") List<MovieMinimal> resultList,
            @JsonProperty("Resultados") String total,
            @JsonProperty("Resposta") String response) {
        this(resultList, parseInt(total), valueOf(response));
    }
}
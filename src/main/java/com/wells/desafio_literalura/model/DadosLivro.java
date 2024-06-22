package com.wells.desafio_literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(@JsonAlias("title") String titulo,
        @JsonAlias("subtitle") String subtitulo,
        @JsonAlias("authors") List<String> listaAutores,
        @JsonAlias("categories") List<String> listaCategorias,
        @JsonAlias("releaseDate") String dataLancamento) {
}

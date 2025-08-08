package com.alura.lucans.livroapi.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(@JsonAlias("title") String titulo,
                         @JsonAlias("authors") String autor,
                         @JsonAlias("languages") String idiomas,
                         @JsonAlias("download_count") Integer downloads) {

}

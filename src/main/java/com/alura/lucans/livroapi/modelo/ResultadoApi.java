package com.alura.lucans.livroapi.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record ResultadoApi(@JsonAlias("results") List<DadosLivro> resposta ) {
}

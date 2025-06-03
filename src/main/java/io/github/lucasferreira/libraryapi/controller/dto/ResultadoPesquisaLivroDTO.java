package io.github.lucasferreira.libraryapi.controller.dto;

import io.github.lucasferreira.libraryapi.model.GeneroLivro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ResultadoPesquisaLivroDTO(
        UUID id,
        String isbn,
        String titulo,
        LocalDate datapublicacao,
        GeneroLivro genero,
        BigDecimal preco,
        AutorDTO autorDTO
) {
}

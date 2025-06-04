package io.github.lucasferreira.libraryapi.controller.mappers;

import io.github.lucasferreira.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.lucasferreira.libraryapi.model.Livro;
import io.github.lucasferreira.libraryapi.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class LivroMapper {

    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java( autorRepository.findById(dto.idAutor()).orElse(null))")
    public abstract Livro toEntity(CadastroLivroDTO dto);

}

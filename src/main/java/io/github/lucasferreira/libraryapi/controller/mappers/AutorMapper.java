package io.github.lucasferreira.libraryapi.controller.mappers;

import io.github.lucasferreira.libraryapi.controller.dto.AutorDTO;
import io.github.lucasferreira.libraryapi.model.Autor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    @Mapping(source = "nome",target = "nome")
    Autor toEntity(AutorDTO dto);

    AutorDTO toDTO(Autor autor);
}

package io.github.lucasferreira.libraryapi.controller;

import io.github.lucasferreira.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.lucasferreira.libraryapi.controller.dto.ErroResposta;
import io.github.lucasferreira.libraryapi.controller.mappers.LivroMapper;
import io.github.lucasferreira.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.lucasferreira.libraryapi.model.Livro;
import io.github.lucasferreira.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController implements GenericController{
    private final LivroService service;
    private final LivroMapper mapper;


    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid CadastroLivroDTO dto){
        try{
            Livro livro = mapper.toEntity(dto);
            service.salvar(livro);
            var url = gerarHeaderLocation(livro.getId());
            //mapear dto para entidade
             //mapear a entidade para o service validar e salvar no service
             //criar uma url para acesso dos dados do livro
             //retornar codigo created com header location
            //
            return ResponseEntity.created(url).build();
        }catch (RegistroDuplicadoException e){
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }
}

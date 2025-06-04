package io.github.lucasferreira.libraryapi.service;

import io.github.lucasferreira.libraryapi.model.Livro;
import io.github.lucasferreira.libraryapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;

    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }


}

package io.github.lucasferreira.libraryapi.service;

import io.github.lucasferreira.libraryapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LivroService {
    private final LivroRepository repository;
}

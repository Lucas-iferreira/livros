package io.github.lucasferreira.libraryapi.repository;

import io.github.lucasferreira.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
}

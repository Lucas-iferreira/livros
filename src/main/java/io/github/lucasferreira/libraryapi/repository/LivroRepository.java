package io.github.lucasferreira.libraryapi.repository;

import io.github.lucasferreira.libraryapi.model.Autor;
import io.github.lucasferreira.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    //Query Method

    //SELECT * FROM livro WHERE id_autor = id
    @Query
    List<Livro> findLivroByAutor(Autor autor);


    //SELECT * FROM livro WHERE titulo = titulo
    @Query
    List<Livro> findByTitulo(String titulo);

    //SELECT * FROM livro WHERE isbn = ?
    @Query
    List<Livro> findByIsbn(String isbn);

    //SELECT * FROM livro WHERE titulo = ? AND preco = ?
    @Query
    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);


    //SELECT * FROM livro WHERE titulo = ? OR isbnb= ?
    @Query
    List<Livro> findByTituloOrIsbn(String titulo, String isbn);


    //SELECT * FROM livro WHERE data_publicacao between ? and ?
    @Query
    List<Livro> findByDataPublicacaoBetween(LocalDate inicio,LocalDate fim);
}

package io.github.lucasferreira.libraryapi.repository;

import io.github.lucasferreira.libraryapi.model.Autor;
import io.github.lucasferreira.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @see LivroRepositoryTest
 */
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


    //JPQL - Referencia as entidades e as propriedades
    //SELECT l.* FROM  livro AS l ORDER BY l.titulo
    //me retorne um objeto livro pelo seu nome que na entidade Livro é o atributo "titulo" e pelo atributo "preco"
    @Query(" SELECT l FROM Livro AS l ORDER BY l.titulo, l.preco ")
    List<Livro> listarTodosOrdenadoPorTituloAndPreco();

    /**
     * SELECT a.*
     * FROM livro l
     * JOIN autor a ON a.id = l.id_autor
     */
    @Query(" SELECT a FROM Livro l join l.autor a ")
    List<Autor> listarAutoresDosLivros();

    @Query("""
            SELECT l.genero
            FROM Livro l
            join l.autor a
            WHERE a.nacionalidade = 'Brasileira'
            ORDER BY l.genero
            """)
    List<String> listarGenerosAutoresBrasileiros();
}

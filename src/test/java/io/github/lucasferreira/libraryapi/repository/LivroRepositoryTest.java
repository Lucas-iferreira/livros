package io.github.lucasferreira.libraryapi.repository;

import io.github.lucasferreira.libraryapi.model.Autor;
import io.github.lucasferreira.libraryapi.model.GeneroLivro;
import io.github.lucasferreira.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("9888845-1234");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 05, 25));

        Autor autor = autorRepository.findById(UUID.fromString("11f84f18-6b80-404b-b00d-321f7ad31340")).get();


        livro.setAutor(autor);
        repository.save(livro);

        System.out.println("O usuario " + livro + " foi salvo no banco de teste!");

    }

    @Test
    void salvarAutorELivroTest() {
        Livro livro = new Livro();

        livro.setIsbn("9888845-1234");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Segundo Livro");
        livro.setDataPublicacao(LocalDate.of(1980, 05, 25));


        Autor autor = new Autor();
        autor.setNome("João");
        autor.setNacionalidade("Canadense");
        autor.setDataNascimento(LocalDate.of(1971, 5, 23));

        autorRepository.save(autor);
        livro.setAutor(autor);
        repository.save(livro);

        System.out.println("O livro " + livro.getTitulo() + " foi salvo no banco de teste!");

    }

    @Test
    void salvarCascadeTest() {
        Livro livro = new Livro();

        livro.setIsbn("9888845-1234");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 05, 25));


        Autor autor = new Autor();
        autor.setNome("João");
        autor.setNacionalidade("Canadense");
        autor.setDataNascimento(LocalDate.of(1971, 5, 23));

        livro.setAutor(autor);
        repository.save(livro);

        System.out.println("O livro " + livro.getTitulo() + " foi salvo no banco de teste!");

    }

    @Test
    void atualizarAutorDoLivroTest() {
        UUID id = UUID.fromString("a19bef3e-6eb7-45c3-b8bc-f4f883c6a072");
        var livroParaAtualizar = repository.findById(id);

        UUID idAutor = UUID.fromString("11f84f18-6b80-404b-b00d-321f7ad31340");
        var marcia = autorRepository.findById(idAutor);
        if (livroParaAtualizar.isPresent() && marcia.isPresent()) {
            Livro livro = livroParaAtualizar.get();
            Autor autor = marcia.get();
            livro.setAutor(autor);
            repository.save(livro);
        }
    }

    @Test
    void deletarTest(){
        UUID id = UUID.fromString("c919dc02-741e-4048-9779-77dce882a1b2");
        repository.deleteById(id);


    }

    @Test
    void deletarPorObjetoTest(){
        UUID id = UUID.fromString("19f6eb0c-5d33-4421-bee7-6b75ec55187d");
        var livro = repository.findById(id);
        repository.delete(livro.get());
    }

    @Test
    @Transactional
    void buscarLivroTest(){
        UUID id = UUID.fromString("28bf83fe-0372-40db-877e-2cdf1f95f48c");

        var livro = repository.findById(id).get();

        System.out.println("Livro: ");
        System.out.println(livro.getTitulo());
        System.out.println("Autor: ");
        System.out.println(livro.getAutor().getNome());

    }

    @Test
    void listarLivrosComQueryJPQL(){
        var resultado = repository.listarTodosOrdenadoPorTituloAndPreco();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarAutoresDosLivros(){
        var resultado = repository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarGenerosDeAutoresBrasileiros(){
        var resultado = repository.listarGenerosAutoresBrasileiros();
        resultado.forEach(System.out::println);
    }



}
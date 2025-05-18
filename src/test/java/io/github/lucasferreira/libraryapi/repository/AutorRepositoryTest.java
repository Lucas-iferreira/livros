package io.github.lucasferreira.libraryapi.repository;

import io.github.lucasferreira.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    //    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));
        var autorSalvo = repository.save(autor);
        System.out.println("Autor " + autorSalvo);
    }

    @Test
    public void deletarTest() {
        var id = UUID.fromString("00708a9e-8f83-4bd5-a8bf-3fff9873d358");
        repository.deleteById(id);

        System.out.println("Usuario deletado");
    }

    @Test
    public void deletarObjetoTest() {
        var id = UUID.fromString("47e112a2-5842-4b6d-8e10-6e0053ac7c67");
        var autor = repository.findById(id).get();

        repository.delete(autor);

        System.out.println("Usuario deletado");
    }

    @Test
    public void atualizarTest() {
        var id = UUID.fromString("00708a9e-8f83-4bd5-a8bf-3fff9873d358");
        Optional<Autor> possivelAutor = repository.findById(id);
        if (possivelAutor.isPresent()) {
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do autor: ");
            System.out.println(possivelAutor.get());

            autorEncontrado.setDataNascimento(LocalDate.of(1960, 1, 30));

            repository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest(){
        List<Autor> autorList = repository.findAll();

        autorList.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contagem: " + repository.count());
    }

}

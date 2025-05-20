package io.github.lucasferreira.libraryapi.service;

import io.github.lucasferreira.libraryapi.model.Autor;
import io.github.lucasferreira.libraryapi.model.GeneroLivro;
import io.github.lucasferreira.libraryapi.model.Livro;
import io.github.lucasferreira.libraryapi.repository.AutorRepository;
import io.github.lucasferreira.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {
    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;


    @Transactional
    public void atualizarSemAtualizar(){
        var livro = livroRepository.findById(UUID.fromString("28bf83fe-0372-40db-877e-2cdf1f95f48c")).get();


        livro.setDataPublicacao(LocalDate.of(2024,6,1));
    }

    @Transactional
    public void executar() {
        //salva o autor
        Autor autor = new Autor();
        autor.setNome("Teste Francisco");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1971, 5, 23));

        autorRepository.save(autor);


        //salva o livro
        Livro livro = new Livro();
        livro.setIsbn("9888845-1234");
        livro.setPreco(BigDecimal.valueOf(300));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Teste Livro da Francisca");
        livro.setDataPublicacao(LocalDate.of(1992, 4, 29));

        livro.setAutor(autor);

        livroRepository.save(livro);

        if(autor.getNome().equals("Teste Francisco")){
            throw new RuntimeException("Rollback");
        };

    }
}

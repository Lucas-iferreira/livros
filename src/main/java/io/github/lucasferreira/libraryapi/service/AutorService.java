package io.github.lucasferreira.libraryapi.service;

import io.github.lucasferreira.libraryapi.exceptions.OperacaoNaoPermitidaException;
import io.github.lucasferreira.libraryapi.model.Autor;
import io.github.lucasferreira.libraryapi.repository.AutorRepository;
import io.github.lucasferreira.libraryapi.repository.LivroRepository;
import io.github.lucasferreira.libraryapi.validator.AutorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AutorService {
    private final AutorRepository autorRepository;
    private final LivroRepository livroRepository;
    private final AutorValidator validator;

//    @Autowired
//    public AutorService(AutorRepository autorRepository, LivroRepository livroRepository, AutorValidator validator) {
//        this.autorRepository = autorRepository;
//        this.livroRepository = livroRepository;
//        this.validator = validator;
//    }



    public Autor salvar(Autor autor) {
        validator.validar(autor);
        return autorRepository.save(autor);
    }

    public Autor atualizar(Autor autor) {
        if (autor.getId() == null) {
            throw new IllegalArgumentException("Para atualizar é necessário que o Autor já esteja salvo na base!");
        }
        validator.validar(autor);
        return autorRepository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id) {
        return autorRepository.findById(id);
    }

    public void deletar(Autor autor) {
        if(possuiLivro(autor)){
            throw new OperacaoNaoPermitidaException("Não é permitido excluir um Autor que possui livros cadastrados!");
        }
        autorRepository.delete(autor);
    }

    public List<Autor> pesquisaByExample(String nome, String nacionalidade) {
        var autor = new Autor();
        autor.setNome(nome);
        autor.setNacionalidade(nacionalidade);
        ExampleMatcher matcher = ExampleMatcher.
                matching().
                withIgnorePaths("id","dataNascimento","dataCadastro").
                withIgnoreNullValues().
                withIgnoreCase().
                withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Autor> autorExample = Example.of(autor,matcher);

        return autorRepository.findAll(autorExample);
    }

    public boolean possuiLivro(Autor autor){
        return livroRepository.existsByAutor(autor);
    }
}

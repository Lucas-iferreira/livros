package io.github.lucasferreira.libraryapi.validator;

import io.github.lucasferreira.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.lucasferreira.libraryapi.model.Autor;
import io.github.lucasferreira.libraryapi.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AutorValidator {
    private AutorRepository autorRepository;

    @Autowired
    public AutorValidator(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public void validar(Autor autor) {
        if (existeAutorCadatrado(autor)) {
            throw new RegistroDuplicadoException("Já existe autor cadastrado!");
        }
    }

    private boolean existeAutorCadatrado(Autor autor) {
        Optional<Autor> autorEncontrado = autorRepository.findByNomeAndDataNascimentoAndNacionalidade(
                autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade().trim()
        );
        if (autor.getId() == null) {
            return autorEncontrado.isPresent();
        }

        return !autor.getId().equals(autorEncontrado.get().getId()) && autorEncontrado.isPresent();
    }
}

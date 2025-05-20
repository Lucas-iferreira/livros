package io.github.lucasferreira.libraryapi.repository;

import io.github.lucasferreira.libraryapi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransacoesTest {

    @Autowired
    TransacaoService transacaoService;

    /**
     * Commit -> confirmar as alterações
     * Rollvack -> desfazer as alterações
     */
    @Test
    void transacaoSimples(){
        transacaoService.executar();

    }
    @Test
    void transacaoEstadoManaged(){
        transacaoService.atualizarSemAtualizar();
    }


}

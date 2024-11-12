package br.com.tarefas.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.tarefas.minhas_tarefas.MinhasTarefasApplication;
import br.com.tarefas.minhas_tarefas.model.Tarefa;
import br.com.tarefas.minhas_tarefas.repository.TarefaRepository;

//Como a pasta de teste esta em um mesmo nível que a pasta com a classe main é necessário informar qual é a classe principal do projeto
@SpringBootTest(classes = MinhasTarefasApplication.class)
public class TarefaRepositoryTest {

    @Autowired
    private TarefaRepository repositorio;

    @Test
    void testFindByNomeCategoria() {
       List<Tarefa> tarefas = repositorio.findByNomeCategoria("Estudos");
       Assertions.assertEquals(2, tarefas.size());

    }

    @Test
    void testTarefasPorCategoria() {
       List<Tarefa> tarefas = repositorio.tarefasPorCategoria("Estudos");
       Assertions.assertEquals(2, tarefas.size());

    }

}

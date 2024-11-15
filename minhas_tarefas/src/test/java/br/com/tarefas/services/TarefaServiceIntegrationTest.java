package br.com.tarefas.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.tarefas.minhas_tarefas.MinhasTarefasApplication;
import br.com.tarefas.minhas_tarefas.exception.TarefaStatusException;
import br.com.tarefas.minhas_tarefas.model.Tarefa;
import br.com.tarefas.minhas_tarefas.model.TarefaStatus;
import br.com.tarefas.minhas_tarefas.services.TarefaService;

@SpringBootTest(classes=MinhasTarefasApplication.class)
public class TarefaServiceIntegrationTest {

    @Autowired
    private TarefaService service;

    @Test
    void deveIniciarTarefaPorId(){
        Tarefa tarefa = service.iniciarTarefaPorId(2);
        Assertions.assertEquals(TarefaStatus.EM_ANDAMENTO, tarefa.getStatus());
    }

    @Test
    void naoDeveIniciarTarefaPorId() {
        Tarefa tarefa = service.getTarefaPorId(2);
        tarefa.setStatus(TarefaStatus.CONCLUIDA);
        service.salvaTarefa(tarefa);

        Assertions.assertThrows(TarefaStatusException.class, () -> service.iniciarTarefaPorId(2));
    }

}

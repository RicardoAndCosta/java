package br.com.tarefas.services;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.tarefas.minhas_tarefas.exception.TarefaStatusException;
import br.com.tarefas.minhas_tarefas.model.Tarefa;
import br.com.tarefas.minhas_tarefas.model.TarefaStatus;
import br.com.tarefas.minhas_tarefas.repository.TarefaRepository;
import br.com.tarefas.minhas_tarefas.services.TarefaService;

@ExtendWith(MockitoExtension.class)
public class TarefaServiceTest {

    @Mock
    private TarefaRepository repositorio;

    @InjectMocks
    private TarefaService service;

    @Test
    void naoDeveConcluirTarefaCancelada() {

        Integer idExemplo = 1;

        Tarefa tarefa = new Tarefa();
        tarefa.setId(idExemplo);
        tarefa.setDescricao("Teste 01");
        tarefa.setStatus(TarefaStatus.CANCELADA);

        Mockito.when(repositorio.findById(1)).thenReturn(Optional.of(tarefa));

        Assertions.assertThrows(TarefaStatusException.class, () -> service.concluirTarefaPorId(idExemplo));
    }
}

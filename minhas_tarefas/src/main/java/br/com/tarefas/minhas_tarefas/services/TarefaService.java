package br.com.tarefas.minhas_tarefas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tarefas.minhas_tarefas.exception.TarefaStatusException;
import br.com.tarefas.minhas_tarefas.model.Tarefa;
import br.com.tarefas.minhas_tarefas.model.TarefaStatus;
import br.com.tarefas.minhas_tarefas.repository.TarefaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TarefaService {
    
    @Autowired
    private TarefaRepository repositorio;
    
    public List<Tarefa> getTodasTarefas() {
        return repositorio.findAll();       
    }
        
    public List<Tarefa> getTarefaPorDescricao(String descricao) {
        return repositorio.findByDescricaoLike("%" + descricao + "%");
    }

    public Tarefa getTarefaPorId(Integer id){
        return repositorio.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public Tarefa salvaTarefa(Tarefa tarefa){
        return repositorio.save(tarefa);
    }

    public void deleteById(Integer id){
        repositorio.deleteById(id);
    }

    public Tarefa iniciarTarefaPorId(Integer id){
        Tarefa tarefa = getTarefaPorId(id);

        if (!TarefaStatus.ABERTO.equals(tarefa.getStatus()))
            throw new TarefaStatusException();

        tarefa.setStatus(TarefaStatus.EM_ANDAMENTO);

        repositorio.save(tarefa);
        return tarefa;
    }

    public Tarefa concluirTarefaPorId(Integer id){
        Tarefa tarefa = getTarefaPorId(id);

        if (!TarefaStatus.EM_ANDAMENTO.equals(tarefa.getStatus()))
            throw new TarefaStatusException();

        tarefa.setStatus(TarefaStatus.CONCLUIDA);

        repositorio.save(tarefa);
        return tarefa;
    }

    public Tarefa cancelarTarefaPorId(Integer id){
        Tarefa tarefa = getTarefaPorId(id);

        if (!TarefaStatus.CONCLUIDA.equals(tarefa.getStatus()))
            throw new TarefaStatusException();

        tarefa.setStatus(TarefaStatus.CANCELADA);

        repositorio.save(tarefa);
        return tarefa;
    }

}
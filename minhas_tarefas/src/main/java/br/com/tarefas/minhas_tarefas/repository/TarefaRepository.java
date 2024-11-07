package br.com.tarefas.minhas_tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tarefas.minhas_tarefas.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

}

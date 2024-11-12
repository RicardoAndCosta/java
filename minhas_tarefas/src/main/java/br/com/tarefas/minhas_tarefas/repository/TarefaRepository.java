package br.com.tarefas.minhas_tarefas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tarefas.minhas_tarefas.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

    // Desta forma só será retornado caso seja informado nos parametros o nome exato da tarefa
    public List<Tarefa> findByDescricao(String descricao);

    // Desta forma serão retornados as tarefas que tenham parte do nome da tarefa que foram informados nos parametros
    public List<Tarefa> findByDescricaoLike(String descricao);

}

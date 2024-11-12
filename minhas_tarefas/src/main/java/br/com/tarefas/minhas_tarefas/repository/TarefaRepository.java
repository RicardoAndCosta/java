package br.com.tarefas.minhas_tarefas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.tarefas.minhas_tarefas.model.Tarefa;
import br.com.tarefas.minhas_tarefas.model.TarefaCategoria;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

    // Desta forma só será retornado caso seja informado nos parametros o nome exato da tarefa
    public List<Tarefa> findByDescricao(String descricao);

    // Desta forma serão retornados as tarefas que tenham parte do nome da tarefa que foram informados nos parametros
    public List<Tarefa> findByDescricaoLike(String descricao);

    public List<Tarefa> findByCategoria(TarefaCategoria categoria);

    // Podemos utilizar as anotações para que seja realizado as consulta de forma mai elaborada onde o ?1 será substituido pelo categoria
    @Query("select t from Tarefa t inner join t.categoria c where c.nome = ?1 ")
    public List<Tarefa> findByNomeCategoria(String nomeCategoria);

    public List<Tarefa> tarefasPorCategoria(String nomeCategoria);

}

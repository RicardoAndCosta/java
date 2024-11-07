package br.com.tarefas.minhas_tarefas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import br.com.tarefas.minhas_tarefas.model.Tarefa;
import br.com.tarefas.minhas_tarefas.repository.TarefaRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class TarefaController {

    @Autowired
    private TarefaRepository repositorio;

    @GetMapping("/tarefa")
    public List<Tarefa> todasTarefas() {
        return repositorio.findAll();
    }

    @GetMapping("/tarefa/{id}")
    public Tarefa umaTarefa(@PathVariable Integer id) {
        return repositorio.findById(id).orElse(null);
    }

    @PostMapping("/tarefa")
    public Tarefa salvarTarefa(@RequestBody Tarefa tarefa) {
        return repositorio.save(tarefa);
    }

    @DeleteMapping("/tarefa/{id}")
    public void excluirTarefa(@PathVariable Integer id) {
        repositorio.deleteById(id);
    }

}

package br.com.tarefas.minhas_tarefas.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.tarefas.minhas_tarefas.controller.request.tarefaCategoriaRequest;
import br.com.tarefas.minhas_tarefas.controller.response.TarefaCategoriaResponse;
import br.com.tarefas.minhas_tarefas.model.TarefaCategoria;
import br.com.tarefas.minhas_tarefas.services.TarefaCategoriaService;



@RestController
public class TarefaCategoriaController {

    @Autowired
    private TarefaCategoriaService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/categoria")
    public List<TarefaCategoriaResponse> todasCategorias() {
        List<TarefaCategoria> categorias = service.getTodasCategorias();
        return categorias
            .stream().map(categoria -> mapper.map(categoria, TarefaCategoriaResponse.class)).collect(Collectors.toList());        
    }

    @GetMapping("/categoria/{id}")
    public TarefaCategoriaResponse umCategoria(@PathVariable Integer id){
        return mapper.map(
            service.getCategoriaPorId(id),
            TarefaCategoriaResponse.class);
    }

    @PostMapping("/categoria")
    public TarefaCategoriaResponse salvaCategoriaResponse(@RequestBody tarefaCategoriaRequest categoriaReq){
        TarefaCategoria categoria = mapper.map(categoriaReq, TarefaCategoria.class);
        return mapper.map(service.salvar(categoria), TarefaCategoriaResponse.class);
    }

    @DeleteMapping("/categoria/{id}")
    public void excluirTarefa(@PathVariable Integer id){
        service.deleteById(id);
    }
}

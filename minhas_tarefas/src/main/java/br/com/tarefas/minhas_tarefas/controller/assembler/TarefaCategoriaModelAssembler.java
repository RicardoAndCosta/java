package br.com.tarefas.minhas_tarefas.controller.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.com.tarefas.minhas_tarefas.controller.TarefaCategoriaController;
import br.com.tarefas.minhas_tarefas.controller.response.TarefaCategoriaResponse;
import br.com.tarefas.minhas_tarefas.model.TarefaCategoria;
import br.com.tarefas.minhas_tarefas.model.TarefaStatus;

@Component
public class TarefaCategoriaModelAssembler implements RepresentationModelAssembler<TarefaCategoria, EntityModel<TarefaCategoriaResponse>>{

    @Autowired
    private ModelMapper mapper;


    @SuppressWarnings("null")
    @Override
    public EntityModel<TarefaCategoriaResponse> toModel(TarefaCategoria categoria) {
        
        TarefaCategoriaResponse categoriaResp = mapper.map(categoria, TarefaCategoriaResponse.class);

        EntityModel<TarefaCategoriaResponse> categoriaModel = EntityModel.of(categoriaResp, 
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaCategoriaController.class).umCategoria(categoriaResp.getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaCategoriaController.class).todasCategorias()).withRel("categorias"));
            
        return categoriaModel;
    }

}

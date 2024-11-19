package br.com.tarefas.minhas_tarefas.controller.assembler;

import java.util.HashMap;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.com.tarefas.minhas_tarefas.controller.TarefaCategoriaController;
import br.com.tarefas.minhas_tarefas.controller.TarefaController;
import br.com.tarefas.minhas_tarefas.controller.UsuarioController;
import br.com.tarefas.minhas_tarefas.controller.response.TarefaResponse;
import br.com.tarefas.minhas_tarefas.model.Tarefa;
import br.com.tarefas.minhas_tarefas.model.TarefaStatus;

@Component
public class TarefaModelAssembler implements RepresentationModelAssembler<Tarefa, EntityModel<TarefaResponse>> {
    
    @Autowired
    private ModelMapper mapper;
    
    @SuppressWarnings("null")
    @Override
    public EntityModel<TarefaResponse> toModel(Tarefa tarefa) {
        TarefaResponse tarefaResp = mapper.map(tarefa, TarefaResponse.class);
    
        EntityModel<TarefaResponse> tarefaModel = EntityModel.of(tarefaResp, 
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class).umaTarefa(tarefaResp.getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class).todasTarefas(new HashMap<>())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaCategoriaController.class).umCategoria(tarefaResp.getCategoriaId())).withRel("categoria"),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).umUsuario(tarefaResp.getUsuarioId())).withRel("usuario")
            );

            if (TarefaStatus.EM_ANDAMENTO.equals(tarefa.getStatus())) {
                tarefaModel.add(
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class).concluirTarefa(tarefa.getId())).withRel("concluir"),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class).cancelarTarefa(tarefa.getId())).withRel("cancelar")
                );
            }

            if (TarefaStatus.ABERTO.equals(tarefa.getStatus())) {
                tarefaModel.add(
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class).iniciarTarefa(tarefa.getId())).withRel("iniciar")
                );
            }
            
        return tarefaModel;
    }
}

package br.com.tarefas.minhas_tarefas.controller.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.tarefas.minhas_tarefas.controller.UsuarioController;
import br.com.tarefas.minhas_tarefas.controller.response.UsuarioResponse;
import br.com.tarefas.minhas_tarefas.model.Usuario;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<UsuarioResponse>>{

    @Autowired
	private ModelMapper mapper;

	@SuppressWarnings("null")
    @Override
	public EntityModel<UsuarioResponse> toModel(Usuario usuario) {
		UsuarioResponse usuarioResp = mapper.map(usuario, UsuarioResponse.class);
		
		EntityModel<UsuarioResponse> usuarioModel = EntityModel.of(usuarioResp,
				linkTo(
						methodOn(UsuarioController.class).umUsuario(usuarioResp.getId()))
				.withSelfRel()
				);
		
		return usuarioModel;
	}

}

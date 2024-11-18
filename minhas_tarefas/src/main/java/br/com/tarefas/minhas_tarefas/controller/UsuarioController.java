package br.com.tarefas.minhas_tarefas.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tarefas.minhas_tarefas.controller.response.UsuarioResponse;
import br.com.tarefas.minhas_tarefas.model.Usuario;
import br.com.tarefas.minhas_tarefas.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/{id}")
    public UsuarioResponse umUsuario(@PathVariable Integer id){
        Usuario usuario = usuarioService.getUsuarioPorId(id);
        UsuarioResponse usuarioResponse = mapper.map(usuario, UsuarioResponse.class);
        return usuarioResponse;
    }
}

package br.com.tarefas.minhas_tarefas.services;

import org.springframework.stereotype.Service;

import br.com.tarefas.minhas_tarefas.model.Usuario;
import br.com.tarefas.minhas_tarefas.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepositorio;

    public Usuario getUsuarioPorId(Integer id) {
        return usuarioRepositorio.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }
}

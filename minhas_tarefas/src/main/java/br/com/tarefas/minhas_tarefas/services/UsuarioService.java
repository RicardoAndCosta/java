package br.com.tarefas.minhas_tarefas.services;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.tarefas.minhas_tarefas.model.Role;
import br.com.tarefas.minhas_tarefas.model.Usuario;
import br.com.tarefas.minhas_tarefas.repository.RoleRepository;
import br.com.tarefas.minhas_tarefas.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepositorio;

    @Autowired
    private RoleRepository roleRepositorio;

    @Autowired
    private PasswordEncoder encoder;

    public Usuario getUsuarioPorId(Integer id) {
        return usuarioRepositorio.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public Usuario salvar(Usuario usuario) {
        Set<Role> roles = getRoles(usuario);
        usuario.setRoles(roles);
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return usuarioRepositorio.save(usuario);
    }

    public Usuario atualizar(Integer id, Usuario usuario) {
        if (usuarioRepositorio.existsById(id))
            throw new EntityNotFoundException();

        usuario.setId(id);

        return salvar(usuario);
    }

    public void deleteById(Integer id) {
        usuarioRepositorio.deleteById(id);
    }

    private Set<Role> getRoles(Usuario usuario) {
        Set<Role> rolesBanco = usuario
            .getRoles()
            .stream()
            .map(role -> roleRepositorio.findByName(role.getNome()))
            .collect(Collectors.toSet());
        return rolesBanco;
    }
}

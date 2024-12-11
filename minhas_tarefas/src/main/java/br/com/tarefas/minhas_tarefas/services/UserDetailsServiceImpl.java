package br.com.tarefas.minhas_tarefas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.tarefas.minhas_tarefas.model.Usuario;
import br.com.tarefas.minhas_tarefas.repository.UsuarioRepository;
import br.com.tarefas.minhas_tarefas.security.UserDetailsImpl;
import jakarta.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNome(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return UserDetailsImpl.build(usuario);
    }

}
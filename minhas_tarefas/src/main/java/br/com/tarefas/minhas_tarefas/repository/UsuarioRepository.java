package br.com.tarefas.minhas_tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tarefas.minhas_tarefas.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}

package br.com.tarefas.minhas_tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tarefas.minhas_tarefas.model.ERole;
import br.com.tarefas.minhas_tarefas.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByNome(ERole nome);

    
} 

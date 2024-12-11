package br.com.tarefas.minhas_tarefas.config;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.tarefas.minhas_tarefas.model.ERole;
import br.com.tarefas.minhas_tarefas.model.Role;
import br.com.tarefas.minhas_tarefas.model.Tarefa;
import br.com.tarefas.minhas_tarefas.model.TarefaCategoria;
import br.com.tarefas.minhas_tarefas.model.TarefaStatus;
import br.com.tarefas.minhas_tarefas.model.Usuario;
import br.com.tarefas.minhas_tarefas.repository.RoleRepository;
import br.com.tarefas.minhas_tarefas.repository.TarefaCategoriaRepository;
import br.com.tarefas.minhas_tarefas.repository.TarefaRepository;
import br.com.tarefas.minhas_tarefas.repository.UsuarioRepository;

@Configuration
@Profile("dev") // Configuração para que apenas em desevolvimento há uma carga de dados no banco
public class CarregarBaseDeDados {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TarefaCategoriaRepository tarefaCategoriaRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Bean
    CommandLineRunner executar() {
        return args -> {

            Role roleAdmin = new Role(ERole.ROLE_ADMIN);
            roleAdmin = roleRepository.save(roleAdmin);
            

            Usuario usuario = new Usuario();
            usuario.setnome("Admin");
            usuario.setSenha(encoder.encode("123456"));
            usuario.setRoles(Set.of(roleAdmin));

            usuarioRepository.save(usuario);

            TarefaCategoria categoria = new TarefaCategoria();
            categoria.setnome("Estudos");
            
            tarefaCategoriaRepository.save(categoria);

            Tarefa tarefa= new Tarefa();
            tarefa.setDescricao("Aprender Spring Boot");
            tarefa.setdataEntrega(LocalDate.now().plusDays(1));
            tarefa.setStatus(TarefaStatus.ABERTO);
            tarefa.setVisivel(true);
            tarefa.setCategoria(categoria);
            tarefa.setUsuario(usuario);
            
            tarefaRepository.save(tarefa);

            Tarefa tarefa2= new Tarefa();
            tarefa2.setDescricao("Aprender Spring Data JPA");
            tarefa2.setdataEntrega(LocalDate.now().plusDays(1));
            tarefa2.setStatus(TarefaStatus.ABERTO);
            tarefa2.setVisivel(true);
            tarefa2.setCategoria(categoria);
            tarefa2.setUsuario(usuario);
            
            tarefaRepository.save(tarefa2);

        };
    }

}

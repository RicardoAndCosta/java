package br.com.tarefas.minhas_tarefas.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.tarefas.minhas_tarefas.model.Tarefa;
import br.com.tarefas.minhas_tarefas.model.TarefaCategoria;
import br.com.tarefas.minhas_tarefas.model.TarefaStatus;
import br.com.tarefas.minhas_tarefas.model.Usuario;
import br.com.tarefas.minhas_tarefas.repository.TarefaCategoriaRepository;
import br.com.tarefas.minhas_tarefas.repository.TarefaRepository;
import br.com.tarefas.minhas_tarefas.repository.UsuarioRepository;

@Configuration
@Profile("dev")
public class CarregarBaseDeDados {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TarefaCategoriaRepository tarefaCategoriaRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Bean
    CommandLineRunner executar() {
        return args -> {
            Usuario usuario = new Usuario();
            usuario.setnome("Admin");
            usuario.setSenha("123456");

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
        };
    }

}

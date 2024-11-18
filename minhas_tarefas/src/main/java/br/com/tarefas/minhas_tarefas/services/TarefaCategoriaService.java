package br.com.tarefas.minhas_tarefas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tarefas.minhas_tarefas.model.TarefaCategoria;
import br.com.tarefas.minhas_tarefas.repository.TarefaCategoriaRepository;
import jakarta.persistence.EntityNotFoundException;



@Service
public class TarefaCategoriaService {

	@Autowired
	private TarefaCategoriaRepository repositorio;
	
	public List<TarefaCategoria> getTodasCategorias() {
		return repositorio.findAll();
	}

	public TarefaCategoria getCategoriaPorId(Integer id) {
		return repositorio.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}

	public TarefaCategoria salvar(TarefaCategoria categoria) {
		return repositorio.save(categoria);
	}

	public void deleteById(Integer id) {
		repositorio.deleteById(id);
	}

}

    
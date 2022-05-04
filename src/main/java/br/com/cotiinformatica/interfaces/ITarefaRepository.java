package br.com.cotiinformatica.interfaces;

import java.util.Date;
import java.util.List;

import br.com.cotiinformatica.entities.Tarefa;

public interface ITarefaRepository {

	// método para incluir uma tarefa no banco de dados
	void create(Tarefa tarefa) throws Exception;

	// método para atualizar uma tarefa no banco de dados
	void update(Tarefa tarefa) throws Exception;

	// método para excluir uma tarefa no banco de dados
	void delete(Tarefa tarefa) throws Exception;

	// método para realizar a consulta de tarefas por data e por usuário
	List<Tarefa> findAll(Date dataMin, Date dataMax, Integer idUsuario) throws Exception;

	// método para buscar 1 tarefa através do idTarefa e idUsuario
	Tarefa findById(Integer idTarefa, Integer idUsuario) throws Exception;
}

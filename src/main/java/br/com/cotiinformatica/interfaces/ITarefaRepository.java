package br.com.cotiinformatica.interfaces;

import java.util.Date;
import java.util.List;

import br.com.cotiinformatica.entities.Tarefa;

public interface ITarefaRepository {

	// m�todo para incluir uma tarefa no banco de dados
	void create(Tarefa tarefa) throws Exception;

	// m�todo para atualizar uma tarefa no banco de dados
	void update(Tarefa tarefa) throws Exception;

	// m�todo para excluir uma tarefa no banco de dados
	void delete(Tarefa tarefa) throws Exception;

	// m�todo para realizar a consulta de tarefas por data e por usu�rio
	List<Tarefa> findAll(Date dataMin, Date dataMax, Integer idUsuario) throws Exception;

	// m�todo para buscar 1 tarefa atrav�s do idTarefa e idUsuario
	Tarefa findById(Integer idTarefa, Integer idUsuario) throws Exception;
}

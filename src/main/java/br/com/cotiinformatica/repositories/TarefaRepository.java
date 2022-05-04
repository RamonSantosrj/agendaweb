package br.com.cotiinformatica.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.cotiinformatica.entities.Tarefa;
import br.com.cotiinformatica.factories.ConnectionFactory;
import br.com.cotiinformatica.helpers.DateHelper;
import br.com.cotiinformatica.interfaces.ITarefaRepository;

public class TarefaRepository implements ITarefaRepository {

	@Override
	public void create(Tarefa tarefa) throws Exception {

		// abrindo uma conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();

		// Escrevendo uma query SQL para execução no banco de dados
		PreparedStatement statement = connection.prepareStatement(
				"insert into tarefa(nome, data, hora, descricao, prioridade, idusuario) values(?, ?, ?, ?, ?, ?)");

		statement.setString(1, tarefa.getNome());
		statement.setString(2, DateHelper.formatToString(tarefa.getData()));
		statement.setString(3, tarefa.getHora());
		statement.setString(4, tarefa.getDescricao());
		statement.setInt(5, tarefa.getPrioridade());
		statement.setInt(6, tarefa.getUsuario().getIdUsuario());
		statement.execute();

		connection.close();
	}

	@Override
	public void update(Tarefa tarefa) throws Exception {

		// abrindo uma conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();

		// Escrevendo uma query SQL para execução no banco de dados
		PreparedStatement statement = connection.prepareStatement(
				"update tarefa set nome=?, data=?, hora=?, descricao=?, prioridade=? where idtarefa=? and idusuario=?");

		statement.setString(1, tarefa.getNome());
		statement.setString(2, DateHelper.formatToString(tarefa.getData()));
		statement.setString(3, tarefa.getHora());
		statement.setString(4, tarefa.getDescricao());
		statement.setInt(5, tarefa.getPrioridade());
		statement.setInt(6, tarefa.getIdTarefa());
		statement.setInt(7, tarefa.getUsuario().getIdUsuario());
		statement.execute();

		connection.close();

	}

	@Override
	public void delete(Tarefa tarefa) throws Exception {

		// abrindo uma conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();

		// Escrevendo uma query SQL para execução no banco de dados
		PreparedStatement statement = connection
				.prepareStatement("delete from tarefa where idtarefa=? and idusuario=?");

		statement.setInt(1, tarefa.getIdTarefa());
		statement.setInt(2, tarefa.getUsuario().getIdUsuario());
		statement.execute();

		connection.close();

	}

	@Override
	public List<Tarefa> findAll(Date dataMin, Date dataMax, Integer idUsuario) throws Exception {

		// abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();

		// Escrevendo uma query SQL para consultar as tarefas
		PreparedStatement statement = connection.prepareStatement(
				"select * from tarefa where data between ? and ? and idusuario = ? order by data desc, hora desc");
		
		statement.setString(1, DateHelper.formatToString(dataMin));
		statement.setString(2, DateHelper.formatToString(dataMax));
		statement.setInt(3, idUsuario);

		// executando e lendo o resultado da consulta
		ResultSet resultSet = statement.executeQuery();

		// criando uma lista de tarefas vazia
		List<Tarefa> lista = new ArrayList<Tarefa>();

		// lendo cada resultado obtido do SQL
		while (resultSet.next()) {

			Tarefa tarefa = new Tarefa();

			tarefa.setIdTarefa(resultSet.getInt("idtarefa"));
			tarefa.setNome(resultSet.getString("nome"));
			tarefa.setData(DateHelper.formatToDate(resultSet.getString("data")));
			tarefa.setHora(resultSet.getString("hora"));
			tarefa.setPrioridade(resultSet.getInt("prioridade"));
			tarefa.setDescricao(resultSet.getString("descricao"));

			lista.add(tarefa); // adicionando na lista
		}

		connection.close(); // fechando a conexão
		return lista; // retornando a lista
	}

	@Override
	public Tarefa findById(Integer idTarefa, Integer idUsuario) throws Exception {

		// abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();

		// Escrevendo uma query SQL para consultar as tarefas
		PreparedStatement statement = connection.prepareStatement(
				"select * from tarefa where idtarefa=? and idusuario=?");
		
		statement.setInt(1, idTarefa);
		statement.setInt(2, idUsuario);

		// executando e lendo o resultado da consulta
		ResultSet resultSet = statement.executeQuery();

		Tarefa tarefa = null;

		// lendo cada resultado obtido do SQL
		if (resultSet.next()) {

			tarefa = new Tarefa();

			tarefa.setIdTarefa(resultSet.getInt("idtarefa"));
			tarefa.setNome(resultSet.getString("nome"));
			tarefa.setData(DateHelper.formatToDate(resultSet.getString("data")));
			tarefa.setHora(resultSet.getString("hora"));
			tarefa.setPrioridade(resultSet.getInt("prioridade"));
			tarefa.setDescricao(resultSet.getString("descricao"));
		}

		connection.close(); // fechando a conexão
		return tarefa; 
	}

}

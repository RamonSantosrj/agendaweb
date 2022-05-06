package br.com.cotiinformatica.interfaces;

import br.com.cotiinformatica.entities.Usuario;

public interface IUsuarioRepository {

	//M�todo para gravar um usu�rio na base de dados
	void create(Usuario usuario) throws Exception;
	
	//M�todo para atualizar a senha do usu�rio
	void update(Integer idUsuario, String novaSenha) throws Exception;
	
	//M�todo para consultar no banco de dados 1 usu�rio
	Usuario find(String email) throws Exception;
	
	//M�todo para consultar no banco de dados 1 usu�rio
	Usuario find(String email, String senha) throws Exception;
}

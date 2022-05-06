package br.com.cotiinformatica.interfaces;

import br.com.cotiinformatica.entities.Usuario;

public interface IUsuarioRepository {

	//Método para gravar um usuário na base de dados
	void create(Usuario usuario) throws Exception;
	
	//Método para atualizar a senha do usuário
	void update(Integer idUsuario, String novaSenha) throws Exception;
	
	//Método para consultar no banco de dados 1 usuário
	Usuario find(String email) throws Exception;
	
	//Método para consultar no banco de dados 1 usuário
	Usuario find(String email, String senha) throws Exception;
}

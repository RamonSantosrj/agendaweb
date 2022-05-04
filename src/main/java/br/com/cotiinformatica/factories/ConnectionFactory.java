package br.com.cotiinformatica.factories;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	//atributos
	private static final String HOST = "jdbc:mysql://localhost:3306/bd_agendaweb?useTimezone=true&serverTimezone=UTC&useSSL=false";
	private static final String USER = "root";
	private static final String PASS = "coti";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	
	//método para abrir e retornar conexão com o MYSQL
	public static Connection getConnection() throws Exception {		
		//carregar o driver de conexão JDBC do MySQL
		Class.forName(DRIVER);		
		//abrir e retornar a conexão
		return DriverManager.getConnection(HOST, USER, PASS);
	}
	
}

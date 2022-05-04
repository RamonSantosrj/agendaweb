package br.com.cotiinformatica.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TarefaCadastroModel {

	private String nome;
	private String data;
	private String hora;
	private String descricao;
	private String prioridade;	
}

package br.com.cotiinformatica.entities;

import java.util.Date;

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
public class Tarefa {

	private Integer idTarefa;
	private String nome;
	private Date data;
	private String hora;
	private String descricao;
	private Integer prioridade;
	private Usuario usuario; // Associação (TER-1)
}

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
public class TarefaConsultaModel {

	private String dataMin; //campo de data de in�cio
	private String dataMax; //campo de data de t�rmino
	
}

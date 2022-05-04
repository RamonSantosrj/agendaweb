package br.com.cotiinformatica.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Tarefa;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.helpers.DateHelper;
import br.com.cotiinformatica.models.TarefaConsultaModel;
import br.com.cotiinformatica.repositories.TarefaRepository;

@Controller
public class TarefasConsultaController {

	@RequestMapping(value = "/tarefas-consulta")
	public ModelAndView consulta() {
		
		ModelAndView modelAndView = new ModelAndView("tarefas-consulta");
		
		modelAndView.addObject("model", new TarefaConsultaModel());
		return modelAndView;
	}
	
	@RequestMapping(value = "/consultar-tarefas", method = RequestMethod.GET)
	public ModelAndView consultarTarefas(TarefaConsultaModel model, HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView("tarefas-consulta");
		
		try {
			
			//capturar o usuário autenticado (sessão)
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_auth");
			
			//capturar as datas informadas no formulário
			Date dataMin = DateHelper.formatToDate(model.getDataMin());
			Date dataMax = DateHelper.formatToDate(model.getDataMax());
			
			//acessando o repositório e consultar as tarefas
			TarefaRepository tarefaRepository = new TarefaRepository();
			List<Tarefa> lista = tarefaRepository.findAll(dataMin, dataMax, usuario.getIdUsuario());
			
			//enviando a lista de tarefas para a página:
			modelAndView.addObject("tarefas", lista);
			
			//gerando uma mensagem
			if(lista.size() > 0) {
				modelAndView.addObject("mensagem", lista.size() + " tarefa(s) encontrada(s) para o período especificado.");
			}
			else {
				modelAndView.addObject("mensagem", "Nenhuma tarefa foi encontrada para o período especificado.");
			}
		}
		catch(Exception e) {
			//gerando uma mensagem de erro
			modelAndView.addObject("mensagem", "Ocorreu um erro: " + e.getMessage());
		}
		
		modelAndView.addObject("model", model);		
		return modelAndView;
	}
	
}

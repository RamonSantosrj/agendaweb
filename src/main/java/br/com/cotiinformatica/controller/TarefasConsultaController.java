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
			
			//capturar o usu�rio autenticado (sess�o)
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_auth");
			
			//capturar as datas informadas no formul�rio
			Date dataMin = DateHelper.formatToDate(model.getDataMin());
			Date dataMax = DateHelper.formatToDate(model.getDataMax());
			
			//acessando o reposit�rio e consultar as tarefas
			TarefaRepository tarefaRepository = new TarefaRepository();
			List<Tarefa> lista = tarefaRepository.findAll(dataMin, dataMax, usuario.getIdUsuario());
			
			//enviando a lista de tarefas para a p�gina:
			modelAndView.addObject("tarefas", lista);
			
			//gerando uma mensagem
			if(lista.size() > 0) {
				modelAndView.addObject("mensagem", lista.size() + " tarefa(s) encontrada(s) para o per�odo especificado.");
			}
			else {
				modelAndView.addObject("mensagem", "Nenhuma tarefa foi encontrada para o per�odo especificado.");
			}
		}
		catch(Exception e) {
			//gerando uma mensagem de erro
			modelAndView.addObject("mensagem", "Ocorreu um erro: " + e.getMessage());
		}
		
		modelAndView.addObject("model", model);		
		return modelAndView;
	}
		
	@RequestMapping(value = "/tarefas-exclusao")
	public ModelAndView excluirTarefa(Integer id, HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView("tarefas-consulta");
		
		try {
			
			//capturar o usu�rio autenticado (sess�o)
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_auth");
			
			Tarefa tarefa = new Tarefa();
			tarefa.setIdTarefa(id);
			tarefa.setUsuario(usuario);
			
			//excluindo a tarefa
			TarefaRepository tarefaRepository = new TarefaRepository();
			tarefaRepository.delete(tarefa);
			
			modelAndView.addObject("mensagem", "Tarefa exclu�da com sucesso.");
		}
		catch(Exception e) {
			//gerando uma mensagem de erro
			modelAndView.addObject("mensagem", "Ocorreu um erro: " + e.getMessage());
		}
		
		modelAndView.addObject("model", new TarefaConsultaModel());
		return modelAndView;
	}
	
}






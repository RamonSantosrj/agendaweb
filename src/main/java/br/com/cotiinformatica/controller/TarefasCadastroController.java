package br.com.cotiinformatica.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Tarefa;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.enums.PrioridadeTarefa;
import br.com.cotiinformatica.helpers.DateHelper;
import br.com.cotiinformatica.models.TarefaCadastroModel;
import br.com.cotiinformatica.repositories.TarefaRepository;

@Controller
public class TarefasCadastroController {

	//método patra abrir a página de cadastro de tarefa
	@RequestMapping(value = "/tarefas-cadastro")
	public ModelAndView cadastro() {
		
		ModelAndView modelAndView = new ModelAndView("tarefas-cadastro");
		
		modelAndView.addObject("model", new TarefaCadastroModel());		
		modelAndView.addObject("prioridades", PrioridadeTarefa.values());
		
		return modelAndView;
	}
	
	//método para receber o SUBMIT POST do formulário
	@RequestMapping(value = "/cadastrar-tarefa", method = RequestMethod.POST)
	public ModelAndView cadastrarTarefa(TarefaCadastroModel model, HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView("tarefas-cadastro");
		
		try {
			
			//ler o usuário gravado na sessão
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_auth");
			
			//capturar os campos do formulário (model)
			Tarefa tarefa = new Tarefa();
			
			tarefa.setNome(model.getNome());
			tarefa.setData(DateHelper.formatToDate(model.getData()));
			tarefa.setHora(model.getHora());
			
			tarefa.setPrioridade(model.getPrioridade().equals("ALTA") ? 1 
					: model.getPrioridade().equals("MEDIA") ? 2 
					: model.getPrioridade().equals("BAIXA") ? 3
					: 0);
			
			tarefa.setDescricao(model.getDescricao());
			tarefa.setUsuario(usuario);
			
			TarefaRepository tarefaRepository = new TarefaRepository();
			tarefaRepository.create(tarefa);
			
			modelAndView.addObject("mensagem", "Tarefa cadastrada com sucesso!");
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem", "Ocorreu um erro: " + e.getMessage());
		}
		
		modelAndView.addObject("model", new TarefaCadastroModel());	
		modelAndView.addObject("prioridades", PrioridadeTarefa.values());
		return modelAndView;
	}
	
}

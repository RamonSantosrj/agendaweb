package br.com.cotiinformatica.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.models.LoginModel;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Controller
public class LoginController {

	//M�todo para mapear a p�gina inicial do projeto	
	@RequestMapping(value = "/") //URL da p�gina (ROTA)
	public ModelAndView login() {
		
		//define a p�gina .jsp que ser� aberta atraves desta rota
		////WEB-INF/views/login.jsp
		ModelAndView modelAndView = new ModelAndView("login");
		
		modelAndView.addObject("model", new LoginModel());
		return modelAndView;
	}
	
	@RequestMapping(value = "/autenticar-usuario", method = RequestMethod.POST)
	public ModelAndView autenticarUsuario(LoginModel model, HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView("login");
		
		try {
			
			//consultar o usu�rio no banco de dados atrav�s do email e da senha
			UsuarioRepository usuarioRepository = new UsuarioRepository();
			Usuario usuario = usuarioRepository.find(model.getEmail(), model.getSenha());
			
			//verificar se o usu�rio foi encontrado
			if(usuario != null) {
				
				//armazenar os dados do usu�rio em sess�o
				request.getSession().setAttribute("usuario_auth", usuario);
				
				//redirecionamento
				modelAndView.setViewName("redirect:tarefas-consulta"); //WEB-INF/views/tarefas-consulta.jsp
			}
			else {
				modelAndView.addObject("mensagem", "Acesso negado, email ou senha inv�lidos.");
			}
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem", "Ocorreu um erro: " + e.getMessage());
		}
		
		//se o usu�rio estiver retornando para a p�gina de login
		//criamos uma nova inst�ncia da classe LoginModel
		if(modelAndView.getViewName().equals("login"))
			modelAndView.addObject("model", new LoginModel());
		
		return modelAndView;
	}
	
	//M�todo para mapear a rota /logout do sistema
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request) {
		
		//apagar especificamente uma vari�vel da sess�o
		request.getSession().removeAttribute("usuario_auth");
		
		//apagar todas as variaveis gravadas em sess�o
		request.getSession().invalidate();
		
		//redirecionar de volta para o raiz do projeto
		ModelAndView modelAndView = new ModelAndView("redirect:/");
		return modelAndView;
	}
	
	
}

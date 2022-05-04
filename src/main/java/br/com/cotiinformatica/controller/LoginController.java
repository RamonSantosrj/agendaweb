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

	//Método para mapear a página inicial do projeto	
	@RequestMapping(value = "/") //URL da página (ROTA)
	public ModelAndView login() {
		
		//define a página .jsp que será aberta atraves desta rota
		////WEB-INF/views/login.jsp
		ModelAndView modelAndView = new ModelAndView("login");
		
		modelAndView.addObject("model", new LoginModel());
		return modelAndView;
	}
	
	@RequestMapping(value = "/autenticar-usuario", method = RequestMethod.POST)
	public ModelAndView autenticarUsuario(LoginModel model, HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView("login");
		
		try {
			
			//consultar o usuário no banco de dados através do email e da senha
			UsuarioRepository usuarioRepository = new UsuarioRepository();
			Usuario usuario = usuarioRepository.find(model.getEmail(), model.getSenha());
			
			//verificar se o usuário foi encontrado
			if(usuario != null) {
				
				//armazenar os dados do usuário em sessão
				request.getSession().setAttribute("usuario_auth", usuario);
				
				//redirecionamento
				modelAndView.setViewName("redirect:tarefas-consulta"); //WEB-INF/views/tarefas-consulta.jsp
			}
			else {
				modelAndView.addObject("mensagem", "Acesso negado, email ou senha inválidos.");
			}
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem", "Ocorreu um erro: " + e.getMessage());
		}
		
		//se o usuário estiver retornando para a página de login
		//criamos uma nova instância da classe LoginModel
		if(modelAndView.getViewName().equals("login"))
			modelAndView.addObject("model", new LoginModel());
		
		return modelAndView;
	}
	
	//Método para mapear a rota /logout do sistema
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request) {
		
		//apagar especificamente uma variável da sessão
		request.getSession().removeAttribute("usuario_auth");
		
		//apagar todas as variaveis gravadas em sessão
		request.getSession().invalidate();
		
		//redirecionar de volta para o raiz do projeto
		ModelAndView modelAndView = new ModelAndView("redirect:/");
		return modelAndView;
	}
	
	
}

package br.com.cotiinformatica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.models.RegisterModel;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Controller
public class RegisterController {

	// Método para mapear a página de cadastro de usuário
	@RequestMapping(value = "/register") // URL da página (ROTA)
	public ModelAndView register() {

		// define a página .jsp que será aberta atraves desta rota
		//// WEB-INF/views/register.jsp
		ModelAndView modelAndView = new ModelAndView("register");
		
		//criando um objeto da classe RegisterModel
		modelAndView.addObject("model", new RegisterModel());		
		return modelAndView;
	}
	
	//Método para receber a requisição POST realizada no SUBMIT do formulário
	@RequestMapping(value = "/cadastrar-usuario", method = RequestMethod.POST)
	public ModelAndView cadastrarUsuario(RegisterModel model) {
		
		ModelAndView modelAndView = new ModelAndView("register");
		
		try {
			
			UsuarioRepository usuarioRepository = new UsuarioRepository();
			
			//verificar se já existe um usuário cadastrado com o email informado
			if(usuarioRepository.find(model.getEmail()) != null) {
				modelAndView.addObject("erro_email", "O email informado já está cadastrado, tente outro.");
				modelAndView.addObject("model", model);
			}
			else {
				
				Usuario usuario = new Usuario();
				
				usuario.setNome(model.getNome());
				usuario.setEmail(model.getEmail());
				usuario.setSenha(model.getSenha());			
				
				usuarioRepository.create(usuario);
				
				modelAndView.addObject("mensagem", "Parabéns " + usuario.getNome() + ", sua conta foi criada com sucesso!");
				modelAndView.addObject("model", new RegisterModel());		
			}
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem", "Ocorreu um erro: " + e.getMessage());
		}		
		
		return modelAndView;
	}
}







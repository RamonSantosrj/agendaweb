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

	// M�todo para mapear a p�gina de cadastro de usu�rio
	@RequestMapping(value = "/register") // URL da p�gina (ROTA)
	public ModelAndView register() {

		// define a p�gina .jsp que ser� aberta atraves desta rota
		//// WEB-INF/views/register.jsp
		ModelAndView modelAndView = new ModelAndView("register");
		
		//criando um objeto da classe RegisterModel
		modelAndView.addObject("model", new RegisterModel());		
		return modelAndView;
	}
	
	//M�todo para receber a requisi��o POST realizada no SUBMIT do formul�rio
	@RequestMapping(value = "/cadastrar-usuario", method = RequestMethod.POST)
	public ModelAndView cadastrarUsuario(RegisterModel model) {
		
		ModelAndView modelAndView = new ModelAndView("register");
		
		try {
			
			UsuarioRepository usuarioRepository = new UsuarioRepository();
			
			//verificar se j� existe um usu�rio cadastrado com o email informado
			if(usuarioRepository.find(model.getEmail()) != null) {
				modelAndView.addObject("erro_email", "O email informado j� est� cadastrado, tente outro.");
				modelAndView.addObject("model", model);
			}
			else {
				
				Usuario usuario = new Usuario();
				
				usuario.setNome(model.getNome());
				usuario.setEmail(model.getEmail());
				usuario.setSenha(model.getSenha());			
				
				usuarioRepository.create(usuario);
				
				modelAndView.addObject("mensagem", "Parab�ns " + usuario.getNome() + ", sua conta foi criada com sucesso!");
				modelAndView.addObject("model", new RegisterModel());		
			}
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem", "Ocorreu um erro: " + e.getMessage());
		}		
		
		return modelAndView;
	}
}







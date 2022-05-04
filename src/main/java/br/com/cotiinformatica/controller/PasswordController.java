package br.com.cotiinformatica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.models.PasswordModel;

@Controller
public class PasswordController {

	// Método para mapear a página de recuperação de senha de usuário
	@RequestMapping(value = "/password") // URL da página (ROTA)
	public ModelAndView password() {

		// define a página .jsp que será aberta atraves desta rota
		//// WEB-INF/views/password.jsp
		ModelAndView modelAndView = new ModelAndView("password");
		
		modelAndView.addObject("model", new PasswordModel());
		return modelAndView;
	}

}

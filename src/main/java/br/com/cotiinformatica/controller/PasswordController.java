package br.com.cotiinformatica.controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.messages.MailMessage;
import br.com.cotiinformatica.models.PasswordModel;
import br.com.cotiinformatica.repositories.UsuarioRepository;

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

	@RequestMapping(value = "/recuperar-senha", method = RequestMethod.POST)
	public ModelAndView recuperarSenha(PasswordModel model) {

		ModelAndView modelAndView = new ModelAndView("password");

		try {
			
			//buscar o usuario no banco de dados atraves do email
			UsuarioRepository usuarioRepository = new UsuarioRepository();
			Usuario usuario = usuarioRepository.find(model.getEmail());
			
			//verificando se o usuário foi encontrado
			if(usuario != null) {
				
				usuario.setSenha(getRandom()); //gerando uma nova senha
				enviarEmailDeRecuperacaoDeSenha(usuario); //enviando o email
				usuarioRepository.update(usuario.getIdUsuario(), usuario.getSenha());
				
				modelAndView.addObject("mensagem", "Recuperação de senha realizada com sucesso, por favor verifique sua conta de email.");
			}
			else {
				modelAndView.addObject("mensagem", "O email informado não está cadastrado, por favor verifique.");
			}
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem", "Ocorreu um erro: " + e.getMessage());
		}
		
		modelAndView.addObject("model", new PasswordModel());
		return modelAndView;		
	}
	
	//método para executar o envio do email de recuperação de senha
	private void enviarEmailDeRecuperacaoDeSenha(Usuario usuario) throws Exception {
		
		String assunto = "Recuperação de senha acesso - COTI Informática";
		
		String mensagem  = "Olá " + usuario.getNome();
			   mensagem += "\n\nSua senha foi atualizada para: " + usuario.getSenha();
			   mensagem += "\nPor favor, utilize esta senha para acessar a agenda.";
			   mensagem += "\nAo acessar você poderá atualizar sua senha para uma nova senha.";
			   mensagem += "\n\n";
			   mensagem += "Att\nEquipe COTI Informática";
		
       MailMessage.send(usuario.getEmail(), assunto, mensagem);
	}
	
	//método para gerar uma senha aleatória para o usuario
	private String getRandom() {
		return String.valueOf(new Random().nextInt(999999999));
	}
}



